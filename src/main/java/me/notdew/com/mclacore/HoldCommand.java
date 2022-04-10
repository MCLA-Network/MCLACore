package me.notdew.com.mclacore;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpectralArrow;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class HoldCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender.hasPermission("ref.is"))){sender.sendMessage(ChatColor.RED + "No Permission."); return true;}
        Player p = (Player) sender;

        ArrayList<Entity> nearByEntities = (ArrayList)p.getNearbyEntities(75,75,75);
        nearByEntities.add(p);

        for(int x = 0; x < nearByEntities.size(); ++x) {
            if (nearByEntities.get(x) instanceof Player) {
                Player otherplayer = (Player)nearByEntities.get(x);



                otherplayer.getInventory().remove(Material.SPECTRAL_ARROW);
                otherplayer.sendTitle(ChatColor.RED + "" + ChatColor.BOLD + "HOLD", "HOLD HAS BEEN CALLED");
            }
        }
        if (p.getWorld().getName().equals("Scrim1")) {
            MCLACore.s1getHitList().clear();
            MCLACore.s1getOneHitList().clear();
            MCLACore.s1getTwoHitList().clear();
        }
        if (p.getWorld().getName().equals("Scrim2")) {
            MCLACore.s2getHitList().clear();
            MCLACore.s2getOneHitList().clear();
            MCLACore.s2getTwoHitList().clear();
        }
        if (p.getWorld().getName().equals("SouthwestArena")) {
            MCLACore.getHitList().clear();
            MCLACore.getOneHitList().clear();
            MCLACore.getTwoHitList().clear();
        }
        if (p.getWorld().getName().equals("Hedgehogs")) {
            MCLACore.s3getHitList().clear();
            MCLACore.s3getOneHitList().clear();
            MCLACore.s3getTwoHitList().clear();
        }
        if (p.getWorld().getName().equals("Tarantulas")) {
            MCLACore.s4getHitList().clear();
            MCLACore.s4getOneHitList().clear();
            MCLACore.s4getTwoHitList().clear();
        }
        if (p.getWorld().getName().equals("Cowboys")) {
            MCLACore.s5getHitList().clear();
            MCLACore.s5getOneHitList().clear();
            MCLACore.s5getTwoHitList().clear();
        }
        if (p.getWorld().getName().equals("Bobcats")) {
            MCLACore.s6getHitList().clear();
            MCLACore.s6getOneHitList().clear();
            MCLACore.s6getTwoHitList().clear();
        }

        return true;
    }
}
