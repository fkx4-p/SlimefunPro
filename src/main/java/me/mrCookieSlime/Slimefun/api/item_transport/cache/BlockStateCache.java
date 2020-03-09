package me.mrCookieSlime.Slimefun.api.item_transport.cache;

import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.plugin.RegisteredListener;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

public class BlockStateCache implements Listener {

    private static ConcurrentMap<Location, BlockState> cache = new ConcurrentHashMap<>();
    private static ConcurrentMap<Location, Object> locks = new ConcurrentHashMap<>();
    public static final boolean available;

    static {
        available = isAvailable();
    }

    private static boolean isAvailable() {
        try {
            BlockEvent.class.getName();
        } catch (NoClassDefFoundError e) {
            return false;
        }
        return true;
    }

    public static void registerEvents() {
        BlockStateCache instance = new BlockStateCache();
        RegisteredListener registeredListener = new RegisteredListener(instance,
                (listener, event) -> {
                    if (event instanceof BlockEvent)
                        instance.onBlockEvents((BlockEvent) event);
                }, EventPriority.MONITOR, SlimefunPlugin.instance,
                false);
        for (HandlerList handler : HandlerList.getHandlerLists())
            handler.register(registeredListener);
    }

    @Nonnull
    public static BlockState query(Block block) throws ExecutionException, InterruptedException {
        final Location blockLocation = block.getLocation();
        locks.putIfAbsent(blockLocation, new Object());

        // Faster query
        BlockState cachedState = cache.get(blockLocation);
        if (cachedState != null) return cachedState;

        else if (available)
            synchronized (locks.get(blockLocation)) {
                cachedState = cache.get(blockLocation);
                if (cachedState != null) return cachedState;
                return getBlockStateSlow(block, blockLocation);
            }
        return getBlockStateSlow(block, blockLocation);
    }

    @NotNull
    private static BlockState getBlockStateSlow(Block block, Location blockLocation)
            throws InterruptedException, ExecutionException {
        final BlockState queriedState = Slimefun.runSyncFuture((Callable<@NotNull BlockState>) block::getState).get();
        cache.put(blockLocation, queriedState);
        return queriedState;
    }

    public void onBlockEvents(BlockEvent event) {
        cache.remove(event.getBlock().getLocation());
    }

}
