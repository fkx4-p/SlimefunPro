package io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines;

import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import io.github.thebusybiscuit.cscorelib2.protection.ProtectableAction;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public abstract class ItemDecompressor extends AContainer {

    public ItemDecompressor(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        new BlockMenuPreset(getID(), getInventoryTitle()) {

            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public boolean canOpen(Block b, Player p) {
                return p.hasPermission("slimefun.inventory.bypass") || SlimefunPlugin.getProtectionManager().hasPermission(p, b.getLocation(), ProtectableAction.ACCESS_INVENTORIES);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                return new int[0];
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(DirtyChestMenu menu, ItemTransportFlow flow, ItemStack item) {
                if (flow == ItemTransportFlow.WITHDRAW) return getOutputSlots();

                List<Integer> slots = new ArrayList<>();

                for (int slot : getInputSlots()) {
                    if (SlimefunManager.isItemSimilar(menu.getItemInSlot(slot), item, true)) {
                        slots.add(slot);
                    }
                }

                if (slots.isEmpty()) {
                    return getInputSlots();
                } else {
                    Collections.sort(slots, compareSlots(menu));
                    int[] array = new int[slots.size()];

                    for (int i = 0; i < slots.size(); i++) {
                        array[i] = slots.get(i);
                    }

                    return array;
                }
            }
        };

        this.registerDefaultRecipes();
    }

    private Comparator<Integer> compareSlots(DirtyChestMenu menu) {
        return (slot1, slot2) -> menu.getItemInSlot(slot1).getAmount() - menu.getItemInSlot(slot2).getAmount();
    }

    @Override
    protected void registerDefaultRecipes() {
        // registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COBBLESTONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.COBBLESTONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COBBLESTONE_1}, new ItemStack[]{new CustomItem(SlimefunItems.COMPRESSED_COBBLESTONE, 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ACACIA_BUTTON}, new ItemStack[]{new CustomItem(new ItemStack(Material.ACACIA_BUTTON), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ACACIA_DOOR}, new ItemStack[]{new CustomItem(new ItemStack(Material.ACACIA_DOOR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ACACIA_FENCE}, new ItemStack[]{new CustomItem(new ItemStack(Material.ACACIA_FENCE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ACACIA_FENCE_GATE}, new ItemStack[]{new CustomItem(new ItemStack(Material.ACACIA_FENCE_GATE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ACACIA_LEAVES}, new ItemStack[]{new CustomItem(new ItemStack(Material.ACACIA_LEAVES), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ACACIA_LOG}, new ItemStack[]{new CustomItem(new ItemStack(Material.ACACIA_LOG), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ACACIA_PLANKS}, new ItemStack[]{new CustomItem(new ItemStack(Material.ACACIA_PLANKS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ACACIA_PRESSURE_PLATE}, new ItemStack[]{new CustomItem(new ItemStack(Material.ACACIA_PRESSURE_PLATE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ACACIA_SAPLING}, new ItemStack[]{new CustomItem(new ItemStack(Material.ACACIA_SAPLING), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ACACIA_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.ACACIA_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ACACIA_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.ACACIA_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ACACIA_TRAPDOOR}, new ItemStack[]{new CustomItem(new ItemStack(Material.ACACIA_TRAPDOOR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ACACIA_WOOD}, new ItemStack[]{new CustomItem(new ItemStack(Material.ACACIA_WOOD), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ACTIVATOR_RAIL}, new ItemStack[]{new CustomItem(new ItemStack(Material.ACTIVATOR_RAIL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ALLIUM}, new ItemStack[]{new CustomItem(new ItemStack(Material.ALLIUM), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ANDESITE}, new ItemStack[]{new CustomItem(new ItemStack(Material.ANDESITE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ANDESITE_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.ANDESITE_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ANDESITE_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.ANDESITE_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ANDESITE_WALL}, new ItemStack[]{new CustomItem(new ItemStack(Material.ANDESITE_WALL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ANVIL}, new ItemStack[]{new CustomItem(new ItemStack(Material.ANVIL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_APPLE}, new ItemStack[]{new CustomItem(new ItemStack(Material.APPLE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ARROW}, new ItemStack[]{new CustomItem(new ItemStack(Material.ARROW), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_AZURE_BLUET}, new ItemStack[]{new CustomItem(new ItemStack(Material.AZURE_BLUET), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BAKED_POTATO}, new ItemStack[]{new CustomItem(new ItemStack(Material.BAKED_POTATO), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BAMBOO}, new ItemStack[]{new CustomItem(new ItemStack(Material.BAMBOO), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BARREL}, new ItemStack[]{new CustomItem(new ItemStack(Material.BARREL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BEACON}, new ItemStack[]{new CustomItem(new ItemStack(Material.BEACON), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BEEF}, new ItemStack[]{new CustomItem(new ItemStack(Material.BEEF), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BEEHIVE}, new ItemStack[]{new CustomItem(new ItemStack(Material.BEEHIVE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BEETROOT}, new ItemStack[]{new CustomItem(new ItemStack(Material.BEETROOT), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BEETROOT_SEEDS}, new ItemStack[]{new CustomItem(new ItemStack(Material.BEETROOT_SEEDS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BEE_NEST}, new ItemStack[]{new CustomItem(new ItemStack(Material.BEE_NEST), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BELL}, new ItemStack[]{new CustomItem(new ItemStack(Material.BELL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BIRCH_BUTTON}, new ItemStack[]{new CustomItem(new ItemStack(Material.BIRCH_BUTTON), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BIRCH_DOOR}, new ItemStack[]{new CustomItem(new ItemStack(Material.BIRCH_DOOR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BIRCH_FENCE}, new ItemStack[]{new CustomItem(new ItemStack(Material.BIRCH_FENCE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BIRCH_FENCE_GATE}, new ItemStack[]{new CustomItem(new ItemStack(Material.BIRCH_FENCE_GATE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BIRCH_LEAVES}, new ItemStack[]{new CustomItem(new ItemStack(Material.BIRCH_LEAVES), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BIRCH_LOG}, new ItemStack[]{new CustomItem(new ItemStack(Material.BIRCH_LOG), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BIRCH_PLANKS}, new ItemStack[]{new CustomItem(new ItemStack(Material.BIRCH_PLANKS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BIRCH_PRESSURE_PLATE}, new ItemStack[]{new CustomItem(new ItemStack(Material.BIRCH_PRESSURE_PLATE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BIRCH_SAPLING}, new ItemStack[]{new CustomItem(new ItemStack(Material.BIRCH_SAPLING), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BIRCH_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.BIRCH_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BIRCH_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.BIRCH_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BIRCH_TRAPDOOR}, new ItemStack[]{new CustomItem(new ItemStack(Material.BIRCH_TRAPDOOR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BIRCH_WOOD}, new ItemStack[]{new CustomItem(new ItemStack(Material.BIRCH_WOOD), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLACK_CARPET}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLACK_CARPET), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLACK_CONCRETE}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLACK_CONCRETE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLACK_CONCRETE_POWDER}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLACK_CONCRETE_POWDER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLACK_DYE}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLACK_DYE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLACK_GLAZED_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLACK_GLAZED_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLACK_STAINED_GLASS}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLACK_STAINED_GLASS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLACK_STAINED_GLASS_PANE}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLACK_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLACK_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLACK_WOOL}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLACK_WOOL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLAST_FURNACE}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLAST_FURNACE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLAZE_POWDER}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLAZE_POWDER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLAZE_ROD}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLAZE_ROD), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLUE_CARPET}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLUE_CARPET), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLUE_CONCRETE}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLUE_CONCRETE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLUE_CONCRETE_POWDER}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLUE_CONCRETE_POWDER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLUE_DYE}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLUE_DYE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLUE_GLAZED_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLUE_GLAZED_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLUE_ICE}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLUE_ICE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLUE_ORCHID}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLUE_ORCHID), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLUE_STAINED_GLASS}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLUE_STAINED_GLASS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLUE_STAINED_GLASS_PANE}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLUE_STAINED_GLASS_PANE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLUE_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLUE_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLUE_WOOL}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLUE_WOOL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.BONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BONE_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.BONE_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BONE_MEAL}, new ItemStack[]{new CustomItem(new ItemStack(Material.BONE_MEAL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BOOK}, new ItemStack[]{new CustomItem(new ItemStack(Material.BOOK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BOOKSHELF}, new ItemStack[]{new CustomItem(new ItemStack(Material.BOOKSHELF), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BOWL}, new ItemStack[]{new CustomItem(new ItemStack(Material.BOWL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BRAIN_CORAL}, new ItemStack[]{new CustomItem(new ItemStack(Material.BRAIN_CORAL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BRAIN_CORAL_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.BRAIN_CORAL_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BRAIN_CORAL_FAN}, new ItemStack[]{new CustomItem(new ItemStack(Material.BRAIN_CORAL_FAN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BREAD}, new ItemStack[]{new CustomItem(new ItemStack(Material.BREAD), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BREWING_STAND}, new ItemStack[]{new CustomItem(new ItemStack(Material.BREWING_STAND), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BRICK}, new ItemStack[]{new CustomItem(new ItemStack(Material.BRICK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BRICKS}, new ItemStack[]{new CustomItem(new ItemStack(Material.BRICKS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BRICK_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.BRICK_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BRICK_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.BRICK_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BRICK_WALL}, new ItemStack[]{new CustomItem(new ItemStack(Material.BRICK_WALL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BROWN_CARPET}, new ItemStack[]{new CustomItem(new ItemStack(Material.BROWN_CARPET), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BROWN_CONCRETE}, new ItemStack[]{new CustomItem(new ItemStack(Material.BROWN_CONCRETE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BROWN_CONCRETE_POWDER}, new ItemStack[]{new CustomItem(new ItemStack(Material.BROWN_CONCRETE_POWDER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BROWN_DYE}, new ItemStack[]{new CustomItem(new ItemStack(Material.BROWN_DYE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BROWN_GLAZED_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.BROWN_GLAZED_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BROWN_MUSHROOM}, new ItemStack[]{new CustomItem(new ItemStack(Material.BROWN_MUSHROOM), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BROWN_MUSHROOM_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.BROWN_MUSHROOM_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BROWN_STAINED_GLASS}, new ItemStack[]{new CustomItem(new ItemStack(Material.BROWN_STAINED_GLASS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BROWN_STAINED_GLASS_PANE}, new ItemStack[]{new CustomItem(new ItemStack(Material.BROWN_STAINED_GLASS_PANE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BROWN_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.BROWN_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BROWN_WOOL}, new ItemStack[]{new CustomItem(new ItemStack(Material.BROWN_WOOL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BUBBLE_CORAL}, new ItemStack[]{new CustomItem(new ItemStack(Material.BUBBLE_CORAL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BUBBLE_CORAL_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.BUBBLE_CORAL_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BUBBLE_CORAL_FAN}, new ItemStack[]{new CustomItem(new ItemStack(Material.BUBBLE_CORAL_FAN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CACTUS}, new ItemStack[]{new CustomItem(new ItemStack(Material.CACTUS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CAMPFIRE}, new ItemStack[]{new CustomItem(new ItemStack(Material.CAMPFIRE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CARROT}, new ItemStack[]{new CustomItem(new ItemStack(Material.CARROT), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CARTOGRAPHY_TABLE}, new ItemStack[]{new CustomItem(new ItemStack(Material.CARTOGRAPHY_TABLE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CARVED_PUMPKIN}, new ItemStack[]{new CustomItem(new ItemStack(Material.CARVED_PUMPKIN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CAULDRON}, new ItemStack[]{new CustomItem(new ItemStack(Material.CAULDRON), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CHARCOAL}, new ItemStack[]{new CustomItem(new ItemStack(Material.CHARCOAL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CHEST}, new ItemStack[]{new CustomItem(new ItemStack(Material.CHEST), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CHICKEN}, new ItemStack[]{new CustomItem(new ItemStack(Material.CHICKEN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CHIPPED_ANVIL}, new ItemStack[]{new CustomItem(new ItemStack(Material.CHIPPED_ANVIL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CHISELED_QUARTZ_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.CHISELED_QUARTZ_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CHISELED_RED_SANDSTONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.CHISELED_RED_SANDSTONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CHISELED_SANDSTONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.CHISELED_SANDSTONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CHISELED_STONE_BRICKS}, new ItemStack[]{new CustomItem(new ItemStack(Material.CHISELED_STONE_BRICKS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CHORUS_FLOWER}, new ItemStack[]{new CustomItem(new ItemStack(Material.CHORUS_FLOWER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CHORUS_FRUIT}, new ItemStack[]{new CustomItem(new ItemStack(Material.CHORUS_FRUIT), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CLAY}, new ItemStack[]{new CustomItem(new ItemStack(Material.CLAY), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CLAY_BALL}, new ItemStack[]{new CustomItem(new ItemStack(Material.CLAY_BALL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COAL}, new ItemStack[]{new CustomItem(new ItemStack(Material.COAL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COAL_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.COAL_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COAL_ORE}, new ItemStack[]{new CustomItem(new ItemStack(Material.COAL_ORE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COARSE_DIRT}, new ItemStack[]{new CustomItem(new ItemStack(Material.COARSE_DIRT), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COBBLESTONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.COBBLESTONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COBBLESTONE_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.COBBLESTONE_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COBBLESTONE_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.COBBLESTONE_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COBBLESTONE_WALL}, new ItemStack[]{new CustomItem(new ItemStack(Material.COBBLESTONE_WALL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COBWEB}, new ItemStack[]{new CustomItem(new ItemStack(Material.COBWEB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COCOA_BEANS}, new ItemStack[]{new CustomItem(new ItemStack(Material.COCOA_BEANS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COMPARATOR}, new ItemStack[]{new CustomItem(new ItemStack(Material.COMPARATOR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COMPASS}, new ItemStack[]{new CustomItem(new ItemStack(Material.COMPASS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COMPOSTER}, new ItemStack[]{new CustomItem(new ItemStack(Material.COMPOSTER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CONDUIT}, new ItemStack[]{new CustomItem(new ItemStack(Material.CONDUIT), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COOKED_BEEF}, new ItemStack[]{new CustomItem(new ItemStack(Material.COOKED_BEEF), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COOKED_CHICKEN}, new ItemStack[]{new CustomItem(new ItemStack(Material.COOKED_CHICKEN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COOKED_COD}, new ItemStack[]{new CustomItem(new ItemStack(Material.COOKED_COD), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COOKED_MUTTON}, new ItemStack[]{new CustomItem(new ItemStack(Material.COOKED_MUTTON), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COOKED_PORKCHOP}, new ItemStack[]{new CustomItem(new ItemStack(Material.COOKED_PORKCHOP), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COOKED_RABBIT}, new ItemStack[]{new CustomItem(new ItemStack(Material.COOKED_RABBIT), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COOKED_SALMON}, new ItemStack[]{new CustomItem(new ItemStack(Material.COOKED_SALMON), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COOKIE}, new ItemStack[]{new CustomItem(new ItemStack(Material.COOKIE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CORNFLOWER}, new ItemStack[]{new CustomItem(new ItemStack(Material.CORNFLOWER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CRACKED_STONE_BRICKS}, new ItemStack[]{new CustomItem(new ItemStack(Material.CRACKED_STONE_BRICKS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CRAFTING_TABLE}, new ItemStack[]{new CustomItem(new ItemStack(Material.CRAFTING_TABLE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CUT_RED_SANDSTONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.CUT_RED_SANDSTONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CUT_RED_SANDSTONE_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.CUT_RED_SANDSTONE_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CUT_SANDSTONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.CUT_SANDSTONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CUT_SANDSTONE_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.CUT_SANDSTONE_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CYAN_CARPET}, new ItemStack[]{new CustomItem(new ItemStack(Material.CYAN_CARPET), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CYAN_CONCRETE}, new ItemStack[]{new CustomItem(new ItemStack(Material.CYAN_CONCRETE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CYAN_CONCRETE_POWDER}, new ItemStack[]{new CustomItem(new ItemStack(Material.CYAN_CONCRETE_POWDER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CYAN_DYE}, new ItemStack[]{new CustomItem(new ItemStack(Material.CYAN_DYE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CYAN_GLAZED_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.CYAN_GLAZED_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CYAN_STAINED_GLASS}, new ItemStack[]{new CustomItem(new ItemStack(Material.CYAN_STAINED_GLASS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CYAN_STAINED_GLASS_PANE}, new ItemStack[]{new CustomItem(new ItemStack(Material.CYAN_STAINED_GLASS_PANE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CYAN_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.CYAN_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CYAN_WOOL}, new ItemStack[]{new CustomItem(new ItemStack(Material.CYAN_WOOL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DAMAGED_ANVIL}, new ItemStack[]{new CustomItem(new ItemStack(Material.DAMAGED_ANVIL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DANDELION}, new ItemStack[]{new CustomItem(new ItemStack(Material.DANDELION), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DARK_OAK_BUTTON}, new ItemStack[]{new CustomItem(new ItemStack(Material.DARK_OAK_BUTTON), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DARK_OAK_DOOR}, new ItemStack[]{new CustomItem(new ItemStack(Material.DARK_OAK_DOOR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DARK_OAK_FENCE}, new ItemStack[]{new CustomItem(new ItemStack(Material.DARK_OAK_FENCE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DARK_OAK_FENCE_GATE}, new ItemStack[]{new CustomItem(new ItemStack(Material.DARK_OAK_FENCE_GATE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DARK_OAK_LEAVES}, new ItemStack[]{new CustomItem(new ItemStack(Material.DARK_OAK_LEAVES), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DARK_OAK_LOG}, new ItemStack[]{new CustomItem(new ItemStack(Material.DARK_OAK_LOG), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DARK_OAK_PLANKS}, new ItemStack[]{new CustomItem(new ItemStack(Material.DARK_OAK_PLANKS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DARK_OAK_PRESSURE_PLATE}, new ItemStack[]{new CustomItem(new ItemStack(Material.DARK_OAK_PRESSURE_PLATE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DARK_OAK_SAPLING}, new ItemStack[]{new CustomItem(new ItemStack(Material.DARK_OAK_SAPLING), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DARK_OAK_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.DARK_OAK_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DARK_OAK_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.DARK_OAK_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DARK_OAK_TRAPDOOR}, new ItemStack[]{new CustomItem(new ItemStack(Material.DARK_OAK_TRAPDOOR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DARK_OAK_WOOD}, new ItemStack[]{new CustomItem(new ItemStack(Material.DARK_OAK_WOOD), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DARK_PRISMARINE}, new ItemStack[]{new CustomItem(new ItemStack(Material.DARK_PRISMARINE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DARK_PRISMARINE_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.DARK_PRISMARINE_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DARK_PRISMARINE_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.DARK_PRISMARINE_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DAYLIGHT_DETECTOR}, new ItemStack[]{new CustomItem(new ItemStack(Material.DAYLIGHT_DETECTOR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DEAD_BRAIN_CORAL}, new ItemStack[]{new CustomItem(new ItemStack(Material.DEAD_BRAIN_CORAL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DEAD_BRAIN_CORAL_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.DEAD_BRAIN_CORAL_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DEAD_BRAIN_CORAL_FAN}, new ItemStack[]{new CustomItem(new ItemStack(Material.DEAD_BRAIN_CORAL_FAN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DEAD_BUBBLE_CORAL}, new ItemStack[]{new CustomItem(new ItemStack(Material.DEAD_BUBBLE_CORAL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DEAD_BUBBLE_CORAL_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.DEAD_BUBBLE_CORAL_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DEAD_BUBBLE_CORAL_FAN}, new ItemStack[]{new CustomItem(new ItemStack(Material.DEAD_BUBBLE_CORAL_FAN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DEAD_FIRE_CORAL}, new ItemStack[]{new CustomItem(new ItemStack(Material.DEAD_FIRE_CORAL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DEAD_FIRE_CORAL_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.DEAD_FIRE_CORAL_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DEAD_FIRE_CORAL_FAN}, new ItemStack[]{new CustomItem(new ItemStack(Material.DEAD_FIRE_CORAL_FAN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DEAD_HORN_CORAL}, new ItemStack[]{new CustomItem(new ItemStack(Material.DEAD_HORN_CORAL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DEAD_HORN_CORAL_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.DEAD_HORN_CORAL_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DEAD_HORN_CORAL_FAN}, new ItemStack[]{new CustomItem(new ItemStack(Material.DEAD_HORN_CORAL_FAN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DEAD_TUBE_CORAL}, new ItemStack[]{new CustomItem(new ItemStack(Material.DEAD_TUBE_CORAL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DEAD_TUBE_CORAL_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.DEAD_TUBE_CORAL_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DEAD_TUBE_CORAL_FAN}, new ItemStack[]{new CustomItem(new ItemStack(Material.DEAD_TUBE_CORAL_FAN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DETECTOR_RAIL}, new ItemStack[]{new CustomItem(new ItemStack(Material.DETECTOR_RAIL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DIAMOND}, new ItemStack[]{new CustomItem(new ItemStack(Material.DIAMOND), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DIAMOND_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.DIAMOND_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DIAMOND_ORE}, new ItemStack[]{new CustomItem(new ItemStack(Material.DIAMOND_ORE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DIORITE}, new ItemStack[]{new CustomItem(new ItemStack(Material.DIORITE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DIORITE_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.DIORITE_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DIORITE_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.DIORITE_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DIORITE_WALL}, new ItemStack[]{new CustomItem(new ItemStack(Material.DIORITE_WALL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DIRT}, new ItemStack[]{new CustomItem(new ItemStack(Material.DIRT), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DISPENSER}, new ItemStack[]{new CustomItem(new ItemStack(Material.DISPENSER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DRIED_KELP}, new ItemStack[]{new CustomItem(new ItemStack(Material.DRIED_KELP), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DRIED_KELP_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.DRIED_KELP_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DROPPER}, new ItemStack[]{new CustomItem(new ItemStack(Material.DROPPER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_EMERALD}, new ItemStack[]{new CustomItem(new ItemStack(Material.EMERALD), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_EMERALD_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.EMERALD_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_EMERALD_ORE}, new ItemStack[]{new CustomItem(new ItemStack(Material.EMERALD_ORE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ENCHANTED_GOLDEN_APPLE}, new ItemStack[]{new CustomItem(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ENCHANTING_TABLE}, new ItemStack[]{new CustomItem(new ItemStack(Material.ENCHANTING_TABLE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ENDER_CHEST}, new ItemStack[]{new CustomItem(new ItemStack(Material.ENDER_CHEST), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_END_CRYSTAL}, new ItemStack[]{new CustomItem(new ItemStack(Material.END_CRYSTAL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_END_ROD}, new ItemStack[]{new CustomItem(new ItemStack(Material.END_ROD), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_END_STONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.END_STONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_END_STONE_BRICKS}, new ItemStack[]{new CustomItem(new ItemStack(Material.END_STONE_BRICKS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_END_STONE_BRICK_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.END_STONE_BRICK_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_END_STONE_BRICK_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.END_STONE_BRICK_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_END_STONE_BRICK_WALL}, new ItemStack[]{new CustomItem(new ItemStack(Material.END_STONE_BRICK_WALL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_EXPERIENCE_BOTTLE}, new ItemStack[]{new CustomItem(new ItemStack(Material.EXPERIENCE_BOTTLE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_FEATHER}, new ItemStack[]{new CustomItem(new ItemStack(Material.FEATHER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_FERMENTED_SPIDER_EYE}, new ItemStack[]{new CustomItem(new ItemStack(Material.FERMENTED_SPIDER_EYE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_FERN}, new ItemStack[]{new CustomItem(new ItemStack(Material.FERN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_FIREWORK_ROCKET}, new ItemStack[]{new CustomItem(new ItemStack(Material.FIREWORK_ROCKET), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_FIREWORK_STAR}, new ItemStack[]{new CustomItem(new ItemStack(Material.FIREWORK_STAR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_FIRE_CHARGE}, new ItemStack[]{new CustomItem(new ItemStack(Material.FIRE_CHARGE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_FIRE_CORAL}, new ItemStack[]{new CustomItem(new ItemStack(Material.FIRE_CORAL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_FIRE_CORAL_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.FIRE_CORAL_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_FIRE_CORAL_FAN}, new ItemStack[]{new CustomItem(new ItemStack(Material.FIRE_CORAL_FAN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_FLETCHING_TABLE}, new ItemStack[]{new CustomItem(new ItemStack(Material.FLETCHING_TABLE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_FLINT}, new ItemStack[]{new CustomItem(new ItemStack(Material.FLINT), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_FLOWER_POT}, new ItemStack[]{new CustomItem(new ItemStack(Material.FLOWER_POT), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_FURNACE}, new ItemStack[]{new CustomItem(new ItemStack(Material.FURNACE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GHAST_TEAR}, new ItemStack[]{new CustomItem(new ItemStack(Material.GHAST_TEAR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GLASS}, new ItemStack[]{new CustomItem(new ItemStack(Material.GLASS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GLASS_PANE}, new ItemStack[]{new CustomItem(new ItemStack(Material.GLASS_PANE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GLISTERING_MELON_SLICE}, new ItemStack[]{new CustomItem(new ItemStack(Material.GLISTERING_MELON_SLICE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GLOWSTONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.GLOWSTONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GLOWSTONE_DUST}, new ItemStack[]{new CustomItem(new ItemStack(Material.GLOWSTONE_DUST), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GOLDEN_APPLE}, new ItemStack[]{new CustomItem(new ItemStack(Material.GOLDEN_APPLE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GOLDEN_CARROT}, new ItemStack[]{new CustomItem(new ItemStack(Material.GOLDEN_CARROT), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GOLD_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.GOLD_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GOLD_INGOT}, new ItemStack[]{new CustomItem(new ItemStack(Material.GOLD_INGOT), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GOLD_NUGGET}, new ItemStack[]{new CustomItem(new ItemStack(Material.GOLD_NUGGET), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GOLD_ORE}, new ItemStack[]{new CustomItem(new ItemStack(Material.GOLD_ORE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GRANITE}, new ItemStack[]{new CustomItem(new ItemStack(Material.GRANITE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GRANITE_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.GRANITE_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GRANITE_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.GRANITE_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GRANITE_WALL}, new ItemStack[]{new CustomItem(new ItemStack(Material.GRANITE_WALL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GRASS}, new ItemStack[]{new CustomItem(new ItemStack(Material.GRASS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GRASS_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.GRASS_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GRASS_PATH}, new ItemStack[]{new CustomItem(new ItemStack(Material.GRASS_PATH), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GRAVEL}, new ItemStack[]{new CustomItem(new ItemStack(Material.GRAVEL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GRAY_CARPET}, new ItemStack[]{new CustomItem(new ItemStack(Material.GRAY_CARPET), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GRAY_CONCRETE}, new ItemStack[]{new CustomItem(new ItemStack(Material.GRAY_CONCRETE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GRAY_CONCRETE_POWDER}, new ItemStack[]{new CustomItem(new ItemStack(Material.GRAY_CONCRETE_POWDER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GRAY_DYE}, new ItemStack[]{new CustomItem(new ItemStack(Material.GRAY_DYE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GRAY_GLAZED_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.GRAY_GLAZED_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GRAY_STAINED_GLASS}, new ItemStack[]{new CustomItem(new ItemStack(Material.GRAY_STAINED_GLASS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GRAY_STAINED_GLASS_PANE}, new ItemStack[]{new CustomItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GRAY_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.GRAY_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GRAY_WOOL}, new ItemStack[]{new CustomItem(new ItemStack(Material.GRAY_WOOL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GREEN_CARPET}, new ItemStack[]{new CustomItem(new ItemStack(Material.GREEN_CARPET), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GREEN_CONCRETE}, new ItemStack[]{new CustomItem(new ItemStack(Material.GREEN_CONCRETE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GREEN_CONCRETE_POWDER}, new ItemStack[]{new CustomItem(new ItemStack(Material.GREEN_CONCRETE_POWDER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GREEN_DYE}, new ItemStack[]{new CustomItem(new ItemStack(Material.GREEN_DYE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GREEN_GLAZED_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.GREEN_GLAZED_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GREEN_STAINED_GLASS}, new ItemStack[]{new CustomItem(new ItemStack(Material.GREEN_STAINED_GLASS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GREEN_STAINED_GLASS_PANE}, new ItemStack[]{new CustomItem(new ItemStack(Material.GREEN_STAINED_GLASS_PANE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GREEN_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.GREEN_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GREEN_WOOL}, new ItemStack[]{new CustomItem(new ItemStack(Material.GREEN_WOOL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GRINDSTONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.GRINDSTONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GUNPOWDER}, new ItemStack[]{new CustomItem(new ItemStack(Material.GUNPOWDER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_HAY_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.HAY_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_HEAVY_WEIGHTED_PRESSURE_PLATE}, new ItemStack[]{new CustomItem(new ItemStack(Material.HEAVY_WEIGHTED_PRESSURE_PLATE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_HONEYCOMB}, new ItemStack[]{new CustomItem(new ItemStack(Material.HONEYCOMB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_HONEYCOMB_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.HONEYCOMB_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_HONEY_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.HONEY_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_HOPPER}, new ItemStack[]{new CustomItem(new ItemStack(Material.HOPPER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_HORN_CORAL}, new ItemStack[]{new CustomItem(new ItemStack(Material.HORN_CORAL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_HORN_CORAL_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.HORN_CORAL_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_HORN_CORAL_FAN}, new ItemStack[]{new CustomItem(new ItemStack(Material.HORN_CORAL_FAN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ICE}, new ItemStack[]{new CustomItem(new ItemStack(Material.ICE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_INK_SAC}, new ItemStack[]{new CustomItem(new ItemStack(Material.INK_SAC), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_IRON_BARS}, new ItemStack[]{new CustomItem(new ItemStack(Material.IRON_BARS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_IRON_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.IRON_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_IRON_DOOR}, new ItemStack[]{new CustomItem(new ItemStack(Material.IRON_DOOR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_IRON_INGOT}, new ItemStack[]{new CustomItem(new ItemStack(Material.IRON_INGOT), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_IRON_NUGGET}, new ItemStack[]{new CustomItem(new ItemStack(Material.IRON_NUGGET), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_IRON_ORE}, new ItemStack[]{new CustomItem(new ItemStack(Material.IRON_ORE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_IRON_TRAPDOOR}, new ItemStack[]{new CustomItem(new ItemStack(Material.IRON_TRAPDOOR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ITEM_FRAME}, new ItemStack[]{new CustomItem(new ItemStack(Material.ITEM_FRAME), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_JACK_O_LANTERN}, new ItemStack[]{new CustomItem(new ItemStack(Material.JACK_O_LANTERN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_JUKEBOX}, new ItemStack[]{new CustomItem(new ItemStack(Material.JUKEBOX), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_JUNGLE_BUTTON}, new ItemStack[]{new CustomItem(new ItemStack(Material.JUNGLE_BUTTON), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_JUNGLE_DOOR}, new ItemStack[]{new CustomItem(new ItemStack(Material.JUNGLE_DOOR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_JUNGLE_FENCE}, new ItemStack[]{new CustomItem(new ItemStack(Material.JUNGLE_FENCE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_JUNGLE_FENCE_GATE}, new ItemStack[]{new CustomItem(new ItemStack(Material.JUNGLE_FENCE_GATE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_JUNGLE_LEAVES}, new ItemStack[]{new CustomItem(new ItemStack(Material.JUNGLE_LEAVES), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_JUNGLE_LOG}, new ItemStack[]{new CustomItem(new ItemStack(Material.JUNGLE_LOG), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_JUNGLE_PLANKS}, new ItemStack[]{new CustomItem(new ItemStack(Material.JUNGLE_PLANKS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_JUNGLE_PRESSURE_PLATE}, new ItemStack[]{new CustomItem(new ItemStack(Material.JUNGLE_PRESSURE_PLATE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_JUNGLE_SAPLING}, new ItemStack[]{new CustomItem(new ItemStack(Material.JUNGLE_SAPLING), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_JUNGLE_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.JUNGLE_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_JUNGLE_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.JUNGLE_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_JUNGLE_TRAPDOOR}, new ItemStack[]{new CustomItem(new ItemStack(Material.JUNGLE_TRAPDOOR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_JUNGLE_WOOD}, new ItemStack[]{new CustomItem(new ItemStack(Material.JUNGLE_WOOD), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_KELP}, new ItemStack[]{new CustomItem(new ItemStack(Material.KELP), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LADDER}, new ItemStack[]{new CustomItem(new ItemStack(Material.LADDER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LANTERN}, new ItemStack[]{new CustomItem(new ItemStack(Material.LANTERN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LAPIS_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.LAPIS_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LAPIS_LAZULI}, new ItemStack[]{new CustomItem(new ItemStack(Material.LAPIS_LAZULI), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LAPIS_ORE}, new ItemStack[]{new CustomItem(new ItemStack(Material.LAPIS_ORE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LARGE_FERN}, new ItemStack[]{new CustomItem(new ItemStack(Material.LARGE_FERN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LEAD}, new ItemStack[]{new CustomItem(new ItemStack(Material.LEAD), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LEATHER}, new ItemStack[]{new CustomItem(new ItemStack(Material.LEATHER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LECTERN}, new ItemStack[]{new CustomItem(new ItemStack(Material.LECTERN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LEVER}, new ItemStack[]{new CustomItem(new ItemStack(Material.LEVER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIGHT_BLUE_CARPET}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIGHT_BLUE_CARPET), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIGHT_BLUE_CONCRETE}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIGHT_BLUE_CONCRETE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIGHT_BLUE_CONCRETE_POWDER}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIGHT_BLUE_CONCRETE_POWDER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIGHT_BLUE_DYE}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIGHT_BLUE_DYE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIGHT_BLUE_GLAZED_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIGHT_BLUE_GLAZED_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIGHT_BLUE_STAINED_GLASS}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIGHT_BLUE_STAINED_GLASS_PANE}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIGHT_BLUE_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIGHT_BLUE_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIGHT_BLUE_WOOL}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIGHT_BLUE_WOOL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIGHT_GRAY_CARPET}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIGHT_GRAY_CARPET), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIGHT_GRAY_CONCRETE}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIGHT_GRAY_CONCRETE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIGHT_GRAY_CONCRETE_POWDER}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIGHT_GRAY_CONCRETE_POWDER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIGHT_GRAY_DYE}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIGHT_GRAY_DYE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIGHT_GRAY_GLAZED_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIGHT_GRAY_GLAZED_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIGHT_GRAY_STAINED_GLASS}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIGHT_GRAY_STAINED_GLASS_PANE}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIGHT_GRAY_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIGHT_GRAY_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIGHT_GRAY_WOOL}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIGHT_GRAY_WOOL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIGHT_WEIGHTED_PRESSURE_PLATE}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIGHT_WEIGHTED_PRESSURE_PLATE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LILAC}, new ItemStack[]{new CustomItem(new ItemStack(Material.LILAC), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LILY_OF_THE_VALLEY}, new ItemStack[]{new CustomItem(new ItemStack(Material.LILY_OF_THE_VALLEY), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LILY_PAD}, new ItemStack[]{new CustomItem(new ItemStack(Material.LILY_PAD), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIME_CARPET}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIME_CARPET), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIME_CONCRETE}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIME_CONCRETE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIME_CONCRETE_POWDER}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIME_CONCRETE_POWDER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIME_DYE}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIME_DYE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIME_GLAZED_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIME_GLAZED_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIME_STAINED_GLASS}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIME_STAINED_GLASS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIME_STAINED_GLASS_PANE}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIME_STAINED_GLASS_PANE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIME_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIME_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LIME_WOOL}, new ItemStack[]{new CustomItem(new ItemStack(Material.LIME_WOOL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LOOM}, new ItemStack[]{new CustomItem(new ItemStack(Material.LOOM), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MAGENTA_CARPET}, new ItemStack[]{new CustomItem(new ItemStack(Material.MAGENTA_CARPET), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MAGENTA_CONCRETE}, new ItemStack[]{new CustomItem(new ItemStack(Material.MAGENTA_CONCRETE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MAGENTA_CONCRETE_POWDER}, new ItemStack[]{new CustomItem(new ItemStack(Material.MAGENTA_CONCRETE_POWDER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MAGENTA_DYE}, new ItemStack[]{new CustomItem(new ItemStack(Material.MAGENTA_DYE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MAGENTA_GLAZED_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.MAGENTA_GLAZED_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MAGENTA_STAINED_GLASS}, new ItemStack[]{new CustomItem(new ItemStack(Material.MAGENTA_STAINED_GLASS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MAGENTA_STAINED_GLASS_PANE}, new ItemStack[]{new CustomItem(new ItemStack(Material.MAGENTA_STAINED_GLASS_PANE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MAGENTA_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.MAGENTA_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MAGENTA_WOOL}, new ItemStack[]{new CustomItem(new ItemStack(Material.MAGENTA_WOOL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MAGMA_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.MAGMA_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MAGMA_CREAM}, new ItemStack[]{new CustomItem(new ItemStack(Material.MAGMA_CREAM), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MELON}, new ItemStack[]{new CustomItem(new ItemStack(Material.MELON), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MELON_SEEDS}, new ItemStack[]{new CustomItem(new ItemStack(Material.MELON_SEEDS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MELON_SLICE}, new ItemStack[]{new CustomItem(new ItemStack(Material.MELON_SLICE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MOSSY_COBBLESTONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.MOSSY_COBBLESTONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MOSSY_COBBLESTONE_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.MOSSY_COBBLESTONE_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MOSSY_COBBLESTONE_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.MOSSY_COBBLESTONE_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MOSSY_COBBLESTONE_WALL}, new ItemStack[]{new CustomItem(new ItemStack(Material.MOSSY_COBBLESTONE_WALL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MOSSY_STONE_BRICKS}, new ItemStack[]{new CustomItem(new ItemStack(Material.MOSSY_STONE_BRICKS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MOSSY_STONE_BRICK_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.MOSSY_STONE_BRICK_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MOSSY_STONE_BRICK_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.MOSSY_STONE_BRICK_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MOSSY_STONE_BRICK_WALL}, new ItemStack[]{new CustomItem(new ItemStack(Material.MOSSY_STONE_BRICK_WALL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MUTTON}, new ItemStack[]{new CustomItem(new ItemStack(Material.MUTTON), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_MYCELIUM}, new ItemStack[]{new CustomItem(new ItemStack(Material.MYCELIUM), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_NAUTILUS_SHELL}, new ItemStack[]{new CustomItem(new ItemStack(Material.NAUTILUS_SHELL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_NETHERRACK}, new ItemStack[]{new CustomItem(new ItemStack(Material.NETHERRACK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_NETHER_BRICK}, new ItemStack[]{new CustomItem(new ItemStack(Material.NETHER_BRICK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_NETHER_BRICKS}, new ItemStack[]{new CustomItem(new ItemStack(Material.NETHER_BRICKS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_NETHER_BRICK_FENCE}, new ItemStack[]{new CustomItem(new ItemStack(Material.NETHER_BRICK_FENCE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_NETHER_BRICK_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.NETHER_BRICK_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_NETHER_BRICK_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.NETHER_BRICK_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_NETHER_BRICK_WALL}, new ItemStack[]{new CustomItem(new ItemStack(Material.NETHER_BRICK_WALL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_NETHER_QUARTZ_ORE}, new ItemStack[]{new CustomItem(new ItemStack(Material.NETHER_QUARTZ_ORE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_NETHER_STAR}, new ItemStack[]{new CustomItem(new ItemStack(Material.NETHER_STAR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_NETHER_WART}, new ItemStack[]{new CustomItem(new ItemStack(Material.NETHER_WART), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_NETHER_WART_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.NETHER_WART_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_NOTE_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.NOTE_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_OAK_BUTTON}, new ItemStack[]{new CustomItem(new ItemStack(Material.OAK_BUTTON), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_OAK_DOOR}, new ItemStack[]{new CustomItem(new ItemStack(Material.OAK_DOOR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_OAK_FENCE}, new ItemStack[]{new CustomItem(new ItemStack(Material.OAK_FENCE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_OAK_FENCE_GATE}, new ItemStack[]{new CustomItem(new ItemStack(Material.OAK_FENCE_GATE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_OAK_LEAVES}, new ItemStack[]{new CustomItem(new ItemStack(Material.OAK_LEAVES), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_OAK_LOG}, new ItemStack[]{new CustomItem(new ItemStack(Material.OAK_LOG), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_OAK_PLANKS}, new ItemStack[]{new CustomItem(new ItemStack(Material.OAK_PLANKS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_OAK_PRESSURE_PLATE}, new ItemStack[]{new CustomItem(new ItemStack(Material.OAK_PRESSURE_PLATE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_OAK_SAPLING}, new ItemStack[]{new CustomItem(new ItemStack(Material.OAK_SAPLING), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_OAK_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.OAK_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_OAK_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.OAK_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_OAK_TRAPDOOR}, new ItemStack[]{new CustomItem(new ItemStack(Material.OAK_TRAPDOOR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_OAK_WOOD}, new ItemStack[]{new CustomItem(new ItemStack(Material.OAK_WOOD), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_OBSERVER}, new ItemStack[]{new CustomItem(new ItemStack(Material.OBSERVER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_OBSIDIAN}, new ItemStack[]{new CustomItem(new ItemStack(Material.OBSIDIAN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ORANGE_CARPET}, new ItemStack[]{new CustomItem(new ItemStack(Material.ORANGE_CARPET), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ORANGE_CONCRETE}, new ItemStack[]{new CustomItem(new ItemStack(Material.ORANGE_CONCRETE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ORANGE_CONCRETE_POWDER}, new ItemStack[]{new CustomItem(new ItemStack(Material.ORANGE_CONCRETE_POWDER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ORANGE_DYE}, new ItemStack[]{new CustomItem(new ItemStack(Material.ORANGE_DYE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ORANGE_GLAZED_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.ORANGE_GLAZED_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ORANGE_STAINED_GLASS}, new ItemStack[]{new CustomItem(new ItemStack(Material.ORANGE_STAINED_GLASS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ORANGE_STAINED_GLASS_PANE}, new ItemStack[]{new CustomItem(new ItemStack(Material.ORANGE_STAINED_GLASS_PANE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ORANGE_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.ORANGE_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ORANGE_TULIP}, new ItemStack[]{new CustomItem(new ItemStack(Material.ORANGE_TULIP), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ORANGE_WOOL}, new ItemStack[]{new CustomItem(new ItemStack(Material.ORANGE_WOOL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_OXEYE_DAISY}, new ItemStack[]{new CustomItem(new ItemStack(Material.OXEYE_DAISY), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PACKED_ICE}, new ItemStack[]{new CustomItem(new ItemStack(Material.PACKED_ICE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PAINTING}, new ItemStack[]{new CustomItem(new ItemStack(Material.PAINTING), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PAPER}, new ItemStack[]{new CustomItem(new ItemStack(Material.PAPER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PEONY}, new ItemStack[]{new CustomItem(new ItemStack(Material.PEONY), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PHANTOM_MEMBRANE}, new ItemStack[]{new CustomItem(new ItemStack(Material.PHANTOM_MEMBRANE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PINK_CARPET}, new ItemStack[]{new CustomItem(new ItemStack(Material.PINK_CARPET), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PINK_CONCRETE}, new ItemStack[]{new CustomItem(new ItemStack(Material.PINK_CONCRETE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PINK_CONCRETE_POWDER}, new ItemStack[]{new CustomItem(new ItemStack(Material.PINK_CONCRETE_POWDER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PINK_DYE}, new ItemStack[]{new CustomItem(new ItemStack(Material.PINK_DYE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PINK_GLAZED_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.PINK_GLAZED_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PINK_STAINED_GLASS}, new ItemStack[]{new CustomItem(new ItemStack(Material.PINK_STAINED_GLASS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PINK_STAINED_GLASS_PANE}, new ItemStack[]{new CustomItem(new ItemStack(Material.PINK_STAINED_GLASS_PANE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PINK_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.PINK_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PINK_TULIP}, new ItemStack[]{new CustomItem(new ItemStack(Material.PINK_TULIP), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PINK_WOOL}, new ItemStack[]{new CustomItem(new ItemStack(Material.PINK_WOOL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PISTON}, new ItemStack[]{new CustomItem(new ItemStack(Material.PISTON), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PODZOL}, new ItemStack[]{new CustomItem(new ItemStack(Material.PODZOL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_POISONOUS_POTATO}, new ItemStack[]{new CustomItem(new ItemStack(Material.POISONOUS_POTATO), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_POLISHED_ANDESITE}, new ItemStack[]{new CustomItem(new ItemStack(Material.POLISHED_ANDESITE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_POLISHED_ANDESITE_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.POLISHED_ANDESITE_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_POLISHED_ANDESITE_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.POLISHED_ANDESITE_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_POLISHED_DIORITE}, new ItemStack[]{new CustomItem(new ItemStack(Material.POLISHED_DIORITE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_POLISHED_DIORITE_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.POLISHED_DIORITE_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_POLISHED_DIORITE_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.POLISHED_DIORITE_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_POLISHED_GRANITE}, new ItemStack[]{new CustomItem(new ItemStack(Material.POLISHED_GRANITE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_POLISHED_GRANITE_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.POLISHED_GRANITE_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_POLISHED_GRANITE_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.POLISHED_GRANITE_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_POPPED_CHORUS_FRUIT}, new ItemStack[]{new CustomItem(new ItemStack(Material.POPPED_CHORUS_FRUIT), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_POPPY}, new ItemStack[]{new CustomItem(new ItemStack(Material.POPPY), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PORKCHOP}, new ItemStack[]{new CustomItem(new ItemStack(Material.PORKCHOP), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_POTATO}, new ItemStack[]{new CustomItem(new ItemStack(Material.POTATO), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_POWERED_RAIL}, new ItemStack[]{new CustomItem(new ItemStack(Material.POWERED_RAIL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PRISMARINE}, new ItemStack[]{new CustomItem(new ItemStack(Material.PRISMARINE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PRISMARINE_BRICKS}, new ItemStack[]{new CustomItem(new ItemStack(Material.PRISMARINE_BRICKS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PRISMARINE_BRICK_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.PRISMARINE_BRICK_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PRISMARINE_BRICK_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.PRISMARINE_BRICK_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PRISMARINE_CRYSTALS}, new ItemStack[]{new CustomItem(new ItemStack(Material.PRISMARINE_CRYSTALS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PRISMARINE_SHARD}, new ItemStack[]{new CustomItem(new ItemStack(Material.PRISMARINE_SHARD), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PRISMARINE_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.PRISMARINE_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PRISMARINE_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.PRISMARINE_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PRISMARINE_WALL}, new ItemStack[]{new CustomItem(new ItemStack(Material.PRISMARINE_WALL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PUFFERFISH}, new ItemStack[]{new CustomItem(new ItemStack(Material.PUFFERFISH), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PUMPKIN}, new ItemStack[]{new CustomItem(new ItemStack(Material.PUMPKIN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PUMPKIN_PIE}, new ItemStack[]{new CustomItem(new ItemStack(Material.PUMPKIN_PIE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PUMPKIN_SEEDS}, new ItemStack[]{new CustomItem(new ItemStack(Material.PUMPKIN_SEEDS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PURPLE_CARPET}, new ItemStack[]{new CustomItem(new ItemStack(Material.PURPLE_CARPET), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PURPLE_CONCRETE}, new ItemStack[]{new CustomItem(new ItemStack(Material.PURPLE_CONCRETE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PURPLE_CONCRETE_POWDER}, new ItemStack[]{new CustomItem(new ItemStack(Material.PURPLE_CONCRETE_POWDER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PURPLE_DYE}, new ItemStack[]{new CustomItem(new ItemStack(Material.PURPLE_DYE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PURPLE_GLAZED_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.PURPLE_GLAZED_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PURPLE_STAINED_GLASS}, new ItemStack[]{new CustomItem(new ItemStack(Material.PURPLE_STAINED_GLASS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PURPLE_STAINED_GLASS_PANE}, new ItemStack[]{new CustomItem(new ItemStack(Material.PURPLE_STAINED_GLASS_PANE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PURPLE_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.PURPLE_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PURPLE_WOOL}, new ItemStack[]{new CustomItem(new ItemStack(Material.PURPLE_WOOL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PURPUR_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.PURPUR_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PURPUR_PILLAR}, new ItemStack[]{new CustomItem(new ItemStack(Material.PURPUR_PILLAR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PURPUR_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.PURPUR_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PURPUR_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.PURPUR_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_QUARTZ}, new ItemStack[]{new CustomItem(new ItemStack(Material.QUARTZ), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_QUARTZ_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.QUARTZ_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_QUARTZ_PILLAR}, new ItemStack[]{new CustomItem(new ItemStack(Material.QUARTZ_PILLAR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_QUARTZ_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.QUARTZ_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_QUARTZ_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.QUARTZ_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RABBIT_FOOT}, new ItemStack[]{new CustomItem(new ItemStack(Material.RABBIT_FOOT), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RABBIT_HIDE}, new ItemStack[]{new CustomItem(new ItemStack(Material.RABBIT_HIDE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RAIL}, new ItemStack[]{new CustomItem(new ItemStack(Material.RAIL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_REDSTONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.REDSTONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_REDSTONE_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.REDSTONE_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_REDSTONE_LAMP}, new ItemStack[]{new CustomItem(new ItemStack(Material.REDSTONE_LAMP), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_REDSTONE_ORE}, new ItemStack[]{new CustomItem(new ItemStack(Material.REDSTONE_ORE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_REDSTONE_TORCH}, new ItemStack[]{new CustomItem(new ItemStack(Material.REDSTONE_TORCH), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RED_CARPET}, new ItemStack[]{new CustomItem(new ItemStack(Material.RED_CARPET), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RED_CONCRETE}, new ItemStack[]{new CustomItem(new ItemStack(Material.RED_CONCRETE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RED_CONCRETE_POWDER}, new ItemStack[]{new CustomItem(new ItemStack(Material.RED_CONCRETE_POWDER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RED_DYE}, new ItemStack[]{new CustomItem(new ItemStack(Material.RED_DYE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RED_GLAZED_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.RED_GLAZED_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RED_MUSHROOM}, new ItemStack[]{new CustomItem(new ItemStack(Material.RED_MUSHROOM), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RED_MUSHROOM_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.RED_MUSHROOM_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RED_NETHER_BRICKS}, new ItemStack[]{new CustomItem(new ItemStack(Material.RED_NETHER_BRICKS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RED_NETHER_BRICK_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.RED_NETHER_BRICK_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RED_NETHER_BRICK_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.RED_NETHER_BRICK_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RED_NETHER_BRICK_WALL}, new ItemStack[]{new CustomItem(new ItemStack(Material.RED_NETHER_BRICK_WALL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RED_SAND}, new ItemStack[]{new CustomItem(new ItemStack(Material.RED_SAND), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RED_SANDSTONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.RED_SANDSTONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RED_SANDSTONE_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.RED_SANDSTONE_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RED_SANDSTONE_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.RED_SANDSTONE_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RED_SANDSTONE_WALL}, new ItemStack[]{new CustomItem(new ItemStack(Material.RED_SANDSTONE_WALL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RED_STAINED_GLASS}, new ItemStack[]{new CustomItem(new ItemStack(Material.RED_STAINED_GLASS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RED_STAINED_GLASS_PANE}, new ItemStack[]{new CustomItem(new ItemStack(Material.RED_STAINED_GLASS_PANE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RED_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.RED_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RED_TULIP}, new ItemStack[]{new CustomItem(new ItemStack(Material.RED_TULIP), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_RED_WOOL}, new ItemStack[]{new CustomItem(new ItemStack(Material.RED_WOOL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_REPEATER}, new ItemStack[]{new CustomItem(new ItemStack(Material.REPEATER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ROSE_BUSH}, new ItemStack[]{new CustomItem(new ItemStack(Material.ROSE_BUSH), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ROTTEN_FLESH}, new ItemStack[]{new CustomItem(new ItemStack(Material.ROTTEN_FLESH), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SALMON}, new ItemStack[]{new CustomItem(new ItemStack(Material.SALMON), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SAND}, new ItemStack[]{new CustomItem(new ItemStack(Material.SAND), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SANDSTONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.SANDSTONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SANDSTONE_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.SANDSTONE_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SANDSTONE_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.SANDSTONE_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SANDSTONE_WALL}, new ItemStack[]{new CustomItem(new ItemStack(Material.SANDSTONE_WALL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SCAFFOLDING}, new ItemStack[]{new CustomItem(new ItemStack(Material.SCAFFOLDING), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SCUTE}, new ItemStack[]{new CustomItem(new ItemStack(Material.SCUTE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SEAGRASS}, new ItemStack[]{new CustomItem(new ItemStack(Material.SEAGRASS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SEA_LANTERN}, new ItemStack[]{new CustomItem(new ItemStack(Material.SEA_LANTERN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SEA_PICKLE}, new ItemStack[]{new CustomItem(new ItemStack(Material.SEA_PICKLE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SHULKER_SHELL}, new ItemStack[]{new CustomItem(new ItemStack(Material.SHULKER_SHELL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SKELETON_SKULL}, new ItemStack[]{new CustomItem(new ItemStack(Material.SKELETON_SKULL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SLIME_BALL}, new ItemStack[]{new CustomItem(new ItemStack(Material.SLIME_BALL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SLIME_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.SLIME_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SMITHING_TABLE}, new ItemStack[]{new CustomItem(new ItemStack(Material.SMITHING_TABLE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SMOKER}, new ItemStack[]{new CustomItem(new ItemStack(Material.SMOKER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SMOOTH_QUARTZ}, new ItemStack[]{new CustomItem(new ItemStack(Material.SMOOTH_QUARTZ), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SMOOTH_QUARTZ_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.SMOOTH_QUARTZ_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SMOOTH_QUARTZ_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.SMOOTH_QUARTZ_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SMOOTH_RED_SANDSTONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.SMOOTH_RED_SANDSTONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SMOOTH_RED_SANDSTONE_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.SMOOTH_RED_SANDSTONE_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SMOOTH_RED_SANDSTONE_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.SMOOTH_RED_SANDSTONE_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SMOOTH_SANDSTONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.SMOOTH_SANDSTONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SMOOTH_SANDSTONE_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.SMOOTH_SANDSTONE_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SMOOTH_SANDSTONE_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.SMOOTH_SANDSTONE_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SMOOTH_STONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.SMOOTH_STONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SMOOTH_STONE_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.SMOOTH_STONE_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SNOW}, new ItemStack[]{new CustomItem(new ItemStack(Material.SNOW), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SNOWBALL}, new ItemStack[]{new CustomItem(new ItemStack(Material.SNOWBALL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SNOW_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.SNOW_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SOUL_SAND}, new ItemStack[]{new CustomItem(new ItemStack(Material.SOUL_SAND), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SPECTRAL_ARROW}, new ItemStack[]{new CustomItem(new ItemStack(Material.SPECTRAL_ARROW), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SPIDER_EYE}, new ItemStack[]{new CustomItem(new ItemStack(Material.SPIDER_EYE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SPONGE}, new ItemStack[]{new CustomItem(new ItemStack(Material.SPONGE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SPRUCE_BUTTON}, new ItemStack[]{new CustomItem(new ItemStack(Material.SPRUCE_BUTTON), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SPRUCE_DOOR}, new ItemStack[]{new CustomItem(new ItemStack(Material.SPRUCE_DOOR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SPRUCE_FENCE}, new ItemStack[]{new CustomItem(new ItemStack(Material.SPRUCE_FENCE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SPRUCE_FENCE_GATE}, new ItemStack[]{new CustomItem(new ItemStack(Material.SPRUCE_FENCE_GATE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SPRUCE_LEAVES}, new ItemStack[]{new CustomItem(new ItemStack(Material.SPRUCE_LEAVES), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SPRUCE_LOG}, new ItemStack[]{new CustomItem(new ItemStack(Material.SPRUCE_LOG), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SPRUCE_PLANKS}, new ItemStack[]{new CustomItem(new ItemStack(Material.SPRUCE_PLANKS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SPRUCE_PRESSURE_PLATE}, new ItemStack[]{new CustomItem(new ItemStack(Material.SPRUCE_PRESSURE_PLATE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SPRUCE_SAPLING}, new ItemStack[]{new CustomItem(new ItemStack(Material.SPRUCE_SAPLING), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SPRUCE_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.SPRUCE_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SPRUCE_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.SPRUCE_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SPRUCE_TRAPDOOR}, new ItemStack[]{new CustomItem(new ItemStack(Material.SPRUCE_TRAPDOOR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SPRUCE_WOOD}, new ItemStack[]{new CustomItem(new ItemStack(Material.SPRUCE_WOOD), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STICK}, new ItemStack[]{new CustomItem(new ItemStack(Material.STICK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STICKY_PISTON}, new ItemStack[]{new CustomItem(new ItemStack(Material.STICKY_PISTON), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.STONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STONECUTTER}, new ItemStack[]{new CustomItem(new ItemStack(Material.STONECUTTER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STONE_BRICKS}, new ItemStack[]{new CustomItem(new ItemStack(Material.STONE_BRICKS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STONE_BRICK_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.STONE_BRICK_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STONE_BRICK_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.STONE_BRICK_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STONE_BRICK_WALL}, new ItemStack[]{new CustomItem(new ItemStack(Material.STONE_BRICK_WALL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STONE_BUTTON}, new ItemStack[]{new CustomItem(new ItemStack(Material.STONE_BUTTON), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STONE_PRESSURE_PLATE}, new ItemStack[]{new CustomItem(new ItemStack(Material.STONE_PRESSURE_PLATE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STONE_SLAB}, new ItemStack[]{new CustomItem(new ItemStack(Material.STONE_SLAB), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STONE_STAIRS}, new ItemStack[]{new CustomItem(new ItemStack(Material.STONE_STAIRS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STRING}, new ItemStack[]{new CustomItem(new ItemStack(Material.STRING), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STRIPPED_ACACIA_LOG}, new ItemStack[]{new CustomItem(new ItemStack(Material.STRIPPED_ACACIA_LOG), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STRIPPED_ACACIA_WOOD}, new ItemStack[]{new CustomItem(new ItemStack(Material.STRIPPED_ACACIA_WOOD), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STRIPPED_BIRCH_LOG}, new ItemStack[]{new CustomItem(new ItemStack(Material.STRIPPED_BIRCH_LOG), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STRIPPED_BIRCH_WOOD}, new ItemStack[]{new CustomItem(new ItemStack(Material.STRIPPED_BIRCH_WOOD), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STRIPPED_DARK_OAK_LOG}, new ItemStack[]{new CustomItem(new ItemStack(Material.STRIPPED_DARK_OAK_LOG), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STRIPPED_DARK_OAK_WOOD}, new ItemStack[]{new CustomItem(new ItemStack(Material.STRIPPED_DARK_OAK_WOOD), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STRIPPED_JUNGLE_LOG}, new ItemStack[]{new CustomItem(new ItemStack(Material.STRIPPED_JUNGLE_LOG), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STRIPPED_JUNGLE_WOOD}, new ItemStack[]{new CustomItem(new ItemStack(Material.STRIPPED_JUNGLE_WOOD), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STRIPPED_OAK_LOG}, new ItemStack[]{new CustomItem(new ItemStack(Material.STRIPPED_OAK_LOG), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STRIPPED_OAK_WOOD}, new ItemStack[]{new CustomItem(new ItemStack(Material.STRIPPED_OAK_WOOD), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STRIPPED_SPRUCE_LOG}, new ItemStack[]{new CustomItem(new ItemStack(Material.STRIPPED_SPRUCE_LOG), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STRIPPED_SPRUCE_WOOD}, new ItemStack[]{new CustomItem(new ItemStack(Material.STRIPPED_SPRUCE_WOOD), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SUGAR}, new ItemStack[]{new CustomItem(new ItemStack(Material.SUGAR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SUGAR_CANE}, new ItemStack[]{new CustomItem(new ItemStack(Material.SUGAR_CANE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SUNFLOWER}, new ItemStack[]{new CustomItem(new ItemStack(Material.SUNFLOWER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SWEET_BERRIES}, new ItemStack[]{new CustomItem(new ItemStack(Material.SWEET_BERRIES), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_TNT}, new ItemStack[]{new CustomItem(new ItemStack(Material.TNT), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_TORCH}, new ItemStack[]{new CustomItem(new ItemStack(Material.TORCH), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_TRAPPED_CHEST}, new ItemStack[]{new CustomItem(new ItemStack(Material.TRAPPED_CHEST), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_TRIPWIRE_HOOK}, new ItemStack[]{new CustomItem(new ItemStack(Material.TRIPWIRE_HOOK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_TROPICAL_FISH}, new ItemStack[]{new CustomItem(new ItemStack(Material.TROPICAL_FISH), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_TUBE_CORAL}, new ItemStack[]{new CustomItem(new ItemStack(Material.TUBE_CORAL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_TUBE_CORAL_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.TUBE_CORAL_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_TUBE_CORAL_FAN}, new ItemStack[]{new CustomItem(new ItemStack(Material.TUBE_CORAL_FAN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_TURTLE_EGG}, new ItemStack[]{new CustomItem(new ItemStack(Material.TURTLE_EGG), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_VINE}, new ItemStack[]{new CustomItem(new ItemStack(Material.VINE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_WET_SPONGE}, new ItemStack[]{new CustomItem(new ItemStack(Material.WET_SPONGE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_WHEAT}, new ItemStack[]{new CustomItem(new ItemStack(Material.WHEAT), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_WHEAT_SEEDS}, new ItemStack[]{new CustomItem(new ItemStack(Material.WHEAT_SEEDS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_WHITE_CARPET}, new ItemStack[]{new CustomItem(new ItemStack(Material.WHITE_CARPET), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_WHITE_CONCRETE}, new ItemStack[]{new CustomItem(new ItemStack(Material.WHITE_CONCRETE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_WHITE_CONCRETE_POWDER}, new ItemStack[]{new CustomItem(new ItemStack(Material.WHITE_CONCRETE_POWDER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_WHITE_DYE}, new ItemStack[]{new CustomItem(new ItemStack(Material.WHITE_DYE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_WHITE_GLAZED_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.WHITE_GLAZED_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_WHITE_STAINED_GLASS}, new ItemStack[]{new CustomItem(new ItemStack(Material.WHITE_STAINED_GLASS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_WHITE_STAINED_GLASS_PANE}, new ItemStack[]{new CustomItem(new ItemStack(Material.WHITE_STAINED_GLASS_PANE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_WHITE_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.WHITE_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_WHITE_TULIP}, new ItemStack[]{new CustomItem(new ItemStack(Material.WHITE_TULIP), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_WHITE_WOOL}, new ItemStack[]{new CustomItem(new ItemStack(Material.WHITE_WOOL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_WITHER_SKELETON_SKULL}, new ItemStack[]{new CustomItem(new ItemStack(Material.WITHER_SKELETON_SKULL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_YELLOW_CARPET}, new ItemStack[]{new CustomItem(new ItemStack(Material.YELLOW_CARPET), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_YELLOW_CONCRETE}, new ItemStack[]{new CustomItem(new ItemStack(Material.YELLOW_CONCRETE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_YELLOW_CONCRETE_POWDER}, new ItemStack[]{new CustomItem(new ItemStack(Material.YELLOW_CONCRETE_POWDER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_YELLOW_DYE}, new ItemStack[]{new CustomItem(new ItemStack(Material.YELLOW_DYE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_YELLOW_GLAZED_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.YELLOW_GLAZED_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_YELLOW_STAINED_GLASS}, new ItemStack[]{new CustomItem(new ItemStack(Material.YELLOW_STAINED_GLASS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_YELLOW_STAINED_GLASS_PANE}, new ItemStack[]{new CustomItem(new ItemStack(Material.YELLOW_STAINED_GLASS_PANE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_YELLOW_TERRACOTTA}, new ItemStack[]{new CustomItem(new ItemStack(Material.YELLOW_TERRACOTTA), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_YELLOW_WOOL}, new ItemStack[]{new CustomItem(new ItemStack(Material.YELLOW_WOOL), 32)});

    }

    @Override
    public String getInventoryTitle() {
        return "&cItem Decompressor";
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.FLINT_AND_STEEL);
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{19, 20};
    }

    @Override
    public int[] getOutputSlots() {
        return new int[]{24, 25};
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {

            @Override
            public void tick(Block b, SlimefunItem sf, Config data) {
                ItemDecompressor.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return false;
            }
        });
    }

    @Override
    protected void tick(Block b) {
        BlockMenu menu = BlockStorage.getInventory(b);

        if (isProcessing(b)) {
            int timeleft = progress.get(b);

            if (timeleft > 0) {
                ChestMenuUtils.updateProgressbar(menu, 22, timeleft, processing.get(b).getTicks(), getProgressBar());

                if (ChargableBlock.isChargable(b)) {
                    if (ChargableBlock.getCharge(b) < getEnergyConsumption()) return;
                    ChargableBlock.addCharge(b, -getEnergyConsumption());
                    progress.put(b, timeleft - 1);
                } else progress.put(b, timeleft - 1);
            } else {
                menu.replaceExistingItem(22, new CustomItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE), " "));
                menu.pushItem(processing.get(b).getOutput()[0], getOutputSlots());

                progress.remove(b);
                processing.remove(b);

                if (timeleft != -1)
                    tick(b);
            }
        } else {
            Map<Integer, Integer> found = new HashMap<>();
            MachineRecipe recipe = findRecipe(menu, found);

            if (recipe != null) {
                if (!menu.fits(recipe.getOutput()[0], getOutputSlots())) {
                    return;
                }

                for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
                    menu.consumeItem(entry.getKey(), entry.getValue());
                }

                processing.put(b, recipe);
                progress.put(b, recipe.getTicks() == 0 ? -1 : recipe.getTicks());
                tick(b);
            }
        }
    }

    private MachineRecipe findRecipe(BlockMenu menu, Map<Integer, Integer> found) {
        for (MachineRecipe recipe : recipes) {
            for (ItemStack input : recipe.getInput()) {
                for (int slot : getInputSlots()) {
                    if (SlimefunManager.isItemSimilar(menu.getItemInSlot(slot), input, true)) {
                        found.put(slot, input.getAmount());
                        break;
                    }
                }
            }

            if (found.size() == recipe.getInput().length) {
                return recipe;
            } else found.clear();
        }

        return null;
    }

    @Override
    public String getMachineIdentifier() {
        return "ITEM_DECOMPRESSOR";
    }

}
