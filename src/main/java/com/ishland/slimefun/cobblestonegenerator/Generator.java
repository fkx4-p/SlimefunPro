package com.ishland.slimefun.cobblestonegenerator;

import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import io.github.thebusybiscuit.slimefun4.utils.holograms.SimpleHologram;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.interfaces.InventoryBlock;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.interfaces.RecipeDisplayItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class Generator extends AContainer implements InventoryBlock, RecipeDisplayItem {

    public static final SlimefunItemStack COBBLESTONE_GENERATOR =
            new SlimefunItemStack("COBBLESTONE_GENERATOR", Material.COBBLESTONE, "&cCobblestone Generator",
                    "", "&eLots of cobblestone", "",
                    LoreBuilder.machine(MachineTier.AVERAGE, MachineType.MACHINE),
                    "&8\u21E8 &7Speed: 1x",
                    LoreBuilder.powerPerSecond(8)
            );
    public static final SlimefunItemStack COBBLESTONE_GENERATOR_2 =
            new SlimefunItemStack("COBBLESTONE_GENERATOR_2", Material.COBBLESTONE,
                    "&cCobblestone Generator &r- &eII",
                    "",
                    "&eLots of cobblestone", "",
                    LoreBuilder.machine(MachineTier.GOOD, MachineType.MACHINE),
                    "&8\u21E8 &7Speed: 4x",
                    LoreBuilder.powerPerSecond(24)
            );
    public static final SlimefunItemStack COBBLESTONE_GENERATOR_3 =
            new SlimefunItemStack("COBBLESTONE_GENERATOR_3", Material.COBBLESTONE,
                    "&cCobblestone Generator &r- &eIII",
                    "",
                    "&eLots of cobblestone", "",
                    LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE),
                    "&8\u21E8 &7Speed: 16x",
                    LoreBuilder.powerPerSecond(96)
            );
    public static final SlimefunItemStack COBBLESTONE_GENERATOR_4 =
            new SlimefunItemStack("COBBLESTONE_GENERATOR_4", Material.COBBLESTONE,
                    "&cCobblestone Generator &r- &eIV",
                    "",
                    "&eLots of cobblestone", "",
                    LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE),
                    "&8\u21E8 &7Speed: 64x",
                    LoreBuilder.powerPerSecond(384)
            );
    public static final SlimefunItemStack COBBLESTONE_GENERATOR_5 =
            new SlimefunItemStack("COBBLESTONE_GENERATOR_5", Material.COBBLESTONE,
                    "&cCobblestone Generator &r- &eV",
                    "",
                    "&eLots of cobblestone", "",
                    LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE),
                    "&8\u21E8 &7Speed: 256x",
                    LoreBuilder.powerPerSecond(1536)
            );

    private static final int[] BORDER = {0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 26, 27, 35, 36, 44, 45, 53};
    private static final int[] OUTPUT_BORDER = {19, 20, 21, 22, 23, 24, 25, 28, 34, 37, 43, 46, 47, 48, 49, 50, 51, 52};
    private static final int[] OUTPUT_SLOTS = {29, 30, 31, 32, 33, 38, 39, 40, 41, 42};

    public Generator(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        registerBlockHandler(getID(), (p, b, item1, reason) -> {
            SimpleHologram.remove(b);

            BlockMenu inv = BlockStorage.getInventory(b);

            if (inv != null) {
                for (int slot : getInputSlots()) {
                    if (inv.getItemInSlot(slot) != null) {
                        b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                        inv.replaceExistingItem(slot, null);
                    }
                }

                for (int slot : getOutputSlots()) {
                    if (inv.getItemInSlot(slot) != null) {
                        b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                        inv.replaceExistingItem(slot, null);
                    }
                }
            }

            progress.remove(b);
            processing.remove(b);
            return true;
        });
    }

    /**
     * This method returns the title that is used for the {@link Inventory} of an
     * {@link AContainer} that has been opened by a Player.
     * <p>
     * Override this method to set the title.
     *
     * @return The title of the {@link Inventory} of this {@link AContainer}
     */
    @Override
    public String getInventoryTitle() {
        return "Cobblestone Generator";
    }

    /**
     * This method returns the {@link ItemStack} that this {@link AContainer} will
     * use as a progress bar.
     * <p>
     * Override this method to set the progress bar.
     *
     * @return The {@link ItemStack} to use as the progress bar
     */
    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.DIAMOND_PICKAXE);
    }

    /**
     * This method returns an internal identifier that is used to identify this {@link AContainer}
     * and its recipes.
     * <p>
     * When adding recipes to an {@link AContainer} we will use this identifier to
     * identify all instances of the same {@link AContainer}.
     * This way we can add the recipes to all instances of the same machine.
     *
     * @return The identifier of this machine
     */
    @Override
    public String getMachineIdentifier() {
        return "COBBLESTONE_GENERATOR";
    }

    @Override
    public int[] getInputSlots() {
        return new int[0];
    }

    @Override
    public int[] getOutputSlots() {
        return OUTPUT_SLOTS;
    }

    @Override
    protected void constructMenu(BlockMenuPreset preset) {
        for (int i : BORDER) {
            preset.addItem(i, new CustomItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), " "), (p, slot, item, action) -> false);
        }

        for (int i : OUTPUT_BORDER) {
            preset.addItem(i, new CustomItem(new ItemStack(Material.ORANGE_STAINED_GLASS_PANE), " "), (p, slot, item, action) -> false);
        }

        preset.addItem(4, new CustomItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE), " "), (p, slot, item, action) -> false);

        for (int i : getOutputSlots()) {
            preset.addMenuClickHandler(i, new ChestMenu.AdvancedMenuClickHandler() {

                @Override
                public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
                    return false;
                }

                @Override
                public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
                    if (cursor == null) return true;
                    return cursor.getType() == Material.AIR;
                }
            });
        }
    }

    private ItemStack[] getCobblestone(int amount) {
        List<ItemStack> items = new ArrayList<>();
        for (int i = 0; i < amount; i += 64)
            items.add(new ItemStack(Material.COBBLESTONE, 64));
        items.add(new ItemStack(Material.COBBLESTONE, amount % 64));

        ItemStack[] result = new ItemStack[items.size()];
        for (int i = 0; i < items.size(); i++)
            result[i] = items.get(i);
        return result;
    }

    @Override
    protected void tick(Block b) {
        BlockMenu menu = BlockStorage.getInventory(b);

        if (isProcessing(b)) {
            int timeLeft = progress.get(b);

            if (timeLeft > 0) {
                ChestMenuUtils.updateProgressbar(menu, 4, timeLeft, processing.get(b).getTicks(), getProgressBar());

                if (ChargableBlock.getCharge(b) < getEnergyConsumption()) return;
                ChargableBlock.addCharge(b, -getEnergyConsumption());

                progress.put(b, timeLeft - 1);
            } else {
                menu.replaceExistingItem(4, new CustomItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE), " "));
                final ItemStack[] outputs = processing.get(b).getOutput();
                for (ItemStack output : outputs)
                    menu.pushItem(output, getOutputSlots());

                progress.remove(b);
                processing.remove(b);
            }
        } else {

            MachineRecipe r = new MachineRecipe(1, new ItemStack[0],
                    getCobblestone(getSpeed()));
            if (!menu.fits(r.getOutput()[0], getOutputSlots())) return;

            processing.put(b, r);
            progress.put(b, r.getTicks());

        }
    }

}
