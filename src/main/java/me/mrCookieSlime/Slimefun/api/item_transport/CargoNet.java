package me.mrCookieSlime.Slimefun.api.item_transport;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import io.github.thebusybiscuit.cscorelib2.chat.ChatColors;
import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import io.github.thebusybiscuit.cscorelib2.math.DoubleHandler;
import io.github.thebusybiscuit.slimefun4.api.network.Network;
import io.github.thebusybiscuit.slimefun4.api.network.NetworkComponent;
import io.github.thebusybiscuit.slimefun4.utils.holograms.SimpleHologram;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.MenuClickHandler;
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
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.logging.Level;

public class CargoNet extends Network {

    public static boolean extraChannels = false;

    private static final int RANGE = 5;

    private static final int[] slots = new int[]{19, 20, 21, 28, 29, 30, 37, 38, 39};

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
    private final Set<Location> terminals = Sets.newConcurrentHashSet();
    private final Set<Location> imports = Sets.newConcurrentHashSet();
    private final Set<Location> exports = Sets.newConcurrentHashSet();

    private final ConcurrentMap<Location, Integer> roundRobin = new ConcurrentHashMap<>();
    private final ConcurrentLinkedQueue<ItemRequest> itemRequests = new ConcurrentLinkedQueue<>();

    private static final Map<Location, SynchronizedLock<Block>> locks = new ConcurrentHashMap<>();

    private Future<?> lastFuture = null;

    private static final ExecutorService pool = Executors.newFixedThreadPool(4, new ThreadFactory() {
        private int threadCount = 0;

        @Override
        public synchronized Thread newThread(@NotNull Runnable runnable) {
            return new Thread(runnable, "Slimefun Async CargoNet-" + threadCount++);
        }
    });

    private static final ExecutorService executePool = Executors.newCachedThreadPool(new ThreadFactory() {
        private int threadCount = 0;

        @Override
        public Thread newThread(@NotNull Runnable runnable) {
            return new Thread(runnable, "Slimefun Async CargoNet Executor-" + threadCount++);
        }
    });

    public static CargoNet getNetworkFromLocation(Location l) {
        return SlimefunPlugin.getNetworkManager().getNetworkFromLocation(l, CargoNet.class);
    }

