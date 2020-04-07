package com.ishland.slimefun.core.cargonet;

import com.google.common.collect.ConcurrentHashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import com.ishland.slimefun.core.cargonet.cache.AttachedBlockCache;
import com.ishland.slimefun.core.cargonet.cache.BlockStateCache;
import com.ishland.slimefun.core.cargonet.cache.InventoryCache;
import io.github.thebusybiscuit.slimefun4.api.network.Network;
import io.github.thebusybiscuit.slimefun4.api.network.NetworkComponent;
import io.github.thebusybiscuit.slimefun4.utils.holograms.SimpleHologram;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.jetbrains.annotations.NotNull;
import sun.misc.Unsafe;

import java.util.*;
import java.util.concurrent.*;

public class CargoNet extends Network {

    // Instances
    public static ConcurrentHashMap<Location, CargoNet> instances = new ConcurrentHashMap<>();

    // Executors
    public static Set<Thread> tickingPoolThreads = Sets.newConcurrentHashSet();
    public static ExecutorService tickingPool;
    public static Set<Thread> executePoolThreads = Sets.newConcurrentHashSet();
    public static ExecutorService executePool;

    // Ticker
    public static CargoNetTickerThread tickerThread;

    // Energy consumption
    public static final int energyConsumptionManager = 4;
    public static final int energyConsumptionConnector = 2;
    public static final int energyConsumptionNode = 1;
    public static final int energyConsumptionSlot = 1;

    // Energy consumption lock
    private final Object consumeLock = new Object();

    // Input & output nodes
    private Set<Location> inputs = Sets.newConcurrentHashSet();
    private Set<Location> outputs = Sets.newConcurrentHashSet();

    // Stats
    private long lastHeartbeat;
    private Block lastHeartbeatBlock;
    private Integer triedConsume = 0;
    private Integer successConsume = 0;
    private long lastUpdate = System.currentTimeMillis();

    protected CargoNet(Location regulator) {
        super(regulator);
        alive(regulator.getBlock());
    }

    public static CargoNet getNetworkFromLocation(Location l) {
        return SlimefunPlugin.getNetworkManager().getNetworkFromLocation(l, CargoNet.class);
    }

    public static CargoNet getNetworkFromLocationOrCreate(Location l) {
        CargoNet cargoNetwork = getNetworkFromLocation(l);

        if (cargoNetwork == null) {
            cargoNetwork = new CargoNet(l);
            instances.put(l, cargoNetwork);
            SlimefunPlugin.getNetworkManager().registerNetwork(cargoNetwork);
        }

        return cargoNetwork;
    }

    public static void restartPool() {
        if (executePool != null)
            executePool.shutdown();
        executePoolThreads.clear();
        executePool = Executors.newCachedThreadPool(new ThreadFactory() {
            private int threadCount = 0;

            @Override
            public Thread newThread(@NotNull Runnable runnable) {
                Thread thread = new Thread(runnable, "Slimefun Async CargoNet Executor #" + threadCount++);
                executePoolThreads.add(thread);
                return thread;
            }
        });

        if (tickingPool != null)
            tickingPool.shutdown();
        tickingPoolThreads.clear();
        SlimefunPlugin.getCfg().getConfiguration().addDefault("cargonet.thread-pool.ticking", 10);
        tickingPool = Executors.newFixedThreadPool(SlimefunPlugin.getCfg().getInt("cargonet.thread-pool.ticking"), new ThreadFactory() {
            private int threadCount = 0;

            @Override
            public synchronized Thread newThread(@NotNull Runnable runnable) {
                Thread thread = new Thread(runnable, "Slimefun Async CargoNet Ticking #" + threadCount++);
                tickingPoolThreads.add(thread);
                return thread;
            }
        });

        if (tickerThread != null)
            tickerThread.stopTicker();
        tickerThread = new CargoNetTickerThread();
        tickerThread.start();

    }

