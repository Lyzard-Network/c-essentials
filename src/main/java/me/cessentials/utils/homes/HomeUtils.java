package me.cessentials.utils.homes;

import me.cessentials.data.beans.PlayerData;
import me.cessentials.data.beans.PlayerHome;
import org.bukkit.entity.Player;


public class HomeUtils {

    public static PlayerHome getHomeFromName(PlayerData playerData, String homeName) {
        for(PlayerHome playerHome : playerData.getPlayerHomeList()) {
            if(playerHome.getHomeName().equals(homeName)) {
                return playerHome;
            }
        }

        return null;
    }

    //check if player has an home with that name already
    public static boolean hasHome(PlayerData playerData, String homeName) {
        for(PlayerHome home : playerData.getPlayerHomeList()) {
            if(home.getHomeName().equals(homeName)) {
                return true;
            }
        }

        return false;
    }

    public static boolean doesPlayerHaveAnyHomes(PlayerData playerData) {
        return !playerData.getPlayerHomeList().isEmpty();
    }

    public static int getMaxHomesAllowedForPlayer(Player player) {
        if (player.hasPermission("core.homes.unlimited")) {
            return 1000;
        } else if (player.hasPermission("core.homes.10")) {
            return 10;
        } else if (player.hasPermission("core.homes.9")) {
            return 9;
        } else if (player.hasPermission("core.homes.8")) {
            return 8;
        } else if (player.hasPermission("core.homes.7")) {
            return 7;
        } else if (player.hasPermission("core.homes.6")) {
            return 6;
        } else if (player.hasPermission("core.homes.5")) {
            return 5;
        } else if (player.hasPermission("core.homes.4")) {
            return 4;
        } else if (player.hasPermission("core.homes.3")) {
            return 3;
        } else if (player.hasPermission("core.homes.2")) {
            return 2;
        } else {
            return 1;
        }
    }



}
