package me.liamplugins.freedom.cmds;

import me.liamplugins.freedom.core.AdminChat;
import me.liamplugins.freedom.core.Freedom;
import me.liamplugins.freedom.core.UserData;
import static me.liamplugins.freedom.core.Util.message;
import me.liamplugins.freedom.ranking.Ranking.Rank;
import net.pravian.aero.command.SimpleCommand;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class C_o extends SimpleCommand<Freedom> {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!Rank.getRank(sender).isAtLeast(Rank.SA)) {
            message(sender, "No permission!", ChatColor.YELLOW);
            return true;
        }

        if (args.length == 0) {
            UserData.getPlayerData((Player) sender).setInAC(!UserData.getPlayerData((Player) sender).inAC());
            sender.sendMessage("Toggling admin chat " + (UserData.getPlayerData((Player) sender).inAC() ? "on" : "off") + ".");
        } else {
            AdminChat.AChat(sender, StringUtils.join(args, " "));
        }

        return true;
    }
}
