package me.mrCookieSlime.Slimefun.api.item_transport;

import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

public class ItemPostTransport {

    static void runTask(ItemRequest request, CargoNet instance) {
        if (instance == null)
            return;
        if (request == null) {
            CargoNet.requestQueuePool.execute(() -> runNewTask(instance));
            return;
        }
        CargoNet.requestQueuePool.execute(() -> {
            try {
                if (instance.terminals.contains(request.getTerminal())
                        || instance.imports.contains(request.getTerminal())
                        || instance.exports.contains(request.getTerminal())) {
                    BlockMenu menu = Slimefun.runSyncFuture(() ->
                            BlockStorage.getInventory(request.getTerminal())).get();
                    if (menu == null) return;

                    switch (request.getDirection()) {
                        case INSERT:
                            AtomicReference<ItemStack> requestedItem = new AtomicReference<>(request.getItem());

                            for (Location l : request.getDestinations()) {
                                try {
                                    CargoNet.runBlockWithLock(CargoNet.getAttachedBlock(l.getBlock()), target -> {
                                        requestedItem.set(CargoUtils.insert(
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

                            for (Location l : request.getProviders()) {

                                try {
                                    CargoNet.runBlockWithLock(CargoNet.getAttachedBlock(l.getBlock()), target -> {
                                        ItemStack is = CargoUtils.withdraw(l.getBlock(), target, requested[0]);

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
                Slimefun.getLogger().log(Level.WARNING, e.getMessage(), e);
            } finally {
                CargoNet.requestQueuePool.execute(() -> runNewTask(instance));
            }
        });
    }

    public static Object runNewTask(CargoNet instance) {
        if (instance == null) return null;
        try {
            runTask(instance.itemRequests.poll(1, TimeUnit.SECONDS), instance);
        } catch (InterruptedException e) {
            return runNewTask(instance);
        }
        return null;
    }
}
