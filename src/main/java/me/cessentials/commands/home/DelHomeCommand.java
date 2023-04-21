package me.cessentials.commands.home;

import me.cessentials.CEssentials;
import me.cessentials.commands.AbstractCommand;
import me.cessentials.data.beans.PlayerData;
import me.cessentials.utils.homes.HomeUtils;
import me.cessentials.utils.messages.MessagesUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelHomeCommand extends AbstractCommand {
    private CEssentials instance;

    public DelHomeCommand(CEssentials instance) {
        super("delhome", null, true, instance);
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        Player player = (Player) sender;
        PlayerData playerData = instance.getPlayerDataManager().getPlayerData(player);


        //controlla se il player non ha home settate
        if(!HomeUtils.doesPlayerHaveAnyHomes(playerData)) {
            player.sendMessage(MessagesUtils.getMessage("delhome-no-homes", instance));
            return;
        }

        //controlla se il player ha specificato il nome della home da eliminare
        if(args.length == 0) {

            //se il player ha solo 1 home elimina l'ultima
            if(playerData.getPlayerHomeList().size() == 1) {
                instance.getPlayerDataManager().removeHomeFromUser(player, playerData.getPlayerHomeList().get(0).getHomeName());
                return;
            }

            player.sendMessage(MessagesUtils.getMessage("delhome-usage", instance));
            return;
        }

        if(args.length == 1) {
            instance.getPlayerDataManager().removeHomeFromUser(player, args[1]);
            return;
        }

        //se arriviamo qua vuol dire che gli arguments sono maggiori di 1
        player.sendMessage(MessagesUtils.getMessage("delhome-usage", instance));
    }



}
