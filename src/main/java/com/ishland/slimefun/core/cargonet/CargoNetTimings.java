package com.ishland.slimefun.core.cargonet;

import com.ishland.slimefun.core.cargonet.cache.AttachedBlockCache;
import com.ishland.slimefun.core.cargonet.cache.BlockStateCache;
import com.ishland.slimefun.core.cargonet.cache.InventoryCache;
import io.github.thebusybiscuit.slimefun4.implementation.tasks.TickerTask;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CargoNetTimings {

    public static long timeNanos = 0L;
    public static long tickedCargoManagers = 0L;

    public static void info(CommandSender sender, String[] args) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes(
                '&', "&2== &aSlimefun Async CargoNet Diagnostic Tool &2=="));
        sender.sendMessage(ChatColor.translateAlternateColorCodes(
                '&', "&6Tick time: &e" + TickerTask.toMillis(timeNanos)));
        sender.sendMessage();

        sender.sendMessage(ChatColor.translateAlternateColorCodes(
                '&', "&6AttachedBlockCache size: &e" + AttachedBlockCache.getSize()));
        sender.sendMessage(ChatColor.translateAlternateColorCodes(
                '&', "&6AttachedBlockCache hits: &e" + AttachedBlockCache.getHit()));
        sender.sendMessage(ChatColor.translateAlternateColorCodes(
                '&', "&6AttachedBlockCache misses: &e" + AttachedBlockCache.getMiss()));
        sender.sendMessage(ChatColor.translateAlternateColorCodes(
                '&', "&6AttachedBlockCache updates: &e" + AttachedBlockCache.getClean()));

        sender.sendMessage(ChatColor.translateAlternateColorCodes(
                '&', "&6BlockStateCache size: &e" + BlockStateCache.getSize()));
        sender.sendMessage(ChatColor.translateAlternateColorCodes(
                '&', "&6BlockStateCache hits: &e" + BlockStateCache.getHit()));
        sender.sendMessage(ChatColor.translateAlternateColorCodes(
                '&', "&6BlockStateCache misses: &e" + BlockStateCache.getMiss()));
        sender.sendMessage(ChatColor.translateAlternateColorCodes(
                '&', "&6BlockStateCache updates: &e" + BlockStateCache.getClean()));

        sender.sendMessage(ChatColor.translateAlternateColorCodes(
                '&', "&6InventoryCache size: &e" + InventoryCache.getSize()));
        sender.sendMessage(ChatColor.translateAlternateColorCodes(
                '&', "&6InventoryCache hits: &e" + InventoryCache.getHit()));
        sender.sendMessage(ChatColor.translateAlternateColorCodes(
                '&', "&6InventoryCache misses: &e" + InventoryCache.getMiss()));
        sender.sendMessage(ChatColor.translateAlternateColorCodes(
                '&', "&6InventoryCache updates: &e" + InventoryCache.getClean()));
    }

}
