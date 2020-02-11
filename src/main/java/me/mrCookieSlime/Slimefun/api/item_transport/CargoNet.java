package me.mrCookieSlime.Slimefun.api.item_transport;

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
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Directional;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;

public class CargoNet extends Network {

    public static boolean extraChannels = false;

    private static final int RANGE = 5;

    private static final int[] slots = new int[]{19, 20, 21, 28, 29, 30, 37, 38, 39};

    // Chest Terminal Stuff
    public static final int[] terminal_slots = new int[]{0, 1, 2, 3, 4, 5, 6, 9, 10, 11, 12, 13, 14, 15, 18, 19, 20, 21, 22, 23, 24, 27, 28, 29, 30, 31, 32, 33, 36, 37, 38, 39, 40, 41, 42};
    public static final int TERMINAL_OUT_SLOT = 17;

    private static final ItemStack terminal_noitem_item = new CustomItem(new ItemStack(Material.BARRIER), "&4No Item cached");
    private static final MenuClickHandler terminal_noitem_handler = (p, slot, item, action) -> false;

    private Set<Location> inputNodes = new HashSet<>();
    private Set<Location> outputNodes = new HashSet<>();

    // Chest Terminal Stuff
    private final Set<Location> terminals = new HashSet<>();
    private final Set<Location> imports = new HashSet<>();
    private final Set<Location> exports = new HashSet<>();

    private final Map<Location, Integer> roundRobin = new HashMap<>();
    private final Set<ItemRequest> itemRequests = new HashSet<>();

