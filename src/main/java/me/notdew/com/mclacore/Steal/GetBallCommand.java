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

public class GetBallCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        p.getInventory().addItem(new ItemStack(Material.SPECTRAL_ARROW));
        ArrayList<Entity> nearByEntities = (ArrayList)p.getNearbyEntities(75,75,75);
        nearByEntities.add(p);
        for(int x = 0; x < nearByEntities.size(); ++x) {
            if (nearByEntities.get(x) instanceof Player) {
                Player otherplayer = (Player)nearByEntities.get(x);
                otherplayer.sendMessage(ChatColor.WHITE + p.getName() + ChatColor.BLUE + " has picked up the ball.");
            }
        }
        if (p.getWorld().getName().equals("Scrim1")) {
            MCLACore.s1getHitList().clear();
            MCLACore.s1getOneHitList().clear();
            MCLACore.s1getTwoHitList().clear();
        }
        if (p.getWorld().getName().equals("Hedgehogs")) {
            MCLACore.s3getHitList().clear();
            MCLACore.s3getOneHitList().clear();
            MCLACore.s3getTwoHitList().clear();
            MCLACore.s3getHitList().add(p);
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
        if (p.getWorld().getName().equals("Scrim1")) {
            MCLACore.s1getHitList().add(p);

        }
        if (p.getWorld().getName().equals("Scrim2")) {
            MCLACore.s2getHitList().add(p);

        }
        if (p.getWorld().getName().equals("SouthwestArena")) {
            MCLACore.getHitList().add(p);
        }
        return true;
    }
}
