package me.notdew.com.mclacore;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import me.notdew.com.mclacore.Stats.ProfileCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.utils.MarkdownUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.Instant;

import static me.notdew.com.mclacore.Stats.ProfileCommand.rg;

public class StartGameCommand implements CommandExecutor {
    MCLACore plugin;
    public StartGameCommand(MCLACore plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender.hasPermission("ref.is"))){sender.sendMessage(ChatColor.RED + "No Permission."); return true;}

        if (args.length < 2) {sender.sendMessage(ChatColor.RED + "Not enough arguments."); return true;}

        if (args.length == 2) {

            Score score = MCLACore.getSb().getScore(args[0]);
            score.setScore(0);
            score = MCLACore.getSb().getScore(args[1]);
            score.setScore(0);

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

            ScoreCommand.team1 = args[0];
            ScoreCommand.team2 = args[1];
            ScoreCommand.startscore = true;
            TextChannel ch = plugin.getJda().getTextChannelById("942972254118109235");
            if (args[0] != null && args[1] != null) {
                Guild guild = ch.getGuild();
                EmbedBuilder emb = new EmbedBuilder()
                        .setColor(Color.GRAY)
                        .setTitle(MarkdownUtil.bold("Game Score"))
                        .setDescription(String.format(args[0] + " - " + MCLACore.getSb().getScore(args[0]).getScore() + "\n" + args[1] + " - "  + MCLACore.getSb().getScore(args[1]).getScore()))
                        .setFooter(guild.getName(), guild.getIconUrl())
                        .setTimestamp(Instant.now());
                ch.sendMessageEmbeds(emb.build()).queue();
            }
            sender.sendMessage(ChatColor.GREEN + "Score pushed.");

        }

        return true;
    }
}
