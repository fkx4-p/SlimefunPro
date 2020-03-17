package com.ishland.slimefun.cobblestonegenerator;

import me.mrCookieSlime.Slimefun.Lists.Categories;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("unused")
public final class CobblestoneGenerator {

    public static void init(SlimefunPlugin plugin) {
        new Generator(Categories.ELECTRICITY, Generator.COBBLESTONE_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        new ItemStack(Material.IRON_PICKAXE), SlimefunItems.MEDIUM_CAPACITOR, new ItemStack(Material.IRON_PICKAXE),
                        new ItemStack(Material.WATER_BUCKET), null, new ItemStack(Material.LAVA_BUCKET),
                        null, SlimefunItems.ELECTRIC_MOTOR, null
                }) {
            /**
             * This method returns the amount of energy that is consumed per operation.
             *
             * @return The rate of energy consumption
             */
            @Override
            public int getEnergyConsumption() {
                return 8;
            }

            /**
             * This method returns the speed at which this machine will operate.
             * This can be implemented on an instantiation-level to create different tiers
             * of machines.
             *
             * @return The speed of this machine
             */
            @Override
            public int getSpeed() {
                return 1;
            }

            @Override
            public int getCapacity() {
                return 512;
            }
        }.register(plugin);

        new Generator(Categories.ELECTRICITY, Generator.COBBLESTONE_GENERATOR_2, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        new ItemStack(Material.DIAMOND_PICKAXE), SlimefunItems.BIG_CAPACITOR, new ItemStack(Material.DIAMOND_PICKAXE),
                        new ItemStack(Material.WATER_BUCKET), Generator.COBBLESTONE_GENERATOR, new ItemStack(Material.LAVA_BUCKET),
                        SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRIC_MOTOR
                }) {
            /**
             * This method returns the amount of energy that is consumed per operation.
             *
             * @return The rate of energy consumption
             */
            @Override
            public int getEnergyConsumption() {
                return 24;
            }

            /**
             * This method returns the speed at which this machine will operate.
             * This can be implemented on an instantiation-level to create different tiers
             * of machines.
             *
             * @return The speed of this machine
             */
            @Override
            public int getSpeed() {
                return 4;
            }

            @Override
            public int getCapacity() {
                return 1280;
            }

        }.register(plugin);

        new Generator(Categories.ELECTRICITY, Generator.COBBLESTONE_GENERATOR_3, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        new ItemStack(Material.GOLDEN_PICKAXE), SlimefunItems.BIG_CAPACITOR, new ItemStack(Material.GOLDEN_PICKAXE),
                        new ItemStack(Material.WATER_BUCKET), Generator.COBBLESTONE_GENERATOR_2, new ItemStack(Material.LAVA_BUCKET),
                        SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRIC_MOTOR
                }) {
            /**
             * This method returns the amount of energy that is consumed per operation.
             *
             * @return The rate of energy consumption
             */
            @Override
            public int getEnergyConsumption() {
                return 96;
            }

            /**
             * This method returns the speed at which this machine will operate.
             * This can be implemented on an instantiation-level to create different tiers
             * of machines.
             *
             * @return The speed of this machine
             */
            @Override
            public int getSpeed() {
                return 16;
            }

            @Override
            public int getCapacity() {
                return 2048;
            }

        }.register(plugin);

        new Generator(Categories.ELECTRICITY, Generator.COBBLESTONE_GENERATOR_4, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        new ItemStack(Material.GOLDEN_PICKAXE), SlimefunItems.BIG_CAPACITOR, new ItemStack(Material.GOLDEN_PICKAXE),
                        new ItemStack(Material.WATER_BUCKET), Generator.COBBLESTONE_GENERATOR_3, new ItemStack(Material.LAVA_BUCKET),
                        SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRIC_MOTOR
                }) {
            /**
             * This method returns the amount of energy that is consumed per operation.
             *
             * @return The rate of energy consumption
             */
            @Override
            public int getEnergyConsumption() {
                return 384;
            }

            /**
             * This method returns the speed at which this machine will operate.
             * This can be implemented on an instantiation-level to create different tiers
             * of machines.
             *
             * @return The speed of this machine
             */
            @Override
            public int getSpeed() {
                return 64;
            }

            @Override
            public int getCapacity() {
                return 3072;
            }

        }.register(plugin);

        new Generator(Categories.ELECTRICITY, Generator.COBBLESTONE_GENERATOR_5, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        new ItemStack(Material.GOLDEN_PICKAXE), SlimefunItems.CARBONADO_EDGED_CAPACITOR, new ItemStack(Material.GOLDEN_PICKAXE),
                        new ItemStack(Material.WATER_BUCKET), Generator.COBBLESTONE_GENERATOR_4, new ItemStack(Material.LAVA_BUCKET),
                        SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRIC_MOTOR
                }) {
            /**
             * This method returns the amount of energy that is consumed per operation.
             *
             * @return The rate of energy consumption
             */
            @Override
            public int getEnergyConsumption() {
                return 1536;
            }

            /**
             * This method returns the speed at which this machine will operate.
             * This can be implemented on an instantiation-level to create different tiers
             * of machines.
             *
             * @return The speed of this machine
             */
            @Override
            public int getSpeed() {
                return 256;
            }

            @Override
            public int getCapacity() {
                return 10240;
            }

        }.register(plugin);
    }
}
