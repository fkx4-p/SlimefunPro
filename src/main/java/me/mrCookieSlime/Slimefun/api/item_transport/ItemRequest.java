package me.mrCookieSlime.Slimefun.api.item_transport;

import com.google.common.collect.Sets;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.Set;

public class ItemRequest {

    private final ItemStack item;
    private final ItemTransportFlow flow;
    private final Location terminal;
    private final int slot;

    @Nonnull
    public Set<Location> getProviders() {
        return providers;
    }

    @Nonnull
    public Set<Location> getDestinations() {
        return destinations;
    }

    private final Set<Location> providers;
    private final Set<Location> destinations;

    @Deprecated
    public ItemRequest(@Nonnull Location terminal, int slot, @Nonnull ItemStack item, @Nonnull ItemTransportFlow flow) {
        this.terminal = terminal;
        this.item = item;
        this.slot = slot;
        this.flow = flow;
        this.providers = Sets.newConcurrentHashSet();
        this.destinations = Sets.newConcurrentHashSet();
    }

    public ItemRequest(@Nonnull Location terminal, int slot, @Nonnull ItemStack item, @Nonnull ItemTransportFlow flow, @Nonnull Set<Location> providers, @Nonnull Set<Location> destinations) {
        this.terminal = terminal;
        this.item = item;
        this.slot = slot;
        this.flow = flow;
        this.providers = providers;
        this.destinations = destinations;
    }

    @Nonnull
    public Location getTerminal() {
        return this.terminal;
    }

    @Nonnull
    public ItemStack getItem() {
        return this.item;
    }

    @Nonnull
    public ItemTransportFlow getDirection() {
        return this.flow;
    }

    public int getSlot() {
        return this.slot;
    }

}
