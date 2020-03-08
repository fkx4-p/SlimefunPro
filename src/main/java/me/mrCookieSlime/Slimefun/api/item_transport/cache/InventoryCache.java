package me.mrCookieSlime.Slimefun.api.item_transport.cache;

import com.destroystokyo.paper.event.server.ServerTickEndEvent;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.block.Container;
import org.bukkit.block.DoubleChest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

public class InventoryCache implements Listener {

    private static ConcurrentMap<Location, Inventory> cache = new ConcurrentHashMap<>();
    private static ConcurrentMap<Location, Object> locks = new ConcurrentHashMap<>();
    public static final boolean isPaper;

    static {
        isPaper = isAvailable();
    }

    private static boolean isAvailable() {
        try {
            ServerTickEndEvent.class.getName();
        } catch (NoClassDefFoundError e) {
            return false;
        }
        return true;
    }

    @Nonnull
    public static Inventory query(Container container) throws ExecutionException, InterruptedException {
        final Location blockLocation = container.getLocation();
        locks.putIfAbsent(blockLocation, new Object());

        // Faster query
        Inventory cachedInventory = cache.get(blockLocation);
        if (cachedInventory != null) return cachedInventory;

        else if (isPaper)
            synchronized (locks.get(blockLocation)) {
                cachedInventory = cache.get(blockLocation);
                if (cachedInventory != null) return cachedInventory;
                return getInventorySlow(container, blockLocation);
            }
        return getInventorySlow(container, blockLocation);
    }

    @NotNull
    private static Inventory getInventorySlow(Container container, Location blockLocation)
            throws InterruptedException, ExecutionException {
        final Object[] objects = Slimefun.runSyncFuture(() -> {
            final Inventory inventory = container.getInventory();
            return new Object[]{inventory, inventory.getHolder()};
        }).get();
        final Inventory initialInventory = (Inventory) objects[0];
        final InventoryHolder holder = (InventoryHolder) objects[1];
        if (holder instanceof DoubleChest) {
            DoubleChest doubleChest = (DoubleChest) holder;
            final Object[] objects1 = Slimefun.runSyncFuture(() -> new Object[]{
                    doubleChest.getLeftSide(), doubleChest.getRightSide(), doubleChest.getInventory()}).get();
            cache.put(((Chest) objects1[0]).getLocation(), (Inventory) objects1[2]);
            cache.put(((Chest) objects1[1]).getLocation(), (Inventory) objects1[2]);
            return (Inventory) objects1[2];
        } else {
            cache.put(blockLocation, initialInventory);
            return initialInventory;
        }
    }

    @EventHandler
    public void onServerTickEnd(ServerTickEndEvent event) {
        cache.clear();
    }

}
