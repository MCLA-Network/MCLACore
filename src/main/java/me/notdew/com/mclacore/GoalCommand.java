package me.notdew.com.mclacore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scoreboard.Score;
import org.jetbrains.annotations.NotNull;

public class GoalCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender.hasPermission("ref.is"))){sender.sendMessage(ChatColor.RED + "No Permission."); return true;}
        if (args.length == 1) {


            if (MCLACore.getSb().getScore(args[0]) != null) {
                Score score = MCLACore.getSb().getScore(args[0]);

                score.setScore(score.getScore() + 1);
                ScoreCommand.updateScore();
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Not enough arguments.");
        }




        return true;
    }
}
