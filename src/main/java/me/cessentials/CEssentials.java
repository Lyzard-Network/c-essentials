package me.cessentials;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class CEssentials extends JavaPlugin {

    private CEssentials instance;
    private FileConfiguration configuration;
    private FileConfiguration messagesConfiguration;

    @Override
    public void onEnable() {
        instance = this;

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

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    public FileConfiguration getMessagesConfiguration() {
        return messagesConfiguration;
    }

    public CEssentials getInstance() {
        return instance;
    }
}
