package me.mrCookieSlime.Slimefun;

import java.io.File;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.cscorelib2.config.Config;
import io.github.thebusybiscuit.cscorelib2.protection.ProtectionManager;
import io.github.thebusybiscuit.cscorelib2.recipes.RecipeSnapshot;
import io.github.thebusybiscuit.cscorelib2.reflection.ReflectionUtils;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.gps.GPSNetwork;
import io.github.thebusybiscuit.slimefun4.api.network.NetworkManager;
import io.github.thebusybiscuit.slimefun4.core.SlimefunRegistry;
import io.github.thebusybiscuit.slimefun4.core.commands.SlimefunCommand;
import io.github.thebusybiscuit.slimefun4.core.hooks.SlimefunHooks;
import io.github.thebusybiscuit.slimefun4.core.services.*;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.*;
import io.github.thebusybiscuit.slimefun4.implementation.resources.NetherIceResource;
import io.github.thebusybiscuit.slimefun4.implementation.resources.OilResource;
import io.github.thebusybiscuit.slimefun4.implementation.resources.SaltResource;
import io.github.thebusybiscuit.slimefun4.implementation.resources.UraniumResource;
import io.github.thebusybiscuit.slimefun4.core.services.AutoSavingService;
import io.github.thebusybiscuit.slimefun4.core.services.BackupService;
import io.github.thebusybiscuit.slimefun4.core.services.BlockDataService;
import io.github.thebusybiscuit.slimefun4.core.services.CustomItemDataService;
import io.github.thebusybiscuit.slimefun4.core.services.CustomTextureService;
import io.github.thebusybiscuit.slimefun4.core.services.LocalizationService;
import io.github.thebusybiscuit.slimefun4.core.services.UpdaterService;
import io.github.thebusybiscuit.slimefun4.core.services.github.GitHubService;
import io.github.thebusybiscuit.slimefun4.core.services.metrics.MetricsService;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.AncientAltarListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.AndroidKillingListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.BackpackListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.BlockListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.BlockPhysicsListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.CoolerListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.DeathpointListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.DebugFishListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.DispenserListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.EnhancedFurnaceListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.EntityKillListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.ExplosionsListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.GearListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.GrapplingHookListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.ItemPickupListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.MultiBlockListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.NetworkListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.PlayerProfileListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.SlimefunBootsListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.SlimefunBowListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.SlimefunGuideListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.SlimefunItemListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.SoulboundListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.TalismanListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.TeleporterListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.VampireBladeListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.VanillaMachinesListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.WorldListener;
import io.github.thebusybiscuit.slimefun4.implementation.resources.GEOResourcesSetup;
import io.github.thebusybiscuit.slimefun4.implementation.setup.MiscSetup;
import io.github.thebusybiscuit.slimefun4.implementation.setup.ResearchSetup;
import io.github.thebusybiscuit.slimefun4.implementation.setup.SlimefunItemSetup;
import io.github.thebusybiscuit.slimefun4.implementation.setup.WikiSetup;
import io.github.thebusybiscuit.slimefun4.implementation.tasks.ArmorTask;
import io.github.thebusybiscuit.slimefun4.implementation.tasks.TickerTask;
import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AGenerator;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AReactor;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.PlayerProfile;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.mrCookieSlime.Slimefun.api.inventory.UniversalBlockMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.CargoNet;
import me.mrCookieSlime.Slimefun.api.item_transport.cache.BlockStateCache;
import me.mrCookieSlime.Slimefun.api.item_transport.cache.InventoryCache;
import me.mrCookieSlime.Slimefun.utils.ConfigCache;
import me.mrCookieSlime.Slimefun.utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 * This is the main class of Slimefun.
 * This is where all the magic starts, take a look around.
 * Feel like home.
 *
 * @author TheBusyBiscuit
 *
 */
public final class SlimefunPlugin extends JavaPlugin implements SlimefunAddon {

    public static SlimefunPlugin instance;

    private RecipeSnapshot recipeSnapshot;
    private SlimefunRegistry registry;

