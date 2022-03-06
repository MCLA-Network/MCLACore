package me.notdew.com.mclacore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ArrowHitGround implements Listener {
    public ArrowHitGround() {
    }


    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getHitBlock() == null) {
            if (!(event.getHitEntity() instanceof Player)) return;
            Player p = (Player) event.getHitEntity();

            p.getInventory().addItem(new ItemStack(Material.SPECTRAL_ARROW));
            ArrayList<Entity> nearByEntities = (ArrayList)event.getHitEntity().getNearbyEntities(75,75,75);
            nearByEntities.add(p);
            if (p.getWorld().getName().equals("Scrim1")) {
                MCLACore.s1getHitList().clear();
                MCLACore.s1getOneHitList().clear();
                MCLACore.s1getTwoHitList().clear();
                MCLACore.s1getHitList().add((Player)event.getHitEntity());
            }
            if (p.getWorld().getName().equals("Scrim2")) {
                MCLACore.s2getHitList().clear();
                MCLACore.s2getOneHitList().clear();
                MCLACore.s2getTwoHitList().clear();
                MCLACore.s2getHitList().add((Player)event.getHitEntity());
            }
            if (p.getWorld().getName().equals("Hedgehogs")) {
                MCLACore.s3getHitList().clear();
                MCLACore.s3getOneHitList().clear();
                MCLACore.s3getTwoHitList().clear();
                MCLACore.s3getHitList().add((Player)event.getHitEntity());
            }
            if (p.getWorld().getName().equals("SouthwestArena")) {
                MCLACore.getHitList().clear();
                MCLACore.getOneHitList().clear();
                MCLACore.getTwoHitList().clear();
                MCLACore.getHitList().add((Player)event.getHitEntity());
            }



            for(int x = 0; x < nearByEntities.size(); ++x) {
                if (nearByEntities.get(x) instanceof Player) {
                    Player otherplayer = (Player)nearByEntities.get(x);
                    otherplayer.sendMessage(ChatColor.WHITE + event.getHitEntity().getName() + "" + ChatColor.BLUE + " has picked up the ball.");

                }
            }
        }
        Block block = event.getHitBlock();
        try {
            if (block.getType() == Material.POLISHED_ANDESITE || block.getType() == Material.WHITE_STAINED_GLASS_PANE) {
                Player shooter = (Player) event.getEntity().getShooter();
                if (shooter.getWorld().getName().equals("Scrim2")) {
                    ArrayList<Entity> nearByEntities = (ArrayList) event.getEntity().getNearbyEntities(75, 75, 75);
                    MCLACore.s2getHitList().clear();
                    MCLACore.s2getOneHitList().clear();
                    MCLACore.s2getTwoHitList().clear();

                    for (int x = 0; x < nearByEntities.size(); ++x) {
                        if (nearByEntities.get(x) instanceof Player) {
                            Player otherplayer = (Player) nearByEntities.get(x);
                            otherplayer.sendTitle(ChatColor.RED + "GOAL!!", "GOAL BY: " + shooter.getName());
                        }
                    }
                    event.getEntity().remove();
                    return;
                }
            } else {
                Player p = (Player) event.getEntity().getShooter();
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

            }
        }
        catch (Exception e) {
            return;
        }
        if (block.getType() == Material.POLISHED_ANDESITE || block.getType() == Material.WHITE_STAINED_GLASS_PANE) {
            Player shooter = (Player) event.getEntity().getShooter();
            if (shooter.getWorld().getName().equals("Scrim1")) {
                ArrayList<Entity> nearByEntities = (ArrayList) event.getEntity().getNearbyEntities(75, 75, 75);
                MCLACore.s1getHitList().clear();
                MCLACore.s1getOneHitList().clear();
                MCLACore.s1getTwoHitList().clear();

                for (int x = 0; x < nearByEntities.size(); ++x) {
                    if (nearByEntities.get(x) instanceof Player) {
                        Player otherplayer = (Player) nearByEntities.get(x);
                        otherplayer.sendTitle(ChatColor.RED + "GOAL!!", "GOAL BY: " + shooter.getName());
                    }
                }
                event.getEntity().remove();
                return;

            } else if (shooter.getWorld().getName().equals("SouthwestArena")) {
                ArrayList<Entity> nearByEntities = (ArrayList) event.getEntity().getNearbyEntities(75, 75, 75);
                MCLACore.getHitList().clear();
                MCLACore.getOneHitList().clear();
                MCLACore.getTwoHitList().clear();

                for (int x = 0; x < nearByEntities.size(); ++x) {
                    if (nearByEntities.get(x) instanceof Player) {
                        Player otherplayer = (Player) nearByEntities.get(x);
                        otherplayer.sendTitle(ChatColor.RED + "GOAL!!", "GOAL BY: " + shooter.getName());
                    }
                }
                event.getEntity().remove();
                return;

            } else if (shooter.getWorld().getName().equals("Hedgehogs")) {
                ArrayList<Entity> nearByEntities = (ArrayList) event.getEntity().getNearbyEntities(75, 75, 75);
                MCLACore.s3getHitList().clear();
                MCLACore.s3getOneHitList().clear();
                MCLACore.s3getTwoHitList().clear();

                for (int x = 0; x < nearByEntities.size(); ++x) {
                    if (nearByEntities.get(x) instanceof Player) {
                        Player otherplayer = (Player) nearByEntities.get(x);
                        otherplayer.sendTitle(ChatColor.RED + "GOAL!!", "GOAL BY: " + shooter.getName());
                    }
                }
                event.getEntity().remove();
                return;

            }


            return;


        }
    }
}
