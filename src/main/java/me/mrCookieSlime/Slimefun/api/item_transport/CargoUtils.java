package me.mrCookieSlime.Slimefun.api.item_transport;

import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.cache.BlockStateCache;
import me.mrCookieSlime.Slimefun.api.item_transport.cache.InventoryCache;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.inventory.*;

import java.util.ArrayList;
import java.util.List;

final class CargoUtils {

    //Whitelist or blacklist slots
    private static final int[] SLOTS = {19, 20, 21, 28, 29, 30, 37, 38, 39};

    private CargoUtils() {}

    public static ItemStack withdraw(Block node, Block target, ItemStack template) {
        DirtyChestMenu menu;
        try {
            menu = getChestMenu(target);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (menu != null) {
            for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.WITHDRAW, null)) {
                ItemStack is;
                try {
                    is = menu.getItemInSlot(slot);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                if (SlimefunManager.isItemSimilar(is, template, true) && matchesFilter(node, is, -1)) {
                    if (is.getAmount() > template.getAmount()) {
                        try {
                            menu.replaceExistingItem(slot, new CustomItem(is, is.getAmount() - template.getAmount()));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        return template;
                    } else {
                        try {
                            menu.replaceExistingItem(slot, null);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        return is.clone();
                    }
                }
            }
        } else {
            BlockState state;
            try {
                state = BlockStateCache.query(target);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (state instanceof Container) {
                Inventory inv;
                ItemStack[] invContents;
                try {
                    inv = InventoryCache.query((Container) state).inventory;
                    invContents = inv.getContents();
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
                    return withdrawFromVanillaInventory(node, template, ((InventoryHolder) state).getInventory());
                }
            }

            return null;
        }
        return null;
    }

    private static ItemStack withdrawFromVanillaInventory(Block node, ItemStack template, Inventory inv) {
        int minSlot = 0;
        int maxSlot = inv.getContents().length;

        if (inv instanceof FurnaceInventory) {
            minSlot = 2;
            maxSlot = 3;
        } else if (inv instanceof BrewerInventory) {
            maxSlot = 3;
        }

        for (int slot = minSlot; slot < maxSlot; slot++) {
            ItemStack is = inv.getContents()[slot];

            if (SlimefunManager.isItemSimilar(is, template, true) && matchesFilter(node, is, -1)) {
                if (is.getAmount() > template.getAmount()) {
                    int finalSlot = slot;
                    try {
                        Slimefun.runSyncFuture(() -> inv.setItem(finalSlot,
                                new CustomItem(is, is.getAmount() - template.getAmount())));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return template;
                } else {
                    int finalSlot1 = slot;
                    try {
                        Slimefun.runSyncFuture(() -> inv.setItem(finalSlot1,
                                new CustomItem(is, is.getAmount() - template.getAmount())));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return is.clone();
                }
            }
        }

        return null;
    }

    public static ItemStackAndInteger withdraw(Block node, Block target, int index) { // Async safe
        DirtyChestMenu menu;
        try {
            menu = getChestMenu(target);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (menu != null) {
            for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.WITHDRAW, null)) {
                ItemStack is;
                try {
                    is = menu.getItemInSlot(slot);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                if (is != null && matchesFilter(node, is, index)) {
                    try {
                        menu.replaceExistingItem(slot, null);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return new ItemStackAndInteger(is.clone(), slot);
                }
            }
        } else {
            BlockState state;
            try {
                state = BlockStateCache.query(target);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (state instanceof Container) {
                Inventory inv;
                try {
                    inv = InventoryCache.query((Container) state).inventory;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                ItemStack[] invContents;
                try {
                    invContents = inv.getContents();
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
                        try {
                            Slimefun.runSyncFuture(() -> inv.setItem(slotI, null)).get();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        return new ItemStackAndInteger(is.clone(), slot);
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
            menu = getChestMenu(target);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (menu != null) {
            for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.INSERT, stack)) {
                ItemStack itemStack;
                try {
                    itemStack = menu.getItemInSlot(slot);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                ItemStack is = (itemStack == null) ? null : itemStack.clone();

                if (is == null) {
                    ItemStack stack1 = stack.clone();
                    try {
                        menu.replaceExistingItem(slot, stack1);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                } else if (SlimefunManager.isItemSimilar(new CustomItem(is, 1), new CustomItem(stack, 1), true) && is.getAmount() < is.getType().getMaxStackSize()) {
                    int amount = is.getAmount() + stack.getAmount();

                    if (amount > is.getType().getMaxStackSize()) {
                        ItemStack finalStack1 = stack;
                        try {
                            is.setAmount(is.getType().getMaxStackSize());
                            finalStack1.setAmount(amount - is.getType().getMaxStackSize());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        try {
                            is.setAmount(amount);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        stack = null;
                    }

                    try {
                        menu.replaceExistingItem(slot, is);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return stack;
                }
            }
        } else {
            BlockState state;
            try {
                state = BlockStateCache.query(target);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (state instanceof Container) {
                Inventory inv;
                ItemStack[] invContents;
                try {
                    inv = InventoryCache.query((Container) state).inventory;
                    invContents = inv.getContents();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return insertIntoVanillaInventory(invContents, stack, inv);
            }
        }

        return stack;
    }

    private static ItemStack insertIntoVanillaInventory(ItemStack[] invContents, ItemStack stack, Inventory inv) {
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
                try {
                    Slimefun.runSyncFuture(() -> inv.setItem(finalSlot, finalStack.clone())).get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
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
                try {
                    Slimefun.runSyncFuture(() -> inv.setItem(finalSlot1, finalIs)).get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return stack;
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

    public static boolean matchesFilter(Block block, ItemStack item, int index) {
        if (item == null) return false;

        String id;
        try {
            id = BlockStorage.checkID(block);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (id.equals("CARGO_NODE_OUTPUT")) return true;

        // Store the returned Config instance to avoid heavy calls
        @SuppressWarnings("deprecation")
        Config blockInfo = BlockStorage.getLocationInfo(block.getLocation());
        if (blockInfo.getString("id").equals("CARGO_NODE_OUTPUT")) return true;

        BlockMenu menu;
        try {
            menu = BlockStorage.getInventory(block.getLocation());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        boolean lore = "true".equals(blockInfo.getString("filter-lore"));

        if ("whitelist".equals(blockInfo.getString("filter-type"))) {
            List<ItemStack> items = new ArrayList<>();

            for (int slot : SLOTS) {
                ItemStack template;
                try {
                    template = menu.getItemInSlot(slot);
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
                    if (SlimefunManager.isItemSimilar(item, stack, lore)) {
                        return true;
                    }
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
