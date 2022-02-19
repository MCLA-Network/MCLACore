package me.notdew.com.mclacore.Steal;

import me.notdew.com.mclacore.MCLACore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HitListTest implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (((Player) sender).getWorld().getName().equals("Scrim1")) {
            sender.sendMessage(MCLACore.s1getHitList().toString() + " 1");
            sender.sendMessage(MCLACore.s1getOneHitList().toString());
            sender.sendMessage(MCLACore.s1getTwoHitList().toString());
        } else if (((Player) sender).getWorld().getName().equals("Scrim2")) {
            sender.sendMessage(MCLACore.s2getHitList().toString() + " 2");
            sender.sendMessage(MCLACore.s2getOneHitList().toString());
            sender.sendMessage(MCLACore.s2getTwoHitList().toString());
        } else if (((Player) sender).getWorld().getName().equals("SouthwestArena")) {
            sender.sendMessage(MCLACore.getHitList().toString() + " 3");
            sender.sendMessage(MCLACore.getOneHitList().toString());
            sender.sendMessage(MCLACore.getTwoHitList().toString());
        }
        return false;
    }
}
