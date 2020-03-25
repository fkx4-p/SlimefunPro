package com.ishland.slimefun.core.cargonet;

import me.mrCookieSlime.Slimefun.api.Slimefun;
import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class SlotLockManager {

    private static final ConcurrentHashMap<Location, SlotLock> locks = new ConcurrentHashMap<>();

    public static boolean runWithLock(Inventory inventory, int slot, Runnable runnable) {
        return runWithLock(inventory.getLocation(), slot, runnable, false);
    }

    @SuppressWarnings("SameParameterValue")
    public static boolean runWithLock(Inventory inventory, int slot, Runnable runnable, boolean tryOnly) {
        return runWithLock(inventory.getLocation(), slot, runnable, tryOnly);
    }

    public static boolean runWithLock(Location location, int slot, Runnable runnable) {
        return runWithLock(location, slot, runnable, false);
    }

    public static boolean runWithLock(Location location, int slot, Runnable runnable, boolean tryOnly) {
        final SlotLock lock = getLock(location, slot);
        return runWithLock(lock, runnable, tryOnly);
    }

    static boolean runWithLock(SlotLock lock, Runnable runnable, boolean tryOnly) {
        final ReentrantLock lock1 = lock.lock;
        if (tryOnly) {
            try {
                if (!lock1.tryLock(10, TimeUnit.MICROSECONDS))
                    return false;
            } catch (InterruptedException e) {
                return false;
            }
        } else {
            try {
                if (!lock1.tryLock(10, TimeUnit.SECONDS)) {
                    Slimefun.getLogger().warning("=======================================================");
                    Slimefun.getLogger().warning("Possible deadlock of Async CargoNet was detected.");
                    Slimefun.getLogger().warning("This may be caused by a bug of Slimefun Async CargoNet,");
                    Slimefun.getLogger().warning("or misconfiguration.");
                    Slimefun.getLogger().warning("To make the system function again, we removed the lock");
                    Slimefun.getLogger().warning("and created a new lock for this operation.");
                    Slimefun.getLogger().warning("Details of the operation: ");
                    Slimefun.getLogger().warning("Location: " + lock.location);
                    Slimefun.getLogger().warning("Slot being operated: " + lock.slot);
                    Slimefun.getLogger().warning("Lock information: " + lock.lock.toString());
                    Slimefun.getLogger().warning("=======================================================");
                    lock.lock = new ReentrantLock();
                    //noinspection ConstantConditions
                    runWithLock(lock, runnable, tryOnly);
                }
            } catch (InterruptedException e) {
                return false;
            }
        }
        try {
            runnable.run();
            return true;
        } finally {
            lock1.unlock();
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

        public ReentrantLock lock = new ReentrantLock();

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