    private final ExecutorService pool = Executors.newFixedThreadPool(4, new ThreadFactory() {
        private int threadCount = 0;

        @Override
        public synchronized Thread newThread(@NotNull Runnable runnable) {
            return new Thread(runnable, "Slimefun Async CargoNet-" + threadCount++);
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
            Map<Integer, List<Location>> output = new HashMap<>();

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
            Set<Location> providers = new HashSet<>();
            Set<Location> destinations = new HashSet<>();

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
                    display();
                }

                //Chest Terminal Code
                if (extraChannels) {
                    for (Location bus : imports) {
                        BlockMenu menu = Slimefun.runSyncCallable(() -> BlockStorage.getInventory(bus)).get();
                        ItemStack item17 = Slimefun.runSyncCallable(() -> menu.getItemInSlot(17)).get();

                        if (item17 == null) {
                            Block target = Slimefun.runSyncCallable(() -> getAttachedBlock(bus.getBlock())).get();
                            ItemSlot stack = CargoManager.withdraw(bus.getBlock(), target, -1);

                            if (stack != null) {
                                Slimefun.runSync(() -> menu.replaceExistingItem(17, stack.getItem()));
                            }
                        }

                        if (item17 != null) {
                            itemRequests.add(new ItemRequest(bus, 17, item17, ItemTransportFlow.INSERT));
                        }
                    }
                }

                for (Location bus : exports) {
                    BlockMenu menu = Slimefun.runSyncCallable(() -> BlockStorage.getInventory(bus)).get();
                    ItemStack item17 = Slimefun.runSyncCallable(() -> menu.getItemInSlot(17)).get();

                    if (item17 != null) {
                        Block target = Slimefun.runSyncCallable(() -> getAttachedBlock(bus.getBlock())).get();

                        Slimefun.runSync(() -> menu.replaceExistingItem(
                                17,
                                CargoManager.insert(bus.getBlock(), target, menu.getItemInSlot(17), -1)
                        ));
                    }

                    if (item17 == null) {
                        List<ItemStack> items = new ArrayList<>();
                        for (int slot : slots) {
                            ItemStack template = Slimefun.runSyncCallable(() -> menu.getItemInSlot(slot)).get();
                            if (template != null) items.add(new CustomItem(template, 1));
                        }

                        if (!items.isEmpty()) {
                            int index = Integer.parseInt(BlockStorage.getLocationInfo(bus, "index"));

                            index++;
                            if (index > (items.size() - 1)) index = 0;

                            int finalIndex = index;
                            Slimefun.runSync(() -> BlockStorage.addBlockInfo(bus, "index", String.valueOf(finalIndex)));
                            itemRequests.add(new ItemRequest(bus, 17, items.get(index), ItemTransportFlow.WITHDRAW));
                        }
                    }
                }

                for (Location terminal : terminals) {
                    BlockMenu menu = Slimefun.runSyncCallable(() -> BlockStorage.getInventory(terminal)).get();
                    if (menu == null) continue;
                    ItemStack sendingItem = Slimefun.runSyncCallable(() -> menu.getItemInSlot(TERMINAL_OUT_SLOT)).get();

                    if (sendingItem != null) {
                        itemRequests.add(new ItemRequest(terminal, TERMINAL_OUT_SLOT, sendingItem, ItemTransportFlow.INSERT));
                    }
                }

                Iterator<ItemRequest> iterator = itemRequests.iterator();
                while (iterator.hasNext()) {
                    ItemRequest request = iterator.next();

                    if (terminals.contains(request.getTerminal()) || imports.contains(request.getTerminal()) || exports.contains(request.getTerminal())) {
                        BlockMenu menu = Slimefun.runSyncCallable(() -> BlockStorage.getInventory(request.getTerminal())).get();
                        if (menu == null) continue;

                        switch (request.getDirection()) {
                            case INSERT:
                                ItemStack requestedItem = request.getItem();

                                for (Location l : destinations) {
                                    Block target = Slimefun.runSyncCallable(() -> getAttachedBlock(l.getBlock())).get();
                                    requestedItem = CargoManager.insert(l.getBlock(), target, requestedItem, -1);

                                    if (requestedItem == null) {
                                        Slimefun.runSync(() -> menu.replaceExistingItem(request.getSlot(), null));
                                        break;
                                    }
                                }

                                if (requestedItem != null) {
                                    ItemStack finalRequestedItem = requestedItem;
                                    Slimefun.runSync(() -> menu.replaceExistingItem(request.getSlot(), finalRequestedItem));
                                }

                                iterator.remove();
                                break;
                            case WITHDRAW:
                                int slot = Slimefun.runSyncCallable(request::getSlot).get();
                                ItemStack prevStack = Slimefun.runSyncCallable(() -> menu.getItemInSlot(slot)).get();

                                if (!(prevStack == null || (prevStack.getAmount() + request.getItem().getAmount() <= prevStack.getMaxStackSize() && SlimefunManager.isItemSimilar(prevStack, new CustomItem(request.getItem(), 1), true)))) {
                                    iterator.remove();
                                    break;
                                }

                                ItemStack stack = null;
                                ItemStack requested = request.getItem();

                                for (Location l : providers) {
                                    Block target = Slimefun.runSyncCallable(() -> getAttachedBlock(l.getBlock())).get();
                                    ItemStack is = CargoManager.withdraw(l.getBlock(), target, requested);

                                    if (is != null) {
                                        if (stack == null) {
                                            stack = is;
                                        } else {
                                            stack = new CustomItem(stack, stack.getAmount() + is.getAmount());
                                        }

                                        if (is.getAmount() == requested.getAmount()) {
                                            break;
                                        } else {
                                            requested = new CustomItem(requested, requested.getAmount() - is.getAmount());
                                        }
                                    }
                                }

                                if (stack != null) {
                                    ItemStack prev = Slimefun.runSyncCallable(() -> menu.getItemInSlot(slot)).get();

                                    if (prev == null) {
                                        ItemStack finalStack = stack;
                                        Slimefun.runSync(() -> menu.replaceExistingItem(slot, finalStack));
                                    } else {
                                        ItemStack finalStack1 = stack;
                                        Slimefun.runSync(() -> menu.replaceExistingItem(slot, new CustomItem(finalStack1, finalStack1.getAmount() + prev.getAmount())));
                                    }
                                }

                                iterator.remove();
                                break;
                            default:
                                break;
                        }
                    }
                }

                // All operations happen here: Everything gets iterated from the Input Nodes. (Apart from ChestTerminal Buses)
                for (Location input : inputNodes) {
                    int frequency = getFrequency(input);

                    if (frequency < 0 || frequency > 15) {
                        continue;
                    }

                    Block inputTarget = Slimefun.runSyncCallable(() -> getAttachedBlock(input.getBlock())).get();
                    ItemStack stack = null;
                    int previousSlot = -1;

                    @SuppressWarnings("deprecation") Config cfg = BlockStorage.getLocationInfo(input);
                    boolean roundRobin = "true".equals(cfg.getString("round-robin"));

                    if (inputTarget != null) {
                        ItemSlot slot = CargoManager.withdraw(input.getBlock(), inputTarget, Integer.parseInt(cfg.getString("index")));

                        if (slot != null) {
                            stack = slot.getItem();
                            previousSlot = slot.getSlot();
                        }
                    }

                    if (stack != null) {
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
                                Block target = Slimefun.runSyncCallable(() -> getAttachedBlock(out.getBlock())).get();

                                if (target != null) {
                                    stack = CargoManager.insert(out.getBlock(), target, stack, -1);
                                    if (stack == null) break;
                                }
                            }
                        }
                    }

                    if (stack != null && previousSlot > -1) {
                        DirtyChestMenu menu = Slimefun.runSyncCallable(() -> CargoManager.getChestMenu(inputTarget)).get();

                        if (menu != null) {
                            int finalPreviousSlot = previousSlot;
                            ItemStack finalStack = stack;
                            Slimefun.runSync(() -> menu.replaceExistingItem(finalPreviousSlot, finalStack));
                        } else {
                            BlockState state;
                            try {
                                state = Slimefun.runSyncCallable(inputTarget::getState).get();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            if (state instanceof InventoryHolder) {
                                Inventory inv = Slimefun.runSyncCallable(((InventoryHolder) state)::getInventory).get();
                                int finalPreviousSlot1 = previousSlot;
                                ItemStack finalStack1 = stack;
                                Slimefun.runSync(() -> inv.setItem(finalPreviousSlot1, finalStack1));
                            }
                        }
                    }
                }

                //Chest Terminal Code
                if (extraChannels) {
                    List<StoredItem> items = new ArrayList<>();

                    for (Location l : providers) {
                        Block target = Slimefun.runSyncCallable(() -> getAttachedBlock(l.getBlock())).get();
                        UniversalBlockMenu menu = Slimefun.runSyncCallable(() -> BlockStorage.getUniversalInventory(target)).get();

                        if (menu != null) {
                            for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.WITHDRAW, null)) {
                                ItemStack is = Slimefun.runSyncCallable(() -> menu.getItemInSlot(slot)).get();
                                filter(is, items, l);
                            }
                        } else if (BlockStorage.hasInventory(target)) {
                            BlockMenu blockMenu = Slimefun.runSyncCallable(() -> BlockStorage.getInventory(target)).get();
                            @SuppressWarnings("deprecation") Config cfg = BlockStorage.getLocationInfo(target.getLocation());

                            if (cfg.getString("id").startsWith("BARREL_") && cfg.getString("storedItems") != null) {
                                int stored = Integer.parseInt(cfg.getString("storedItems"));

                                for (int slot : blockMenu.getPreset().getSlotsAccessedByItemTransport(blockMenu, ItemTransportFlow.WITHDRAW, null)) {
                                    ItemStack is = Slimefun.runSyncCallable(() -> blockMenu.getItemInSlot(slot)).get();

                                    if (is != null && CargoManager.matchesFilter(l.getBlock(), is, -1)) {
                                        boolean add = true;

                                        for (StoredItem item : items) {
                                            if (SlimefunManager.isItemSimilar(is, item.getItem(), true)) {
                                                add = false;
                                                Slimefun.runSync(() -> item.add(is.getAmount() + stored));
                                            }
                                        }

                                        if (add) {
                                            Slimefun.runSync(() -> items.add(new StoredItem(new CustomItem(is, 1), is.getAmount() + stored)));
                                        }
                                    }
                                }
                            } else {
                                handleWithdraw(blockMenu, items, l);
                            }
                        } else {
                            BlockState state;
                            try {
                                state = Slimefun.runSyncCallable(target::getState).get();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                            if (state instanceof InventoryHolder) {
                                Inventory inv = Slimefun.runSyncCallable(((InventoryHolder) state)::getInventory).get();

                                for (ItemStack is : inv.getContents()) {
                                    filter(is, items, l);
                                }
                            }
                        }
                    }

                    items.sort(Comparator.comparingInt(item -> -item.getAmount()));

                    for (Location l : terminals) {
                        BlockMenu menu = Slimefun.runSyncCallable(() -> BlockStorage.getInventory(l)).get();
                        int page = Integer.parseInt(BlockStorage.getLocationInfo(l, "page"));

                        if (!items.isEmpty() && items.size() < (page - 1) * terminal_slots.length + 1) {
                            page = 1;
                            BlockStorage.addBlockInfo(l, "page", String.valueOf(1));
                        }

                        for (int i = 0; i < terminal_slots.length; i++) {
                            int slot = terminal_slots[i];

                            if (items.size() > i + (terminal_slots.length * (page - 1))) {
                                StoredItem item = items.get(i + (terminal_slots.length * (page - 1)));

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
                                Slimefun.runSync(() -> menu.replaceExistingItem(slot, terminal_noitem_item));
                                Slimefun.runSync(() -> menu.addMenuClickHandler(slot, terminal_noitem_handler));
                            }
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Code exported from runSync() - end
        }

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

    private void handleWithdraw(DirtyChestMenu menu, List<StoredItem> items, Location l) {
        for (int slot : menu.getPreset().getSlotsAccessedByItemTransport(menu, ItemTransportFlow.WITHDRAW, null)) {
            try {
                filter(Slimefun.runSyncCallable(() -> menu.getItemInSlot(slot)).get(), items, l);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void filter(ItemStack is, List<StoredItem> items, Location l) {
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
