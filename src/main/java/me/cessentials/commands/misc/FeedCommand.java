package me.cessentials.commands.misc;

import me.cessentials.CEssentials;
import me.cessentials.commands.AbstractCommand;
import me.cessentials.utils.messages.MessagesUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand extends AbstractCommand {

    private final CEssentials instance;

    public FeedCommand(CEssentials instance) {
        super("feed", "cessentials.feed", false, instance);
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0) {
            Player player = (Player) sender;
            player.setFoodLevel(20);
            player.sendMessage(MessagesUtils.getMessage("feed-self-success", instance));
            return;
        }

        if(args.length == 1) {
            if(!sender.hasPermission("cessentials.feed.others")) {
                sender.sendMessage(MessagesUtils.getMessage("not-enough-permissions", instance));
                return;
            }

            if(Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(MessagesUtils.getMessage("player-not-online", instance));
                return;
            }

            Player target = Bukkit.getPlayer(args[0]);

            target.setFoodLevel(20);

            sender.sendMessage(MessagesUtils.getMessage("feed-success-self-others", instance)
                    .replace("<player>", target.getName())
            );

            target.sendMessage(MessagesUtils.getMessage("feed-success-others", instance));
            return;
        }

        sender.sendMessage(MessagesUtils.getMessage("feed-usage", instance));
    }
}
