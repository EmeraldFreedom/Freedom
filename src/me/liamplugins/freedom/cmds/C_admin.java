package me.liamplugins.freedom.cmds;

import me.liamplugins.freedom.core.Admin;
import me.liamplugins.freedom.core.Freedom;
import me.liamplugins.freedom.core.Util;
import static me.liamplugins.freedom.core.Util.message;
import me.liamplugins.freedom.ranking.Ranking.Rank;
import net.pravian.aero.command.SimpleCommand;
import net.pravian.aero.util.Ips;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class C_admin extends SimpleCommand<Freedom> {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {
            message(sender, "/admin <add/remove/verify> <player>", ChatColor.GRAY);
            return true;
        }

        switch (args[0]) {

            case "add": {

                if (!Rank.getRank(sender).isAtLeast(Rank.SRA)) {
                    message(sender, "Rank Level: 'SENIOR'", ChatColor.RED);
                    return true;
                }

                if (args.length < 2) {
                    message(sender, "/admin add <player>", ChatColor.GRAY);
                    return true;
                }

                if (args.length > 2) {
                    message(sender, "/admin add <player>", ChatColor.GRAY);
                    return true;
                }

                Player player = getPlayer(args[1]);

                if (player == null) {
                    message(sender, "Cannot find player!", ChatColor.RED);
                    return true;
                }

                if (Rank.Admin(player)) {
                    message(sender, "That player is already an admin!", ChatColor.RED);
                    return true;
                }

                if (!Rank.Admin(player)) {
                    Util.action(sender.getName(), "Adding " + player.getName() + " to the admin list admin", true);
                    final Admin admin = new Admin();
                    admin.addAdmin(player, Ips.getIp(player), player.getName(), "super admin");
                    admin.save();
                    return true;
                }
            }

            case "remove": {

                if (!Rank.getRank(sender).isAtLeast(Rank.SRA)) {
                    message(sender, "Rank Level: 'SENIOR'", ChatColor.RED);
                    return true;
                }

                Player player = getPlayer(args[1]);

                if (args.length > 2) {
                    message(sender, "/admin remove <player>", ChatColor.RED);
                    return true;
                }

                if (!Rank.Admin(player)) {
                    message(sender, "That player is not an admin!", ChatColor.RED);
                    return true;
                }

                if (Rank.Admin(player)) {
                    Util.action(sender.getName(), "Removing " + player.getName() + " from admin", true);
                    final Admin admin = new Admin();
                    admin.removeAdmin(player.getUniqueId().toString());
                    admin.save();
                    return true;
                }
            }
            case "verify": {

                if (!Rank.getRank(sender).isAtLeast(Rank.STA)) {
                    message(sender, "Rank Level: 'TELNET'", ChatColor.RED);
                    return true;
                }

                if (args.length > 2) {
                    message(sender, "/admin verify <player>", ChatColor.GRAY);
                    return true;
                }

                Player player = getPlayer(args[1]);

                if (player == null) {
                    message(sender, "Cannot find player!", ChatColor.RED);
                    return true;
                }

                if (!Rank.Imposter(player)) {
                    message(sender, "That player is not an imposter!", ChatColor.RED);
                    return true;
                }

                if (Rank.Imposter(player)) {
                    Util.action(sender.getName(), "Verifying " + player.getName(), true);
                    plugin.admins.set(player.getUniqueId().toString() + ".ip", Ips.getIp(player));
                    player.kickPlayer(ChatColor.RED + "You have been verified, relog for your permissions and data!");
                    plugin.admins.save();
                    return true;
                }
            }
        }
        return true;
    }
}
