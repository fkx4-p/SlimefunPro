package io.github.thebusybiscuit.slimefun4.core.services.plugins;

import me.mrCookieSlime.Slimefun.api.Slimefun;
import net.coreprotect.CoreProtectAPI;
import net.coreprotect.Functions;
import net.coreprotect.consumer.Queue;
import net.coreprotect.model.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;

public class CoreProtectHook {
    private static CoreProtectAPI api;

    public static void start() {
        CoreProtectAPI coreProtect = ((net.coreprotect.CoreProtect)
                Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("CoreProtect"))).getAPI();
        if (!coreProtect.isEnabled() || coreProtect.APIVersion() < 6)
            throw new IllegalArgumentException();
        api = coreProtect;
    }

    public static boolean logInventory(String user, Inventory inventory, Location location) {
        try {
            ItemStack[] inventoryData = inventory.getContents();

            int x = location.getBlockX();
            int y = location.getBlockY();
            int z = location.getBlockZ();
            Iterator<org.bukkit.entity.HumanEntity> var11 = inventory.getViewers().iterator();

            while (true) {
                String transacting_chest_id;
                String logging_chest_id;
                int size_old;
                List<ItemStack[]> list;
                do {
                    do {
                        HumanEntity viewer;
                        do {
                            if (!var11.hasNext()) {
                                transacting_chest_id = location.getWorld().getUID().toString() + "." + x + "." + y + "." + z;
                                logging_chest_id = user.toLowerCase(Locale.ENGLISH) + "." + x + "." + y + "." + z;
                                int chest_id = Config.logging_chest.getOrDefault(logging_chest_id, -1) + 1;
                                if (chest_id > 0) {
                                    if (Config.force_containers.get(logging_chest_id) != null) {
                                        int force_size = Config.force_containers.get(logging_chest_id).size();
                                        list = Config.old_container.get(logging_chest_id);
                                        if (list.size() > force_size) {
                                            list.set(force_size, Functions.get_container_state(inventoryData));
                                        } else {
                                            list.add(Functions.get_container_state(inventoryData));
                                        }

                                        Config.old_container.put(logging_chest_id, list);
                                    }
                                } else {
                                    list = new ArrayList<>();
                                    list.add(Functions.get_container_state(inventoryData));
                                    Config.old_container.put(logging_chest_id, list);
                                }

                                Config.transacting_chest.computeIfAbsent(transacting_chest_id, (k) -> new ArrayList<>());
                                Config.logging_chest.put(logging_chest_id, chest_id);

                                {
                                    Class<Queue> queueClass = Queue.class;
                                    Method method = queueClass.getDeclaredMethod("queueContainerTransaction",
                                            String.class, BlockState.class, Material.class, Object.class, int.class);
                                    method.invoke(null, user,
                                            location.getBlock().getState(), Material.CHEST, inventory, chest_id);
                                }

                                return true;
                            }

                            viewer = var11.next();
                        } while (viewer.getName().equals(user));

                        transacting_chest_id = location.getWorld().getUID().toString() + "." + x + "." + y + "." + z;
                        logging_chest_id = viewer.getName().toLowerCase(Locale.ENGLISH) + "." + x + "." + y + "." + z;
                    } while (Config.old_container.get(logging_chest_id) == null);

                    size_old = Config.old_container.get(logging_chest_id).size();
                    Config.force_containers.computeIfAbsent(logging_chest_id, k -> new ArrayList<>());

                    list = Config.force_containers.get(logging_chest_id);
                } while (list.size() >= size_old);

                ItemStack[] containerState = Functions.get_container_state(inventoryData);
                if (Config.transacting_chest.get(transacting_chest_id) != null) {
                    List<ItemStack> transacting_chest_list = Config.transacting_chest.get(transacting_chest_id);
                    if (transacting_chest_list.size() > 0) {
                        ItemStack[] new_state = new ItemStack[containerState.length + transacting_chest_list.size()];
                        int count = 0;

                        for (int j = 0; j < containerState.length; ++j) {
                            new_state[j] = containerState[j];
                            ++count;
                        }

                        for (Iterator<ItemStack> var31 = (new ArrayList<>(transacting_chest_list)).iterator();
                             var31.hasNext(); ++count) {
                            ItemStack item = var31.next();
                            new_state[count] = item;
                        }

                        containerState = new_state;
                    }
                }

                list.add(containerState);
                Config.force_containers.put(logging_chest_id, list);
            }
        } catch (Exception e) {
            Slimefun.getLogger().log(Level.WARNING, "Error while recording data with CoreProtect", e);
            return false;
        }
    }
}
