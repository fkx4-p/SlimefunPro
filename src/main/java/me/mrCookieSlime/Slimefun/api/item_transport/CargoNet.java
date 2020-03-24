package me.mrCookieSlime.Slimefun.api.item_transport;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import io.github.thebusybiscuit.cscorelib2.chat.ChatColors;
import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import io.github.thebusybiscuit.cscorelib2.math.DoubleHandler;
import io.github.thebusybiscuit.slimefun4.api.network.Network;
import io.github.thebusybiscuit.slimefun4.api.network.NetworkComponent;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.holograms.SimpleHologram;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.inventory.UniversalBlockMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.cache.AttachedBlockCache;
import me.mrCookieSlime.Slimefun.api.item_transport.cache.BlockStateCache;
import me.mrCookieSlime.Slimefun.api.item_transport.cache.InventoryCache;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

public class CargoNet extends Network {

    public static final int energyConsumptionManager = 4;
    public static final int energyConsumptionConnector = 2;
    public static final int energyConsumptionNode = 1;
    public static final int energyConsumptionSlot = 1;

    private static final int RANGE = 5;

    private static final int[] slots = { 19, 20, 21, 28, 29, 30, 37, 38, 39 };

    // Chest Terminal Stuff
    private static final int[] TERMINAL_SLOTS = { 0, 1, 2, 3, 4, 5, 6, 9, 10, 11, 12, 13, 14, 15, 18, 19, 20, 21, 22, 23, 24, 27, 28, 29, 30, 31, 32, 33, 36, 37, 38, 39, 40, 41, 42 };
    private static final int TERMINAL_OUT_SLOT = 17;

    // Chest Terminal Stuff
    public static final int[] terminal_slots = new int[] {
            0, 1, 2, 3, 4, 5, 6,
            9, 10, 11, 12, 13, 14, 15,
            18, 19, 20, 21, 22, 23, 24,
            27, 28, 29, 30, 31, 32, 33,
            36, 37, 38, 39, 40, 41, 42
    };

    private static final ItemStack terminal_noItem_item =
            new CustomItem(new ItemStack(Material.BARRIER), "&4No Item cached");

    private final ItemStack terminalPlaceholderItem = new CustomItem(new ItemStack(Material.BARRIER), "&4No Item cached");

    public Set<Location> getInputNodes() {
        return Collections.unmodifiableSet(inputNodes);
    }

    public Set<Location> getOutputNodes() {
        return Collections.unmodifiableSet(outputNodes);
    }

    private Set<Location> inputNodes = Sets.newConcurrentHashSet();
    private Set<Location> outputNodes = Sets.newConcurrentHashSet();

    private final Object consumeLock = new Object();
    private Integer triedConsume = 0;
    private Integer successConsume = 0;
    private boolean secondTick = false;
    private long lastUpdate = System.currentTimeMillis();

    // Chest Terminal Stuff
    final Set<Location> terminals = Sets.newConcurrentHashSet();
    final Set<Location> imports = Sets.newConcurrentHashSet();
    final Set<Location> exports = Sets.newConcurrentHashSet();

    private final ConcurrentMap<Location, Integer> roundRobin = new ConcurrentHashMap<>();
    final LinkedBlockingQueue<ItemRequest> itemRequests = new LinkedBlockingQueue<>();

    public static Set<Thread> tickingPoolThreads = Sets.newConcurrentHashSet();
    public static ExecutorService tickingPool;

    public static Set<Thread> requestQueuePoolThreads = Sets.newConcurrentHashSet();
    public static ExecutorService requestQueuePool;

    public static Set<Thread> executePoolThreads = Sets.newConcurrentHashSet();
    public static ExecutorService executePool;

    public static CargoNetTickerThread tickerThread;

    public static ConcurrentMap<Location, CargoNet> instances = new ConcurrentHashMap<>();

    public Location location;
    private Block managerBlock = null;
    public long lastHeartbeat = 0;

    public static int maxServerThreadTime = 10;

    public static CargoNet getNetworkFromLocation(Location l) {
        return SlimefunPlugin.getNetworkManager().getNetworkFromLocation(l, CargoNet.class);
    }

