package net.reflxction.superpowers.abilities;

import com.connorlinfoot.titleapi.TitleAPI;
import net.reflxction.superpowers.core.AbilityListener;
import net.reflxction.superpowers.core.AbilityType;
import net.reflxction.superpowers.utils.CheckUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;

/**
 * Created by Reflxction, on 02/12/18.
 */
public class IronFist implements AbilityListener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            final Player p = ((Player) event.getDamager());
            if (CheckUtils.canUseAbility(p, AbilityType.IRON_FIST)) {
                Random r = new Random();
                int chance = r.nextInt(100 - 1) + 1;
                if (c.getIronFistChance() >= chance) {
                    TitleAPI.sendTitle(p, 10, 10, 10, ChatColor.RED + "Ability activated!", "");
                    event.setDamage(event.getDamage() + c.getIronFistExtraDamage());
                }
            }
        }
    }

}
