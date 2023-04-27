package me.cessentials.commands.spawn;

import me.cessentials.CEssentials;
import me.cessentials.commands.AbstractCommand;
import org.bukkit.command.CommandSender;

public class SetSpawnCommand extends AbstractCommand {

    private final CEssentials instance;

    public SetSpawnCommand(CEssentials instance) {
        super("setspawn", "cessentials.setspawn", false, instance);
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

    }
}
