package me.notdew.com.mclacore;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import me.notdew.com.mclacore.Stats.ProfileCommand;
import me.notdew.com.mclacore.Steal.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.SelfUser;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;

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
    private static List<Player> s3hitlist = new ArrayList<>();
    private static List<Player> s3OneHitList = new ArrayList<Player>();
    private static List<Player> s3TwoHitList = new ArrayList<Player>();
    public static List<Player> s3getHitList() {return s3hitlist;}
    public static List<Player> s3getOneHitList() {return s3OneHitList;}
    public static List<Player> s3getTwoHitList() {return s3TwoHitList;}
    private static List<Player> s4hitlist = new ArrayList<>();
    private static List<Player> s4OneHitList = new ArrayList<Player>();
    private static List<Player> s4TwoHitList = new ArrayList<Player>();
    public static List<Player> s4getHitList() {return s4hitlist;}
    public static List<Player> s4getOneHitList() {return s4OneHitList;}
    public static List<Player> s4getTwoHitList() {return s4TwoHitList;}
    private static List<Player> s5hitlist = new ArrayList<>();
    private static List<Player> s5OneHitList = new ArrayList<Player>();
    private static List<Player> s5TwoHitList = new ArrayList<Player>();
    public static List<Player> s5getHitList() {return s5hitlist;}
    public static List<Player> s5getOneHitList() {return s5OneHitList;}
    public static List<Player> s5getTwoHitList() {return s5TwoHitList;}
    private static List<Player> s6hitlist = new ArrayList<>();
    private static List<Player> s6OneHitList = new ArrayList<Player>();
    private static List<Player> s6TwoHitList = new ArrayList<Player>();
    public static List<Player> s6getHitList() {return s5hitlist;}
    public static List<Player> s6getOneHitList() {return s5OneHitList;}
    public static List<Player> s6getTwoHitList() {return s5TwoHitList;}
    private static MCLACore instance;
    private static List<Player> onField = new ArrayList<Player>();
    public static List<Player> getonField() {return onField;}

    public static MCLACore getInstance() {return instance;}
    private static int scoreID;
    public static int getId() {return scoreID;}

    private final Logger logger = null;
    private final String token = null;
    private final String address = null;
    private final String prefix = null;
    public static String away;
    public static String home;
    public static final int DEFAULT_CPS_LIMIT = 10;
    private int maxCPS;

    private FileConfiguration config = getConfig();
    private final String discordServer = null;
    private final String adminRole = null;
    private final String deathChannel = null;
    private final String joinLeaveChannel = null;
    private final String botCommandsChannel = null;
    private final String chatChannel = null;
    public static final String PREFIX = "§7(§cMCLA) Timer §7» §7";
    public final String TOKEN = "OTMyNzExNDUzNDkwODE5MTMy.YeW9Ow.DIReUfgLlM1tQMOGyrmnlvpCUWU";
    private static JDA jda;
    private LuckPerms luckPerms = LuckPermsProvider.get();
    @Override
    public void onEnable() {
        reloadConfig();
        instance = this;
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ArrowHitGround(this, luckPerms), this);
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
        getCommand("profile").setExecutor(new ProfileCommand());
        getCommand("startgame").setExecutor(new StartGameCommand(this));
        getCommand("goal").setExecutor(new GoalCommand());
        getCommand("hold").setExecutor(new HoldCommand());
        System.out.println("Connecting to Discord API");
        try {
            jda = JDABuilder.createDefault(TOKEN)
                    .setActivity(Activity.playing("discord.gg/mcla :D"))
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
    public static JDA getJda() {
        return jda;
    }


    private void handleException(Exception e) {
        logger.severe("Exception occurred: " + e.getClass().getName());
        logger.severe("With message: " + e.getMessage());
    }

    public int getMaxCPS() {
        return config.getInt("CPS Limit");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.saveConfig();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().setScoreboard(board);
        // --------------------------------

        if (this.getConfig().contains(e.getPlayer().getName())) return;

        this.getConfig().set(e.getPlayer().getDisplayName() + ".goals", 0);
        this.getConfig().set(e.getPlayer().getDisplayName() + ".steals", 0);
        this.getConfig().set(e.getPlayer().getDisplayName() + ".passes", 0);
        System.out.println("Succefully Loaded New Profile: " + e.getPlayer().getDisplayName());
        this.getInstance().saveConfig();
    }
    public static boolean isOnRegion(Player p, String region) {
        Location location = p.getLocation();
        RegionContainer regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regionManager = regionContainer.get(BukkitAdapter.adapt(location.getWorld()));
        for(ProtectedRegion r : regionManager.getApplicableRegions(BukkitAdapter.asBlockVector(p.getLocation()))) {
            if(r.getId().equalsIgnoreCase(region)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }



}