    public static void shutdownPool() {
        if (tickerThread != null)
            tickerThread.stopTicker();

        if (executePool != null)
            executePool.shutdown();

        if (tickingPool != null)
            tickingPool.shutdown();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                for (Thread thread : tickingPoolThreads)
                    if (thread.isAlive() && thread.getState() == Thread.State.WAITING) {
                        Unsafe.getUnsafe().unpark(thread);
                    }
                Slimefun.didUnpark = true;
            }
        }, 2000);
        while ((executePool != null && !executePool.isTerminated()) || (tickingPool != null && !tickingPool.isTerminated())) {
            try {
                FutureTask<?> task = Slimefun.FUTURE_TASKS.poll(1, TimeUnit.SECONDS);
                if (task == null) continue;
                task.run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean consume(int consumption) {
        synchronized (this.consumeLock) {
            this.triedConsume += consumption;
        }
        if (ChargableBlock.getCharge(this.lastHeartbeatBlock) < consumption) return false;
        ChargableBlock.addCharge(this.lastHeartbeatBlock, -consumption);
        synchronized (this.consumeLock) {
            this.successConsume += consumption;
        }
        return true;
    }

    public long getLastHeartbeat() {
        return lastHeartbeat;
    }

    /**
     * This method returns the range of the {@link Network}.
     * The range determines how far the {@link Network} will search for
     * nearby nodes from any given node.
     * <p>
     * It basically translates to the maximum distance between nodes.
     *
     * @return the range of this {@link Network}
     */
    @Override
    public int getRange() {
        return 0;
    }

    /**
     * This method assigns the given {@link Location} a type of {@link NetworkComponent}
     * for classification.
     *
     * @param l The {@link Location} to classify
     * @return The assigned type of {@link NetworkComponent} for this {@link Location}
     */
    @Override
    public NetworkComponent classifyLocation(Location l) {
        String id = BlockStorage.checkID(l);
        if (id == null) return null;

        switch (id) {
            case "CARGO_MANAGER":
                return NetworkComponent.REGULATOR;
            case "CARGO_NODE":
                return NetworkComponent.CONNECTOR;
            case "CARGO_NODE_INPUT":
            case "CARGO_NODE_OUTPUT":
            case "CARGO_NODE_OUTPUT_ADVANCED":
                return NetworkComponent.TERMINUS;
            default:
                return null;
        }
    }

    @Override
    public void onClassificationChange(Location l, NetworkComponent from, NetworkComponent to) {
        if (from == NetworkComponent.TERMINUS) {
            inputs.remove(l);
            outputs.remove(l);
        }
        if (to == NetworkComponent.TERMINUS) {
            switch (Objects.requireNonNull(BlockStorage.checkID(l))) {
                case "CARGO_NODE_INPUT":
                    inputs.add(l);
                    break;
                case "CARGO_NODE_OUTPUT":
                case "CARGO_NODE_OUTPUT_ADVANCED":
                    outputs.add(l);
                    break;
                default:
                    break;
            }
        }
    }

    public Set<Location> getInputs() {
        return Collections.unmodifiableSet(inputs);
    }

    public Set<Location> getOutputs() {
        return Collections.unmodifiableSet(outputs);
    }

    public void alive(Block b) {
        lastHeartbeat = System.currentTimeMillis();
        lastHeartbeatBlock = b;
    }

    public Future<?> tick() {
        if (!regulator.equals(lastHeartbeatBlock.getLocation())) {
            SimpleHologram.update(lastHeartbeatBlock, "&4Multiple Cargo Regulators connected");
            return null;
        }

        super.tick();

        {
            final double elapsedTime = (System.currentTimeMillis() - lastUpdate) / 1000.0;
            if (elapsedTime > 0.9D) {
                String text;
                synchronized (consumeLock) {
                    text = (triedConsume > successConsume ? "&c&l" : "&a&l") +
                            successConsume / elapsedTime +
                            " &r&fJ/s &7 " + "(Tried " +
                            triedConsume / elapsedTime + " J/s)";
                    triedConsume = successConsume = 0;
                    lastUpdate = System.currentTimeMillis();
                }
                SimpleHologram.update(lastHeartbeatBlock, text);
            }
        }

        if (!consume(energyConsumptionManager
                + (energyConsumptionConnector * connectorNodes.size() + 1))) return null;

        return executePool.submit(() -> {
            try {
                // Prepare for inventories
                ConcurrentHashMultiset<InventoryCache.CachedInventory> inventories =
                        ConcurrentHashMultiset.create();
                {
                    List<Future<?>> futures = new LinkedList<>();
                    for (Location location : inputs)
                        futures.add(tickingPool.submit(() -> {
                            try {
                                Block attachedBlock = AttachedBlockCache.query(location.getBlock());
                                if (attachedBlock == null) return;
                                BlockState state = BlockStateCache.query(attachedBlock);
                                if (!(state instanceof Container)) return;
                                Container container = (Container) state;
                                inventories.add(InventoryCache.query(container));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }));
                    for (Future<?> future : futures)
                        future.get();
                }

                // Iterate over inventories
                {
                    List<Future<?>> futures = new LinkedList<>();
                    for (Multiset.Entry<InventoryCache.CachedInventory> entry : inventories.createEntrySet())
                        futures.add(tickingPool.submit(() -> {
                            try {

                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }));
                    for (Future<?> future : futures)
                        future.get();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
