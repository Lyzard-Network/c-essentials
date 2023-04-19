package me.cessentials.commands.flight;

import me.cessentials.CEssentials;
import me.cessentials.commands.AbstractCommand;
import me.cessentials.data.beans.PlayerData;
import me.cessentials.utils.MessagesUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class FlyCommand extends AbstractCommand {

    private final CEssentials instance;

    public FlyCommand(CEssentials instance) {
        super("fly", "core.fly", false, instance);
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        //enable/disable fly for himself - /fly
        if (args.length == 0) {
            Player player = (Player) sender;
            PlayerData playerData = instance.getPlayerDataManager().getPlayerData(player);

            if(!playerData.isFlight()) {
                playerData.setFlight(true);
                player.setAllowFlight(true);

                player.sendMessage(MessagesUtils.getMessage("flight-enabled-self", instance));
            }

            else {
                playerData.setFlight(false);
                player.setAllowFlight(false);

                player.sendMessage(MessagesUtils.getMessage("flight-disabled-self", instance));
            }

            return;
        }

        //enable/disable fly for others - /fly <player>
        if (args.length == 1) {
            if(!sender.hasPermission("core.fly.others")) {
                sender.sendMessage(MessagesUtils.getMessage("not-enough-permissions", instance));
                return;
            }

            if(Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(MessagesUtils.getMessage("player-not-online", instance));
                return;
            }

            Player target = Bukkit.getPlayer(args[0]);
            PlayerData targetData = instance.getPlayerDataManager().getPlayerData(target);

            //se il player non sta gia volando
            if(!targetData.isFlight()) {
                target.setAllowFlight(true);
                targetData.setFlight(true);

                sender.sendMessage(MessagesUtils.getMessage("flight-enabled-to", instance)
                        .replace("<player>", target.getName()));

                target.sendMessage(MessagesUtils.getMessage("flight-enabled-target", instance));

            }

            //se il player sta gia volando
            else {
                target.setAllowFlight(false);
                targetData.setFlight(false);


                sender.sendMessage(MessagesUtils.getMessage("flight-disabled-to", instance)
                        .replace("<player>", target.getName()));

                target.sendMessage(MessagesUtils.getMessage("flight-disabled-target", instance));
            }


        }

    }
}
