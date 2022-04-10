package me.notdew.com.mclacore.Steal;

import me.notdew.com.mclacore.MCLACore;
import me.notdew.com.mclacore.Stats.ProfileCommand;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpectralArrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class PlayerHitEvent implements Listener {
    private BukkitTask task;
    private MCLACore plugin;
    private int schedulerId = -1;


    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {

        if ((e.getDamager() instanceof Player && e.getEntity() instanceof Player)) {

            Player player = (Player) e.getEntity();
                if (player.getWorld().getName().equals("Scrim1")) {
                    if (MCLACore.s1getHitList().contains(player)) {
                        MCLACore.s1getOneHitList().add(player);
                        MCLACore.s1getHitList().remove(player);
                        return;
                    }
                    if (MCLACore.s1getOneHitList().contains(player)) {
                        MCLACore.s1getTwoHitList().add(player);
                        MCLACore.s1getOneHitList().remove(player);
                        return;
                    }
                }
                if (player.getWorld().getName().equals("Hedgehogs")) {
                    if (MCLACore.s3getHitList().contains(player)) {
                        MCLACore.s3getOneHitList().add(player);
                        MCLACore.s3getHitList().remove(player);
                        return;
                    }
                    if (MCLACore.s3getOneHitList().contains(player)) {
                        MCLACore.s3getTwoHitList().add(player);
                        MCLACore.s3getOneHitList().remove(player);
                        return;
                    }
                }
                if (player.getWorld().getName().equals("Scrim2")) {
                    if (MCLACore.s2getHitList().contains(player)) {
                        MCLACore.s2getOneHitList().add(player);
                        MCLACore.s2getHitList().remove(player);
                        return;
                    }
                    if (MCLACore.s2getOneHitList().contains(player)) {
                        MCLACore.s2getTwoHitList().add(player);
                        MCLACore.s2getOneHitList().remove(player);
                        return;
                    }
                }
                if (player.getWorld().getName().equals("SouthwestArena")) {
                    if (MCLACore.getHitList().contains(player)) {
                        MCLACore.getOneHitList().add(player);
                        MCLACore.getHitList().remove(player);
                        return;
                    }
                    if (MCLACore.getOneHitList().contains(player)) {
                        MCLACore.getTwoHitList().add(player);
                        MCLACore.getOneHitList().remove(player);
                        return;
                    }
                }
                if (player.getWorld().getName().equals("Tarantulas")) {
                    if (MCLACore.s4getHitList().contains(player)) {
                        MCLACore.s4getOneHitList().add(player);
                        MCLACore.s4getHitList().remove(player);
                        return;
                    }
                    if (MCLACore.s4getOneHitList().contains(player)) {
                        MCLACore.s4getTwoHitList().add(player);
                        MCLACore.s4getOneHitList().remove(player);
                        return;
                }
            }
            if (player.getWorld().getName().equals("Cowboys")) {
                if (MCLACore.s5getHitList().contains(player)) {
                    MCLACore.s5getOneHitList().add(player);
                    MCLACore.s5getHitList().remove(player);
                    return;
                }
                if (MCLACore.s5getOneHitList().contains(player)) {
                    MCLACore.s5getTwoHitList().add(player);
                    MCLACore.s5getOneHitList().remove(player);
                    return;
                }
            }
            if (player.getWorld().getName().equals("Bobcats")) {
                if (MCLACore.s6getHitList().contains(player)) {
                    MCLACore.s6getOneHitList().add(player);
                    MCLACore.s6getHitList().remove(player);
                    return;
                }
                if (MCLACore.s6getOneHitList().contains(player)) {
                    MCLACore.s6getTwoHitList().add(player);
                    MCLACore.s6getOneHitList().remove(player);
                    return;
                }
            }




            if (MCLACore.s1getTwoHitList().contains(player)) {
                if(ProfileCommand.rg != null) {
                    if (MCLACore.isOnRegion(player, ProfileCommand.rg.getId())) {

                        if (!(MCLACore.getInstance().getConfig().contains(player.getName()))) Bukkit.broadcastMessage(ChatColor.RED + "Cannot statkeep for: " + player.getName() + ". Profile not found.");
                        MCLACore.getInstance().getConfig().set(player.getName() + ".steals", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(player.getName() + ".steals")) + 1));
                    }
                }
                MCLACore.s1getTwoHitList().remove(player);
                World world = player.getWorld();
                Player p = player;
                Location l = player.getLocation();
                MCLACore.s1getHitList().clear();
                MCLACore.s1getOneHitList().clear();
                MCLACore.s1getTwoHitList().clear();
                MCLACore.s1getHitList().add((Player) e.getDamager());
                ((Player) e.getDamager()).getInventory().addItem(new ItemStack(Material.SPECTRAL_ARROW));
                ArrayList<Entity> nearByEntities = (ArrayList)e.getEntity().getNearbyEntities(75,75,75);
                nearByEntities.add((Player) e.getEntity());
                ((Player) e.getEntity()).getInventory().remove(new ItemStack (Material.SPECTRAL_ARROW));

                for(int x = 0; x < nearByEntities.size(); ++x) {
                    if (nearByEntities.get(x) instanceof Player) {
                        Player otherplayer = (Player)nearByEntities.get(x);
                        otherplayer.sendMessage(ChatColor.WHITE + e.getDamager().getName() + "" + ChatColor.RED + " has STOLEN the ball.");
                    }
                }
            }
            if (MCLACore.s3getTwoHitList().contains(player)) {
                if(ProfileCommand.rg != null) {
                    if (MCLACore.isOnRegion(player, ProfileCommand.rg.getId())) {

                        if (!(MCLACore.getInstance().getConfig().contains(player.getName()))) Bukkit.broadcastMessage(ChatColor.RED + "Cannot statkeep for: " + player.getName() + ". Profile not found.");
                        MCLACore.getInstance().getConfig().set(player.getName() + ".steals", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(player.getName() + ".steals")) + 1));
                    }
                }
                MCLACore.s3getTwoHitList().remove(player);
                World world = player.getWorld();
                Player p = player;
                Location l = player.getLocation();
                MCLACore.s3getHitList().clear();
                MCLACore.s3getOneHitList().clear();
                MCLACore.s3getTwoHitList().clear();
                MCLACore.s3getHitList().add((Player) e.getDamager());
                ((Player) e.getDamager()).getInventory().addItem(new ItemStack(Material.SPECTRAL_ARROW));
                ArrayList<Entity> nearByEntities = (ArrayList)e.getEntity().getNearbyEntities(75,75,75);
                nearByEntities.add((Player) e.getEntity());
                ((Player) e.getEntity()).getInventory().remove(new ItemStack (Material.SPECTRAL_ARROW));

                for(int x = 0; x < nearByEntities.size(); ++x) {
                    if (nearByEntities.get(x) instanceof Player) {
                        Player otherplayer = (Player)nearByEntities.get(x);
                        otherplayer.sendMessage(ChatColor.WHITE + e.getDamager().getName() + "" + ChatColor.RED + " has STOLEN the ball.");
                    }
                }
            }
            if (MCLACore.s2getTwoHitList().contains(player)) {
                if(ProfileCommand.rg != null) {
                    if (MCLACore.isOnRegion(player, ProfileCommand.rg.getId())) {

                        if (!(MCLACore.getInstance().getConfig().contains(player.getName()))) Bukkit.broadcastMessage(ChatColor.RED + "Cannot statkeep for: " + player.getName() + ". Profile not found.");
                        MCLACore.getInstance().getConfig().set(player.getName() + ".steals", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(player.getName() + ".steals")) + 1));
                    }
                }

                MCLACore.s2getTwoHitList().remove(player);
                World world = player.getWorld();
                Player p = player;
                Location l = player.getLocation();
                MCLACore.s2getHitList().clear();
                MCLACore.s2getOneHitList().clear();
                MCLACore.s2getTwoHitList().clear();
                MCLACore.s2getHitList().add((Player) e.getDamager());
                ((Player) e.getDamager()).getInventory().addItem(new ItemStack(Material.SPECTRAL_ARROW));
                ArrayList<Entity> nearByEntities = (ArrayList)e.getEntity().getNearbyEntities(75,75,75);
                nearByEntities.add((Player) e.getEntity());
                ((Player) e.getEntity()).getInventory().remove(new ItemStack (Material.SPECTRAL_ARROW));

                for(int x = 0; x < nearByEntities.size(); ++x) {
                    if (nearByEntities.get(x) instanceof Player) {
                        Player otherplayer = (Player)nearByEntities.get(x);
                        otherplayer.sendMessage(ChatColor.WHITE + e.getDamager().getName() + "" +  ChatColor.RED + " has STOLEN the ball.");
                    }
                }
            }
            if (MCLACore.s4getTwoHitList().contains(player)) {
                if(ProfileCommand.rg != null) {
                    if (MCLACore.isOnRegion(player, ProfileCommand.rg.getId())) {

                        if (!(MCLACore.getInstance().getConfig().contains(player.getName()))) Bukkit.broadcastMessage(ChatColor.RED + "Cannot statkeep for: " + player.getName() + ". Profile not found.");
                        MCLACore.getInstance().getConfig().set(player.getName() + ".steals", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(player.getName() + ".steals")) + 1));
                    }
                }

                MCLACore.s4getTwoHitList().remove(player);
                World world = player.getWorld();
                Player p = player;
                Location l = player.getLocation();
                MCLACore.s4getHitList().clear();
                MCLACore.s4getOneHitList().clear();
                MCLACore.s4getTwoHitList().clear();
                MCLACore.s4getHitList().add((Player) e.getDamager());
                ((Player) e.getDamager()).getInventory().addItem(new ItemStack(Material.SPECTRAL_ARROW));
                ArrayList<Entity> nearByEntities = (ArrayList)e.getEntity().getNearbyEntities(75,75,75);
                nearByEntities.add((Player) e.getEntity());
                ((Player) e.getEntity()).getInventory().remove(new ItemStack (Material.SPECTRAL_ARROW));

                for(int x = 0; x < nearByEntities.size(); ++x) {
                    if (nearByEntities.get(x) instanceof Player) {
                        Player otherplayer = (Player)nearByEntities.get(x);
                        otherplayer.sendMessage(ChatColor.WHITE + e.getDamager().getName() + "" +  ChatColor.RED + " has STOLEN the ball.");
                    }
                }
            }
            if (MCLACore.s5getTwoHitList().contains(player)) {
                if(ProfileCommand.rg != null) {
                    if (MCLACore.isOnRegion(player, ProfileCommand.rg.getId())) {

                        if (!(MCLACore.getInstance().getConfig().contains(player.getName()))) Bukkit.broadcastMessage(ChatColor.RED + "Cannot statkeep for: " + player.getName() + ". Profile not found.");
                        MCLACore.getInstance().getConfig().set(player.getName() + ".steals", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(player.getName() + ".steals")) + 1));
                    }
                }

                MCLACore.s5getTwoHitList().remove(player);
                World world = player.getWorld();
                Player p = player;
                Location l = player.getLocation();
                MCLACore.s5getHitList().clear();
                MCLACore.s5getOneHitList().clear();
                MCLACore.s5getTwoHitList().clear();
                MCLACore.s5getHitList().add((Player) e.getDamager());
                ((Player) e.getDamager()).getInventory().addItem(new ItemStack(Material.SPECTRAL_ARROW));
                ArrayList<Entity> nearByEntities = (ArrayList)e.getEntity().getNearbyEntities(75,75,75);
                nearByEntities.add((Player) e.getEntity());
                ((Player) e.getEntity()).getInventory().remove(new ItemStack (Material.SPECTRAL_ARROW));

                for(int x = 0; x < nearByEntities.size(); ++x) {
                    if (nearByEntities.get(x) instanceof Player) {
                        Player otherplayer = (Player)nearByEntities.get(x);
                        otherplayer.sendMessage(ChatColor.WHITE + e.getDamager().getName() + "" +  ChatColor.RED + " has STOLEN the ball.");
                    }
                }
            }
            if (MCLACore.s6getTwoHitList().contains(player)) {
                if(ProfileCommand.rg != null) {
                    if (MCLACore.isOnRegion(player, ProfileCommand.rg.getId())) {

                        if (!(MCLACore.getInstance().getConfig().contains(player.getName()))) Bukkit.broadcastMessage(ChatColor.RED + "Cannot statkeep for: " + player.getName() + ". Profile not found.");
                        MCLACore.getInstance().getConfig().set(player.getName() + ".steals", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(player.getName() + ".steals")) + 1));
                    }
                }

                MCLACore.s6getTwoHitList().remove(player);
                World world = player.getWorld();
                Player p = player;
                Location l = player.getLocation();
                MCLACore.s6getHitList().clear();
                MCLACore.s6getOneHitList().clear();
                MCLACore.s6getTwoHitList().clear();
                MCLACore.s6getHitList().add((Player) e.getDamager());
                ((Player) e.getDamager()).getInventory().addItem(new ItemStack(Material.SPECTRAL_ARROW));
                ArrayList<Entity> nearByEntities = (ArrayList)e.getEntity().getNearbyEntities(75,75,75);
                nearByEntities.add((Player) e.getEntity());
                ((Player) e.getEntity()).getInventory().remove(new ItemStack (Material.SPECTRAL_ARROW));

                for(int x = 0; x < nearByEntities.size(); ++x) {
                    if (nearByEntities.get(x) instanceof Player) {
                        Player otherplayer = (Player)nearByEntities.get(x);
                        otherplayer.sendMessage(ChatColor.WHITE + e.getDamager().getName() + "" +  ChatColor.RED + " has STOLEN the ball.");
                    }
                }
            }
            if (MCLACore.getTwoHitList().contains(player)) {

                if(ProfileCommand.rg != null) {
                    if (MCLACore.isOnRegion(player, ProfileCommand.rg.getId())) {

                        if (!(MCLACore.getInstance().getConfig().contains(player.getName()))) Bukkit.broadcastMessage(ChatColor.RED + "Cannot statkeep for: " + player.getName() + ". Profile not found.");
                        MCLACore.getInstance().getConfig().set(player.getName() + ".steals", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(player.getName() + ".steals")) + 1));
                    }
                }
                //-----------------------------------------
                MCLACore.getTwoHitList().remove(player);
                World world = player.getWorld();
                Player p = player;
                Location l = player.getLocation();
                MCLACore.getHitList().clear();
                MCLACore.getOneHitList().clear();
                MCLACore.getTwoHitList().clear();
                MCLACore.getHitList().add((Player) e.getDamager());
                ((Player) e.getDamager()).getInventory().addItem(new ItemStack(Material.SPECTRAL_ARROW));
                ArrayList<Entity> nearByEntities = (ArrayList)e.getEntity().getNearbyEntities(75,75,75);
                nearByEntities.add((Player) e.getEntity());
                ((Player) e.getEntity()).getInventory().remove(new ItemStack (Material.SPECTRAL_ARROW));

                for(int x = 0; x < nearByEntities.size(); ++x) {
                    if (nearByEntities.get(x) instanceof Player) {
                        Player otherplayer = (Player)nearByEntities.get(x);
                        otherplayer.sendMessage(ChatColor.WHITE + e.getDamager().getName() + ChatColor.RED + " has STOLEN the ball.");
                    }
                }
            }
        }
    }
}