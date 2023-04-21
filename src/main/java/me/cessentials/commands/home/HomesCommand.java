package me.cessentials.commands.home;

import me.cessentials.CEssentials;
import me.cessentials.commands.AbstractCommand;
import me.cessentials.data.beans.PlayerData;
import me.cessentials.data.beans.PlayerHome;
import me.cessentials.utils.homes.HomeUtils;
import me.cessentials.utils.messages.MessagesUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class HomesCommand extends AbstractCommand {
    private final CEssentials instance;

    public HomesCommand(CEssentials instance) {
        super("homes", null, false, instance);
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        Player player = (Player) sender;
        PlayerData playerData = instance.getPlayerDataManager().getPlayerData(player);

        if (!HomeUtils.doesPlayerHaveAnyHomes(playerData)) {
            player.sendMessage(MessagesUtils.getMessage("homes-homeless", instance));
            return;
        }


        player.sendMessage(
                MessagesUtils.getColor(
                        homesListMessage(
                                playerData.getPlayerHomeList(),
                                MessagesUtils.getMessage("homes-prefix", instance),
                                MessagesUtils.getMessage("homes-home-color", instance),
                                MessagesUtils.getMessage("homes-separator-color", instance)
                        )
                )
        );


    }

    public String homesListMessage(List<PlayerHome> playerHomeList, String homesPrefix, String colorHome, String colorSeparator) {

        StringBuilder sb = new StringBuilder();


        if (playerHomeList.size() == 1) {
            return MessagesUtils.getColor(homesPrefix + colorHome + playerHomeList.get(0).getHomeName());
        }

        sb.append(MessagesUtils.getColor(homesPrefix));

        for (int i = 0; i < playerHomeList.size(); i++) {

            if (i == playerHomeList.size() - 1) {
                sb.append(MessagesUtils.getColor(colorHome + playerHomeList.get(i).getHomeName()));
            } else {
                sb.append(MessagesUtils.getColor(colorHome + playerHomeList.get(i).getHomeName() + colorSeparator + ", "));
            }

        }

        return MessagesUtils.getColor(sb.toString());
    }
}
