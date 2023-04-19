package me.cessentials.data;

import me.cessentials.CEssentials;
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
}
