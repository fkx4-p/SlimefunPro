package me.mrCookieSlime.Slimefun.api.item_transport.cache;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import com.destroystokyo.paper.event.block.TNTPrimeEvent;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

/**
 * Retrieve {@link Inventory} with caching support for async use.
 */
public class InventoryCache implements Listener {

    public static long getHit() {
        return hit;
    }

    public static long getMiss() {
        return miss;
    }

    private static long hit = 0L;
    private static long miss = 0L;

    public static long getClean() {
        return clean;
    }

    private static long clean = 0L;

    public static int getSize() {
        return cache.size();
    }

    private static final ConcurrentMap<Location, CachedInventory> cache = new ConcurrentHashMap<>();
    private static final ConcurrentMap<Location, Object> locks = new ConcurrentHashMap<>();
    public static final boolean available;

    static {
        available = isAvailable();
    }

    /**
     * Check if required events is available
     *
     * @return the availability
     */
    private static boolean isAvailable() {
        try {
            BlockDestroyEvent.class.getName();
            BlockExplodeEvent.class.getName();
            BlockFadeEvent.class.getName();
            BlockFertilizeEvent.class.getName();
            BlockFromToEvent.class.getName();
            BlockPhysicsEvent.class.getName();
            BlockPlaceEvent.class.getName();
            LeavesDecayEvent.class.getName();
            TNTPrimeEvent.class.getName();
        } catch (NoClassDefFoundError e) {
            return false;
        }
        return true;
    }

    /**
     * Retrieve {@link CachedInventory} of a {@link Container}
     *
     * @param container container to be queried
     * @return queried inventory
     * @throws ExecutionException   when an error occurred while getting from server
     * @throws InterruptedException when interrupted
     */
    @Nonnull
    public static @NotNull CachedInventory query(Container container) throws ExecutionException, InterruptedException {
        final Location blockLocation = container.getLocation();
        locks.putIfAbsent(blockLocation, new Object());

        // Faster query
        CachedInventory cachedInventory = cache.get(blockLocation);
        if (cachedInventory != null) {
            hit++;
            return cachedInventory;
        } else if (available)
            synchronized (locks.get(blockLocation)) {
                cachedInventory = cache.get(blockLocation);
                if (cachedInventory != null) {
                    hit++;
                    return cachedInventory;
                }
                return getInventorySlow(container, blockLocation);
            }
        return getInventorySlow(container, blockLocation);
    }

    /**
     * Retrieve {@link CachedInventory} of a {@link Container}
     *
     * @param container     container to be queried
     * @param blockLocation the location of the container to be queried
     * @return the cached inventory
     * @throws InterruptedException when an error occurred while getting from server
     * @throws ExecutionException   when interrupted
     */
    @NotNull
    private static CachedInventory getInventorySlow(Container container, Location blockLocation)
            throws InterruptedException, ExecutionException {
        miss++;
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
            final CachedInventory cachedInventory = new CachedInventory((Inventory) objects1[2],
                    ((Chest) objects1[0]).getLocation(), ((Chest) objects1[1]).getLocation());
            cache.put(((Chest) objects1[0]).getLocation(), cachedInventory);
            cache.put(((Chest) objects1[1]).getLocation(), cachedInventory);
            final Object v = locks.get(container.getLocation());
            locks.put(((Chest) objects1[0]).getLocation(), v);
            locks.put(((Chest) objects1[1]).getLocation(), v);
            return cachedInventory;
        } else {
            final CachedInventory cachedInventory = new CachedInventory(initialInventory, blockLocation);
            cache.put(blockLocation, cachedInventory);
            return cachedInventory;
        }
    }

    /**
     * Update cache using recursion
     *
     * @param location location of inventory to be updated
     */
    public static void updateCache(Location location) {
        CacheGC.cleanThread.execute(() -> {
            CachedInventory cachedInventory = cache.get(location);
            if (cachedInventory == null) return;
            clean++;
            cache.remove(location);
            for (Location loc : cachedInventory.locations)
                CacheGC.cleanThread.execute(() -> updateCache(loc));
        });
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockDestroy(BlockDestroyEvent event) {
        if (event.isCancelled()) return;
        updateCache(event.getBlock().getLocation());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockExplode(BlockExplodeEvent event) {
        if (event.isCancelled()) return;
        updateCache(event.getBlock().getLocation());
        for (Block block : event.blockList())
            updateCache(block.getLocation());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockFade(BlockFadeEvent event) {
        if (event.isCancelled()) return;
        updateCache(event.getBlock().getLocation());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockFertilize(BlockFertilizeEvent event) {
        if (event.isCancelled()) return;
        updateCache(event.getBlock().getLocation());
        for (BlockState state : event.getBlocks())
            updateCache(state.getLocation());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockFromTo(BlockFromToEvent event) {
        if (event.isCancelled()) return;
        updateCache(event.getBlock().getLocation());
        updateCache(event.getToBlock().getLocation());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPhysics(BlockPhysicsEvent event) {
        if (event.isCancelled()) return;
        updateCache(event.getBlock().getLocation());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.isCancelled()) return;
        updateCache(event.getBlock().getLocation());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onLeavesDecay(LeavesDecayEvent event) {
        if (event.isCancelled()) return;
        updateCache(event.getBlock().getLocation());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onTNTPrime(TNTPrimeEvent event) {
        if (event.isCancelled()) return;
        updateCache(event.getBlock().getLocation());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onChunkUnload(ChunkUnloadEvent event) {
        final Chunk chunk = event.getChunk();
        for (BlockState state : chunk.getTileEntities())
            updateCache(state.getLocation());
    }

    /**
     * Represents a cached inventory
     */
    public static class CachedInventory {
        @Nonnull
        public Inventory inventory;
        @Nonnull
        public Location[] locations;

        public CachedInventory(@NotNull Inventory inventory, @NotNull Location @NotNull ... locations) {
            this.inventory = inventory;
            this.locations = locations;
        }
    }

}
