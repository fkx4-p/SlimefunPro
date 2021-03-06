package io.github.thebusybiscuit.slimefun4.api.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines.AutoDisenchanter;

/**
 * An {@link Event} that is called whenever an {@link AutoDisenchanter} has
 * disenchanted an {@link ItemStack}.
 * 
 * @author poma123
 *
 */
public class AutoDisenchantEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private final ItemStack item;
    private boolean cancelled;

    public AutoDisenchantEvent(ItemStack item) {
        super(true);

        this.item = item;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public ItemStack getItem() {
        return this.item;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

}
