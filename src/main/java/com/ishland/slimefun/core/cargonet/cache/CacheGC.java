package com.ishland.slimefun.core.cargonet.cache;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class CacheGC {
    /**
     * GC Thread Pool
     */
    public static ExecutorService cleanThread = Executors.newFixedThreadPool(4, new ThreadFactory() {
        private int serial = 0;

        @Override
        public Thread newThread(@NotNull Runnable runnable) {
            return new Thread(runnable, "Slimefun Async CargoNet GC #" + serial++);
        }
    });
}