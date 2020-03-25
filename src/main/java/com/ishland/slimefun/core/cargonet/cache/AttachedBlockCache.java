package com.ishland.slimefun.core.cargonet.cache;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import com.destroystokyo.paper.event.block.TNTPrimeEvent;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;

import javax.annotation.Nullable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

/**
 * Retrieve attached {@link Block} with caching support for async use.
 */
public class AttachedBlockCache implements Listener {

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

    private static ConcurrentMap<Location, Object> cache = new ConcurrentHashMap<>();
    private static ConcurrentMap<Location, Object> locks = new ConcurrentHashMap<>();
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
     * Retrieve {@link Block} which is attached to another with caching support
     *
     * @param block block to be queried
     * @return queried block
     * @throws ExecutionException   when an error occurred while getting block from server
     * @throws InterruptedException when interrupted
     */
    @Nullable
    public static Block query(Block block) throws ExecutionException, InterruptedException {
        final Location blockLocation = block.getLocation();
        locks.putIfAbsent(blockLocation, new Object());

        // Faster query
        Object cachedAttachedState = cache.get(blockLocation);

        if (cachedAttachedState != null) {
            hit++;
            return cachedAttachedState instanceof Block ? (Block) cachedAttachedState : null;
        } else if (available)
            synchronized (locks.get(blockLocation)) {
                cachedAttachedState = cache.get(blockLocation);
                if (cachedAttachedState instanceof Block) {
                    hit++;
                    return (Block) cachedAttachedState;
                }
                return getAttachedBlockSlow(block, blockLocation);
            }
        return getAttachedBlockSlow(block, blockLocation);
    }

    /**
     * Retrieve {@link Block} which is attached to another from the server and store it into cache
     *
     * @param block         block to be queried
     * @param blockLocation the location of the block to be queried
     * @return the queried block
     * @throws InterruptedException when an error occurred while getting block from server
     * @throws ExecutionException   when interrupted
     */
    @Nullable
    private static Block getAttachedBlockSlow(Block block, Location blockLocation)
            throws InterruptedException, ExecutionException {
        miss++;
        Object queriedAttachedBlock = new Null();
        final BlockData blockData = Slimefun.runSyncFuture(block::getBlockData).get();
        if (blockData instanceof Directional) {
            queriedAttachedBlock = Slimefun.runSyncFuture(() -> block.getRelative(((Directional) blockData)
                    .getFacing().getOppositeFace())).get();
        }
        cache.put(blockLocation, queriedAttachedBlock);
        return (queriedAttachedBlock instanceof Block) ? (Block) queriedAttachedBlock : null;
    }

    /**
     * Flush cache of specific position
     *
     * @param location location of block to refresh
     */
    public static void remove(Location location) {
        if (cache.remove(location) != null) clean++;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockDestroy(BlockDestroyEvent event) {
        if (event.isCancelled()) return;
        CacheGC.cleanThread.execute(() -> cleanCache(event.getBlock()));
    }

    private void cleanCache(Block block) {
        if (cache.remove(block.getLocation()) != null) clean++;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockExplode(BlockExplodeEvent event) {
        if (event.isCancelled()) return;
        CacheGC.cleanThread.execute(() -> {
            cleanCache(event.getBlock());
            for (Block block : event.blockList())
                cleanCache(block);
        });
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockFade(BlockFadeEvent event) {
        if (event.isCancelled()) return;
        CacheGC.cleanThread.execute(() -> cleanCache(event.getBlock()));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockFertilize(BlockFertilizeEvent event) {
        if (event.isCancelled()) return;
        CacheGC.cleanThread.execute(() -> {
            cleanCache(event.getBlock());
            for (BlockState state : event.getBlocks())
                cache.remove(state.getLocation());
        });
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockFromTo(BlockFromToEvent event) {
        if (event.isCancelled()) return;
        CacheGC.cleanThread.execute(() -> {
            cleanCache(event.getBlock());
            cleanCache(event.getToBlock());
        });
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPhysics(BlockPhysicsEvent event) {
        if (event.isCancelled()) return;
        CacheGC.cleanThread.execute(() -> cleanCache(event.getBlock()));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.isCancelled()) return;
        CacheGC.cleanThread.execute(() -> cleanCache(event.getBlock()));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onLeavesDecay(LeavesDecayEvent event) {
        if (event.isCancelled()) return;
        CacheGC.cleanThread.execute(() -> cleanCache(event.getBlock()));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onTNTPrime(TNTPrimeEvent event) {
        if (event.isCancelled()) return;
        CacheGC.cleanThread.execute(() -> cleanCache(event.getBlock()));
    }

    private static class Null {
    }

}
