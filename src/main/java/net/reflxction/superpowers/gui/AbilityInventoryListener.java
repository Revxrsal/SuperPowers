package net.reflxction.superpowers.gui;

import net.reflxction.superpowers.core.AbilityType;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.managers.AbilityManager;
import net.reflxction.superpowers.utils.managers.ConfigVariables;
import net.reflxction.superpowers.utils.managers.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.io.IOException;

/**
 * Created by Reflxction, on 01/31/18.
 */
public class AbilityInventoryListener implements Listener {

    private SuperPowers m;

    public AbilityInventoryListener(SuperPowers m) {
        this.m = (m == null) ? SuperPowers.getPlugin() : m;
    }

    private final ConfigVariables c = new ConfigVariables(m);

    private final AbilityManager au = new AbilityManager(m);


    private void cancel(InventoryClickEvent e) {
        e.setCancelled(true);
        e.getWhoClicked().closeInventory();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) throws IOException {
        final Inventory i = event.getInventory();
        final int slot = event.getRawSlot();
        final String title = ChatColor.stripColor(i.getTitle());
        final Player p = (Player) event.getWhoClicked();
        final MessageManager mm = new MessageManager(m);
        // Checking if the inventory is the one specified in the config
        if (title.equalsIgnoreCase(ChatColor.stripColor(format(c.getGuiName())))) {
            final int invis = c.getSlotInvisCloak();
            final int bomber = c.getSlotBomber();
            final int fpunch = c.getSlotFPunch();
            final int thor = c.getThorSlot();
            final int vampire = c.getVampireSlot();
            final int ifist = c.getIronFistSlot();
            if (slot == invis) {
                mm.sendMessage(p, "&eYour ability has been set to &cInvisible Cloak");
                au.setAbility(p, AbilityType.INVISIBLE_CLOAK);
                cancel(event);
            }
            if (slot == bomber) {
                mm.sendMessage(p, "&eYour ability has been set to &cBomber");
                au.setAbility(p, AbilityType.BOMBER);
                cancel(event);
            }
            if (slot == fpunch) {
                mm.sendMessage(p, "&eYour ability has been set to &cFire Punch");
                au.setAbility(p, AbilityType.FIRE_PUNCH);
                cancel(event);
            }
            if (slot == thor) {
                mm.sendMessage(p, "&eYour ability has been set to &cThor");
                au.setAbility(p, AbilityType.THOR);
                cancel(event);
            }
            if (slot == vampire) {
                mm.sendMessage(p, "&eYour ability has been set to &cVampre");
                au.setAbility(p, AbilityType.VAMPIRE);
                cancel(event);
            }
            if (slot == ifist) {
                mm.sendMessage(p, "&eYour ability has been set to &cIron Fist");
                au.setAbility(p, AbilityType.IRON_FIST);
                cancel(event);
            }
        }
    }

    private String format(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}

