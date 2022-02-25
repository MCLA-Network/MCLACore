package me.notdew.com.mclacore;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemListener implements Listener {

    private final MCLACore plugin;

    public ItemListener(MCLACore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClickItem(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        if (item == null) {
            return;
        }
        if (!(item.equals(getStartItem()) || item.equals(getStopItem()) || item.equals(getPauseItem()))) {
            return;
        }
        if (!e.getPlayer().hasPermission("timer.manage")) {
            e.getPlayer().sendMessage(ChatColor.RED + "You have no permission!");
            return;
        }
        if (item.equals(getStartItem())) {
            e.getPlayer();
            plugin.getRunnable().countdown = true;
        } else if (item.equals(getStopItem())) {
            e.getPlayer();
            plugin.getRunnable().cancel();
        } else if (item.equals(getPauseItem())) {
            e.getPlayer();
            plugin.getRunnable().countdown = false;
        }

    }

    @EventHandler
    public void onPlaceItem(BlockPlaceEvent e) {
        ItemStack item = e.getItemInHand();
        if ( item.equals(getStartItem()) || item.equals(getStopItem()) || item.equals(getPauseItem()) ) {
            e.setCancelled(true);
        }
    }


    public static ItemStack getStartItem() {
        ItemStack startItem = new ItemStack(Material.SLIME_BLOCK, 1);
        ItemMeta i = startItem.getItemMeta();
        i.setDisplayName(ChatColor.GREEN + "Start Timer");
        startItem.setItemMeta(i);
        return startItem;
    }

    public static ItemStack getStopItem() {
        ItemStack stopItem = new ItemStack(Material.BARRIER, 1);
        ItemMeta i = stopItem.getItemMeta();
        i.setDisplayName( ChatColor.RED + "Stop Timer");
        stopItem.setItemMeta(i);
        return stopItem;
    }

    public static ItemStack getPauseItem() {
        ItemStack pauseItem = new ItemStack(Material.STONE, 1);
        ItemMeta i = pauseItem.getItemMeta();
        i.setDisplayName(ChatColor.GRAY + "Pause Timer");
        pauseItem.setItemMeta(i);
        return pauseItem;
    }

}