package net.reflxction.superpowers.gui;

import net.reflxction.superpowers.config.IAbilityConfigHandler;
import net.reflxction.superpowers.core.AbilityType;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.StringUtils;
import net.reflxction.superpowers.utils.managers.ConfigVariables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

/**
 * Created by Reflxction, on 01/31/18.
 */
public class AbilityGUI {

    private SuperPowers m;

    public AbilityGUI(SuperPowers m) {
        this.m = m;
    }

    private final ConfigVariables c = new ConfigVariables(m);

    private final StringUtils su = new StringUtils();

    /**
     * @param p Player to open the inventory for
     */
    public void openGUI(Player p) {
        final Inventory gui = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', c.getGuiName()));
        IAbilityConfigHandler fPunch = new IAbilityConfigHandler() {
            @Override
            public AbilityType getAbility() {
                return AbilityType.FIRE_PUNCH;
            }
        };
        IAbilityConfigHandler invis = new IAbilityConfigHandler() {
            @Override
            public AbilityType getAbility() {
                return AbilityType.INVISIBLE_CLOAK;
            }
        };
        IAbilityConfigHandler bomber = new IAbilityConfigHandler() {
            @Override
            public AbilityType getAbility() {
                return AbilityType.BOMBER;
            }
        };
        IAbilityConfigHandler thor = new IAbilityConfigHandler() {
            @Override
            public AbilityType getAbility() {
                return AbilityType.THOR;
            }
        };
        IAbilityConfigHandler vampire = new IAbilityConfigHandler() {
            @Override
            public AbilityType getAbility() {
                return AbilityType.VAMPIRE;
            }
        };
        IAbilityConfigHandler iFist = new IAbilityConfigHandler() {
            @Override
            public AbilityType getAbility() {
                return AbilityType.IRON_FIST;
            }
        };
        gui.setItem(invis.getSlot(), invis.getOverallItem());
        gui.setItem(fPunch.getSlot(), fPunch.getOverallItem());
        gui.setItem(bomber.getSlot(), bomber.getOverallItem());
        gui.setItem(thor.getSlot(), thor.getOverallItem());
        gui.setItem(vampire.getSlot(), vampire.getOverallItem());
        gui.setItem(iFist.getSlot(), iFist.getOverallItem());
        p.openInventory(gui);
    }


    private static String format(Object s) {
        return ChatColor.translateAlternateColorCodes('&', s.toString());
    }

    List<String> format(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, ChatColor.translateAlternateColorCodes('&', list.get(i)));
        }
        return list;
    }
}
