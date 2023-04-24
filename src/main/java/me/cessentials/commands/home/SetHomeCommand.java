package me.cessentials.commands.home;

import me.cessentials.CEssentials;
import me.cessentials.commands.AbstractCommand;
import me.cessentials.data.beans.PlayerData;
import me.cessentials.utils.homes.HomeUtils;
import me.cessentials.utils.messages.MessagesUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

public class SetHomeCommand extends AbstractCommand {
    private CEssentials instance;

    public SetHomeCommand(CEssentials instance) {
        super("sethome", null, true, instance);
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        Player player = (Player) sender;
        PlayerData playerData = instance.getPlayerDataManager().getPlayerData(player);


        //controlla se il player ha il permesso di settare la home
        if(playerData.getPlayerHomeList().size() == HomeUtils.getMaxHomesAllowedForPlayer(player)) {
            player.sendMessage(MessagesUtils.getMessage("sethome-limit-reached", instance));
            return;
        }

        //controlla se il player ha specificato il nome della home da settare
        if(args.length == 0) {
            if(!HomeUtils.doesPlayerHaveAnyHomes(playerData)) {
                instance.getPlayerDataManager().addHomeToUser(player, "home");
                return;
            }

            player.sendMessage(MessagesUtils.getMessage("sethome-usage", instance));
            return;
        }

        if(args.length == 1) {
            instance.getPlayerDataManager().addHomeToUser(player, args[0]);
            return;
        }

        //se arriviamo qua vuol dire che gli arguments sono maggiori di 1
        player.sendMessage(MessagesUtils.getMessage("sethome-usage", instance));
    }



}
