package me.notdew.com.mclacore.Stats;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import me.notdew.com.mclacore.MCLACore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ProfileCommand implements CommandExecutor {
    public static ProtectedRegion rg;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        OfflinePlayer p = (Player) sender;
        if (args.length >= 1) {
            if (args[0].equals("reload")) {
                if (!(sender.hasPermission("ref.is"))) return true;
                MCLACore.getInstance().reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "Config Reloaded.");
                return true;
            }
            if (args[0].equals("stop")) {
                if (!(sender.hasPermission("ref.is"))) return true;
                sender.sendMessage(ChatColor.RED + "Stats stopped");
                rg = null;

            }
            if (args[0].equals("edit") || args[0].equals("set")) {
                if (!(sender.hasPermission("ref.is"))) return true;
                p = Bukkit.getPlayer(args[1]);
                if (p == null) {
                    p = Bukkit.getOfflinePlayer(args[0]);
                }
                if (p == null || !p.hasPlayedBefore()) {
                    return true;
                }
                if (args[2].equals("goals")) {
                    MCLACore.getInstance().getConfig().set(p.getName() + ".goals", (args[3]));
                    sender.sendMessage(ChatColor.GREEN + "Successfully changed " + p.getName() + "'s stats.");
                    MCLACore.getInstance().saveConfig();
                    return true;
                }
                if (args[2].equals("passes")) {
                    MCLACore.getInstance().getConfig().set(p.getName() + ".passes", (args[3]));
                    sender.sendMessage(ChatColor.GREEN + "Successfully changed " + p.getName() + "'s stats.");
                    MCLACore.getInstance().saveConfig();
                    return true;
                }
                if (args[2].equals("steals")) {
                    MCLACore.getInstance().getConfig().set(p.getName() + ".steals", (args[3]));
                    sender.sendMessage(ChatColor.GREEN + "Successfully changed " + p.getName() + "'s stats.");
                    MCLACore.getInstance().saveConfig();
                    return true;
                }
                return true;

            }
            if (args[0].equals("start")) {
                if (!(sender.hasPermission("ref.is"))) return true;
                Player player = ((Player) sender);
                RegionContainer regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
                Location location = player.getLocation();
                RegionManager regionManager = regionContainer.get(BukkitAdapter.adapt(location.getWorld()));
                for (ProtectedRegion r : regionManager.getApplicableRegions(BukkitAdapter.asBlockVector(player.getLocation()))) {
                    if (r != null) {
                        rg = r;
                    }
                }
                sender.sendMessage(ChatColor.GREEN + "Stats started on field: " + rg.getId());
            }
            p = Bukkit.getPlayer(args[0]);
            if (p == null) {
                p = Bukkit.getOfflinePlayer(args[0]);
            }
            if (p == null || !p.hasPlayedBefore()) {
                return true;
            }

        }



        try {
            if (MCLACore.getInstance().getConfig().contains(p.getName())) {}
        } catch (NullPointerException e) {
            sender.sendMessage(ChatColor.RED + "Player does not have a profile. Try having them relog?");
            return true;
        }

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&0----------------"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',""));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&r&l" + p.getName() + "'s Stats:"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',""));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&rGoals: " + MCLACore.getInstance().getConfig().getString(p.getName() + ".goals")));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&rSteals: " + MCLACore.getInstance().getConfig().getString(p.getName() + ".steals")));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&rPasses: " + MCLACore.getInstance().getConfig().getString(p.getName() + ".passes")));
        double gtop;
        if (MCLACore.getInstance().getConfig().getString(p.getName() + ".passes").equals("0") || MCLACore.getInstance().getConfig().getString(p.getName() + ".goals").equals("0")){
            gtop = 0;
        } else {
            gtop = Double.parseDouble(MCLACore.getInstance().getConfig().getString(p.getName() + ".passes")) / Double.parseDouble(MCLACore.getInstance().getConfig().getString(p.getName() + ".goals"));
        }
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&rPass To Goals Ratio: " + gtop));


        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',""));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&0----------------"));

        return true;
    }


}
