package me.liamplugins.freedom.cmds;

import me.liamplugins.freedom.core.Freedom;
import static me.liamplugins.freedom.core.Util.message;
import net.pravian.aero.command.SimpleCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

public class C_freedomhelp extends SimpleCommand<Freedom> {
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        if (args.length > 0) {
            message(sender, "/freedomhelp", ChatColor.RED);
            return true;
        }
        
        message(sender, StringUtils.join(Freedom.handler.getRegisteredCommands().iterator(), ChatColor.WHITE + ", "));
        return true;
    }
}
