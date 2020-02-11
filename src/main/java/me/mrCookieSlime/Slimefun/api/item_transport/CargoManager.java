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
        DirtyChestMenu menu;
        try {
            menu = Slimefun.runSyncCallable(() -> getChestMenu(target)).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (menu != null) {
            for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.WITHDRAW, null)) {
                ItemStack is;
                try {
                    is = Slimefun.runSyncCallable(() -> menu.getItemInSlot(slot)).get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

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
            BlockState state;
            try {
                state = Slimefun.runSyncCallable(target::getState).get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (state instanceof InventoryHolder) {
                Inventory inv;
                ItemStack[] invContents;
                try {
                    inv = Slimefun.runSyncCallable(((InventoryHolder) state)::getInventory).get();
                    invContents = Slimefun.runSyncCallable(inv::getContents).get();
                } catch (Exception e) {
                    throw new RuntimeException();
                }
                int minSlot = 0;
                int maxSlot = invContents.length;

                if (inv instanceof FurnaceInventory) {
                    minSlot = 2;
                    maxSlot = 3;
                } else if (inv instanceof BrewerInventory) {
                    maxSlot = 3;
                }
                for (int slot = minSlot; slot < maxSlot; slot++) {
                    ItemStack is = invContents[slot].clone();

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
        DirtyChestMenu menu;
        try {
            menu = Slimefun.runSyncCallable(() -> getChestMenu(target)).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (menu != null) {
            for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.WITHDRAW, null)) {
                ItemStack is;
                try {
                    is = Slimefun.runSyncCallable(() -> menu.getItemInSlot(slot)).get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                if (matchesFilter(node, is, index)) {
                    Slimefun.runSync(() -> menu.replaceExistingItem(slot, null));
                    return new ItemSlot(is.clone(), slot);
                }
            }
        } else {
            BlockState state;
            try {
                state = Slimefun.runSyncCallable(target::getState).get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (state instanceof InventoryHolder) {
                Inventory inv;
                try {
                    inv = Slimefun.runSyncCallable(((InventoryHolder) state)::getInventory).get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                ItemStack[] invContents;
                try {
                    invContents = Slimefun.runSyncCallable(inv::getContents).get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                int minSlot = 0;
                int maxSlot = invContents.length;

                if (inv instanceof FurnaceInventory) {
                    minSlot = 2;
                    maxSlot = 3;
                } else if (inv instanceof BrewerInventory) {
                    maxSlot = 3;
                }

                for (int slot = minSlot; slot < maxSlot; slot++) {
                    ItemStack is = invContents[slot];

                    if (is != null && matchesFilter(node, is, index)) {
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

        DirtyChestMenu menu;
        try {
            menu = Slimefun.runSyncCallable(() -> getChestMenu(target)).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (menu != null) {
            for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.INSERT, stack)) {
                ItemStack itemStack;
                try {
                    itemStack = Slimefun.runSyncCallable(() -> menu.getItemInSlot(slot)).get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                ItemStack is = (itemStack == null) ? null : itemStack.clone();

                if (is == null) {
                    ItemStack stack1 = stack.clone();
                    Slimefun.runSync(() -> menu.replaceExistingItem(slot, stack1));
                    return null;
                } else if (SlimefunManager.isItemSimilar(new CustomItem(is, 1), new CustomItem(stack, 1), true) && is.getAmount() < is.getType().getMaxStackSize()) {
                    int amount = is.getAmount() + stack.getAmount();

                    if (amount > is.getType().getMaxStackSize()) {
                        ItemStack finalStack1 = stack;
                        Slimefun.runSync(() -> {
                            is.setAmount(is.getType().getMaxStackSize());
                            finalStack1.setAmount(amount - is.getType().getMaxStackSize());
                        });
                    } else {
                        Slimefun.runSync(() -> is.setAmount(amount));
                        stack = null;
                    }

                    Slimefun.runSync(() -> menu.replaceExistingItem(slot, is));
                    return stack;
                }
            }
        } else {
            BlockState state;
            try {
                state = Slimefun.runSyncCallable(target::getState).get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (state instanceof InventoryHolder) {
                Inventory inv;
                ItemStack[] invContents;
                try {
                    inv = Slimefun.runSyncCallable(((InventoryHolder) state)::getInventory).get();
                    invContents = Slimefun.runSyncCallable(inv::getContents).get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                int minSlot = 0;
                int maxSlot = invContents.length;

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
                    ItemStack is = invContents[slot];

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
//        if (item == null) return false;

        String id;
        try {
            id = Slimefun.runSyncCallable(() -> BlockStorage.checkID(block)).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (id.equals("CARGO_NODE_OUTPUT")) return true;

        // Store the returned Config instance to avoid heavy calls
        @SuppressWarnings("deprecation")
        Config blockInfo = BlockStorage.getLocationInfo(block.getLocation());

        BlockMenu menu;
        try {
            menu = Slimefun.runSyncCallable(() -> BlockStorage.getInventory(block.getLocation())).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        boolean lore = "true".equals(blockInfo.getString("filter-lore"));

        if ("whitelist".equals(blockInfo.getString("filter-type"))) {
            List<ItemStack> items = new ArrayList<>();

            for (int slot : SLOTS) {
                ItemStack template;
                try {
                    template = Slimefun.runSyncCallable(() -> menu.getItemInSlot(slot)).get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
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
