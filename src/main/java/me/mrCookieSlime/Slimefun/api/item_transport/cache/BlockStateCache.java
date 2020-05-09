package me.mrCookieSlime.Slimefun.api.item_transport.cache;

import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.plugin.RegisteredListener;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

/**
 * Retrieve {@link BlockState} with caching support for async use.
 */
public class BlockStateCache implements Listener {

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

    private static final ConcurrentMap<Location, BlockState> cache = new ConcurrentHashMap<>();
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
            BlockEvent.class.getName();
        } catch (NoClassDefFoundError e) {
            return false;
        }
        return true;
    }

    /**
     * Register event listener
     */
    public static void registerEvents() {
        BlockStateCache instance = new BlockStateCache();
        RegisteredListener registeredListener = new RegisteredListener(instance,
                (listener, event) -> {
                    if (event instanceof BlockEvent)
                        CacheGC.cleanThread.execute(() -> instance.onBlockEvents((BlockEvent) event));
                    if (event instanceof ChunkUnloadEvent)
                        instance.onChunkUnload((ChunkUnloadEvent) event);
                }, EventPriority.MONITOR, SlimefunPlugin.instance,
                true);
        for (HandlerList handler : HandlerList.getHandlerLists())
            handler.register(registeredListener);
    }

    /**
     * Retrieve {@link BlockState} from a block with caching support
     *
     * @param block block to be queried
     * @return the queried {@link BlockState}
     * @throws ExecutionException   when an error occurred while getting block from server
     * @throws InterruptedException when interrupted
     */
    @Nonnull
    public static BlockState query(@Nonnull Block block) throws ExecutionException, InterruptedException {
        final Location blockLocation = block.getLocation();
        locks.putIfAbsent(blockLocation, new Object());

        // Faster query
        BlockState cachedState = cache.get(blockLocation);
        if (cachedState != null) {
            hit++;
            return cachedState;
        } else if (available)
            synchronized (locks.get(blockLocation)) {
                cachedState = cache.get(blockLocation);
                if (cachedState != null) {
                    hit++;
                    return cachedState;
                }
                return getBlockStateSlow(block, blockLocation);
            }
        return getBlockStateSlow(block, blockLocation);
    }

    /**
     * Retrieve {@link BlockState} from the block from the server and store it into cache
     *
     * @param block         block to be queried
     * @param blockLocation the location of block to be queried
     * @return queried {@link BlockState}
     * @throws InterruptedException when interrupted
     * @throws ExecutionException   when an error occurred while getting block from server
     */
    @NotNull
    private static BlockState getBlockStateSlow(Block block, Location blockLocation)
            throws InterruptedException, ExecutionException {
        miss++;
        final BlockState queriedState = Slimefun.runSyncFuture(() -> block.getState(false)).get();
        cache.put(blockLocation, queriedState);
        return queriedState;
    }

    /**
     * Flush cache of specific position
     *
     * @param location location of block to refresh
     */
    public static void remove(Location location) {
        if (cache.remove(location) != null) clean++;
    }

    private void onBlockEvents(BlockEvent event) {
        CacheGC.cleanThread.execute(() -> remove(event.getBlock().getLocation()));
    }

    private void onChunkUnload(ChunkUnloadEvent event) {
        final Chunk chunk = event.getChunk();
        for (BlockState state : chunk.getTileEntities())
            remove(state.getLocation());
    }

}
