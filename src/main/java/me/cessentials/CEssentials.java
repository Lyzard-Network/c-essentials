package me.cessentials;

import me.cessentials.commands.flight.FlyCommand;
import me.cessentials.commands.gamemode.GamemodeAdventureCommand;
import me.cessentials.commands.gamemode.GamemodeCreativeCommand;
import me.cessentials.commands.gamemode.GamemodeSpectatorCommand;
import me.cessentials.commands.gamemode.GamemodeSurvivalCommand;
import me.cessentials.commands.home.DelHomeCommand;
import me.cessentials.commands.home.HomeCommand;
import me.cessentials.commands.home.HomesCommand;
import me.cessentials.commands.home.SetHomeCommand;
import me.cessentials.commands.teleport.TeleportCommand;
import me.cessentials.data.beans.PlayerData;
import me.cessentials.data.handlers.PlayerDataManager;
import me.cessentials.listeners.JoinEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class CEssentials extends JavaPlugin {

    private CEssentials instance;
    private FileConfiguration configuration;
    private FileConfiguration messagesConfiguration;

    private PlayerDataManager playerDataManager;

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info(" ");
        getLogger().info("Enabling cEssentials - carter39");
        getLogger().info(" ");

        getLogger().info("Loading files...");
        loadConfig();
        loadMessages();

        /**
         * TODO carica da db
         * */
        getLogger().info("Loading users");

        //per ora è una nuova lista
        // quando caricheremo dal db il valore di playerDataList
        // sarà passato da un metodo che appunto carica gli user da db/file
        List<PlayerData> playerDataList = new ArrayList<>(); //loadUsers()

        playerDataManager = new PlayerDataManager(playerDataList, instance);


        getLogger().info("Registering Commands and Listeners...");
        registerCommands();
        registerEvents();



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadConfig() {
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveResource("config.yml", false);
        }
        configuration = YamlConfiguration.loadConfiguration(configFile);
    }

    public void loadMessages() {
        File messagesFile = new File(getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            saveResource("messages.yml", false);
        }
        messagesConfiguration = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public void registerEvents() {
        instance.getServer().getPluginManager().registerEvents(new JoinEvent(instance), this);
    }

    public void registerCommands() {
        new GamemodeAdventureCommand(instance);
        new GamemodeCreativeCommand(instance);
        new GamemodeSurvivalCommand(instance);
        new GamemodeSpectatorCommand(instance);
        new FlyCommand(instance);
        new DelHomeCommand(instance);
        new SetHomeCommand(instance);
        new HomeCommand(instance);
        new HomesCommand(instance);
        new TeleportCommand(instance);
        new TeleportCommand(instance);

    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    public FileConfiguration getMessagesConfiguration() {
        return messagesConfiguration;
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }

    public CEssentials getInstance() {
        return instance;
    }
}