    public static CargoNet getNetworkFromLocationOrCreate(Location l) {
        CargoNet cargoNetwork = getNetworkFromLocation(l);

        if (cargoNetwork == null) {
            cargoNetwork = new CargoNet(l);
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
                    lastFuture.get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    lastFuture = null;
                }

            this.lastFuture = executePool.submit(() -> {
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

                        list = Collections.synchronizedList(new LinkedList<>());
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

                // Code exported from runSync() - start
                try {
                    if (BlockStorage.getLocationInfo(b.getLocation(), "visualizer") == null) {
                        pool.execute(this::display);
                    }

                    //Chest Terminal Code
                    if (extraChannels) {
                        Set<Future<?>> futures = new HashSet<>();
                        for (Location bus : imports)
                            futures.add(pool.submit(() -> {
                                try {
                                    BlockMenu menu = Slimefun.runSyncFuture(() -> BlockStorage.getInventory(bus)).get();

                                    runBlockWithLock(locks,
                                            Slimefun.runSyncFuture(() ->
                                                    Objects.requireNonNull(getAttachedBlock(bus.getBlock()))).get(),
                                            target -> {
                                                try {
                                                    ItemStack item17 = Slimefun.runSyncFuture(() -> menu.getItemInSlot(17)).get();
                                                    if (item17 == null) {
                                                        ItemSlot stack = CargoManager.withdraw(bus.getBlock(), target, -1);

                                                        if (stack != null) {
                                                            Slimefun.runSyncFuture(() -> menu.replaceExistingItem(17, stack.getItem())).get();
                                                        }
                                                    }

                                                    if (item17 != null) {
                                                        itemRequests.add(new ItemRequest(bus, 17, item17, ItemTransportFlow.INSERT));
                                                    }
                                                } catch (Exception e) {
                                                    throw new RuntimeException(e);
                                                }
                                            });
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }));
                        for (Future<?> future : futures)
                            future.get();
                    }

                    {
                        Set<Future<?>> futures = new HashSet<>();
                        for (Location bus : exports) {
                            futures.add(pool.submit(() -> {
                                try {
                                    BlockMenu menu = Slimefun.runSyncFuture(() -> BlockStorage.getInventory(bus)).get();

                                    runBlockWithLock(locks, Slimefun.runSyncFuture(() -> getAttachedBlock(bus.getBlock())).get(), target -> {
                                        try {
                                            ItemStack item17 = Slimefun.runSyncFuture(() -> menu.getItemInSlot(17)).get();
                                            if (item17 != null) {
                                                Slimefun.runSyncFuture(() -> menu.replaceExistingItem(
                                                        17,
                                                        CargoManager.insert(bus.getBlock(), target, menu.getItemInSlot(17), -1)
                                                )).get();
                                            }

                                            if (item17 == null) {
                                                List<ItemStack> items = new ArrayList<>();
                                                for (int slot : slots) {
                                                    ItemStack template = Slimefun.runSyncFuture(() -> menu.getItemInSlot(slot)).get();
                                                    if (template != null) items.add(new CustomItem(template, 1));
                                                }

                                                if (!items.isEmpty()) {
                                                    int index = Integer.parseInt(BlockStorage.getLocationInfo(bus, "index"));

                                                    index++;
                                                    if (index > (items.size() - 1)) index = 0;

                                                    int finalIndex = index;
                                                    Slimefun.runSyncFuture(() -> BlockStorage.addBlockInfo(bus, "index", String.valueOf(finalIndex))).get();
                                                    itemRequests.add(new ItemRequest(bus, 17, items.get(index), ItemTransportFlow.WITHDRAW));
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
                        for (Future<?> future : futures)
                            future.get();
                    }

                    {
                        Set<Future<?>> futures = new HashSet<>();
                        for (Location terminal : terminals) {
                            futures.add(pool.submit(() -> {
                                try {
                                    BlockMenu menu = Slimefun.runSyncFuture(() -> BlockStorage.getInventory(terminal)).get();
                                    if (menu == null) return;
                                    ItemStack sendingItem = Slimefun.runSyncFuture(() -> menu.getItemInSlot(TERMINAL_OUT_SLOT)).get();

                                    if (sendingItem != null) {
                                        itemRequests.add(
                                                new ItemRequest(terminal, TERMINAL_OUT_SLOT, sendingItem, ItemTransportFlow.INSERT));
                                    }
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }));
                        }
                        for (Future<?> future : futures)
                            future.get();
                    }

                    {
                        Set<Future<?>> futures = new HashSet<>();
                        while (true) {
                            ItemRequest request = itemRequests.poll();
                            if (request == null) break;

                            futures.add(pool.submit(() -> {
                                try {
                                    if (terminals.contains(request.getTerminal())
                                            || imports.contains(request.getTerminal())
                                            || exports.contains(request.getTerminal())) {
                                        BlockMenu menu = Slimefun.runSyncFuture(() ->
                                                BlockStorage.getInventory(request.getTerminal())).get();
                                        if (menu == null) return;

                                        switch (request.getDirection()) {
                                            case INSERT:
                                                AtomicReference<ItemStack> requestedItem = new AtomicReference<>(request.getItem());

                                                for (Location l : destinations) {
                                                    try {
                                                        runBlockWithLock(locks, Slimefun.runSyncFuture(() ->
                                                                getAttachedBlock(l.getBlock())).get(), target -> {
                                                            requestedItem.set(CargoManager.insert(
                                                                    l.getBlock(), target, requestedItem.get(), -1));

                                                            if (requestedItem.get() == null) {
                                                                try {
                                                                    Slimefun.runSyncFuture(() ->
                                                                            menu.replaceExistingItem(request.getSlot(), null)).get();
                                                                } catch (InterruptedException | ExecutionException e) {
                                                                    throw new RuntimeException(e);
                                                                }
                                                                throw new RuntimeException("break");
                                                            }
                                                        });
                                                    } catch (Exception e) {
                                                        if (e.getMessage().equals("break"))
                                                            break;
                                                        else throw new RuntimeException(e);
                                                    }

                                                }

                                                if (requestedItem.get() != null) {
                                                    ItemStack finalRequestedItem = requestedItem.get();
                                                    Slimefun.runSyncFuture(() ->
                                                            menu.replaceExistingItem(request.getSlot(), finalRequestedItem)).get();
                                                }

                                                break;
                                            case WITHDRAW:
                                                int slot = Slimefun.runSyncFuture(request::getSlot).get();
                                                ItemStack prevStack = Slimefun.runSyncFuture(() ->
                                                        menu.getItemInSlot(slot)).get();

                                                if (
                                                        !(prevStack == null
                                                                || (
                                                                prevStack.getAmount() + request.getItem().getAmount()
                                                                        <= prevStack.getMaxStackSize()
                                                                        && SlimefunManager.isItemSimilar(
                                                                        prevStack,
                                                                        new CustomItem(request.getItem(), 1),
                                                                        true)
                                                        ))) {
                                                    break;
                                                }

                                                final ItemStack[] stack = {null};
                                                final ItemStack[] requested = {request.getItem()};

                                                for (Location l : providers) {

                                                    try {
                                                        runBlockWithLock(locks, Slimefun.runSyncFuture(() ->
                                                                getAttachedBlock(l.getBlock())).get(), target -> {
                                                            ItemStack is = CargoManager.withdraw(l.getBlock(), target, requested[0]);

                                                            if (is != null) {
                                                                if (stack[0] == null) {
                                                                    stack[0] = is;
                                                                } else {
                                                                    stack[0] = new CustomItem(
                                                                            stack[0], stack[0].getAmount() + is.getAmount());
                                                                }

                                                                if (is.getAmount() == requested[0].getAmount()) {
                                                                    throw new RuntimeException("break");
                                                                } else {
                                                                    requested[0] = new CustomItem(
                                                                            requested[0],
                                                                            requested[0].getAmount() - is.getAmount());
                                                                }
                                                            }
                                                        });
                                                    } catch (Exception e) {
                                                        if (e.getMessage().equals("break")) break;
                                                        else throw new RuntimeException(e);
                                                    }

                                                }

                                                if (stack[0] != null) {
                                                    ItemStack prev = Slimefun.runSyncFuture(() ->
                                                            menu.getItemInSlot(slot)).get();

                                                    ItemStack finalStack = stack[0];
                                                    if (prev == null) {
                                                        Slimefun.runSyncFuture(() -> menu.replaceExistingItem(slot, finalStack)).get();
                                                    } else {
                                                        Slimefun.runSyncFuture(() ->
                                                                menu.replaceExistingItem(
                                                                        slot, new CustomItem(
                                                                                finalStack,
                                                                                finalStack.getAmount()
                                                                                        + prev.getAmount()
                                                                        )
                                                                )
                                                        ).get();
                                                    }
                                                }

                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }));
                            for (Future<?> future : futures)
                                future.get();
                        }
                    }

                    // All operations happen here: Everything gets iterated from the Input Nodes. (Apart from ChestTerminal Buses)
                    {
                        Set<Future<?>> futures = new HashSet<>();
                        for (Location input : inputNodes) {
                            futures.add(pool.submit(() -> {
                                try {
                                    int frequency = getFrequency(input);

                                    if (frequency < 0 || frequency > 15) {
                                        return;
                                    }

                                    Block inputTargetA = Slimefun.runSyncFuture(() -> getAttachedBlock(input.getBlock())).get();
                                    if (inputTargetA == null) return;

                                    runBlockWithLock(locks, inputTargetA, inputTarget -> {
                                        try {
                                            AtomicReference<ItemStack> stack = new AtomicReference<>(null);
                                            int previousSlot = -1;

                                            @SuppressWarnings("deprecation")
                                            Config cfg = BlockStorage.getLocationInfo(input);
                                            boolean roundRobin = "true".equals(cfg.getString("round-robin"));

                                            ItemSlot slot = CargoManager.withdraw(input.getBlock(), inputTarget, Integer.parseInt(cfg.getString("index")));

                                            if (slot != null) {
                                                stack.set(slot.getItem());
                                                previousSlot = slot.getSlot();
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
                                                            runBlockWithLock(locks, Slimefun.runSyncFuture(() -> getAttachedBlock(out.getBlock())).get(), target -> {
                                                                if (target != null) {
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

                                            if (stack.get() != null && previousSlot > -1) {
                                                DirtyChestMenu menu = Slimefun.runSyncFuture(() -> CargoManager.getChestMenu(inputTarget)).get();

                                                if (menu != null) {
                                                    int finalPreviousSlot = previousSlot;
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
                                                        int finalPreviousSlot1 = previousSlot;
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
                            }));
                        }
                        for (Future<?> future : futures)
                            future.get();
                    }

                    //Chest Terminal Code
                    if (extraChannels) {
                        Set<StoredItem> items = new ConcurrentSkipListSet<>(Comparator.comparingInt(item -> -item.getAmount()));

                        {
                            Set<Future<?>> futures = new HashSet<>();
                            for (Location l : providers) {
                                futures.add(pool.submit(() -> {
                                    try {
                                        runBlockWithLock(locks, Slimefun.runSyncFuture(() -> getAttachedBlock(l.getBlock())).get(), target -> {
                                            try {
                                                UniversalBlockMenu menu = Slimefun.runSyncFuture(() -> BlockStorage.getUniversalInventory(target)).get();

                                                if (menu != null) {
                                                    for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.WITHDRAW, null)) {
                                                        ItemStack is = Slimefun.runSyncFuture(() -> menu.getItemInSlot(slot)).get();
                                                        filter(is, items, l);
                                                    }
                                                } else if (BlockStorage.hasInventory(target)) {
                                                    BlockMenu blockMenu = Slimefun.runSyncFuture(() -> BlockStorage.getInventory(target)).get();
                                                    @SuppressWarnings("deprecation")
                                                    Config cfg = BlockStorage.getLocationInfo(target.getLocation());

                                                    if (cfg.getString("id").startsWith("BARREL_") && cfg.getString("storedItems") != null) {
                                                        int stored = Integer.parseInt(cfg.getString("storedItems"));

                                                        for (int slot : blockMenu.getPreset().getSlotsAccessedByItemTransport(blockMenu, ItemTransportFlow.WITHDRAW, null)) {
                                                            ItemStack is = Slimefun.runSyncFuture(() -> blockMenu.getItemInSlot(slot)).get();

                                                            if (is != null && CargoManager.matchesFilter(l.getBlock(), is, -1)) {
                                                                boolean add = true;

                                                                for (StoredItem item : items) {
                                                                    if (SlimefunManager.isItemSimilar(is, item.getItem(), true)) {
                                                                        add = false;
                                                                        Slimefun.runSyncFuture(() -> item.add(is.getAmount() + stored)).get();
                                                                    }
                                                                }

                                                                if (add) {
                                                                    Slimefun.runSyncFuture(() -> items.add(new StoredItem(new CustomItem(is, 1), is.getAmount() + stored))).get();
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
                            for (Future<?> future : futures)
                                future.get();
                        }


                        {
                            Set<Future<?>> futures = new HashSet<>();
                            for (Location l : terminals) {
                                futures.add(pool.submit(() -> {
                                    try {
                                        BlockMenu menu = Slimefun.runSyncFuture(() -> BlockStorage.getInventory(l)).get();
                                        int page = Integer.parseInt(BlockStorage.getLocationInfo(l, "page"));

                                        if (!items.isEmpty() && items.size() < (page - 1) * terminal_slots.length + 1) {
                                            page = 1;
                                            BlockStorage.addBlockInfo(l, "page", String.valueOf(1));
                                        }

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
                                                    int amount = Math.min(item.getAmount(), item.getItem().getMaxStackSize());
                                                    itemRequests.add(new ItemRequest(l, 44, new CustomItem(item.getItem(), action.isRightClicked() ? amount : 1), ItemTransportFlow.WITHDRAW));
                                                    return false;
                                                });

                                            } else {
                                                Slimefun.runSyncFuture(() -> menu.replaceExistingItem(slot, terminal_noItem_item)).get();
                                                Slimefun.runSyncFuture(() -> menu.addMenuClickHandler(slot, terminal_noItem_handler)).get();
                                            }
                                        }
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }));
                                for (Future<?> future : futures)
                                    future.get();
                            }
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                // Code exported from runSync() - end
            });
        }

    }

    private static void runBlockWithLock(Map<Location, SynchronizedLock<Block>> locks, Block block, Consumer<Block> consumer)
            throws ExecutionException, InterruptedException {
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
                SynchronizedLock<Block> leftOriginalLock = locks.get(leftLocation);
                SynchronizedLock<Block> rightOriginalLock = locks.get(rightLocation);
                if (leftOriginalLock == null || !Objects.equals(leftOriginalLock, rightOriginalLock)) {
                    Slimefun.getLogger().info("created double chest");
                    locks.put(leftLocation, currentLock);
                    locks.put(rightLocation, currentLock);
                } else {
                    Slimefun.getLogger().info("reused double chest");
                    currentLock = leftOriginalLock;
                }
            } else {
                Location blockLocation = block.getLocation();
                SynchronizedLock<Block> originalLock = locks.get(blockLocation);
                if (originalLock == null) {
                    Slimefun.getLogger().info("created non double chest");
                    locks.put(blockLocation, currentLock);
                } else {
                    Slimefun.getLogger().info("reused non double chest");
                    currentLock = originalLock;
                }
            }
        } else {
            Location blockLocation = block.getLocation();
            SynchronizedLock<Block> originalLock = locks.get(blockLocation);
            if (originalLock == null) {
                Slimefun.getLogger().info("created block");
                locks.put(blockLocation, currentLock);
            } else {
                Slimefun.getLogger().info("reused block");
                currentLock = originalLock;
            }
        }

        currentLock.run(consumer, block);
    }

    private static Block getAttachedBlock(Block block) {
        if (block.getBlockData() instanceof Directional) {
            return block.getRelative(((Directional) block.getBlockData()).getFacing().getOppositeFace());
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

    private void handleWithdraw(DirtyChestMenu menu, Set<StoredItem> items, Location l) {
        for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.WITHDRAW, null)) {
            try {
                filter(Slimefun.runSyncFuture(() -> menu.getItemInSlot(slot)).get(), items, l);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void filter(ItemStack is, Set<StoredItem> items, Location l) {
        if (is != null && CargoManager.matchesFilter(l.getBlock(), is, -1)) {
            boolean add = true;

            for (StoredItem item : items) {
                if (SlimefunManager.isItemSimilar(is, item.getItem(), true)) {
                    add = false;
                    item.add(is.getAmount());
                }
            }

            if (add) {
                items.add(new StoredItem(new CustomItem(is, 1), is.getAmount()));
            }
        }
    }
}
