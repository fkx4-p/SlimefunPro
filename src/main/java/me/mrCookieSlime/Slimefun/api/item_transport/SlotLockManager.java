package me.mrCookieSlime.Slimefun.api.item_transport;

import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class SlotLockManager {

    private static final ConcurrentHashMap<Location, SlotLock> locks = new ConcurrentHashMap<>();

    static void runWithLock(Inventory inventory, int slot, Runnable runnable) {
        synchronized (getLock(inventory, slot)) {
            runnable.run();
        }
    }

    static void runWithLock(Location location, int slot, Runnable runnable) {
        synchronized (getLock(location, slot)) {
            runnable.run();
        }
    }

    @Nonnull
    static SlotLock getLock(Inventory inventory, int slot) {
        return getLock(Objects.requireNonNull(inventory.getLocation()), slot);
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
