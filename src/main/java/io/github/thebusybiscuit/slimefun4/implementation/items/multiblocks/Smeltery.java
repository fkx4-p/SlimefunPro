package io.github.thebusybiscuit.slimefun4.implementation.items.multiblocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Dropper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.thebusybiscuit.cscorelib2.inventory.InvUtils;
import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.Lists.Categories;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.multiblocks.MultiBlockMachine;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.Slimefun;

public class Smeltery extends MultiBlockMachine {

    private final BlockFace[] faces = { BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };
    private int fireBreakingChance;

    public Smeltery() {
        super(Categories.MACHINES_1, SlimefunItems.SMELTERY, new ItemStack[] { 
              null, new ItemStack(Material.NETHER_BRICK_FENCE), null, 
              new ItemStack(Material.NETHER_BRICKS), new CustomItem(Material.DISPENSER, "Dispenser (Facing up)"), new ItemStack(Material.NETHER_BRICKS), 
              null, new ItemStack(Material.FLINT_AND_STEEL), null 
        }, 
        new ItemStack[] { 
              SlimefunItems.IRON_DUST, new ItemStack(Material.IRON_INGOT),
              SlimefunItems.LAPIS_LAZULI_DUST, new ItemStack(Material.LAPIS_LAZULI)
        }, 
        BlockFace.DOWN, new String[] { "chance.fireBreak" }, new Integer[] { 34 });
    }

    @Override
    public void postRegister() {
        super.postRegister();

        fireBreakingChance = (int) Slimefun.getItemValue(getID(), "chance.fireBreak");
    }

    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> items = new ArrayList<>();

        for (int i = 0; i < recipes.size() - 1; i += 2) {
            if (Arrays.stream(recipes.get(i)).skip(1).anyMatch(Objects::nonNull)) {
                continue;
            }

            items.add(recipes.get(i)[0]);
            items.add(recipes.get(i + 1)[0]);
        }

        return items;
    }

    @Override
    public void onInteract(Player p, Block b) {
        Block dispBlock = b.getRelative(BlockFace.DOWN);
        Dispenser disp = (Dispenser) dispBlock.getState();
        Inventory inv = disp.getInventory();
        List<ItemStack[]> inputs = RecipeType.getRecipeInputList(this);

        for (int i = 0; i < inputs.size(); i++) {
            if (canCraft(inv, inputs, i)) {
                ItemStack output = RecipeType.getRecipeOutputList(this, inputs.get(i)).clone();

                if (Slimefun.hasUnlocked(p, output, true)) {
                    Inventory outputInv = findOutputInventory(output, dispBlock, inv);

                    if (outputInv != null) {
                        craft(p, dispBlock, b, inv, inputs.get(i), output, outputInv);
                    }
                    else {
                        SlimefunPlugin.getLocal().sendMessage(p, "machines.full-inventory", true);
                    }
                }

                return;
            }
        }
        
        SlimefunPlugin.getLocal().sendMessage(p, "machines.pattern-not-found", true);
    }

    private void craft(Player p, Block dispenser, Block b, Inventory inv, ItemStack[] recipe, ItemStack output, Inventory outputInv) {
        for (ItemStack removing : recipe) {
            if (removing != null) {
                InvUtils.removeItem(inv, removing.getAmount(), true, stack -> SlimefunManager.isItemSimilar(stack, removing, true));
            }
        }

        outputInv.addItem(output);
        p.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
        p.getWorld().playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);

        if (ThreadLocalRandom.current().nextInt(100) < fireBreakingChance) {
            consumeFire(p, dispenser, b);
        }
    }

    private void consumeFire(Player p, Block dispenser, Block b) {
        Inventory chamber = findIgnitionChamber(dispenser);

        if (chamber != null) {
            if (chamber.contains(Material.FLINT_AND_STEEL)) {
                ItemStack item = chamber.getItem(chamber.first(Material.FLINT_AND_STEEL));
                ItemMeta meta = item.getItemMeta();
                ((Damageable) meta).setDamage(((Damageable) meta).getDamage() + 1);
                item.setItemMeta(meta);

                if (((Damageable) item.getItemMeta()).getDamage() >= item.getType().getMaxDurability()) {
                    item.setAmount(0);
                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
                }

                p.getWorld().playSound(p.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1, 1);
            }
            else {
                SlimefunPlugin.getLocal().sendMessage(p, "machines.ignition-chamber-no-flint", true);

                Block fire = b.getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN);
                fire.getWorld().playEffect(fire.getLocation(), Effect.STEP_SOUND, fire.getType());
                fire.setType(Material.AIR);
            }
        }
        else {
            Block fire = b.getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN);
            fire.getWorld().playEffect(fire.getLocation(), Effect.STEP_SOUND, fire.getType());
            fire.setType(Material.AIR);
        }
    }

    private boolean canCraft(Inventory inv, List<ItemStack[]> inputs, int i) {
        for (ItemStack converting : inputs.get(i)) {
            if (converting != null) {
                for (int j = 0; j < inv.getContents().length; j++) {
                    if (j == (inv.getContents().length - 1) && !SlimefunManager.isItemSimilar(converting, inv.getContents()[j], true)) {
                        return false;
                    }
                    else if (SlimefunManager.isItemSimilar(inv.getContents()[j], converting, true)) break;
                }
            }
        }

        return true;
    }

    private Inventory findIgnitionChamber(Block b) {
        for (BlockFace face : faces) {
            if (b.getRelative(face).getType() == Material.DROPPER && BlockStorage.check(b.getRelative(face), "IGNITION_CHAMBER")) {
                return ((Dropper) b.getRelative(face).getState()).getInventory();
            }
        }

        return null;
    }

}
