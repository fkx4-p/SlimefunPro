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
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.MenuClickHandler;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.inventory.UniversalBlockMenu;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.block.data.Directional;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.logging.Level;

public class CargoNet extends Network {

    public static boolean extraChannels = false;

    private static final int RANGE = 5;

    private static final int[] slots = {19, 20, 21, 28, 29, 30, 37, 38, 39};

	// Chest Terminal Stuff
	public static final int[] terminal_slots = {0, 1, 2, 3, 4, 5, 6, 9, 10, 11, 12, 13, 14, 15, 18, 19, 20, 21, 22, 23, 24, 27, 28, 29, 30, 31, 32, 33, 36, 37, 38, 39, 40, 41, 42};
	public static final int TERMINAL_OUT_SLOT = 17;

	private static final ItemStack terminal_noitem_item = new CustomItem(new ItemStack(Material.BARRIER), "&4No Item cached");

	private Set<Location> inputNodes = new HashSet<>();
	private Set<Location> outputNodes = new HashSet<>();
    // Chest Terminal Stuff
    public static final int[] terminal_slots = new int[]{
            0, 1, 2, 3, 4, 5, 6,
            9, 10, 11, 12, 13, 14, 15,
            18, 19, 20, 21, 22, 23, 24,
            27, 28, 29, 30, 31, 32, 33,
            36, 37, 38, 39, 40, 41, 42
    };
    public static final int TERMINAL_OUT_SLOT = 17;

    private static final ItemStack terminal_noItem_item =
            new CustomItem(new ItemStack(Material.BARRIER), "&4No Item cached");
    private static final MenuClickHandler terminal_noItem_handler = (p, slot, item, action) -> false;

    private Set<Location> inputNodes = Sets.newConcurrentHashSet();
    private Set<Location> outputNodes = Sets.newConcurrentHashSet();

    // Chest Terminal Stuff
    final Set<Location> terminals = Sets.newConcurrentHashSet();
    final Set<Location> imports = Sets.newConcurrentHashSet();
    final Set<Location> exports = Sets.newConcurrentHashSet();

    private final ConcurrentMap<Location, Integer> roundRobin = new ConcurrentHashMap<>();
    final LinkedBlockingQueue<ItemRequest> itemRequests = new LinkedBlockingQueue<>();

    private static final Map<Location, SynchronizedLock<Block>> locks = new ConcurrentHashMap<>();

    public static Set<Thread> tickingPoolThreads = Sets.newConcurrentHashSet();
    public static ExecutorService tickingPool;

    public static Set<Thread> requestQueuePoolThreads = Sets.newConcurrentHashSet();
    public static ExecutorService requestQueuePool;

    public static Set<Thread> executePoolThreads = Sets.newConcurrentHashSet();
    public static ExecutorService executePool;

    public static ConcurrentMap<Location, CargoNet> instances = new ConcurrentHashMap<>();

    private Future<?> lastFuture = null;

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

    @Deprecated
    public static boolean isConnected(Block b) {
        return getNetworkFromLocation(b.getLocation()) != null;
    }

