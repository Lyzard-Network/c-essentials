package me.cessentials.commands.spawn;

import me.cessentials.CEssentials;
import me.cessentials.commands.AbstractCommand;
import org.bukkit.command.CommandSender;

public class SpawnCommand extends AbstractCommand {

    private final CEssentials instance;

    public SpawnCommand(CEssentials instance) {
        super("spawn", null, false, instance);
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

    }
}
