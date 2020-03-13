package me.mrCookieSlime.Slimefun.api.item_transport;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Synchronized lock for internal use of Async CargoNet
 *
 * @param <T> type
 */
class SynchronizedLock<T> {

    private static int number = Integer.MIN_VALUE;

    private int serial;

    public SynchronizedLock() {
        this.serial = number;
        number++;
    }

    public synchronized void run(Consumer<T> consumer, T object) {
        consumer.accept(object);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SynchronizedLock<?> that = (SynchronizedLock<?>) o;
        return serial == that.serial;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serial);
    }

    public int getSerial() {
        return serial;
    }
}
