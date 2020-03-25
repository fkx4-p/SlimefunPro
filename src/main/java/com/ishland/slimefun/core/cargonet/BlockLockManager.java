package com.ishland.slimefun.core.cargonet;

import com.ishland.slimefun.core.cargonet.cache.BlockStateCache;
import com.ishland.slimefun.core.cargonet.cache.InventoryCache;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class BlockLockManager {
    private static final Map<Location, SynchronizedLock<Block>> locks = new ConcurrentHashMap<>();

    public static void runWithLock(@Nonnull Block block, @Nonnull Consumer<Block> consumer)
            throws ExecutionException, InterruptedException {
        getLock(block).run(consumer, block);
    }

    @SuppressWarnings("unused")
    static void runWithLock(@Nonnull SynchronizedLock<Block> lock, @Nonnull Block block, @Nonnull Consumer<Block> consumer) {
        lock.run(consumer, block);
    }

    static SynchronizedLock<Block> getLock(@Nonnull Block block) throws ExecutionException, InterruptedException {
        SynchronizedLock<Block> currentLock = new SynchronizedLock<>();
        BlockState state = BlockStateCache.query(block);
        if (state instanceof Container) {
            InventoryCache.CachedInventory inventory;
            try {
                inventory = InventoryCache.query((Container) state);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            boolean lockExists = true;
            SynchronizedLock<Block> originalLock = null;
            {
                for (Location location : inventory.locations) {
                    if (originalLock == null) originalLock = locks.get(location);
                    if (originalLock == null) {
                        lockExists = false;
                        break;
                    }
                    if (!Objects.equals(locks.get(location), originalLock)) {
                        lockExists = false;
                        break;
                    }
                }
            }
            if (!lockExists)
                for (Location location : inventory.locations)
                    locks.put(location, currentLock);
            else currentLock = originalLock;
        } else {
            Location blockLocation = block.getLocation();
            SynchronizedLock<Block> originalLock = locks.get(blockLocation);
            if (originalLock == null) {
                // Slimefun.getLogger().info("created block");
                locks.put(blockLocation, currentLock);
            } else {
                // Slimefun.getLogger().info("reused block");
                currentLock = originalLock;
            }
        }
        return currentLock;
    }
}