    public static CargoNet getNetworkFromLocationOrCreate(Location l) {
        CargoNet cargoNetwork = getNetworkFromLocation(l);

        if (cargoNetwork == null) {
            cargoNetwork = new CargoNet(l);
            instances.put(l, cargoNetwork);
            ItemPostTransport.runNewTask(cargoNetwork);
            SlimefunPlugin.getNetworkManager().registerNetwork(cargoNetwork);
        }

        return cargoNetwork;
    }

    protected CargoNet(Location l) {
        super(l);
        this.location = l;
    }

    public int getRange() {
        return RANGE;
    }

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
            case "CT_IMPORT_BUS":
            case "CT_EXPORT_BUS":
            case "CHEST_TERMINAL":
                return NetworkComponent.TERMINUS;
            default:
                return null;
        }
    }

    public void onClassificationChange(Location l, NetworkComponent from, NetworkComponent to) {
        if (from == NetworkComponent.REGULATOR)
            CargoNet.instances.remove(l);
        if (from == NetworkComponent.TERMINUS) {
            inputNodes.remove(l);
            outputNodes.remove(l);
            terminals.remove(l);
            imports.remove(l);
            exports.remove(l);
        }
        if (to == NetworkComponent.TERMINUS) {
            switch (Objects.requireNonNull(BlockStorage.checkID(l))) {
                case "CARGO_NODE_INPUT":
                    inputNodes.add(l);
                    break;
                case "CARGO_NODE_OUTPUT":
                case "CARGO_NODE_OUTPUT_ADVANCED":
                    outputNodes.add(l);
                    break;
                case "CHEST_TERMINAL":
                    terminals.add(l);
                    break;
                case "CT_IMPORT_BUS":
                    imports.add(l);
                    break;
                case "CT_EXPORT_BUS":
                    exports.add(l);
                    break;
                default:
                    break;
            }
        }
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

        {
            if (requestQueuePool != null)
                requestQueuePool.shutdown();
            requestQueuePoolThreads.clear();
            SlimefunPlugin.getCfg().getConfiguration().addDefault("cargonet.thread-pool.request-queue", 2);
            int threadCount = SlimefunPlugin.getCfg().getInt("cargonet.thread-pool.request-queue");
            requestQueuePool = Executors.newFixedThreadPool(threadCount, new ThreadFactory() {
                private int threadCount = 0;

                @Override
                public synchronized Thread newThread(@NotNull Runnable runnable) {
                    Thread thread = new Thread(runnable, "Slimefun Async CargoNet Post Actions #" + threadCount++);
                    requestQueuePoolThreads.add(thread);
                    return thread;
                }
            });
            {
                Set<Callable<Object>> runnables = Sets.newConcurrentHashSet();
                for (CargoNet instance : instances.values())
                    runnables.add(() -> {
                        ItemPostTransport.runNewTask(instance);
                        return null;
                    });
                try {
                    requestQueuePool.invokeAll(runnables);
                } catch (InterruptedException ignored) {
                }
            }
        }

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

        new Thread(() -> {
            Set<CargoNet> aInstances = Sets.newConcurrentHashSet(instances.values());
            while (!aInstances.isEmpty()) {
                Iterator<CargoNet> iterator = aInstances.iterator();
                while (iterator.hasNext()) {
                    CargoNet instance = iterator.next();
                    if (!instance.itemRequests.isEmpty()) {
                        try {
                            Thread.sleep(60);
                        } catch (InterruptedException ignored) {
                        }
                    }
                    if (instance.itemRequests.isEmpty())
                        iterator.remove();

                }
            }

            if (requestQueuePool != null)
                requestQueuePool.shutdown();
        }).start();

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

    public void alive(Block block) {
        this.managerBlock = block;
        this.lastHeartbeat = System.currentTimeMillis();
        if (!instances.containsKey(location))
            instances.put(location, this);
    }

    public Future<?> tick() {
        if (!regulator.equals(managerBlock.getLocation())) {
            SimpleHologram.update(managerBlock, "&4Multiple Cargo Regulators connected");
            return null;
        }

        super.tick();

        if (connectorNodes.isEmpty() && terminusNodes.isEmpty()) {
            SimpleHologram.update(managerBlock, "&cNo Cargo Nodes found");
        } else {
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
                    SimpleHologram.update(managerBlock, text);
                }
            }

            return executePool.submit(() -> {
                ConcurrentMap<Integer, List<Location>> output = new ConcurrentHashMap<>();

                List<Location> list = new LinkedList<>();
                int lastFrequency = -1;

                for (Location outputNode : outputNodes) {
                    int frequency = getFrequency(outputNode);

                    if (frequency != lastFrequency && lastFrequency != -1) {
                        output.merge(lastFrequency, list, (prev, next) -> {
                            prev.addAll(next);
                            return prev;
                        });

                        list = new LinkedList<>();
                    }

                    list.add(outputNode);
                    lastFrequency = frequency;
                }

                if (!list.isEmpty()) {
                    output.merge(lastFrequency, list, (prev, next) -> {
                        prev.addAll(next);
                        return prev;
                    });
                }

                //Chest Terminal Stuff
                Set<Location> providers = Sets.newConcurrentHashSet();
                Set<Location> destinations = Sets.newConcurrentHashSet();

                List<Location> output16 = output.get(16);
                if (output16 != null) destinations.addAll(output16);

                for (Location inputNode : inputNodes) {
                    int frequency = getFrequency(inputNode);

                    if (frequency == 16) {
                        providers.add(inputNode);
                    }
                }

                if (!consume(this,
                        energyConsumptionManager
                                + (energyConsumptionConnector * connectorNodes.size() + 1))) return;

                //if(!consume(this,
                //        energyConsumptionManager // Manager consumption
                //                + inputNodes.size() * energyConsumptionNode // Input Node consumption
                //                + outputNodes.size() * energyConsumptionNode // Output Node consumption
                //                + connectorNodes.size() * energyConsumptionConnector // Connector consumption
                //)) return;

                // Code exported from runSync() - start
                try {
                    if (BlockStorage.getLocationInfo(managerBlock.getLocation(), "visualizer") == null) {
                        this.display();
                    }

                    Set<Future<?>> futures = Sets.newConcurrentHashSet();

                    //Chest Terminal Code
                    if (SlimefunPlugin.getThirdPartySupportService().isChestTerminalInstalled()) {
                        for (Location bus : imports)
                            futures.add(tickingPool.submit(() -> {
                                try {
                                    BlockMenu menu = BlockStorage.getInventory(bus);
                                    if (menu == null) return;

                                    if (!consume(this, energyConsumptionNode)) return;

                                    final Block attachedBlock = AttachedBlockCache.query(bus.getBlock());
                                    if (attachedBlock == null) return; // In case there is no block attached to it
                                    BlockLockManager.runWithLock(
                                            attachedBlock,
                                            target -> {
                                                try {
                                                    ItemStack item17 = menu.getItemInSlot(17);
                                                    if (item17 == null) {
                                                        ItemStackAndInteger stack = CargoUtils.withdraw(bus.getBlock(), target, -1);

                                                        if (stack != null) {
                                                            menu.replaceExistingItem(17, stack.getItem());
                                                        }
                                                    }

                                                    if (item17 != null) {
                                                        itemRequests.add(new ItemRequest(bus, 17, item17, ItemTransportFlow.INSERT, providers, destinations));
                                                    }
                                                } catch (Exception e) {
                                                    throw new RuntimeException(e);
                                                }
                                            });
                                } catch (Exception e) {
                                    Slimefun.getLogger().log(Level.WARNING, e.getMessage(), e);
                                }
                            }));
                    }

                    {
                        for (Location bus : exports) {
                            futures.add(tickingPool.submit(() -> {
                                try {
                                    BlockMenu menu = BlockStorage.getInventory(bus);
                                    if (menu == null) return;

                                    if (!consume(this, energyConsumptionNode)) return;

                                    final Block attachedBlock = AttachedBlockCache.query(bus.getBlock());
                                    if (attachedBlock == null) return; // In case there is no block attached to it
                                    BlockLockManager.runWithLock(attachedBlock, target -> {
                                        try {
                                            ItemStack item17 = menu.getItemInSlot(17);
                                            if (item17 != null) {
                                                menu.replaceExistingItem(
                                                        17,
                                                        CargoUtils.insert(bus.getBlock(), target, item17, -1));
                                            }

                                            if (item17 == null) {
                                                List<ItemStack> items = new ArrayList<>();
                                                for (int slot : slots) {
                                                    ItemStack template = menu.getItemInSlot(slot);
                                                    if (template != null) items.add(new CustomItem(template, 1));
                                                }

                                                if (!items.isEmpty()) {
                                                    int index = Integer.parseInt(BlockStorage.getLocationInfo(bus, "index"));

                                                    index++;
                                                    if (index > (items.size() - 1)) index = 0;

                                                    int finalIndex = index;
                                                    BlockStorage.addBlockInfo(bus, "index", String.valueOf(finalIndex));
                                                    itemRequests.add(new ItemRequest(bus, 17, items.get(index), ItemTransportFlow.WITHDRAW, providers, destinations));
                                                }
                                            }
                                        } catch (Exception e) {
                                            throw new RuntimeException(e);
                                        }

                                    });

                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }));
                        }
                    }

                    {
                        for (Location terminal : terminals) {
                            futures.add(tickingPool.submit(() -> {
                                try {
                                    BlockMenu menu = BlockStorage.getInventory(terminal);
                                    if (menu == null) return;

                                    if (!consume(this, energyConsumptionNode)) return;

                                    ItemStack sendingItem = menu.getItemInSlot(TERMINAL_OUT_SLOT);

                                    if (sendingItem != null) {
                                        itemRequests.add(
                                                new ItemRequest(terminal, TERMINAL_OUT_SLOT, sendingItem, ItemTransportFlow.INSERT, providers, destinations));
                                    }
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }));
                        }
                    }


                    // All operations happen here: Everything gets iterated from the Input Nodes. (Apart from ChestTerminal Buses)
                    {
                        for (Location input : inputNodes) {
                            futures.add(tickingPool.submit(() -> {
                                try {
                                    int frequency = getFrequency(input);

                                    if (frequency < 0 || frequency > 15) {
                                        return;
                                    }

                                    Block target = AttachedBlockCache.query(input.getBlock());
                                    if (target == null) return;

                                    if (!consume(this, energyConsumptionNode)) return;

                                    try {
                                        AtomicReference<ItemStack> stack = new AtomicReference<>(null);
                                        AtomicInteger previousSlot = new AtomicInteger(-1);

                                        @SuppressWarnings("deprecation")
                                        Config cfg = BlockStorage.getLocationInfo(input);
                                        boolean roundRobin = "true".equals(cfg.getString("round-robin"));

                                        AtomicReference<ItemStackAndInteger> slot = new AtomicReference<>();
                                        slot.set(CargoUtils.withdraw(input.getBlock(), target,
                                                Integer.parseInt(cfg.getString("index"))));
                                        if (slot.get() != null) {
                                            stack.set(slot.get().getItem());
                                            previousSlot.set(slot.get().getInt());
                                        }


                                        if (stack.get() != null) {
                                            List<Location> outputs = output.get(frequency);

                                            if (outputs != null) {
                                                List<Location> outputsList = new ArrayList<>(outputs);

                                                if (roundRobin) {
                                                    int cIndex = this.roundRobin.getOrDefault(input, 0);

                                                    if (cIndex < outputsList.size()) {
                                                        for (int i = 0; i < cIndex; i++) {
                                                            Location temp = outputsList.get(0);
                                                            outputsList.remove(temp);
                                                            outputsList.add(temp);
                                                        }
                                                        cIndex++;
                                                    } else cIndex = 1;

                                                    this.roundRobin.put(input, cIndex);
                                                }

                                                for (Location out : outputsList) {
                                                    try {
                                                        //noinspection ConstantConditions
                                                        if (out.getBlock() == null) continue;
                                                        final Block attachedBlock = AttachedBlockCache
                                                                .query(out.getBlock());
                                                        if (attachedBlock == null)
                                                            continue; // In case there is no block attached to it
                                                        stack.set(CargoUtils.insert(
                                                                out.getBlock(), attachedBlock,
                                                                stack.get(), -1));
                                                        if (stack.get() == null) {
                                                            throw new RuntimeException("break");
                                                        }
                                                    } catch (Exception e) {
                                                        if (e.getMessage() != null && e.getMessage().equals("break"))
                                                            break;
                                                        else throw new RuntimeException(e);
                                                    }
                                                }
                                            }
                                        }

                                        try {
                                            if (stack.get() != null && previousSlot.get() > -1) {
                                                DirtyChestMenu menu = CargoUtils.getChestMenu(target);

                                                if (menu != null) {
                                                    int finalPreviousSlot = previousSlot.get();
                                                    ItemStack finalStack = stack.get();
                                                    SlotLockManager.runWithLock(target.getLocation(), finalPreviousSlot,
                                                            () ->
                                                                    menu.replaceExistingItem(finalPreviousSlot,
                                                                            finalStack));
                                                } else {
                                                    BlockState state;
                                                    try {
                                                        state = BlockStateCache.query(target);
                                                    } catch (Exception e) {
                                                        throw new RuntimeException(e);
                                                    }
                                                    if (state instanceof Container) {
                                                        Inventory inv = InventoryCache.query((Container) state).inventory;
                                                        int finalPreviousSlot1 = previousSlot.get();
                                                        ItemStack finalStack1 = stack.get();
                                                        SlotLockManager.runWithLock(inv, finalPreviousSlot1, () ->
                                                        {
                                                            try {
                                                                inv.setItem(finalPreviousSlot1, finalStack1);
                                                            } catch (Exception e) {
                                                                throw new RuntimeException(e);
                                                            }
                                                        });
                                                    }

                                                }
                                            }
                                        } catch (Exception e) {
                                            throw new RuntimeException(e);
                                        }
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }


                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }));
                        }
                    }

                    //Chest Terminal Code
                    if (SlimefunPlugin.getThirdPartySupportService().isChestTerminalInstalled()) {
                        Set<ItemStackAndInteger> items = new ConcurrentSkipListSet<>(Comparator.comparingInt(item -> -item.getInt()));

                        {
                            for (Location l : providers) {
                                futures.add(tickingPool.submit(() -> {
                                    try {
                                        if (!consume(this, energyConsumptionNode)) return;
                                        final Block attachedBlock = AttachedBlockCache.query(l.getBlock());
                                        if (attachedBlock == null) return; // In case there is no block attached to it
                                        BlockLockManager.runWithLock(attachedBlock, target -> {
                                            try {
                                                UniversalBlockMenu menu = BlockStorage.getUniversalInventory(target);

                                                if (menu != null) {
                                                    for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.WITHDRAW, null)) {
                                                        ItemStack is = menu.getItemInSlot(slot);
                                                        filter(is, items, l);
                                                    }
                                                } else if (BlockStorage.hasInventory(target)) {
                                                    BlockMenu blockMenu = BlockStorage.getInventory(target);
                                                    @SuppressWarnings("deprecation")
                                                    Config cfg = BlockStorage.getLocationInfo(target.getLocation());

                                                    if (cfg.getString("id").startsWith("BARREL_") && cfg.getString("storedItems") != null) {
                                                        int stored = Integer.parseInt(cfg.getString("storedItems"));

                                                        for (int slot : blockMenu.getPreset().getSlotsAccessedByItemTransport(blockMenu, ItemTransportFlow.WITHDRAW, null)) {
                                                            ItemStack is = blockMenu.getItemInSlot(slot);

                                                            if (is != null && CargoUtils.matchesFilter(l.getBlock(), is, -1)) {
                                                                boolean add = true;

                                                                for (ItemStackAndInteger item : items) {
                                                                    if (SlimefunManager.isItemSimilar(is, item.getItem(), true)) {
                                                                        add = false;
                                                                        item.add(is.getAmount() + stored);
                                                                    }
                                                                }

                                                                if (add) {
                                                                    items.add(new ItemStackAndInteger(new CustomItem(is, 1), is.getAmount() + stored));
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        handleWithdraw(blockMenu, items, l);
                                                    }
                                                } else {
                                                    BlockState state;
                                                    try {
                                                        state = BlockStateCache.query(target);
                                                    } catch (Exception e) {
                                                        throw new RuntimeException(e);
                                                    }

                                                    if (state instanceof Container) {
                                                        Inventory inv = InventoryCache.query((Container) state).inventory;

                                                        for (ItemStack is : inv.getContents()) {
                                                            filter(is, items, l);
                                                        }
                                                    }
                                                }
                                            } catch (Exception e) {
                                                throw new RuntimeException(e);
                                            }
                                        });
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }));
                            }
                        }


                        {
                            for (Location l : terminals) {
                                futures.add(tickingPool.submit(() -> {
                                    try {
                                        BlockMenu menu = BlockStorage.getInventory(l);
                                        if (menu == null) return;

                                        if (!consume(this, energyConsumptionNode)) return;

                                        int page = Integer.parseInt(BlockStorage.getLocationInfo(l, "page"));

                                        if (!items.isEmpty() && items.size() < (page - 1) * TERMINAL_SLOTS.length + 1) {
                                            page = 1;
                                            BlockStorage.addBlockInfo(l, "page", String.valueOf(1));
                                        }

                                        for (int i = 0; i < terminal_slots.length; i++) {
                                            int slot = terminal_slots[i];

                                            if (items.size() > i + (terminal_slots.length * (page - 1))) {

                                                if (items.size() > i + (terminal_slots.length * (page - 1))) {
                                                    ItemStackAndInteger item = Iterables.get(items, i + (terminal_slots.length * (page - 1)));

                                                    ItemStack stack = item.getItem().clone();
                                                    ItemMeta im = stack.getItemMeta();
                                                    if (im == null) continue;
                                                    List<String> lore = new ArrayList<>();
                                                    lore.add("");
                                                    lore.add(ChatColors.color("&7Stored Items: &r" + DoubleHandler.getFancyDouble(item.getInt())));

                                                    if (stack.getMaxStackSize() > 1)
                                                        lore.add(ChatColors.color("&7<Left Click: Request 1 | Right Click: Request " + (Math.min(item.getInt(), stack.getMaxStackSize())) + ">"));
                                                    else
                                                        lore.add(ChatColors.color("&7<Left Click: Request 1>"));

                                                    lore.add("");
                                                    if (im.hasLore()) {
                                                        lore.addAll(Objects.requireNonNull(im.getLore()));
                                                    }

                                                    im.setLore(lore);
                                                    stack.setItemMeta(im);
                                                    menu.replaceExistingItem(slot, stack);
                                                    menu.addMenuClickHandler(slot, (p, sl, is, action) -> {
                                                        int amount = Math.min(item.getInt(), item.getItem().getMaxStackSize());
                                                        itemRequests.add(new ItemRequest(l, 44, new CustomItem(item.getItem(), action.isRightClicked() ? amount : 1), ItemTransportFlow.WITHDRAW, providers, destinations));
                                                        return false;
                                                    });

                                                } else {
                                                    menu.replaceExistingItem(slot, terminalPlaceholderItem);
                                                    menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }));
                            }
                        }
                        // Code exported from runSync() - end
                    }

                    for (Future<?> future : futures)
                        future.get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return null;
    }

    private static int getFrequency(Location l) {
        int freq = 0;
        try {
            String str = BlockStorage.getLocationInfo(l).getString("frequency");
            if (str != null) freq = Integer.parseInt(str);
        } catch (Exception x) {
            Slimefun.getLogger().log(Level.SEVERE, "An Error occured while parsing a Cargo Node Frequency", x);
        }
        return freq;
    }

    private void handleWithdraw(DirtyChestMenu menu, Set<ItemStackAndInteger> items, Location l) {
        for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.WITHDRAW, null)) {
            try {
                filter(menu.getItemInSlot(slot), items, l);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void filter(ItemStack is, Set<ItemStackAndInteger> items, Location l) {
        if (is != null && CargoUtils.matchesFilter(l.getBlock(), is, -1)) {
            boolean add = true;

            for (ItemStackAndInteger item : items) {
                if (SlimefunManager.isItemSimilar(is, item.getItem(), true)) {
                    add = false;
                    item.add(is.getAmount());
                }
            }

            if (add) {
                items.add(new ItemStackAndInteger(new CustomItem(is, 1), is.getAmount()));
            }
        }
    }

    @SuppressWarnings("unused")
    static void printStackTrace(@NotNull Set<Thread> threads) {
        for (Thread thread : threads) {
            if (!thread.isAlive()) continue;
            Slimefun.getLogger().warning("Thread: " + thread.getName());
            Slimefun.getLogger().warning("ID: " + thread.getId() +
                    " | State: " + thread.getState().name());
            Slimefun.getLogger().warning("");
            Slimefun.getLogger().warning("Stacktrace: ");
            for (StackTraceElement stackTrace : thread.getStackTrace())
                Slimefun.getLogger().warning(stackTrace.toString());
            Slimefun.getLogger().warning("");
            Slimefun.getLogger().warning("--------------------------------");
        }
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    static boolean consume(CargoNet instance, int consumption) {
        synchronized (instance.consumeLock) {
            instance.triedConsume += consumption;
        }
        if (ChargableBlock.getCharge(instance.location) < consumption) return false;
        ChargableBlock.addCharge(instance.location, -consumption);
        synchronized (instance.consumeLock) {
            instance.successConsume += consumption;
        }
        return true;
    }
}
