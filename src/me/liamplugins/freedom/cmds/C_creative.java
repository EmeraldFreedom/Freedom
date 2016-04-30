package me.liamplugins.freedom.cmds;

import me.liamplugins.freedom.core.Freedom;
import static me.liamplugins.freedom.core.Util.message;
import net.pravian.aero.command.SimpleCommand;
import org.bukkit.command.Command;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class C_creative extends SimpleCommand<Freedom> {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player idk = (Player) sender;

        if (args.length == 0) {
            if (!idk.getGameMode().equals(GameMode.CREATIVE)) {
                message(sender, "Gamemode set to creative");
                idk.setGameMode(GameMode.CREATIVE);
                return true;
            }

            if (idk.getGameMode().equals(GameMode.CREATIVE)) {
                message(sender, "You are already in creative!");
                return true;
            }
            return true;
        }

        if (args.length > 0) {
            message(sender, "/creative");
            return true;
        }

        return true;
    }
}
