package io.github.thebusybiscuit.slimefun4.utils.holograms;

import io.github.thebusybiscuit.cscorelib2.chat.ChatColors;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.concurrent.CompletableFuture;

public final class SimpleHologram {

    private SimpleHologram() {
    }

    public static void update(Block b, String name) {
        getArmorStand(b, true).thenAccept(hologram ->
                hologram.setCustomName(ChatColors.color(name)));

    }

    public static void remove(Block b) {
        getArmorStand(b, false).thenAccept(hologram -> {
            if (hologram != null) hologram.remove();
        });

    }

    private static CompletableFuture<ArmorStand> getArmorStand(Block b, boolean createIfNoneExists) {
        Location l = new Location(b.getWorld(), b.getX() + 0.5, b.getY() + 0.7F, b.getZ() + 0.5);

        CompletableFuture<ArmorStand> future = new CompletableFuture<>();

        l.getWorld().getChunkAtAsync(l).thenAccept(chunk -> {
            try {
                for (Entity n : chunk.getEntities()) {
                    if (n instanceof ArmorStand && n.getCustomName() != null && l.distanceSquared(n.getLocation()) < 1.0D) {
                        future.complete((ArmorStand) n);
                        return;
                    }
                }
                if (!createIfNoneExists) future.complete(null);
                else future.complete(create(l));
            } catch (Throwable throwable) {
                future.completeExceptionally(throwable);
            }
        });

        return future;

    }

    public static ArmorStand create(Location l) {
        ArmorStand armorStand = (ArmorStand) l.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setSilent(true);
        armorStand.setMarker(true);
        armorStand.setGravity(false);
        armorStand.setBasePlate(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setRemoveWhenFarAway(false);
        return armorStand;
    }

}
