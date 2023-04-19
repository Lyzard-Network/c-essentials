package me.cessentials.commands;

import me.cessentials.CEssentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public abstract class AbstractCommand implements CommandExecutor {

    private final CEssentials instance;

    String command;
    String permission;
    boolean canConsoleUse;

    public AbstractCommand(String commandName, String permission, boolean canConsoleUse, CEssentials instance) {
        this.command = commandName;
        this.permission = permission;
        this.canConsoleUse = canConsoleUse;
        this.instance = instance;

        instance.getCommand(commandName).setExecutor(this);
    }

    public abstract void execute(CommandSender sender, String[] args);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!canConsoleUse && !(sender instanceof Player)) {
            //sender.sendMessage(MessagesUtils.getMessage("errors.only-players"));
            return true;
        }


        if(permission != null && !sender.hasPermission(permission)) {
            Player player = (Player) sender;
            //player.sendMessage(MessagesUtils.getMessage("errors.no-permissions"));
            return true;
        }

        execute(sender, args);
        return true;
    }
}
