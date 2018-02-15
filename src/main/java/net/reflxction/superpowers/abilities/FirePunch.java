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
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;

/**
 * Created by Reflxction, on 02/02/18.
 */
public class FirePunch implements AbilityListener {

    private SuperPowers m;

    public FirePunch(SuperPowers m) {
        this.m = (m == null) ? SuperPowers.getPlugin() : m;
    }

    private ConfigVariables c = new ConfigVariables(m);

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player p = (Player) event.getDamager();
            Random r = new Random();
            if (CheckUtils.canUseAbility(p, AbilityType.FIRE_PUNCH)) {
                int chance = r.nextInt(100 - 1) + 1;
                if (chance <= c.getFPunchChance()) {
                    event.getEntity().setFireTicks(c.getFPunchFireTicks());
                    TitleAPI.sendTitle(p, 10, 10, 10, ChatColor.RED + "Ability activated!", "");

                }
            }
        }
    }
}
