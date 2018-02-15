package net.reflxction.superpowers.utils.managers;

import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Reflxction, on 02/01/18.
 */
public class MessageManager {

    private SuperPowers m = SuperPowers.getPlugin();

    private final StringUtils su = new StringUtils();

    private final AbilityManager au = new AbilityManager(m);

    public MessageManager(SuperPowers m) {
        this.m = (m == null) ? SuperPowers.getPlugin() : m;
    }

    private final String format(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    private String getMessage(String path) {
        return format(m.getMessagesConfig().getString("Messages." + path));
    }

    private boolean getBool(String path) {
        return m.getMessagesConfig().getBoolean(path);
    }

    public String getNotPlayerMessage() {
        return getMessage("NotPlayer");
    }

    public String getNoPermissionCmdMessage() {
        return getMessage("NoPermissionCommand");
    }

    public String getNoPermissionAbilityMessage() {
        return getMessage("NoPermissionAbility");
    }
/*
    public void sendMessage(Player p, String message) {
        System.out.println(au);
        System.out.println(au.getCooldownTime());
        System.out.println(au.getCooldownTime().get(p));
        //p.sendMessage(format(su.filter(message, p, au.getCooldownTime().get(p))));
    }
*/
    public void sendMessage(Player p, String message) {
        p.sendMessage(format(message));
    }


}
