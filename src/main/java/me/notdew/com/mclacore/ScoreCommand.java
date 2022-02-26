package me.notdew.com.mclacore;

import me.notdew.com.mclacore.handling.handlers.BossBarHandler;
import me.notdew.com.mclacore.runnable.TimerRunnable;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.utils.MarkdownUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Score;

import java.awt.*;
import java.time.Instant;

public class ScoreCommand implements CommandExecutor {
    private final MCLACore plugin = MCLACore.getInstance();
    public static boolean startscore = false;
    public static String team1;
    public static String team2;

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
                    updateScore();
                }


            }
            if (args[0].equalsIgnoreCase("remove")) {
                Score score = MCLACore.getSb().getScore(args[1]);
                score.getScoreboard().resetScores(args[1]);

            }
            if (args[0].equalsIgnoreCase("startscore")) {
                team1 = args[1];
                team2 = args[2];
                startscore = true;
                TextChannel ch = plugin.getJda().getTextChannelById("946982883640352808");


                if (args[1] != null && args[2] != null) {
                    Guild guild = ch.getGuild();
                    EmbedBuilder emb = new EmbedBuilder()
                            .setColor(Color.GRAY)
                            .setTitle(MarkdownUtil.bold("Game Score"))
                            .setDescription(TimerRunnable.message + "\n" + String.format(args[1] + " - " + MCLACore.getSb().getScore(args[1]).getScore() + "\n" + args[2] + " - "  + MCLACore.getSb().getScore(args[2]).getScore()))
                            .setFooter(guild.getName(), guild.getIconUrl())
                            .setTimestamp(Instant.now());
                    ch.sendMessageEmbeds(emb.build()).queue();
                }

                System.out.println("Score pushed.");
            }
            if (args[0].equalsIgnoreCase("pushscore")) {
                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle("Score", null);
                eb.setColor(Color.red);
                eb.setColor(new Color(0xF40C0C));
                eb.setColor(new Color(255, 0, 54));
                TextChannel ch = plugin.getJda().getTextChannelById("946982883640352808");




                if (team1 != null && team2 != null) {
                    Guild guild = ch.getGuild();
                    EmbedBuilder emb = new EmbedBuilder()
                            .setColor(Color.GRAY)
                            .setTitle(MarkdownUtil.bold("Final Game Score"))
                            .setDescription(TimerRunnable.message + "\n" + String.format(args[1] + " - " + MCLACore.getSb().getScore(args[1]).getScore() + "\n" + args[2] + " - "  + MCLACore.getSb().getScore(args[2]).getScore()))
                            .setFooter(guild.getName(), guild.getIconUrl())
                            .setTimestamp(Instant.now());
                    ch.editMessageEmbedsById(ch.getLatestMessageIdLong(), emb.build()).queue();
                    team1 = null;
                    team2 = null;
                    startscore = false;

                }
                System.out.println("Score pushed.");
            }



        }


        return true;
    }
    public static void updateScore() {
        if(startscore == false || team1 == null || team2 == null) return;
        startscore = true;
        TextChannel ch = MCLACore.getJda().getTextChannelById("946982883640352808");


        if (team1 != null && team2 != null) {
            Guild guild = ch.getGuild();
            EmbedBuilder emb = new EmbedBuilder()
                    .setColor(Color.GRAY)
                    .setTitle(MarkdownUtil.bold("Game Score"))
                    .setDescription(TimerRunnable.message + "\n" + String.format(team1 + " - " + MCLACore.getSb().getScore(team1).getScore() + "\n" + team2 + " - "  + MCLACore.getSb().getScore(team2).getScore()))
                    .setFooter(guild.getName(), guild.getIconUrl())
                    .setTimestamp(Instant.now());
            ch.editMessageEmbedsById(ch.getLatestMessageIdLong(), emb.build()).queue();
        }

        System.out.println("Score pushed.");




    }
}
