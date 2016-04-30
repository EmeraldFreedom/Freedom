package me.liamplugins.freedom.cmds;

import me.liamplugins.freedom.core.Freedom;
import me.liamplugins.freedom.core.Util;
import static me.liamplugins.freedom.core.Util.message;
import me.liamplugins.freedom.ranking.Ranking.Rank;
import net.pravian.aero.command.CommandOptions;
import net.pravian.aero.command.SimpleCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandOptions(aliases = "rbu")
public class C_rollbackundo extends SimpleCommand<Freedom> {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!Rank.getRank(sender).isAtLeast(Rank.SA)) {
            message(sender, "No permission!", ChatColor.YELLOW);
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Please provide a player!");
            return true;
        }

        if (args.length > 1) {
            sender.sendMessage(ChatColor.RED + "/rbu <player>");
        }

        Player player = getPlayer(args[0]);

        if (player == null) {
            sender.sendMessage(ChatColor.RED + "That player cannot be found!");
            return true;
        }

        Bukkit.dispatchCommand(sender, "co rs t:1d u:" + player.getName() + " r:#global #silent");
        Util.action(sender.getName(), "Undoing rollback on " + player.getName(), true);
        return true;
    }
}
