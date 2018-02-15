package net.reflxction.superpowers.utils;

import net.reflxction.superpowers.core.AbilityType;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.managers.AbilityManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Reflxction, on 01/31/18.
 */
public class StringUtils {

    public String capitalise(String string) {
        String s = string.substring(0, 1).toUpperCase();
        return s + string.substring(1);
    }

    public String format(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public String filter(String s, Player p, int delay) {
        AbilityType abilityType = new AbilityManager(SuperPowers.getPlugin()).getAbility(p);
        return s.replace("{player}", p.getName()).replace("{ability}", abilityType.getName()).replace("{time_left}", s(delay));
    }


    public String filter(String s, Player p) {
        AbilityType abilityType = new AbilityManager(SuperPowers.getPlugin()).getAbility(p);
        return s.replace("{player}", p.getName()).replace("{abilityType}", abilityType.getName());
    }


    public List<String> format(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, format(list.get(i)));
        }
        return list;
    }

    public String s(Object o) {
        return String.valueOf(o);
    }

}
