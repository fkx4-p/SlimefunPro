package me.mrCookieSlime.Slimefun.Lists;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import io.github.thebusybiscuit.cscorelib2.skull.SkullItem;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.implementation.items.magical.StormStaff;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ColoredFireworkStar;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

/**
 * This class holds a static references to every {@link SlimefunItemStack}
 * found in Slimefun.
 * 
 * @author TheBusyBiscuit
 *
 */
public final class SlimefunItems {

    private SlimefunItems() {}

    /* Items */
    public static final ItemStack PORTABLE_CRAFTER = new SlimefunItemStack("PORTABLE_CRAFTER", "72ec4a4bd8a58f8361f8a0303e2199d33d624ea5f92f7cb3414fee95e2d861", "&6Portable Crafter", "&a&oA portable Crafting Table", "", "&eRight Click&7 to open");
    public static final ItemStack PORTABLE_DUSTBIN = new SlimefunItemStack("PORTABLE_DUSTBIN", "32d41042ce99147cc38cac9e46741576e7ee791283e6fac8d3292cae2935f1f", "&6Portable Dustbin", "&rYour portable Item-Destroyer", "", "&eRight Click&7 to open");
    public static final ItemStack ENDER_BACKPACK = new SlimefunItemStack("ENDER_BACKPACK", "2a3b34862b9afb63cf8d5779966d3fba70af82b04e83f3eaf6449aeba", "&6Ender Backpack", "&a&oA portable Ender Chest", "", "&eRight Click&7 to open");
    public static final ItemStack MAGIC_EYE_OF_ENDER = new SlimefunItemStack("MAGIC_EYE_OF_ENDER", Material.ENDER_EYE, "&6&lMagic Eye of Ender", "&4&lRequires full Ender Armor", "", "&7&eRight Click&7 to shoot an Ender Pearl");
    public static final ItemStack BROKEN_SPAWNER = new SlimefunItemStack("BROKEN_SPAWNER", Material.SPAWNER, "&cBroken Spawner", "&7Type: &b<Type>", "", "&cFractured, must be repaired in an Ancient Altar");
    public static final ItemStack REPAIRED_SPAWNER = new SlimefunItemStack("REINFORCED_SPAWNER", Material.SPAWNER, "&bReinforced Spawner", "&7Type: &b<Type>");
    public static final ItemStack INFERNAL_BONEMEAL = new SlimefunItemStack("INFERNAL_BONEMEAL", Material.BONE_MEAL, "&4Infernal Bonemeal", "", "&cSpeeds up the Growth of", "&cNether Warts as well");

    /* Gadgets */
    public static final SlimefunItemStack GOLD_PAN = new SlimefunItemStack("GOLD_PAN", Material.BOWL, "&6Gold Pan", "&a&oCan get you all kinds of Goodies...", "", "&7&eRight Click&7 to pan various Stuff out of Gravel");
    public static final SlimefunItemStack NETHER_GOLD_PAN = new SlimefunItemStack("NETHER_GOLD_PAN", Material.BOWL, "&4Nether Gold Pan", "", "&7&eRight Click&7 to pan various stuff out of Soul Sand");
    public static final SlimefunItemStack PARACHUTE = new SlimefunItemStack("PARACHUTE", Material.LEATHER_CHESTPLATE, Color.WHITE, "&r&lParachute", "", "&7Hold &eShift&7 to use");
    public static final SlimefunItemStack GRAPPLING_HOOK = new SlimefunItemStack("GRAPPLING_HOOK", Material.LEAD, "&6Grappling Hook", "", "&7&eRight Click&7 to use");
    public static final SlimefunItemStack SOLAR_HELMET = new SlimefunItemStack("SOLAR_HELMET", Material.IRON_HELMET, "&bSolar Helmet", "", "&a&oCharges held Items and Armor");
    public static final ItemStack CLOTH = new SlimefunItemStack("CLOTH", Material.PAPER, "&bCloth");
    public static final ItemStack CAN = new SlimefunItemStack("CAN", "94da97f080e395b842c4cc82a840823d4dbd8ca688a206853e5783e4bfdc012", "&rTin Can");
    public static final SlimefunItemStack NIGHT_VISION_GOGGLES = new SlimefunItemStack("NIGHT_VISION_GOGGLES", Material.LEATHER_HELMET, Color.BLACK, "&aNight Vision Goggles", "", "&9+ Night Vision");
    public static final SlimefunItemStack FARMER_SHOES = new SlimefunItemStack("FARMER_SHOES", Material.LEATHER_BOOTS, Color.YELLOW, "&eFarmer Shoes", "", "&6&oPrevents you from trampling your Crops");
    public static final ItemStack INFUSED_MAGNET = new SlimefunItemStack("INFUSED_MAGNET", "aba8ebc4c6a81730947499bf7e1d5e73fed6c1bb2c051e96d35eb16d24610e7", "&aInfused Magnet", "", "&rMagical infused Magnets", "&rattract nearby Items", "&ras long as it is somewhere in", "&ryour Inventory", "", "&7Hold &eShift&7 to pick up nearby Items");
    public static final ItemStack RAG = new SlimefunItemStack("RAG", Material.PAPER, "&cRag", "", "&aLevel I - Medical Supply", "", "&rRestores 2 Hearts", "&rExtinguishes Fire", "", "&7&eRight Click&7 to use");
    public static final ItemStack BANDAGE = new SlimefunItemStack("BANDAGE", Material.PAPER, "&cBandage", "", "&aLevel II - Medical Supply", "", "&rRestores 4 Hearts", "&rExtinguishes Fire", "", "&7&eRight Click&7 to use");
    public static final ItemStack SPLINT = new SlimefunItemStack("SPLINT", Material.STICK, "&cSplint", "", "&aLevel I - Medical Supply", "", "&rRestores 2 Hearts", "", "&7&eRight Click&7 to use");
    public static final ItemStack VITAMINS = new SlimefunItemStack("VITAMINS", Material.NETHER_WART, "&cVitamins", "", "&aLevel III - Medical Supply", "", "&rRestores 4 Hearts", "&rExtinguishes Fire", "&rCures Poison/Wither/Radiation", "", "&7&eRight Click&7 to use");
    public static final ItemStack MEDICINE = new SlimefunItemStack("MEDICINE", Material.POTION, Color.RED, "&cMedicine", "", "&aLevel III - Medical Supply", "", "&rRestores 4 Hearts", "&rExtinguishes Fire", "&rCures Poison/Wither/Radiation");

    public static final SlimefunItemStack FLASK_OF_KNOWLEDGE = new SlimefunItemStack("FLASK_OF_KNOWLEDGE", Material.GLASS_BOTTLE, "&cFlask of Knowledge", "", "&rAllows you to store some of", "&ryour Experience in a Bottle", "&7Cost: &a1 Level");
    public static final SlimefunItemStack FILLED_FLASK_OF_KNOWLEDGE = new SlimefunItemStack("FILLED_FLASK_OF_KNOWLEDGE", Material.EXPERIENCE_BOTTLE, "&aFlask of Knowledge");

    /* Backpacks */
    public static final ItemStack BACKPACK_SMALL = new SlimefunItemStack("SMALL_BACKPACK", "40cb1e67b512ab2d4bf3d7ace0eaaf61c32cd4681ddc3987ceb326706a33fa", "&eSmall Backpack", "", "&7Size: &e9", "&7ID: <ID>", "", "&7&eRight Click&7 to open");
    public static final ItemStack BACKPACK_MEDIUM = new SlimefunItemStack("MEDIUM_BACKPACK", "40cb1e67b512ab2d4bf3d7ace0eaaf61c32cd4681ddc3987ceb326706a33fa", "&eBackpack", "", "&7Size: &e18", "&7ID: <ID>", "", "&7&eRight Click&7 to open");
    public static final ItemStack BACKPACK_LARGE = new SlimefunItemStack("LARGE_BACKPACK", "40cb1e67b512ab2d4bf3d7ace0eaaf61c32cd4681ddc3987ceb326706a33fa", "&eLarge Backpack", "", "&7Size: &e27", "&7ID: <ID>", "", "&7&eRight Click&7 to open");
    public static final ItemStack WOVEN_BACKPACK = new SlimefunItemStack("WOVEN_BACKPACK", "40cb1e67b512ab2d4bf3d7ace0eaaf61c32cd4681ddc3987ceb326706a33fa", "&eWoven Backpack", "", "&7Size: &e36", "&7ID: <ID>", "", "&7&eRight Click&7 to open");
    public static final ItemStack GILDED_BACKPACK = new SlimefunItemStack("GILDED_BACKPACK", "40cb1e67b512ab2d4bf3d7ace0eaaf61c32cd4681ddc3987ceb326706a33fa", "&eGilded Backpack", "", "&7Size: &e45", "&7ID: <ID>", "", "&7&eRight Click&7 to open");
    public static final ItemStack RADIANT_BACKPACK = new SlimefunItemStack("RADIANT_BACKPACK", "40cb1e67b512ab2d4bf3d7ace0eaaf61c32cd4681ddc3987ceb326706a33fa", "&eRadiant Backpack", "", "&7Size: &e54 (Double chest)", "&7ID: <ID>", "", "&7&eRight Click&7 to open");
    public static final ItemStack BOUND_BACKPACK = new SlimefunItemStack("BOUND_BACKPACK", "2a3b34862b9afb63cf8d5779966d3fba70af82b04e83f3eaf6449aeba", "&cSoulbound Backpack", "", "&7Size: &e36", "&7ID: <ID>", "", "&7&eRight Click&7 to open");
    public static final ItemStack COOLER = new SlimefunItemStack("COOLER", "d4c1572584eb5de229de9f5a4f779d0aacbaffd33bcb33eb4536a6a2bc6a1", "&bCooler", "&rAllows you to store Juices/Smoothies", "&rand automatically consumes them when you are hungry", "&rand you have this in your Inventory", "", "&7Size: &e27", "&7ID: <ID>", "", "&7&eRight Click&7 to open");

    /* Jetpacks */
    public static final SlimefunItemStack DURALUMIN_JETPACK = new SlimefunItemStack("DURALUMIN_JETPACK", Material.LEATHER_CHESTPLATE, Color.SILVER, "&9Electric Jetpack &7- &eI", "", "&8\u21E8 &7Material: &bDuralumin", "&c&o&8\u21E8 &e\u26A1 &70 / 20 J", "&8\u21E8 &7Thrust: &c0.35", "", "&7Hold &eShift&7 to use");
    public static final SlimefunItemStack SOLDER_JETPACK = new SlimefunItemStack("SOLDER_JETPACK", Material.LEATHER_CHESTPLATE, Color.SILVER, "&9Electric Jetpack &7- &eII", "", "&8\u21E8 &7Material: &bSolder", "&c&o&8\u21E8 &e\u26A1 &70 / 30 J", "&8\u21E8 &7Thrust: &c0.4", "", "&7Hold &eShift&7 to use");
    public static final SlimefunItemStack BILLON_JETPACK = new SlimefunItemStack("BILLON_JETPACK", Material.LEATHER_CHESTPLATE, Color.SILVER, "&9Electric Jetpack &7- &eIII", "", "&8\u21E8 &7Material: &bBillon", "&c&o&8\u21E8 &e\u26A1 &70 / 45 J", "&8\u21E8 &7Thrust: &c0.45", "", "&7Hold &eShift&7 to use");
    public static final SlimefunItemStack STEEL_JETPACK = new SlimefunItemStack("STEEL_JETPACK", Material.LEATHER_CHESTPLATE, Color.SILVER, "&9Electric Jetpack &7- &eIV", "", "&8\u21E8 &7Material: &bSteel", "&c&o&8\u21E8 &e\u26A1 &70 / 60 J", "&8\u21E8 &7Thrust: &c0.5", "", "&7Hold &eShift&7 to use");
    public static final SlimefunItemStack DAMASCUS_STEEL_JETPACK = new SlimefunItemStack("DAMASCUS_STEEL_JETPACK", Material.LEATHER_CHESTPLATE, Color.SILVER, "&9Electric Jetpack &7- &eV", "", "&8\u21E8 &7Material: &bDamascus Steel", "&c&o&8\u21E8 &e\u26A1 &70 / 75 J", "&8\u21E8 &7Thrust: &c0.55", "", "&7Hold &eShift&7 to use");
    public static final SlimefunItemStack REINFORCED_ALLOY_JETPACK = new SlimefunItemStack("REINFORCED_ALLOY_JETPACK", Material.LEATHER_CHESTPLATE, Color.SILVER, "&9Electric Jetpack &7- &eVI", "", "&8\u21E8 &7Material: &bReinforced Alloy", "&c&o&8\u21E8 &e\u26A1 &70 / 100 J", "&8\u21E8 &7Thrust: &c0.6", "", "&7Hold &eShift&7 to use");
    public static final SlimefunItemStack CARBONADO_JETPACK = new SlimefunItemStack("CARBONADO_JETPACK", Material.LEATHER_CHESTPLATE, Color.BLACK, "&9Electric Jetpack &7- &eVII", "", "&8\u21E8 &7Material: &bCarbonado", "&c&o&8\u21E8 &e\u26A1 &70 / 150 J", "&8\u21E8 &7Thrust: &c0.7", "", "&7Hold &eShift&7 to use");
    public static final SlimefunItemStack ARMORED_JETPACK = new SlimefunItemStack("ARMORED_JETPACK", Material.IRON_CHESTPLATE, "&9Armored Jetpack", "&8\u21E8 &7Material: &bSteel", "", "&c&o&8\u21E8 &e\u26A1 &70 / 50 J", "&8\u21E8 &7Thrust: &c0.45", "", "&7Hold &eShift&7 to use");

    /* Jetboots */
    public static final SlimefunItemStack DURALUMIN_JETBOOTS = new SlimefunItemStack("DURALUMIN_JETBOOTS", Material.LEATHER_BOOTS, Color.SILVER, "&9Jet Boots &7- &eI", "", "&8\u21E8 &7Material: &bDuralumin", "&c&o&8\u21E8 &e\u26A1 &70 / 20 J", "&8\u21E8 &7Speed: &a0.35", "&8\u21E8 &7Accuracy: &c50%", "", "&7Hold &eShift&7 to use");
    public static final SlimefunItemStack SOLDER_JETBOOTS = new SlimefunItemStack("SOLDER_JETBOOTS", Material.LEATHER_BOOTS, Color.SILVER, "&9Jet Boots &7- &eII", "", "&8\u21E8 &7Material: &bSolder", "&c&o&8\u21E8 &e\u26A1 &70 / 30 J", "&8\u21E8 &7Speed: &a0.4", "&8\u21E8 &7Accuracy: &660%", "", "&7Hold &eShift&7 to use");
    public static final SlimefunItemStack BILLON_JETBOOTS = new SlimefunItemStack("BILLON_JETBOOTS", Material.LEATHER_BOOTS, Color.SILVER, "&9Jet Boots &7- &eIII", "", "&8\u21E8 &7Material: &bBillon", "&c&o&8\u21E8 &e\u26A1 &70 / 40 J", "&8\u21E8 &7Speed: &a0.45", "&8\u21E8 &7Accuracy: &665%", "", "&7Hold &eShift&7 to use");
    public static final SlimefunItemStack STEEL_JETBOOTS = new SlimefunItemStack("STEEL_JETBOOTS", Material.LEATHER_BOOTS, Color.SILVER, "&9Jet Boots &7- &eIV", "", "&8\u21E8 &7Material: &bSteel", "&c&o&8\u21E8 &e\u26A1 &70 / 50 J", "&8\u21E8 &7Speed: &a0.5", "&8\u21E8 &7Accuracy: &e70%", "", "&7Hold &eShift&7 to use");
    public static final SlimefunItemStack DAMASCUS_STEEL_JETBOOTS = new SlimefunItemStack("DAMASCUS_STEEL_JETBOOTS", Material.LEATHER_BOOTS, Color.SILVER, "&9Jet Boots &7- &eV", "", "&8\u21E8 &7Material: &bDamascus Steel", "&c&o&8\u21E8 &e\u26A1 &70 / 75 J", "&8\u21E8 &7Speed: &a0.55", "&8\u21E8 &7Accuracy: &a75%", "", "&7Hold &eShift&7 to use");
    public static final SlimefunItemStack REINFORCED_ALLOY_JETBOOTS = new SlimefunItemStack("REINFORCED_ALLOY_JETBOOTS", Material.LEATHER_BOOTS, Color.SILVER, "&9Jet Boots &7- &eVI", "", "&8\u21E8 &7Material: &bReinforced Alloy", "&c&o&8\u21E8 &e\u26A1 &70 / 100 J", "&8\u21E8 &7Speed: &a0.6", "&8\u21E8 &7Accuracy: &c80%", "", "&7Hold &eShift&7 to use");
    public static final SlimefunItemStack CARBONADO_JETBOOTS = new SlimefunItemStack("CARBONADO_JETBOOTS", Material.LEATHER_BOOTS, Color.BLACK, "&9Jet Boots &7- &eVII", "", "&8\u21E8 &7Material: &bCarbonado", "&c&o&8\u21E8 &e\u26A1 &70 / 125 J", "&8\u21E8 &7Speed: &a0.7", "&8\u21E8 &7Accuracy: &c99.9%", "", "&7Hold &eShift&7 to use");
    public static final SlimefunItemStack ARMORED_JETBOOTS = new SlimefunItemStack("ARMORED_JETBOOTS", Material.IRON_BOOTS, "&9Armored Jet Boots", "", "&8\u21E8 &7Material: &bSteel", "&c&o&8\u21E8 &e\u26A1 &70 / 50 J", "&8\u21E8 &7Speed: &a0.45", "&8\u21E8 &7Accuracy: &e70%", "", "&7Hold &eShift&7 to use");

    /* Multi Tools */
    public static final SlimefunItemStack DURALUMIN_MULTI_TOOL = new SlimefunItemStack("DURALUMIN_MULTI_TOOL", Material.SHEARS, "&9Multi Tool &7- &eI", "", "&8\u21E8 &7Material: &bDuralumin", "&c&o&8\u21E8 &e\u26A1 &70 / 20 J", "", "&7&eRight Click&7 to use", "&7Hold &eShift + Right Click&7 to change the Mode");
    public static final SlimefunItemStack SOLDER_MULTI_TOOL = new SlimefunItemStack("SOLDER_MULTI_TOOL", Material.SHEARS, "&9Multi Tool &7- &eII", "", "&8\u21E8 &7Material: &bSolder", "&c&o&8\u21E8 &e\u26A1 &70 / 30 J", "", "&7&eRight Click&7 to use", "&7Hold &eShift + Right Click&7 to change the Mode");
    public static final SlimefunItemStack BILLON_MULTI_TOOL = new SlimefunItemStack("BILLON_MULTI_TOOL", Material.SHEARS, "&9Multi Tool &7- &eIII", "", "&8\u21E8 &7Material: &bBillon", "&c&o&8\u21E8 &e\u26A1 &70 / 40 J", "", "&7&eRight Click&7 to use", "&7Hold &eShift + Right Click&7 to change the Mode");
    public static final SlimefunItemStack STEEL_MULTI_TOOL = new SlimefunItemStack("STEEL_MULTI_TOOL", Material.SHEARS, "&9Multi Tool &7- &eIV", "", "&8\u21E8 &7Material: &bSteel", "&c&o&8\u21E8 &e\u26A1 &70 / 50 J", "", "&7&eRight Click&7 to use", "&7Hold &eShift + Right Click&7 to change the Mode");
    public static final SlimefunItemStack DAMASCUS_STEEL_MULTI_TOOL = new SlimefunItemStack("DAMASCUS_STEEL_MULTI_TOOL", Material.SHEARS, "&9Multi Tool &7- &eV", "", "&8\u21E8 &7Material: &bDamascus Steel", "&c&o&8\u21E8 &e\u26A1 &70 / 60 J", "", "&7&eRight Click&7 to use", "&7Hold &eShift + Right Click&7 to change the Mode");
    public static final SlimefunItemStack REINFORCED_ALLOY_MULTI_TOOL = new SlimefunItemStack("REINFORCED_ALLOY_MULTI_TOOL", Material.SHEARS, "&9Multi Tool &7- &eVI", "", "&8\u21E8 &7Material: &bReinforced Alloy", "&c&o&8\u21E8 &e\u26A1 &70 / 75 J", "", "&7&eRight Click&7 to use", "&7Hold &eShift + Right Click&7 to change the Mode");
    public static final SlimefunItemStack CARBONADO_MULTI_TOOL = new SlimefunItemStack("CARBONADO_MULTI_TOOL", Material.SHEARS, "&9Multi Tool &7- &eVII", "", "&8\u21E8 &7Material: &bCarbonado", "&c&o&8\u21E8 &e\u26A1 &70 / 100 J", "", "&7&eRight Click&7 to use", "&7Hold &eShift + Right Click&7 to change the Mode");

    static {
        ItemMeta duralumin = DURALUMIN_MULTI_TOOL.getItemMeta();
        duralumin.setUnbreakable(true);
        DURALUMIN_MULTI_TOOL.setItemMeta(duralumin);

        ItemMeta solder = SOLDER_MULTI_TOOL.getItemMeta();
        solder.setUnbreakable(true);
        SOLDER_MULTI_TOOL.setItemMeta(solder);

        ItemMeta billon = BILLON_MULTI_TOOL.getItemMeta();
        billon.setUnbreakable(true);
        BILLON_MULTI_TOOL.setItemMeta(billon);

        ItemMeta steel = STEEL_MULTI_TOOL.getItemMeta();
        steel.setUnbreakable(true);
        STEEL_MULTI_TOOL.setItemMeta(steel);

        ItemMeta damascus = DAMASCUS_STEEL_MULTI_TOOL.getItemMeta();
        damascus.setUnbreakable(true);
        DAMASCUS_STEEL_MULTI_TOOL.setItemMeta(damascus);

        ItemMeta reinforced = REINFORCED_ALLOY_MULTI_TOOL.getItemMeta();
        reinforced.setUnbreakable(true);
        REINFORCED_ALLOY_MULTI_TOOL.setItemMeta(reinforced);

        ItemMeta carbonado = CARBONADO_MULTI_TOOL.getItemMeta();
        carbonado.setUnbreakable(true);
        CARBONADO_MULTI_TOOL.setItemMeta(carbonado);
    }

    /* Food */
    public static final ItemStack FORTUNE_COOKIE = new SlimefunItemStack("FORTUNE_COOKIE", Material.COOKIE, "&6Fortune Cookie", "", "&a&oTells you stuff about your Future :o");
    public static final ItemStack DIET_COOKIE = new SlimefunItemStack("DIET_COOKIE", Material.COOKIE, "&6Diet Cookie", "", "&aA very &olightweight &r&acookie.");
    public static final ItemStack MAGIC_SUGAR = new SlimefunItemStack("MAGIC_SUGAR", Material.SUGAR, "&6Magic Sugar", "", "&a&oFeel the Power of Hermes!");
    public static final ItemStack MONSTER_JERKY = new SlimefunItemStack("MONSTER_JERKY", Material.ROTTEN_FLESH, "&6Monster Jerky", "", "&a&oNo longer hungry");
    public static final ItemStack APPLE_JUICE = new SlimefunItemStack("APPLE_JUICE", Color.RED, new PotionEffect(PotionEffectType.SATURATION, 6, 0), "&cApple Juice", "", "&7&oRestores &b&o" + "3.0" + " &7&oHunger");
    public static final ItemStack MELON_JUICE = new SlimefunItemStack("MELON_JUICE", Color.RED, new PotionEffect(PotionEffectType.SATURATION, 6, 0), "&cMelon Juice", "", "&7&oRestores &b&o" + "3.0" + " &7&oHunger");
    public static final ItemStack CARROT_JUICE = new SlimefunItemStack("CARROT_JUICE", Color.ORANGE, new PotionEffect(PotionEffectType.SATURATION, 6, 0), "&6Carrot Juice", "", "&7&oRestores &b&o" + "3.0" + " &7&oHunger");
    public static final ItemStack PUMPKIN_JUICE = new SlimefunItemStack("PUMPKIN_JUICE", Color.ORANGE, new PotionEffect(PotionEffectType.SATURATION, 6, 0), "&6Pumpkin Juice", "", "&7&oRestores &b&o" + "3.0" + " &7&oHunger");
    public static final ItemStack SWEET_BERRY_JUICE = new SlimefunItemStack("SWEET_BERRY_JUICE", Color.RED, new PotionEffect(PotionEffectType.SATURATION, 6, 0), "&cSweet Berry Juice", "", "&7&oRestores &b&o" + "3.0" + " &7&oHunger");
    public static final ItemStack GOLDEN_APPLE_JUICE = new SlimefunItemStack("GOLDEN_APPLE_JUICE", Color.YELLOW, new PotionEffect(PotionEffectType.ABSORPTION, 20 * 20, 0), "&bGolden Apple Juice");

    public static final SlimefunItemStack BEEF_JERKY = new SlimefunItemStack("BEEF_JERKY", Material.COOKED_BEEF, "&6Beef Jerky", "", "&rExtra saturating!");
    public static final SlimefunItemStack PORK_JERKY = new SlimefunItemStack("PORK_JERKY", Material.COOKED_PORKCHOP, "&6Pork Jerky", "", "&rExtra saturating!");
    public static final SlimefunItemStack CHICKEN_JERKY = new SlimefunItemStack("CHICKEN_JERKY", Material.COOKED_CHICKEN, "&6Chicken Jerky", "", "&rExtra saturating!");
    public static final SlimefunItemStack MUTTON_JERKY = new SlimefunItemStack("MUTTON_JERKY", Material.COOKED_MUTTON, "&6Mutton Jerky", "", "&rExtra saturating!");
    public static final SlimefunItemStack RABBIT_JERKY = new SlimefunItemStack("RABBIT_JERKY", Material.COOKED_RABBIT, "&6Rabbit Jerky", "", "&rExtra saturating!");
    public static final SlimefunItemStack FISH_JERKY = new SlimefunItemStack("FISH_JERKY", Material.COOKED_COD, "&6Fish Jerky", "", "&rExtra saturating!");

    public static final SlimefunItemStack KELP_COOKIE = new SlimefunItemStack("KELP_COOKIE", Material.COOKIE, "&2Kelp Cookie");

    /* Christmas */
    public static final SlimefunItemStack CHRISTMAS_MILK = new SlimefunItemStack("CHRISTMAS_MILK", Color.WHITE, new PotionEffect(PotionEffectType.SATURATION, 5, 0), "&6Glass of Milk", "", "&7&oRestores &b&o" + "2.5" + " &7&oHunger");
    public static final SlimefunItemStack CHRISTMAS_CHOCOLATE_MILK = new SlimefunItemStack("CHRISTMAS_CHOCOLATE_MILK", Color.MAROON, new PotionEffect(PotionEffectType.SATURATION, 12, 0), "&6Chocolate Milk", "", "&7&oRestores &b&o" + "6.0" + " &7&oHunger");
    public static final SlimefunItemStack CHRISTMAS_EGG_NOG = new SlimefunItemStack("CHRISTMAS_EGG_NOG", Color.GRAY, new PotionEffect(PotionEffectType.SATURATION, 7, 0), "&aEgg Nog", "", "&7&oRestores &b&o" + "3.5" + " &7&oHunger");
    public static final SlimefunItemStack CHRISTMAS_APPLE_CIDER = new SlimefunItemStack("CHRISTMAS_APPLE_CIDER", Color.RED, new PotionEffect(PotionEffectType.SATURATION, 14, 0), "&cApple Cider", "", "&7&oRestores &b&o" + "7.0" + " &7&oHunger");
    public static final SlimefunItemStack CHRISTMAS_COOKIE = new SlimefunItemStack("CHRISTMAS_COOKIE", Material.COOKIE, ChatUtils.christmas("Christmas Cookie"));
    public static final SlimefunItemStack CHRISTMAS_FRUIT_CAKE = new SlimefunItemStack("CHRISTMAS_FRUIT_CAKE", Material.PUMPKIN_PIE, ChatUtils.christmas("Fruit Cake"));
    public static final SlimefunItemStack CHRISTMAS_APPLE_PIE = new SlimefunItemStack("CHRISTMAS_APPLE_PIE", Material.PUMPKIN_PIE, "&rApple Pie");
    public static final SlimefunItemStack CHRISTMAS_HOT_CHOCOLATE = new SlimefunItemStack("CHRISTMAS_HOT_CHOCOLATE", Color.MAROON, new PotionEffect(PotionEffectType.SATURATION, 14, 0), "&6Hot Chocolate", "", "&7&oRestores &b&o" + "7.0" + " &7&oHunger");
    public static final SlimefunItemStack CHRISTMAS_CAKE = new SlimefunItemStack("CHRISTMAS_CAKE", Material.PUMPKIN_PIE, ChatUtils.christmas("Christmas Cake"));
    public static final SlimefunItemStack CHRISTMAS_CARAMEL = new SlimefunItemStack("CHRISTMAS_CARAMEL", Material.BRICK, "&6Caramel");
    public static final SlimefunItemStack CHRISTMAS_CARAMEL_APPLE = new SlimefunItemStack("CHRISTMAS_CARAMEL_APPLE", Material.APPLE, "&6Caramel Apple");
    public static final SlimefunItemStack CHRISTMAS_CHOCOLATE_APPLE = new SlimefunItemStack("CHRISTMAS_CHOCOLATE_APPLE", Material.APPLE, "&6Chocolate Apple");
    public static final SlimefunItemStack CHRISTMAS_PRESENT = new SlimefunItemStack("CHRISTMAS_PRESENT", Material.CHEST, ChatUtils.christmas("Christmas Present"), "&7From: &emrCookieSlime", "&7To: &eYou", "", "&eRight Click&7 to open");

    /* Easter */
    public static final SlimefunItemStack EASTER_EGG = new SlimefunItemStack("EASTER_EGG", Material.EGG, "&rEaster Egg", "&bSurprise! Surprise!");
    public static final SlimefunItemStack EASTER_CARROT_PIE = new SlimefunItemStack("CARROT_PIE", Material.PUMPKIN_PIE, "&6Carrot Pie");
    public static final SlimefunItemStack EASTER_APPLE_PIE = new SlimefunItemStack("EASTER_APPLE_PIE", Material.PUMPKIN_PIE, "&rApple Pie");

    /* Weapons */
    public static final ItemStack GRANDMAS_WALKING_STICK = new SlimefunItemStack("GRANDMAS_WALKING_STICK", Material.STICK, "&7Grandmas Walking Stick");
    public static final ItemStack GRANDPAS_WALKING_STICK = new SlimefunItemStack("GRANDPAS_WALKING_STICK", Material.STICK, "&7Grandpas Walking Stick");
    public static final ItemStack SWORD_OF_BEHEADING = new SlimefunItemStack("SWORD_OF_BEHEADING", Material.IRON_SWORD, "&6Sword of Beheading", "&7Beheading II", "", "&rHas a chance to behead Mobs", "&r(even a higher chance for Wither Skeletons)");
    public static final ItemStack BLADE_OF_VAMPIRES = new SlimefunItemStack("BLADE_OF_VAMPIRES", Material.GOLDEN_SWORD, "&cBlade of Vampires", "&7Life Steal I", "", "&rEverytime you attack something", "&ryou have a 45% chance to", "&rrecover 2 Hearts of your Health");
    public static final ItemStack SEISMIC_AXE = new SlimefunItemStack("SEISMIC_AXE", Material.IRON_AXE, "&aSeismic Axe", "", "&7&oA portable Earthquake...", "", "&7&eRight Click&7 to use");

    static {
        GRANDMAS_WALKING_STICK.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
        GRANDPAS_WALKING_STICK.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);

