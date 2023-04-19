package me.cessentials.commands.gamemode;

import me.cessentials.CEssentials;
import me.cessentials.commands.AbstractCommand;
import me.cessentials.utils.MessagesUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeSurvivalCommand extends AbstractCommand {

    private final CEssentials instance;

    public GamemodeSurvivalCommand(CEssentials instance) {
        super("gms", "cessentials.gms", false, instance);
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        //player is running gmc on himself
        if(args.length == 0) {
            Player player = (Player) sender;

            player.setGameMode(GameMode.SURVIVAL);
            
            player.sendMessage(MessagesUtils.getMessage("gamemode-success", instance)
                    .replace("<gamemode>", GameMode.SURVIVAL.toString()));

            return;
        }

        //player is trying to change another player gamemode so he need cessentials.gms.others
        if(args.length == 1) {

            //sender has permissions?
            if (!sender.hasPermission("cessentials.gms.others")) {
                sender.sendMessage(MessagesUtils.getMessage("not-enough-permissions", instance));
                return;
            }

            //is the second argument a real player?
            if(Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(MessagesUtils.getMessage("player-not-online", instance));
                return;
            }

            Player target = Bukkit.getPlayer(args[0]);

            target.setGameMode(GameMode.SURVIVAL);

            //alert the target player that his gamemode has been changed
            target.sendMessage(MessagesUtils.getMessage("gamemode-success", instance)
                    .replace("<gamemode>", GameMode.SURVIVAL.toString()));

            //alert the sender player that the target gamemode has been changed
            sender.sendMessage(MessagesUtils.getMessage("gamemode-sender-success", instance)
                    .replace("<player>", target.getName())
                    .replace("<gamemode>", GameMode.SURVIVAL.toString()));

            return;
        }

        sender.sendMessage(MessagesUtils.getMessage("gamemode-survival-usage", instance));

    }
}
