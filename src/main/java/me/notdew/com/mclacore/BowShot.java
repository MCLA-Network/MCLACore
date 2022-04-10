package me.notdew.com.mclacore;

import me.notdew.com.mclacore.Stats.ProfileCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class BowShot implements Listener {

    @EventHandler
    public void onBowShot(EntityShootBowEvent e) {
        if (e.getEntity() instanceof Player) {



            Player player = (Player) e.getEntity();
            Player p = (Player) e.getEntity();
            if(ProfileCommand.rg != null) {
                if (MCLACore.isOnRegion(p, ProfileCommand.rg.getId())) {

                    if (!(MCLACore.getInstance().getConfig().contains(p.getName()))) Bukkit.broadcastMessage(ChatColor.RED + "Cannot statkeep for: " + p.getName() + ". Profile not found.");
                    MCLACore.getInstance().getConfig().set(p.getName() + ".passes", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(p.getName() + ".passes")) + 1));
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
        }

    }



}