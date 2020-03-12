package io.github.thebusybiscuit.slimefun4.api.gps;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.thebusybiscuit.cscorelib2.chat.ChatColors;
import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import io.github.thebusybiscuit.cscorelib2.skull.SkullItem;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.api.Slimefun;

public final class TeleportationManager {

    private final Set<UUID> teleporterUsers = new HashSet<>();
    private final int[] teleporterBorder = { 0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53 };
    private final int[] teleporterInventory = { 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43 };

    private final GPSNetwork network;

    public TeleportationManager(GPSNetwork gpsNetwork) {
        network = gpsNetwork;
    }

    public void openTeleporterGUI(Player p, UUID uuid, Block b, int complexity) {
        if (teleporterUsers.contains(p.getUniqueId())) {
            return;
        }

        p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1F, 1F);
        teleporterUsers.add(p.getUniqueId());

        ChestMenu menu = new ChestMenu("&3Teleporter");
        menu.addMenuCloseHandler(pl -> teleporterUsers.remove(pl.getUniqueId()));

        for (int slot : teleporterBorder) {
            menu.addItem(slot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }

        menu.addItem(4, new CustomItem(SkullItem.fromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzljODg4MWU0MjkxNWE5ZDI5YmI2MWExNmZiMjZkMDU5OTEzMjA0ZDI2NWRmNWI0MzliM2Q3OTJhY2Q1NiJ9fX0="), "&7Waypoint Overview &e(Select a Destination)"));
        menu.addMenuClickHandler(4, ChestMenuUtils.getEmptyClickHandler());

        Location source = new Location(b.getWorld(), b.getX(), b.getY(), b.getZ());
        //Location source = new Location(b.getWorld(), b.getX() + 0.5D, b.getY() + 2D, b.getZ() + 0.5D);
        int index = 0;

        for (Map.Entry<String, Location> entry : network.getWaypoints(uuid).entrySet()) {
            if (index >= teleporterInventory.length) break;
            int slot = teleporterInventory[index];

            Location l = entry.getValue();
            ItemStack globe = network.getIcon(entry);

            if(source.getWorld().getName().equals(l.getWorld().getName()))
                menu.addItem(slot, new CustomItem(globe, entry.getKey(), "&8\u21E8 &7World: &r" + l.getWorld().getName(), "&8\u21E8 &7X: &r" + l.getX(), "&8\u21E8 &7Y: &r" + l.getY(), "&8\u21E8 &7Z: &r" + l.getZ(), "&8\u21E8 &7Estimated Teleportation Time: &r" + (50 / getSpeed(network.getNetworkComplexity(uuid), source, l)) + "s", "", "&8\u21E8 &cClick to select" , "&8\u21E8 &7speed: &r" + getSpeed(complexity, source, l), "&8\u21E8 &7distance: &r" + Math.abs((int) (source.getX() - l.getX())) + Math.abs((int) (source.getY() - l.getY())) + Math.abs((int) (source.getZ() - l.getZ())) ));
            else
                menu.addItem(slot, new CustomItem(globe, entry.getKey(), "&8\u21E8 &7World: &r" + l.getWorld().getName(), "&8\u21E8 &7X: &r" + l.getX(), "&8\u21E8 &7Y: &r" + l.getY(), "&8\u21E8 &7Z: &r" + l.getZ(), "&8\u21E8 &7Estimated Teleportation Time: &r" + (50 / getSpeed(network.getNetworkComplexity(uuid), source, l)) + "s", "", "&8\u21E8 &cClick to select" , "&8\u21E8 &7speed: &r" + getSpeed(complexity, source, l), "&8\u21E8 &7distance: &r" + Math.abs((int) (source.getX() - l.getX() * 8.0)) + Math.abs((int) (source.getY() - l.getY())) + Math.abs((int) (source.getZ() - l.getZ() * 8.0)) ));

            menu.addMenuClickHandler(slot, (pl, slotn, item, action) -> {
                pl.closeInventory();
                start(pl.getUniqueId(), complexity, source, l, false);
                return false;
            });

            index++;
        }

        menu.open(p);
    }

    public void start(UUID uuid, int complexity, Location source, Location destination, boolean resistance) {
        teleporterUsers.add(uuid);

        updateProgress(uuid, getSpeed(complexity, source, destination), 1, source, destination, resistance);
    }

    public int getSpeed(int complexity, Location source, Location destination) {
        int speed = complexity;
        if(!(source.getWorld().getName().equals(destination.getWorld().getName()))) speed *= 0.1;

        speed = ((complexity < distance(source, destination)) ? ((complexity - 5 * (distance(source, destination) - complexity)) / 200) : speed / 200);

        if(complexity == 0) return -1;
        /*else if(complexity <= 20000) return speed < 1 ? 1 : speed;
        else if(complexity <= 50000) return speed < 5 ? 5 : speed;
        else if(complexity <= 80000) return speed < 10 ? 10 : speed;
        else if(complexity <= 100000) return speed < 25 ? 25 : speed;
        else if(complexity > 100000) return speed < 35 ? 35 : speed;*/
        /********** If you want to get fucked, enable the above code. **********/
        
        // if (speed > 1500000000) speed = 1500000000;//max speed changed from 50%/sec -> 500%/s(now that 10w complexity is "full speed")
        // if (!(source.getWorld().getName().equals(destination.getWorld().getName())))
        //     return (int) (speed / 10);
        // else if(complexity > source.distance(destination))
        //     speed = speed - (int) (distance(source, destination) / ((long) 200));
        // else 
		//     speed = speed - (int) (distance(source, destination) / ((long) 200));
		
		return speed < 1 ? 1 : speed;
	}
	
	private int distance(Location source, Location destination) {
		if (source.getWorld().getName().equals(destination.getWorld().getName())) {
            int distance = 0;
            // distance = (int) source.distance(destination);
            distance = Math.abs((int) (source.getX() - destination.getX())) + Math.abs((int) (source.getY() - destination.getY())) + Math.abs((int) (source.getZ() - destination.getZ()));
        //return distance;
		    return distance > 1500000000 ? 1500000000 : distance;//modified
        }
        else{
            int distance = 0;
            distance = Math.abs((int) (source.getX() - destination.getX() * 8.0)) + Math.abs((int) (source.getY() - destination.getY())) + Math.abs((int) (source.getZ() - destination.getZ() * 8.0));
            return distance;
        } 
        // return 1500000000;
		//else return ;//modified
	}

    private boolean isValid(Player p, Location source) {
        return p != null && p.getLocation().distanceSquared(source) < 2.0;
    }

    private void no_complexity(UUID uuid, Player p){
        teleporterUsers.remove(uuid);

        if (p != null) {
            p.sendTitle(ChatColors.color(SlimefunPlugin.getLocal().getMessage(p, "machines.TELEPORTER.cancelled")), ChatColors.color("You don't have any satellites!"), 20, 60, 20);
        }
    }

    private void cancel(UUID uuid, Player p) {
        teleporterUsers.remove(uuid);

        if (p != null) {
            p.sendTitle(ChatColors.color(SlimefunPlugin.getLocal().getMessage(p, "machines.TELEPORTER.cancelled")), ChatColors.color("&c&k40&r&c%"), 20, 60, 20);
        }
    }

    private void updateProgress(UUID uuid, int speed, int progress, Location source, Location destination, boolean resistance) {
        Player p = Bukkit.getPlayer(uuid);

        if(speed == -1){
            no_complexity(uuid, p);
        }

        else if (isValid(p, source)) {
            if (progress > 99) {
                p.sendTitle(ChatColors.color(SlimefunPlugin.getLocal().getMessage(p, "machines.TELEPORTER.teleported")), ChatColors.color("&b100%"), 20, 60, 20);

                p.teleport(destination);

                if (resistance) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 20));
                    SlimefunPlugin.getLocal().sendMessage(p, "machines.TELEPORTER.invulnerability");
                }

                destination.getWorld().spawnParticle(Particle.PORTAL, new Location(destination.getWorld(), destination.getX(), destination.getY() + 1, destination.getZ()), progress * 2, 0.2F, 0.8F, 0.2F);
                destination.getWorld().playSound(destination, Sound.BLOCK_BEACON_ACTIVATE, 1F, 1F);
                teleporterUsers.remove(uuid);
            }
            else {
                p.sendTitle(ChatColors.color(SlimefunPlugin.getLocal().getMessage(p, "machines.TELEPORTER.teleporting")), ChatColors.color("&b" + progress + "%"), 0, 60, 0);

                source.getWorld().spawnParticle(Particle.PORTAL, source, progress * 2, 0.2F, 0.8F, 0.2F);
                source.getWorld().playSound(source, Sound.BLOCK_BEACON_AMBIENT, 1F, 0.6F);

                Slimefun.runSync(() -> updateProgress(uuid, speed, progress + speed, source, destination, resistance), 10L);
            }
        }
        else cancel(uuid, p);
    }

}
