package me.notdew.com.mclacore.Steal;

import me.notdew.com.mclacore.MCLACore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpectralArrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupArrowEvent;

import java.util.ArrayList;

public class PlayerPickupArrow implements Listener {


    @EventHandler
    public void pickupArrow(PlayerPickupArrowEvent e) {
        Player player = e.getPlayer();

        if (e.getArrow() instanceof SpectralArrow) {

            if (player.getWorld().getName().equals("Scrim1")) {
                MCLACore.s1getHitList().clear();
                MCLACore.s1getOneHitList().clear();
                MCLACore.s1getTwoHitList().clear();
                MCLACore.s1getHitList().add(e.getPlayer());
                ArrayList<Entity> nearByEntities = (ArrayList)e.getPlayer().getNearbyEntities(75,75,75);
                nearByEntities.add(e.getPlayer());

                for(int x = 0; x < nearByEntities.size(); ++x) {
                    if (nearByEntities.get(x) instanceof Player) {
                        Player otherplayer = (Player)nearByEntities.get(x);
                        otherplayer.sendMessage(ChatColor.BLUE + e.getPlayer().getName() + " has picked up the ball.");
                    }
                }
                return;
            }
            if (player.getWorld().getName().equals("Scrim2")) {
                MCLACore.s2getHitList().clear();
                MCLACore.s2getOneHitList().clear();
                MCLACore.s2getTwoHitList().clear();
                MCLACore.s2getHitList().add(e.getPlayer());
                ArrayList<Entity> nearByEntities = (ArrayList)e.getPlayer().getNearbyEntities(75,75,75);
                nearByEntities.add(e.getPlayer());

                for(int x = 0; x < nearByEntities.size(); ++x) {
                    if (nearByEntities.get(x) instanceof Player) {
                        Player otherplayer = (Player)nearByEntities.get(x);
                        otherplayer.sendMessage(ChatColor.BLUE + e.getPlayer().getName() + " has picked up the ball.");
                    }
                }
                return;
            }
            if (player.getWorld().getName().equals("SouthwestArena")) {
                MCLACore.getHitList().clear();
                MCLACore.getOneHitList().clear();
                MCLACore.getTwoHitList().clear();
                MCLACore.getHitList().add(e.getPlayer());
                ArrayList<Entity> nearByEntities = (ArrayList)e.getPlayer().getNearbyEntities(75,75,75);
                nearByEntities.add(e.getPlayer());

                for(int x = 0; x < nearByEntities.size(); ++x) {
                    if (nearByEntities.get(x) instanceof Player) {
                        Player otherplayer = (Player)nearByEntities.get(x);
                        otherplayer.sendMessage(ChatColor.BLUE + e.getPlayer().getName() + " has picked up the ball.");
                    }
                }
                return;
            }
        }

    }


}
