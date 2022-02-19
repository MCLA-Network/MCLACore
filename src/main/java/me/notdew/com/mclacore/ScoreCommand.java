package me.notdew.com.mclacore;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.utils.MarkdownUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scoreboard.Score;

import java.awt.*;
import java.time.Instant;

public class ScoreCommand implements CommandExecutor {
    private final MCLACore plugin = MCLACore.getInstance();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (args.length > 1) {

            if (args[0].equalsIgnoreCase("create")) {

                Score score = MCLACore.getSb().getScore(args[1]);
                score.setScore(0);


            }

            if (args[0].equalsIgnoreCase("set")) {

                if (MCLACore.getSb().getScore(args[1]) != null) {
                    Score score = MCLACore.getSb().getScore(args[1]);

                    score.setScore(Integer.parseInt(args[2]));
                }


            }
            if (args[0].equalsIgnoreCase("remove")) {
                Score score = MCLACore.getSb().getScore(args[1]);
                score.getScoreboard().resetScores(args[1]);

            }
            if (args[0].equalsIgnoreCase("pushscore")) {
                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle("Score", null);
                eb.setColor(Color.red);
                eb.setColor(new Color(0xF40C0C));
                eb.setColor(new Color(255, 0, 54));
                TextChannel ch = plugin.getJda().getTextChannelById("940809901972803697");




                if (args[1] != null && args[2] != null) {
                    Guild guild = ch.getGuild();
                    ch.sendMessageEmbeds(new EmbedBuilder()
                            .setColor(Color.GRAY)
                            .setTitle(MarkdownUtil.bold("Game Score"))
                            .addField(args[1] + "", "" + " - " + args[2], false)
                            .setFooter(guild.getName(), guild.getIconUrl())
                            .setTimestamp(Instant.now())
                            .build()).queue();
                }
                System.out.println("Score pushed.");
            }



        }


        return true;
    }
}
