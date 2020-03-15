package io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.interfaces.InventoryBlock;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;

public abstract class CropGrowthAccelerator extends SlimefunItem implements InventoryBlock, EnergyNetComponent {

    private final int[] border = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26 };
    private final Set<Material> crops = new HashSet<>();

    public CropGrowthAccelerator(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        crops.add(Material.WHEAT);
        crops.add(Material.POTATOES);
        crops.add(Material.CARROTS);
        crops.add(Material.NETHER_WART);
        crops.add(Material.BEETROOTS);
        crops.add(Material.COCOA);
        crops.add(Material.SWEET_BERRY_BUSH);

        createPreset(this, this::constructMenu);

        registerBlockHandler(getID(), (p, b, tool, reason) -> {
            BlockMenu inv = BlockStorage.getInventory(b);

            if (inv != null) {
                for (int slot : getInputSlots()) {
                    if (inv.getItemInSlot(slot) != null) {
                        b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
                        inv.replaceExistingItem(slot, null);
                    }
                }
            }
            return true;
        });
    }

    private void constructMenu(BlockMenuPreset preset) {
        for (int i : border) {
            preset.addItem(i, new CustomItem(new ItemStack(Material.CYAN_STAINED_GLASS_PANE), " "), ChestMenuUtils.getEmptyClickHandler());
        }
    }

    public abstract int getEnergyConsumption();

    public abstract int getRadius();

    public abstract int getSpeed();

    @Override
    public int[] getInputSlots() {
        return new int[] { 10, 11, 12, 13, 14, 15, 16 };
    }

    @Override
    public int[] getOutputSlots() {
        return new int[0];
    }

    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public int getCapacity() {
        return 1024;
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {

            @Override
            public void tick(Block b, SlimefunItem sf, Config data) {
                CropGrowthAccelerator.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return true;
            }
        });
    }

    protected void tick(Block b) {
        BlockMenu inv = BlockStorage.getInventory(b);

        if (work(b, inv) > 0) {
            for (int slot : getInputSlots()) {
                if (SlimefunManager.isItemSimilar(inv.getItemInSlot(slot), SlimefunItems.FERTILIZER, false)) {
                    inv.consumeItem(slot);
                    break;
                }
            }
        }
    }

    private int work(Block b, BlockMenu inv) {
        int work = 0;

        for (int x = -getRadius(); x <= getRadius(); x++) {
            for (int z = -getRadius(); z <= getRadius(); z++) {
                Block block = b.getRelative(x, 0, z);

                if (crops.contains(block.getType())) {
                    Ageable ageable = (Ageable) block.getBlockData();

                    if (ageable.getAge() < ageable.getMaximumAge()) {
                        for (int slot : getInputSlots()) {
                            if (SlimefunManager.isItemSimilar(inv.getItemInSlot(slot), SlimefunItems.FERTILIZER, false)) {
                                if (work > (getSpeed() - 1) || ChargableBlock.getCharge(b) < getEnergyConsumption()) return work;
                                ChargableBlock.addCharge(b, -getEnergyConsumption());

                                ageable.setAge(ageable.getAge() + 1);
                                block.setBlockData(ageable);

                                block.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, block.getLocation().add(0.5D, 0.5D, 0.5D), 4, 0.1F, 0.1F, 0.1F);
                                work++;
                                return work;
                            }
                        }
                    }
                }
            }
        }

        return work;
    }

}