    // Servicesprivate final CustomItemDataService itemDataService = new CustomItemDataService(this, "slimefun_item");
    private final CustomTextureService textureService = new CustomTextureService(this);
    private final BlockDataService blockDataService = new BlockDataService(this, "slimefun_block");
    private final GitHubService gitHubService = new GitHubService("TheBusyBiscuit/Slimefun4");
    private final UpdaterService updaterService = new UpdaterService(this, getFile());
    private final AutoSavingService autoSavingService = new AutoSavingService();
    private final BackupService backupService = new BackupService();private final MetricsService metricsService = new MetricsService(this);

    private TickerTask ticker;
    private LocalizationService local;
    private NetworkManager networkManager;
    private Config researches;
    private Config items;
    private Config whitelist;
    private Config config;

    private GPSNetwork gps;
    private ProtectionManager protections;
    private  ConfigCache settings;
    private SlimefunHooks hooks;private SlimefunCommand command;

    // Supported Versions of Minecraft, to ensure people dont use it on the wrong version.
    private final String[] supportedMinecraftVersions = {"v1_14_", "v1_15_"};
// Listeners that need to be accessed elsewhere
    private AncientAltarListener ancientAltarListener;
    private BackpackListener backpackListener;private GrapplingHookListener grapplingHookListener;
    private SlimefunBowListener bowListener;

