package net.reflxction.superpowers.abilities;

import com.connorlinfoot.titleapi.TitleAPI;
import net.reflxction.superpowers.core.AbilityListener;
import net.reflxction.superpowers.core.AbilityType;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.CheckUtils;
import net.reflxction.superpowers.utils.managers.ConfigVariables;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by Reflxction, on 02/09/18.
 */
public class Bomber implements AbilityListener {

    private final ConfigVariables c = new ConfigVariables(SuperPowers.getPlugin());

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        final Player p = event.getEntity();
        if (CheckUtils.canUseAbility(p, AbilityType.BOMBER)) {
            p.getWorld().createExplosion(p.getLocation(), c.getBomberExplosionPower());
            runAPI(p, au.getAbility(p));

            TitleAPI.sendTitle(p, 10, 10, 10, ChatColor.RED + "Ability activated!", "");
        }
    }
}
