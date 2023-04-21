package me.cessentials.data.handlers;

import me.cessentials.CEssentials;
import me.cessentials.data.beans.PlayerData;
import me.cessentials.data.beans.PlayerHome;
import me.cessentials.utils.homes.HomeUtils;
import me.cessentials.utils.messages.MessagesUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerDataManager {

    private final CEssentials instance;
    private final List<PlayerData> playerDataList;

    public PlayerDataManager(List<PlayerData> playerDataList, CEssentials instance) {
        this.instance = instance;
        this.playerDataList = playerDataList;
    }

    public List<PlayerData> getPlayerDataList() {
        return playerDataList;
    }

    public boolean playerExists(Player player) {
        for(PlayerData playerData : playerDataList) {
            if(playerData.getUuid().equals(player.getUniqueId())) {
                return true;
            }
        }

        return false;
    }


    public PlayerData getPlayerData(Player player) {

        for(PlayerData playerData : playerDataList) {
            if(playerData.getUuid().equals(player.getUniqueId())) {
                return playerData;
            }
        }

        //non dovrebbe mai succedere ma almeno se succede sappiamo dove è il problema
        instance.getLogger().info("Player con username : " + player.getName());
        return null;
    }

    public void addUser(PlayerData playerData) {
        playerDataList.add(playerData);
    }


    public void addHomeToUser(Player player, String homeName) {
        PlayerData playerData = getPlayerData(player);

        if(HomeUtils.hasHome(playerData, homeName)) {
            player.sendMessage(MessagesUtils.getMessage("home-already-set", instance));
            return;
        }

        PlayerHome newHome = new PlayerHome(homeName, player.getLocation());
        playerData.getPlayerHomeList().add(newHome);

        player.sendMessage(MessagesUtils.getMessage("sethome-success", instance));
    }

    public void removeHomeFromUser(Player player, String homeName) {
        PlayerData playerData = getPlayerData(player);

        if(!HomeUtils.hasHome(playerData, homeName)) {
            player.sendMessage(MessagesUtils.getMessage("delhome-nohome-with-such-name", instance));
            return;
        }

        playerData.getPlayerHomeList().remove(HomeUtils.getHomeFromName(playerData, homeName));

        player.sendMessage(MessagesUtils.getMessage("delhome-success", instance));
    }
}
