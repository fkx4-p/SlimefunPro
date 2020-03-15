package io.github.thebusybiscuit.slimefun4.implementation.items.cargo;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNet;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.utils.holograms.SimpleHologram;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockUseHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.item_transport.CargoNet;
import me.mrCookieSlime.Slimefun.api.item_transport.cache.AttachedBlockCache;
import me.mrCookieSlime.Slimefun.api.item_transport.cache.BlockStateCache;
import me.mrCookieSlime.Slimefun.api.item_transport.cache.CacheGC;
import me.mrCookieSlime.Slimefun.api.item_transport.cache.InventoryCache;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

public class CargoManager extends SlimefunItem implements EnergyNetComponent {

    public CargoManager(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        registerBlockHandler(getID(), (p, b, tool, reason) -> {
            SimpleHologram.remove(b);
            return true;
        });
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {

            @Override
            @SuppressWarnings("deprecation")
            public void tick(Block b, SlimefunItem item, Config data) {
                CargoNet.getNetworkFromLocationOrCreate(b.getLocation()).tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return false;
            }

        }, new BlockUseHandler() {

            private static final String visualizerKey = "visualizer";
            private Map<Location, Boolean> purges = new ConcurrentHashMap<>();

            @Override
            public void onRightClick(PlayerRightClickEvent e) {
                Optional<Block> block = e.getClickedBlock();

                if (block.isPresent()) {
                    Player p = e.getPlayer();
                    Block b = block.get();

                    if (!e.getPlayer().isSneaking()) {
                        if (BlockStorage.getLocationInfo(b.getLocation(), visualizerKey) == null) {
                            BlockStorage.addBlockInfo(b, visualizerKey, "disabled");
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCargo Net Visualizer: " + "&4\u2718"));
                        } else {
                            BlockStorage.addBlockInfo(b, visualizerKey, null);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCargo Net Visualizer: " + "&2\u2714"));
                        }
                    } else {
                        Boolean isPurging = purges.get(b.getLocation());
                        if (isPurging != null && isPurging) return;
                        purges.put(b.getLocation(), true);
                        p.sendTitle("CargoNet fix queued", "", 10, 80, 10);
                        CacheGC.cleanThread.execute(() -> {
                            p.sendTitle("CargoNet fix started", "Progress: ...", 0, 80, 10);
                            CargoNet instance = CargoNet.getNetworkFromLocationOrCreate(b.getLocation());
                            Set<Location> inputs = instance.getInputNodes();
                            Set<Location> outputs = instance.getOutputNodes();
                            int nodeCount = inputs.size() + outputs.size();
                            List<Location> nodes = new ArrayList<>(nodeCount);
                            nodes.addAll(inputs);
                            nodes.addAll(outputs);
                            long lastReport = System.currentTimeMillis();
                            int fixed = 0;
                            int skipped = 0;
                            int errored = 0;
                            for (int i = 0; i < nodeCount; i++) {
                                Location location = nodes.get(i);
                                try {
                                    if (location == null) {
                                        skipped++;
                                        continue;
                                    }
                                    AttachedBlockCache.remove(location);
                                    final Block attachedBlock = AttachedBlockCache.query(location.getBlock());
                                    if (attachedBlock == null) continue; // In case there is no block attached to it
                                    final Location location1 = attachedBlock.getLocation();
                                    BlockStateCache.remove(location1);
                                    InventoryCache.remove(location1);
                                    BlockState blockState = BlockStateCache.query(location1.getBlock());
                                    if (blockState instanceof Container)
                                        InventoryCache.query((Container) blockState);
                                    fixed++;
                                } catch (Throwable ex) {
                                    p.sendMessage("CargoNet fix failed for " + location + ": " + ex.toString()
                                            + " [See console] ");
                                    Slimefun.getLogger().log(Level.WARNING, "CargoNet fix failed " + location, ex);
                                    errored++;
                                }
                                if (System.currentTimeMillis() - lastReport > 500) {
                                    lastReport = System.currentTimeMillis();
                                    p.sendTitle("CargoNet fix running", "Progress: " + i / nodeCount + "%",
                                            0, 80, 10);
                                }
                            }
                            p.sendTitle("CargoNet fix completed", fixed + " fixed, "
                                    + skipped + " skipped, " + errored + " errored", 0, 80, 20);
                            purges.put(b.getLocation(), false);
                        });
                    }
                }
            }
        });
    }

    /**
     * This method returns the Type of {@link EnergyNetComponentType} this {@link SlimefunItem} represents.
     * It describes how this Block will interact with an {@link EnergyNet}.
     *
     * @return The {@link EnergyNetComponentType} this {@link SlimefunItem} represents.
     */
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    /**
     * This method returns the max amount of electricity this Block can hold.
     * If the capacity is zero, then this Block cannot hold any electricity.
     *
     * @return The max amount of electricity this Block can store.
     */
    @Override
    public int getCapacity() {
        return 8192;
    }
}
