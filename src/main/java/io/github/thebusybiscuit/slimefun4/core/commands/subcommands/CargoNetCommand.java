package io.github.thebusybiscuit.slimefun4.core.commands.subcommands;

import com.ishland.slimefun.core.cargonet.CargoNetTimings;
import io.github.thebusybiscuit.slimefun4.core.commands.SlimefunCommand;
import io.github.thebusybiscuit.slimefun4.core.commands.SubCommand;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class CargoNetCommand extends SubCommand {

    protected CargoNetCommand(SlimefunPlugin plugin, SlimefunCommand cmd) {
        super(plugin, cmd);
    }

    @Override
    public String getName() {
        return "cargonet";
    }

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        if (sender.hasPermission("slimefun.command.timings") || sender instanceof ConsoleCommandSender)
            CargoNetTimings.info(sender, args);
        else
            SlimefunPlugin.getLocal().sendMessage(sender, "messages.no-permission", true);
    }
}
