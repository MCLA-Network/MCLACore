/*
 * Project: Timer
 * Class: com.leontg77.timer.commands.TimerCommand
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2018 Leon Vaktskjold <leontg77@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package me.notdew.com.mclacore;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import me.notdew.com.mclacore.handling.TimerHandler;
import me.notdew.com.mclacore.handling.handlers.BossBarHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;


/**
 * Timer command class.
 *
 * @author LeonTG
 */
public class TimerCommand implements CommandExecutor, TabCompleter {

    private final MCLACore plugin;
    private final List<String> colors = Lists.newArrayList();
    private final List<String> styles = Lists.newArrayList();

    public TimerCommand(MCLACore plugin) {

        this.plugin = plugin;

        try {
            for (Object enumz : Class.forName("org.bukkit.boss.BarColor").getEnumConstants()) {
                colors.add(enumz.toString().toLowerCase());
            }

            for (Object enumz : Class.forName("org.bukkit.boss.BarStyle").getEnumConstants()) {
                styles.add(enumz.toString().toLowerCase());
            }
        } catch (ClassNotFoundException ex) {
            plugin.getLogger().log(Level.WARNING, "Unable to find tab completable colors and styles for boss bars.", ex);
        }
    }

    private static final String PERMISSION = "timer.manage";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {

        if (!sender.hasPermission(PERMISSION)) {
            sender.sendMessage(ChatColor.RED + "You can't use that command.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(MCLACore.PREFIX + "Usage: §c/timer <seconds|-1> <message> §7| §c/timer cancel");
            return true;
        }

        if (args[0].equalsIgnoreCase("cancel")) {
            if (!plugin.getRunnable().isRunning()) {
                sender.sendMessage(ChatColor.RED + "There are no timers running.");
                return true;
            }

            plugin.getRunnable().cancel();
            sender.sendMessage(MCLACore.PREFIX + "The timer has been cancelled.");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (plugin.getRunnable().isRunning()) {
                sender.sendMessage(ChatColor.RED + "Cancel the current timer before you can reloading.");
                return true;
            }

            plugin.getRunnable().cancel();
            plugin.reloadConfig();

            sender.sendMessage(MCLACore.PREFIX + "Timer config has been reloaded.");
            return true;
        }
        if (args[0].equalsIgnoreCase("pause")) {
            if (plugin.getRunnable().isRunning()) {
                plugin.getRunnable().countdown = false;
                sender.sendMessage(MCLACore.PREFIX + "Timer has been paused.");
            } else {
                sender.sendMessage(MCLACore.PREFIX + "The timer is already paused.");
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("unpause")) {
            if (plugin.getRunnable().countdown != true) {
                plugin.getRunnable().countdown = true;
                sender.sendMessage(MCLACore.PREFIX + "Timer has been unpaused");
            } else {
                sender.sendMessage(MCLACore.PREFIX + "The timer is not pasued");
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("items")) {
            MCLACore.getItems(sender);
            return true;
        }
        if (args[0].equalsIgnoreCase("update")) {
            TimerHandler handler = plugin.getRunnable().getHandler();

            if (!(handler instanceof BossBarHandler)) {
                sender.sendMessage(ChatColor.RED + "Boss bar timer is disabled, coloring and style doesn't work in the action bar.");
                return true;
            }

            BossBarHandler bossBar = (BossBarHandler) handler;

            if (args.length == 1) {
                sender.sendMessage(MCLACore.PREFIX + "Usage: §c/timer update <color> [style]");
                return true;
            }

            String color = args[1];
            String style = "solid";

            if (args.length > 2) {
                style = args[2];
            }

            try {
                bossBar.update(color.toUpperCase(), style.toUpperCase());

                plugin.getConfig().set("bossbar.color", color);
                plugin.getConfig().set("bossbar.style", style);
                plugin.saveConfig();

                sender.sendMessage(MCLACore.PREFIX + "Boss bar settings have been updated.");
            } catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException ex) {
                sender.sendMessage(ChatColor.RED + "The color or style you entered is invalid, use tab-complete!");
                return true;
            }
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(MCLACore.PREFIX + "Usage: §c/timer <seconds|-1> <message> §7| §c/timer cancel");
            return true;
        }

        if (plugin.getRunnable().isRunning()) {
            sender.sendMessage(ChatColor.RED + "The timer is already running, cancel with /timer cancel.");
            return true;
        }

        int seconds;

        try {
            seconds = Integer.parseInt(args[0]);
        } catch (Exception ex) {
            sender.sendMessage(ChatColor.RED + "'" + args[0] + "' is not a valid time.");
            return true;
        }
        Player player = ((Player) sender);


        String message = Joiner.on(' ').join(Arrays.copyOfRange(args, 1, args.length));
        message = ChatColor.translateAlternateColorCodes('&', message);

        plugin.getRunnable().startSendingMessage(message, seconds);
        sender.sendMessage(MCLACore.PREFIX + "The timer has been started.");

        return true;
    }

    public static void createTimer(String message, int seconds) {
        MCLACore.getRunnable().startSendingMessage(message, seconds);
        pauseTimer();
    }

    public static void pauseTimer() {
        MCLACore.getRunnable().countdown = false;
    }
    public static void resumeTimer() {MCLACore.getRunnable().countdown = true;}

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission(PERMISSION)) {
            return null;
        }

        List<String> toReturn = Lists.newArrayList();

        if (args.length == 1) {
            toReturn.add("cancel");
            toReturn.add("reload");
            toReturn.add("update");
            toReturn.add("pause");
            toReturn.add("unpause");
            toReturn.add("items");
        }

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("update")) {
                return colors;
            }

            toReturn.addAll(Bukkit.getOnlinePlayers()
                    .stream()
                    .map(Player::getName)
                    .collect(Collectors.toList()));
        }

        if (args.length == 3 && args[0].equalsIgnoreCase("update")) {
            return styles;
        }

        return StringUtil.copyPartialMatches(args[args.length - 1], toReturn, Lists.newArrayList());
    }
}