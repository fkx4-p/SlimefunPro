package io.github.thebusybiscuit.slimefun4.core.commands.subcommands;

import io.github.thebusybiscuit.slimefun4.core.commands.SlimefunCommand;
import io.github.thebusybiscuit.slimefun4.core.commands.SubCommand;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.api.item_transport.CargoNetTimings;
import org.bukkit.command.CommandSender;

public class CargoNetCommand extends SubCommand {

    protected CargoNetCommand(SlimefunPlugin plugin, SlimefunCommand cmd) {
        super(plugin, cmd);
    }

    @Override
    public String getName() {
        return "cargonet";
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        CargoNetTimings.info(sender, args);
    }
}
