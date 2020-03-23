package me.mrCookieSlime.Slimefun.api.item_transport;

import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class SlotLockManager {

    private static final ConcurrentHashMap<Location, SlotLock> locks = new ConcurrentHashMap<>();

    static boolean runWithLock(Inventory inventory, int slot, Runnable runnable) {
        return runWithLock(inventory.getLocation(), slot, runnable, false);
    }

    @SuppressWarnings("SameParameterValue")
    static boolean runWithLock(Inventory inventory, int slot, Runnable runnable, boolean tryOnly) {
        return runWithLock(inventory.getLocation(), slot, runnable, tryOnly);
    }

    static boolean runWithLock(Location location, int slot, Runnable runnable) {
        return runWithLock(location, slot, runnable, false);
    }

    static boolean runWithLock(Location location, int slot, Runnable runnable, boolean tryOnly) {
        final SlotLock lock = getLock(location, slot);
        return runWithLock(lock, runnable, tryOnly);
    }

    static boolean runWithLock(SlotLock lock, Runnable runnable, boolean tryOnly) {
        if (tryOnly && !lock.lock.tryLock())
            return false;
        else
            lock.lock.lock();
        try {
            runnable.run();
            return true;
        } finally {
            lock.lock.unlock();
        }
    }

    @Nonnull
    static SlotLock getLock(@Nonnull Location location, int slot) {
        SlotLock lock = new SlotLock(location, slot);
        if (locks.contains(lock))
            return locks.get(location);
        locks.put(location, lock);
        return lock;
    }

    private static class SlotLock {
        @Nonnull
        public final Location location;
        public final int slot;

        public final ReentrantLock lock = new ReentrantLock();

        public SlotLock(@NotNull Location location, int slot) {
            this.location = location;
            this.slot = slot;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SlotLock lock = (SlotLock) o;
            return slot == lock.slot &&
                    location.equals(lock.location);
        }

        @Override
        public int hashCode() {
            return Objects.hash(location, slot);
        }
    }
}
