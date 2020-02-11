package me.mrCookieSlime.Slimefun.api.item_transport;

import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.*;

import java.util.ArrayList;
import java.util.List;

public final class CargoManager {

    //Whitelist or blacklist slots
    private static final int[] SLOTS = new int[]{19, 20, 21, 28, 29, 30, 37, 38, 39};

    private CargoManager() {
    }

    public static ItemStack withdraw(Block node, Block target, ItemStack template) {
        DirtyChestMenu menu = getChestMenu(target); // TODO may not async safe

        if (menu != null) {
            for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.WITHDRAW, null)) {
                ItemStack is = menu.getItemInSlot(slot); // TODO may not async safe

                if (SlimefunManager.isItemSimilar(is, template, true) && matchesFilter(node, is, -1)) {
                    if (is.getAmount() > template.getAmount()) {
                        Slimefun.runSync(() -> menu.replaceExistingItem(slot, new CustomItem(is, is.getAmount() - template.getAmount())));
                        return template;
                    } else {
                        Slimefun.runSync(() -> menu.replaceExistingItem(slot, null));
                        return is.clone();
                    }
                }
            }
        } else {
            BlockState state = null;
            try {
                state = Slimefun.runSyncCallable(target::getState).get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (state instanceof InventoryHolder) {
                Inventory inv = ((InventoryHolder) state).getInventory(); // TODO may not async safe
                int minSlot = 0;
                int maxSlot = inv.getContents().length;

                if (inv instanceof FurnaceInventory) {
                    minSlot = 2;
                    maxSlot = 3;
                } else if (inv instanceof BrewerInventory) {
                    maxSlot = 3;
                }
                for (int slot = minSlot; slot < maxSlot; slot++) {
                    ItemStack is = inv.getContents()[slot].clone(); // TODO may not async safe

                    if (SlimefunManager.isItemSimilar(is, template, true) && matchesFilter(node, is, -1)) {
                        if (is.getAmount() > template.getAmount()) {
                            int finalSlot = slot;
                            Slimefun.runSync(() -> inv.setItem(finalSlot, new CustomItem(is, is.getAmount() - template.getAmount())));
                            return template;
                        } else {
                            int finalSlot1 = slot;
                            Slimefun.runSync(() -> inv.setItem(finalSlot1, new CustomItem(is, is.getAmount() - template.getAmount())));
                            return is.clone();
                        }
                    }
                }
            }
        }
        return null;
    }

    public static ItemSlot withdraw(Block node, Block target, int index) { // Async safe
        DirtyChestMenu menu = getChestMenu(target); // TODO may not async safe

        if (menu != null) {
            for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.WITHDRAW, null)) {
                ItemStack is = menu.getItemInSlot(slot); // TODO may not async safe

                if (matchesFilter(node, is, index)) {
                    Slimefun.runSync(() -> menu.replaceExistingItem(slot, null));
                    return new ItemSlot(is.clone(), slot);
                }
            }
        } else {
            BlockState state = null;
            try {
                state = Slimefun.runSyncCallable(target::getState).get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (state instanceof InventoryHolder) {
                Inventory inv = ((InventoryHolder) state).getInventory(); // TODO may not async safe

                int minSlot = 0;
                int maxSlot = inv.getContents().length;

                if (inv instanceof FurnaceInventory) {
                    minSlot = 2;
                    maxSlot = 3;
                } else if (inv instanceof BrewerInventory) {
                    maxSlot = 3;
                }

                for (int slot = minSlot; slot < maxSlot; slot++) {
                    ItemStack is = inv.getContents()[slot].clone(); // TODO may not async safe

                    if (matchesFilter(node, is, index)) {
                        int slotI = slot;
                        Slimefun.runSync(() -> inv.setItem(slotI, null));
                        return new ItemSlot(is.clone(), slot);
                    }
                }
            }
        }
        return null;
    }

    public static ItemStack insert(Block node, Block target, ItemStack stack, int index) {
        if (!matchesFilter(node, stack, index)) return stack;

        DirtyChestMenu menu = getChestMenu(target); // TODO may not async safe

        if (menu != null) {
            for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.INSERT, stack)) {
                ItemStack is = menu.getItemInSlot(slot) == null ? null : menu.getItemInSlot(slot).clone(); // TODO may not async safe

                if (is == null) {
                    ItemStack stack1 = stack.clone();
                    Slimefun.runSync(() -> menu.replaceExistingItem(slot, stack1));
                    return null;
                } else if (SlimefunManager.isItemSimilar(new CustomItem(is, 1), new CustomItem(stack, 1), true) && is.getAmount() < is.getType().getMaxStackSize()) {
                    int amount = is.getAmount() + stack.getAmount();

                    if (amount > is.getType().getMaxStackSize()) {
                        is.setAmount(is.getType().getMaxStackSize());
                        stack.setAmount(amount - is.getType().getMaxStackSize());
                    } else {
                        is.setAmount(amount);
                        stack = null;
                    }

                    Slimefun.runSync(() -> menu.replaceExistingItem(slot, is));
                    return stack;
                }
            }
        } else {
            BlockState state = null;
            try {
                state = Slimefun.runSyncCallable(target::getState).get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (state instanceof InventoryHolder) {
                Inventory inv = ((InventoryHolder) state).getInventory(); // TODO may not async safe

                int minSlot = 0;
                int maxSlot = inv.getContents().length; // TODO may not async safe

                //Check if it is a normal furnace
                if (inv instanceof FurnaceInventory) {
                    //Check if it is fuel or not
                    if (stack.getType().isFuel()) {
                        minSlot = 1;
                        maxSlot = 2;
                    } else {
                        maxSlot = 1;
                    }
                } else if (inv instanceof BrewerInventory) {
                    //Check if it goes in the potion slot,
                    if (stack.getType() == Material.POTION || stack.getType() == Material.LINGERING_POTION || stack.getType() == Material.SPLASH_POTION) {
                        maxSlot = 3;
                        //The blaze powder slot,
                    } else if (stack.getType() == Material.BLAZE_POWDER) {
                        minSlot = 4;
                        maxSlot = 5;
                    } else {
                        //Or the input
                        minSlot = 3;
                        maxSlot = 4;
                    }
                }

                for (int slot = minSlot; slot < maxSlot; slot++) {
                    ItemStack is = inv.getContents()[slot]; // TODO may not async safe

                    if (is == null) {
                        int finalSlot = slot;
                        ItemStack finalStack = stack;
                        Slimefun.runSync(() -> inv.setItem(finalSlot, finalStack.clone()));
                        return null;
                    } else if (SlimefunManager.isItemSimilar(new CustomItem(is, 1), new CustomItem(stack, 1), true) && is.getAmount() < is.getType().getMaxStackSize()) {
                        is = is.clone();
                        int amount = is.getAmount() + stack.getAmount();

                        if (amount > is.getType().getMaxStackSize()) {
                            is.setAmount(is.getType().getMaxStackSize());
                            stack.setAmount(amount - is.getType().getMaxStackSize());
                        } else {
                            is.setAmount(amount);
                            stack = null;
                        }

                        int finalSlot1 = slot;
                        ItemStack finalIs = is;
                        Slimefun.runSync(() -> inv.setItem(finalSlot1, finalIs));
                        return stack;
                    }
                }
            }
        }

        return stack;
    }

    public static DirtyChestMenu getChestMenu(Block block) {
        if (BlockStorage.hasInventory(block)) {
            return BlockStorage.getInventory(block);
        }

        return BlockStorage.getUniversalInventory(block);
    }

    public static boolean matchesFilter(Block block, ItemStack item, int index) { // Async safe
        if (item == null) return false;

        String id = BlockStorage.checkID(block);
        if (id.equals("CARGO_NODE_OUTPUT")) return true;

        // Store the returned Config instance to avoid heavy calls
        Config blockInfo = BlockStorage.getLocationInfo(block.getLocation());

        BlockMenu menu = BlockStorage.getInventory(block.getLocation());
        boolean lore = "true".equals(blockInfo.getString("filter-lore"));

        if ("whitelist".equals(blockInfo.getString("filter-type"))) {
            List<ItemStack> items = new ArrayList<>();

            for (int slot : SLOTS) {
                ItemStack template = menu.getItemInSlot(slot);
                if (template != null) items.add(new CustomItem(template, 1));
            }

            if (items.isEmpty()) {
                return false;
            }

            if (index >= 0) {
                index++;
                if (index > (items.size() - 1)) index = 0;

                BlockStorage.addBlockInfo(block, "index", String.valueOf(index));

                return SlimefunManager.isItemSimilar(item, items.get(index), lore);
            } else {
                for (ItemStack stack : items) {
                    if (SlimefunManager.isItemSimilar(item, stack, lore)) return true;
                }

                return false;
            }
        } else {
            for (int slot : SLOTS) {
                if (menu.getItemInSlot(slot) != null && SlimefunManager.isItemSimilar(item, new CustomItem(menu.getItemInSlot(slot), 1), lore)) {
                    return false;
                }
            }

            return true;
        }
    }

}
