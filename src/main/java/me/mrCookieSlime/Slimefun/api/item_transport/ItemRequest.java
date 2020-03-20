package me.mrCookieSlime.Slimefun.api.item_transport;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

class ItemRequest {

    private final ItemStack item;
    private final ItemTransportFlow flow;
    private final Location terminal;
    private final int slot;

    public Set<Location> getProviders() {
        return providers;
    }

    public Set<Location> getDestinations() {
        return destinations;
    }

    private final Set<Location> providers;
    private final Set<Location> destinations;

    public ItemRequest(Location terminal, int slot, ItemStack item, ItemTransportFlow flow
            , Set<Location> providers, Set<Location> destinations) {
        this.terminal = terminal;
        this.item = item;
        this.slot = slot;
        this.flow = flow;
        this.providers = providers;
        this.destinations = destinations;
    }

    public Location getTerminal() {
        return this.terminal;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public ItemTransportFlow getDirection() {
        return this.flow;
    }

    public int getSlot() {
        return this.slot;
    }

}
