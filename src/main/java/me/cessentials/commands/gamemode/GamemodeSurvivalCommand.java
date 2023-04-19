package me.cessentials.commands.gamemode;

import me.cessentials.CEssentials;
import me.cessentials.commands.AbstractCommand;
import me.cessentials.utils.MessagesUtils;
import org.bukkit.command.CommandSender;

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

            return;
        }

        //player is trying to change another player gamemode so he need cessentials.gms.others
        if(args.length == 1) {

            return;
        }

        sender.sendMessage(MessagesUtils.getMessage("gamemode-survival-usage", instance));

    }
}
