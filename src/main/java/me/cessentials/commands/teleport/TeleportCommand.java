package me.cessentials.commands.teleport;

import io.papermc.lib.PaperLib;
import me.cessentials.CEssentials;
import me.cessentials.commands.AbstractCommand;
import me.cessentials.utils.MessagesUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand extends AbstractCommand {

    private final CEssentials instance;

    public TeleportCommand(CEssentials instance) {
        super("tp", "core.tp", false, instance);
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        // - /tp <player>
        // - /tp <x> <y> <z>
        if (args.length == 1) {

            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(MessagesUtils.getMessage("player-not-online", instance));
                return;
            }

            Player player = (Player) sender;
            Player target = Bukkit.getPlayer(args[0]);

            PaperLib.teleportAsync(player, target.getLocation());

            player.sendMessage(MessagesUtils.getMessage("teleport-success", instance)
                    .replace("<player>", target.getName()));

            return;
        }

        if (args.length == 3) {

            try {
                Player player = (Player) sender;
                int x = Integer.parseInt(args[0]);
                int y = Integer.parseInt(args[1]);
                int z = Integer.parseInt(args[2]);

                PaperLib.teleportAsync(
                        player,
                        new Location(
                                player.getWorld(),
                                x, y, z, player.getBodyYaw(), player.getLocation().getPitch()
                        ));

            } catch (NumberFormatException ex) {
                sender.sendMessage(MessagesUtils.getMessage("teleport-position-usage", instance));
            }

            return;
        }

        sender.sendMessage(MessagesUtils.getMessage("teleport-wrong-usage", instance));

    }
}
