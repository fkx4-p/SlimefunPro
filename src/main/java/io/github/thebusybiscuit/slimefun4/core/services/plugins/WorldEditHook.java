package io.github.thebusybiscuit.slimefun4.core.services.plugins;

import com.google.common.collect.ImmutableMap;
import com.sk89q.jnbt.CompoundTag;
import com.sk89q.jnbt.StringTag;
import com.sk89q.jnbt.Tag;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.event.extent.EditSessionEvent;
import com.sk89q.worldedit.extent.AbstractDelegateExtent;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.util.eventbus.Subscribe;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockStateHolder;
import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockPlaceHandler;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class WorldEditHook {

    public WorldEditHook() {
        WorldEdit.getInstance().getEventBus().register(this);
    }

    @Subscribe
    public void wrapForLogging(final EditSessionEvent event) {
        event.setExtent(new AbstractDelegateExtent(event.getExtent()) {

            @Override
            public <T extends BlockStateHolder<T>> boolean setBlock(BlockVector3 pos, T block) throws WorldEditException {
                Slimefun.getLogger().info("Called setBlock " + pos);
                World world = Bukkit.getWorld(event.getWorld().getName());
                if (world != null) {
                    CompoundTag nbt = block.toBaseBlock().getNbtData();
                    final Location location = new Location(world, pos.getBlockX(), pos.getBlockY(), pos.getBlockZ());
                    if (nbt != null && nbt.containsKey("PublicBukkitValues")
                            && nbt.getValue().get("PublicBukkitValues") instanceof CompoundTag
                            && ((CompoundTag) nbt.getValue().get("PublicBukkitValues"))
                            .containsKey("slimefun:slimefun_block_data")) {
                        Slimefun.getLogger().info("update block info" + location);

                        SlimefunItem sfItem = SlimefunItem.getByID(((CompoundTag) nbt.getValue().get("PublicBukkitValues"))
                                .getString("slimefun:slimefun_block"));
                        SlimefunBlockHandler blockHandler = SlimefunPlugin.getRegistry()
                                .getBlockHandlers().get(sfItem.getID());
                        if (blockHandler != null) {
                            blockHandler.onPlace(Bukkit.getPlayer(event.getActor().getUniqueId()),
                                    location.getBlock(), sfItem);
                        } else {
                            sfItem.callItemHandler(BlockPlaceHandler.class, handler ->
                                    handler.onBlockPlace(new BlockPlaceEvent(
                                            location.getBlock(),
                                            location.getBlock().getState(),
                                            (location.getBlock().getFace(location.getBlock()) != null)
                                                    ? location.getBlock().getRelative(
                                                    location.getBlock().getFace(location.getBlock()))
                                                    : location.getBlock().getRelative(BlockFace.DOWN),
                                            sfItem.getItem().clone(),
                                            Bukkit.getPlayer(event.getActor().getUniqueId()),
                                            true,
                                            EquipmentSlot.HAND
                                    ), sfItem.getItem().clone()));
                        }
                        BlockStorage.setBlockInfo(
                                location,
                                ((CompoundTag) nbt.getValue().get("PublicBukkitValues"))
                                        .getString("slimefun:slimefun_block_data"),
                                true
                        );
                    } else {
                        Slimefun.getLogger().info("remove block info " + location);
                        BlockStorage.clearBlockInfo(location);
                    }
                }
                return getExtent().setBlock(pos, block);
            }

            @Override
            public BaseBlock getFullBlock(BlockVector3 pos) {
                Slimefun.getLogger().info("Called getFullBlock " + pos);
                World world = Bukkit.getWorld(event.getWorld().getName());
                if (world != null) {
                    final Location location = new Location(world, pos.getBlockX(), pos.getBlockY(), pos.getBlockZ());
                    if (BlockStorage.hasBlockInfo(location)) {
                        Slimefun.getLogger().info("save block info " + location);
                        BaseBlock state = super.getBlock(pos).toBaseBlock(); // Get base block
                        if (!state.hasNbtData()) // Create one if no NBT presents
                            state = state.toBaseBlock(new CompoundTag(ImmutableMap.of()));
                        CompoundTag nbt = state.getNbtData(); // Get NBT Data

                        assert nbt != null; // IDEA behavior
                        Map<String, Tag> values1 = new HashMap<>(nbt.getValue()); // Get root NBT

                        if (!(values1.get("PublicBukkitValues") instanceof CompoundTag)) // Create one if the tag is missing
                            values1.put("PublicBukkitValues", new CompoundTag(ImmutableMap.of()));
                        Map<String, Tag> valuesInner = new HashMap<>(
                                ((CompoundTag) (values1.get("PublicBukkitValues"))).getValue()); // Get data
                        valuesInner.put("slimefun:slimefun_block_raw",
                                new StringTag(BlockStorage.getBlockInfoAsJson(location))); // Write raw data
                        valuesInner.put("slimefun:slimefun_block",
                                new StringTag(Objects.requireNonNull(BlockStorage.checkID(location)))); // IDEA Behavior & write block name

                        values1.put("PublicBukkitValues", new CompoundTag(valuesInner)); // Put "PublicBukkitValues" back to NBT

                        state = state.toBaseBlock(new CompoundTag(values1)); // Put NBT back to block

                        return state;
                    }
                }
                return super.getFullBlock(pos);
            }
        });
    }

}
