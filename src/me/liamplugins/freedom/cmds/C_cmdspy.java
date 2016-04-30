package me.liamplugins.freedom.cmds;

import me.liamplugins.freedom.core.UserData;
import me.liamplugins.freedom.core.Freedom;
import static me.liamplugins.freedom.core.Util.message;
import me.liamplugins.freedom.ranking.Ranking.Rank;
import net.pravian.aero.command.SimpleCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class C_cmdspy extends SimpleCommand<Freedom> {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        if (!(sender instanceof Player)) {
            message(sender, "Console cannot toggle cmdspy, please include message!");
        }
        
        
        if (!Rank.getRank(sender).isAtLeast(Rank.SA)) {
            message(sender, "No permission!", ChatColor.YELLOW);
            return true;
        }

        if (args.length > 1) {
            message(sender, "/cmdspy", ChatColor.RED);
        }

        if (args.length == 0) {
            UserData.getPlayerData((Player) sender).setCMDSPYon(!UserData.getPlayerData((Player) sender).CMDSPYon());
            message(sender, "Toggling commandspy " + (UserData.getPlayerData((Player) sender).CMDSPYon() ? "on" : "off") + ".", ChatColor.RED);
        }

        return true;
    }
}
