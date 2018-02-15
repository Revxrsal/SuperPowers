package net.reflxction.superpowers.core;

import net.reflxction.superpowers.api.events.PlayerUseAbilityEvent;
import net.reflxction.superpowers.utils.managers.AbilityManager;
import net.reflxction.superpowers.utils.managers.ConfigVariables;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * Created by Reflxction, on 02/09/18.
 */
public interface AbilityListener extends Listener {

    SuperPowers m = SuperPowers.getPlugin();

    AbilityManager au = new AbilityManager(m);

    ConfigVariables c = new ConfigVariables(m);

    default void runAPI(Player p, AbilityType ability) {
        Bukkit.getPluginManager().callEvent(new PlayerUseAbilityEvent(p, ability));
    }

}
