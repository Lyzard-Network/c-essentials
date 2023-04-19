package me.cessentials.commands.teleport;

import io.papermc.lib.PaperLib;
import me.cessentials.CEssentials;
import me.cessentials.commands.AbstractCommand;
import me.cessentials.utils.MessagesUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class TeleportHereCommand extends AbstractCommand {
    private final CEssentials instance;

    public TeleportHereCommand(CEssentials instance) {
        super("tphere", "core.tphere", false, instance);
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length != 1) {
            sender.sendMessage(MessagesUtils.getMessage("teleport-here-usage", instance));
            return;
        }

        if(Bukkit.getPlayer(args[0]) == null) {
            sender.sendMessage(MessagesUtils.getMessage("player-not-online", instance));
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        Player player = (Player) sender;

        PaperLib.teleportAsync(target, player.getLocation());

        player.sendMessage(MessagesUtils.getMessage("teleport-here-self", instance)
                .replace("<player>", target.getName()));

        target.sendMessage(MessagesUtils.getMessage("teleport-here-target", instance)
                .replace("<player>", player.getName()));
    }
}
