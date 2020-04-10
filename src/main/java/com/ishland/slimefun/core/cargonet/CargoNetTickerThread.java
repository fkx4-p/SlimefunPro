package com.ishland.slimefun.core.cargonet;

import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.logging.Level;

public class CargoNetTickerThread extends Thread {

    private boolean isStopping = false;
    private long pause = 100;

    public CargoNetTickerThread() {
        super("Slimefun Async CargoNet Ticker Thread");
        SlimefunPlugin.getCfg().setDefaultValue("cargonet.delay", 100);
        pause = SlimefunPlugin.getCfg().getLong("cargonet.delay");
    }

    @Override
    public void run() {
        while (!isStopping) {
            try {
                long startTime = System.nanoTime();
                mainLoop();
                long endTime = System.nanoTime();
                long elapsedTimeNanos = endTime - startTime;
                if (pause - (elapsedTimeNanos / 1000 / 1000) > 0)
                    Thread.sleep(pause - (elapsedTimeNanos / 1000 / 1000));
            } catch (Throwable throwable) {
                Slimefun.getLogger().log(Level.SEVERE, "Error while running CargoNet ticker", throwable);
            }
        }
    }

    private void mainLoop() {
        long startTime = System.nanoTime();

        Map<Location, Future<?>> futures = new HashMap<>();
        for (CargoNet cargoNetEntry : SlimefunPlugin.getNetworkManager()
                .getNetworks(CargoNet.class)) {
            try {
                final Future<?> future = cargoNetEntry.tick();
                if (future != null)
                    futures.put(cargoNetEntry.getRegulator(), future);
            } catch (Exception e) {
                final Location location = cargoNetEntry.getRegulator();
                SlimefunPlugin.getTicker().reportErrors(location, e);
            }
        }

        for (Map.Entry<Location, Future<?>> entry : futures.entrySet()) {
            try {
                entry.getValue().get();
            } catch (Exception e) {
                final Location location = entry.getKey();
                SlimefunPlugin.getTicker().reportErrors(location, e);
            }
        }

        long endTime = System.nanoTime();
        CargoNetTimings.timeNanos = endTime - startTime;
        CargoNetTimings.tickedCargoManagers = futures.size();
    }

    public void stopTicker() {
        isStopping = true;
    }
}
