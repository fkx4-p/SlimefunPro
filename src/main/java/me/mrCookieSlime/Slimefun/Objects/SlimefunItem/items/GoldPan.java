package me.mrCookieSlime.Slimefun.Objects.SlimefunItem.items;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.collections.RandomizedSet;
import io.github.thebusybiscuit.cscorelib2.protection.ProtectableAction;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SimpleSlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.interfaces.RecipeDisplayItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class GoldPan extends SimpleSlimefunItem<ItemUseHandler> implements RecipeDisplayItem {
	
	private final List<ItemStack> recipes;
	private final RandomizedSet<ItemStack> randomizer = new RandomizedSet<>();
	private int weights;

	public GoldPan(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, recipeType, recipe, new String[] {"chance.FLINT", "chance.CLAY", "chance.SIFTED_ORE", "chance.IRON_NUGGET"}, new Integer[] {40, 20, 35, 5});
		
		recipes = Arrays.asList(
			new ItemStack(Material.GRAVEL), new ItemStack(Material.FLINT), 
			new ItemStack(Material.GRAVEL), new ItemStack(Material.CLAY_BALL), 
			new ItemStack(Material.GRAVEL), SlimefunItems.SIFTED_ORE,
			new ItemStack(Material.GRAVEL), new ItemStack(Material.IRON_NUGGET)
		);
	}
	
	@Override
	public void postRegister() {
		add(SlimefunItems.SIFTED_ORE, (int) Slimefun.getItemValue(getID(), "chance.SIFTED_ORE"));
		add(new ItemStack(Material.CLAY_BALL), (int) Slimefun.getItemValue(getID(), "chance.CLAY"));
		add(new ItemStack(Material.FLINT), (int) Slimefun.getItemValue(getID(), "chance.FLINT"));
		add(new ItemStack(Material.IRON_NUGGET), (int) Slimefun.getItemValue(getID(), "chance.IRON_NUGGET"));
		
		if (weights < 100) {
			add(new ItemStack(Material.AIR), 100 - weights);
		}
	}
	
	private void add(ItemStack item, int chance) {
		randomizer.add(item, chance);
		weights += chance;
	}
	
	@Override
	public String getLabelLocalPath() {
		return "guide.tooltips.recipes.gold-pan";
	}

	@Override
	public ItemUseHandler getItemHandler() {
		return e -> {
			Optional<Block> block = e.getClickedBlock();
			
			if (block.isPresent()) {
				Block b = block.get();
				
				if (b.getType() == Material.GRAVEL && SlimefunPlugin.getProtectionManager().hasPermission(e.getPlayer(), b.getLocation(), ProtectableAction.BREAK_BLOCK)) {
					ItemStack output = randomizer.getRandom();
					
					b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, b.getType());
					b.setType(Material.AIR);

					if (output.getType() != Material.AIR) {
						b.getWorld().dropItemNaturally(b.getLocation(), output.clone());
					}
				}
			}
			
			e.cancel();
		};
	}

	@Override
	public List<ItemStack> getDisplayRecipes() {
		return recipes;
	}

}
