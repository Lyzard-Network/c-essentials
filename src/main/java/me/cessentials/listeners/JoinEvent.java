package me.cessentials.listeners;

import me.cessentials.CEssentials;
import me.cessentials.data.PlayerData;
import me.cessentials.utils.MessagesUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class JoinEvent implements Listener {

    private final CEssentials instance;

    public JoinEvent(CEssentials instance) {
        this.instance = instance;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        //controlliamo che il player non sia presente nella lista di players
        if(!instance.getPlayerDataManager().playerExists(player)) {
            //se non esiste lo creiamo e lo aggiungiamo alla lista dei players
            PlayerData playerData = new PlayerData(player.getUniqueId());
            instance.getPlayerDataManager().addUser(playerData);

            if(instance.getConfiguration().getBoolean("welcome-message-enabled")) {
                List<String> welcomeMessages = MessagesUtils.getMessages("welcome-message", instance);
                for(String message : welcomeMessages) {
                    if(message.contains("<index>")) {
                        message = message.replace("<index>", String.valueOf(instance.getPlayerDataManager().getPlayerDataList().size()));
                    }
                    Bukkit.broadcastMessage(message);
                }
            }
        }


    }

}
