package com.ishland.slimefun.core.cargonet;

import com.ishland.slimefun.core.cargonet.data.CargoNetFilter;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

public class CargoUtils {

    // Filter slots
    private static final int[] FILTER_SLOT = { 19, 20, 21, 28, 29, 30, 37, 38, 39 };

    static int getChannel(Location l) {
        int channel = 0;
        try {
            String str = BlockStorage.getLocationInfo(l).getString("frequency");
            if (str != null) channel = Integer.parseInt(str);
        } catch (Exception x) {
            Slimefun.getLogger().log(Level.SEVERE, "An Error occurred while parsing a Cargo Node Frequency", x);
        }
        return channel;
    }

    static CargoNetFilter getFilters(Location location) {
        // No filter for normal output
        if (Objects.equals(BlockStorage.checkID(location), "CARGO_NODE_OUTPUT"))
            return new CargoNetFilter(false, Collections.emptyList());

        @SuppressWarnings("deprecation")
        Config blockInfo = BlockStorage.getLocationInfo(location);

        // No filter for normal output
        if (Objects.equals(blockInfo.getString("id"), "CARGO_NODE_OUTPUT"))
            return new CargoNetFilter(false, Collections.emptyList());

        BlockMenu menu;
        try {
            menu = BlockStorage.getInventory(location);
            SlimefunPlugin.getTicker().getBuggedBlocks().put(location, 0);
        } catch (Throwable e) {
            SlimefunPlugin.getTicker().reportErrors(location, e);
            return new CargoNetFilter(true, Collections.emptyList());
        }

        if (menu == null) {
            SlimefunPlugin.getTicker().reportErrors(location,
                    new NullPointerException("No BlockMenu found on this node"));
            return new CargoNetFilter(true, Collections.emptyList());
        }
        SlimefunPlugin.getTicker().getBuggedBlocks().put(location, 0);

        List<ItemStack> filters = new ArrayList<>(9);
        boolean isWhitelist = "whitelist".equals(blockInfo.getString("filter-type"));

        for (int slot : FILTER_SLOT) {
            ItemStack current = menu.getItemInSlot(slot).clone();
            current.setAmount(0);
            filters.add(current);
        }

        return new CargoNetFilter(isWhitelist, filters);
    }
}