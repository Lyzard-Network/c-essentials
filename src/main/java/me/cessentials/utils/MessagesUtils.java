package me.cessentials.utils;

import me.cessentials.CEssentials;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class MessagesUtils {

    public static String getColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> getColor(List<String> texts) {
        List<String> newList = new ArrayList<>();

        for(String text : texts) {
            newList.add(ChatColor.translateAlternateColorCodes('&', text));
        }

        return newList;
    }

    public static String getMessage(String path, CEssentials instance) {
        return getColor(instance.getMessagesConfiguration().getString(path));
    }

    public static List<String> getMessages(String path, CEssentials instance) {
        return getColor(instance.getMessagesConfiguration().getStringList(path));
    }

    public static String getMessageFromConfig(String path, CEssentials instance) {
        return  getColor(instance.getConfiguration().getString(path));
    }
}
