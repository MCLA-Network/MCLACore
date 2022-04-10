package me.notdew.com.mclacore;

import me.notdew.com.mclacore.Stats.ProfileCommand;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
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
    public ArrowHitGround(MCLACore plugin, LuckPerms luckPerms) {
        this.plugin = plugin;
        this.luckPerms = luckPerms;
    }

    private final MCLACore plugin;
    private final LuckPerms luckPerms;

    public String getGroup(Player player) {
        String group;
        // Get a LuckPerms cached metadata for the player.
        CachedMetaData metaData = this.luckPerms.getPlayerAdapter(Player.class).getMetaData(player);

        // Get their prefix.
        group = metaData.getPrefix();

        if (group.equals("&dSpectator &r")) {
            return player.getName();
        }
        return group;
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
            if (p.getWorld().getName().equals("Tarantulas")) {
                MCLACore.s4getHitList().clear();
                MCLACore.s4getOneHitList().clear();
                MCLACore.s4getTwoHitList().clear();
                MCLACore.s4getHitList().add((Player)event.getHitEntity());
            }
            if (p.getWorld().getName().equals("Cowboys")) {
                MCLACore.s5getHitList().clear();
                MCLACore.s5getOneHitList().clear();
                MCLACore.s5getTwoHitList().clear();
                MCLACore.s5getHitList().add((Player)event.getHitEntity());
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
            Player shooter = (Player) event.getEntity().getShooter();
            if (block.getType() == Material.POLISHED_ANDESITE || block.getType() == Material.WHITE_STAINED_GLASS_PANE) {
                if (shooter.getWorld().getName().equals("Scrim2")) {
                    if(ProfileCommand.rg != null) {
                        if (MCLACore.isOnRegion(shooter, ProfileCommand.rg.getId())) {

                            if (!(MCLACore.getInstance().getConfig().contains(shooter.getName()))) Bukkit.broadcastMessage(ChatColor.RED + "Cannot statkeep for: " + shooter.getName() + ". Profile not found.");
                            MCLACore.getInstance().getConfig().set(shooter.getName() + ".goals", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(shooter.getName() + ".goals")) + 1));
                            MCLACore.getInstance().getConfig().set(shooter.getName() + ".passes", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(shooter.getName() + ".passes")) - 1));
                        }
                    }
                    ArrayList<Entity> nearByEntities = (ArrayList) event.getEntity().getNearbyEntities(75, 75, 75);
                    MCLACore.s2getHitList().clear();
                    MCLACore.s2getOneHitList().clear();
                    MCLACore.s2getTwoHitList().clear();

                    for (int x = 0; x < nearByEntities.size(); ++x) {
                        if (nearByEntities.get(x) instanceof Player) {
                            Player otherplayer = (Player) nearByEntities.get(x);
                            otherplayer.sendTitle(ChatColor.RED + "GOAL!!", "GOAL BY: " + ChatColor.translateAlternateColorCodes('&', getGroup(shooter)));
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
                if(ProfileCommand.rg != null) {
                    if (MCLACore.isOnRegion(shooter, ProfileCommand.rg.getId())) {

                        if (!(MCLACore.getInstance().getConfig().contains(shooter.getName()))) Bukkit.broadcastMessage(ChatColor.RED + "Cannot statkeep for: " + shooter.getName() + ". Profile not found.");
                        MCLACore.getInstance().getConfig().set(shooter.getName() + ".goals", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(shooter.getName() + ".goals")) + 1));
                        MCLACore.getInstance().getConfig().set(shooter.getName() + ".passes", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(shooter.getName() + ".passes")) - 1));
                    }
                }
                ArrayList<Entity> nearByEntities = (ArrayList) event.getEntity().getNearbyEntities(75, 75, 75);
                MCLACore.s1getHitList().clear();
                MCLACore.s1getOneHitList().clear();
                MCLACore.s1getTwoHitList().clear();

                for (int x = 0; x < nearByEntities.size(); ++x) {
                    if (nearByEntities.get(x) instanceof Player) {
                        Player otherplayer = (Player) nearByEntities.get(x);
                        otherplayer.sendTitle(ChatColor.RED + "GOAL!!", "GOAL BY: " + ChatColor.translateAlternateColorCodes('&', getGroup(shooter)));
                    }
                }
                event.getEntity().remove();
                return;

            }  if (shooter.getWorld().getName().equals("SouthwestArena")) {
                if(ProfileCommand.rg != null) {
                    if (MCLACore.isOnRegion(shooter, ProfileCommand.rg.getId())) {

                        if (!(MCLACore.getInstance().getConfig().contains(shooter.getName()))) Bukkit.broadcastMessage(ChatColor.RED + "Cannot statkeep for: " + shooter.getName() + ". Profile not found.");
                        MCLACore.getInstance().getConfig().set(shooter.getName() + ".goals", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(shooter.getName() + ".goals")) + 1));
                        MCLACore.getInstance().getConfig().set(shooter.getName() + ".passes", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(shooter.getName() + ".passes")) - 1));
                    }
                }
                ArrayList<Entity> nearByEntities = (ArrayList) event.getEntity().getNearbyEntities(75, 75, 75);
                MCLACore.getHitList().clear();
                MCLACore.getOneHitList().clear();
                MCLACore.getTwoHitList().clear();

                for (int x = 0; x < nearByEntities.size(); ++x) {
                    if (nearByEntities.get(x) instanceof Player) {
                        Player otherplayer = (Player) nearByEntities.get(x);
                        otherplayer.sendTitle(ChatColor.RED + "GOAL!!", "GOAL BY: " + ChatColor.translateAlternateColorCodes('&', getGroup(shooter)));
                    }
                }
                event.getEntity().remove();
                return;

            } if (shooter.getWorld().getName().equals("Hedgehogs")) {
                if(ProfileCommand.rg != null) {
                    if (MCLACore.isOnRegion(shooter, ProfileCommand.rg.getId())) {

                        if (!(MCLACore.getInstance().getConfig().contains(shooter.getName()))) Bukkit.broadcastMessage(ChatColor.RED + "Cannot statkeep for: " + shooter.getName() + ". Profile not found.");
                        MCLACore.getInstance().getConfig().set(shooter.getName() + ".goals", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(shooter.getName() + ".goals")) + 1));
                        MCLACore.getInstance().getConfig().set(shooter.getName() + ".passes", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(shooter.getName() + ".passes")) - 1));
                    }
                }
                ArrayList<Entity> nearByEntities = (ArrayList) event.getEntity().getNearbyEntities(75, 75, 75);
                MCLACore.s3getHitList().clear();
                MCLACore.s3getOneHitList().clear();
                MCLACore.s3getTwoHitList().clear();

                for (int x = 0; x < nearByEntities.size(); ++x) {
                    if (nearByEntities.get(x) instanceof Player) {
                        Player otherplayer = (Player) nearByEntities.get(x);
                        otherplayer.sendTitle(ChatColor.RED + "GOAL!!", "GOAL BY: " + ChatColor.translateAlternateColorCodes('&', getGroup(shooter)));
                    }
                }
                event.getEntity().remove();
                return;

            }  if (shooter.getWorld().getName().equals("Tarantulas")) {
                if(ProfileCommand.rg != null) {
                    if (MCLACore.isOnRegion(shooter, ProfileCommand.rg.getId())) {

                        if (!(MCLACore.getInstance().getConfig().contains(shooter.getName()))) Bukkit.broadcastMessage(ChatColor.RED + "Cannot statkeep for: " + shooter.getName() + ". Profile not found.");
                        MCLACore.getInstance().getConfig().set(shooter.getName() + ".goals", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(shooter.getName() + ".goals")) + 1));
                        MCLACore.getInstance().getConfig().set(shooter.getName() + ".passes", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(shooter.getName() + ".passes")) - 1));
                    }
                }
                ArrayList<Entity> nearByEntities = (ArrayList) event.getEntity().getNearbyEntities(75, 75, 75);
                MCLACore.s4getHitList().clear();
                MCLACore.s4getOneHitList().clear();
                MCLACore.s4getTwoHitList().clear();

                for (int x = 0; x < nearByEntities.size(); ++x) {
                    if (nearByEntities.get(x) instanceof Player) {
                        Player otherplayer = (Player) nearByEntities.get(x);
                        otherplayer.sendTitle(ChatColor.RED + "GOAL!!", "GOAL BY: " + ChatColor.translateAlternateColorCodes('&', getGroup(shooter)));
                    }
                }
                event.getEntity().remove();
                return;

            }  if (shooter.getWorld().getName().equals("Cowboys")) {
                if(ProfileCommand.rg != null) {
                    if (MCLACore.isOnRegion(shooter, ProfileCommand.rg.getId())) {

                        if (!(MCLACore.getInstance().getConfig().contains(shooter.getName()))) Bukkit.broadcastMessage(ChatColor.RED + "Cannot statkeep for: " + shooter.getName() + ". Profile not found.");
                        MCLACore.getInstance().getConfig().set(shooter.getName() + ".goals", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(shooter.getName() + ".goals")) + 1));
                        MCLACore.getInstance().getConfig().set(shooter.getName() + ".passes", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(shooter.getName() + ".passes")) - 1));
                    }
                }
                ArrayList<Entity> nearByEntities = (ArrayList) event.getEntity().getNearbyEntities(75, 75, 75);
                MCLACore.s5getHitList().clear();
                MCLACore.s5getOneHitList().clear();
                MCLACore.s5getTwoHitList().clear();

                for (int x = 0; x < nearByEntities.size(); ++x) {
                    if (nearByEntities.get(x) instanceof Player) {
                        Player otherplayer = (Player) nearByEntities.get(x);
                        otherplayer.sendTitle(ChatColor.RED + "GOAL!!", "GOAL BY: " + ChatColor.translateAlternateColorCodes('&', getGroup(shooter)));
                    }
                }
                event.getEntity().remove();
                return;

            }  if (shooter.getWorld().getName().equals("Bobcats")) {
                if(ProfileCommand.rg != null) {
                    if (MCLACore.isOnRegion(shooter, ProfileCommand.rg.getId())) {

                        if (!(MCLACore.getInstance().getConfig().contains(shooter.getName()))) Bukkit.broadcastMessage(ChatColor.RED + "Cannot statkeep for: " + shooter.getName() + ". Profile not found.");
                        MCLACore.getInstance().getConfig().set(shooter.getName() + ".goals", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(shooter.getName() + ".goals")) + 1));
                        MCLACore.getInstance().getConfig().set(shooter.getName() + ".passes", (Integer.parseInt(MCLACore.getInstance().getConfig().getString(shooter.getName() + ".passes")) - 1));
                    }
                }
                ArrayList<Entity> nearByEntities = (ArrayList) event.getEntity().getNearbyEntities(75, 75, 75);
                MCLACore.s6getHitList().clear();
                MCLACore.s6getOneHitList().clear();
                MCLACore.s6getTwoHitList().clear();

                for (int x = 0; x < nearByEntities.size(); ++x) {
                    if (nearByEntities.get(x) instanceof Player) {
                        Player otherplayer = (Player) nearByEntities.get(x);
                        otherplayer.sendTitle(ChatColor.RED + "GOAL!!", "GOAL BY: " + ChatColor.translateAlternateColorCodes('&', getGroup(shooter)));
                    }
                }
                event.getEntity().remove();
                return;

            }


            return;


        }
    }
}
