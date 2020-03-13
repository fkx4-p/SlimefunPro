package io.github.thebusybiscuit.slimefun4.implementation.items.androids;

import org.bukkit.Effect;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.ExoticGarden.ExoticGarden;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

/**
 * The {@link AdvancedFarmerAndroid} is an extension of the {@link FarmerAndroid}.
 * It also allows the {@link Player} to harvest plants from the addon ExoticGarden.
 * 
 * @author John000708
 * @author TheBusyBiscuit
 * 
 * @see FarmerAndroid
 *
 */
public abstract class AdvancedFarmerAndroid extends FarmerAndroid {

    public AdvancedFarmerAndroid(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public AndroidType getAndroidType() {
        return AndroidType.ADVANCED_FARMER;
    }

    @Override
    protected void exoticFarm(BlockMenu menu, Block block) {
        farm(menu, block);

        if (SlimefunPlugin.getThirdPartySupportService().isExoticGardenInstalled()) {
            ItemStack drop = ExoticGarden.harvestPlant(block);

            if (drop != null && menu.fits(drop, getOutputSlots())) {
                menu.pushItem(drop, getOutputSlots());
                block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block.getType());
            }
        }
    }

}