    @SuppressWarnings({"ConstantConditions", "deprecation"})
    @Override
    public void onEnable() {
        if (getServer().getPluginManager().isPluginEnabled("CS-CoreLib")) {

            if (isVersionUnsupported()) {
                getServer().getPluginManager().disablePlugin(this);
                return;
            }

            instance = this;

            // Creating all necessary Folders
            getLogger().log(Level.INFO, "Loading Files...");
            createDirectories();

            getLogger().log(Level.INFO, "Loading Config...");

            // Setup config.yml
            config = new Config(this);
            registry = new SlimefunRegistry();
            settings = new ConfigCache(config);

            // Loading all extra configs
            researches = new Config(this, "Researches.yml");
            items = new Config(this, "Items.yml");
            whitelist = new Config(this, "whitelist.yml");

            // Setup messages.yml
            local = new LocalizationService(this, config.getString("options.language"));

            // Setting up Network classes
            networkManager = new NetworkManager(config.getInt("options.max-network-size"));

            // Setting up other stuff

            gps = new GPSNetwork();

            // Setting up bStats
            metricsService.start();

            // Starting the Auto-Updater
            if (config.getBoolean("options.auto-update")) {
                getLogger().log(Level.INFO, "Starting Auto-Updater...");updaterService.start();
            }else {
                updaterService.disable();
            }

            // Registering all GEO Resources
            getLogger().log(Level.INFO, "Loading GEO-Resources...");

            GEOResourcesSetup.setup();

            getLogger().log(Level.INFO, "Loading Items...");
            MiscSetup.setupItemSettings();

            try {
                SlimefunItemSetup.setup(this);
            }
            catch (Throwable x) {
                getLogger().log(Level.SEVERE, "An Error occured while initializing SlimefunItems for Slimefun " + getVersion(), x);
            }

            getLogger().log(Level.INFO, "Loading Researches...");
            try {
                ResearchSetup.setupResearches();
            }
            catch (Throwable x) {
                getLogger().log(Level.SEVERE, "An Error occured while initializing Slimefun Researches for Slimefun " + getVersion(), x);
            }
            settings.researchesEnabled = getResearchCfg().getBoolean("enable-researching");

            MiscSetup.setupMisc();
            Slimefun.getLogger().log(Level.INFO, "Loading Wiki pages...");WikiSetup.addWikiPages(this);
            textureService.setup(registry.getAllSlimefunItems());

            // Setting up GitHub Connectors...
            gitHubService.connect(config.getBoolean("options.print-out-github-data-retrieving"));

            // All Slimefun Listeners
            new SlimefunBootsListener(this);
            new SlimefunItemListener(this);
            new BlockPhysicsListener(this);
            new MultiBlockListener(this);
            new GearListener(this);
            new DispenserListener(this);
            new EntityKillListener(this);
            new BlockListener(this);
            new EnhancedFurnaceListener(this);
            new TeleporterListener(this);
            new AndroidKillingListener(this);
            new NetworkListener(this);
            new ItemPickupListener(this);
            new DeathpointListener(this);
            new ExplosionsListener(this);
            new DebugFishListener(this);
            new VanillaMachinesListener(this);

            bowListener = new SlimefunBowListener(this);
            ancientAltarListener = new AncientAltarListener();
            grapplingHookListener = new GrapplingHookListener();

            // Toggleable Listeners for performance
            if (config.getBoolean("items.talismans")) new TalismanListener(this);
            if (config.getBoolean("items.soulbound")) new SoulboundListener(this);

            if (config.getBoolean("items.backpacks")) {
                backpackListener = new BackpackListener(this);
            }

            // Handle Slimefun Guide being given on Join
            new SlimefunGuideListener(this, config.getBoolean("options.give-guide-on-first-join"));

            // Load/Unload Worlds in Slimefun
            new WorldListener(this);

            // Clear the Slimefun Guide History upon Player Leaving
            new PlayerProfileListener(this);

            // Initiating various Stuff and all Items with a slightly delay (0ms after the Server finished loading)
            Slimefun.runSync(() -> {
                recipeSnapshot = new RecipeSnapshot(this);
                protections = new ProtectionManager(getServer());
                MiscSetup.loadItems(settings);

                for (World world : Bukkit.getWorlds()) {
                    new BlockStorage(world);
                }

                if (SlimefunItem.getByID("ANCIENT_ALTAR") != null) {
                    ancientAltarListener.load(this);
                }

                if (SlimefunItem.getByID("GRAPPLING_HOOK") != null) {
                    grapplingHookListener.load(this);
                }

                if (SlimefunItem.getByID("BLADE_OF_VAMPIRES") != null) {
                    new VampireBladeListener(this);
                }

                if (SlimefunItem.getByID("COOLER") != null) {
                    new CoolerListener(this);
                }
            }, 0);

            // Setting up the command /sf and all subcommands command = new SlimefunCommand(this);



            // Armor Update Task
            if (config.getBoolean("options.enable-armor-effects")) {
                getServer().getScheduler().runTaskTimerAsynchronously(this, new ArmorTask(), 0L, config.getInt("options.armor-update-interval") * 20L);
            }

            ticker = new TickerTask();

            autoSavingService.start(this, config.getInt("options.auto-save-delay-in-minutes"));

            // Starting all ASYNC Tasks
            getServer().getScheduler().runTaskTimerAsynchronously(this, () -> {
                try {
                    ticker.run();
                } catch (Exception x) {
                    getLogger().log(Level.SEVERE, "An Exception was caught while ticking the Block Tickers Task for Slimefun v" + getVersion(), x);
                    ticker.abortTick();
                }
            }, 100L, config.getInt("URID.custom-ticker-delay"));

            gitHubService.start(this);

            // Slimefun Async CargoNet Scheduler
            Slimefun.isStopping = false;
            config.getConfiguration().addDefault("cargonet.max-server-thread-time", 10);
            CargoNet.maxServerThreadTime = config.getInt("cargonet.max-server-thread-time");
            CargoNet.restartPool();
            Bukkit.getScheduler().runTaskTimer(this, () -> {
                long startTime = System.nanoTime();
                int maxTimeMillis = CargoNet.maxServerThreadTime;
                while ((System.nanoTime() - startTime) / 1000 / 1000 < maxTimeMillis) {
                    try {
                        FutureTask<?> task = Slimefun.FUTURE_TASKS.poll((System.nanoTime() - startTime) / 8, TimeUnit.NANOSECONDS);
                        if (task == null) return;
                        task.run();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, 5, 1);
            config.save();
            if (BlockStateCache.isPaper) {
                Bukkit.getPluginManager().registerEvents(new BlockStateCache(), this);
                Bukkit.getPluginManager().registerEvents(new InventoryCache(), this);
            }

            // Hooray!
            getLogger().log(Level.INFO, "Finished!");
            hooks = new SlimefunHooks(this);



            // Do not show /sf elevator command in our Log, it could get quite spammy
            CSCoreLib.getLib().filterLog("([A-Za-z0-9_]{3,16}) issued server command: /sf elevator (.{0,})");
        } else {
            getLogger().log(Level.INFO, "#################### - INFO - ####################");
            getLogger().log(Level.INFO, " ");
            getLogger().log(Level.INFO, "Slimefun could not be loaded (yet).");
            getLogger().log(Level.INFO, "It appears that you have not installed CS-CoreLib.");
            getLogger().log(Level.INFO, "Please download and install CS-CoreLib manually:");
            getLogger().log(Level.INFO, "https://thebusybiscuit.github.io/builds/TheBusyBiscuit/CS-CoreLib/master/");

            getCommand("slimefun").setExecutor((sender, cmd, label, args) -> {
                sender.sendMessage("You have forgotten to install CS-CoreLib! Slimefun is disabled.");
                sender.sendMessage("https://thebusybiscuit.github.io/builds/TheBusyBiscuit/CS-CoreLib/master/");
                return true;
            });
        }
    }

    private boolean isVersionUnsupported() {
        String currentVersion = ReflectionUtils.getVersion();

        if (currentVersion.startsWith("v")) {
            boolean compatibleVersion = false;
            StringBuilder versions = new StringBuilder();

            int i = 0;
            for (String version : supportedMinecraftVersions) {
                if (currentVersion.startsWith(version)) {
                    compatibleVersion = true;
                }

                String s = version.substring(1).replaceFirst("_", ".").replace("_", ".X");
                if (i == 0) versions.append(s);
                else if (i == supportedMinecraftVersions.length - 1) versions.append(" or ").append(s);
                else versions.append(", ").append(s);

                i++;
            }

            // Looks like you are using an unsupported Minecraft Version
            if (!compatibleVersion) {
                getLogger().log(Level.SEVERE, "### Slimefun was not installed correctly!");
                getLogger().log(Level.SEVERE, "###");
                getLogger().log(Level.SEVERE, "### You are using the wrong Version of Minecraft!");
                getLogger().log(Level.SEVERE, "###");
                getLogger().log(Level.SEVERE, "### You are using Minecraft " + ReflectionUtils.getVersion());
                getLogger().log(Level.SEVERE, "### but Slimefun v" + getDescription().getVersion() + " requires you to be using");
                getLogger().log(Level.SEVERE, "### Minecraft {0}", versions);
                getLogger().log(Level.SEVERE, "###");
                getLogger().log(Level.SEVERE, "### Please use an older Version of Slimefun and disable auto-updating");
                getLogger().log(Level.SEVERE, "### or consider updating your Server Software.");
                return true;
            }
        }

        return false;
    }

    @Override
    public void onDisable() {
        // CS-CoreLib wasn't loaded, just disabling
        if (instance == null) return;

        Bukkit.getScheduler().cancelTasks(this);

        if (ticker != null) {
            // Finishes all started movements/removals of block data
            ticker.halt();
            ticker.run();
        }

        PlayerProfile.iterator().forEachRemaining(profile -> {
            if (profile.isDirty()) {profile.save();
        }});

        for (World world : Bukkit.getWorlds()) {
            try {
                BlockStorage storage = BlockStorage.getStorage(world);

                if (storage != null) {
                    storage.save(true);
                } else {
                    getLogger().log(Level.SEVERE, "Could not save Slimefun Blocks for World \"" + world.getName() + "\"");
                }
            } catch (Exception x) {
                getLogger().log(Level.SEVERE, "An Error occured while saving Slimefun-Blocks in World '" + world.getName() + "' for Slimefun " + getVersion(), x);
            }
        }

        for (UniversalBlockMenu menu : registry.getUniversalInventories().values()) {
            menu.save();
        }

        backupService.run();

        Slimefun.isStopping = true;
        CargoNet.shutdownPool();

        // Prevent Memory Leaks
        AContainer.processing = null;
        AContainer.progress = null;

        AGenerator.processing = null;
        AGenerator.progress = null;

        AReactor.processing = null;
        AReactor.progress = null;

        instance = null;

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.closeInventory();
        }
    }

    private void createDirectories() {
        String[] storage = { "Players", "blocks", "stored-blocks", "stored-inventories", "stored-chunks", "universal-inventories", "waypoints", "block-backups" };
        String[] general = { "scripts", "generators", "error-reports", "cache/github" };

        for (String file : storage) {
            createDir("data-storage/Slimefun/" + file);
        }

        for (String file : general) {
            createDir("plugins/Slimefun/" + file);
        }
    }

    private void createDir(String path) {
        File file = new File(path);
        if (!file.exists()) file.mkdirs();
    }

    public static Config getCfg() {
        return instance.config;
    }

    public static io.github.thebusybiscuit.cscorelib2.config.Config getResearchCfg() {
        return instance.researches;
    }

    public static Config getItemCfg() {
        return instance.items;
    }

    public static io.github.thebusybiscuit.cscorelib2.config.Config getWhitelist() {
        return instance.whitelist;
    }

    public static GPSNetwork getGPSNetwork() {
        return instance.gps;
    }

    public static SlimefunHooks getHooks() {
        return instance.hooks;
    }



    public static ConfigCache getSettings() {
        return instance.settings;
    }

    public static TickerTask getTicker() {
        return instance.ticker;
    }

    @SuppressWarnings("unused")
    public static boolean isActive() {
        return instance != null;
    }

    /**
     * This returns the version of Slimefun that is currently installed.
     *
     * @return The currently installed version of Slimefun
     */public static String getVersion() {
        return instance.getDescription().getVersion();
    }

    public static ProtectionManager getProtectionManager() {
        return instance.protections;
    }

    public static LocalizationService getLocal() {
        return instance.local;
    }

    public static RecipeSnapshot getMinecraftRecipes() {
        return instance.recipeSnapshot;
    }

    public static CustomItemDataService getItemDataService() {
        return instance.itemDataService;
    }

    public static CustomTextureService getItemTextureService() {
        return instance.textureService;
    }

    public static BlockDataService getBlockDataService() {
        return instance.blockDataService;
    }

    /**
     * This method returns the {@link UpdaterService} of Slimefun.
     * It is used to handle automatic updates.
     *
     * @return The {@link UpdaterService} for Slimefun
     */public static UpdaterService getUpdater() {
        return instance.updaterService;
    }

    /**
     * This method returns the {@link GitHubService} of Slimefun.
     * It is used to retrieve data from GitHub repositories.
     *
     * @return The {@link GitHubService} for Slimefun
     */public static GitHubService getGitHubService() {
        return instance.gitHubService;
    }

    public static SlimefunRegistry getRegistry() {
        return instance.registry;
    }

    public static NetworkManager getNetworkManager() {
        return instance.networkManager;
    }

    public static AncientAltarListener getAncientAltarListener() {
        return instance.ancientAltarListener;}

    public static GrapplingHookListener getGrapplingHookListener() {
        return instance.grapplingHookListener;
    }

    public static BackpackListener getBackpackListener() {
        return instance.backpackListener;
    }

    public static SlimefunBowListener getBowListener() {
        return instance.bowListener;
    }

    /**
     * This method returns a {@link Set} of every {@link Plugin} that lists Slimefun
     * as a required or optional dependency.
     *
     * We will just assume this to be a list of our addons.
     *
     * @return A {@link Set} of every {@link Plugin} that is dependent on Slimefun
     */public static Set<Plugin> getInstalledAddons() {
        return Arrays.stream(instance.getServer().getPluginManager().getPlugins())

                .filter(plugin -> plugin.getDescription().getDepend().contains(instance.getName()) || plugin.getDescription().getSoftDepend().contains(instance.getName()))
                .collect(Collectors.toSet());
    }public static SlimefunCommand getCommand() {
        return instance.command;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/TheBusyBiscuit/Slimefun4/issues";
    }

}
