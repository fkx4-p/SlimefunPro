package io.github.thebusybiscuit.slimefun4.implementation.items.cargo;

import io.github.thebusybiscuit.slimefun4.utils.holograms.SimpleHologram;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockUseHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.item_transport.CargoNet;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class CargoManagerBlock extends SlimefunItem {

	public CargoManagerBlock(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, recipeType, recipe);
		
		registerBlockHandler(getID(), (p, b, tool, reason) -> {
			SimpleHologram.remove(b);
			return true;
		});
	}
	
	@Override
	public void preRegister() {
		addItemHandler(new BlockTicker() {

			@Override
			@SuppressWarnings("deprecation")
			public void tick(Block b, SlimefunItem item, Config data) {
				CargoNet.getNetworkFromLocationOrCreate(b.getLocation()).alive(b);
			}

			@Override
			public boolean isSynchronized() {
				return false;
			}

		}, (BlockUseHandler) e -> {
			Optional<Block> block = e.getClickedBlock();

			if (block.isPresent()) {
				Player p = e.getPlayer();
				Block b = block.get();

				if (BlockStorage.getLocationInfo(b.getLocation(), "visualizer") == null) {
					BlockStorage.addBlockInfo(b, "visualizer", "disabled");
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCargo Net Visualizer: " + "&4\u2718"));
				} else {
					BlockStorage.addBlockInfo(b, "visualizer", null);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCargo Net Visualizer: " + "&2\u2714"));
				}
			}
		});
	}

}
