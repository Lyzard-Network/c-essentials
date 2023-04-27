package me.cessentials.commands.misc;

import me.cessentials.CEssentials;
import me.cessentials.commands.AbstractCommand;
import me.cessentials.utils.messages.MessagesUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand extends AbstractCommand {

    private final CEssentials instance;

    public HealCommand(CEssentials instance) {
        super("heal", "cessentials.heal", false, instance);
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0) {
            Player player = (Player) sender;
            player.setHealth(20);
            player.sendMessage(MessagesUtils.getMessage("heal-self-success", instance));
            return;
        }

        if(args.length == 1) {
            if(!sender.hasPermission("cessentials.heal.others")) {
               sender.sendMessage(MessagesUtils.getMessage("not-enough-permissions", instance));
               return;
            }

            if(Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(MessagesUtils.getMessage("player-not-online", instance));
                return;
            }

            Player target = Bukkit.getPlayer(args[0]);

            target.setHealth(20);

            sender.sendMessage(MessagesUtils.getMessage("heal-success-self-others", instance)
                    .replace("<player>", target.getName())
            );

            target.sendMessage(MessagesUtils.getMessage("heal-success-others", instance));
            return;
        }

        sender.sendMessage(MessagesUtils.getMessage("heal-usage", instance));
    }
}
