package me.mrCookieSlime.Slimefun.api.item_transport.cache;

import com.destroystokyo.paper.event.server.ServerTickEndEvent;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

public class BlockStateCache implements Listener {

    private static ConcurrentMap<Location, BlockState> cache = new ConcurrentHashMap<>();
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
    public static BlockState query(Block block) throws ExecutionException, InterruptedException {
        final Location blockLocation = block.getLocation();
        locks.putIfAbsent(blockLocation, new Object());

        // Faster query
        BlockState cachedState = cache.get(blockLocation);
        if (cachedState != null) return cachedState;

        else if (isPaper)
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

    @EventHandler
    public void onServerTickEnd(ServerTickEndEvent event) {
        cache.clear();
    }

}
