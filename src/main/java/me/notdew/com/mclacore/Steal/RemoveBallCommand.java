package me.notdew.com.mclacore.Steal;

import me.notdew.com.mclacore.MCLACore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class RemoveBallCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        p.getInventory().removeItem(new ItemStack(Material.SPECTRAL_ARROW));

        if (p.getWorld().getName().equals("Scrim1")) {
            MCLACore.s1getHitList().remove(p);
            MCLACore.s1getOneHitList().remove(p);
            MCLACore.s1getTwoHitList().remove(p);
        }
        if (p.getWorld().getName().equals("Scrim2")) {
            MCLACore.s2getHitList().remove(p);
            MCLACore.s2getOneHitList().remove(p);
            MCLACore.s2getTwoHitList().remove(p);
        }
        if (p.getWorld().getName().equals("Hedgehogs")) {
            MCLACore.s3getHitList().remove(p);
            MCLACore.s3getOneHitList().remove(p);
            MCLACore.s3getTwoHitList().remove(p);
        }
        if (p.getWorld().getName().equals("SouthwestArena")) {
            MCLACore.getHitList().remove(p);
            MCLACore.getOneHitList().remove(p);
            MCLACore.getTwoHitList().remove(p);
        }
        return true;
    }
}
