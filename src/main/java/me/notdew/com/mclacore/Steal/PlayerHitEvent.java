package me.notdew.com.mclacore.Steal;

import me.notdew.com.mclacore.MCLACore;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
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
        new BukkitRunnable() {
            @Override
            public void run(){
                cancel();
                return;
                }
        }.runTaskLater(MCLACore.getInstance(),18L);

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



            if (MCLACore.s1getTwoHitList().contains(player)) {
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
            if (MCLACore.getTwoHitList().contains(player)) {
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