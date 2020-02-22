package io.github.thebusybiscuit.slimefun4.implementation.items.tools;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.materials.MaterialCollections;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SimpleSlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.interfaces.DamageableItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class PickaxeOfTheSeeker extends SimpleSlimefunItem<ItemUseHandler> implements DamageableItem {

	public PickaxeOfTheSeeker(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, recipeType, recipe);
	}

	@Override
	public ItemUseHandler getItemHandler() {
		return e -> {
			Player p = e.getPlayer();
			Block closest = null;

			for (int x = -4; x <= 4; x++) {
				for (int y = -4; y <= 4; y++) {
					for (int z = -4; z <= 4; z++) {
						if (MaterialCollections.getAllOres().contains(p.getLocation().getBlock().getRelative(x, y, z).getType()) && (closest == null || p.getLocation().distanceSquared(closest.getLocation()) > p.getLocation().distanceSquared(p.getLocation().getBlock().getRelative(x, y, z).getLocation()))) {
							closest = p.getLocation().getBlock().getRelative(x, y, z);
						}
					}
				}
			}

			if (closest == null) {
				SlimefunPlugin.getLocal().sendMessage(p, "miner.no-ores");
			}
			else {
				double l = closest.getX() + 0.5 - p.getLocation().getX();
				double w = closest.getZ() + 0.5 - p.getLocation().getZ();
				float yaw;
				float pitch;
				double c = Math.sqrt(l * l + w * w);
				double alpha1 = -Math.asin(l / c) / Math.PI * 180;
				double alpha2 =  Math.acos(w / c) / Math.PI * 180;
				if (alpha2 > 90) yaw = (float) (180 - alpha1);
				else yaw = (float) alpha1;
				pitch = (float) ((-Math.atan((closest.getY() - 0.5 - p.getLocation().getY()) / Math.sqrt(l * l + w * w))) * 180F / Math.PI);

				p.teleport(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), yaw, pitch));
			}

			damageItem(p, e.getItem());
		};
	}

	@Override
	public boolean isDamageable() {
		return true;
	}

}
