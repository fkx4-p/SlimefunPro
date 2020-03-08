package io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public abstract class FoodComposter extends AContainer implements RecipeDisplayItem {

    public FoodComposter(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(30, new ItemStack[] { SlimefunItems.WHEAT_ORGANIC_FOOD }, new ItemStack[] { SlimefunItems.WHEAT_FERTILIZER });
        registerRecipe(30, new ItemStack[] { SlimefunItems.CARROT_ORGANIC_FOOD }, new ItemStack[] { SlimefunItems.CARROT_FERTILIZER });
        registerRecipe(30, new ItemStack[] { SlimefunItems.POTATO_ORGANIC_FOOD }, new ItemStack[] { SlimefunItems.POTATO_FERTILIZER });
        registerRecipe(30, new ItemStack[] { SlimefunItems.SEEDS_ORGANIC_FOOD }, new ItemStack[] { SlimefunItems.SEEDS_FERTILIZER });
        registerRecipe(30, new ItemStack[] { SlimefunItems.BEETROOT_ORGANIC_FOOD }, new ItemStack[] { SlimefunItems.BEETROOT_FERTILIZER });
        registerRecipe(30, new ItemStack[] { SlimefunItems.MELON_ORGANIC_FOOD }, new ItemStack[] { SlimefunItems.MELON_FERTILIZER });
        registerRecipe(30, new ItemStack[] { SlimefunItems.APPLE_ORGANIC_FOOD }, new ItemStack[] { SlimefunItems.APPLE_FERTILIZER });
        registerRecipe(30, new ItemStack[] { SlimefunItems.SWEET_BERRIES_ORGANIC_FOOD }, new ItemStack[] { SlimefunItems.SWEET_BERRIES_FERTILIZER });
        registerRecipe(30, new ItemStack[] { SlimefunItems.KELP_ORGANIC_FOOD }, new ItemStack[] { SlimefunItems.KELP_FERTILIZER });
    }

    @Override
    public String getMachineIdentifier() {
        return "FOOD_COMPOSTER";
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.GOLDEN_HOE);
    }

    @Override
    public String getInventoryTitle() {
        return "&cFood Composter";
    }

}