        BLADE_OF_VAMPIRES.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
        BLADE_OF_VAMPIRES.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
        BLADE_OF_VAMPIRES.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
    }

    /* Bows */
    public static final SlimefunItemStack EXPLOSIVE_BOW = new SlimefunItemStack("EXPLOSIVE_BOW", Material.BOW, "&cExplosive Bow", "&rAny Arrows fired using this Bow", "&rwill launch hit enemys into the air");
    public static final SlimefunItemStack ICY_BOW = new SlimefunItemStack("ICY_BOW", Material.BOW, "&bIcy Bow", "&rAny Arrows fired using this Bow", "&rwill prevent hit enemys from moving", "&rfor 2 seconds");

    /* Tools */
    public static final SlimefunItemStack AUTO_SMELT_PICKAXE = new SlimefunItemStack("SMELTERS_PICKAXE", Material.DIAMOND_PICKAXE, "&6Smelter's Pickaxe", "&c&lAuto-Smelting", "", "&9Works with Fortune");
    public static final SlimefunItemStack LUMBER_AXE = new SlimefunItemStack("LUMBER_AXE", Material.DIAMOND_AXE, "&6Lumber Axe", "&a&oCuts down the whole Tree...");
    public static final SlimefunItemStack PICKAXE_OF_CONTAINMENT = new SlimefunItemStack("PICKAXE_OF_CONTAINMENT", Material.IRON_PICKAXE, "&cPickaxe of Containment", "", "&9Can pickup Spawners");
    public static final SlimefunItemStack HERCULES_PICKAXE = new SlimefunItemStack("HERCULES_PICKAXE", Material.IRON_PICKAXE, "&9Hercules' Pickaxe", "", "&rSo powerful that it", "&rcrushes all mined Ores", "&rinto Dust...");
    public static final SlimefunItemStack EXPLOSIVE_PICKAXE = new SlimefunItemStack("EXPLOSIVE_PICKAXE", Material.DIAMOND_PICKAXE, "&eExplosive Pickaxe", "", "&rAllows you to mine a good bit", "&rof Blocks at once...", "", "&9Works with Fortune");
    public static final SlimefunItemStack EXPLOSIVE_SHOVEL = new SlimefunItemStack("EXPLOSIVE_SHOVEL", Material.DIAMOND_SHOVEL, "&eExplosive Shovel", "", "&rAllows you to mine a good bit", "&rof diggable Blocks at once...");
    public static final SlimefunItemStack PICKAXE_OF_THE_SEEKER = new SlimefunItemStack("PICKAXE_OF_THE_SEEKER", Material.DIAMOND_PICKAXE, "&aPickaxe of the Seeker", "&rWill always point you to the nearest Ore", "&rbut might get damaged when doing it", "", "&7&eRight Click&7 to be pointed to the nearest Ore");
    public static final SlimefunItemStack COBALT_PICKAXE = new SlimefunItemStack("COBALT_PICKAXE", Material.IRON_PICKAXE, "&9Cobalt Pickaxe");
    public static final SlimefunItemStack PICKAXE_OF_VEIN_MINING = new SlimefunItemStack("PICKAXE_OF_VEIN_MINING", Material.DIAMOND_PICKAXE, "&ePickaxe of Vein Mining", "", "&rThis Pickaxe will dig out", "&rwhole Veins of Ores...");

    static {
        HERCULES_PICKAXE.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
        HERCULES_PICKAXE.addUnsafeEnchantment(Enchantment.DIG_SPEED, 3);

        COBALT_PICKAXE.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
        COBALT_PICKAXE.addUnsafeEnchantment(Enchantment.DIG_SPEED, 6);
    }

    /* Armor */
    public static final SlimefunItemStack GLOWSTONE_HELMET = new SlimefunItemStack("GLOWSTONE_HELMET", Material.LEATHER_HELMET, Color.YELLOW, "&e&lGlowstone Helmet", "", "&a&oShining like the sun!", "", "&9+ Night Vision");
    public static final SlimefunItemStack GLOWSTONE_CHESTPLATE = new SlimefunItemStack("GLOWSTONE_CHESTPLATE", Material.LEATHER_CHESTPLATE, Color.YELLOW, "&e&lGlowstone Chestplate", "", "&a&oShining like the sun!", "", "&9+ Night Vision");
    public static final SlimefunItemStack GLOWSTONE_LEGGINGS = new SlimefunItemStack("GLOWSTONE_LEGGINGS", Material.LEATHER_LEGGINGS, Color.YELLOW, "&e&lGlowstone Leggings", "", "&a&oShining like the sun!", "", "&9+ Night Vision");
    public static final SlimefunItemStack GLOWSTONE_BOOTS = new SlimefunItemStack("GLOWSTONE_BOOTS", Material.LEATHER_BOOTS, Color.YELLOW, "&e&lGlowstone Boots", "", "&a&oShining like the sun!", "", "&9+ Night Vision");

    public static final SlimefunItemStack ENDER_HELMET = new SlimefunItemStack("ENDER_HELMET", Material.LEATHER_HELMET, Color.fromRGB(28, 25, 112), "&5&lEnder Helmet", "", "&a&oSometimes its here, sometimes there!");
    public static final SlimefunItemStack ENDER_CHESTPLATE = new SlimefunItemStack("ENDER_CHESTPLATE", Material.LEATHER_CHESTPLATE, Color.fromRGB(28, 25, 112), "&5&lEnder Chestplate", "", "&a&oSometimes its here, sometimes there!");
    public static final SlimefunItemStack ENDER_LEGGINGS = new SlimefunItemStack("ENDER_LEGGINGS", Material.LEATHER_LEGGINGS, Color.fromRGB(28, 25, 112), "&5&lEnder Leggings", "", "&a&oSometimes its here, sometimes there!");
    public static final SlimefunItemStack ENDER_BOOTS = new SlimefunItemStack("ENDER_BOOTS", Material.LEATHER_BOOTS, Color.fromRGB(28, 25, 112), "&5&lEnder Boots", "", "&a&oSometimes its here, sometimes there!", "", "&9+ No Enderpearl Damage");

    public static final SlimefunItemStack SLIME_HELMET = new SlimefunItemStack("SLIME_HELMET", Material.LEATHER_HELMET, Color.LIME, "&a&lSlime Helmet", "", "&a&oBouncy Feeling");
    public static final SlimefunItemStack SLIME_CHESTPLATE = new SlimefunItemStack("SLIME_CHESTPLATE", Material.LEATHER_CHESTPLATE, Color.LIME, "&a&lSlime Chestplate", "", "&a&oBouncy Feeling");
    public static final SlimefunItemStack SLIME_LEGGINGS = new SlimefunItemStack("SLIME_LEGGINGS", Material.LEATHER_LEGGINGS, Color.LIME, "&a&lSlime Leggings", "", "&a&oBouncy Feeling", "", "&9+ Speed");
    public static final SlimefunItemStack SLIME_BOOTS = new SlimefunItemStack("SLIME_BOOTS", Material.LEATHER_BOOTS, Color.LIME, "&a&lSlime Boots", "", "&a&oBouncy Feeling", "", "&9+ Jump Boost", "&9+ No Fall Damage");

    public static final SlimefunItemStack CACTUS_HELMET = new SlimefunItemStack("CACTUS_HELMET", Material.LEATHER_HELMET, Color.GREEN, "&2Cactus Helmet");
    public static final SlimefunItemStack CACTUS_CHESTPLATE = new SlimefunItemStack("CACTUS_CHESTPLATE", Material.LEATHER_CHESTPLATE, Color.GREEN, "&2Cactus Chestplate");
    public static final SlimefunItemStack CACTUS_LEGGINGS = new SlimefunItemStack("CACTUS_LEGGINGS", Material.LEATHER_LEGGINGS, Color.GREEN, "&2Cactus Leggings");
    public static final SlimefunItemStack CACTUS_BOOTS = new SlimefunItemStack("CACTUS_BOOTS", Material.LEATHER_BOOTS, Color.GREEN, "&2Cactus Boots");

    public static final SlimefunItemStack DAMASCUS_STEEL_HELMET = new SlimefunItemStack("DAMASCUS_STEEL_HELMET", Material.IRON_HELMET, "&7Damascus Steel Helmet");
    public static final SlimefunItemStack DAMASCUS_STEEL_CHESTPLATE = new SlimefunItemStack("DAMASCUS_STEEL_CHESTPLATE", Material.IRON_CHESTPLATE, "&7Damascus Steel Chestplate");
    public static final SlimefunItemStack DAMASCUS_STEEL_LEGGINGS = new SlimefunItemStack("DAMASCUS_STEEL_LEGGINGS", Material.IRON_LEGGINGS, "&7Damascus Steel Leggings");
    public static final SlimefunItemStack DAMASCUS_STEEL_BOOTS = new SlimefunItemStack("DAMASCUS_STEEL_BOOTS", Material.IRON_BOOTS, "&7Damascus Steel Boots");

    public static final SlimefunItemStack REINFORCED_ALLOY_HELMET = new SlimefunItemStack("REINFORCED_ALLOY_HELMET", Material.IRON_HELMET, "&bReinforced Helmet");
    public static final SlimefunItemStack REINFORCED_ALLOY_CHESTPLATE = new SlimefunItemStack("REINFORCED_ALLOY_CHESTPLATE", Material.IRON_CHESTPLATE, "&bReinforced Chestplate");
    public static final SlimefunItemStack REINFORCED_ALLOY_LEGGINGS = new SlimefunItemStack("REINFORCED_ALLOY_LEGGINGS", Material.IRON_LEGGINGS, "&bReinforced Leggings");
    public static final SlimefunItemStack REINFORCED_ALLOY_BOOTS = new SlimefunItemStack("REINFORCED_ALLOY_BOOTS", Material.IRON_BOOTS, "&bReinforced Boots");

    public static final ItemStack SCUBA_HELMET = new SlimefunItemStack("SCUBA_HELMET", Material.LEATHER_HELMET, Color.ORANGE, "&cScuba Helmet", "", "&bAllows you to breathe Underwater", "&4&oPart of Hazmat Suit");
    public static final ItemStack HAZMATSUIT_CHESTPLATE = new SlimefunItemStack("HAZMAT_CHESTPLATE", Material.LEATHER_CHESTPLATE, Color.ORANGE, "&cHazmat Suit", "", "&bAllows you to walk through Fire", "&4&oPart of Hazmat Suit");
    public static final ItemStack HAZMATSUIT_LEGGINGS = new SlimefunItemStack("HAZMAT_LEGGINGS", Material.LEATHER_LEGGINGS, Color.ORANGE, "&cHazmat Suit Leggings", "", "&4&oPart of Hazmat Suit");
    public static final ItemStack RUBBER_BOOTS = new SlimefunItemStack("RUBBER_BOOTS", Material.LEATHER_BOOTS, Color.BLACK, "&cRubber Boots", "", "&4&oPart of Hazmat Suit");

    public static final SlimefunItemStack GILDED_IRON_HELMET = new SlimefunItemStack("GILDED_IRON_HELMET", Material.GOLDEN_HELMET, "&6Gilded Iron Helmet");
    public static final SlimefunItemStack GILDED_IRON_CHESTPLATE = new SlimefunItemStack("GILDED_IRON_CHESTPLATE", Material.GOLDEN_CHESTPLATE, "&6Gilded Iron Chestplate");
    public static final SlimefunItemStack GILDED_IRON_LEGGINGS = new SlimefunItemStack("GILDED_IRON_LEGGINGS", Material.GOLDEN_LEGGINGS, "&6Gilded Iron Leggings");
    public static final SlimefunItemStack GILDED_IRON_BOOTS = new SlimefunItemStack("GILDED_IRON_BOOTS", Material.GOLDEN_BOOTS, "&6Gilded Iron Boots");

    public static final SlimefunItemStack GOLD_HELMET = new SlimefunItemStack("GOLD_12K_HELMET", Material.GOLDEN_HELMET, "&6Gold Helmet", "&912-Carat");
    public static final SlimefunItemStack GOLD_CHESTPLATE = new SlimefunItemStack("GOLD_12K_CHESTPLATE", Material.GOLDEN_CHESTPLATE, "&6Gold Chestplate", "&912-Carat");
    public static final SlimefunItemStack GOLD_LEGGINGS = new SlimefunItemStack("GOLD_12K_LEGGINGS", Material.GOLDEN_LEGGINGS, "&6Gold Leggings", "&912-Carat");
    public static final SlimefunItemStack GOLD_BOOTS = new SlimefunItemStack("GOLD_12K_BOOTS", Material.GOLDEN_BOOTS, "&6Gold Boots", "&912-Carat");

    public static final SlimefunItemStack SLIME_HELMET_STEEL = new SlimefunItemStack("SLIME_STEEL_HELMET", Material.IRON_HELMET, "&a&lSlime Helmet", "&7&oReinforced", "", "&a&oBouncy Feeling");
    public static final SlimefunItemStack SLIME_CHESTPLATE_STEEL = new SlimefunItemStack("SLIME_STEEL_CHESTPLATE", Material.IRON_CHESTPLATE, "&a&lSlime Chestplate", "&7&oReinforced", "", "&a&oBouncy Feeling");
    public static final SlimefunItemStack SLIME_LEGGINGS_STEEL = new SlimefunItemStack("SLIME_STEEL_LEGGINGS", Material.IRON_LEGGINGS, "&a&lSlime Leggings", "&7&oReinforced", "", "&a&oBouncy Feeling", "", "&9+ Speed");
    public static final SlimefunItemStack SLIME_BOOTS_STEEL = new SlimefunItemStack("SLIME_STEEL_BOOTS", Material.IRON_BOOTS, "&a&lSlime Boots", "&7&oReinforced", "", "&a&oBouncy Feeling", "", "&9+ Jump Boost", "&9+ No Fall Damage");

    public static final SlimefunItemStack BOOTS_OF_THE_STOMPER = new SlimefunItemStack("BOOTS_OF_THE_STOMPER", Material.LEATHER_BOOTS, Color.AQUA, "&bBoots of the Stomper", "", "&9All Fall Damage you receive", "&9will be applied to nearby Mobs/Players", "", "&9+ No Fall Damage");

    static {
        Map<Enchantment, Integer> cactus = new HashMap<>();
        cactus.put(Enchantment.THORNS, 3);
        cactus.put(Enchantment.DURABILITY, 6);

        CACTUS_HELMET.addUnsafeEnchantments(cactus);
        CACTUS_CHESTPLATE.addUnsafeEnchantments(cactus);
        CACTUS_LEGGINGS.addUnsafeEnchantments(cactus);
        CACTUS_BOOTS.addUnsafeEnchantments(cactus);

        Map<Enchantment, Integer> damascus = new HashMap<>();
        damascus.put(Enchantment.DURABILITY, 5);
        damascus.put(Enchantment.PROTECTION_ENVIRONMENTAL, 5);

        DAMASCUS_STEEL_HELMET.addUnsafeEnchantments(damascus);
        DAMASCUS_STEEL_CHESTPLATE.addUnsafeEnchantments(damascus);
        DAMASCUS_STEEL_LEGGINGS.addUnsafeEnchantments(damascus);
        DAMASCUS_STEEL_BOOTS.addUnsafeEnchantments(damascus);

        Map<Enchantment, Integer> reinforced = new HashMap<>();
        reinforced.put(Enchantment.DURABILITY, 9);
        reinforced.put(Enchantment.PROTECTION_ENVIRONMENTAL, 9);

        REINFORCED_ALLOY_HELMET.addUnsafeEnchantments(reinforced);
        REINFORCED_ALLOY_CHESTPLATE.addUnsafeEnchantments(reinforced);
        REINFORCED_ALLOY_LEGGINGS.addUnsafeEnchantments(reinforced);
        REINFORCED_ALLOY_BOOTS.addUnsafeEnchantments(reinforced);

        Map<Enchantment, Integer> gilded = new HashMap<>();
        gilded.put(Enchantment.DURABILITY, 6);
        gilded.put(Enchantment.PROTECTION_ENVIRONMENTAL, 8);

        GILDED_IRON_HELMET.addUnsafeEnchantments(gilded);
        GILDED_IRON_CHESTPLATE.addUnsafeEnchantments(gilded);
        GILDED_IRON_LEGGINGS.addUnsafeEnchantments(gilded);
        GILDED_IRON_BOOTS.addUnsafeEnchantments(gilded);

        GOLD_HELMET.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
        GOLD_CHESTPLATE.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
        GOLD_LEGGINGS.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
        GOLD_BOOTS.addUnsafeEnchantment(Enchantment.DURABILITY, 10);

        Map<Enchantment, Integer> slime = new HashMap<>();
        slime.put(Enchantment.DURABILITY, 4);
        slime.put(Enchantment.PROTECTION_ENVIRONMENTAL, 2);

        SLIME_HELMET_STEEL.addUnsafeEnchantments(slime);
        SLIME_CHESTPLATE_STEEL.addUnsafeEnchantments(slime);
        SLIME_LEGGINGS_STEEL.addUnsafeEnchantments(slime);
        SLIME_BOOTS_STEEL.addUnsafeEnchantments(slime);
    }

    /* Misc */
    public static final ItemStack MAGIC_LUMP_1 = new SlimefunItemStack("MAGIC_LUMP_1", Material.GOLD_NUGGET, "&6Magical Lump &7- &eI", "", "&c&oTier: I");
    public static final ItemStack MAGIC_LUMP_2 = new SlimefunItemStack("MAGIC_LUMP_2", Material.GOLD_NUGGET, "&6Magical Lump &7- &eII", "", "&c&oTier: II");
    public static final ItemStack MAGIC_LUMP_3 = new SlimefunItemStack("MAGIC_LUMP_3", Material.GOLD_NUGGET, "&6Magical Lump &7- &eIII", "", "&c&oTier: III");
    public static final ItemStack ENDER_LUMP_1 = new SlimefunItemStack("ENDER_LUMP_1", Material.GOLD_NUGGET, "&5Ender Lump &7- &eI", "", "&c&oTier: I");
    public static final ItemStack ENDER_LUMP_2 = new SlimefunItemStack("ENDER_LUMP_2", Material.GOLD_NUGGET, "&5Ender Lump &7- &eII", "", "&c&oTier: II");
    public static final ItemStack ENDER_LUMP_3 = new SlimefunItemStack("ENDER_LUMP_3", Material.GOLD_NUGGET, "&5Ender Lump &7- &eIII", "", "&c&oTier: III");
    public static final ItemStack MAGICAL_BOOK_COVER = new SlimefunItemStack("MAGICAL_BOOK_COVER", Material.PAPER, "&6Magical Book Cover", "", "&a&oUsed for various Magic Books");
    public static final ItemStack BASIC_CIRCUIT_BOARD = new SlimefunItemStack("BASIC_CIRCUIT_BOARD", Material.ACTIVATOR_RAIL, "&bBasic Circuit Board");
    public static final ItemStack ADVANCED_CIRCUIT_BOARD = new SlimefunItemStack("ADVANCED_CIRCUIT_BOARD", Material.POWERED_RAIL, "&bAdvanced Circuit Board");
    public static final ItemStack WHEAT_FLOUR = new SlimefunItemStack("WHEAT_FLOUR", Material.SUGAR, "&rWheat Flour");
    public static final ItemStack STEEL_PLATE = new SlimefunItemStack("STEEL_PLATE", Material.PAPER, "&7&lSteel Plate");
    public static final ItemStack BATTERY = new SlimefunItemStack("BATTERY", "6e2dda6ef6185d4dd6ea8684e97d39ba8ab037e25f75cdea6bd29df8eb34ee", "&6Battery");
    public static final ItemStack CARBON = new SlimefunItemStack("CARBON", "8b3a095b6b81e6b9853a19324eedf0bb9349417258dd173b8eff87a087aa", "&eCarbon");
    public static final ItemStack COMPRESSED_CARBON = new SlimefunItemStack("COMPRESSED_CARBON", "321d495165748d3116f99d6b5bd5d42eb8ba592bcdfad37fd95f9b6c04a3b", "&cCompressed Carbon");
    public static final ItemStack CARBON_CHUNK = new SlimefunItemStack("CARBON_CHUNK", "321d495165748d3116f99d6b5bd5d42eb8ba592bcdfad37fd95f9b6c04a3b", "&4Carbon Chunk");
    public static final ItemStack STEEL_THRUSTER = new SlimefunItemStack("STEEL_THRUSTER", Material.BUCKET, "&7&lSteel Thruster");
    public static final ItemStack POWER_CRYSTAL = new SlimefunItemStack("POWER_CRYSTAL", "53c1b036b6e03517b285a811bd85e73f5abfdacc1ddf90dff962e180934e3", "&c&lPower Crystal");
    public static final ItemStack CHAIN = new SlimefunItemStack("CHAIN", Material.STRING, "&bChain");
    public static final ItemStack HOOK = new SlimefunItemStack("HOOK", Material.FLINT, "&bHook");
    public static final ItemStack SIFTED_ORE = new SlimefunItemStack("SIFTED_ORE", Material.GUNPOWDER, "&6Sifted Ore");
    public static final ItemStack STONE_CHUNK = new SlimefunItemStack("STONE_CHUNK", "ce8f5adb14d6c9f6b810d027543f1a8c1f417e2fed993c97bcd89c74f5e2e8", "&6Stone Chunk");
    public static final ItemStack LAVA_CRYSTAL = new SlimefunItemStack("LAVA_CRYSTAL", "a3ad8ee849edf04ed9a26ca3341f6033bd76dcc4231ed1ea63b7565751b27ac", "&4Lava Crystal");
    public static final ItemStack SALT = new SlimefunItemStack("SALT", Material.SUGAR, "&rSalt");
    public static final ItemStack CHEESE = new SlimefunItemStack("CHEESE", "34febbc15d1d4cc62bedc5d7a2b6f0f46cd5b0696a884de75e289e35cbb53a0", "&rCheese");
    public static final ItemStack BUTTER = new SlimefunItemStack("BUTTER", "b66b19f7d635d03473891df33017c549363209a8f6328a8542c213d08525e", "&rButter");
    public static final ItemStack DUCT_TAPE = new SlimefunItemStack("DUCT_TAPE", "b2faaceab6384fff5ed24bb44a4af2f584eb1382729ecd93a5369acfd6654", "&8Duct Tape", "", "&rYou can repair Items using this", "&rin an Auto-Anvil");
    public static final ItemStack HEAVY_CREAM = new SlimefunItemStack("HEAVY_CREAM", Material.SNOWBALL, "&rHeavy Cream");
    public static final ItemStack CRUSHED_ORE = new SlimefunItemStack("CRUSHED_ORE", Material.GUNPOWDER, "&6Crushed Ore");
    public static final ItemStack PULVERIZED_ORE = new SlimefunItemStack("PULVERIZED_ORE", Material.GUNPOWDER, "&6Pulverized Ore");
    public static final ItemStack PURE_ORE_CLUSTER = new SlimefunItemStack("PURE_ORE_CLUSTER", Material.GUNPOWDER, "&6Pure Ore Cluster");
    public static final ItemStack SMALL_URANIUM = new SlimefunItemStack("SMALL_URANIUM", "c8b29afa6d6dc923e2e1324bf8192750f7bdbddc689632a2b6c18d9fe7a5e", "&cSmall Chunk of Uranium", "", LoreBuilder.radioactive(Radioactivity.MODERATE), LoreBuilder.HAZMAT_SUIT_REQUIRED);
    public static final ItemStack TINY_URANIUM = new SlimefunItemStack("TINY_URANIUM", "c8b29afa6d6dc923e2e1324bf8192750f7bdbddc689632a2b6c18d9fe7a5e", "&cTiny Pile of Uranium", "", LoreBuilder.radioactive(Radioactivity.LOW));

    public static final ItemStack MAGNET = new SlimefunItemStack("MAGNET", "aba8ebc4c6a81730947499bf7e1d5e73fed6c1bb2c051e96d35eb16d24610e7", "&cMagnet");
    public static final ItemStack NECROTIC_SKULL = new SlimefunItemStack("NECROTIC_SKULL", Material.WITHER_SKELETON_SKULL, "&cNecrotic Skull");
    public static final ItemStack ESSENCE_OF_AFTERLIFE = new SlimefunItemStack("ESSENCE_OF_AFTERLIFE", Material.GUNPOWDER, "&4Essence of Afterlife");
    public static final ItemStack ELECTRO_MAGNET = new SlimefunItemStack("ELECTRO_MAGNET", "aba8ebc4c6a81730947499bf7e1d5e73fed6c1bb2c051e96d35eb16d24610e7", "&cElectromagnet");
    public static final ItemStack HEATING_COIL = new SlimefunItemStack("HEATING_COIL", "7e3bc4893ba41a3f73ee28174cdf4fef6b145e41fe6c82cb7be8d8e9771a5", "&cHeating Coil");
    public static final ItemStack COOLING_UNIT = new SlimefunItemStack("COOLING_UNIT", "754bad86c99df780c889a1098f77648ead7385cc1ddb093da5a7d8c4c2ae54d", "&bCooling Unit");
    public static final ItemStack ELECTRIC_MOTOR = new SlimefunItemStack("ELECTRIC_MOTOR", "8cbca012f67e54de9aee72ff424e056c2ae58de5eacc949ab2bcd9683cec", "&cElectric Motor");
    public static final ItemStack CARGO_MOTOR = new SlimefunItemStack("CARGO_MOTOR", "8cbca012f67e54de9aee72ff424e056c2ae58de5eacc949ab2bcd9683cec", "&3Cargo Motor");
    public static final ItemStack SCROLL_OF_DIMENSIONAL_TELEPOSITION = new SlimefunItemStack("SCROLL_OF_DIMENSIONAL_TELEPOSITION", Material.PAPER, "&6Scroll of Dimensional Teleposition", "", "&cThis Scroll is capable of creating", "&ca temporary black Hole which pulls", "&cnearby Entities into itself and sends", "&cthem into another Dimension where", "&ceverything is turned around", "", "&rIn other words: Makes Entities turn by 180 Degrees");
    public static final ItemStack TOME_OF_KNOWLEDGE_SHARING = new SlimefunItemStack("TOME_OF_KNOWLEDGE_SHARING", Material.BOOK, "&6Tome of Knowledge Sharing", "&7Owner: &bNone", "", "&eRight Click&7 to bind this Tome to yourself", "", "", "&eRight Click&7 to obtain all Researches by", "&7the previously assigned Owner");
    public static final ItemStack HARDENED_GLASS = new SlimefunItemStack("HARDENED_GLASS", Material.LIGHT_GRAY_STAINED_GLASS, "&7Hardened Glass", "", "&rWithstands Explosions");
    public static final ItemStack WITHER_PROOF_OBSIDIAN = new SlimefunItemStack("WITHER_PROOF_OBSIDIAN", Material.OBSIDIAN, "&5Wither-Proof Obsidian", "", "&rWithstands Explosions", "&rWithstands Wither Bosses");
    public static final ItemStack WITHER_PROOF_GLASS = new SlimefunItemStack("WITHER_PROOF_GLASS", Material.PURPLE_STAINED_GLASS, "&5Wither-Proof Glass", "", "&rWithstands Explosions", "&rWithstands Wither Bosses");
    public static final ItemStack REINFORCED_PLATE = new SlimefunItemStack("REINFORCED_PLATE", Material.PAPER, "&7Reinforced Plate");
    public static final ItemStack ANCIENT_PEDESTAL = new SlimefunItemStack("ANCIENT_PEDESTAL", Material.DISPENSER, "&dAncient Pedestal", "", "&5Part of the Ancient Altar");
    public static final ItemStack ANCIENT_ALTAR = new SlimefunItemStack("ANCIENT_ALTAR", Material.ENCHANTING_TABLE, "&dAncient Altar", "", "&5Multi-Block Altar for", "&5magical Crafting Processes");
    public static final ItemStack COPPER_WIRE = new SlimefunItemStack("COPPER_WIRE", Material.STRING, "&6Copper Wire", "", "&6Crucial component in electric modules");

    public static final SlimefunItemStack RAINBOW_WOOL = new SlimefunItemStack("RAINBOW_WOOL", Material.WHITE_WOOL, "&5Rainbow Wool", "", "&dCycles through all Colors of the Rainbow!");
    public static final SlimefunItemStack RAINBOW_GLASS = new SlimefunItemStack("RAINBOW_GLASS", Material.WHITE_STAINED_GLASS, "&5Rainbow Glass", "", "&dCycles through all Colors of the Rainbow!");
    public static final SlimefunItemStack RAINBOW_CLAY = new SlimefunItemStack("RAINBOW_CLAY", Material.WHITE_TERRACOTTA, "&5Rainbow Clay", "", "&dCycles through all Colors of the Rainbow!");
    public static final SlimefunItemStack RAINBOW_GLASS_PANE = new SlimefunItemStack("RAINBOW_GLASS_PANE", Material.WHITE_STAINED_GLASS_PANE, "&5Rainbow Glass Pane", "", "&dCycles through all Colors of the Rainbow!");
    public static final SlimefunItemStack RAINBOW_CONCRETE = new SlimefunItemStack("RAINBOW_CONCRETE", Material.WHITE_CONCRETE, "&5Rainbow Concrete", "", "&dCycles through all Colors of the Rainbow!");
    public static final SlimefunItemStack RAINBOW_GLAZED_TERRACOTTA = new SlimefunItemStack("RAINBOW_GLAZED_TERRACOTTA", Material.WHITE_GLAZED_TERRACOTTA, "&5Rainbow Glazed Terracotta", "", "&dCycles through all Colors of the Rainbow!");

    public static final SlimefunItemStack RAINBOW_WOOL_XMAS = new SlimefunItemStack("RAINBOW_WOOL_XMAS", Material.WHITE_WOOL, "&5Rainbow Wool &7(Christmas)", "", ChatUtils.christmas("< Christmas Edition >"));
    public static final SlimefunItemStack RAINBOW_GLASS_XMAS = new SlimefunItemStack("RAINBOW_GLASS_XMAS", Material.WHITE_STAINED_GLASS, "&5Rainbow Glass &7(Christmas)", "", ChatUtils.christmas("< Christmas Edition >"));
    public static final SlimefunItemStack RAINBOW_CLAY_XMAS = new SlimefunItemStack("RAINBOW_CLAY_XMAS", Material.WHITE_TERRACOTTA, "&5Rainbow Clay &7(Christmas)", "", ChatUtils.christmas("< Christmas Edition >"));
    public static final SlimefunItemStack RAINBOW_GLASS_PANE_XMAS = new SlimefunItemStack("RAINBOW_GLASS_PANE_XMAS", Material.WHITE_STAINED_GLASS_PANE, "&5Rainbow Glass Pane &7(Christmas)", "", ChatUtils.christmas("< Christmas Edition >"));
    public static final SlimefunItemStack RAINBOW_CONCRETE_XMAS = new SlimefunItemStack("RAINBOW_CONCRETE_XMAS", Material.WHITE_CONCRETE, "&5Rainbow Concrete &7(Christmas)", "", ChatUtils.christmas("< Christmas Edition >"));
    public static final SlimefunItemStack RAINBOW_GLAZED_TERRACOTTA_XMAS = new SlimefunItemStack("RAINBOW_GLAZED_TERRACOTTA_XMAS", Material.WHITE_GLAZED_TERRACOTTA, "&5Rainbow Glazed Terracotta &7(Christmas)", "", ChatUtils.christmas("< Christmas Edition >"));

    public static final SlimefunItemStack RAINBOW_WOOL_VALENTINE = new SlimefunItemStack("RAINBOW_WOOL_VALENTINE", Material.PINK_WOOL, "&5Rainbow Wool &7(Valentine's Day)", "", "&d< Valentine's Day Edition >");
    public static final SlimefunItemStack RAINBOW_GLASS_VALENTINE = new SlimefunItemStack("RAINBOW_GLASS_VALENTINE", Material.PINK_STAINED_GLASS, "&5Rainbow Glass &7(Valentine's Day)", "", "&d< Valentine's Day Edition >");
    public static final SlimefunItemStack RAINBOW_CLAY_VALENTINE = new SlimefunItemStack("RAINBOW_CLAY_VALENTINE", Material.PINK_TERRACOTTA, "&5Rainbow Clay &7(Valentine's Day)", "", "&d< Valentine's Day Edition >");
    public static final SlimefunItemStack RAINBOW_GLASS_PANE_VALENTINE = new SlimefunItemStack("RAINBOW_GLASS_PANE_VALENTINE", Material.PINK_STAINED_GLASS_PANE, "&5Rainbow Glass Pane &7(Valentine's Day)", "", "&d< Valentine's Day Edition >");
    public static final SlimefunItemStack RAINBOW_CONCRETE_VALENTINE = new SlimefunItemStack("RAINBOW_CONCRETE_VALENTINE", Material.PINK_CONCRETE, "&5Rainbow Concrete &7(Valentine's Day)", "", "&d< Valentine's Day Edition >");
    public static final SlimefunItemStack RAINBOW_GLAZED_TERRACOTTA_VALENTINE = new SlimefunItemStack("RAINBOW_GLAZED_TERRACOTTA_VALENTINE", Material.PINK_GLAZED_TERRACOTTA, "&5Rainbow Glazed Terracotta &7(Valentine's Day)", "", "&d< Valentine's Day Edition >");

    public static final SlimefunItemStack RAINBOW_WOOL_HALLOWEEN = new SlimefunItemStack("RAINBOW_WOOL_HALLOWEEN", Material.ORANGE_WOOL, "&5Rainbow Wool &7(Halloween)", "", "&6< Halloween Edition >");
    public static final SlimefunItemStack RAINBOW_GLASS_HALLOWEEN = new SlimefunItemStack("RAINBOW_GLASS_HALLOWEEN", Material.ORANGE_STAINED_GLASS, "&5Rainbow Glass &7(Halloween)", "", "&6< Halloween Edition >");
    public static final SlimefunItemStack RAINBOW_CLAY_HALLOWEEN = new SlimefunItemStack("RAINBOW_CLAY_HALLOWEEN", Material.ORANGE_TERRACOTTA, "&5Rainbow Clay &7(Halloween)", "", "&6< Halloween Edition >");
    public static final SlimefunItemStack RAINBOW_GLASS_PANE_HALLOWEEN = new SlimefunItemStack("RAINBOW_GLASS_PANE_HALLOWEEN", Material.ORANGE_STAINED_GLASS_PANE, "&5Rainbow Glass Pane &7(Halloween)", "", "&6< Halloween Edition >");
    public static final SlimefunItemStack RAINBOW_CONCRETE_HALLOWEEN = new SlimefunItemStack("RAINBOW_CONCRETE_HALLOWEEN", Material.ORANGE_CONCRETE, "&5Rainbow Concrete &7(Halloween)", "", "&6< Halloween Edition >");
    public static final SlimefunItemStack RAINBOW_GLAZED_TERRACOTTA_HALLOWEEN = new SlimefunItemStack("RAINBOW_GLAZED_TERRACOTTA_HALLOWEEN", Material.ORANGE_GLAZED_TERRACOTTA, "&5Rainbow Glazed Terracotta &7(Halloween)", "", "&6< Halloween Edition >");

    /* Ingots */
    public static final ItemStack COPPER_INGOT = new SlimefunItemStack("COPPER_INGOT", Material.BRICK, "&bCopper Ingot");
    public static final ItemStack TIN_INGOT = new SlimefunItemStack("TIN_INGOT", Material.IRON_INGOT, "&bTin Ingot");
    public static final ItemStack SILVER_INGOT = new SlimefunItemStack("SILVER_INGOT", Material.IRON_INGOT, "&bSilver Ingot");
    public static final ItemStack ALUMINUM_INGOT = new SlimefunItemStack("ALUMINUM_INGOT", Material.IRON_INGOT, "&bAluminum Ingot");
    public static final ItemStack LEAD_INGOT = new SlimefunItemStack("LEAD_INGOT", Material.IRON_INGOT, "&bLead Ingot");
    public static final ItemStack ZINC_INGOT = new SlimefunItemStack("ZINC_INGOT", Material.IRON_INGOT, "&bZinc Ingot");
    public static final ItemStack MAGNESIUM_INGOT = new SlimefunItemStack("MAGNESIUM_INGOT", Material.IRON_INGOT, "&bMagnesium Ingot");

    /* Alloy (Carbon + Iron) */
    public static final ItemStack STEEL_INGOT = new SlimefunItemStack("STEEL_INGOT", Material.IRON_INGOT, "&bSteel Ingot");
    /* Alloy (Copper + Tin) */
    public static final ItemStack BRONZE_INGOT = new SlimefunItemStack("BRONZE_INGOT", Material.BRICK, "&bBronze Ingot");
    /* Alloy (Copper + Aluminum) */
    public static final ItemStack DURALUMIN_INGOT = new SlimefunItemStack("DURALUMIN_INGOT", Material.IRON_INGOT, "&bDuralumin Ingot");
    /* Alloy (Copper + Silver) */
    public static final ItemStack BILLON_INGOT = new SlimefunItemStack("BILLON_INGOT", Material.IRON_INGOT, "&bBillon Ingot");
    /* Alloy (Copper + Zinc) */
    public static final ItemStack BRASS_INGOT = new SlimefunItemStack("BRASS_INGOT", Material.GOLD_INGOT, "&bBrass Ingot");
    /* Alloy (Aluminum + Brass) */
    public static final ItemStack ALUMINUM_BRASS_INGOT = new SlimefunItemStack("ALUMINUM_BRASS_INGOT", Material.GOLD_INGOT, "&bAluminum Brass Ingot");
    /* Alloy (Aluminum + Bronze) */
    public static final ItemStack ALUMINUM_BRONZE_INGOT = new SlimefunItemStack("ALUMINUM_BRONZE_INGOT", Material.GOLD_INGOT, "&bAluminum Bronze Ingot");
    /* Alloy (Gold + Silver + Copper) */
    public static final ItemStack CORINTHIAN_BRONZE_INGOT = new SlimefunItemStack("CORINTHIAN_BRONZE_INGOT", Material.GOLD_INGOT, "&bCorinthian Bronze Ingot");
    /* Alloy (Lead + Tin) */
    public static final ItemStack SOLDER_INGOT = new SlimefunItemStack("SOLDER_INGOT", Material.IRON_INGOT, "&bSolder Ingot");
    /* Alloy (Steel + Iron + Carbon) */
    public static final ItemStack DAMASCUS_STEEL_INGOT = new SlimefunItemStack("DAMASCUS_STEEL_INGOT", Material.IRON_INGOT, "&bDamascus Steel Ingot");
    /* Alloy (Damascus Steel + Duralumin + Compressed Carbon + Aluminium Bronze) */
    public static final ItemStack HARDENED_METAL_INGOT = new SlimefunItemStack("HARDENED_METAL_INGOT", Material.IRON_INGOT, "&b&lHardened Metal");
    /* Alloy (Hardened Metal + Corinthian Bronze + Solder + Billon + Damascus Steel) */
    public static final ItemStack REINFORCED_ALLOY_INGOT = new SlimefunItemStack("REINFORCED_ALLOY_INGOT", Material.IRON_INGOT, "&b&lReinforced Alloy Ingot");
    /* Alloy (Iron + Silicon) */
    public static final ItemStack FERROSILICON = new SlimefunItemStack("FERROSILICON", Material.IRON_INGOT, "&bFerrosilicon");
    /* Alloy (Iron + Gold) */
    public static final ItemStack GILDED_IRON = new SlimefunItemStack("GILDED_IRON", Material.GOLD_INGOT, "&6&lGilded Iron");
    /* Alloy (Redston + Ferrosilicon) */
    public static final ItemStack REDSTONE_ALLOY = new SlimefunItemStack("REDSTONE_ALLOY", Material.BRICK, "&cRedstone Alloy Ingot");
    /* Alloy (Iron + Copper) */
    public static final ItemStack NICKEL_INGOT = new SlimefunItemStack("NICKEL_INGOT", Material.IRON_INGOT, "&bNickel Ingot");
    /* Alloy (Nickel + Iron + Copper) */
    public static final ItemStack COBALT_INGOT = new SlimefunItemStack("COBALT_INGOT", Material.IRON_INGOT, "&9Cobalt Ingot");

    /* Gold */
    public static final ItemStack GOLD_4K = new SlimefunItemStack("GOLD_4K", Material.GOLD_INGOT, "&rGold Ingot &7(4-Carat)");
    public static final ItemStack GOLD_6K = new SlimefunItemStack("GOLD_6K", Material.GOLD_INGOT, "&rGold Ingot &7(6-Carat)");
    public static final ItemStack GOLD_8K = new SlimefunItemStack("GOLD_8K", Material.GOLD_INGOT, "&rGold Ingot &7(8-Carat)");
    public static final ItemStack GOLD_10K = new SlimefunItemStack("GOLD_10K", Material.GOLD_INGOT, "&rGold Ingot &7(10-Carat)");
    public static final ItemStack GOLD_12K = new SlimefunItemStack("GOLD_12K", Material.GOLD_INGOT, "&rGold Ingot &7(12-Carat)");
    public static final ItemStack GOLD_14K = new SlimefunItemStack("GOLD_14K", Material.GOLD_INGOT, "&rGold Ingot &7(14-Carat)");
    public static final ItemStack GOLD_16K = new SlimefunItemStack("GOLD_16K", Material.GOLD_INGOT, "&rGold Ingot &7(16-Carat)");
    public static final ItemStack GOLD_18K = new SlimefunItemStack("GOLD_18K", Material.GOLD_INGOT, "&rGold Ingot &7(18-Carat)");
    public static final ItemStack GOLD_20K = new SlimefunItemStack("GOLD_20K", Material.GOLD_INGOT, "&rGold Ingot &7(20-Carat)");
    public static final ItemStack GOLD_22K = new SlimefunItemStack("GOLD_22K", Material.GOLD_INGOT, "&rGold Ingot &7(22-Carat)");
    public static final ItemStack GOLD_24K = new SlimefunItemStack("GOLD_24K", Material.GOLD_INGOT, "&rGold Ingot &7(24-Carat)");

    /* Dusts */
    public static final ItemStack IRON_DUST = new SlimefunItemStack("IRON_DUST", Material.GUNPOWDER, "&6Iron Dust");
    public static final ItemStack GOLD_DUST = new SlimefunItemStack("GOLD_DUST", Material.GLOWSTONE_DUST, "&6Gold Dust");
    public static final ItemStack TIN_DUST = new SlimefunItemStack("TIN_DUST", Material.SUGAR, "&6Tin Dust");
    public static final ItemStack COPPER_DUST = new SlimefunItemStack("COPPER_DUST", Material.GLOWSTONE_DUST, "&6Copper Dust");
    public static final ItemStack SILVER_DUST = new SlimefunItemStack("SILVER_DUST", Material.SUGAR, "&6Silver Dust");
    public static final ItemStack ALUMINUM_DUST = new SlimefunItemStack("ALUMINUM_DUST", Material.SUGAR, "&6Aluminum Dust");
    public static final ItemStack LEAD_DUST = new SlimefunItemStack("LEAD_DUST", Material.GUNPOWDER, "&6Lead Dust");
    public static final ItemStack ZINC_DUST = new SlimefunItemStack("ZINC_DUST", Material.SUGAR, "&6Zinc Dust");
    public static final ItemStack MAGNESIUM_DUST = new SlimefunItemStack("MAGNESIUM_DUST", Material.SUGAR, "&6Magnesium");
    public static final ItemStack LAPIS_LAZULI_DUST = new SlimefunItemStack("LAPIS_LAZULI_DUST", Material.BLUE_DYE, "&6Lapis Lazuli Dust");

    public static final ItemStack SULFATE = new SlimefunItemStack("SULFATE", Material.GLOWSTONE_DUST, "&6Sulfate");
    public static final ItemStack SILICON = new SlimefunItemStack("SILICON", Material.FIREWORK_STAR, "&6Silicon");
    public static final ItemStack GOLD_24K_BLOCK = new SlimefunItemStack("GOLD_24K_BLOCK", Material.GOLD_BLOCK, "&rGold Block &7(24-Carat)");

    /* Gems */
    public static final ItemStack SYNTHETIC_DIAMOND = new SlimefunItemStack("SYNTHETIC_DIAMOND", Material.DIAMOND, "&bSynthetic Diamond", "", "&rThis item can be used in a", "&rworkbench and acts like a normal Diamond");
    public static final ItemStack SYNTHETIC_EMERALD = new SlimefunItemStack("SYNTHETIC_EMERALD", Material.EMERALD, "&bSynthetic Emerald", "", "&rThis item can be used to", "&rtrade with Villagers");
    public static final ItemStack SYNTHETIC_SAPPHIRE = new SlimefunItemStack("SYNTHETIC_SAPPHIRE", "e35032f4d7d01de8ec99d89f8723012d4e74fa73022c4facf1b57c7ff6ff0", "&bSynthetic Sapphire", "", "&rThis item can be used in a", "&rworkbench and acts like Lapis Lazuli");
    public static final ItemStack CARBONADO = new SlimefunItemStack("CARBONADO", "12f4b1577f5160c6893172571c4a71d8b321cdceaa032c6e0e3b60e0b328fa", "&b&lCarbonado", "", "&7&o\"Black Diamond\"");
    public static final ItemStack RAW_CARBONADO = new SlimefunItemStack("RAW_CARBONADO", "eb49e6ec10771e899225aea73cd8cf03684f411d1415c7323c93cb9476230", "&bRaw Carbonado");

    public static final ItemStack URANIUM = new SlimefunItemStack("URANIUM", "c8b29afa6d6dc923e2e1324bf8192750f7bdbddc689632a2b6c18d9fe7a5e", "&4Uranium", "", LoreBuilder.radioactive(Radioactivity.HIGH), LoreBuilder.HAZMAT_SUIT_REQUIRED);
    public static final ItemStack NEPTUNIUM = new SlimefunItemStack("NEPTUNIUM", "4edea6bfd37e49de43f154fe6fca617d4129e61b95759a3d49a15935a1c2dcf0", "&aNeptunium", "", LoreBuilder.radioactive(Radioactivity.HIGH), LoreBuilder.HAZMAT_SUIT_REQUIRED);
    public static final ItemStack PLUTONIUM = new SlimefunItemStack("PLUTONIUM", "25cf91b7388665a6d7c1b6026bdb2322c6d278997a44478677cbcc15f76124f", "&7Plutonium", "", LoreBuilder.radioactive(Radioactivity.VERY_HIGH), LoreBuilder.HAZMAT_SUIT_REQUIRED);
    public static final ItemStack BOOSTED_URANIUM = new SlimefunItemStack("BOOSTED_URANIUM", "6837ca12f222f4787196a17b8ab656985f8404c50767adbcb6e7f14254fee", "&2Boosted Uranium", "", LoreBuilder.radioactive(Radioactivity.VERY_HIGH), LoreBuilder.HAZMAT_SUIT_REQUIRED);

    /* Talisman */
    public static final SlimefunItemStack TALISMAN = new SlimefunItemStack("COMMON_TALISMAN", Material.EMERALD, "&6Common Talisman");
    public static final SlimefunItemStack ENDER_TALISMAN = new SlimefunItemStack("ENDER_TALISMAN", Material.EMERALD, "&5Ender Talisman");

    public static final SlimefunItemStack TALISMAN_ANVIL = new SlimefunItemStack("ANVIL_TALISMAN", Material.EMERALD, "&aTalisman of the Anvil", "", "&rEach Talisman can prevent", "&r1 Tool from breaking, but will then", "&rbe consumed", "", "&4&lWARNING:", "&4This Talisman does not work on", "&4Tools which are too powerful", "&4due to their complexity");
    public static final SlimefunItemStack TALISMAN_MINER = new SlimefunItemStack("MINER_TALISMAN", Material.EMERALD, "&aTalisman of the Miner", "", "&rWhile you have this Talisman", "&rin your Inventory it has", "&ra 20% chance of doubling", "&rall Ores you mine");
    public static final SlimefunItemStack TALISMAN_HUNTER = new SlimefunItemStack("HUNTER_TALISMAN", Material.EMERALD, "&aTalisman of the Hunter", "", "&rWhile you have this Talisman", "&rin your Inventory it has", "&ra 20% chance of doubling", "&rall Drops from Mobs you kill");
    public static final SlimefunItemStack TALISMAN_LAVA = new SlimefunItemStack("LAVA_TALISMAN", Material.EMERALD, "&aTalisman of the Lava Walker", "", "&rWhile you have this Talisman", "&rin your Inventory it will", "&rgive you Fire Resistance", "&ras soon as you touch Lava", "&rbut will then be consumed");
    public static final SlimefunItemStack TALISMAN_WATER = new SlimefunItemStack("WATER_TALISMAN", Material.EMERALD, "&aTalisman of the Water Breather", "", "&rWhile you have this Talisman", "&rin your Inventory it will", "&rgive you the ability", "&rto breath underwater as", "&rsoon as you start drowning", "&rbut will then be consumed");
    public static final SlimefunItemStack TALISMAN_ANGEL = new SlimefunItemStack("ANGEL_TALISMAN", Material.EMERALD, "&aTalisman of the Angel", "", "&rWhile you have this Talisman", "&rin your Inventory it has a", "&r75% chance to prevent you", "&rfrom taking Fall Damage");
    public static final SlimefunItemStack TALISMAN_FIRE = new SlimefunItemStack("FIRE_TALISMAN", Material.EMERALD, "&aTalisman of the Firefighter", "", "&rWhile you have this Talisman", "&rin your Inventory it will", "&rgive you Fire Resistance", "&ras soon as you start burning", "&rbut will then be consumed");
    public static final SlimefunItemStack TALISMAN_MAGICIAN = new SlimefunItemStack("MAGICIAN_TALISMAN", Material.EMERALD, "&aTalisman of the Magician", "", "&rWhile you have this Talisman", "&rin your Inventory it gives", "&ryou a 80% Luck Bonus on Enchanting", "&rYou will sometimes get an Extra Enchantment");
    public static final SlimefunItemStack TALISMAN_TRAVELLER = new SlimefunItemStack("TRAVELLER_TALISMAN", Material.EMERALD, "&aTalisman of the Traveller", "", "&rWhile you have this Talisman", "&rin your Inventory it gives", "&ryou a 60% Chance for a decent", "&rSpeed Buff when you start sprinting");
    public static final SlimefunItemStack TALISMAN_WARRIOR = new SlimefunItemStack("WARRIOR_TALISMAN", Material.EMERALD, "&aTalisman of the Warrior", "", "&rWhile you have this Talisman", "&rin your Inventory it gives", "&ryou Strength III whenever you get hit", "&rbut will then be consumed");
    public static final SlimefunItemStack TALISMAN_KNIGHT = new SlimefunItemStack("KNIGHT_TALISMAN", Material.EMERALD, "&aTalisman of the Knight", "", "&rWhile you have this Talisman", "&rin your Inventory it gives", "&ryou a 30% Chance for 5 Seconds of Regeneration", "&rwhenever You get hit", "&rbut will then be consumed");
    public static final SlimefunItemStack TALISMAN_WHIRLWIND = new SlimefunItemStack("WHIRLWIND_TALISMAN", Material.EMERALD, "&aTalisman of the Whirlwind", "", "&rWhile you have this Talisman", "&rin your Inventory it will reflect", "&r60% of all Projectiles fired at you");
    public static final SlimefunItemStack TALISMAN_WIZARD = new SlimefunItemStack("WIZARD_TALISMAN", Material.EMERALD, "&aTalisman of the Wizard", "", "&rWhile you have this Talisman", "&rin your Inventory it allows you to", "&robtain Fortune Level 4/5 however", "&rit also has a chance to lower the", "&rLevel of some Enchantments on your Item");

    /* Staves */
    public static final SlimefunItemStack STAFF_ELEMENTAL = new SlimefunItemStack("STAFF_ELEMENTAL", Material.STICK, "&6Elemental Staff");
    public static final SlimefunItemStack STAFF_WIND = new SlimefunItemStack("STAFF_ELEMENTAL_WIND", Material.STICK, "&6Elemental Staff &7- &b&oWind", "", "&7Element: &b&oWind", "", "&eRight Click&7 to launch yourself forward");
    public static final SlimefunItemStack STAFF_FIRE = new SlimefunItemStack("STAFF_ELEMENTAL_FIRE", Material.STICK, "&6Elemental Staff &7- &c&oFire", "", "&7Element: &c&oFire");
    public static final SlimefunItemStack STAFF_WATER = new SlimefunItemStack("STAFF_ELEMENTAL_WATER", Material.STICK, "&6Elemental Staff &7- &1&oWater", "", "&7Element: &1&oWater", "", "&eRight Click&7 to extinguish yourself");
    public static final SlimefunItemStack STAFF_STORM = new SlimefunItemStack("STAFF_ELEMENTAL_STORM", Material.STICK, "&6Elemental Staff &7- &8&oStorm", "", "&7Element: &8&oStorm", "", "&eRight Click&7 to summon a lightning", "&e" + StormStaff.MAX_USES + " Uses &7left");

    static {
        STAFF_WIND.addUnsafeEnchantment(Enchantment.LUCK, 1);
        STAFF_FIRE.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 5);
        STAFF_WATER.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
        STAFF_STORM.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
    }

    /* Machines */
    public static final SlimefunItemStack GRIND_STONE = new SlimefunItemStack("GRIND_STONE", Material.DISPENSER, "&bGrind Stone", "", "&aGrinds items down into other items");
    public static final SlimefunItemStack ARMOR_FORGE = new SlimefunItemStack("ARMOR_FORGE", Material.ANVIL, "&6Armor Forge", "", "&aGives you the ability to create powerful armor");
    public static final SlimefunItemStack MAKESHIFT_SMELTERY = new SlimefunItemStack("MAKESHIFT_SMELTERY", Material.BLAST_FURNACE, "&eMakeshift Smeltery", "", "&rImprovised version of the Smeltery", "&rthat only allows you to", "&rsmelt dusts into ingots");
    public static final SlimefunItemStack SMELTERY = new SlimefunItemStack("SMELTERY", Material.FURNACE, "&6Smeltery", "", "&rA high-temperature furnace", "&rthat allows you to smelt dusts", "&rinto ingots and create alloys.");
    public static final SlimefunItemStack IGNITION_CHAMBER = new SlimefunItemStack("IGNITION_CHAMBER", Material.DROPPER, "&4Automatic Ignition Chamber", "", "&rPrevents the Smeltery from using up fire.", "&rJust fill it up with \"Flint and Steel\"", "&rand place it adjacent to the Smeltery's dispenser");
    public static final SlimefunItemStack ORE_CRUSHER = new SlimefunItemStack("ORE_CRUSHER", Material.DISPENSER, "&bOre Crusher", "", "&aCrushes ores to double them");
    public static final SlimefunItemStack COMPRESSOR = new SlimefunItemStack("COMPRESSOR", Material.PISTON, "&bCompressor", "", "&aCompresses Items");
    public static final SlimefunItemStack PRESSURE_CHAMBER = new SlimefunItemStack("PRESSURE_CHAMBER", Material.GLASS, "&bPressure Chamber", "", "&aCompresses Items even further");
    public static final SlimefunItemStack MAGIC_WORKBENCH = new SlimefunItemStack("MAGIC_WORKBENCH", Material.CRAFTING_TABLE, "&6Magic Workbench", "", "&dInfuses Items with magical Energy");
    public static final SlimefunItemStack ORE_WASHER = new SlimefunItemStack("ORE_WASHER", Material.CAULDRON, "&6Ore Washer", "", "&aWashes Sifted Ore to filter Ores", "&aand gives you small Stone Chunks");
    public static final SlimefunItemStack TABLE_SAW = new SlimefunItemStack("TABLE_SAW", Material.STONECUTTER, "&6Table Saw", "", "&aAllows you to get 8 planks from 1 Log", "&a(Works with all log types)");
    public static final SlimefunItemStack COMPOSTER = new SlimefunItemStack("COMPOSTER", Material.CAULDRON, "&aComposter", "", "&a&oCan convert various Materials over Time...");
    public static final SlimefunItemStack ENHANCED_CRAFTING_TABLE = new SlimefunItemStack("ENHANCED_CRAFTING_TABLE", Material.CRAFTING_TABLE, "&eEnhanced Crafting Table", "", "&aA regular Crafting Table cannot", "&ahold this massive Amount of Power...");
    public static final SlimefunItemStack CRUCIBLE = new SlimefunItemStack("CRUCIBLE", Material.CAULDRON, "&cCrucible", "", "&a&oUsed to smelt Items into Liquids");
    public static final SlimefunItemStack JUICER = new SlimefunItemStack("JUICER", Material.GLASS_BOTTLE, "&aJuicer", "", "&aAllows you to create delicious Juice");

    public static final ItemStack SOLAR_PANEL = new SlimefunItemStack("SOLAR_PANEL", Material.DAYLIGHT_DETECTOR, "&bSolar Panel", "", "&a&oTransforms Sunlight to Energy");

    // @Deprecated
    // public static final ItemStack DIGITAL_MINER = new CustomItem(Material.IRON_PICKAXE, "&bDigital Miner", "", "&4DEPRECATED", "&cThis machine will be removed at some point!", "&cWe don't know when.");

    // @Deprecated
    // public static final ItemStack ADVANCED_DIGITAL_MINER = new CustomItem(Material.DIAMOND_PICKAXE, "&6Advanced Digital Miner", "", "&4DEPRECATED", "&cThis machine will be removed at some point!", "&cWe don't know when.");

    public static final SlimefunItemStack AUTOMATED_PANNING_MACHINE = new SlimefunItemStack("AUTOMATED_PANNING_MACHINE", Material.BOWL, "&eAutomated Panning Machine", "", "&rA MultiBlock Version of the Gold Pan", "&rand Nether Gold Pan combined in one machine.");
    public static final SlimefunItemStack OUTPUT_CHEST = new SlimefunItemStack("OUTPUT_CHEST", Material.CHEST, "&4Output Chest", "", "&c&oA basic machine will try to put", "&c&oitems in this chest if it's placed", "&c&oadjacent to the dispenser.");
    public static final SlimefunItemStack HOLOGRAM_PROJECTOR = new SlimefunItemStack("HOLOGRAM_PROJECTOR", Material.QUARTZ_SLAB, "&bHologram Projector", "", "&rProjects an Editable Hologram");

    /* Enhanced Furnaces */
    public static final SlimefunItemStack ENHANCED_FURNACE = new SlimefunItemStack("ENHANCED_FURNACE", Material.FURNACE, "&7Enhanced Furnace - &eI", "", "&7Processing Speed: &e1x", "&7Fuel Efficiency: &e1x", "&7Luck Multiplier: &e1x");
    public static final SlimefunItemStack ENHANCED_FURNACE_2 = new SlimefunItemStack("ENHANCED_FURNACE_2", Material.FURNACE, "&7Enhanced Furnace - &eII", "", "&7Processing Speed: &e2x", "&7Fuel Efficiency: &e1x", "&7Luck Multiplier: &e1x");
    public static final SlimefunItemStack ENHANCED_FURNACE_3 = new SlimefunItemStack("ENHANCED_FURNACE_3", Material.FURNACE, "&7Enhanced Furnace - &eIII", "", "&7Processing Speed: &e2x", "&7Fuel Efficiency: &e2x", "&7Luck Multiplier: &e1x");
    public static final SlimefunItemStack ENHANCED_FURNACE_4 = new SlimefunItemStack("ENHANCED_FURNACE_4", Material.FURNACE, "&7Enhanced Furnace - &eIV", "", "&7Processing Speed: &e3x", "&7Fuel Efficiency: &e2x", "&7Luck Multiplier: &e1x");
    public static final SlimefunItemStack ENHANCED_FURNACE_5 = new SlimefunItemStack("ENHANCED_FURNACE_5", Material.FURNACE, "&7Enhanced Furnace - &eV", "", "&7Processing Speed: &e3x", "&7Fuel Efficiency: &e2x", "&7Luck Multiplier: &e2x");
    public static final SlimefunItemStack ENHANCED_FURNACE_6 = new SlimefunItemStack("ENHANCED_FURNACE_6", Material.FURNACE, "&7Enhanced Furnace - &eVI", "", "&7Processing Speed: &e3x", "&7Fuel Efficiency: &e3x", "&7Luck Multiplier: &e2x");
    public static final SlimefunItemStack ENHANCED_FURNACE_7 = new SlimefunItemStack("ENHANCED_FURNACE_7", Material.FURNACE, "&7Enhanced Furnace - &eVII", "", "&7Processing Speed: &e4x", "&7Fuel Efficiency: &e3x", "&7Luck Multiplier: &e2x");
    public static final SlimefunItemStack ENHANCED_FURNACE_8 = new SlimefunItemStack("ENHANCED_FURNACE_8", Material.FURNACE, "&7Enhanced Furnace - &eVIII", "", "&7Processing Speed: &e4x", "&7Fuel Efficiency: &e4x", "&7Luck Multiplier: &e2x");
    public static final SlimefunItemStack ENHANCED_FURNACE_9 = new SlimefunItemStack("ENHANCED_FURNACE_9", Material.FURNACE, "&7Enhanced Furnace - &eIX", "", "&7Processing Speed: &e5x", "&7Fuel Efficiency: &e4x", "&7Luck Multiplier: &e2x");
    public static final SlimefunItemStack ENHANCED_FURNACE_10 = new SlimefunItemStack("ENHANCED_FURNACE_10", Material.FURNACE, "&7Enhanced Furnace - &eX", "", "&7Processing Speed: &e5x", "&7Fuel Efficiency: &e5x", "&7Luck Multiplier: &e2x");
    public static final SlimefunItemStack ENHANCED_FURNACE_11 = new SlimefunItemStack("ENHANCED_FURNACE_11", Material.FURNACE, "&7Enhanced Furnace - &eXI", "", "&7Processing Speed: &e5x", "&7Fuel Efficiency: &e5x", "&7Luck Multiplier: &e3x");
    public static final SlimefunItemStack REINFORCED_FURNACE = new SlimefunItemStack("REINFORCED_FURNACE", Material.FURNACE, "&7Reinforced Furnace", "", "&7Processing Speed: &e10x", "&7Fuel Efficiency: &e10x", "&7Luck Multiplier: &e3x");
    public static final SlimefunItemStack CARBONADO_EDGED_FURNACE = new SlimefunItemStack("CARBONADO_EDGED_FURNACE", Material.FURNACE, "&7Carbonado Edged Furnace", "", "&7Processing Speed: &e20x", "&7Fuel Efficiency: &e10x", "&7Luck Multiplier: &e3x");

    public static final SlimefunItemStack BLOCK_PLACER = new SlimefunItemStack("BLOCK_PLACER", Material.DISPENSER, "&aBlock Placer", "", "&rAll Blocks in this Dispenser", "&rwill automatically get placed");

    /* Soulbound Items */
    public static final SlimefunItemStack SOULBOUND_SWORD = new SlimefunItemStack("SOULBOUND_SWORD", Material.DIAMOND_SWORD, "&cSoulbound Sword");
    public static final SlimefunItemStack SOULBOUND_BOW = new SlimefunItemStack("SOULBOUND_BOW", Material.BOW, "&cSoulbound Bow");
    public static final SlimefunItemStack SOULBOUND_PICKAXE = new SlimefunItemStack("SOULBOUND_PICKAXE", Material.DIAMOND_PICKAXE, "&cSoulbound Pickaxe");
    public static final SlimefunItemStack SOULBOUND_AXE = new SlimefunItemStack("SOULBOUND_AXE", Material.DIAMOND_AXE, "&cSoulbound Axe");
    public static final SlimefunItemStack SOULBOUND_SHOVEL = new SlimefunItemStack("SOULBOUND_SHOVEL", Material.DIAMOND_SHOVEL, "&cSoulbound Shovel");
    public static final SlimefunItemStack SOULBOUND_HOE = new SlimefunItemStack("SOULBOUND_HOE", Material.DIAMOND_HOE, "&cSoulbound Hoe");
    public static final SlimefunItemStack SOULBOUND_TRIDENT = new SlimefunItemStack("SOULBOUND_TRIDENT", Material.TRIDENT, "&cSoulbound Trident");

    public static final SlimefunItemStack SOULBOUND_HELMET = new SlimefunItemStack("SOULBOUND_HELMET", Material.DIAMOND_HELMET, "&cSoulbound Helmet");
    public static final SlimefunItemStack SOULBOUND_CHESTPLATE = new SlimefunItemStack("SOULBOUND_CHESTPLATE", Material.DIAMOND_CHESTPLATE, "&cSoulbound Chestplate");
    public static final SlimefunItemStack SOULBOUND_LEGGINGS = new SlimefunItemStack("SOULBOUND_LEGGINGS", Material.DIAMOND_LEGGINGS, "&cSoulbound Leggings");
    public static final SlimefunItemStack SOULBOUND_BOOTS = new SlimefunItemStack("SOULBOUND_BOOTS", Material.DIAMOND_BOOTS, "&cSoulbound Boots");

    /* Runes */
    public static final ItemStack BLANK_RUNE = new SlimefunItemStack("BLANK_RUNE", new ColoredFireworkStar(Color.BLACK, "&8Blank Rune"));

    public static final ItemStack RUNE_AIR = new SlimefunItemStack("ANCIENT_RUNE_AIR", new ColoredFireworkStar(Color.AQUA, "&7Ancient Rune &8&l[&b&lAir&8&l]"));
    public static final ItemStack RUNE_WATER = new SlimefunItemStack("ANCIENT_RUNE_WATER", new ColoredFireworkStar(Color.BLUE, "&7Ancient Rune &8&l[&1&lWater&8&l]"));
    public static final ItemStack RUNE_FIRE = new SlimefunItemStack("ANCIENT_RUNE_FIRE", new ColoredFireworkStar(Color.RED, "&7Ancient Rune &8&l[&4&lFire&8&l]"));
    public static final ItemStack RUNE_EARTH = new SlimefunItemStack("ANCIENT_RUNE_EARTH", new ColoredFireworkStar(Color.fromRGB(112, 47, 7), "&7Ancient Rune &8&l[&c&lEarth&8&l]"));
    public static final ItemStack RUNE_ENDER = new SlimefunItemStack("ANCIENT_RUNE_ENDER", new ColoredFireworkStar(Color.PURPLE, "&7Ancient Rune &8&l[&5&lEnder&8&l]"));

    public static final ItemStack RUNE_RAINBOW = new SlimefunItemStack("ANCIENT_RUNE_RAINBOW", new ColoredFireworkStar(Color.FUCHSIA, "&7Ancient Rune &8&l[&d&lRainbow&8&l]"));
    public static final ItemStack RUNE_LIGHTNING = new SlimefunItemStack("ANCIENT_RUNE_LIGHTNING", new ColoredFireworkStar(Color.fromRGB(255, 255, 95), "&7Ancient Rune &8&l[&e&lLightning&8&l]"));
    public static final ItemStack RUNE_SOULBOUND = new SlimefunItemStack("ANCIENT_RUNE_SOULBOUND", new ColoredFireworkStar(Color.fromRGB(47, 0, 117), "&7Ancient Rune &8&l[&5&lSoulbound&8&l]", "&eDrop this rune onto a dropped item to", "&5bind &ethat item to your soul.", " ", "&eIt is advised that you only use this rune", "&eon &6important &eitems.", " ", "&eItems bound to your soul won't drop on death."));

    /* Electricity */
    public static final ItemStack SOLAR_GENERATOR = new SlimefunItemStack("SOLAR_GENERATOR", Material.DAYLIGHT_DETECTOR, "&bSolar Generator", "", LoreBuilder.machine(MachineTier.BASIC, MachineType.GENERATOR), LoreBuilder.powerBuffer(0), LoreBuilder.powerPerSecond(4));
    public static final ItemStack SOLAR_GENERATOR_2 = new SlimefunItemStack("SOLAR_GENERATOR_2", Material.DAYLIGHT_DETECTOR, "&cAdvanced Solar Generator", "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.GENERATOR), LoreBuilder.powerBuffer(0), LoreBuilder.powerPerSecond(16));
    public static final ItemStack SOLAR_GENERATOR_3 = new SlimefunItemStack("SOLAR_GENERATOR_3", Material.DAYLIGHT_DETECTOR, "&4Carbonado Solar Generator", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.GENERATOR), LoreBuilder.powerBuffer(0), LoreBuilder.powerPerSecond(64));
    public static final ItemStack SOLAR_GENERATOR_4 = new SlimefunItemStack("SOLAR_GENERATOR_4", Material.DAYLIGHT_DETECTOR, "&eEnergized Solar Generator", "", "&9Works at Night", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.GENERATOR), LoreBuilder.powerBuffer(0), LoreBuilder.powerPerSecond(256) + " (Day)", LoreBuilder.powerPerSecond(128) + " (Night)");
    public static final ItemStack SOLAR_GENERATOR_5 = new SlimefunItemStack("SOLAR_GENERATOR_5", Material.DAYLIGHT_DETECTOR, "&eNether Solar Generator", "", "&9Works and only works in Nether", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.GENERATOR), LoreBuilder.powerBuffer(0), LoreBuilder.powerPerSecond(1024) + " (Day)", LoreBuilder.powerPerSecond(512) + " (Night)");


    public static final ItemStack COAL_GENERATOR = new SlimefunItemStack("COAL_GENERATOR", "9343ce58da54c79924a2c9331cfc417fe8ccbbea9be45a7ac85860a6c730", "&cCoal Generator", "", LoreBuilder.machine(MachineTier.AVERAGE, MachineType.GENERATOR), LoreBuilder.powerBuffer(64), LoreBuilder.powerPerSecond(16));
    public static final ItemStack COAL_GENERATOR_2 = new SlimefunItemStack("COAL_GENERATOR_2", "9343ce58da54c79924a2c9331cfc417fe8ccbbea9be45a7ac85860a6c730", "&cCoal Generator &7(&eII&7)", "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.GENERATOR), LoreBuilder.powerBuffer(256), LoreBuilder.powerPerSecond(30));

    public static final ItemStack LAVA_GENERATOR = new SlimefunItemStack("LAVA_GENERATOR", "9343ce58da54c79924a2c9331cfc417fe8ccbbea9be45a7ac85860a6c730", "&4Lava Generator", "", LoreBuilder.machine(MachineTier.AVERAGE, MachineType.GENERATOR), LoreBuilder.powerBuffer(512), LoreBuilder.powerPerSecond(20));
    public static final ItemStack LAVA_GENERATOR_2 = new SlimefunItemStack("LAVA_GENERATOR_2", "9343ce58da54c79924a2c9331cfc417fe8ccbbea9be45a7ac85860a6c730", "&4Lava Generator &7(&eII&7)", "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.GENERATOR), LoreBuilder.powerBuffer(1024), LoreBuilder.powerPerSecond(40));

    public static final ItemStack ELECTRIC_FURNACE = new SlimefunItemStack("ELECTRIC_FURNACE", Material.FURNACE, "&cElectric Furnace", "", LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerPerSecond(4));
    public static final ItemStack ELECTRIC_FURNACE_2 = new SlimefunItemStack("ELECTRIC_FURNACE_2", Material.FURNACE, "&cElectric Furnace &7- &eII", "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.MACHINE), "&8\u21E8 &7Speed: 2x", LoreBuilder.powerPerSecond(6));
    public static final ItemStack ELECTRIC_FURNACE_3 = new SlimefunItemStack("ELECTRIC_FURNACE_3", Material.FURNACE, "&cElectric Furnace &7- &eIII", "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.MACHINE), "&8\u21E8 &7Speed: 4x", LoreBuilder.powerPerSecond(10));

    public static final ItemStack ELECTRIC_ORE_GRINDER = new SlimefunItemStack("ELECTRIC_ORE_GRINDER", Material.FURNACE, "&cElectric Ore Grinder", "", "&rWorks as an Ore Crusher and Grind Stone", "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerPerSecond(12));
    public static final ItemStack ELECTRIC_ORE_GRINDER_2 = new SlimefunItemStack("ELECTRIC_ORE_GRINDER_2", Material.FURNACE, "&cElectric Ore Grinder &7(&eII&7)", "", "&rWorks as an Ore Crusher and Grind Stone", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 4x", LoreBuilder.powerPerSecond(30));
    public static final ItemStack ELECTRIC_ORE_GRINDER_3 = new SlimefunItemStack("ELECTRIC_ORE_GRINDER_3", Material.FURNACE, "&cElectric Ore Grinder &7(&eIII&7)", "", "&rWorks as an Ore Crusher and Grind Stone", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 10x", LoreBuilder.powerPerSecond(80));
    public static final ItemStack ELECTRIC_INGOT_PULVERIZER = new SlimefunItemStack("ELECTRIC_INGOT_PULVERIZER", Material.FURNACE, "&cElectric Ingot Pulverizer", "", "&rPulverizes Ingots into Dust", "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerPerSecond(14));
    public static final ItemStack AUTO_DRIER = new SlimefunItemStack("AUTO_DRIER", Material.SMOKER, "&eAuto Drier", "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerPerSecond(10));
    public static final ItemStack AUTO_ENCHANTER = new SlimefunItemStack("AUTO_ENCHANTER", Material.ENCHANTING_TABLE, "&5Auto Enchanter", "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerPerSecond(18));
    public static final ItemStack AUTO_DISENCHANTER = new SlimefunItemStack("AUTO_DISENCHANTER", Material.ENCHANTING_TABLE, "&5Auto Disenchanter", "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerPerSecond(18));
    public static final ItemStack AUTO_ANVIL = new SlimefunItemStack("AUTO_ANVIL", Material.IRON_BLOCK, "&7Auto Anvil", "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), "&8\u21E8 &7Repair Factor: 10%", LoreBuilder.powerPerSecond(24));
    public static final ItemStack AUTO_ANVIL_2 = new SlimefunItemStack("AUTO_ANVIL_2", Material.IRON_BLOCK, "&7Auto Anvil Mk.II", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Repair Factor: 25%", LoreBuilder.powerPerSecond(32));

    public static final ItemStack BIO_REACTOR = new SlimefunItemStack("BIO_REACTOR", Material.LIME_TERRACOTTA, "&2Bio Reactor", "", LoreBuilder.machine(MachineTier.AVERAGE, MachineType.GENERATOR), LoreBuilder.powerBuffer(128), LoreBuilder.powerPerSecond(8));
    public static final ItemStack MULTIMETER = new SlimefunItemStack("MULTIMETER", Material.CLOCK, "&eMultimeter", "", "&rMeasures the Amount of stored", "&rEnergy in a Block");

    public static final ItemStack SMALL_CAPACITOR = new SlimefunItemStack("SMALL_CAPACITOR", "91361e576b493cbfdfae328661cedd1add55fab4e5eb418b92cebf6275f8bb4", "&aSmall Energy Capacitor", "", LoreBuilder.machine(MachineTier.BASIC, MachineType.CAPACITOR), "&8\u21E8 &e\u26A1 &7128 J Capacity");
    public static final ItemStack MEDIUM_CAPACITOR = new SlimefunItemStack("MEDIUM_CAPACITOR", "91361e576b493cbfdfae328661cedd1add55fab4e5eb418b92cebf6275f8bb4", "&aMedium Energy Capacitor", "", LoreBuilder.machine(MachineTier.AVERAGE, MachineType.CAPACITOR), "&8\u21E8 &e\u26A1 &7512 J Capacity");
    public static final ItemStack BIG_CAPACITOR = new SlimefunItemStack("BIG_CAPACITOR", "91361e576b493cbfdfae328661cedd1add55fab4e5eb418b92cebf6275f8bb4", "&aBig Energy Capacitor", "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.CAPACITOR), "&8\u21E8 &e\u26A1 &71024 J Capacity");
    public static final ItemStack LARGE_CAPACITOR = new SlimefunItemStack("LARGE_CAPACITOR", "91361e576b493cbfdfae328661cedd1add55fab4e5eb418b92cebf6275f8bb4", "&aLarge Energy Capacitor", "", LoreBuilder.machine(MachineTier.GOOD, MachineType.CAPACITOR), "&8\u21E8 &e\u26A1 &78192 J Capacity");
    public static final ItemStack CARBONADO_EDGED_CAPACITOR = new SlimefunItemStack("CARBONADO_EDGED_CAPACITOR", "91361e576b493cbfdfae328661cedd1add55fab4e5eb418b92cebf6275f8bb4", "&aCarbonado Edged Energy Capacitor", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.CAPACITOR), "&8\u21E8 &e\u26A1 &765536 J Capacity");

    /* Robots */
    public static final ItemStack PROGRAMMABLE_ANDROID = new SlimefunItemStack("PROGRAMMABLE_ANDROID", "3503cb7ed845e7a507f569afc647c47ac483771465c9a679a54594c76afba", "&cProgrammable Android &7(Normal)", "", "&8\u21E8 &7Function: None", "&8\u21E8 &7Fuel Efficiency: 1.0x");
    public static final ItemStack PROGRAMMABLE_ANDROID_FARMER = new SlimefunItemStack("PROGRAMMABLE_ANDROID_FARMER", "f9d33357e8418823bf783de92de80291b4ebd392aec8706698e06896d498f6", "&cProgrammable Android &7(Farmer)", "", "&8\u21E8 &7Function: Farming", "&8\u21E8 &7Fuel Efficiency: 1.0x");
    public static final ItemStack PROGRAMMABLE_ANDROID_MINER = new SlimefunItemStack("PROGRAMMABLE_ANDROID_MINER", "e638a28541ab3ae0a723d5578738e08758388ec4c33247bd4ca13482aef334", "&cProgrammable Android &7(Miner)", "", "&8\u21E8 &7Function: Mining", "&8\u21E8 &7Fuel Efficiency: 1.0x");
    public static final ItemStack PROGRAMMABLE_ANDROID_WOODCUTTER = new SlimefunItemStack("PROGRAMMABLE_ANDROID_WOODCUTTER", "d32a814510142205169a1ad32f0a745f18e9cb6c66ee64eca2e65babdef9ff", "&cProgrammable Android &7(Woodcutter)", "", "&8\u21E8 &7Function: Woodcutting", "&8\u21E8 &7Fuel Efficiency: 1.0x");
    public static final ItemStack PROGRAMMABLE_ANDROID_BUTCHER = new SlimefunItemStack("PROGRAMMABLE_ANDROID_BUTCHER", "3b472df0ad9a3be88f2e5d5d422d02b116d64d8df1475ed32e546afc84b31", "&cProgrammable Android &7(Butcher)", "", "&8\u21E8 &7Function: Slaughtering", "&8\u21E8 &7Damage: 4", "&8\u21E8 &7Fuel Efficiency: 1.0x");
    public static final ItemStack PROGRAMMABLE_ANDROID_FISHERMAN = new SlimefunItemStack("PROGRAMMABLE_ANDROID_FISHERMAN", "345e8733a73114333b98b3601751241722f4713e1a1a5d36fbb132493f1c7", "&cProgrammable Android &7(Fisherman)", "", "&8\u21E8 &7Function: Fishing", "&8\u21E8 &7Success Rate: 10%", "&8\u21E8 &7Fuel Efficiency: 1.0x");

    public static final ItemStack PROGRAMMABLE_ANDROID_2 = new SlimefunItemStack("PROGRAMMABLE_ANDROID_2", "3503cb7ed845e7a507f569afc647c47ac483771465c9a679a54594c76afba", "&cAdvanced Programmable Android &7(Normal)", "", "&8\u21E8 &7Function: None", "&8\u21E8 &7Fuel Efficiency: 1.5x");
    public static final ItemStack PROGRAMMABLE_ANDROID_2_FISHERMAN = new SlimefunItemStack("PROGRAMMABLE_ANDROID_2_FISHERMAN", "345e8733a73114333b98b3601751241722f4713e1a1a5d36fbb132493f1c7", "&cAdvanced Programmable Android &7(Fisherman)", "", "&8\u21E8 &7Function: Fishing", "&8\u21E8 &7Success Rate: 20%", "&8\u21E8 &7Fuel Efficiency: 1.5x");
    public static final ItemStack PROGRAMMABLE_ANDROID_2_FARMER = new SlimefunItemStack("PROGRAMMABLE_ANDROID_2_FARMER", "f9d33357e8418823bf783de92de80291b4ebd392aec8706698e06896d498f6", "&cAdvanced Programmable Android &7(Farmer)", "", "&8\u21E8 &7Function: Farming", "&8\u21E8 &7Fuel Efficiency: 1.5x", "&8\u21E8 &7Can also harvest Plants from ExoticGarden");
    public static final ItemStack PROGRAMMABLE_ANDROID_2_BUTCHER = new SlimefunItemStack("PROGRAMMABLE_ANDROID_2_BUTCHER", "3b472df0ad9a3be88f2e5d5d422d02b116d64d8df1475ed32e546afc84b31", "&cAdvanced Programmable Android &7(Butcher)", "", "&8\u21E8 &7Function: Slaughtering", "&8\u21E8 &7Damage: 8", "&8\u21E8 &7Fuel Efficiency: 1.5x");

    public static final ItemStack PROGRAMMABLE_ANDROID_3 = new SlimefunItemStack("PROGRAMMABLE_ANDROID_3", "3503cb7ed845e7a507f569afc647c47ac483771465c9a679a54594c76afba", "&eEmpowered Programmable Android &7(Normal)", "", "&8\u21E8 &7Function: None", "&8\u21E8 &7Fuel Efficiency: 3.0x");
    public static final ItemStack PROGRAMMABLE_ANDROID_3_FISHERMAN = new SlimefunItemStack("PROGRAMMABLE_ANDROID_3_FISHERMAN", "345e8733a73114333b98b3601751241722f4713e1a1a5d36fbb132493f1c7", "&eEmpowered Programmable Android &7(Fisherman)", "", "&8\u21E8 &7Function: Fishing", "&8\u21E8 &7Success Rate: 30%", "&8\u21E8 &7Fuel Efficiency: 8.0x");
    public static final ItemStack PROGRAMMABLE_ANDROID_3_BUTCHER = new SlimefunItemStack("PROGRAMMABLE_ANDROID_3_BUTCHER", "3b472df0ad9a3be88f2e5d5d422d02b116d64d8df1475ed32e546afc84b31", "&eEmpowered Programmable Android &7(Butcher)", "", "&8\u21E8 &7Function: Slaughtering", "&8\u21E8 &7Damage: 20", "&8\u21E8 &7Fuel Efficiency: 8.0x");

    /* GPS */
    public static final ItemStack GPS_TRANSMITTER = new SlimefunItemStack("GPS_TRANSMITTER", "b0c9c1a022f40b73f14b4cba37c718c6a533f3a2864b6536d5f456934cc1f", "&bGPS Transmitter", "", LoreBuilder.powerBuffer(16), LoreBuilder.powerPerSecond(2));
    public static final ItemStack GPS_TRANSMITTER_2 = new SlimefunItemStack("GPS_TRANSMITTER_2", "b0c9c1a022f40b73f14b4cba37c718c6a533f3a2864b6536d5f456934cc1f", "&cAdvanced GPS Transmitter", "", LoreBuilder.powerBuffer(64), LoreBuilder.powerPerSecond(6));
    public static final ItemStack GPS_TRANSMITTER_3 = new SlimefunItemStack("GPS_TRANSMITTER_3", "b0c9c1a022f40b73f14b4cba37c718c6a533f3a2864b6536d5f456934cc1f", "&4Carbonado GPS Transmitter", "", LoreBuilder.powerBuffer(256), LoreBuilder.powerPerSecond(22));
    public static final ItemStack GPS_TRANSMITTER_4 = new SlimefunItemStack("GPS_TRANSMITTER_4", "b0c9c1a022f40b73f14b4cba37c718c6a533f3a2864b6536d5f456934cc1f", "&eEnergized GPS Transmitter", "", LoreBuilder.powerBuffer(1024), LoreBuilder.powerPerSecond(92));

    public static final ItemStack GPS_MARKER_TOOL = new SlimefunItemStack("GPS_MARKER_TOOL", Material.REDSTONE_TORCH, "&bGPS Marker Tool", "", "&rAllows you to set a Waypoint at", "&rthe Location you place this");
    public static final ItemStack GPS_CONTROL_PANEL = new SlimefunItemStack("GPS_CONTROL_PANEL", "ddcfba58faf1f64847884111822b64afa21d7fc62d4481f14f3f3bcb6330", "&bGPS Control Panel", "", "&rAllows you to track your Satellites", "&rand manage your Waypoints");
    public static final ItemStack GPS_EMERGENCY_TRANSMITTER = new SlimefunItemStack("GPS_EMERGENCY_TRANSMITTER", "b0c9c1a022f40b73f14b4cba37c718c6a533f3a2864b6536d5f456934cc1f", "&cGPS Emergency Transmitter", "", "&rCarrying this in your Inventory", "&rautomatically sets a Waypoint", "&rat your Location when you die.");

    public static final ItemStack ANDROID_INTERFACE_FUEL = new SlimefunItemStack("ANDROID_INTERFACE_FUEL", Material.DISPENSER, "&7Android Interface &c(Fuel)", "", "&rItems stored in this Interface", "&rwill be inserted into an Android's Fuel Slot", "&rwhen its Script tells them to do so");
    public static final ItemStack ANDROID_INTERFACE_ITEMS = new SlimefunItemStack("ANDROID_INTERFACE_ITEMS", Material.DISPENSER, "&7Android Interface &9(Items)", "", "&rItems stored in an Android's Inventory", "&rwill be inserted into this Interface", "&rwhen its Script tells them to do so");

    public static final ItemStack GPS_GEO_SCANNER = new SlimefunItemStack("GPS_GEO_SCANNER", "2ad8cfeb387a56e3e5bcf85345d6a417b242293887db3ce3ba91fa409b254b86", "&bGPS Geo-Scanner", "", "&rScans a Chunk for natural Resources", "&rsuch as &8Oil");
    public static final ItemStack PORTABLE_GEO_SCANNER = new SlimefunItemStack("PORTABLE_GEO_SCANNER", Material.CLOCK, "&bPortable Geo-Scanner", "", "&rScans a Chunk for natural Resources", "", "&eRight Click&7 to scan");
    public static final ItemStack GEO_MINER = new SlimefunItemStack("GEO_MINER", "a37741f764dd3dd7adaeb43b63d3959eb70e5eb28f15d6b34cab34a1d1f60387", "&6GEO Miner", "", "&eMines up resources from the chunk", "&eThese Resources cannot be mined with a pickaxe", "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerPerSecond(48), "", "&c&l! &cMake sure to Geo-Scan the Chunk first");
    public static final ItemStack OIL_PUMP = new SlimefunItemStack("OIL_PUMP", "afe1a040a425e31a46d4f9a9b9806fa2f0c47ee84711cc1932fd8ab32b2d038", "&rOil Pump", "", "&7Pumps up Oil and fills it into Buckets", "", "&c&l! &cMake sure to Geo-Scan the Chunk first");
    public static final ItemStack BUCKET_OF_OIL = new SlimefunItemStack("BUCKET_OF_OIL", "6ce04b41d19ec7927f982a63a94a3d79f78ecec33363051fde0831bfabdbd", "&rBucket of Oil");
    public static final ItemStack BUCKET_OF_FUEL = new SlimefunItemStack("BUCKET_OF_FUEL", "a84ddca766725b8b97413f259c3f7668070f6ae55483a90c8e5525394f9c099", "&rBucket of Fuel");

    public static final ItemStack REFINERY = new SlimefunItemStack("REFINERY", Material.PISTON, "&cRefinery", "", "&rRefines Oil to create Fuel");
    public static final ItemStack COMBUSTION_REACTOR = new SlimefunItemStack("COMBUSTION_REACTOR", "9343ce58da54c79924a2c9331cfc417fe8ccbbea9be45a7ac85860a6c730", "&cCombustion Reactor", "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.GENERATOR), LoreBuilder.powerBuffer(256), LoreBuilder.powerPerSecond(24));
    public static final ItemStack ANDROID_MEMORY_CORE = new SlimefunItemStack("ANDROID_MEMORY_CORE", "d78f2b7e5e75639ea7fb796c35d364c4df28b4243e66b76277aadcd6261337", "&bAndroid Memory Core");

    public static final ItemStack GPS_TELEPORTER_PYLON = new SlimefunItemStack("GPS_TELEPORTER_PYLON", Material.PURPLE_STAINED_GLASS, "&5GPS Teleporter Pylon", "", "&7Teleporter Component");
    public static final ItemStack GPS_TELEPORTATION_MATRIX = new SlimefunItemStack("GPS_TELEPORTATION_MATRIX", Material.IRON_BLOCK, "&bGPS Teleporter Matrix", "", "&rThis is your Teleporter's Main Component", "&rThis Matrix allows Players to choose from all", "&rWaypoints made by the Player who has placed", "&rthis Device.");
    public static final ItemStack GPS_ACTIVATION_DEVICE_SHARED = new SlimefunItemStack("GPS_ACTIVATION_DEVICE_SHARED", Material.STONE_PRESSURE_PLATE, "&rGPS Activation Device &3(Shared)", "", "&rPlace this onto a Teleportation Matrix", "&rand step onto this Plate to activate", "&rthe Teleportation Process");
    public static final ItemStack GPS_ACTIVATION_DEVICE_PERSONAL = new SlimefunItemStack("GPS_ACTIVATION_DEVICE_PERSONAL", Material.STONE_PRESSURE_PLATE, "&rGPS Activation Device &a(Personal)", "", "&rPlace this onto a Teleportation Matrix", "&rand step onto this Plate to activate", "&rthe Teleportation Process", "", "&rThis Version only allows the Person who", "&rplaced this Device to use it");

    public static final ItemStack ELEVATOR = new SlimefunItemStack("ELEVATOR_PLATE", Material.STONE_PRESSURE_PLATE, "&bElevator Plate", "", "&rPlace an Elevator Plate on every floor", "&rand you will be able to teleport between them.", "", "&eRight Click this Block &7to name it");

    public static final ItemStack INFUSED_HOPPER = new SlimefunItemStack("INFUSED_HOPPER", Material.HOPPER, "&5Infused Hopper", "", "&rAutomatically picks up nearby Items in a 7x7x7", "&rRadius when placed.");

    public static final ItemStack PLASTIC_SHEET = new SlimefunItemStack("PLASTIC_SHEET", Material.PAPER, "&rPlastic Sheet");

    public static final ItemStack HEATED_PRESSURE_CHAMBER = new SlimefunItemStack("HEATED_PRESSURE_CHAMBER", Material.LIGHT_GRAY_STAINED_GLASS, "&cHeated Pressure Chamber", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerPerSecond(10));
    public static final ItemStack HEATED_PRESSURE_CHAMBER_2 = new SlimefunItemStack("HEATED_PRESSURE_CHAMBER_2", Material.LIGHT_GRAY_STAINED_GLASS, "&cHeated Pressure Chamber &7- &eII", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 5x", LoreBuilder.powerPerSecond(44));
    public static final ItemStack HEATED_PRESSURE_CHAMBER_3 = new SlimefunItemStack("HEATED_PRESSURE_CHAMBER_3", Material.LIGHT_GRAY_STAINED_GLASS, "&cHeated Pressure Chamber &7- &eIII", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 10x", LoreBuilder.powerPerSecond(80));

    public static final ItemStack ITEM_COMPRESSOR = new SlimefunItemStack("ITEM_COMPRESSOR", Material.LIGHT_GRAY_STAINED_GLASS, "&cItem Compressor", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerPerSecond(10));
    public static final ItemStack ITEM_COMPRESSOR_2 = new SlimefunItemStack("ITEM_COMPRESSOR_2", Material.LIGHT_GRAY_STAINED_GLASS, "&cItem Compressor &7- &eII", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 5x", LoreBuilder.powerPerSecond(44));
    public static final ItemStack ITEM_COMPRESSOR_3 = new SlimefunItemStack("ITEM_COMPRESSOR_3", Material.LIGHT_GRAY_STAINED_GLASS, "&cItem Compressor &7- &eIII", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 10x", LoreBuilder.powerPerSecond(80));

    public static final ItemStack ITEM_DECOMPRESSOR = new SlimefunItemStack("ITEM_DECOMPRESSOR", Material.LIGHT_GRAY_STAINED_GLASS, "&cItem Decompressor", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerPerSecond(10));
    public static final ItemStack ITEM_DECOMPRESSOR_2 = new SlimefunItemStack("ITEM_DECOMPRESSOR_2", Material.LIGHT_GRAY_STAINED_GLASS, "&cItem Decompressor &7- &eII", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 5x", LoreBuilder.powerPerSecond(44));
    public static final ItemStack ITEM_DECOMPRESSOR_3 = new SlimefunItemStack("ITEM_DECOMPRESSOR_3", Material.LIGHT_GRAY_STAINED_GLASS, "&cItem Decompressor &7- &eIII", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 10x", LoreBuilder.powerPerSecond(80));

    public static final ItemStack ELECTRIC_SMELTERY = new SlimefunItemStack("ELECTRIC_SMELTERY", Material.FURNACE, "&cElectric Smeltery", "", "&4Alloys-Only, doesn't smelt Dust into Ingots", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerPerSecond(20));
    public static final ItemStack ELECTRIC_SMELTERY_2 = new SlimefunItemStack("ELECTRIC_SMELTERY_2", Material.FURNACE, "&cElectric Smeltery &7- &eII", "", "&4Alloys-Only, doesn't smelt Dust into Ingots", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 3x", LoreBuilder.powerPerSecond(40));

    public static final ItemStack ELECTRIC_PRESS = new SlimefunItemStack("ELECTRIC_PRESS", "8d5cf92bc79ec19f4106441affff1406a1367010dcafb197dd94cfca1a6de0fc", "&eElectric Press", "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerPerSecond(16));
    public static final ItemStack ELECTRIC_PRESS_2 = new SlimefunItemStack("ELECTRIC_PRESS_2", "8d5cf92bc79ec19f4106441affff1406a1367010dcafb197dd94cfca1a6de0fc", "&eElectric Press &7- &eII", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 3x", LoreBuilder.powerPerSecond(40));
    public static final ItemStack ELECTRIC_PRESS_3 = new SlimefunItemStack("ELECTRIC_PRESS_3", "8d5cf92bc79ec19f4106441affff1406a1367010dcafb197dd94cfca1a6de0fc", "&eElectric Press &7- &eIII", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 10x", LoreBuilder.powerPerSecond(70));

    public static final ItemStack ELECTRIFIED_CRUCIBLE = new SlimefunItemStack("ELECTRIFIED_CRUCIBLE", Material.RED_TERRACOTTA, "&cElectrified Crucible", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerPerSecond(48));
    public static final ItemStack ELECTRIFIED_CRUCIBLE_2 = new SlimefunItemStack("ELECTRIFIED_CRUCIBLE_2", Material.RED_TERRACOTTA, "&cElectrified Crucible &7- &eII", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 2x", LoreBuilder.powerPerSecond(80));
    public static final ItemStack ELECTRIFIED_CRUCIBLE_3 = new SlimefunItemStack("ELECTRIFIED_CRUCIBLE_3", Material.RED_TERRACOTTA, "&cElectrified Crucible &7- &eIII", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 4x", LoreBuilder.powerPerSecond(120));

    public static final ItemStack CARBON_PRESS = new SlimefunItemStack("CARBON_PRESS", Material.BLACK_STAINED_GLASS, "&cCarbon Press", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerPerSecond(20));
    public static final ItemStack CARBON_PRESS_2 = new SlimefunItemStack("CARBON_PRESS_2", Material.BLACK_STAINED_GLASS, "&cCarbon Press &7- &eII", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 3x", LoreBuilder.powerPerSecond(50));
    public static final ItemStack CARBON_PRESS_3 = new SlimefunItemStack("CARBON_PRESS_3", Material.BLACK_STAINED_GLASS, "&cCarbon Press &7- &eIII", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 15x", LoreBuilder.powerPerSecond(180));

    public static final ItemStack BLISTERING_INGOT = new SlimefunItemStack("BLISTERING_INGOT", Material.GOLD_INGOT, "&6Blistering Ingot &7(33%)", "", LoreBuilder.radioactive(Radioactivity.HIGH), LoreBuilder.HAZMAT_SUIT_REQUIRED);
    public static final ItemStack BLISTERING_INGOT_2 = new SlimefunItemStack("BLISTERING_INGOT_2", Material.GOLD_INGOT, "&6Blistering Ingot &7(66%)", "", LoreBuilder.radioactive(Radioactivity.VERY_HIGH), LoreBuilder.HAZMAT_SUIT_REQUIRED);
    public static final ItemStack BLISTERING_INGOT_3 = new SlimefunItemStack("BLISTERING_INGOT_3", Material.GOLD_INGOT, "&6Blistering Ingot", "", LoreBuilder.radioactive(Radioactivity.VERY_HIGH), LoreBuilder.HAZMAT_SUIT_REQUIRED);

    public static final ItemStack ENERGY_REGULATOR = new SlimefunItemStack("ENERGY_REGULATOR", "d78f2b7e5e75639ea7fb796c35d364c4df28b4243e66b76277aadcd6261337", "&6Energy Regulator", "", "&rCore Component of an Energy Network");
    public static final ItemStack DEBUG_FISH = new CustomItem(Material.SALMON, "&3How much is the Fish?", "", "&eRight Click &rany Block to view it's BlockData", "&eLeft Click &rto break a Block", "&eShift + Left Click &rany Block to erase it's BlockData", "&eShift + Right Click &rto place a Placeholder Block");

    public static final ItemStack NETHER_ICE = new SlimefunItemStack("NETHER_ICE", "3ce2dad9baf7eaba7e80d4d0f9fac0aab01a76b12fb71c3d2af2a16fdd4c7383", "&eNether Ice", "", LoreBuilder.radioactive(Radioactivity.MODERATE), LoreBuilder.HAZMAT_SUIT_REQUIRED);
    public static final ItemStack ENRICHED_NETHER_ICE = new SlimefunItemStack("ENRICHED_NETHER_ICE", "7c818aa13aabc7294838d21caac057e97bd8c89641a0c0f8a55442ff4e27", "&eEnriched Nether Ice", "", LoreBuilder.radioactive(Radioactivity.VERY_HIGH), LoreBuilder.HAZMAT_SUIT_REQUIRED);
    public static final ItemStack NETHER_ICE_COOLANT_CELL = new SlimefunItemStack("NETHER_ICE_COOLANT_CELL", "8d3cd412555f897016213e5d6c7431b448b9e5644e1b19ec51b5316f35840e0", "&6Nether Ice Coolant Cell");

    // Cargo
    public static final ItemStack CARGO_MANAGER = new SlimefunItemStack("CARGO_MANAGER", "e510bc85362a130a6ff9d91ff11d6fa46d7d1912a3431f751558ef3c4d9c2", "&6Cargo Manager", "", "&rCore Component of an Item Transport Network");
    public static final ItemStack CARGO_NODE = new SlimefunItemStack("CARGO_NODE", "07b7ef6fd7864865c31c1dc87bed24ab5973579f5c6638fecb8dedeb443ff0", "&7Cargo Node &c(Connector)", "", "&rCargo Connector Pipe");
    public static final ItemStack CARGO_INPUT = new SlimefunItemStack("CARGO_NODE_INPUT", "16d1c1a69a3de9fec962a77bf3b2e376dd25c873a3d8f14f1dd345dae4c4", "&7Cargo Node &c(Input)", "", "&rCargo Input Pipe");
    public static final ItemStack CARGO_OUTPUT = new SlimefunItemStack("CARGO_NODE_OUTPUT", "55b21fd480c1c43bf3b9f842c869bdc3bc5acc2599bf2eb6b8a1c95dce978f", "&7Cargo Node &c(Output)", "", "&rCargo Output Pipe");
    public static final ItemStack CARGO_OUTPUT_ADVANCED = new SlimefunItemStack("CARGO_NODE_OUTPUT_ADVANCED", "55b21fd480c1c43bf3b9f842c869bdc3bc5acc2599bf2eb6b8a1c95dce978f", "&6Advanced Cargo Node &c(Output)", "", "&rCargo Output Pipe");

    public static final ItemStack AUTO_BREEDER = new SlimefunItemStack("AUTO_BREEDER", Material.HAY_BLOCK, "&eAuto-Breeder", "", "&rRuns on &aOrganic Food", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.powerBuffer(1024), "&8\u21E8 &e\u26A1 &760 J/Animal");

    public static final ItemStack ORGANIC_FOOD = new CustomItem(SkullItem.fromBase64("b439e3f5acbee9be4c4259289d6d9f35c635ffa661114687b3ea6dda8c79"), "&aOrganic Food", "&7Content: &9X");
    public static final SlimefunItemStack WHEAT_ORGANIC_FOOD = new SlimefunItemStack("ORGANIC_FOOD_WHEAT", "b439e3f5acbee9be4c4259289d6d9f35c635ffa661114687b3ea6dda8c79", "&aOrganic Food", "&7Content: &9Wheat");
    public static final SlimefunItemStack CARROT_ORGANIC_FOOD = new SlimefunItemStack("ORGANIC_FOOD_CARROT", "b439e3f5acbee9be4c4259289d6d9f35c635ffa661114687b3ea6dda8c79", "&aOrganic Food", "&7Content: &9Carrots");
    public static final SlimefunItemStack POTATO_ORGANIC_FOOD = new SlimefunItemStack("ORGANIC_FOOD_POTATO", "b439e3f5acbee9be4c4259289d6d9f35c635ffa661114687b3ea6dda8c79", "&aOrganic Food", "&7Content: &9Potatoes");
    public static final SlimefunItemStack SEEDS_ORGANIC_FOOD = new SlimefunItemStack("ORGANIC_FOOD_SEEDS", "b439e3f5acbee9be4c4259289d6d9f35c635ffa661114687b3ea6dda8c79", "&aOrganic Food", "&7Content: &9Seeds");
    public static final SlimefunItemStack BEETROOT_ORGANIC_FOOD = new SlimefunItemStack("ORGANIC_FOOD_BEETROOT", "b439e3f5acbee9be4c4259289d6d9f35c635ffa661114687b3ea6dda8c79", "&aOrganic Food", "&7Content: &9Beetroot");
    public static final SlimefunItemStack MELON_ORGANIC_FOOD = new SlimefunItemStack("ORGANIC_FOOD_MELON", "b439e3f5acbee9be4c4259289d6d9f35c635ffa661114687b3ea6dda8c79", "&aOrganic Food", "&7Content: &9Melon");
    public static final SlimefunItemStack APPLE_ORGANIC_FOOD = new SlimefunItemStack("ORGANIC_FOOD_APPLE", "b439e3f5acbee9be4c4259289d6d9f35c635ffa661114687b3ea6dda8c79", "&aOrganic Food", "&7Content: &9Apple");
    public static final SlimefunItemStack SWEET_BERRIES_ORGANIC_FOOD = new SlimefunItemStack("ORGANIC_FOOD_SWEET_BERRIES", "b439e3f5acbee9be4c4259289d6d9f35c635ffa661114687b3ea6dda8c79", "&aOrganic Food", "&7Content: &9Sweet Berries");
    public static final SlimefunItemStack KELP_ORGANIC_FOOD = new SlimefunItemStack("ORGANIC_FOOD_KELP", "b439e3f5acbee9be4c4259289d6d9f35c635ffa661114687b3ea6dda8c79", "&aOrganic Food", "&7Content: &9Dried Kelp");

    public static final ItemStack FERTILIZER = new CustomItem(SkullItem.fromBase64("b439e3f5acbee9be4c4259289d6d9f35c635ffa661114687b3ea6dda8c79"), "&aOrganic Fertilizer", "&7Content: &9X");
    public static final SlimefunItemStack WHEAT_FERTILIZER = new SlimefunItemStack("FERTILIZER_WHEAT", "b439e3f5acbee9be4c4259289d6d9f35c635ffa661114687b3ea6dda8c79", "&aOrganic Fertilizer", "&7Content: &9Wheat");
    public static final SlimefunItemStack CARROT_FERTILIZER = new SlimefunItemStack("FERTILIZER_CARROT", "b439e3f5acbee9be4c4259289d6d9f35c635ffa661114687b3ea6dda8c79", "&aOrganic Fertilizer", "&7Content: &9Carrots");
    public static final SlimefunItemStack POTATO_FERTILIZER = new SlimefunItemStack("FERTILIZER_POTATO", "b439e3f5acbee9be4c4259289d6d9f35c635ffa661114687b3ea6dda8c79", "&aOrganic Fertilizer", "&7Content: &9Potatoes");
    public static final SlimefunItemStack SEEDS_FERTILIZER = new SlimefunItemStack("FERTILIZER_SEEDS", "b439e3f5acbee9be4c4259289d6d9f35c635ffa661114687b3ea6dda8c79", "&aOrganic Fertilizer", "&7Content: &9Seeds");
    public static final SlimefunItemStack BEETROOT_FERTILIZER = new SlimefunItemStack("FERTILIZER_BEETROOT", "b439e3f5acbee9be4c4259289d6d9f35c635ffa661114687b3ea6dda8c79", "&aOrganic Fertilizer", "&7Content: &9Beetroot");
    public static final SlimefunItemStack MELON_FERTILIZER = new SlimefunItemStack("FERTILIZER_MELON", "b439e3f5acbee9be4c4259289d6d9f35c635ffa661114687b3ea6dda8c79", "&aOrganic Fertilizer", "&7Content: &9Melon");
    public static final SlimefunItemStack APPLE_FERTILIZER = new SlimefunItemStack("FERTILIZER_APPLE", "b439e3f5acbee9be4c4259289d6d9f35c635ffa661114687b3ea6dda8c79", "&aOrganic Fertilizer", "&7Content: &9Apple");
    public static final SlimefunItemStack SWEET_BERRIES_FERTILIZER = new SlimefunItemStack("FERTILIZER_SWEET_BERRIES", "b439e3f5acbee9be4c4259289d6d9f35c635ffa661114687b3ea6dda8c79", "&aOrganic Fertilizer", "&7Content: &9Sweet Berries");
    public static final SlimefunItemStack KELP_FERTILIZER = new SlimefunItemStack("FERTILIZER_KELP", "b439e3f5acbee9be4c4259289d6d9f35c635ffa661114687b3ea6dda8c79", "&aOrganic Fertilizer", "&7Content: &9Dried Kelp");

    public static final SlimefunItemStack ANIMAL_GROWTH_ACCELERATOR = new SlimefunItemStack("ANIMAL_GROWTH_ACCELERATOR", Material.HAY_BLOCK, "&bAnimal Growth Accelerator", "", "&rRuns on &aOrganic Food", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.powerBuffer(1024), LoreBuilder.powerPerSecond(28));
    public static final SlimefunItemStack CROP_GROWTH_ACCELERATOR = new SlimefunItemStack("CROP_GROWTH_ACCELERATOR", Material.LIME_TERRACOTTA, "&aCrop Growth Accelerator", "", "&rRuns on &aOrganic Fertilizer", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Radius: 7x7", "&8\u21E8 &7Speed: &a3/time", LoreBuilder.powerBuffer(1024), LoreBuilder.powerPerSecond(50));
    public static final SlimefunItemStack CROP_GROWTH_ACCELERATOR_2 = new SlimefunItemStack("CROP_GROWTH_ACCELERATOR_2", Material.LIME_TERRACOTTA, "&aCrop Growth Accelerator &7(&eII&7)", "", "&rRuns on &aOrganic Fertilizer", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Radius: 9x9", "&8\u21E8 &7Speed: &a4/time", LoreBuilder.powerBuffer(1024), LoreBuilder.powerPerSecond(60));
    public static final SlimefunItemStack TREE_GROWTH_ACCELERATOR = new SlimefunItemStack("TREE_GROWTH_ACCELERATOR", Material.BROWN_TERRACOTTA, "&aTree Growth Accelerator", "", "&rRuns on &aOrganic Fertilizer", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Radius: 9x9", "&8\u21E8 &7Speed: &a4/time", LoreBuilder.powerBuffer(1024), LoreBuilder.powerPerSecond(48));

    public static final SlimefunItemStack FOOD_FABRICATOR = new SlimefunItemStack("FOOD_FABRICATOR", Material.GREEN_STAINED_GLASS, "&cFood Fabricator", "", "&rProduces &aOrganic Food", "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerBuffer(256), LoreBuilder.powerPerSecond(14));
    public static final SlimefunItemStack FOOD_FABRICATOR_2 = new SlimefunItemStack("FOOD_FABRICATOR_2", Material.GREEN_STAINED_GLASS, "&cFood Fabricator &7(&eII&7)", "", "&rProduces &aOrganic Food", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 6x", LoreBuilder.powerBuffer(512), LoreBuilder.powerPerSecond(48));

    public static final SlimefunItemStack FOOD_COMPOSTER = new SlimefunItemStack("FOOD_COMPOSTER", Material.GREEN_TERRACOTTA, "&cFood Composter", "", "&rProduces &aOrganic Fertilizer", "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerBuffer(256), LoreBuilder.powerPerSecond(16));
    public static final SlimefunItemStack FOOD_COMPOSTER_2 = new SlimefunItemStack("FOOD_COMPOSTER_2", Material.GREEN_TERRACOTTA, "&cFood Composter &7(&eII&7)", "", "&rProduces &aOrganic Fertilizer", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 10x", LoreBuilder.powerBuffer(512), LoreBuilder.powerPerSecond(52));

    public static final ItemStack XP_COLLECTOR = new SlimefunItemStack("XP_COLLECTOR", "1762a15b04692a2e4b3fb3663bd4b78434dce1732b8eb1c7a9f7c0fbf6f", "&aEXP Collector", "", "&rCollects nearby Exp and stores it", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.powerBuffer(1024), LoreBuilder.powerPerSecond(20));
    public static final ItemStack REACTOR_COOLANT_CELL = new SlimefunItemStack("REACTOR_COLLANT_CELL", "de4073be40cb3deb310a0be959b4cac68e825372728fafb6c2973e4e7c33", "&bReactor Coolant Cell");

    public static final ItemStack NUCLEAR_REACTOR = new SlimefunItemStack("NUCLEAR_REACTOR", "9343ce58da54c79924a2c9331cfc417fe8ccbbea9be45a7ac85860a6c730", "&2Nuclear Reactor", "", "&rRequires Cooling!", "&8\u21E8 &bMust be surrounded by Water", "&8\u21E8 &bMust be supplied with Reactor Coolant Cells", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.GENERATOR), LoreBuilder.powerBuffer(16384), LoreBuilder.powerPerSecond(500));
    public static final ItemStack NETHERSTAR_REACTOR = new SlimefunItemStack("NETHERSTAR_REACTOR", "9343ce58da54c79924a2c9331cfc417fe8ccbbea9be45a7ac85860a6c730", "&fNether Star Reactor", "", "&fRuns on Nether Stars", "&8\u21E8 &bMust be surrounded by Water", "&8\u21E8 &bMust be supplied with Nether Ice Coolant Cells", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.GENERATOR), LoreBuilder.powerBuffer(32768), LoreBuilder.powerPerSecond(1024), "&8\u21E8 &4Causes nearby Entities to get Withered");
    public static final ItemStack REACTOR_ACCESS_PORT = new SlimefunItemStack("REACTOR_ACCESS_PORT", Material.CYAN_TERRACOTTA, "&2Reactor Access Port", "", "&rAllows you to interact with a Reactor", "&rvia Cargo Nodes, can also be used", "&ras a Buffer", "", "&8\u21E8 &eMust be placed &a3 Blocks &eabove the Reactor");

    public static final ItemStack FREEZER = new SlimefunItemStack("FREEZER", Material.LIGHT_BLUE_STAINED_GLASS, "&bFreezer", "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerBuffer(256), LoreBuilder.powerPerSecond(18));
    public static final ItemStack FREEZER_2 = new SlimefunItemStack("FREEZER_2", Material.LIGHT_BLUE_STAINED_GLASS, "&bFreezer &7(&eII&7)", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 2x", LoreBuilder.powerBuffer(256), LoreBuilder.powerPerSecond(30));

    public static final ItemStack ELECTRIC_GOLD_PAN = new SlimefunItemStack("ELECTRIC_GOLD_PAN", Material.BROWN_TERRACOTTA, "&6Electric Gold Pan", "", LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerPerSecond(2));
    public static final ItemStack ELECTRIC_GOLD_PAN_2 = new SlimefunItemStack("ELECTRIC_GOLD_PAN_2", Material.BROWN_TERRACOTTA, "&6Electric Gold Pan &7(&eII&7)", "", LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE), "&8\u21E8 &7Speed: 3x", LoreBuilder.powerPerSecond(4));
    public static final ItemStack ELECTRIC_GOLD_PAN_3 = new SlimefunItemStack("ELECTRIC_GOLD_PAN_3", Material.BROWN_TERRACOTTA, "&6Electric Gold Pan &7(&eIII&7)", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 10x", LoreBuilder.powerPerSecond(14));

    public static final ItemStack ELECTRIC_DUST_WASHER = new SlimefunItemStack("ELECTRIC_DUST_WASHER", Material.BLUE_STAINED_GLASS, "&3Electric Dust Washer", "", LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerPerSecond(6));
    public static final ItemStack ELECTRIC_DUST_WASHER_2 = new SlimefunItemStack("ELECTRIC_DUST_WASHER_2", Material.BLUE_STAINED_GLASS, "&3Electric Dust Washer &7(&eII&7)", "", LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE), "&8\u21E8 &7Speed: 2x", LoreBuilder.powerPerSecond(10));
    public static final ItemStack ELECTRIC_DUST_WASHER_3 = new SlimefunItemStack("ELECTRIC_DUST_WASHER_3", Material.BLUE_STAINED_GLASS, "&3Electric Dust Washer &7(&eIII&7)", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 10x", LoreBuilder.powerPerSecond(30));

    public static final ItemStack ELECTRIC_INGOT_FACTORY = new SlimefunItemStack("ELECTRIC_INGOT_FACTORY", Material.RED_TERRACOTTA, "&cElectric Ingot Factory", "", LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerPerSecond(8));
    public static final ItemStack ELECTRIC_INGOT_FACTORY_2 = new SlimefunItemStack("ELECTRIC_INGOT_FACTORY_2", Material.RED_TERRACOTTA, "&cElectric Ingot Factory &7(&eII&7)", "", LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE), "&8\u21E8 &7Speed: 2x", LoreBuilder.powerPerSecond(14));
    public static final ItemStack ELECTRIC_INGOT_FACTORY_3 = new SlimefunItemStack("ELECTRIC_INGOT_FACTORY_3", Material.RED_TERRACOTTA, "&cElectric Ingot Factory &7(&eIII&7)", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Speed: 8x", LoreBuilder.powerPerSecond(40));

    public static final SlimefunItemStack AUTOMATED_CRAFTING_CHAMBER = new SlimefunItemStack("AUTOMATED_CRAFTING_CHAMBER", Material.CRAFTING_TABLE, "&6Automated Crafting Chamber", "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), "&8\u21E8 &e\u26A1 &710 J/Item");
    public static final SlimefunItemStack FLUID_PUMP = new SlimefunItemStack("FLUID_PUMP", Material.BLUE_TERRACOTTA, "&9Fluid Pump", "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), "&8\u21E8 &e\u26A1 &732 J/Block");
    public static final SlimefunItemStack CHARGING_BENCH = new SlimefunItemStack("CHARGING_BENCH", Material.CRAFTING_TABLE, "&6Charging Bench", "", "&rCharges Items such as Jetpacks", "", LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE), LoreBuilder.powerBuffer(128), "&8\u21E8 &e\u26A1 &7Energy Loss: &c50%");

    public static final SlimefunItemStack WITHER_ASSEMBLER = new SlimefunItemStack("WITHER_ASSEMBLER", Material.OBSIDIAN, "&5Wither Assembler", "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), "&8\u21E8 &7Cooldown: &b30 Seconds", LoreBuilder.powerBuffer(4096), "&8\u21E8 &e\u26A1 &74096 J/Wither");

    public static final SlimefunItemStack TRASH_CAN = new SlimefunItemStack("TRASH_CAN_BLOCK", "32d41042ce99147cc38cac9e46741576e7ee791283e6fac8d3292cae2935f1f", "&3Trash Can", "", "&rWill destroy all Items put into it");

    public static final ItemStack ELYTRA = new ItemStack(Material.ELYTRA);
    public static final SlimefunItemStack ELYTRA_SCALE = new SlimefunItemStack("ELYTRA_SCALE", Material.FEATHER, "&bElytra Scale");
    public static final SlimefunItemStack INFUSED_ELYTRA = new SlimefunItemStack("INFUSED_ELYTRA", Material.ELYTRA, "&5Infused Elytra");
    public static final SlimefunItemStack SOULBOUND_ELYTRA = new SlimefunItemStack("SOULBOUND_ELYTRA", Material.ELYTRA, "&cSoulbound Elytra");

    public static final ItemStack TOTEM_OF_UNDYING = new ItemStack(Material.TOTEM_OF_UNDYING);

    public static final SlimefunItemStack MAGNESIUM_SALT = new SlimefunItemStack("MAGNESIUM_SALT", Material.SUGAR, "&cMagnesium Salt", "", "&7A special type of fuel that can be", "&7used in a Magnesium-powered Generator");
    public static final SlimefunItemStack MAGNESIUM_GENERATOR = new SlimefunItemStack("MAGNESIUM_GENERATOR", "9343ce58da54c79924a2c9331cfc417fe8ccbbea9be45a7ac85860a6c730", "&cMagnesium-powered Generator", "", LoreBuilder.machine(MachineTier.MEDIUM, MachineType.GENERATOR), LoreBuilder.powerBuffer(128), LoreBuilder.powerPerSecond(36));

    /* Compressed Items */
    // public static final SlimefunItemStack COMPRESSED_COBBLESTONE = new SlimefunItemStack("COMPRESSED_COBBLESTONE", Material.COBBLESTONE, "&cCobblestone.tar", "", "&6A block of 32 cobblestones", "&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COBBLESTONE_1 = new SlimefunItemStack("COMPRESSED_COBBLESTONE_1", Material.COBBLESTONE, "&cCobblestone.tar.gz", "", "&6A block of 32 cobblestone.tar", "&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ACACIA_BUTTON = new SlimefunItemStack("COMPRESSED_ACACIA_BUTTON", Material.ACACIA_BUTTON,"&cACACIA_BUTTON.tar","","&6A block of 32 ACACIA_BUTTON","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ACACIA_DOOR = new SlimefunItemStack("COMPRESSED_ACACIA_DOOR", Material.ACACIA_DOOR,"&cACACIA_DOOR.tar","","&6A block of 32 ACACIA_DOOR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ACACIA_FENCE = new SlimefunItemStack("COMPRESSED_ACACIA_FENCE", Material.ACACIA_FENCE,"&cACACIA_FENCE.tar","","&6A block of 32 ACACIA_FENCE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ACACIA_FENCE_GATE = new SlimefunItemStack("COMPRESSED_ACACIA_FENCE_GATE", Material.ACACIA_FENCE_GATE,"&cACACIA_FENCE_GATE.tar","","&6A block of 32 ACACIA_FENCE_GATE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ACACIA_LEAVES = new SlimefunItemStack("COMPRESSED_ACACIA_LEAVES", Material.ACACIA_LEAVES,"&cACACIA_LEAVES.tar","","&6A block of 32 ACACIA_LEAVES","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ACACIA_LOG = new SlimefunItemStack("COMPRESSED_ACACIA_LOG", Material.ACACIA_LOG,"&cACACIA_LOG.tar","","&6A block of 32 ACACIA_LOG","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ACACIA_PLANKS = new SlimefunItemStack("COMPRESSED_ACACIA_PLANKS", Material.ACACIA_PLANKS,"&cACACIA_PLANKS.tar","","&6A block of 32 ACACIA_PLANKS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ACACIA_PRESSURE_PLATE = new SlimefunItemStack("COMPRESSED_ACACIA_PRESSURE_PLATE", Material.ACACIA_PRESSURE_PLATE,"&cACACIA_PRESSURE_PLATE.tar","","&6A block of 32 ACACIA_PRESSURE_PLATE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ACACIA_SAPLING = new SlimefunItemStack("COMPRESSED_ACACIA_SAPLING", Material.ACACIA_SAPLING,"&cACACIA_SAPLING.tar","","&6A block of 32 ACACIA_SAPLING","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ACACIA_SLAB = new SlimefunItemStack("COMPRESSED_ACACIA_SLAB", Material.ACACIA_SLAB,"&cACACIA_SLAB.tar","","&6A block of 32 ACACIA_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ACACIA_STAIRS = new SlimefunItemStack("COMPRESSED_ACACIA_STAIRS", Material.ACACIA_STAIRS,"&cACACIA_STAIRS.tar","","&6A block of 32 ACACIA_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ACACIA_TRAPDOOR = new SlimefunItemStack("COMPRESSED_ACACIA_TRAPDOOR", Material.ACACIA_TRAPDOOR,"&cACACIA_TRAPDOOR.tar","","&6A block of 32 ACACIA_TRAPDOOR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ACACIA_WOOD = new SlimefunItemStack("COMPRESSED_ACACIA_WOOD", Material.ACACIA_WOOD,"&cACACIA_WOOD.tar","","&6A block of 32 ACACIA_WOOD","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ACTIVATOR_RAIL = new SlimefunItemStack("COMPRESSED_ACTIVATOR_RAIL", Material.ACTIVATOR_RAIL,"&cACTIVATOR_RAIL.tar","","&6A block of 32 ACTIVATOR_RAIL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ALLIUM = new SlimefunItemStack("COMPRESSED_ALLIUM", Material.ALLIUM,"&cALLIUM.tar","","&6A block of 32 ALLIUM","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ANDESITE = new SlimefunItemStack("COMPRESSED_ANDESITE", Material.ANDESITE,"&cANDESITE.tar","","&6A block of 32 ANDESITE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ANDESITE_SLAB = new SlimefunItemStack("COMPRESSED_ANDESITE_SLAB", Material.ANDESITE_SLAB,"&cANDESITE_SLAB.tar","","&6A block of 32 ANDESITE_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ANDESITE_STAIRS = new SlimefunItemStack("COMPRESSED_ANDESITE_STAIRS", Material.ANDESITE_STAIRS,"&cANDESITE_STAIRS.tar","","&6A block of 32 ANDESITE_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ANDESITE_WALL = new SlimefunItemStack("COMPRESSED_ANDESITE_WALL", Material.ANDESITE_WALL,"&cANDESITE_WALL.tar","","&6A block of 32 ANDESITE_WALL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ANVIL = new SlimefunItemStack("COMPRESSED_ANVIL", Material.ANVIL,"&cANVIL.tar","","&6A block of 32 ANVIL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_APPLE = new SlimefunItemStack("COMPRESSED_APPLE", Material.APPLE,"&cAPPLE.tar","","&6A block of 32 APPLE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ARROW = new SlimefunItemStack("COMPRESSED_ARROW", Material.ARROW,"&cARROW.tar","","&6A block of 32 ARROW","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_AZURE_BLUET = new SlimefunItemStack("COMPRESSED_AZURE_BLUET", Material.AZURE_BLUET,"&cAZURE_BLUET.tar","","&6A block of 32 AZURE_BLUET","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BAKED_POTATO = new SlimefunItemStack("COMPRESSED_BAKED_POTATO", Material.BAKED_POTATO,"&cBAKED_POTATO.tar","","&6A block of 32 BAKED_POTATO","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BAMBOO = new SlimefunItemStack("COMPRESSED_BAMBOO", Material.BAMBOO,"&cBAMBOO.tar","","&6A block of 32 BAMBOO","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BARREL = new SlimefunItemStack("COMPRESSED_BARREL", Material.BARREL,"&cBARREL.tar","","&6A block of 32 BARREL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BEACON = new SlimefunItemStack("COMPRESSED_BEACON", Material.BEACON,"&cBEACON.tar","","&6A block of 32 BEACON","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BEEF = new SlimefunItemStack("COMPRESSED_BEEF", Material.BEEF,"&cBEEF.tar","","&6A block of 32 BEEF","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BEEHIVE = new SlimefunItemStack("COMPRESSED_BEEHIVE", Material.BEEHIVE,"&cBEEHIVE.tar","","&6A block of 32 BEEHIVE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BEETROOT = new SlimefunItemStack("COMPRESSED_BEETROOT", Material.BEETROOT,"&cBEETROOT.tar","","&6A block of 32 BEETROOT","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BEETROOT_SEEDS = new SlimefunItemStack("COMPRESSED_BEETROOT_SEEDS", Material.BEETROOT_SEEDS,"&cBEETROOT_SEEDS.tar","","&6A block of 32 BEETROOT_SEEDS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BEE_NEST = new SlimefunItemStack("COMPRESSED_BEE_NEST", Material.BEE_NEST,"&cBEE_NEST.tar","","&6A block of 32 BEE_NEST","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BELL = new SlimefunItemStack("COMPRESSED_BELL", Material.BELL,"&cBELL.tar","","&6A block of 32 BELL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BIRCH_BUTTON = new SlimefunItemStack("COMPRESSED_BIRCH_BUTTON", Material.BIRCH_BUTTON,"&cBIRCH_BUTTON.tar","","&6A block of 32 BIRCH_BUTTON","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BIRCH_DOOR = new SlimefunItemStack("COMPRESSED_BIRCH_DOOR", Material.BIRCH_DOOR,"&cBIRCH_DOOR.tar","","&6A block of 32 BIRCH_DOOR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BIRCH_FENCE = new SlimefunItemStack("COMPRESSED_BIRCH_FENCE", Material.BIRCH_FENCE,"&cBIRCH_FENCE.tar","","&6A block of 32 BIRCH_FENCE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BIRCH_FENCE_GATE = new SlimefunItemStack("COMPRESSED_BIRCH_FENCE_GATE", Material.BIRCH_FENCE_GATE,"&cBIRCH_FENCE_GATE.tar","","&6A block of 32 BIRCH_FENCE_GATE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BIRCH_LEAVES = new SlimefunItemStack("COMPRESSED_BIRCH_LEAVES", Material.BIRCH_LEAVES,"&cBIRCH_LEAVES.tar","","&6A block of 32 BIRCH_LEAVES","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BIRCH_LOG = new SlimefunItemStack("COMPRESSED_BIRCH_LOG", Material.BIRCH_LOG,"&cBIRCH_LOG.tar","","&6A block of 32 BIRCH_LOG","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BIRCH_PLANKS = new SlimefunItemStack("COMPRESSED_BIRCH_PLANKS", Material.BIRCH_PLANKS,"&cBIRCH_PLANKS.tar","","&6A block of 32 BIRCH_PLANKS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BIRCH_PRESSURE_PLATE = new SlimefunItemStack("COMPRESSED_BIRCH_PRESSURE_PLATE", Material.BIRCH_PRESSURE_PLATE,"&cBIRCH_PRESSURE_PLATE.tar","","&6A block of 32 BIRCH_PRESSURE_PLATE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BIRCH_SAPLING = new SlimefunItemStack("COMPRESSED_BIRCH_SAPLING", Material.BIRCH_SAPLING,"&cBIRCH_SAPLING.tar","","&6A block of 32 BIRCH_SAPLING","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BIRCH_SLAB = new SlimefunItemStack("COMPRESSED_BIRCH_SLAB", Material.BIRCH_SLAB,"&cBIRCH_SLAB.tar","","&6A block of 32 BIRCH_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BIRCH_STAIRS = new SlimefunItemStack("COMPRESSED_BIRCH_STAIRS", Material.BIRCH_STAIRS,"&cBIRCH_STAIRS.tar","","&6A block of 32 BIRCH_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BIRCH_TRAPDOOR = new SlimefunItemStack("COMPRESSED_BIRCH_TRAPDOOR", Material.BIRCH_TRAPDOOR,"&cBIRCH_TRAPDOOR.tar","","&6A block of 32 BIRCH_TRAPDOOR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BIRCH_WOOD = new SlimefunItemStack("COMPRESSED_BIRCH_WOOD", Material.BIRCH_WOOD,"&cBIRCH_WOOD.tar","","&6A block of 32 BIRCH_WOOD","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLACK_CARPET = new SlimefunItemStack("COMPRESSED_BLACK_CARPET", Material.BLACK_CARPET,"&cBLACK_CARPET.tar","","&6A block of 32 BLACK_CARPET","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLACK_CONCRETE = new SlimefunItemStack("COMPRESSED_BLACK_CONCRETE", Material.BLACK_CONCRETE,"&cBLACK_CONCRETE.tar","","&6A block of 32 BLACK_CONCRETE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLACK_CONCRETE_POWDER = new SlimefunItemStack("COMPRESSED_BLACK_CONCRETE_POWDER", Material.BLACK_CONCRETE_POWDER,"&cBLACK_CONCRETE_POWDER.tar","","&6A block of 32 BLACK_CONCRETE_POWDER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLACK_DYE = new SlimefunItemStack("COMPRESSED_BLACK_DYE", Material.BLACK_DYE,"&cBLACK_DYE.tar","","&6A block of 32 BLACK_DYE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLACK_GLAZED_TERRACOTTA = new SlimefunItemStack("COMPRESSED_BLACK_GLAZED_TERRACOTTA", Material.BLACK_GLAZED_TERRACOTTA,"&cBLACK_GLAZED_TERRACOTTA.tar","","&6A block of 32 BLACK_GLAZED_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLACK_STAINED_GLASS = new SlimefunItemStack("COMPRESSED_BLACK_STAINED_GLASS", Material.BLACK_STAINED_GLASS,"&cBLACK_STAINED_GLASS.tar","","&6A block of 32 BLACK_STAINED_GLASS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLACK_STAINED_GLASS_PANE = new SlimefunItemStack("COMPRESSED_BLACK_STAINED_GLASS_PANE", Material.BLACK_STAINED_GLASS_PANE,"&cBLACK_STAINED_GLASS_PANE.tar","","&6A block of 32 BLACK_STAINED_GLASS_PANE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLACK_TERRACOTTA = new SlimefunItemStack("COMPRESSED_BLACK_TERRACOTTA", Material.BLACK_TERRACOTTA,"&cBLACK_TERRACOTTA.tar","","&6A block of 32 BLACK_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLACK_WOOL = new SlimefunItemStack("COMPRESSED_BLACK_WOOL", Material.BLACK_WOOL,"&cBLACK_WOOL.tar","","&6A block of 32 BLACK_WOOL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLAST_FURNACE = new SlimefunItemStack("COMPRESSED_BLAST_FURNACE", Material.BLAST_FURNACE,"&cBLAST_FURNACE.tar","","&6A block of 32 BLAST_FURNACE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLAZE_POWDER = new SlimefunItemStack("COMPRESSED_BLAZE_POWDER", Material.BLAZE_POWDER,"&cBLAZE_POWDER.tar","","&6A block of 32 BLAZE_POWDER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLAZE_ROD = new SlimefunItemStack("COMPRESSED_BLAZE_ROD", Material.BLAZE_ROD,"&cBLAZE_ROD.tar","","&6A block of 32 BLAZE_ROD","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLUE_CARPET = new SlimefunItemStack("COMPRESSED_BLUE_CARPET", Material.BLUE_CARPET,"&cBLUE_CARPET.tar","","&6A block of 32 BLUE_CARPET","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLUE_CONCRETE = new SlimefunItemStack("COMPRESSED_BLUE_CONCRETE", Material.BLUE_CONCRETE,"&cBLUE_CONCRETE.tar","","&6A block of 32 BLUE_CONCRETE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLUE_CONCRETE_POWDER = new SlimefunItemStack("COMPRESSED_BLUE_CONCRETE_POWDER", Material.BLUE_CONCRETE_POWDER,"&cBLUE_CONCRETE_POWDER.tar","","&6A block of 32 BLUE_CONCRETE_POWDER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLUE_DYE = new SlimefunItemStack("COMPRESSED_BLUE_DYE", Material.BLUE_DYE,"&cBLUE_DYE.tar","","&6A block of 32 BLUE_DYE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLUE_GLAZED_TERRACOTTA = new SlimefunItemStack("COMPRESSED_BLUE_GLAZED_TERRACOTTA", Material.BLUE_GLAZED_TERRACOTTA,"&cBLUE_GLAZED_TERRACOTTA.tar","","&6A block of 32 BLUE_GLAZED_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLUE_ICE = new SlimefunItemStack("COMPRESSED_BLUE_ICE", Material.BLUE_ICE,"&cBLUE_ICE.tar","","&6A block of 32 BLUE_ICE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLUE_ORCHID = new SlimefunItemStack("COMPRESSED_BLUE_ORCHID", Material.BLUE_ORCHID,"&cBLUE_ORCHID.tar","","&6A block of 32 BLUE_ORCHID","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLUE_STAINED_GLASS = new SlimefunItemStack("COMPRESSED_BLUE_STAINED_GLASS", Material.BLUE_STAINED_GLASS,"&cBLUE_STAINED_GLASS.tar","","&6A block of 32 BLUE_STAINED_GLASS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLUE_STAINED_GLASS_PANE = new SlimefunItemStack("COMPRESSED_BLUE_STAINED_GLASS_PANE", Material.BLUE_STAINED_GLASS_PANE,"&cBLUE_STAINED_GLASS_PANE.tar","","&6A block of 32 BLUE_STAINED_GLASS_PANE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLUE_TERRACOTTA = new SlimefunItemStack("COMPRESSED_BLUE_TERRACOTTA", Material.BLUE_TERRACOTTA,"&cBLUE_TERRACOTTA.tar","","&6A block of 32 BLUE_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BLUE_WOOL = new SlimefunItemStack("COMPRESSED_BLUE_WOOL", Material.BLUE_WOOL,"&cBLUE_WOOL.tar","","&6A block of 32 BLUE_WOOL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BONE = new SlimefunItemStack("COMPRESSED_BONE", Material.BONE,"&cBONE.tar","","&6A block of 32 BONE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BONE_BLOCK = new SlimefunItemStack("COMPRESSED_BONE_BLOCK", Material.BONE_BLOCK,"&cBONE_BLOCK.tar","","&6A block of 32 BONE_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BONE_MEAL = new SlimefunItemStack("COMPRESSED_BONE_MEAL", Material.BONE_MEAL,"&cBONE_MEAL.tar","","&6A block of 32 BONE_MEAL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BOOK = new SlimefunItemStack("COMPRESSED_BOOK", Material.BOOK,"&cBOOK.tar","","&6A block of 32 BOOK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BOOKSHELF = new SlimefunItemStack("COMPRESSED_BOOKSHELF", Material.BOOKSHELF,"&cBOOKSHELF.tar","","&6A block of 32 BOOKSHELF","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BOWL = new SlimefunItemStack("COMPRESSED_BOWL", Material.BOWL,"&cBOWL.tar","","&6A block of 32 BOWL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BRAIN_CORAL = new SlimefunItemStack("COMPRESSED_BRAIN_CORAL", Material.BRAIN_CORAL,"&cBRAIN_CORAL.tar","","&6A block of 32 BRAIN_CORAL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BRAIN_CORAL_BLOCK = new SlimefunItemStack("COMPRESSED_BRAIN_CORAL_BLOCK", Material.BRAIN_CORAL_BLOCK,"&cBRAIN_CORAL_BLOCK.tar","","&6A block of 32 BRAIN_CORAL_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BRAIN_CORAL_FAN = new SlimefunItemStack("COMPRESSED_BRAIN_CORAL_FAN", Material.BRAIN_CORAL_FAN,"&cBRAIN_CORAL_FAN.tar","","&6A block of 32 BRAIN_CORAL_FAN","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BREAD = new SlimefunItemStack("COMPRESSED_BREAD", Material.BREAD,"&cBREAD.tar","","&6A block of 32 BREAD","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BREWING_STAND = new SlimefunItemStack("COMPRESSED_BREWING_STAND", Material.BREWING_STAND,"&cBREWING_STAND.tar","","&6A block of 32 BREWING_STAND","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BRICK = new SlimefunItemStack("COMPRESSED_BRICK", Material.BRICK,"&cBRICK.tar","","&6A block of 32 BRICK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BRICKS = new SlimefunItemStack("COMPRESSED_BRICKS", Material.BRICKS,"&cBRICKS.tar","","&6A block of 32 BRICKS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BRICK_SLAB = new SlimefunItemStack("COMPRESSED_BRICK_SLAB", Material.BRICK_SLAB,"&cBRICK_SLAB.tar","","&6A block of 32 BRICK_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BRICK_STAIRS = new SlimefunItemStack("COMPRESSED_BRICK_STAIRS", Material.BRICK_STAIRS,"&cBRICK_STAIRS.tar","","&6A block of 32 BRICK_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BRICK_WALL = new SlimefunItemStack("COMPRESSED_BRICK_WALL", Material.BRICK_WALL,"&cBRICK_WALL.tar","","&6A block of 32 BRICK_WALL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BROWN_CARPET = new SlimefunItemStack("COMPRESSED_BROWN_CARPET", Material.BROWN_CARPET,"&cBROWN_CARPET.tar","","&6A block of 32 BROWN_CARPET","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BROWN_CONCRETE = new SlimefunItemStack("COMPRESSED_BROWN_CONCRETE", Material.BROWN_CONCRETE,"&cBROWN_CONCRETE.tar","","&6A block of 32 BROWN_CONCRETE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BROWN_CONCRETE_POWDER = new SlimefunItemStack("COMPRESSED_BROWN_CONCRETE_POWDER", Material.BROWN_CONCRETE_POWDER,"&cBROWN_CONCRETE_POWDER.tar","","&6A block of 32 BROWN_CONCRETE_POWDER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BROWN_DYE = new SlimefunItemStack("COMPRESSED_BROWN_DYE", Material.BROWN_DYE,"&cBROWN_DYE.tar","","&6A block of 32 BROWN_DYE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BROWN_GLAZED_TERRACOTTA = new SlimefunItemStack("COMPRESSED_BROWN_GLAZED_TERRACOTTA", Material.BROWN_GLAZED_TERRACOTTA,"&cBROWN_GLAZED_TERRACOTTA.tar","","&6A block of 32 BROWN_GLAZED_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BROWN_MUSHROOM = new SlimefunItemStack("COMPRESSED_BROWN_MUSHROOM", Material.BROWN_MUSHROOM,"&cBROWN_MUSHROOM.tar","","&6A block of 32 BROWN_MUSHROOM","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BROWN_MUSHROOM_BLOCK = new SlimefunItemStack("COMPRESSED_BROWN_MUSHROOM_BLOCK", Material.BROWN_MUSHROOM_BLOCK,"&cBROWN_MUSHROOM_BLOCK.tar","","&6A block of 32 BROWN_MUSHROOM_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BROWN_STAINED_GLASS = new SlimefunItemStack("COMPRESSED_BROWN_STAINED_GLASS", Material.BROWN_STAINED_GLASS,"&cBROWN_STAINED_GLASS.tar","","&6A block of 32 BROWN_STAINED_GLASS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BROWN_STAINED_GLASS_PANE = new SlimefunItemStack("COMPRESSED_BROWN_STAINED_GLASS_PANE", Material.BROWN_STAINED_GLASS_PANE,"&cBROWN_STAINED_GLASS_PANE.tar","","&6A block of 32 BROWN_STAINED_GLASS_PANE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BROWN_TERRACOTTA = new SlimefunItemStack("COMPRESSED_BROWN_TERRACOTTA", Material.BROWN_TERRACOTTA,"&cBROWN_TERRACOTTA.tar","","&6A block of 32 BROWN_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BROWN_WOOL = new SlimefunItemStack("COMPRESSED_BROWN_WOOL", Material.BROWN_WOOL,"&cBROWN_WOOL.tar","","&6A block of 32 BROWN_WOOL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BUBBLE_CORAL = new SlimefunItemStack("COMPRESSED_BUBBLE_CORAL", Material.BUBBLE_CORAL,"&cBUBBLE_CORAL.tar","","&6A block of 32 BUBBLE_CORAL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BUBBLE_CORAL_BLOCK = new SlimefunItemStack("COMPRESSED_BUBBLE_CORAL_BLOCK", Material.BUBBLE_CORAL_BLOCK,"&cBUBBLE_CORAL_BLOCK.tar","","&6A block of 32 BUBBLE_CORAL_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_BUBBLE_CORAL_FAN = new SlimefunItemStack("COMPRESSED_BUBBLE_CORAL_FAN", Material.BUBBLE_CORAL_FAN,"&cBUBBLE_CORAL_FAN.tar","","&6A block of 32 BUBBLE_CORAL_FAN","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CACTUS = new SlimefunItemStack("COMPRESSED_CACTUS", Material.CACTUS,"&cCACTUS.tar","","&6A block of 32 CACTUS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CAMPFIRE = new SlimefunItemStack("COMPRESSED_CAMPFIRE", Material.CAMPFIRE,"&cCAMPFIRE.tar","","&6A block of 32 CAMPFIRE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CARROT = new SlimefunItemStack("COMPRESSED_CARROT", Material.CARROT,"&cCARROT.tar","","&6A block of 32 CARROT","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CARTOGRAPHY_TABLE = new SlimefunItemStack("COMPRESSED_CARTOGRAPHY_TABLE", Material.CARTOGRAPHY_TABLE,"&cCARTOGRAPHY_TABLE.tar","","&6A block of 32 CARTOGRAPHY_TABLE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CARVED_PUMPKIN = new SlimefunItemStack("COMPRESSED_CARVED_PUMPKIN", Material.CARVED_PUMPKIN,"&cCARVED_PUMPKIN.tar","","&6A block of 32 CARVED_PUMPKIN","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CAULDRON = new SlimefunItemStack("COMPRESSED_CAULDRON", Material.CAULDRON,"&cCAULDRON.tar","","&6A block of 32 CAULDRON","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CHARCOAL = new SlimefunItemStack("COMPRESSED_CHARCOAL", Material.CHARCOAL,"&cCHARCOAL.tar","","&6A block of 32 CHARCOAL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CHEST = new SlimefunItemStack("COMPRESSED_CHEST", Material.CHEST,"&cCHEST.tar","","&6A block of 32 CHEST","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CHICKEN = new SlimefunItemStack("COMPRESSED_CHICKEN", Material.CHICKEN,"&cCHICKEN.tar","","&6A block of 32 CHICKEN","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CHIPPED_ANVIL = new SlimefunItemStack("COMPRESSED_CHIPPED_ANVIL", Material.CHIPPED_ANVIL,"&cCHIPPED_ANVIL.tar","","&6A block of 32 CHIPPED_ANVIL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CHISELED_QUARTZ_BLOCK = new SlimefunItemStack("COMPRESSED_CHISELED_QUARTZ_BLOCK", Material.CHISELED_QUARTZ_BLOCK,"&cCHISELED_QUARTZ_BLOCK.tar","","&6A block of 32 CHISELED_QUARTZ_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CHISELED_RED_SANDSTONE = new SlimefunItemStack("COMPRESSED_CHISELED_RED_SANDSTONE", Material.CHISELED_RED_SANDSTONE,"&cCHISELED_RED_SANDSTONE.tar","","&6A block of 32 CHISELED_RED_SANDSTONE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CHISELED_SANDSTONE = new SlimefunItemStack("COMPRESSED_CHISELED_SANDSTONE", Material.CHISELED_SANDSTONE,"&cCHISELED_SANDSTONE.tar","","&6A block of 32 CHISELED_SANDSTONE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CHISELED_STONE_BRICKS = new SlimefunItemStack("COMPRESSED_CHISELED_STONE_BRICKS", Material.CHISELED_STONE_BRICKS,"&cCHISELED_STONE_BRICKS.tar","","&6A block of 32 CHISELED_STONE_BRICKS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CHORUS_FLOWER = new SlimefunItemStack("COMPRESSED_CHORUS_FLOWER", Material.CHORUS_FLOWER,"&cCHORUS_FLOWER.tar","","&6A block of 32 CHORUS_FLOWER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CHORUS_FRUIT = new SlimefunItemStack("COMPRESSED_CHORUS_FRUIT", Material.CHORUS_FRUIT,"&cCHORUS_FRUIT.tar","","&6A block of 32 CHORUS_FRUIT","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CLAY = new SlimefunItemStack("COMPRESSED_CLAY", Material.CLAY,"&cCLAY.tar","","&6A block of 32 CLAY","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CLAY_BALL = new SlimefunItemStack("COMPRESSED_CLAY_BALL", Material.CLAY_BALL,"&cCLAY_BALL.tar","","&6A block of 32 CLAY_BALL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COAL = new SlimefunItemStack("COMPRESSED_COAL", Material.COAL,"&cCOAL.tar","","&6A block of 32 COAL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COAL_BLOCK = new SlimefunItemStack("COMPRESSED_COAL_BLOCK", Material.COAL_BLOCK,"&cCOAL_BLOCK.tar","","&6A block of 32 COAL_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COAL_ORE = new SlimefunItemStack("COMPRESSED_COAL_ORE", Material.COAL_ORE,"&cCOAL_ORE.tar","","&6A block of 32 COAL_ORE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COARSE_DIRT = new SlimefunItemStack("COMPRESSED_COARSE_DIRT", Material.COARSE_DIRT,"&cCOARSE_DIRT.tar","","&6A block of 32 COARSE_DIRT","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COBBLESTONE = new SlimefunItemStack("COMPRESSED_COBBLESTONE", Material.COBBLESTONE,"&cCOBBLESTONE.tar","","&6A block of 32 COBBLESTONE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COBBLESTONE_SLAB = new SlimefunItemStack("COMPRESSED_COBBLESTONE_SLAB", Material.COBBLESTONE_SLAB,"&cCOBBLESTONE_SLAB.tar","","&6A block of 32 COBBLESTONE_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COBBLESTONE_STAIRS = new SlimefunItemStack("COMPRESSED_COBBLESTONE_STAIRS", Material.COBBLESTONE_STAIRS,"&cCOBBLESTONE_STAIRS.tar","","&6A block of 32 COBBLESTONE_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COBBLESTONE_WALL = new SlimefunItemStack("COMPRESSED_COBBLESTONE_WALL", Material.COBBLESTONE_WALL,"&cCOBBLESTONE_WALL.tar","","&6A block of 32 COBBLESTONE_WALL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COBWEB = new SlimefunItemStack("COMPRESSED_COBWEB", Material.COBWEB,"&cCOBWEB.tar","","&6A block of 32 COBWEB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COCOA_BEANS = new SlimefunItemStack("COMPRESSED_COCOA_BEANS", Material.COCOA_BEANS,"&cCOCOA_BEANS.tar","","&6A block of 32 COCOA_BEANS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COMPARATOR = new SlimefunItemStack("COMPRESSED_COMPARATOR", Material.COMPARATOR,"&cCOMPARATOR.tar","","&6A block of 32 COMPARATOR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COMPASS = new SlimefunItemStack("COMPRESSED_COMPASS", Material.COMPASS,"&cCOMPASS.tar","","&6A block of 32 COMPASS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COMPOSTER = new SlimefunItemStack("COMPRESSED_COMPOSTER", Material.COMPOSTER,"&cCOMPOSTER.tar","","&6A block of 32 COMPOSTER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CONDUIT = new SlimefunItemStack("COMPRESSED_CONDUIT", Material.CONDUIT,"&cCONDUIT.tar","","&6A block of 32 CONDUIT","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COOKED_BEEF = new SlimefunItemStack("COMPRESSED_COOKED_BEEF", Material.COOKED_BEEF,"&cCOOKED_BEEF.tar","","&6A block of 32 COOKED_BEEF","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COOKED_CHICKEN = new SlimefunItemStack("COMPRESSED_COOKED_CHICKEN", Material.COOKED_CHICKEN,"&cCOOKED_CHICKEN.tar","","&6A block of 32 COOKED_CHICKEN","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COOKED_COD = new SlimefunItemStack("COMPRESSED_COOKED_COD", Material.COOKED_COD,"&cCOOKED_COD.tar","","&6A block of 32 COOKED_COD","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COOKED_MUTTON = new SlimefunItemStack("COMPRESSED_COOKED_MUTTON", Material.COOKED_MUTTON,"&cCOOKED_MUTTON.tar","","&6A block of 32 COOKED_MUTTON","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COOKED_PORKCHOP = new SlimefunItemStack("COMPRESSED_COOKED_PORKCHOP", Material.COOKED_PORKCHOP,"&cCOOKED_PORKCHOP.tar","","&6A block of 32 COOKED_PORKCHOP","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COOKED_RABBIT = new SlimefunItemStack("COMPRESSED_COOKED_RABBIT", Material.COOKED_RABBIT,"&cCOOKED_RABBIT.tar","","&6A block of 32 COOKED_RABBIT","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COOKED_SALMON = new SlimefunItemStack("COMPRESSED_COOKED_SALMON", Material.COOKED_SALMON,"&cCOOKED_SALMON.tar","","&6A block of 32 COOKED_SALMON","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_COOKIE = new SlimefunItemStack("COMPRESSED_COOKIE", Material.COOKIE,"&cCOOKIE.tar","","&6A block of 32 COOKIE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CORNFLOWER = new SlimefunItemStack("COMPRESSED_CORNFLOWER", Material.CORNFLOWER,"&cCORNFLOWER.tar","","&6A block of 32 CORNFLOWER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CRACKED_STONE_BRICKS = new SlimefunItemStack("COMPRESSED_CRACKED_STONE_BRICKS", Material.CRACKED_STONE_BRICKS,"&cCRACKED_STONE_BRICKS.tar","","&6A block of 32 CRACKED_STONE_BRICKS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CRAFTING_TABLE = new SlimefunItemStack("COMPRESSED_CRAFTING_TABLE", Material.CRAFTING_TABLE,"&cCRAFTING_TABLE.tar","","&6A block of 32 CRAFTING_TABLE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CUT_RED_SANDSTONE = new SlimefunItemStack("COMPRESSED_CUT_RED_SANDSTONE", Material.CUT_RED_SANDSTONE,"&cCUT_RED_SANDSTONE.tar","","&6A block of 32 CUT_RED_SANDSTONE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CUT_RED_SANDSTONE_SLAB = new SlimefunItemStack("COMPRESSED_CUT_RED_SANDSTONE_SLAB", Material.CUT_RED_SANDSTONE_SLAB,"&cCUT_RED_SANDSTONE_SLAB.tar","","&6A block of 32 CUT_RED_SANDSTONE_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CUT_SANDSTONE = new SlimefunItemStack("COMPRESSED_CUT_SANDSTONE", Material.CUT_SANDSTONE,"&cCUT_SANDSTONE.tar","","&6A block of 32 CUT_SANDSTONE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CUT_SANDSTONE_SLAB = new SlimefunItemStack("COMPRESSED_CUT_SANDSTONE_SLAB", Material.CUT_SANDSTONE_SLAB,"&cCUT_SANDSTONE_SLAB.tar","","&6A block of 32 CUT_SANDSTONE_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CYAN_CARPET = new SlimefunItemStack("COMPRESSED_CYAN_CARPET", Material.CYAN_CARPET,"&cCYAN_CARPET.tar","","&6A block of 32 CYAN_CARPET","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CYAN_CONCRETE = new SlimefunItemStack("COMPRESSED_CYAN_CONCRETE", Material.CYAN_CONCRETE,"&cCYAN_CONCRETE.tar","","&6A block of 32 CYAN_CONCRETE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CYAN_CONCRETE_POWDER = new SlimefunItemStack("COMPRESSED_CYAN_CONCRETE_POWDER", Material.CYAN_CONCRETE_POWDER,"&cCYAN_CONCRETE_POWDER.tar","","&6A block of 32 CYAN_CONCRETE_POWDER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CYAN_DYE = new SlimefunItemStack("COMPRESSED_CYAN_DYE", Material.CYAN_DYE,"&cCYAN_DYE.tar","","&6A block of 32 CYAN_DYE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CYAN_GLAZED_TERRACOTTA = new SlimefunItemStack("COMPRESSED_CYAN_GLAZED_TERRACOTTA", Material.CYAN_GLAZED_TERRACOTTA,"&cCYAN_GLAZED_TERRACOTTA.tar","","&6A block of 32 CYAN_GLAZED_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CYAN_STAINED_GLASS = new SlimefunItemStack("COMPRESSED_CYAN_STAINED_GLASS", Material.CYAN_STAINED_GLASS,"&cCYAN_STAINED_GLASS.tar","","&6A block of 32 CYAN_STAINED_GLASS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CYAN_STAINED_GLASS_PANE = new SlimefunItemStack("COMPRESSED_CYAN_STAINED_GLASS_PANE", Material.CYAN_STAINED_GLASS_PANE,"&cCYAN_STAINED_GLASS_PANE.tar","","&6A block of 32 CYAN_STAINED_GLASS_PANE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CYAN_TERRACOTTA = new SlimefunItemStack("COMPRESSED_CYAN_TERRACOTTA", Material.CYAN_TERRACOTTA,"&cCYAN_TERRACOTTA.tar","","&6A block of 32 CYAN_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_CYAN_WOOL = new SlimefunItemStack("COMPRESSED_CYAN_WOOL", Material.CYAN_WOOL,"&cCYAN_WOOL.tar","","&6A block of 32 CYAN_WOOL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DAMAGED_ANVIL = new SlimefunItemStack("COMPRESSED_DAMAGED_ANVIL", Material.DAMAGED_ANVIL,"&cDAMAGED_ANVIL.tar","","&6A block of 32 DAMAGED_ANVIL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DANDELION = new SlimefunItemStack("COMPRESSED_DANDELION", Material.DANDELION,"&cDANDELION.tar","","&6A block of 32 DANDELION","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DARK_OAK_BUTTON = new SlimefunItemStack("COMPRESSED_DARK_OAK_BUTTON", Material.DARK_OAK_BUTTON,"&cDARK_OAK_BUTTON.tar","","&6A block of 32 DARK_OAK_BUTTON","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DARK_OAK_DOOR = new SlimefunItemStack("COMPRESSED_DARK_OAK_DOOR", Material.DARK_OAK_DOOR,"&cDARK_OAK_DOOR.tar","","&6A block of 32 DARK_OAK_DOOR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DARK_OAK_FENCE = new SlimefunItemStack("COMPRESSED_DARK_OAK_FENCE", Material.DARK_OAK_FENCE,"&cDARK_OAK_FENCE.tar","","&6A block of 32 DARK_OAK_FENCE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DARK_OAK_FENCE_GATE = new SlimefunItemStack("COMPRESSED_DARK_OAK_FENCE_GATE", Material.DARK_OAK_FENCE_GATE,"&cDARK_OAK_FENCE_GATE.tar","","&6A block of 32 DARK_OAK_FENCE_GATE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DARK_OAK_LEAVES = new SlimefunItemStack("COMPRESSED_DARK_OAK_LEAVES", Material.DARK_OAK_LEAVES,"&cDARK_OAK_LEAVES.tar","","&6A block of 32 DARK_OAK_LEAVES","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DARK_OAK_LOG = new SlimefunItemStack("COMPRESSED_DARK_OAK_LOG", Material.DARK_OAK_LOG,"&cDARK_OAK_LOG.tar","","&6A block of 32 DARK_OAK_LOG","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DARK_OAK_PLANKS = new SlimefunItemStack("COMPRESSED_DARK_OAK_PLANKS", Material.DARK_OAK_PLANKS,"&cDARK_OAK_PLANKS.tar","","&6A block of 32 DARK_OAK_PLANKS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DARK_OAK_PRESSURE_PLATE = new SlimefunItemStack("COMPRESSED_DARK_OAK_PRESSURE_PLATE", Material.DARK_OAK_PRESSURE_PLATE,"&cDARK_OAK_PRESSURE_PLATE.tar","","&6A block of 32 DARK_OAK_PRESSURE_PLATE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DARK_OAK_SAPLING = new SlimefunItemStack("COMPRESSED_DARK_OAK_SAPLING", Material.DARK_OAK_SAPLING,"&cDARK_OAK_SAPLING.tar","","&6A block of 32 DARK_OAK_SAPLING","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DARK_OAK_SLAB = new SlimefunItemStack("COMPRESSED_DARK_OAK_SLAB", Material.DARK_OAK_SLAB,"&cDARK_OAK_SLAB.tar","","&6A block of 32 DARK_OAK_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DARK_OAK_STAIRS = new SlimefunItemStack("COMPRESSED_DARK_OAK_STAIRS", Material.DARK_OAK_STAIRS,"&cDARK_OAK_STAIRS.tar","","&6A block of 32 DARK_OAK_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DARK_OAK_TRAPDOOR = new SlimefunItemStack("COMPRESSED_DARK_OAK_TRAPDOOR", Material.DARK_OAK_TRAPDOOR,"&cDARK_OAK_TRAPDOOR.tar","","&6A block of 32 DARK_OAK_TRAPDOOR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DARK_OAK_WOOD = new SlimefunItemStack("COMPRESSED_DARK_OAK_WOOD", Material.DARK_OAK_WOOD,"&cDARK_OAK_WOOD.tar","","&6A block of 32 DARK_OAK_WOOD","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DARK_PRISMARINE = new SlimefunItemStack("COMPRESSED_DARK_PRISMARINE", Material.DARK_PRISMARINE,"&cDARK_PRISMARINE.tar","","&6A block of 32 DARK_PRISMARINE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DARK_PRISMARINE_SLAB = new SlimefunItemStack("COMPRESSED_DARK_PRISMARINE_SLAB", Material.DARK_PRISMARINE_SLAB,"&cDARK_PRISMARINE_SLAB.tar","","&6A block of 32 DARK_PRISMARINE_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DARK_PRISMARINE_STAIRS = new SlimefunItemStack("COMPRESSED_DARK_PRISMARINE_STAIRS", Material.DARK_PRISMARINE_STAIRS,"&cDARK_PRISMARINE_STAIRS.tar","","&6A block of 32 DARK_PRISMARINE_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DAYLIGHT_DETECTOR = new SlimefunItemStack("COMPRESSED_DAYLIGHT_DETECTOR", Material.DAYLIGHT_DETECTOR,"&cDAYLIGHT_DETECTOR.tar","","&6A block of 32 DAYLIGHT_DETECTOR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DEAD_BRAIN_CORAL = new SlimefunItemStack("COMPRESSED_DEAD_BRAIN_CORAL", Material.DEAD_BRAIN_CORAL,"&cDEAD_BRAIN_CORAL.tar","","&6A block of 32 DEAD_BRAIN_CORAL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DEAD_BRAIN_CORAL_BLOCK = new SlimefunItemStack("COMPRESSED_DEAD_BRAIN_CORAL_BLOCK", Material.DEAD_BRAIN_CORAL_BLOCK,"&cDEAD_BRAIN_CORAL_BLOCK.tar","","&6A block of 32 DEAD_BRAIN_CORAL_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DEAD_BRAIN_CORAL_FAN = new SlimefunItemStack("COMPRESSED_DEAD_BRAIN_CORAL_FAN", Material.DEAD_BRAIN_CORAL_FAN,"&cDEAD_BRAIN_CORAL_FAN.tar","","&6A block of 32 DEAD_BRAIN_CORAL_FAN","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DEAD_BUBBLE_CORAL = new SlimefunItemStack("COMPRESSED_DEAD_BUBBLE_CORAL", Material.DEAD_BUBBLE_CORAL,"&cDEAD_BUBBLE_CORAL.tar","","&6A block of 32 DEAD_BUBBLE_CORAL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DEAD_BUBBLE_CORAL_BLOCK = new SlimefunItemStack("COMPRESSED_DEAD_BUBBLE_CORAL_BLOCK", Material.DEAD_BUBBLE_CORAL_BLOCK,"&cDEAD_BUBBLE_CORAL_BLOCK.tar","","&6A block of 32 DEAD_BUBBLE_CORAL_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DEAD_BUBBLE_CORAL_FAN = new SlimefunItemStack("COMPRESSED_DEAD_BUBBLE_CORAL_FAN", Material.DEAD_BUBBLE_CORAL_FAN,"&cDEAD_BUBBLE_CORAL_FAN.tar","","&6A block of 32 DEAD_BUBBLE_CORAL_FAN","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DEAD_FIRE_CORAL = new SlimefunItemStack("COMPRESSED_DEAD_FIRE_CORAL", Material.DEAD_FIRE_CORAL,"&cDEAD_FIRE_CORAL.tar","","&6A block of 32 DEAD_FIRE_CORAL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DEAD_FIRE_CORAL_BLOCK = new SlimefunItemStack("COMPRESSED_DEAD_FIRE_CORAL_BLOCK", Material.DEAD_FIRE_CORAL_BLOCK,"&cDEAD_FIRE_CORAL_BLOCK.tar","","&6A block of 32 DEAD_FIRE_CORAL_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DEAD_FIRE_CORAL_FAN = new SlimefunItemStack("COMPRESSED_DEAD_FIRE_CORAL_FAN", Material.DEAD_FIRE_CORAL_FAN,"&cDEAD_FIRE_CORAL_FAN.tar","","&6A block of 32 DEAD_FIRE_CORAL_FAN","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DEAD_HORN_CORAL = new SlimefunItemStack("COMPRESSED_DEAD_HORN_CORAL", Material.DEAD_HORN_CORAL,"&cDEAD_HORN_CORAL.tar","","&6A block of 32 DEAD_HORN_CORAL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DEAD_HORN_CORAL_BLOCK = new SlimefunItemStack("COMPRESSED_DEAD_HORN_CORAL_BLOCK", Material.DEAD_HORN_CORAL_BLOCK,"&cDEAD_HORN_CORAL_BLOCK.tar","","&6A block of 32 DEAD_HORN_CORAL_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DEAD_HORN_CORAL_FAN = new SlimefunItemStack("COMPRESSED_DEAD_HORN_CORAL_FAN", Material.DEAD_HORN_CORAL_FAN,"&cDEAD_HORN_CORAL_FAN.tar","","&6A block of 32 DEAD_HORN_CORAL_FAN","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DEAD_TUBE_CORAL = new SlimefunItemStack("COMPRESSED_DEAD_TUBE_CORAL", Material.DEAD_TUBE_CORAL,"&cDEAD_TUBE_CORAL.tar","","&6A block of 32 DEAD_TUBE_CORAL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DEAD_TUBE_CORAL_BLOCK = new SlimefunItemStack("COMPRESSED_DEAD_TUBE_CORAL_BLOCK", Material.DEAD_TUBE_CORAL_BLOCK,"&cDEAD_TUBE_CORAL_BLOCK.tar","","&6A block of 32 DEAD_TUBE_CORAL_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DEAD_TUBE_CORAL_FAN = new SlimefunItemStack("COMPRESSED_DEAD_TUBE_CORAL_FAN", Material.DEAD_TUBE_CORAL_FAN,"&cDEAD_TUBE_CORAL_FAN.tar","","&6A block of 32 DEAD_TUBE_CORAL_FAN","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DETECTOR_RAIL = new SlimefunItemStack("COMPRESSED_DETECTOR_RAIL", Material.DETECTOR_RAIL,"&cDETECTOR_RAIL.tar","","&6A block of 32 DETECTOR_RAIL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DIAMOND = new SlimefunItemStack("COMPRESSED_DIAMOND", Material.DIAMOND,"&cDIAMOND.tar","","&6A block of 32 DIAMOND","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DIAMOND_BLOCK = new SlimefunItemStack("COMPRESSED_DIAMOND_BLOCK", Material.DIAMOND_BLOCK,"&cDIAMOND_BLOCK.tar","","&6A block of 32 DIAMOND_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DIAMOND_ORE = new SlimefunItemStack("COMPRESSED_DIAMOND_ORE", Material.DIAMOND_ORE,"&cDIAMOND_ORE.tar","","&6A block of 32 DIAMOND_ORE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DIORITE = new SlimefunItemStack("COMPRESSED_DIORITE", Material.DIORITE,"&cDIORITE.tar","","&6A block of 32 DIORITE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DIORITE_SLAB = new SlimefunItemStack("COMPRESSED_DIORITE_SLAB", Material.DIORITE_SLAB,"&cDIORITE_SLAB.tar","","&6A block of 32 DIORITE_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DIORITE_STAIRS = new SlimefunItemStack("COMPRESSED_DIORITE_STAIRS", Material.DIORITE_STAIRS,"&cDIORITE_STAIRS.tar","","&6A block of 32 DIORITE_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DIORITE_WALL = new SlimefunItemStack("COMPRESSED_DIORITE_WALL", Material.DIORITE_WALL,"&cDIORITE_WALL.tar","","&6A block of 32 DIORITE_WALL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DIRT = new SlimefunItemStack("COMPRESSED_DIRT", Material.DIRT,"&cDIRT.tar","","&6A block of 32 DIRT","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DISPENSER = new SlimefunItemStack("COMPRESSED_DISPENSER", Material.DISPENSER,"&cDISPENSER.tar","","&6A block of 32 DISPENSER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DRIED_KELP = new SlimefunItemStack("COMPRESSED_DRIED_KELP", Material.DRIED_KELP,"&cDRIED_KELP.tar","","&6A block of 32 DRIED_KELP","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DRIED_KELP_BLOCK = new SlimefunItemStack("COMPRESSED_DRIED_KELP_BLOCK", Material.DRIED_KELP_BLOCK,"&cDRIED_KELP_BLOCK.tar","","&6A block of 32 DRIED_KELP_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_DROPPER = new SlimefunItemStack("COMPRESSED_DROPPER", Material.DROPPER,"&cDROPPER.tar","","&6A block of 32 DROPPER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_EMERALD = new SlimefunItemStack("COMPRESSED_EMERALD", Material.EMERALD,"&cEMERALD.tar","","&6A block of 32 EMERALD","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_EMERALD_BLOCK = new SlimefunItemStack("COMPRESSED_EMERALD_BLOCK", Material.EMERALD_BLOCK,"&cEMERALD_BLOCK.tar","","&6A block of 32 EMERALD_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_EMERALD_ORE = new SlimefunItemStack("COMPRESSED_EMERALD_ORE", Material.EMERALD_ORE,"&cEMERALD_ORE.tar","","&6A block of 32 EMERALD_ORE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ENCHANTED_GOLDEN_APPLE = new SlimefunItemStack("COMPRESSED_ENCHANTED_GOLDEN_APPLE", Material.ENCHANTED_GOLDEN_APPLE,"&cENCHANTED_GOLDEN_APPLE.tar","","&6A block of 32 ENCHANTED_GOLDEN_APPLE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ENCHANTING_TABLE = new SlimefunItemStack("COMPRESSED_ENCHANTING_TABLE", Material.ENCHANTING_TABLE,"&cENCHANTING_TABLE.tar","","&6A block of 32 ENCHANTING_TABLE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ENDER_CHEST = new SlimefunItemStack("COMPRESSED_ENDER_CHEST", Material.ENDER_CHEST,"&cENDER_CHEST.tar","","&6A block of 32 ENDER_CHEST","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_END_CRYSTAL = new SlimefunItemStack("COMPRESSED_END_CRYSTAL", Material.END_CRYSTAL,"&cEND_CRYSTAL.tar","","&6A block of 32 END_CRYSTAL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_END_ROD = new SlimefunItemStack("COMPRESSED_END_ROD", Material.END_ROD,"&cEND_ROD.tar","","&6A block of 32 END_ROD","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_END_STONE = new SlimefunItemStack("COMPRESSED_END_STONE", Material.END_STONE,"&cEND_STONE.tar","","&6A block of 32 END_STONE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_END_STONE_BRICKS = new SlimefunItemStack("COMPRESSED_END_STONE_BRICKS", Material.END_STONE_BRICKS,"&cEND_STONE_BRICKS.tar","","&6A block of 32 END_STONE_BRICKS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_END_STONE_BRICK_SLAB = new SlimefunItemStack("COMPRESSED_END_STONE_BRICK_SLAB", Material.END_STONE_BRICK_SLAB,"&cEND_STONE_BRICK_SLAB.tar","","&6A block of 32 END_STONE_BRICK_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_END_STONE_BRICK_STAIRS = new SlimefunItemStack("COMPRESSED_END_STONE_BRICK_STAIRS", Material.END_STONE_BRICK_STAIRS,"&cEND_STONE_BRICK_STAIRS.tar","","&6A block of 32 END_STONE_BRICK_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_END_STONE_BRICK_WALL = new SlimefunItemStack("COMPRESSED_END_STONE_BRICK_WALL", Material.END_STONE_BRICK_WALL,"&cEND_STONE_BRICK_WALL.tar","","&6A block of 32 END_STONE_BRICK_WALL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_EXPERIENCE_BOTTLE = new SlimefunItemStack("COMPRESSED_EXPERIENCE_BOTTLE", Material.EXPERIENCE_BOTTLE,"&cEXPERIENCE_BOTTLE.tar","","&6A block of 32 EXPERIENCE_BOTTLE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_FEATHER = new SlimefunItemStack("COMPRESSED_FEATHER", Material.FEATHER,"&cFEATHER.tar","","&6A block of 32 FEATHER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_FERMENTED_SPIDER_EYE = new SlimefunItemStack("COMPRESSED_FERMENTED_SPIDER_EYE", Material.FERMENTED_SPIDER_EYE,"&cFERMENTED_SPIDER_EYE.tar","","&6A block of 32 FERMENTED_SPIDER_EYE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_FERN = new SlimefunItemStack("COMPRESSED_FERN", Material.FERN,"&cFERN.tar","","&6A block of 32 FERN","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_FIREWORK_ROCKET = new SlimefunItemStack("COMPRESSED_FIREWORK_ROCKET", Material.FIREWORK_ROCKET,"&cFIREWORK_ROCKET.tar","","&6A block of 32 FIREWORK_ROCKET","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_FIREWORK_STAR = new SlimefunItemStack("COMPRESSED_FIREWORK_STAR", Material.FIREWORK_STAR,"&cFIREWORK_STAR.tar","","&6A block of 32 FIREWORK_STAR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_FIRE_CHARGE = new SlimefunItemStack("COMPRESSED_FIRE_CHARGE", Material.FIRE_CHARGE,"&cFIRE_CHARGE.tar","","&6A block of 32 FIRE_CHARGE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_FIRE_CORAL = new SlimefunItemStack("COMPRESSED_FIRE_CORAL", Material.FIRE_CORAL,"&cFIRE_CORAL.tar","","&6A block of 32 FIRE_CORAL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_FIRE_CORAL_BLOCK = new SlimefunItemStack("COMPRESSED_FIRE_CORAL_BLOCK", Material.FIRE_CORAL_BLOCK,"&cFIRE_CORAL_BLOCK.tar","","&6A block of 32 FIRE_CORAL_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_FIRE_CORAL_FAN = new SlimefunItemStack("COMPRESSED_FIRE_CORAL_FAN", Material.FIRE_CORAL_FAN,"&cFIRE_CORAL_FAN.tar","","&6A block of 32 FIRE_CORAL_FAN","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_FLETCHING_TABLE = new SlimefunItemStack("COMPRESSED_FLETCHING_TABLE", Material.FLETCHING_TABLE,"&cFLETCHING_TABLE.tar","","&6A block of 32 FLETCHING_TABLE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_FLINT = new SlimefunItemStack("COMPRESSED_FLINT", Material.FLINT,"&cFLINT.tar","","&6A block of 32 FLINT","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_FLOWER_POT = new SlimefunItemStack("COMPRESSED_FLOWER_POT", Material.FLOWER_POT,"&cFLOWER_POT.tar","","&6A block of 32 FLOWER_POT","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_FURNACE = new SlimefunItemStack("COMPRESSED_FURNACE", Material.FURNACE,"&cFURNACE.tar","","&6A block of 32 FURNACE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GHAST_TEAR = new SlimefunItemStack("COMPRESSED_GHAST_TEAR", Material.GHAST_TEAR,"&cGHAST_TEAR.tar","","&6A block of 32 GHAST_TEAR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GLASS = new SlimefunItemStack("COMPRESSED_GLASS", Material.GLASS,"&cGLASS.tar","","&6A block of 32 GLASS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GLASS_PANE = new SlimefunItemStack("COMPRESSED_GLASS_PANE", Material.GLASS_PANE,"&cGLASS_PANE.tar","","&6A block of 32 GLASS_PANE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GLISTERING_MELON_SLICE = new SlimefunItemStack("COMPRESSED_GLISTERING_MELON_SLICE", Material.GLISTERING_MELON_SLICE,"&cGLISTERING_MELON_SLICE.tar","","&6A block of 32 GLISTERING_MELON_SLICE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GLOWSTONE = new SlimefunItemStack("COMPRESSED_GLOWSTONE", Material.GLOWSTONE,"&cGLOWSTONE.tar","","&6A block of 32 GLOWSTONE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GLOWSTONE_DUST = new SlimefunItemStack("COMPRESSED_GLOWSTONE_DUST", Material.GLOWSTONE_DUST,"&cGLOWSTONE_DUST.tar","","&6A block of 32 GLOWSTONE_DUST","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GOLDEN_APPLE = new SlimefunItemStack("COMPRESSED_GOLDEN_APPLE", Material.GOLDEN_APPLE,"&cGOLDEN_APPLE.tar","","&6A block of 32 GOLDEN_APPLE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GOLDEN_CARROT = new SlimefunItemStack("COMPRESSED_GOLDEN_CARROT", Material.GOLDEN_CARROT,"&cGOLDEN_CARROT.tar","","&6A block of 32 GOLDEN_CARROT","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GOLD_BLOCK = new SlimefunItemStack("COMPRESSED_GOLD_BLOCK", Material.GOLD_BLOCK,"&cGOLD_BLOCK.tar","","&6A block of 32 GOLD_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GOLD_INGOT = new SlimefunItemStack("COMPRESSED_GOLD_INGOT", Material.GOLD_INGOT,"&cGOLD_INGOT.tar","","&6A block of 32 GOLD_INGOT","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GOLD_NUGGET = new SlimefunItemStack("COMPRESSED_GOLD_NUGGET", Material.GOLD_NUGGET,"&cGOLD_NUGGET.tar","","&6A block of 32 GOLD_NUGGET","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GOLD_ORE = new SlimefunItemStack("COMPRESSED_GOLD_ORE", Material.GOLD_ORE,"&cGOLD_ORE.tar","","&6A block of 32 GOLD_ORE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GRANITE = new SlimefunItemStack("COMPRESSED_GRANITE", Material.GRANITE,"&cGRANITE.tar","","&6A block of 32 GRANITE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GRANITE_SLAB = new SlimefunItemStack("COMPRESSED_GRANITE_SLAB", Material.GRANITE_SLAB,"&cGRANITE_SLAB.tar","","&6A block of 32 GRANITE_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GRANITE_STAIRS = new SlimefunItemStack("COMPRESSED_GRANITE_STAIRS", Material.GRANITE_STAIRS,"&cGRANITE_STAIRS.tar","","&6A block of 32 GRANITE_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GRANITE_WALL = new SlimefunItemStack("COMPRESSED_GRANITE_WALL", Material.GRANITE_WALL,"&cGRANITE_WALL.tar","","&6A block of 32 GRANITE_WALL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GRASS = new SlimefunItemStack("COMPRESSED_GRASS", Material.GRASS,"&cGRASS.tar","","&6A block of 32 GRASS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GRASS_BLOCK = new SlimefunItemStack("COMPRESSED_GRASS_BLOCK", Material.GRASS_BLOCK,"&cGRASS_BLOCK.tar","","&6A block of 32 GRASS_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GRASS_PATH = new SlimefunItemStack("COMPRESSED_GRASS_PATH", Material.GRASS_PATH,"&cGRASS_PATH.tar","","&6A block of 32 GRASS_PATH","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GRAVEL = new SlimefunItemStack("COMPRESSED_GRAVEL", Material.GRAVEL,"&cGRAVEL.tar","","&6A block of 32 GRAVEL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GRAY_CARPET = new SlimefunItemStack("COMPRESSED_GRAY_CARPET", Material.GRAY_CARPET,"&cGRAY_CARPET.tar","","&6A block of 32 GRAY_CARPET","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GRAY_CONCRETE = new SlimefunItemStack("COMPRESSED_GRAY_CONCRETE", Material.GRAY_CONCRETE,"&cGRAY_CONCRETE.tar","","&6A block of 32 GRAY_CONCRETE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GRAY_CONCRETE_POWDER = new SlimefunItemStack("COMPRESSED_GRAY_CONCRETE_POWDER", Material.GRAY_CONCRETE_POWDER,"&cGRAY_CONCRETE_POWDER.tar","","&6A block of 32 GRAY_CONCRETE_POWDER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GRAY_DYE = new SlimefunItemStack("COMPRESSED_GRAY_DYE", Material.GRAY_DYE,"&cGRAY_DYE.tar","","&6A block of 32 GRAY_DYE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GRAY_GLAZED_TERRACOTTA = new SlimefunItemStack("COMPRESSED_GRAY_GLAZED_TERRACOTTA", Material.GRAY_GLAZED_TERRACOTTA,"&cGRAY_GLAZED_TERRACOTTA.tar","","&6A block of 32 GRAY_GLAZED_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GRAY_STAINED_GLASS = new SlimefunItemStack("COMPRESSED_GRAY_STAINED_GLASS", Material.GRAY_STAINED_GLASS,"&cGRAY_STAINED_GLASS.tar","","&6A block of 32 GRAY_STAINED_GLASS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GRAY_STAINED_GLASS_PANE = new SlimefunItemStack("COMPRESSED_GRAY_STAINED_GLASS_PANE", Material.GRAY_STAINED_GLASS_PANE,"&cGRAY_STAINED_GLASS_PANE.tar","","&6A block of 32 GRAY_STAINED_GLASS_PANE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GRAY_TERRACOTTA = new SlimefunItemStack("COMPRESSED_GRAY_TERRACOTTA", Material.GRAY_TERRACOTTA,"&cGRAY_TERRACOTTA.tar","","&6A block of 32 GRAY_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GRAY_WOOL = new SlimefunItemStack("COMPRESSED_GRAY_WOOL", Material.GRAY_WOOL,"&cGRAY_WOOL.tar","","&6A block of 32 GRAY_WOOL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GREEN_CARPET = new SlimefunItemStack("COMPRESSED_GREEN_CARPET", Material.GREEN_CARPET,"&cGREEN_CARPET.tar","","&6A block of 32 GREEN_CARPET","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GREEN_CONCRETE = new SlimefunItemStack("COMPRESSED_GREEN_CONCRETE", Material.GREEN_CONCRETE,"&cGREEN_CONCRETE.tar","","&6A block of 32 GREEN_CONCRETE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GREEN_CONCRETE_POWDER = new SlimefunItemStack("COMPRESSED_GREEN_CONCRETE_POWDER", Material.GREEN_CONCRETE_POWDER,"&cGREEN_CONCRETE_POWDER.tar","","&6A block of 32 GREEN_CONCRETE_POWDER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GREEN_DYE = new SlimefunItemStack("COMPRESSED_GREEN_DYE", Material.GREEN_DYE,"&cGREEN_DYE.tar","","&6A block of 32 GREEN_DYE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GREEN_GLAZED_TERRACOTTA = new SlimefunItemStack("COMPRESSED_GREEN_GLAZED_TERRACOTTA", Material.GREEN_GLAZED_TERRACOTTA,"&cGREEN_GLAZED_TERRACOTTA.tar","","&6A block of 32 GREEN_GLAZED_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GREEN_STAINED_GLASS = new SlimefunItemStack("COMPRESSED_GREEN_STAINED_GLASS", Material.GREEN_STAINED_GLASS,"&cGREEN_STAINED_GLASS.tar","","&6A block of 32 GREEN_STAINED_GLASS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GREEN_STAINED_GLASS_PANE = new SlimefunItemStack("COMPRESSED_GREEN_STAINED_GLASS_PANE", Material.GREEN_STAINED_GLASS_PANE,"&cGREEN_STAINED_GLASS_PANE.tar","","&6A block of 32 GREEN_STAINED_GLASS_PANE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GREEN_TERRACOTTA = new SlimefunItemStack("COMPRESSED_GREEN_TERRACOTTA", Material.GREEN_TERRACOTTA,"&cGREEN_TERRACOTTA.tar","","&6A block of 32 GREEN_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GREEN_WOOL = new SlimefunItemStack("COMPRESSED_GREEN_WOOL", Material.GREEN_WOOL,"&cGREEN_WOOL.tar","","&6A block of 32 GREEN_WOOL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GRINDSTONE = new SlimefunItemStack("COMPRESSED_GRINDSTONE", Material.GRINDSTONE,"&cGRINDSTONE.tar","","&6A block of 32 GRINDSTONE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_GUNPOWDER = new SlimefunItemStack("COMPRESSED_GUNPOWDER", Material.GUNPOWDER,"&cGUNPOWDER.tar","","&6A block of 32 GUNPOWDER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_HAY_BLOCK = new SlimefunItemStack("COMPRESSED_HAY_BLOCK", Material.HAY_BLOCK,"&cHAY_BLOCK.tar","","&6A block of 32 HAY_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_HEAVY_WEIGHTED_PRESSURE_PLATE = new SlimefunItemStack("COMPRESSED_HEAVY_WEIGHTED_PRESSURE_PLATE", Material.HEAVY_WEIGHTED_PRESSURE_PLATE,"&cHEAVY_WEIGHTED_PRESSURE_PLATE.tar","","&6A block of 32 HEAVY_WEIGHTED_PRESSURE_PLATE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_HONEYCOMB = new SlimefunItemStack("COMPRESSED_HONEYCOMB", Material.HONEYCOMB,"&cHONEYCOMB.tar","","&6A block of 32 HONEYCOMB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_HONEYCOMB_BLOCK = new SlimefunItemStack("COMPRESSED_HONEYCOMB_BLOCK", Material.HONEYCOMB_BLOCK,"&cHONEYCOMB_BLOCK.tar","","&6A block of 32 HONEYCOMB_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_HONEY_BLOCK = new SlimefunItemStack("COMPRESSED_HONEY_BLOCK", Material.HONEY_BLOCK,"&cHONEY_BLOCK.tar","","&6A block of 32 HONEY_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_HOPPER = new SlimefunItemStack("COMPRESSED_HOPPER", Material.HOPPER,"&cHOPPER.tar","","&6A block of 32 HOPPER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_HORN_CORAL = new SlimefunItemStack("COMPRESSED_HORN_CORAL", Material.HORN_CORAL,"&cHORN_CORAL.tar","","&6A block of 32 HORN_CORAL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_HORN_CORAL_BLOCK = new SlimefunItemStack("COMPRESSED_HORN_CORAL_BLOCK", Material.HORN_CORAL_BLOCK,"&cHORN_CORAL_BLOCK.tar","","&6A block of 32 HORN_CORAL_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_HORN_CORAL_FAN = new SlimefunItemStack("COMPRESSED_HORN_CORAL_FAN", Material.HORN_CORAL_FAN,"&cHORN_CORAL_FAN.tar","","&6A block of 32 HORN_CORAL_FAN","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ICE = new SlimefunItemStack("COMPRESSED_ICE", Material.ICE,"&cICE.tar","","&6A block of 32 ICE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_INK_SAC = new SlimefunItemStack("COMPRESSED_INK_SAC", Material.INK_SAC,"&cINK_SAC.tar","","&6A block of 32 INK_SAC","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_IRON_BARS = new SlimefunItemStack("COMPRESSED_IRON_BARS", Material.IRON_BARS,"&cIRON_BARS.tar","","&6A block of 32 IRON_BARS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_IRON_BLOCK = new SlimefunItemStack("COMPRESSED_IRON_BLOCK", Material.IRON_BLOCK,"&cIRON_BLOCK.tar","","&6A block of 32 IRON_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_IRON_DOOR = new SlimefunItemStack("COMPRESSED_IRON_DOOR", Material.IRON_DOOR,"&cIRON_DOOR.tar","","&6A block of 32 IRON_DOOR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_IRON_INGOT = new SlimefunItemStack("COMPRESSED_IRON_INGOT", Material.IRON_INGOT,"&cIRON_INGOT.tar","","&6A block of 32 IRON_INGOT","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_IRON_NUGGET = new SlimefunItemStack("COMPRESSED_IRON_NUGGET", Material.IRON_NUGGET,"&cIRON_NUGGET.tar","","&6A block of 32 IRON_NUGGET","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_IRON_ORE = new SlimefunItemStack("COMPRESSED_IRON_ORE", Material.IRON_ORE,"&cIRON_ORE.tar","","&6A block of 32 IRON_ORE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_IRON_TRAPDOOR = new SlimefunItemStack("COMPRESSED_IRON_TRAPDOOR", Material.IRON_TRAPDOOR,"&cIRON_TRAPDOOR.tar","","&6A block of 32 IRON_TRAPDOOR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ITEM_FRAME = new SlimefunItemStack("COMPRESSED_ITEM_FRAME", Material.ITEM_FRAME,"&cITEM_FRAME.tar","","&6A block of 32 ITEM_FRAME","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_JACK_O_LANTERN = new SlimefunItemStack("COMPRESSED_JACK_O_LANTERN", Material.JACK_O_LANTERN,"&cJACK_O_LANTERN.tar","","&6A block of 32 JACK_O_LANTERN","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_JUKEBOX = new SlimefunItemStack("COMPRESSED_JUKEBOX", Material.JUKEBOX,"&cJUKEBOX.tar","","&6A block of 32 JUKEBOX","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_JUNGLE_BUTTON = new SlimefunItemStack("COMPRESSED_JUNGLE_BUTTON", Material.JUNGLE_BUTTON,"&cJUNGLE_BUTTON.tar","","&6A block of 32 JUNGLE_BUTTON","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_JUNGLE_DOOR = new SlimefunItemStack("COMPRESSED_JUNGLE_DOOR", Material.JUNGLE_DOOR,"&cJUNGLE_DOOR.tar","","&6A block of 32 JUNGLE_DOOR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_JUNGLE_FENCE = new SlimefunItemStack("COMPRESSED_JUNGLE_FENCE", Material.JUNGLE_FENCE,"&cJUNGLE_FENCE.tar","","&6A block of 32 JUNGLE_FENCE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_JUNGLE_FENCE_GATE = new SlimefunItemStack("COMPRESSED_JUNGLE_FENCE_GATE", Material.JUNGLE_FENCE_GATE,"&cJUNGLE_FENCE_GATE.tar","","&6A block of 32 JUNGLE_FENCE_GATE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_JUNGLE_LEAVES = new SlimefunItemStack("COMPRESSED_JUNGLE_LEAVES", Material.JUNGLE_LEAVES,"&cJUNGLE_LEAVES.tar","","&6A block of 32 JUNGLE_LEAVES","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_JUNGLE_LOG = new SlimefunItemStack("COMPRESSED_JUNGLE_LOG", Material.JUNGLE_LOG,"&cJUNGLE_LOG.tar","","&6A block of 32 JUNGLE_LOG","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_JUNGLE_PLANKS = new SlimefunItemStack("COMPRESSED_JUNGLE_PLANKS", Material.JUNGLE_PLANKS,"&cJUNGLE_PLANKS.tar","","&6A block of 32 JUNGLE_PLANKS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_JUNGLE_PRESSURE_PLATE = new SlimefunItemStack("COMPRESSED_JUNGLE_PRESSURE_PLATE", Material.JUNGLE_PRESSURE_PLATE,"&cJUNGLE_PRESSURE_PLATE.tar","","&6A block of 32 JUNGLE_PRESSURE_PLATE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_JUNGLE_SAPLING = new SlimefunItemStack("COMPRESSED_JUNGLE_SAPLING", Material.JUNGLE_SAPLING,"&cJUNGLE_SAPLING.tar","","&6A block of 32 JUNGLE_SAPLING","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_JUNGLE_SLAB = new SlimefunItemStack("COMPRESSED_JUNGLE_SLAB", Material.JUNGLE_SLAB,"&cJUNGLE_SLAB.tar","","&6A block of 32 JUNGLE_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_JUNGLE_STAIRS = new SlimefunItemStack("COMPRESSED_JUNGLE_STAIRS", Material.JUNGLE_STAIRS,"&cJUNGLE_STAIRS.tar","","&6A block of 32 JUNGLE_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_JUNGLE_TRAPDOOR = new SlimefunItemStack("COMPRESSED_JUNGLE_TRAPDOOR", Material.JUNGLE_TRAPDOOR,"&cJUNGLE_TRAPDOOR.tar","","&6A block of 32 JUNGLE_TRAPDOOR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_JUNGLE_WOOD = new SlimefunItemStack("COMPRESSED_JUNGLE_WOOD", Material.JUNGLE_WOOD,"&cJUNGLE_WOOD.tar","","&6A block of 32 JUNGLE_WOOD","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_KELP = new SlimefunItemStack("COMPRESSED_KELP", Material.KELP,"&cKELP.tar","","&6A block of 32 KELP","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LADDER = new SlimefunItemStack("COMPRESSED_LADDER", Material.LADDER,"&cLADDER.tar","","&6A block of 32 LADDER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LANTERN = new SlimefunItemStack("COMPRESSED_LANTERN", Material.LANTERN,"&cLANTERN.tar","","&6A block of 32 LANTERN","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LAPIS_BLOCK = new SlimefunItemStack("COMPRESSED_LAPIS_BLOCK", Material.LAPIS_BLOCK,"&cLAPIS_BLOCK.tar","","&6A block of 32 LAPIS_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LAPIS_LAZULI = new SlimefunItemStack("COMPRESSED_LAPIS_LAZULI", Material.LAPIS_LAZULI,"&cLAPIS_LAZULI.tar","","&6A block of 32 LAPIS_LAZULI","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LAPIS_ORE = new SlimefunItemStack("COMPRESSED_LAPIS_ORE", Material.LAPIS_ORE,"&cLAPIS_ORE.tar","","&6A block of 32 LAPIS_ORE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LARGE_FERN = new SlimefunItemStack("COMPRESSED_LARGE_FERN", Material.LARGE_FERN,"&cLARGE_FERN.tar","","&6A block of 32 LARGE_FERN","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LEAD = new SlimefunItemStack("COMPRESSED_LEAD", Material.LEAD,"&cLEAD.tar","","&6A block of 32 LEAD","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LEATHER = new SlimefunItemStack("COMPRESSED_LEATHER", Material.LEATHER,"&cLEATHER.tar","","&6A block of 32 LEATHER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LECTERN = new SlimefunItemStack("COMPRESSED_LECTERN", Material.LECTERN,"&cLECTERN.tar","","&6A block of 32 LECTERN","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LEVER = new SlimefunItemStack("COMPRESSED_LEVER", Material.LEVER,"&cLEVER.tar","","&6A block of 32 LEVER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIGHT_BLUE_CARPET = new SlimefunItemStack("COMPRESSED_LIGHT_BLUE_CARPET", Material.LIGHT_BLUE_CARPET,"&cLIGHT_BLUE_CARPET.tar","","&6A block of 32 LIGHT_BLUE_CARPET","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIGHT_BLUE_CONCRETE = new SlimefunItemStack("COMPRESSED_LIGHT_BLUE_CONCRETE", Material.LIGHT_BLUE_CONCRETE,"&cLIGHT_BLUE_CONCRETE.tar","","&6A block of 32 LIGHT_BLUE_CONCRETE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIGHT_BLUE_CONCRETE_POWDER = new SlimefunItemStack("COMPRESSED_LIGHT_BLUE_CONCRETE_POWDER", Material.LIGHT_BLUE_CONCRETE_POWDER,"&cLIGHT_BLUE_CONCRETE_POWDER.tar","","&6A block of 32 LIGHT_BLUE_CONCRETE_POWDER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIGHT_BLUE_DYE = new SlimefunItemStack("COMPRESSED_LIGHT_BLUE_DYE", Material.LIGHT_BLUE_DYE,"&cLIGHT_BLUE_DYE.tar","","&6A block of 32 LIGHT_BLUE_DYE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIGHT_BLUE_GLAZED_TERRACOTTA = new SlimefunItemStack("COMPRESSED_LIGHT_BLUE_GLAZED_TERRACOTTA", Material.LIGHT_BLUE_GLAZED_TERRACOTTA,"&cLIGHT_BLUE_GLAZED_TERRACOTTA.tar","","&6A block of 32 LIGHT_BLUE_GLAZED_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIGHT_BLUE_STAINED_GLASS = new SlimefunItemStack("COMPRESSED_LIGHT_BLUE_STAINED_GLASS", Material.LIGHT_BLUE_STAINED_GLASS,"&cLIGHT_BLUE_STAINED_GLASS.tar","","&6A block of 32 LIGHT_BLUE_STAINED_GLASS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIGHT_BLUE_STAINED_GLASS_PANE = new SlimefunItemStack("COMPRESSED_LIGHT_BLUE_STAINED_GLASS_PANE", Material.LIGHT_BLUE_STAINED_GLASS_PANE,"&cLIGHT_BLUE_STAINED_GLASS_PANE.tar","","&6A block of 32 LIGHT_BLUE_STAINED_GLASS_PANE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIGHT_BLUE_TERRACOTTA = new SlimefunItemStack("COMPRESSED_LIGHT_BLUE_TERRACOTTA", Material.LIGHT_BLUE_TERRACOTTA,"&cLIGHT_BLUE_TERRACOTTA.tar","","&6A block of 32 LIGHT_BLUE_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIGHT_BLUE_WOOL = new SlimefunItemStack("COMPRESSED_LIGHT_BLUE_WOOL", Material.LIGHT_BLUE_WOOL,"&cLIGHT_BLUE_WOOL.tar","","&6A block of 32 LIGHT_BLUE_WOOL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIGHT_GRAY_CARPET = new SlimefunItemStack("COMPRESSED_LIGHT_GRAY_CARPET", Material.LIGHT_GRAY_CARPET,"&cLIGHT_GRAY_CARPET.tar","","&6A block of 32 LIGHT_GRAY_CARPET","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIGHT_GRAY_CONCRETE = new SlimefunItemStack("COMPRESSED_LIGHT_GRAY_CONCRETE", Material.LIGHT_GRAY_CONCRETE,"&cLIGHT_GRAY_CONCRETE.tar","","&6A block of 32 LIGHT_GRAY_CONCRETE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIGHT_GRAY_CONCRETE_POWDER = new SlimefunItemStack("COMPRESSED_LIGHT_GRAY_CONCRETE_POWDER", Material.LIGHT_GRAY_CONCRETE_POWDER,"&cLIGHT_GRAY_CONCRETE_POWDER.tar","","&6A block of 32 LIGHT_GRAY_CONCRETE_POWDER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIGHT_GRAY_DYE = new SlimefunItemStack("COMPRESSED_LIGHT_GRAY_DYE", Material.LIGHT_GRAY_DYE,"&cLIGHT_GRAY_DYE.tar","","&6A block of 32 LIGHT_GRAY_DYE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIGHT_GRAY_GLAZED_TERRACOTTA = new SlimefunItemStack("COMPRESSED_LIGHT_GRAY_GLAZED_TERRACOTTA", Material.LIGHT_GRAY_GLAZED_TERRACOTTA,"&cLIGHT_GRAY_GLAZED_TERRACOTTA.tar","","&6A block of 32 LIGHT_GRAY_GLAZED_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIGHT_GRAY_STAINED_GLASS = new SlimefunItemStack("COMPRESSED_LIGHT_GRAY_STAINED_GLASS", Material.LIGHT_GRAY_STAINED_GLASS,"&cLIGHT_GRAY_STAINED_GLASS.tar","","&6A block of 32 LIGHT_GRAY_STAINED_GLASS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIGHT_GRAY_STAINED_GLASS_PANE = new SlimefunItemStack("COMPRESSED_LIGHT_GRAY_STAINED_GLASS_PANE", Material.LIGHT_GRAY_STAINED_GLASS_PANE,"&cLIGHT_GRAY_STAINED_GLASS_PANE.tar","","&6A block of 32 LIGHT_GRAY_STAINED_GLASS_PANE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIGHT_GRAY_TERRACOTTA = new SlimefunItemStack("COMPRESSED_LIGHT_GRAY_TERRACOTTA", Material.LIGHT_GRAY_TERRACOTTA,"&cLIGHT_GRAY_TERRACOTTA.tar","","&6A block of 32 LIGHT_GRAY_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIGHT_GRAY_WOOL = new SlimefunItemStack("COMPRESSED_LIGHT_GRAY_WOOL", Material.LIGHT_GRAY_WOOL,"&cLIGHT_GRAY_WOOL.tar","","&6A block of 32 LIGHT_GRAY_WOOL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIGHT_WEIGHTED_PRESSURE_PLATE = new SlimefunItemStack("COMPRESSED_LIGHT_WEIGHTED_PRESSURE_PLATE", Material.LIGHT_WEIGHTED_PRESSURE_PLATE,"&cLIGHT_WEIGHTED_PRESSURE_PLATE.tar","","&6A block of 32 LIGHT_WEIGHTED_PRESSURE_PLATE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LILAC = new SlimefunItemStack("COMPRESSED_LILAC", Material.LILAC,"&cLILAC.tar","","&6A block of 32 LILAC","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LILY_OF_THE_VALLEY = new SlimefunItemStack("COMPRESSED_LILY_OF_THE_VALLEY", Material.LILY_OF_THE_VALLEY,"&cLILY_OF_THE_VALLEY.tar","","&6A block of 32 LILY_OF_THE_VALLEY","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LILY_PAD = new SlimefunItemStack("COMPRESSED_LILY_PAD", Material.LILY_PAD,"&cLILY_PAD.tar","","&6A block of 32 LILY_PAD","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIME_CARPET = new SlimefunItemStack("COMPRESSED_LIME_CARPET", Material.LIME_CARPET,"&cLIME_CARPET.tar","","&6A block of 32 LIME_CARPET","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIME_CONCRETE = new SlimefunItemStack("COMPRESSED_LIME_CONCRETE", Material.LIME_CONCRETE,"&cLIME_CONCRETE.tar","","&6A block of 32 LIME_CONCRETE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIME_CONCRETE_POWDER = new SlimefunItemStack("COMPRESSED_LIME_CONCRETE_POWDER", Material.LIME_CONCRETE_POWDER,"&cLIME_CONCRETE_POWDER.tar","","&6A block of 32 LIME_CONCRETE_POWDER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIME_DYE = new SlimefunItemStack("COMPRESSED_LIME_DYE", Material.LIME_DYE,"&cLIME_DYE.tar","","&6A block of 32 LIME_DYE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIME_GLAZED_TERRACOTTA = new SlimefunItemStack("COMPRESSED_LIME_GLAZED_TERRACOTTA", Material.LIME_GLAZED_TERRACOTTA,"&cLIME_GLAZED_TERRACOTTA.tar","","&6A block of 32 LIME_GLAZED_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIME_STAINED_GLASS = new SlimefunItemStack("COMPRESSED_LIME_STAINED_GLASS", Material.LIME_STAINED_GLASS,"&cLIME_STAINED_GLASS.tar","","&6A block of 32 LIME_STAINED_GLASS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIME_STAINED_GLASS_PANE = new SlimefunItemStack("COMPRESSED_LIME_STAINED_GLASS_PANE", Material.LIME_STAINED_GLASS_PANE,"&cLIME_STAINED_GLASS_PANE.tar","","&6A block of 32 LIME_STAINED_GLASS_PANE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIME_TERRACOTTA = new SlimefunItemStack("COMPRESSED_LIME_TERRACOTTA", Material.LIME_TERRACOTTA,"&cLIME_TERRACOTTA.tar","","&6A block of 32 LIME_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LIME_WOOL = new SlimefunItemStack("COMPRESSED_LIME_WOOL", Material.LIME_WOOL,"&cLIME_WOOL.tar","","&6A block of 32 LIME_WOOL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_LOOM = new SlimefunItemStack("COMPRESSED_LOOM", Material.LOOM,"&cLOOM.tar","","&6A block of 32 LOOM","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MAGENTA_CARPET = new SlimefunItemStack("COMPRESSED_MAGENTA_CARPET", Material.MAGENTA_CARPET,"&cMAGENTA_CARPET.tar","","&6A block of 32 MAGENTA_CARPET","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MAGENTA_CONCRETE = new SlimefunItemStack("COMPRESSED_MAGENTA_CONCRETE", Material.MAGENTA_CONCRETE,"&cMAGENTA_CONCRETE.tar","","&6A block of 32 MAGENTA_CONCRETE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MAGENTA_CONCRETE_POWDER = new SlimefunItemStack("COMPRESSED_MAGENTA_CONCRETE_POWDER", Material.MAGENTA_CONCRETE_POWDER,"&cMAGENTA_CONCRETE_POWDER.tar","","&6A block of 32 MAGENTA_CONCRETE_POWDER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MAGENTA_DYE = new SlimefunItemStack("COMPRESSED_MAGENTA_DYE", Material.MAGENTA_DYE,"&cMAGENTA_DYE.tar","","&6A block of 32 MAGENTA_DYE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MAGENTA_GLAZED_TERRACOTTA = new SlimefunItemStack("COMPRESSED_MAGENTA_GLAZED_TERRACOTTA", Material.MAGENTA_GLAZED_TERRACOTTA,"&cMAGENTA_GLAZED_TERRACOTTA.tar","","&6A block of 32 MAGENTA_GLAZED_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MAGENTA_STAINED_GLASS = new SlimefunItemStack("COMPRESSED_MAGENTA_STAINED_GLASS", Material.MAGENTA_STAINED_GLASS,"&cMAGENTA_STAINED_GLASS.tar","","&6A block of 32 MAGENTA_STAINED_GLASS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MAGENTA_STAINED_GLASS_PANE = new SlimefunItemStack("COMPRESSED_MAGENTA_STAINED_GLASS_PANE", Material.MAGENTA_STAINED_GLASS_PANE,"&cMAGENTA_STAINED_GLASS_PANE.tar","","&6A block of 32 MAGENTA_STAINED_GLASS_PANE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MAGENTA_TERRACOTTA = new SlimefunItemStack("COMPRESSED_MAGENTA_TERRACOTTA", Material.MAGENTA_TERRACOTTA,"&cMAGENTA_TERRACOTTA.tar","","&6A block of 32 MAGENTA_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MAGENTA_WOOL = new SlimefunItemStack("COMPRESSED_MAGENTA_WOOL", Material.MAGENTA_WOOL,"&cMAGENTA_WOOL.tar","","&6A block of 32 MAGENTA_WOOL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MAGMA_BLOCK = new SlimefunItemStack("COMPRESSED_MAGMA_BLOCK", Material.MAGMA_BLOCK,"&cMAGMA_BLOCK.tar","","&6A block of 32 MAGMA_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MAGMA_CREAM = new SlimefunItemStack("COMPRESSED_MAGMA_CREAM", Material.MAGMA_CREAM,"&cMAGMA_CREAM.tar","","&6A block of 32 MAGMA_CREAM","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MELON = new SlimefunItemStack("COMPRESSED_MELON", Material.MELON,"&cMELON.tar","","&6A block of 32 MELON","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MELON_SEEDS = new SlimefunItemStack("COMPRESSED_MELON_SEEDS", Material.MELON_SEEDS,"&cMELON_SEEDS.tar","","&6A block of 32 MELON_SEEDS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MELON_SLICE = new SlimefunItemStack("COMPRESSED_MELON_SLICE", Material.MELON_SLICE,"&cMELON_SLICE.tar","","&6A block of 32 MELON_SLICE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MOSSY_COBBLESTONE = new SlimefunItemStack("COMPRESSED_MOSSY_COBBLESTONE", Material.MOSSY_COBBLESTONE,"&cMOSSY_COBBLESTONE.tar","","&6A block of 32 MOSSY_COBBLESTONE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MOSSY_COBBLESTONE_SLAB = new SlimefunItemStack("COMPRESSED_MOSSY_COBBLESTONE_SLAB", Material.MOSSY_COBBLESTONE_SLAB,"&cMOSSY_COBBLESTONE_SLAB.tar","","&6A block of 32 MOSSY_COBBLESTONE_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MOSSY_COBBLESTONE_STAIRS = new SlimefunItemStack("COMPRESSED_MOSSY_COBBLESTONE_STAIRS", Material.MOSSY_COBBLESTONE_STAIRS,"&cMOSSY_COBBLESTONE_STAIRS.tar","","&6A block of 32 MOSSY_COBBLESTONE_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MOSSY_COBBLESTONE_WALL = new SlimefunItemStack("COMPRESSED_MOSSY_COBBLESTONE_WALL", Material.MOSSY_COBBLESTONE_WALL,"&cMOSSY_COBBLESTONE_WALL.tar","","&6A block of 32 MOSSY_COBBLESTONE_WALL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MOSSY_STONE_BRICKS = new SlimefunItemStack("COMPRESSED_MOSSY_STONE_BRICKS", Material.MOSSY_STONE_BRICKS,"&cMOSSY_STONE_BRICKS.tar","","&6A block of 32 MOSSY_STONE_BRICKS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MOSSY_STONE_BRICK_SLAB = new SlimefunItemStack("COMPRESSED_MOSSY_STONE_BRICK_SLAB", Material.MOSSY_STONE_BRICK_SLAB,"&cMOSSY_STONE_BRICK_SLAB.tar","","&6A block of 32 MOSSY_STONE_BRICK_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MOSSY_STONE_BRICK_STAIRS = new SlimefunItemStack("COMPRESSED_MOSSY_STONE_BRICK_STAIRS", Material.MOSSY_STONE_BRICK_STAIRS,"&cMOSSY_STONE_BRICK_STAIRS.tar","","&6A block of 32 MOSSY_STONE_BRICK_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MOSSY_STONE_BRICK_WALL = new SlimefunItemStack("COMPRESSED_MOSSY_STONE_BRICK_WALL", Material.MOSSY_STONE_BRICK_WALL,"&cMOSSY_STONE_BRICK_WALL.tar","","&6A block of 32 MOSSY_STONE_BRICK_WALL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MUTTON = new SlimefunItemStack("COMPRESSED_MUTTON", Material.MUTTON,"&cMUTTON.tar","","&6A block of 32 MUTTON","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_MYCELIUM = new SlimefunItemStack("COMPRESSED_MYCELIUM", Material.MYCELIUM,"&cMYCELIUM.tar","","&6A block of 32 MYCELIUM","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_NAUTILUS_SHELL = new SlimefunItemStack("COMPRESSED_NAUTILUS_SHELL", Material.NAUTILUS_SHELL,"&cNAUTILUS_SHELL.tar","","&6A block of 32 NAUTILUS_SHELL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_NETHERRACK = new SlimefunItemStack("COMPRESSED_NETHERRACK", Material.NETHERRACK,"&cNETHERRACK.tar","","&6A block of 32 NETHERRACK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_NETHER_BRICK = new SlimefunItemStack("COMPRESSED_NETHER_BRICK", Material.NETHER_BRICK,"&cNETHER_BRICK.tar","","&6A block of 32 NETHER_BRICK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_NETHER_BRICKS = new SlimefunItemStack("COMPRESSED_NETHER_BRICKS", Material.NETHER_BRICKS,"&cNETHER_BRICKS.tar","","&6A block of 32 NETHER_BRICKS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_NETHER_BRICK_FENCE = new SlimefunItemStack("COMPRESSED_NETHER_BRICK_FENCE", Material.NETHER_BRICK_FENCE,"&cNETHER_BRICK_FENCE.tar","","&6A block of 32 NETHER_BRICK_FENCE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_NETHER_BRICK_SLAB = new SlimefunItemStack("COMPRESSED_NETHER_BRICK_SLAB", Material.NETHER_BRICK_SLAB,"&cNETHER_BRICK_SLAB.tar","","&6A block of 32 NETHER_BRICK_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_NETHER_BRICK_STAIRS = new SlimefunItemStack("COMPRESSED_NETHER_BRICK_STAIRS", Material.NETHER_BRICK_STAIRS,"&cNETHER_BRICK_STAIRS.tar","","&6A block of 32 NETHER_BRICK_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_NETHER_BRICK_WALL = new SlimefunItemStack("COMPRESSED_NETHER_BRICK_WALL", Material.NETHER_BRICK_WALL,"&cNETHER_BRICK_WALL.tar","","&6A block of 32 NETHER_BRICK_WALL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_NETHER_QUARTZ_ORE = new SlimefunItemStack("COMPRESSED_NETHER_QUARTZ_ORE", Material.NETHER_QUARTZ_ORE,"&cNETHER_QUARTZ_ORE.tar","","&6A block of 32 NETHER_QUARTZ_ORE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_NETHER_STAR = new SlimefunItemStack("COMPRESSED_NETHER_STAR", Material.NETHER_STAR,"&cNETHER_STAR.tar","","&6A block of 32 NETHER_STAR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_NETHER_WART = new SlimefunItemStack("COMPRESSED_NETHER_WART", Material.NETHER_WART,"&cNETHER_WART.tar","","&6A block of 32 NETHER_WART","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_NETHER_WART_BLOCK = new SlimefunItemStack("COMPRESSED_NETHER_WART_BLOCK", Material.NETHER_WART_BLOCK,"&cNETHER_WART_BLOCK.tar","","&6A block of 32 NETHER_WART_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_NOTE_BLOCK = new SlimefunItemStack("COMPRESSED_NOTE_BLOCK", Material.NOTE_BLOCK,"&cNOTE_BLOCK.tar","","&6A block of 32 NOTE_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_OAK_BUTTON = new SlimefunItemStack("COMPRESSED_OAK_BUTTON", Material.OAK_BUTTON,"&cOAK_BUTTON.tar","","&6A block of 32 OAK_BUTTON","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_OAK_DOOR = new SlimefunItemStack("COMPRESSED_OAK_DOOR", Material.OAK_DOOR,"&cOAK_DOOR.tar","","&6A block of 32 OAK_DOOR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_OAK_FENCE = new SlimefunItemStack("COMPRESSED_OAK_FENCE", Material.OAK_FENCE,"&cOAK_FENCE.tar","","&6A block of 32 OAK_FENCE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_OAK_FENCE_GATE = new SlimefunItemStack("COMPRESSED_OAK_FENCE_GATE", Material.OAK_FENCE_GATE,"&cOAK_FENCE_GATE.tar","","&6A block of 32 OAK_FENCE_GATE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_OAK_LEAVES = new SlimefunItemStack("COMPRESSED_OAK_LEAVES", Material.OAK_LEAVES,"&cOAK_LEAVES.tar","","&6A block of 32 OAK_LEAVES","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_OAK_LOG = new SlimefunItemStack("COMPRESSED_OAK_LOG", Material.OAK_LOG,"&cOAK_LOG.tar","","&6A block of 32 OAK_LOG","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_OAK_PLANKS = new SlimefunItemStack("COMPRESSED_OAK_PLANKS", Material.OAK_PLANKS,"&cOAK_PLANKS.tar","","&6A block of 32 OAK_PLANKS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_OAK_PRESSURE_PLATE = new SlimefunItemStack("COMPRESSED_OAK_PRESSURE_PLATE", Material.OAK_PRESSURE_PLATE,"&cOAK_PRESSURE_PLATE.tar","","&6A block of 32 OAK_PRESSURE_PLATE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_OAK_SAPLING = new SlimefunItemStack("COMPRESSED_OAK_SAPLING", Material.OAK_SAPLING,"&cOAK_SAPLING.tar","","&6A block of 32 OAK_SAPLING","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_OAK_SLAB = new SlimefunItemStack("COMPRESSED_OAK_SLAB", Material.OAK_SLAB,"&cOAK_SLAB.tar","","&6A block of 32 OAK_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_OAK_STAIRS = new SlimefunItemStack("COMPRESSED_OAK_STAIRS", Material.OAK_STAIRS,"&cOAK_STAIRS.tar","","&6A block of 32 OAK_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_OAK_TRAPDOOR = new SlimefunItemStack("COMPRESSED_OAK_TRAPDOOR", Material.OAK_TRAPDOOR,"&cOAK_TRAPDOOR.tar","","&6A block of 32 OAK_TRAPDOOR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_OAK_WOOD = new SlimefunItemStack("COMPRESSED_OAK_WOOD", Material.OAK_WOOD,"&cOAK_WOOD.tar","","&6A block of 32 OAK_WOOD","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_OBSERVER = new SlimefunItemStack("COMPRESSED_OBSERVER", Material.OBSERVER,"&cOBSERVER.tar","","&6A block of 32 OBSERVER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_OBSIDIAN = new SlimefunItemStack("COMPRESSED_OBSIDIAN", Material.OBSIDIAN,"&cOBSIDIAN.tar","","&6A block of 32 OBSIDIAN","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ORANGE_CARPET = new SlimefunItemStack("COMPRESSED_ORANGE_CARPET", Material.ORANGE_CARPET,"&cORANGE_CARPET.tar","","&6A block of 32 ORANGE_CARPET","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ORANGE_CONCRETE = new SlimefunItemStack("COMPRESSED_ORANGE_CONCRETE", Material.ORANGE_CONCRETE,"&cORANGE_CONCRETE.tar","","&6A block of 32 ORANGE_CONCRETE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ORANGE_CONCRETE_POWDER = new SlimefunItemStack("COMPRESSED_ORANGE_CONCRETE_POWDER", Material.ORANGE_CONCRETE_POWDER,"&cORANGE_CONCRETE_POWDER.tar","","&6A block of 32 ORANGE_CONCRETE_POWDER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ORANGE_DYE = new SlimefunItemStack("COMPRESSED_ORANGE_DYE", Material.ORANGE_DYE,"&cORANGE_DYE.tar","","&6A block of 32 ORANGE_DYE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ORANGE_GLAZED_TERRACOTTA = new SlimefunItemStack("COMPRESSED_ORANGE_GLAZED_TERRACOTTA", Material.ORANGE_GLAZED_TERRACOTTA,"&cORANGE_GLAZED_TERRACOTTA.tar","","&6A block of 32 ORANGE_GLAZED_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ORANGE_STAINED_GLASS = new SlimefunItemStack("COMPRESSED_ORANGE_STAINED_GLASS", Material.ORANGE_STAINED_GLASS,"&cORANGE_STAINED_GLASS.tar","","&6A block of 32 ORANGE_STAINED_GLASS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ORANGE_STAINED_GLASS_PANE = new SlimefunItemStack("COMPRESSED_ORANGE_STAINED_GLASS_PANE", Material.ORANGE_STAINED_GLASS_PANE,"&cORANGE_STAINED_GLASS_PANE.tar","","&6A block of 32 ORANGE_STAINED_GLASS_PANE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ORANGE_TERRACOTTA = new SlimefunItemStack("COMPRESSED_ORANGE_TERRACOTTA", Material.ORANGE_TERRACOTTA,"&cORANGE_TERRACOTTA.tar","","&6A block of 32 ORANGE_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ORANGE_TULIP = new SlimefunItemStack("COMPRESSED_ORANGE_TULIP", Material.ORANGE_TULIP,"&cORANGE_TULIP.tar","","&6A block of 32 ORANGE_TULIP","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ORANGE_WOOL = new SlimefunItemStack("COMPRESSED_ORANGE_WOOL", Material.ORANGE_WOOL,"&cORANGE_WOOL.tar","","&6A block of 32 ORANGE_WOOL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_OXEYE_DAISY = new SlimefunItemStack("COMPRESSED_OXEYE_DAISY", Material.OXEYE_DAISY,"&cOXEYE_DAISY.tar","","&6A block of 32 OXEYE_DAISY","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PACKED_ICE = new SlimefunItemStack("COMPRESSED_PACKED_ICE", Material.PACKED_ICE,"&cPACKED_ICE.tar","","&6A block of 32 PACKED_ICE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PAINTING = new SlimefunItemStack("COMPRESSED_PAINTING", Material.PAINTING,"&cPAINTING.tar","","&6A block of 32 PAINTING","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PAPER = new SlimefunItemStack("COMPRESSED_PAPER", Material.PAPER,"&cPAPER.tar","","&6A block of 32 PAPER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PEONY = new SlimefunItemStack("COMPRESSED_PEONY", Material.PEONY,"&cPEONY.tar","","&6A block of 32 PEONY","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PHANTOM_MEMBRANE = new SlimefunItemStack("COMPRESSED_PHANTOM_MEMBRANE", Material.PHANTOM_MEMBRANE,"&cPHANTOM_MEMBRANE.tar","","&6A block of 32 PHANTOM_MEMBRANE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PINK_CARPET = new SlimefunItemStack("COMPRESSED_PINK_CARPET", Material.PINK_CARPET,"&cPINK_CARPET.tar","","&6A block of 32 PINK_CARPET","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PINK_CONCRETE = new SlimefunItemStack("COMPRESSED_PINK_CONCRETE", Material.PINK_CONCRETE,"&cPINK_CONCRETE.tar","","&6A block of 32 PINK_CONCRETE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PINK_CONCRETE_POWDER = new SlimefunItemStack("COMPRESSED_PINK_CONCRETE_POWDER", Material.PINK_CONCRETE_POWDER,"&cPINK_CONCRETE_POWDER.tar","","&6A block of 32 PINK_CONCRETE_POWDER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PINK_DYE = new SlimefunItemStack("COMPRESSED_PINK_DYE", Material.PINK_DYE,"&cPINK_DYE.tar","","&6A block of 32 PINK_DYE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PINK_GLAZED_TERRACOTTA = new SlimefunItemStack("COMPRESSED_PINK_GLAZED_TERRACOTTA", Material.PINK_GLAZED_TERRACOTTA,"&cPINK_GLAZED_TERRACOTTA.tar","","&6A block of 32 PINK_GLAZED_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PINK_STAINED_GLASS = new SlimefunItemStack("COMPRESSED_PINK_STAINED_GLASS", Material.PINK_STAINED_GLASS,"&cPINK_STAINED_GLASS.tar","","&6A block of 32 PINK_STAINED_GLASS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PINK_STAINED_GLASS_PANE = new SlimefunItemStack("COMPRESSED_PINK_STAINED_GLASS_PANE", Material.PINK_STAINED_GLASS_PANE,"&cPINK_STAINED_GLASS_PANE.tar","","&6A block of 32 PINK_STAINED_GLASS_PANE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PINK_TERRACOTTA = new SlimefunItemStack("COMPRESSED_PINK_TERRACOTTA", Material.PINK_TERRACOTTA,"&cPINK_TERRACOTTA.tar","","&6A block of 32 PINK_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PINK_TULIP = new SlimefunItemStack("COMPRESSED_PINK_TULIP", Material.PINK_TULIP,"&cPINK_TULIP.tar","","&6A block of 32 PINK_TULIP","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PINK_WOOL = new SlimefunItemStack("COMPRESSED_PINK_WOOL", Material.PINK_WOOL,"&cPINK_WOOL.tar","","&6A block of 32 PINK_WOOL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PISTON = new SlimefunItemStack("COMPRESSED_PISTON", Material.PISTON,"&cPISTON.tar","","&6A block of 32 PISTON","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PODZOL = new SlimefunItemStack("COMPRESSED_PODZOL", Material.PODZOL,"&cPODZOL.tar","","&6A block of 32 PODZOL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_POISONOUS_POTATO = new SlimefunItemStack("COMPRESSED_POISONOUS_POTATO", Material.POISONOUS_POTATO,"&cPOISONOUS_POTATO.tar","","&6A block of 32 POISONOUS_POTATO","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_POLISHED_ANDESITE = new SlimefunItemStack("COMPRESSED_POLISHED_ANDESITE", Material.POLISHED_ANDESITE,"&cPOLISHED_ANDESITE.tar","","&6A block of 32 POLISHED_ANDESITE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_POLISHED_ANDESITE_SLAB = new SlimefunItemStack("COMPRESSED_POLISHED_ANDESITE_SLAB", Material.POLISHED_ANDESITE_SLAB,"&cPOLISHED_ANDESITE_SLAB.tar","","&6A block of 32 POLISHED_ANDESITE_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_POLISHED_ANDESITE_STAIRS = new SlimefunItemStack("COMPRESSED_POLISHED_ANDESITE_STAIRS", Material.POLISHED_ANDESITE_STAIRS,"&cPOLISHED_ANDESITE_STAIRS.tar","","&6A block of 32 POLISHED_ANDESITE_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_POLISHED_DIORITE = new SlimefunItemStack("COMPRESSED_POLISHED_DIORITE", Material.POLISHED_DIORITE,"&cPOLISHED_DIORITE.tar","","&6A block of 32 POLISHED_DIORITE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_POLISHED_DIORITE_SLAB = new SlimefunItemStack("COMPRESSED_POLISHED_DIORITE_SLAB", Material.POLISHED_DIORITE_SLAB,"&cPOLISHED_DIORITE_SLAB.tar","","&6A block of 32 POLISHED_DIORITE_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_POLISHED_DIORITE_STAIRS = new SlimefunItemStack("COMPRESSED_POLISHED_DIORITE_STAIRS", Material.POLISHED_DIORITE_STAIRS,"&cPOLISHED_DIORITE_STAIRS.tar","","&6A block of 32 POLISHED_DIORITE_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_POLISHED_GRANITE = new SlimefunItemStack("COMPRESSED_POLISHED_GRANITE", Material.POLISHED_GRANITE,"&cPOLISHED_GRANITE.tar","","&6A block of 32 POLISHED_GRANITE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_POLISHED_GRANITE_SLAB = new SlimefunItemStack("COMPRESSED_POLISHED_GRANITE_SLAB", Material.POLISHED_GRANITE_SLAB,"&cPOLISHED_GRANITE_SLAB.tar","","&6A block of 32 POLISHED_GRANITE_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_POLISHED_GRANITE_STAIRS = new SlimefunItemStack("COMPRESSED_POLISHED_GRANITE_STAIRS", Material.POLISHED_GRANITE_STAIRS,"&cPOLISHED_GRANITE_STAIRS.tar","","&6A block of 32 POLISHED_GRANITE_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_POPPED_CHORUS_FRUIT = new SlimefunItemStack("COMPRESSED_POPPED_CHORUS_FRUIT", Material.POPPED_CHORUS_FRUIT,"&cPOPPED_CHORUS_FRUIT.tar","","&6A block of 32 POPPED_CHORUS_FRUIT","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_POPPY = new SlimefunItemStack("COMPRESSED_POPPY", Material.POPPY,"&cPOPPY.tar","","&6A block of 32 POPPY","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PORKCHOP = new SlimefunItemStack("COMPRESSED_PORKCHOP", Material.PORKCHOP,"&cPORKCHOP.tar","","&6A block of 32 PORKCHOP","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_POTATO = new SlimefunItemStack("COMPRESSED_POTATO", Material.POTATO,"&cPOTATO.tar","","&6A block of 32 POTATO","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_POWERED_RAIL = new SlimefunItemStack("COMPRESSED_POWERED_RAIL", Material.POWERED_RAIL,"&cPOWERED_RAIL.tar","","&6A block of 32 POWERED_RAIL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PRISMARINE = new SlimefunItemStack("COMPRESSED_PRISMARINE", Material.PRISMARINE,"&cPRISMARINE.tar","","&6A block of 32 PRISMARINE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PRISMARINE_BRICKS = new SlimefunItemStack("COMPRESSED_PRISMARINE_BRICKS", Material.PRISMARINE_BRICKS,"&cPRISMARINE_BRICKS.tar","","&6A block of 32 PRISMARINE_BRICKS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PRISMARINE_BRICK_SLAB = new SlimefunItemStack("COMPRESSED_PRISMARINE_BRICK_SLAB", Material.PRISMARINE_BRICK_SLAB,"&cPRISMARINE_BRICK_SLAB.tar","","&6A block of 32 PRISMARINE_BRICK_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PRISMARINE_BRICK_STAIRS = new SlimefunItemStack("COMPRESSED_PRISMARINE_BRICK_STAIRS", Material.PRISMARINE_BRICK_STAIRS,"&cPRISMARINE_BRICK_STAIRS.tar","","&6A block of 32 PRISMARINE_BRICK_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PRISMARINE_CRYSTALS = new SlimefunItemStack("COMPRESSED_PRISMARINE_CRYSTALS", Material.PRISMARINE_CRYSTALS,"&cPRISMARINE_CRYSTALS.tar","","&6A block of 32 PRISMARINE_CRYSTALS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PRISMARINE_SHARD = new SlimefunItemStack("COMPRESSED_PRISMARINE_SHARD", Material.PRISMARINE_SHARD,"&cPRISMARINE_SHARD.tar","","&6A block of 32 PRISMARINE_SHARD","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PRISMARINE_SLAB = new SlimefunItemStack("COMPRESSED_PRISMARINE_SLAB", Material.PRISMARINE_SLAB,"&cPRISMARINE_SLAB.tar","","&6A block of 32 PRISMARINE_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PRISMARINE_STAIRS = new SlimefunItemStack("COMPRESSED_PRISMARINE_STAIRS", Material.PRISMARINE_STAIRS,"&cPRISMARINE_STAIRS.tar","","&6A block of 32 PRISMARINE_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PRISMARINE_WALL = new SlimefunItemStack("COMPRESSED_PRISMARINE_WALL", Material.PRISMARINE_WALL,"&cPRISMARINE_WALL.tar","","&6A block of 32 PRISMARINE_WALL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PUFFERFISH = new SlimefunItemStack("COMPRESSED_PUFFERFISH", Material.PUFFERFISH,"&cPUFFERFISH.tar","","&6A block of 32 PUFFERFISH","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PUMPKIN = new SlimefunItemStack("COMPRESSED_PUMPKIN", Material.PUMPKIN,"&cPUMPKIN.tar","","&6A block of 32 PUMPKIN","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PUMPKIN_PIE = new SlimefunItemStack("COMPRESSED_PUMPKIN_PIE", Material.PUMPKIN_PIE,"&cPUMPKIN_PIE.tar","","&6A block of 32 PUMPKIN_PIE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PUMPKIN_SEEDS = new SlimefunItemStack("COMPRESSED_PUMPKIN_SEEDS", Material.PUMPKIN_SEEDS,"&cPUMPKIN_SEEDS.tar","","&6A block of 32 PUMPKIN_SEEDS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PURPLE_CARPET = new SlimefunItemStack("COMPRESSED_PURPLE_CARPET", Material.PURPLE_CARPET,"&cPURPLE_CARPET.tar","","&6A block of 32 PURPLE_CARPET","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PURPLE_CONCRETE = new SlimefunItemStack("COMPRESSED_PURPLE_CONCRETE", Material.PURPLE_CONCRETE,"&cPURPLE_CONCRETE.tar","","&6A block of 32 PURPLE_CONCRETE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PURPLE_CONCRETE_POWDER = new SlimefunItemStack("COMPRESSED_PURPLE_CONCRETE_POWDER", Material.PURPLE_CONCRETE_POWDER,"&cPURPLE_CONCRETE_POWDER.tar","","&6A block of 32 PURPLE_CONCRETE_POWDER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PURPLE_DYE = new SlimefunItemStack("COMPRESSED_PURPLE_DYE", Material.PURPLE_DYE,"&cPURPLE_DYE.tar","","&6A block of 32 PURPLE_DYE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PURPLE_GLAZED_TERRACOTTA = new SlimefunItemStack("COMPRESSED_PURPLE_GLAZED_TERRACOTTA", Material.PURPLE_GLAZED_TERRACOTTA,"&cPURPLE_GLAZED_TERRACOTTA.tar","","&6A block of 32 PURPLE_GLAZED_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PURPLE_STAINED_GLASS = new SlimefunItemStack("COMPRESSED_PURPLE_STAINED_GLASS", Material.PURPLE_STAINED_GLASS,"&cPURPLE_STAINED_GLASS.tar","","&6A block of 32 PURPLE_STAINED_GLASS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PURPLE_STAINED_GLASS_PANE = new SlimefunItemStack("COMPRESSED_PURPLE_STAINED_GLASS_PANE", Material.PURPLE_STAINED_GLASS_PANE,"&cPURPLE_STAINED_GLASS_PANE.tar","","&6A block of 32 PURPLE_STAINED_GLASS_PANE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PURPLE_TERRACOTTA = new SlimefunItemStack("COMPRESSED_PURPLE_TERRACOTTA", Material.PURPLE_TERRACOTTA,"&cPURPLE_TERRACOTTA.tar","","&6A block of 32 PURPLE_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PURPLE_WOOL = new SlimefunItemStack("COMPRESSED_PURPLE_WOOL", Material.PURPLE_WOOL,"&cPURPLE_WOOL.tar","","&6A block of 32 PURPLE_WOOL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PURPUR_BLOCK = new SlimefunItemStack("COMPRESSED_PURPUR_BLOCK", Material.PURPUR_BLOCK,"&cPURPUR_BLOCK.tar","","&6A block of 32 PURPUR_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PURPUR_PILLAR = new SlimefunItemStack("COMPRESSED_PURPUR_PILLAR", Material.PURPUR_PILLAR,"&cPURPUR_PILLAR.tar","","&6A block of 32 PURPUR_PILLAR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PURPUR_SLAB = new SlimefunItemStack("COMPRESSED_PURPUR_SLAB", Material.PURPUR_SLAB,"&cPURPUR_SLAB.tar","","&6A block of 32 PURPUR_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_PURPUR_STAIRS = new SlimefunItemStack("COMPRESSED_PURPUR_STAIRS", Material.PURPUR_STAIRS,"&cPURPUR_STAIRS.tar","","&6A block of 32 PURPUR_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_QUARTZ = new SlimefunItemStack("COMPRESSED_QUARTZ", Material.QUARTZ,"&cQUARTZ.tar","","&6A block of 32 QUARTZ","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_QUARTZ_BLOCK = new SlimefunItemStack("COMPRESSED_QUARTZ_BLOCK", Material.QUARTZ_BLOCK,"&cQUARTZ_BLOCK.tar","","&6A block of 32 QUARTZ_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_QUARTZ_PILLAR = new SlimefunItemStack("COMPRESSED_QUARTZ_PILLAR", Material.QUARTZ_PILLAR,"&cQUARTZ_PILLAR.tar","","&6A block of 32 QUARTZ_PILLAR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_QUARTZ_SLAB = new SlimefunItemStack("COMPRESSED_QUARTZ_SLAB", Material.QUARTZ_SLAB,"&cQUARTZ_SLAB.tar","","&6A block of 32 QUARTZ_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_QUARTZ_STAIRS = new SlimefunItemStack("COMPRESSED_QUARTZ_STAIRS", Material.QUARTZ_STAIRS,"&cQUARTZ_STAIRS.tar","","&6A block of 32 QUARTZ_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RABBIT_FOOT = new SlimefunItemStack("COMPRESSED_RABBIT_FOOT", Material.RABBIT_FOOT,"&cRABBIT_FOOT.tar","","&6A block of 32 RABBIT_FOOT","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RABBIT_HIDE = new SlimefunItemStack("COMPRESSED_RABBIT_HIDE", Material.RABBIT_HIDE,"&cRABBIT_HIDE.tar","","&6A block of 32 RABBIT_HIDE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RAIL = new SlimefunItemStack("COMPRESSED_RAIL", Material.RAIL,"&cRAIL.tar","","&6A block of 32 RAIL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_REDSTONE = new SlimefunItemStack("COMPRESSED_REDSTONE", Material.REDSTONE,"&cREDSTONE.tar","","&6A block of 32 REDSTONE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_REDSTONE_BLOCK = new SlimefunItemStack("COMPRESSED_REDSTONE_BLOCK", Material.REDSTONE_BLOCK,"&cREDSTONE_BLOCK.tar","","&6A block of 32 REDSTONE_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_REDSTONE_LAMP = new SlimefunItemStack("COMPRESSED_REDSTONE_LAMP", Material.REDSTONE_LAMP,"&cREDSTONE_LAMP.tar","","&6A block of 32 REDSTONE_LAMP","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_REDSTONE_ORE = new SlimefunItemStack("COMPRESSED_REDSTONE_ORE", Material.REDSTONE_ORE,"&cREDSTONE_ORE.tar","","&6A block of 32 REDSTONE_ORE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_REDSTONE_TORCH = new SlimefunItemStack("COMPRESSED_REDSTONE_TORCH", Material.REDSTONE_TORCH,"&cREDSTONE_TORCH.tar","","&6A block of 32 REDSTONE_TORCH","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RED_CARPET = new SlimefunItemStack("COMPRESSED_RED_CARPET", Material.RED_CARPET,"&cRED_CARPET.tar","","&6A block of 32 RED_CARPET","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RED_CONCRETE = new SlimefunItemStack("COMPRESSED_RED_CONCRETE", Material.RED_CONCRETE,"&cRED_CONCRETE.tar","","&6A block of 32 RED_CONCRETE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RED_CONCRETE_POWDER = new SlimefunItemStack("COMPRESSED_RED_CONCRETE_POWDER", Material.RED_CONCRETE_POWDER,"&cRED_CONCRETE_POWDER.tar","","&6A block of 32 RED_CONCRETE_POWDER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RED_DYE = new SlimefunItemStack("COMPRESSED_RED_DYE", Material.RED_DYE,"&cRED_DYE.tar","","&6A block of 32 RED_DYE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RED_GLAZED_TERRACOTTA = new SlimefunItemStack("COMPRESSED_RED_GLAZED_TERRACOTTA", Material.RED_GLAZED_TERRACOTTA,"&cRED_GLAZED_TERRACOTTA.tar","","&6A block of 32 RED_GLAZED_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RED_MUSHROOM = new SlimefunItemStack("COMPRESSED_RED_MUSHROOM", Material.RED_MUSHROOM,"&cRED_MUSHROOM.tar","","&6A block of 32 RED_MUSHROOM","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RED_MUSHROOM_BLOCK = new SlimefunItemStack("COMPRESSED_RED_MUSHROOM_BLOCK", Material.RED_MUSHROOM_BLOCK,"&cRED_MUSHROOM_BLOCK.tar","","&6A block of 32 RED_MUSHROOM_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RED_NETHER_BRICKS = new SlimefunItemStack("COMPRESSED_RED_NETHER_BRICKS", Material.RED_NETHER_BRICKS,"&cRED_NETHER_BRICKS.tar","","&6A block of 32 RED_NETHER_BRICKS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RED_NETHER_BRICK_SLAB = new SlimefunItemStack("COMPRESSED_RED_NETHER_BRICK_SLAB", Material.RED_NETHER_BRICK_SLAB,"&cRED_NETHER_BRICK_SLAB.tar","","&6A block of 32 RED_NETHER_BRICK_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RED_NETHER_BRICK_STAIRS = new SlimefunItemStack("COMPRESSED_RED_NETHER_BRICK_STAIRS", Material.RED_NETHER_BRICK_STAIRS,"&cRED_NETHER_BRICK_STAIRS.tar","","&6A block of 32 RED_NETHER_BRICK_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RED_NETHER_BRICK_WALL = new SlimefunItemStack("COMPRESSED_RED_NETHER_BRICK_WALL", Material.RED_NETHER_BRICK_WALL,"&cRED_NETHER_BRICK_WALL.tar","","&6A block of 32 RED_NETHER_BRICK_WALL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RED_SAND = new SlimefunItemStack("COMPRESSED_RED_SAND", Material.RED_SAND,"&cRED_SAND.tar","","&6A block of 32 RED_SAND","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RED_SANDSTONE = new SlimefunItemStack("COMPRESSED_RED_SANDSTONE", Material.RED_SANDSTONE,"&cRED_SANDSTONE.tar","","&6A block of 32 RED_SANDSTONE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RED_SANDSTONE_SLAB = new SlimefunItemStack("COMPRESSED_RED_SANDSTONE_SLAB", Material.RED_SANDSTONE_SLAB,"&cRED_SANDSTONE_SLAB.tar","","&6A block of 32 RED_SANDSTONE_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RED_SANDSTONE_STAIRS = new SlimefunItemStack("COMPRESSED_RED_SANDSTONE_STAIRS", Material.RED_SANDSTONE_STAIRS,"&cRED_SANDSTONE_STAIRS.tar","","&6A block of 32 RED_SANDSTONE_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RED_SANDSTONE_WALL = new SlimefunItemStack("COMPRESSED_RED_SANDSTONE_WALL", Material.RED_SANDSTONE_WALL,"&cRED_SANDSTONE_WALL.tar","","&6A block of 32 RED_SANDSTONE_WALL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RED_STAINED_GLASS = new SlimefunItemStack("COMPRESSED_RED_STAINED_GLASS", Material.RED_STAINED_GLASS,"&cRED_STAINED_GLASS.tar","","&6A block of 32 RED_STAINED_GLASS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RED_STAINED_GLASS_PANE = new SlimefunItemStack("COMPRESSED_RED_STAINED_GLASS_PANE", Material.RED_STAINED_GLASS_PANE,"&cRED_STAINED_GLASS_PANE.tar","","&6A block of 32 RED_STAINED_GLASS_PANE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RED_TERRACOTTA = new SlimefunItemStack("COMPRESSED_RED_TERRACOTTA", Material.RED_TERRACOTTA,"&cRED_TERRACOTTA.tar","","&6A block of 32 RED_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RED_TULIP = new SlimefunItemStack("COMPRESSED_RED_TULIP", Material.RED_TULIP,"&cRED_TULIP.tar","","&6A block of 32 RED_TULIP","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_RED_WOOL = new SlimefunItemStack("COMPRESSED_RED_WOOL", Material.RED_WOOL,"&cRED_WOOL.tar","","&6A block of 32 RED_WOOL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_REPEATER = new SlimefunItemStack("COMPRESSED_REPEATER", Material.REPEATER,"&cREPEATER.tar","","&6A block of 32 REPEATER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ROSE_BUSH = new SlimefunItemStack("COMPRESSED_ROSE_BUSH", Material.ROSE_BUSH,"&cROSE_BUSH.tar","","&6A block of 32 ROSE_BUSH","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_ROTTEN_FLESH = new SlimefunItemStack("COMPRESSED_ROTTEN_FLESH", Material.ROTTEN_FLESH,"&cROTTEN_FLESH.tar","","&6A block of 32 ROTTEN_FLESH","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SALMON = new SlimefunItemStack("COMPRESSED_SALMON", Material.SALMON,"&cSALMON.tar","","&6A block of 32 SALMON","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SAND = new SlimefunItemStack("COMPRESSED_SAND", Material.SAND,"&cSAND.tar","","&6A block of 32 SAND","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SANDSTONE = new SlimefunItemStack("COMPRESSED_SANDSTONE", Material.SANDSTONE,"&cSANDSTONE.tar","","&6A block of 32 SANDSTONE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SANDSTONE_SLAB = new SlimefunItemStack("COMPRESSED_SANDSTONE_SLAB", Material.SANDSTONE_SLAB,"&cSANDSTONE_SLAB.tar","","&6A block of 32 SANDSTONE_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SANDSTONE_STAIRS = new SlimefunItemStack("COMPRESSED_SANDSTONE_STAIRS", Material.SANDSTONE_STAIRS,"&cSANDSTONE_STAIRS.tar","","&6A block of 32 SANDSTONE_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SANDSTONE_WALL = new SlimefunItemStack("COMPRESSED_SANDSTONE_WALL", Material.SANDSTONE_WALL,"&cSANDSTONE_WALL.tar","","&6A block of 32 SANDSTONE_WALL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SCAFFOLDING = new SlimefunItemStack("COMPRESSED_SCAFFOLDING", Material.SCAFFOLDING,"&cSCAFFOLDING.tar","","&6A block of 32 SCAFFOLDING","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SCUTE = new SlimefunItemStack("COMPRESSED_SCUTE", Material.SCUTE,"&cSCUTE.tar","","&6A block of 32 SCUTE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SEAGRASS = new SlimefunItemStack("COMPRESSED_SEAGRASS", Material.SEAGRASS,"&cSEAGRASS.tar","","&6A block of 32 SEAGRASS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SEA_LANTERN = new SlimefunItemStack("COMPRESSED_SEA_LANTERN", Material.SEA_LANTERN,"&cSEA_LANTERN.tar","","&6A block of 32 SEA_LANTERN","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SEA_PICKLE = new SlimefunItemStack("COMPRESSED_SEA_PICKLE", Material.SEA_PICKLE,"&cSEA_PICKLE.tar","","&6A block of 32 SEA_PICKLE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SHULKER_SHELL = new SlimefunItemStack("COMPRESSED_SHULKER_SHELL", Material.SHULKER_SHELL,"&cSHULKER_SHELL.tar","","&6A block of 32 SHULKER_SHELL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SKELETON_SKULL = new SlimefunItemStack("COMPRESSED_SKELETON_SKULL", Material.SKELETON_SKULL,"&cSKELETON_SKULL.tar","","&6A block of 32 SKELETON_SKULL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SLIME_BALL = new SlimefunItemStack("COMPRESSED_SLIME_BALL", Material.SLIME_BALL,"&cSLIME_BALL.tar","","&6A block of 32 SLIME_BALL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SLIME_BLOCK = new SlimefunItemStack("COMPRESSED_SLIME_BLOCK", Material.SLIME_BLOCK,"&cSLIME_BLOCK.tar","","&6A block of 32 SLIME_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SMITHING_TABLE = new SlimefunItemStack("COMPRESSED_SMITHING_TABLE", Material.SMITHING_TABLE,"&cSMITHING_TABLE.tar","","&6A block of 32 SMITHING_TABLE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SMOKER = new SlimefunItemStack("COMPRESSED_SMOKER", Material.SMOKER,"&cSMOKER.tar","","&6A block of 32 SMOKER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SMOOTH_QUARTZ = new SlimefunItemStack("COMPRESSED_SMOOTH_QUARTZ", Material.SMOOTH_QUARTZ,"&cSMOOTH_QUARTZ.tar","","&6A block of 32 SMOOTH_QUARTZ","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SMOOTH_QUARTZ_SLAB = new SlimefunItemStack("COMPRESSED_SMOOTH_QUARTZ_SLAB", Material.SMOOTH_QUARTZ_SLAB,"&cSMOOTH_QUARTZ_SLAB.tar","","&6A block of 32 SMOOTH_QUARTZ_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SMOOTH_QUARTZ_STAIRS = new SlimefunItemStack("COMPRESSED_SMOOTH_QUARTZ_STAIRS", Material.SMOOTH_QUARTZ_STAIRS,"&cSMOOTH_QUARTZ_STAIRS.tar","","&6A block of 32 SMOOTH_QUARTZ_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SMOOTH_RED_SANDSTONE = new SlimefunItemStack("COMPRESSED_SMOOTH_RED_SANDSTONE", Material.SMOOTH_RED_SANDSTONE,"&cSMOOTH_RED_SANDSTONE.tar","","&6A block of 32 SMOOTH_RED_SANDSTONE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SMOOTH_RED_SANDSTONE_SLAB = new SlimefunItemStack("COMPRESSED_SMOOTH_RED_SANDSTONE_SLAB", Material.SMOOTH_RED_SANDSTONE_SLAB,"&cSMOOTH_RED_SANDSTONE_SLAB.tar","","&6A block of 32 SMOOTH_RED_SANDSTONE_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SMOOTH_RED_SANDSTONE_STAIRS = new SlimefunItemStack("COMPRESSED_SMOOTH_RED_SANDSTONE_STAIRS", Material.SMOOTH_RED_SANDSTONE_STAIRS,"&cSMOOTH_RED_SANDSTONE_STAIRS.tar","","&6A block of 32 SMOOTH_RED_SANDSTONE_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SMOOTH_SANDSTONE = new SlimefunItemStack("COMPRESSED_SMOOTH_SANDSTONE", Material.SMOOTH_SANDSTONE,"&cSMOOTH_SANDSTONE.tar","","&6A block of 32 SMOOTH_SANDSTONE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SMOOTH_SANDSTONE_SLAB = new SlimefunItemStack("COMPRESSED_SMOOTH_SANDSTONE_SLAB", Material.SMOOTH_SANDSTONE_SLAB,"&cSMOOTH_SANDSTONE_SLAB.tar","","&6A block of 32 SMOOTH_SANDSTONE_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SMOOTH_SANDSTONE_STAIRS = new SlimefunItemStack("COMPRESSED_SMOOTH_SANDSTONE_STAIRS", Material.SMOOTH_SANDSTONE_STAIRS,"&cSMOOTH_SANDSTONE_STAIRS.tar","","&6A block of 32 SMOOTH_SANDSTONE_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SMOOTH_STONE = new SlimefunItemStack("COMPRESSED_SMOOTH_STONE", Material.SMOOTH_STONE,"&cSMOOTH_STONE.tar","","&6A block of 32 SMOOTH_STONE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SMOOTH_STONE_SLAB = new SlimefunItemStack("COMPRESSED_SMOOTH_STONE_SLAB", Material.SMOOTH_STONE_SLAB,"&cSMOOTH_STONE_SLAB.tar","","&6A block of 32 SMOOTH_STONE_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SNOW = new SlimefunItemStack("COMPRESSED_SNOW", Material.SNOW,"&cSNOW.tar","","&6A block of 32 SNOW","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SNOWBALL = new SlimefunItemStack("COMPRESSED_SNOWBALL", Material.SNOWBALL,"&cSNOWBALL.tar","","&6A block of 32 SNOWBALL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SNOW_BLOCK = new SlimefunItemStack("COMPRESSED_SNOW_BLOCK", Material.SNOW_BLOCK,"&cSNOW_BLOCK.tar","","&6A block of 32 SNOW_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SOUL_SAND = new SlimefunItemStack("COMPRESSED_SOUL_SAND", Material.SOUL_SAND,"&cSOUL_SAND.tar","","&6A block of 32 SOUL_SAND","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SPECTRAL_ARROW = new SlimefunItemStack("COMPRESSED_SPECTRAL_ARROW", Material.SPECTRAL_ARROW,"&cSPECTRAL_ARROW.tar","","&6A block of 32 SPECTRAL_ARROW","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SPIDER_EYE = new SlimefunItemStack("COMPRESSED_SPIDER_EYE", Material.SPIDER_EYE,"&cSPIDER_EYE.tar","","&6A block of 32 SPIDER_EYE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SPONGE = new SlimefunItemStack("COMPRESSED_SPONGE", Material.SPONGE,"&cSPONGE.tar","","&6A block of 32 SPONGE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SPRUCE_BUTTON = new SlimefunItemStack("COMPRESSED_SPRUCE_BUTTON", Material.SPRUCE_BUTTON,"&cSPRUCE_BUTTON.tar","","&6A block of 32 SPRUCE_BUTTON","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SPRUCE_DOOR = new SlimefunItemStack("COMPRESSED_SPRUCE_DOOR", Material.SPRUCE_DOOR,"&cSPRUCE_DOOR.tar","","&6A block of 32 SPRUCE_DOOR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SPRUCE_FENCE = new SlimefunItemStack("COMPRESSED_SPRUCE_FENCE", Material.SPRUCE_FENCE,"&cSPRUCE_FENCE.tar","","&6A block of 32 SPRUCE_FENCE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SPRUCE_FENCE_GATE = new SlimefunItemStack("COMPRESSED_SPRUCE_FENCE_GATE", Material.SPRUCE_FENCE_GATE,"&cSPRUCE_FENCE_GATE.tar","","&6A block of 32 SPRUCE_FENCE_GATE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SPRUCE_LEAVES = new SlimefunItemStack("COMPRESSED_SPRUCE_LEAVES", Material.SPRUCE_LEAVES,"&cSPRUCE_LEAVES.tar","","&6A block of 32 SPRUCE_LEAVES","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SPRUCE_LOG = new SlimefunItemStack("COMPRESSED_SPRUCE_LOG", Material.SPRUCE_LOG,"&cSPRUCE_LOG.tar","","&6A block of 32 SPRUCE_LOG","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SPRUCE_PLANKS = new SlimefunItemStack("COMPRESSED_SPRUCE_PLANKS", Material.SPRUCE_PLANKS,"&cSPRUCE_PLANKS.tar","","&6A block of 32 SPRUCE_PLANKS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SPRUCE_PRESSURE_PLATE = new SlimefunItemStack("COMPRESSED_SPRUCE_PRESSURE_PLATE", Material.SPRUCE_PRESSURE_PLATE,"&cSPRUCE_PRESSURE_PLATE.tar","","&6A block of 32 SPRUCE_PRESSURE_PLATE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SPRUCE_SAPLING = new SlimefunItemStack("COMPRESSED_SPRUCE_SAPLING", Material.SPRUCE_SAPLING,"&cSPRUCE_SAPLING.tar","","&6A block of 32 SPRUCE_SAPLING","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SPRUCE_SLAB = new SlimefunItemStack("COMPRESSED_SPRUCE_SLAB", Material.SPRUCE_SLAB,"&cSPRUCE_SLAB.tar","","&6A block of 32 SPRUCE_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SPRUCE_STAIRS = new SlimefunItemStack("COMPRESSED_SPRUCE_STAIRS", Material.SPRUCE_STAIRS,"&cSPRUCE_STAIRS.tar","","&6A block of 32 SPRUCE_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SPRUCE_TRAPDOOR = new SlimefunItemStack("COMPRESSED_SPRUCE_TRAPDOOR", Material.SPRUCE_TRAPDOOR,"&cSPRUCE_TRAPDOOR.tar","","&6A block of 32 SPRUCE_TRAPDOOR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SPRUCE_WOOD = new SlimefunItemStack("COMPRESSED_SPRUCE_WOOD", Material.SPRUCE_WOOD,"&cSPRUCE_WOOD.tar","","&6A block of 32 SPRUCE_WOOD","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STICK = new SlimefunItemStack("COMPRESSED_STICK", Material.STICK,"&cSTICK.tar","","&6A block of 32 STICK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STICKY_PISTON = new SlimefunItemStack("COMPRESSED_STICKY_PISTON", Material.STICKY_PISTON,"&cSTICKY_PISTON.tar","","&6A block of 32 STICKY_PISTON","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STONE = new SlimefunItemStack("COMPRESSED_STONE", Material.STONE,"&cSTONE.tar","","&6A block of 32 STONE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STONECUTTER = new SlimefunItemStack("COMPRESSED_STONECUTTER", Material.STONECUTTER,"&cSTONECUTTER.tar","","&6A block of 32 STONECUTTER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STONE_BRICKS = new SlimefunItemStack("COMPRESSED_STONE_BRICKS", Material.STONE_BRICKS,"&cSTONE_BRICKS.tar","","&6A block of 32 STONE_BRICKS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STONE_BRICK_SLAB = new SlimefunItemStack("COMPRESSED_STONE_BRICK_SLAB", Material.STONE_BRICK_SLAB,"&cSTONE_BRICK_SLAB.tar","","&6A block of 32 STONE_BRICK_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STONE_BRICK_STAIRS = new SlimefunItemStack("COMPRESSED_STONE_BRICK_STAIRS", Material.STONE_BRICK_STAIRS,"&cSTONE_BRICK_STAIRS.tar","","&6A block of 32 STONE_BRICK_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STONE_BRICK_WALL = new SlimefunItemStack("COMPRESSED_STONE_BRICK_WALL", Material.STONE_BRICK_WALL,"&cSTONE_BRICK_WALL.tar","","&6A block of 32 STONE_BRICK_WALL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STONE_BUTTON = new SlimefunItemStack("COMPRESSED_STONE_BUTTON", Material.STONE_BUTTON,"&cSTONE_BUTTON.tar","","&6A block of 32 STONE_BUTTON","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STONE_PRESSURE_PLATE = new SlimefunItemStack("COMPRESSED_STONE_PRESSURE_PLATE", Material.STONE_PRESSURE_PLATE,"&cSTONE_PRESSURE_PLATE.tar","","&6A block of 32 STONE_PRESSURE_PLATE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STONE_SLAB = new SlimefunItemStack("COMPRESSED_STONE_SLAB", Material.STONE_SLAB,"&cSTONE_SLAB.tar","","&6A block of 32 STONE_SLAB","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STONE_STAIRS = new SlimefunItemStack("COMPRESSED_STONE_STAIRS", Material.STONE_STAIRS,"&cSTONE_STAIRS.tar","","&6A block of 32 STONE_STAIRS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STRING = new SlimefunItemStack("COMPRESSED_STRING", Material.STRING,"&cSTRING.tar","","&6A block of 32 STRING","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STRIPPED_ACACIA_LOG = new SlimefunItemStack("COMPRESSED_STRIPPED_ACACIA_LOG", Material.STRIPPED_ACACIA_LOG,"&cSTRIPPED_ACACIA_LOG.tar","","&6A block of 32 STRIPPED_ACACIA_LOG","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STRIPPED_ACACIA_WOOD = new SlimefunItemStack("COMPRESSED_STRIPPED_ACACIA_WOOD", Material.STRIPPED_ACACIA_WOOD,"&cSTRIPPED_ACACIA_WOOD.tar","","&6A block of 32 STRIPPED_ACACIA_WOOD","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STRIPPED_BIRCH_LOG = new SlimefunItemStack("COMPRESSED_STRIPPED_BIRCH_LOG", Material.STRIPPED_BIRCH_LOG,"&cSTRIPPED_BIRCH_LOG.tar","","&6A block of 32 STRIPPED_BIRCH_LOG","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STRIPPED_BIRCH_WOOD = new SlimefunItemStack("COMPRESSED_STRIPPED_BIRCH_WOOD", Material.STRIPPED_BIRCH_WOOD,"&cSTRIPPED_BIRCH_WOOD.tar","","&6A block of 32 STRIPPED_BIRCH_WOOD","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STRIPPED_DARK_OAK_LOG = new SlimefunItemStack("COMPRESSED_STRIPPED_DARK_OAK_LOG", Material.STRIPPED_DARK_OAK_LOG,"&cSTRIPPED_DARK_OAK_LOG.tar","","&6A block of 32 STRIPPED_DARK_OAK_LOG","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STRIPPED_DARK_OAK_WOOD = new SlimefunItemStack("COMPRESSED_STRIPPED_DARK_OAK_WOOD", Material.STRIPPED_DARK_OAK_WOOD,"&cSTRIPPED_DARK_OAK_WOOD.tar","","&6A block of 32 STRIPPED_DARK_OAK_WOOD","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STRIPPED_JUNGLE_LOG = new SlimefunItemStack("COMPRESSED_STRIPPED_JUNGLE_LOG", Material.STRIPPED_JUNGLE_LOG,"&cSTRIPPED_JUNGLE_LOG.tar","","&6A block of 32 STRIPPED_JUNGLE_LOG","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STRIPPED_JUNGLE_WOOD = new SlimefunItemStack("COMPRESSED_STRIPPED_JUNGLE_WOOD", Material.STRIPPED_JUNGLE_WOOD,"&cSTRIPPED_JUNGLE_WOOD.tar","","&6A block of 32 STRIPPED_JUNGLE_WOOD","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STRIPPED_OAK_LOG = new SlimefunItemStack("COMPRESSED_STRIPPED_OAK_LOG", Material.STRIPPED_OAK_LOG,"&cSTRIPPED_OAK_LOG.tar","","&6A block of 32 STRIPPED_OAK_LOG","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STRIPPED_OAK_WOOD = new SlimefunItemStack("COMPRESSED_STRIPPED_OAK_WOOD", Material.STRIPPED_OAK_WOOD,"&cSTRIPPED_OAK_WOOD.tar","","&6A block of 32 STRIPPED_OAK_WOOD","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STRIPPED_SPRUCE_LOG = new SlimefunItemStack("COMPRESSED_STRIPPED_SPRUCE_LOG", Material.STRIPPED_SPRUCE_LOG,"&cSTRIPPED_SPRUCE_LOG.tar","","&6A block of 32 STRIPPED_SPRUCE_LOG","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_STRIPPED_SPRUCE_WOOD = new SlimefunItemStack("COMPRESSED_STRIPPED_SPRUCE_WOOD", Material.STRIPPED_SPRUCE_WOOD,"&cSTRIPPED_SPRUCE_WOOD.tar","","&6A block of 32 STRIPPED_SPRUCE_WOOD","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SUGAR = new SlimefunItemStack("COMPRESSED_SUGAR", Material.SUGAR,"&cSUGAR.tar","","&6A block of 32 SUGAR","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SUGAR_CANE = new SlimefunItemStack("COMPRESSED_SUGAR_CANE", Material.SUGAR_CANE,"&cSUGAR_CANE.tar","","&6A block of 32 SUGAR_CANE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SUNFLOWER = new SlimefunItemStack("COMPRESSED_SUNFLOWER", Material.SUNFLOWER,"&cSUNFLOWER.tar","","&6A block of 32 SUNFLOWER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_SWEET_BERRIES = new SlimefunItemStack("COMPRESSED_SWEET_BERRIES", Material.SWEET_BERRIES,"&cSWEET_BERRIES.tar","","&6A block of 32 SWEET_BERRIES","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_TERRACOTTA = new SlimefunItemStack("COMPRESSED_TERRACOTTA", Material.TERRACOTTA,"&cTERRACOTTA.tar","","&6A block of 32 TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_TNT = new SlimefunItemStack("COMPRESSED_TNT", Material.TNT,"&cTNT.tar","","&6A block of 32 TNT","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_TORCH = new SlimefunItemStack("COMPRESSED_TORCH", Material.TORCH,"&cTORCH.tar","","&6A block of 32 TORCH","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_TRAPPED_CHEST = new SlimefunItemStack("COMPRESSED_TRAPPED_CHEST", Material.TRAPPED_CHEST,"&cTRAPPED_CHEST.tar","","&6A block of 32 TRAPPED_CHEST","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_TRIPWIRE_HOOK = new SlimefunItemStack("COMPRESSED_TRIPWIRE_HOOK", Material.TRIPWIRE_HOOK,"&cTRIPWIRE_HOOK.tar","","&6A block of 32 TRIPWIRE_HOOK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_TROPICAL_FISH = new SlimefunItemStack("COMPRESSED_TROPICAL_FISH", Material.TROPICAL_FISH,"&cTROPICAL_FISH.tar","","&6A block of 32 TROPICAL_FISH","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_TUBE_CORAL = new SlimefunItemStack("COMPRESSED_TUBE_CORAL", Material.TUBE_CORAL,"&cTUBE_CORAL.tar","","&6A block of 32 TUBE_CORAL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_TUBE_CORAL_BLOCK = new SlimefunItemStack("COMPRESSED_TUBE_CORAL_BLOCK", Material.TUBE_CORAL_BLOCK,"&cTUBE_CORAL_BLOCK.tar","","&6A block of 32 TUBE_CORAL_BLOCK","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_TUBE_CORAL_FAN = new SlimefunItemStack("COMPRESSED_TUBE_CORAL_FAN", Material.TUBE_CORAL_FAN,"&cTUBE_CORAL_FAN.tar","","&6A block of 32 TUBE_CORAL_FAN","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_TURTLE_EGG = new SlimefunItemStack("COMPRESSED_TURTLE_EGG", Material.TURTLE_EGG,"&cTURTLE_EGG.tar","","&6A block of 32 TURTLE_EGG","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_VINE = new SlimefunItemStack("COMPRESSED_VINE", Material.VINE,"&cVINE.tar","","&6A block of 32 VINE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_WET_SPONGE = new SlimefunItemStack("COMPRESSED_WET_SPONGE", Material.WET_SPONGE,"&cWET_SPONGE.tar","","&6A block of 32 WET_SPONGE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_WHEAT = new SlimefunItemStack("COMPRESSED_WHEAT", Material.WHEAT,"&cWHEAT.tar","","&6A block of 32 WHEAT","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_WHEAT_SEEDS = new SlimefunItemStack("COMPRESSED_WHEAT_SEEDS", Material.WHEAT_SEEDS,"&cWHEAT_SEEDS.tar","","&6A block of 32 WHEAT_SEEDS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_WHITE_CARPET = new SlimefunItemStack("COMPRESSED_WHITE_CARPET", Material.WHITE_CARPET,"&cWHITE_CARPET.tar","","&6A block of 32 WHITE_CARPET","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_WHITE_CONCRETE = new SlimefunItemStack("COMPRESSED_WHITE_CONCRETE", Material.WHITE_CONCRETE,"&cWHITE_CONCRETE.tar","","&6A block of 32 WHITE_CONCRETE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_WHITE_CONCRETE_POWDER = new SlimefunItemStack("COMPRESSED_WHITE_CONCRETE_POWDER", Material.WHITE_CONCRETE_POWDER,"&cWHITE_CONCRETE_POWDER.tar","","&6A block of 32 WHITE_CONCRETE_POWDER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_WHITE_DYE = new SlimefunItemStack("COMPRESSED_WHITE_DYE", Material.WHITE_DYE,"&cWHITE_DYE.tar","","&6A block of 32 WHITE_DYE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_WHITE_GLAZED_TERRACOTTA = new SlimefunItemStack("COMPRESSED_WHITE_GLAZED_TERRACOTTA", Material.WHITE_GLAZED_TERRACOTTA,"&cWHITE_GLAZED_TERRACOTTA.tar","","&6A block of 32 WHITE_GLAZED_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_WHITE_STAINED_GLASS = new SlimefunItemStack("COMPRESSED_WHITE_STAINED_GLASS", Material.WHITE_STAINED_GLASS,"&cWHITE_STAINED_GLASS.tar","","&6A block of 32 WHITE_STAINED_GLASS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_WHITE_STAINED_GLASS_PANE = new SlimefunItemStack("COMPRESSED_WHITE_STAINED_GLASS_PANE", Material.WHITE_STAINED_GLASS_PANE,"&cWHITE_STAINED_GLASS_PANE.tar","","&6A block of 32 WHITE_STAINED_GLASS_PANE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_WHITE_TERRACOTTA = new SlimefunItemStack("COMPRESSED_WHITE_TERRACOTTA", Material.WHITE_TERRACOTTA,"&cWHITE_TERRACOTTA.tar","","&6A block of 32 WHITE_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_WHITE_TULIP = new SlimefunItemStack("COMPRESSED_WHITE_TULIP", Material.WHITE_TULIP,"&cWHITE_TULIP.tar","","&6A block of 32 WHITE_TULIP","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_WHITE_WOOL = new SlimefunItemStack("COMPRESSED_WHITE_WOOL", Material.WHITE_WOOL,"&cWHITE_WOOL.tar","","&6A block of 32 WHITE_WOOL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_WITHER_SKELETON_SKULL = new SlimefunItemStack("COMPRESSED_WITHER_SKELETON_SKULL", Material.WITHER_SKELETON_SKULL,"&cWITHER_SKELETON_SKULL.tar","","&6A block of 32 WITHER_SKELETON_SKULL","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_YELLOW_CARPET = new SlimefunItemStack("COMPRESSED_YELLOW_CARPET", Material.YELLOW_CARPET,"&cYELLOW_CARPET.tar","","&6A block of 32 YELLOW_CARPET","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_YELLOW_CONCRETE = new SlimefunItemStack("COMPRESSED_YELLOW_CONCRETE", Material.YELLOW_CONCRETE,"&cYELLOW_CONCRETE.tar","","&6A block of 32 YELLOW_CONCRETE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_YELLOW_CONCRETE_POWDER = new SlimefunItemStack("COMPRESSED_YELLOW_CONCRETE_POWDER", Material.YELLOW_CONCRETE_POWDER,"&cYELLOW_CONCRETE_POWDER.tar","","&6A block of 32 YELLOW_CONCRETE_POWDER","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_YELLOW_DYE = new SlimefunItemStack("COMPRESSED_YELLOW_DYE", Material.YELLOW_DYE,"&cYELLOW_DYE.tar","","&6A block of 32 YELLOW_DYE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_YELLOW_GLAZED_TERRACOTTA = new SlimefunItemStack("COMPRESSED_YELLOW_GLAZED_TERRACOTTA", Material.YELLOW_GLAZED_TERRACOTTA,"&cYELLOW_GLAZED_TERRACOTTA.tar","","&6A block of 32 YELLOW_GLAZED_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_YELLOW_STAINED_GLASS = new SlimefunItemStack("COMPRESSED_YELLOW_STAINED_GLASS", Material.YELLOW_STAINED_GLASS,"&cYELLOW_STAINED_GLASS.tar","","&6A block of 32 YELLOW_STAINED_GLASS","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_YELLOW_STAINED_GLASS_PANE = new SlimefunItemStack("COMPRESSED_YELLOW_STAINED_GLASS_PANE", Material.YELLOW_STAINED_GLASS_PANE,"&cYELLOW_STAINED_GLASS_PANE.tar","","&6A block of 32 YELLOW_STAINED_GLASS_PANE","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_YELLOW_TERRACOTTA = new SlimefunItemStack("COMPRESSED_YELLOW_TERRACOTTA", Material.YELLOW_TERRACOTTA,"&cYELLOW_TERRACOTTA.tar","","&6A block of 32 YELLOW_TERRACOTTA","&bA great gift");
    public static final SlimefunItemStack COMPRESSED_YELLOW_WOOL = new SlimefunItemStack("COMPRESSED_YELLOW_WOOL", Material.YELLOW_WOOL,"&cYELLOW_WOOL.tar","","&6A block of 32 YELLOW_WOOL","&bA great gift");


    static {
        INFUSED_ELYTRA.addUnsafeEnchantment(Enchantment.MENDING, 1);
    }
}