    protected CargoNet(Location l) {
        super(l);
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

    public void locationClassificationChange(Location l, NetworkComponent from, NetworkComponent to) {
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
                Thread thread = new Thread(runnable, "Slimefun Async CargoNet Executor-" + threadCount++);
                executePoolThreads.add(thread);
                return thread;
            }
        });

        if (tickingPool != null)
            tickingPool.shutdown();
        tickingPoolThreads.clear();
        SlimefunPlugin.getCfg().getConfiguration().addDefault("cargonet.thread-pool.ticking", 4);
        tickingPool = Executors.newFixedThreadPool(SlimefunPlugin.getCfg().getInt("cargonet.thread-pool.ticking"), new ThreadFactory() {
            private int threadCount = 0;

            @Override
            public synchronized Thread newThread(@NotNull Runnable runnable) {
                Thread thread = new Thread(runnable, "Slimefun Async CargoNet Ticking-" + threadCount++);
                tickingPoolThreads.add(thread);
                return thread;
            }
        });

        {
            if (requestQueuePool != null)
                requestQueuePool.shutdown();
            requestQueuePoolThreads.clear();
            SlimefunPlugin.getCfg().getConfiguration().addDefault("cargonet.thread-pool.request-queue", 4);
            int threadCount = SlimefunPlugin.getCfg().getInt("cargonet.thread-pool.request-queue");
            requestQueuePool = Executors.newFixedThreadPool(threadCount, new ThreadFactory() {
                private int threadCount = 0;

                @Override
                public synchronized Thread newThread(@NotNull Runnable runnable) {
                    Thread thread = new Thread(runnable, "Slimefun Async CargoNet Post Actions-" + threadCount++);
                    requestQueuePoolThreads.add(thread);
                    return thread;
                }
            });
            {
                Set<Callable<Object>> runnables = Sets.newConcurrentHashSet();
                for (CargoNet instance : instances.values())
                    runnables.add(() -> ItemPostTransport.runNewTask(instance));
                try {
                    requestQueuePool.invokeAll(runnables);
                } catch (InterruptedException ignored) {
                }
            }
        }

    }

    public static void shutdownPool() {
        if (executePool != null)
            executePool.shutdown();

        if (tickingPool != null)
            tickingPool.shutdown();

        while ((executePool != null && !executePool.isTerminated()) || (tickingPool != null && !tickingPool.isTerminated())) {
            try {
                FutureTask<?> task = Slimefun.FUTURE_TASKS.poll(1, TimeUnit.SECONDS);
                if (task == null) continue;
                task.run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (requestQueuePool != null)
            requestQueuePool.shutdown();
    }

    public void tick(Block b) {
        if (!regulator.equals(b.getLocation())) {
            SimpleHologram.update(b, "&4Multiple Cargo Regulators connected");
            return;
        }

        super.tick();

        if (connectorNodes.isEmpty() && terminusNodes.isEmpty()) {
            SimpleHologram.update(b, "&cNo Cargo Nodes found");
        } else {
            SimpleHologram.update(b, "&7Status: &a&lONLINE");

            if (lastFuture != null)
                try {
                    try {
                        lastFuture.get(1, TimeUnit.MILLISECONDS);
                    } catch (TimeoutException e) {
                        return;
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            lastFuture = executePool.submit(() -> {
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
			
			Slimefun.runSync(() -> {

                // Code exported from runSync() - start
                try {
                    if (BlockStorage.getLocationInfo(b.getLocation(), "visualizer") == null) {
                        tickingPool.execute(this::display);
                    }

                    Set<Future<?>> futures = Sets.newConcurrentHashSet();

                    //Chest Terminal Code
                    if (extraChannels) {
                        for (Location bus : imports)
                            futures.add(tickingPool.submit(() -> {
                                try {
                                    BlockMenu menu = BlockStorage.getInventory(bus);
                                    if (menu == null) return;

                                    runBlockWithLock(
                                            getAttachedBlock(bus.getBlock()),
                                            target -> {
                                                try {
                                                    ItemStack item17 = menu.getItemInSlot(17);
                                                    if (item17 == null) { // TODO ItemAndInt stack = CargoUtils.withdraw(bus.getBlock(), target, -1);
                                                        ItemSlot stack = CargoManager.withdraw(bus.getBlock(), target, -1);

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

                                    runBlockWithLock(getAttachedBlock(bus.getBlock()), target -> {
                                        try {
                                            ItemStack item17 = menu.getItemInSlot(17);
                                            if (item17 != null) { // TODO CargoUtils
                                                menu.replaceExistingItem(
                                                        17,
                                                        CargoManager.insert(bus.getBlock(), target, menu.getItemInSlot(17), -1));
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

                                    Block inputTargetA = getAttachedBlock(input.getBlock());
                                    if (inputTargetA == null) return;

                                    SynchronizedLock<Block> lock = getLock(inputTargetA);

                                    try {
                                        AtomicReference<ItemStack> stack = new AtomicReference<>(null);
                                        AtomicInteger previousSlot = new AtomicInteger(-1);

                                        @SuppressWarnings("deprecation")
                                        Config cfg = BlockStorage.getLocationInfo(input);
                                        boolean roundRobin = "true".equals(cfg.getString("round-robin"));

                                        AtomicReference<ItemSlot> slot = new AtomicReference<>();
                                        runBlockWithLock(lock, inputTargetA, inputTarget -> { // TODO CargoUtils
                                                    slot.set(CargoManager.withdraw(input.getBlock(), inputTarget, Integer.parseInt(cfg.getString("index"))));
                                                    if (slot.get() != null) {
                                                        stack.set(slot.get().getItem());
                                                        previousSlot.set(slot.get().getSlot());
                                                    }
                                                }
                                        );

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
                                                        runBlockWithLock(getAttachedBlock(out.getBlock()), target -> {
                                                            if (target != null) { // TODO CargoUtils
                                                                stack.set(CargoManager.insert(out.getBlock(), target, stack.get(), -1));
                                                                if (stack.get() == null)
                                                                    throw new RuntimeException("break");
                                                            }
                                                        });
                                                    } catch (Exception e) {
                                                        if (e.getMessage().equals("break")) break;
                                                        else throw new RuntimeException(e);
                                                    }
                                                }
                                            }
                                        }

                                        runBlockWithLock(lock, inputTargetA, inputTarget -> {
                                            try {
                                                if (stack.get() != null && previousSlot.get() > -1) {
                                                    DirtyChestMenu menu = Slimefun.runSyncFuture(() -> CargoManager.getChestMenu(inputTarget)).get();

                                                    if (menu != null) {
                                                        int finalPreviousSlot = previousSlot.get();
                                                        ItemStack finalStack = stack.get();
                                                        Slimefun.runSyncFuture(() -> menu.replaceExistingItem(finalPreviousSlot, finalStack)).get();
                                                    } else {
                                                        BlockState state;
                                                        try {
                                                            state = Slimefun.runSyncFuture(inputTarget::getState).get();
                                                        } catch (Exception e) {
                                                            throw new RuntimeException(e);
                                                        }
                                                        if (state instanceof InventoryHolder) {
                                                            Inventory inv = Slimefun.runSyncFuture(((InventoryHolder) state)::getInventory).get();
                                                            int finalPreviousSlot1 = previousSlot.get();
                                                            ItemStack finalStack1 = stack.get();
                                                            Slimefun.runSyncFuture(() -> inv.setItem(finalPreviousSlot1, finalStack1)).get();
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


                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }));
                        }
                    }

                    //Chest Terminal Code
                    if (extraChannels) {
                        Set<ItemAndInt> items = new ConcurrentSkipListSet<>(Comparator.comparingInt(item -> -item.getAmount()));

                        {
                            for (Location l : providers) {
                                futures.add(tickingPool.submit(() -> {
                                    try {
                                        runBlockWithLock(getAttachedBlock(l.getBlock()), target -> {
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

                                                            // TODO CargoUtils
                                                            if (is != null && CargoManager.matchesFilter(l.getBlock(), is, -1)) {
                                                                boolean add = true;

                                                                for (ItemAndInt item : items) {
                                                                    if (SlimefunManager.isItemSimilar(is, item.getItem(), true)) {
                                                                        add = false;
                                                                        item.add(is.getAmount() + stored);
                                                                    }
                                                                }

                                                                if (add) {
                                                                    items.add(new ItemAndInt(new CustomItem(is, 1), is.getAmount() + stored));
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        handleWithdraw(blockMenu, items, l);
                                                    }
                                                } else {
                                                    BlockState state;
                                                    try {
                                                        state = Slimefun.runSyncFuture(target::getState).get();
                                                    } catch (Exception e) {
                                                        throw new RuntimeException(e);
                                                    }

                                                    if (state instanceof InventoryHolder) {
                                                        Inventory inv = Slimefun.runSyncFuture(((InventoryHolder) state)::getInventory).get();

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
                                        int page = Integer.parseInt(BlockStorage.getLocationInfo(l, "page"));

                                        if (!items.isEmpty() && items.size() < (page - 1) * terminal_slots.length + 1) {
                                            page = 1;
                                            BlockStorage.addBlockInfo(l, "page", String.valueOf(1));
                                        }

						for (int i = 0; i < terminal_slots.length; i++) {
							int slot = terminal_slots[i];
							
							if (items.size() > i + (terminal_slots.length * (page - 1))) {
								ItemAndInt item = items.get(i + (terminal_slots.length * (page - 1)));
                                        for (int i = 0; i < terminal_slots.length; i++) {
                                            int slot = terminal_slots[i];

                                            if (items.size() > i + (terminal_slots.length * (page - 1))) {
                                                StoredItem item = Iterables.get(items, i + (terminal_slots.length * (page - 1)));

                                                ItemStack stack = item.getItem().clone();
                                                ItemMeta im = stack.getItemMeta();
                                                if (im == null) continue;
                                                List<String> lore = new ArrayList<>();
                                                lore.add("");
                                                lore.add(ChatColors.color("&7Stored Items: &r" + DoubleHandler.getFancyDouble(item.getAmount())));

                                                if (stack.getMaxStackSize() > 1)
                                                    lore.add(ChatColors.color("&7<Left Click: Request 1 | Right Click: Request " + (Math.min(item.getAmount(), stack.getMaxStackSize())) + ">"));
                                                else lore.add(ChatColors.color("&7<Left Click: Request 1>"));

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
                                                Slimefun.runSyncFuture(() -> menu.replaceExistingItem(slot, terminal_noItem_item)).get();
                                                menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());
                                            }
                                        }
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }));
                            }
                        }
                    }
                    for (Future<?> future : futures)
                        future.get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                // Code exported from runSync() - end
            });
        }

    }

    static void runBlockWithLock(Block block, Consumer<Block> consumer)
            throws ExecutionException, InterruptedException {
        getLock(block).run(consumer, block);
    }

    static void runBlockWithLock(SynchronizedLock<Block> lock, Block block, Consumer<Block> consumer) {
        lock.run(consumer, block);
    }

    static SynchronizedLock<Block> getLock(Block block) throws ExecutionException, InterruptedException {
        SynchronizedLock<Block> currentLock = new SynchronizedLock<>();
        BlockState state = Slimefun.runSyncFuture(block::getState).get();
        if (state instanceof Container) {
            Inventory inventory;
            try {
                inventory = Slimefun.runSyncFuture(((Container) state)::getInventory).get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            InventoryHolder holder = Slimefun.runSyncFuture(inventory::getHolder).get();
            if (holder instanceof DoubleChest) {
                DoubleChest doubleChest = (DoubleChest) holder;
                Location leftLocation = Slimefun.runSyncFuture(() ->
                        ((Chest) Objects.requireNonNull(doubleChest.getLeftSide())).getLocation()).get();
                Location rightLocation = Slimefun.runSyncFuture(() ->
                        ((Chest) Objects.requireNonNull(doubleChest.getRightSide())).getLocation()).get();
                SynchronizedLock<Block> leftOriginalLock = CargoNet.locks.get(leftLocation);
                SynchronizedLock<Block> rightOriginalLock = CargoNet.locks.get(rightLocation);
                if (leftOriginalLock == null || !Objects.equals(leftOriginalLock, rightOriginalLock)) {
                    // Slimefun.getLogger().info("created double chest");
                    CargoNet.locks.put(leftLocation, currentLock);
                    CargoNet.locks.put(rightLocation, currentLock);
                } else {
                    // Slimefun.getLogger().info("reused double chest");
                    currentLock = leftOriginalLock;
                }
            } else {
                Location blockLocation = block.getLocation();
                SynchronizedLock<Block> originalLock = CargoNet.locks.get(blockLocation);
                if (originalLock == null) {
                    // Slimefun.getLogger().info("created non double chest");
                    CargoNet.locks.put(blockLocation, currentLock);
                } else {
                    // Slimefun.getLogger().info("reused non double chest");
                    currentLock = originalLock;
                }
            }
        } else {
            Location blockLocation = block.getLocation();
            SynchronizedLock<Block> originalLock = CargoNet.locks.get(blockLocation);
            if (originalLock == null) {
                // Slimefun.getLogger().info("created block");
                CargoNet.locks.put(blockLocation, currentLock);
            } else {
                // Slimefun.getLogger().info("reused block");
                currentLock = originalLock;
            }
        }
        return currentLock;
    }

    static Block getAttachedBlock(Block block) throws ExecutionException, InterruptedException {
        if (Slimefun.runSyncFuture(block::getBlockData).get() instanceof Directional) {
            return Slimefun.runSyncFuture(() -> block.getRelative(((Directional) block.getBlockData()).getFacing().getOppositeFace())).get();
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

    private void handleWithdraw(DirtyChestMenu menu, Set<ItemAndInt> items, Location l) {
        for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.WITHDRAW, null)) {
            try {
                filter(menu.getItemInSlot(slot), items, l);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void filter(ItemStack is, Set<ItemAndInt> items, Location l) {
        if (is != null && CargoUtils.matchesFilter(l.getBlock(), is, -1)) {
            boolean add = true;

            for (ItemAndInt item : items) {
                if (SlimefunManager.isItemSimilar(is, item.getItem(), true)) {
                    add = false;
                    item.add(is.getAmount());
                }
            }

            if (add) {
                items.add(new ItemAndInt(new CustomItem(is, 1), is.getAmount()));
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
}
