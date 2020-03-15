package io.github.thebusybiscuit.slimefun4.core.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefun4.core.commands.SlimefunCommand;
import io.github.thebusybiscuit.slimefun4.core.commands.SubCommand;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;

class TeleporterCommand extends SubCommand {

    TeleporterCommand(SlimefunPlugin plugin, SlimefunCommand cmd) {
        super(plugin, cmd);
    }

    @Override
    public String getName() {
        return "teleporter";
    }

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        if (args.length == 2) {
            if (sender.hasPermission("slimefun.command.teleporter") && sender instanceof Player) {

                @SuppressWarnings("deprecation")
                OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);

                if (player.getName() != null) {
                    SlimefunPlugin.getGPSNetwork().getTeleleportationService().openTeleporterGUI((Player) sender, player.getUniqueId(), ((Player) sender).getLocation().getBlock().getRelative(BlockFace.DOWN), 999999999);
                }
                else SlimefunPlugin.getLocal().sendMessage(sender, "messages.unknown-player", msg -> msg.replace("%player%", args[1]));
            }
            else SlimefunPlugin.getLocal().sendMessage(sender, "messages.no-permission");
        }
        else SlimefunPlugin.getLocal().sendMessage(sender, "messages.usage", msg -> msg.replace("%usage%", "/sf teleporter <Player>"));
    }

}
