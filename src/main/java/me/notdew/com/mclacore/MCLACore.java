package me.notdew.com.mclacore;

import me.notdew.com.mclacore.Steal.*;
import me.notdew.com.mclacore.handling.PacketSender;
import me.notdew.com.mclacore.handling.handlers.BossBarHandler;
import me.notdew.com.mclacore.handling.handlers.NewActionBarHandler;
import me.notdew.com.mclacore.handling.handlers.OldActionBarHandler;
import me.notdew.com.mclacore.runnable.TimerRunnable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.SelfUser;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import me.notdew.com.mclacore.ReflectionUtils;

import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class MCLACore extends JavaPlugin implements Listener {
    private static Objective obj;
    public static Objective getSb() {return obj;}
    private static Scoreboard board;
    public static Scoreboard getBoard() {return board;}
    private static List<Player> hitlist = new ArrayList<>();
    private static List<Player> OneHitList = new ArrayList<Player>();
    private static List<Player> TwoHitList = new ArrayList<Player>();
    public static List<Player> getHitList() {return hitlist;}
    public static List<Player> getOneHitList() {return OneHitList;}
    public static List<Player> getTwoHitList() {return TwoHitList;}
    private static List<Player> s1hitlist = new ArrayList<>();
    private static List<Player> s1OneHitList = new ArrayList<Player>();
    private static List<Player> s1TwoHitList = new ArrayList<Player>();
    public static List<Player> s1getHitList() {return s1hitlist;}
    public static List<Player> s1getOneHitList() {return s1OneHitList;}
    public static List<Player> s1getTwoHitList() {return s1TwoHitList;}
    private static List<Player> s2hitlist = new ArrayList<>();
    private static List<Player> s2OneHitList = new ArrayList<Player>();
    private static List<Player> s2TwoHitList = new ArrayList<Player>();
    public static List<Player> s2getHitList() {return s2hitlist;}
    public static List<Player> s2getOneHitList() {return s2OneHitList;}
    public static List<Player> s2getTwoHitList() {return s2TwoHitList;}
    private static MCLACore instance;

    public static MCLACore getInstance() {return instance;}

    private final Logger logger = null;
    private final String token = null;
    private final String address = null;
    private final String prefix = null;
    public static String away;
    public static String home;
    private final String discordServer = null;
    private final String adminRole = null;
    private final String deathChannel = null;
    private final String joinLeaveChannel = null;
    private final String botCommandsChannel = null;
    private final String chatChannel = null;
    public final String TOKEN = "OTMyNzExNDUzNDkwODE5MTMy.YeW9Ow.DIReUfgLlM1tQMOGyrmnlvpCUWU";
    private JDA jda;
    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ArrowHitGround(), this);
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new PlayerHitEvent(), this);
        getServer().getPluginManager().registerEvents(new BowShot(), this);
        getServer().getPluginManager().registerEvents(new PlayerPickupArrow(), this);
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        board = manager.getNewScoreboard();
        obj = board.registerNewObjective("info", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.RED + "MCLA");
        for (Player p: Bukkit.getOnlinePlayers()) {p.setScoreboard(board);}
        getCommand("score").setExecutor(new ScoreCommand());
        getCommand("hitlists").setExecutor(new HitListTest());
        getCommand("getball").setExecutor(new GetBallCommand());
        getCommand("removeball").setExecutor(new RemoveBallCommand());
        System.out.println("Connecting to Discord API");
        try {
            jda = JDABuilder.createDefault(TOKEN)
                    .setActivity(Activity.playing("Minecraft"))
                    .build();
            try {
                jda.awaitReady();
                System.out.println("Successfully connected to Discord API");
                SelfUser bot = jda.getSelfUser();
            } catch (InterruptedException e) {
                handleException(e);
            }
        } catch (LoginException e) {
            handleException(e);
        }

    }
    public JDA getJda() {
        return jda;
    }


    private void handleException(Exception e) {
        logger.severe("Exception occurred: " + e.getClass().getName());
        logger.severe("With message: " + e.getMessage());
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().setScoreboard(board);
    }
    private static TimerRunnable runnable = null;

    public static TimerRunnable getRunnable() {
        return runnable;
    }
    @Override
    public void reloadConfig() {
        super.reloadConfig();

        if (getConfig().getConfigurationSection("bossbar") == null) {
            getConfig().set("bossbar.enabled", true);
            getConfig().set("bossbar.color", "pink");
            getConfig().set("bossbar.style", "solid");
            saveConfig();
        }

        if (runnable != null && runnable.getHandler() instanceof Listener) {
            HandlerList.unregisterAll((Listener) runnable.getHandler());
        }

        try {
            PacketSender packetSender = new PacketSender();
            FileConfiguration config = getConfig();

            if (config.getBoolean("bossbar.enabled")) {
                try {
                    runnable = new TimerRunnable(this, new BossBarHandler(this, config.getString("bossbar.color", "pink"), config.getString("bossbar.style", "solid")));
                    return;
                } catch (Exception ignored) {}

                getLogger().warning("BossBars are not supported in pre Minecraft 1.9, defaulting to action bar.");
            }

            try {
                runnable = new TimerRunnable(this, new NewActionBarHandler(packetSender));
            } catch (Exception ex) {
                runnable = new TimerRunnable(this, new OldActionBarHandler(packetSender));
            }
        } catch (Exception ex) {
            getLogger().log(Level.SEVERE, "Failed to setup action timer plugin, are you using Minecraft 1.8 or higher?", ex);
            setEnabled(false);
        }
    }
    public static void getItems(CommandSender s) {
        if (!(s instanceof Player)) {
            s.sendMessage(ChatColor.RED + "Only players can use items!");
            return;
        }
        Player p = (Player) s;
        p.getInventory().setItem(0, ItemListener.getStartItem());
        p.getInventory().setItem(1, ItemListener.getStopItem());
        p.getInventory().setItem(2, ItemListener.getPauseItem());

    }



}
