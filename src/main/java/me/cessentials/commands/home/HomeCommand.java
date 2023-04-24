package me.cessentials.commands.home;

import io.papermc.lib.PaperLib;
import me.cessentials.CEssentials;
import me.cessentials.commands.AbstractCommand;
import me.cessentials.data.beans.PlayerData;
import me.cessentials.data.beans.PlayerHome;
import me.cessentials.utils.homes.HomeUtils;
import me.cessentials.utils.messages.MessagesUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class HomeCommand extends AbstractCommand {

    private final CEssentials instance;

    public HomeCommand(CEssentials instance) {
        super("home", null, false, instance);
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        PlayerData playerData = instance.getPlayerDataManager().getPlayerData(player);

        if(!HomeUtils.doesPlayerHaveAnyHomes(playerData)) {
            player.sendMessage(MessagesUtils.getMessage("home-nohome-with-such-name", instance));
            return;
        }

        //controlla se il player ha specificato il nome della home in cui teletrasportarsi
        if(args.length == 0) {

            //se il player ha solo 1 home teletrasportarti nell'ultima
            if(playerData.getPlayerHomeList().size() == 1) {

                if(instance.getConfiguration().getBoolean("teleport-time-enabled")) {
                    startTeleport(player, playerData.getPlayerHomeList().get(0).getHomeName());
                    return;
                }

                PaperLib.teleportAsync(player, playerData.getPlayerHomeList().get(0).getHomeLocation());
                return;
            }

            player.sendMessage(MessagesUtils.getMessage("home-usage", instance));
            return;
        }

        if(args.length == 1) {
            if(!HomeUtils.hasHome(playerData, args[0])) {
                player.sendMessage(MessagesUtils.getMessage("home-nohome-with-such-name", instance));
                return;
            }

            if(instance.getConfiguration().getBoolean("teleport-time-enabled")) {
                startTeleport(player, args[0]);
                return;
            }

            PaperLib.teleportAsync(player, HomeUtils.getHomeFromName(playerData, args[0]).getHomeLocation());

            return;
        }

        //se arriviamo qua vuol dire che gli arguments sono maggiori di 1
        player.sendMessage(MessagesUtils.getMessage("delhome-usage", instance));

    }


    public void startTeleport(Player player, String homeName) {

        PlayerData playerData = instance.getPlayerDataManager().getPlayerData(player);
        PlayerHome playerHome = HomeUtils.getHomeFromName(playerData, homeName);

        Location homeLocation = playerHome.getHomeLocation();

        player.sendMessage(MessagesUtils.getMessage("teleport-started", instance)
                .replace("<seconds>", String.valueOf(instance.getConfiguration().getInt("teleport-time"))));
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        new BukkitRunnable() {
            int cooldown = instance.getConfiguration().getInt("teleport-time");

            @Override
            public void run() {

                //check if the player moved
                if(Bukkit.getPlayer(player.getName()).getLocation().getBlockZ() != z || Bukkit.getPlayer(player.getName()).getLocation().getBlockY() != y || Bukkit.getPlayer(player.getName()).getLocation().getBlockX() != x) {
                    player.sendMessage(MessagesUtils.getMessage("teleport-cancelled", instance));

                    player.sendTitle(
                            MessagesUtils.getMessage("teleport-title-cancelled", instance),
                            MessagesUtils.getMessage("teleport-subtitle-cancelled", instance)
                    );

                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1,1);
                    cancel();
                } else {

                    if (cooldown == 0) {

                        player.sendTitle(
                                MessagesUtils.getMessage("teleport-title-success", instance),
                                MessagesUtils.getMessage("teleport-subtitle-success", instance)
                        );

                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

                        PaperLib.teleportAsync(player, homeLocation);
                        cancel();
                    } else {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

                        player.sendMessage(
                                MessagesUtils.getMessage("teleport-in-progress", instance)
                                    .replace("<seconds>", String.valueOf(cooldown))
                        );

                        player.sendTitle(
                                MessagesUtils.getMessage("teleport-title-in-progress", instance),
                                MessagesUtils.getMessage("teleport-subtitle-in-progress", instance)
                                        .replace("<seconds>", String.valueOf(cooldown))
                        );

                        cooldown -= 1;
                    }
                }
            }
        }.runTaskTimer(instance, 0, 20L);
    }
}
