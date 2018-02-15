package net.reflxction.superpowers.abilities;

import com.connorlinfoot.titleapi.TitleAPI;
import net.reflxction.superpowers.core.AbilityListener;
import net.reflxction.superpowers.core.AbilityType;
import net.reflxction.superpowers.utils.CheckUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

/**
 * Created by Reflxction, on 02/10/18.
 */
public class Vampire implements AbilityListener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            final Player p = ((Player) event.getDamager());
            final LivingEntity e = (LivingEntity) event.getEntity();
            if (CheckUtils.canUseAbility(p, AbilityType.VAMPIRE)) {
                int chance = new Random().nextInt(100 - 1) + 1;
                if (c.getVampireChance() >= chance) {
                    e.addPotionEffect(new PotionEffect(PotionEffectType.POISON, c.getVampireDuration(), 2, true));
                    TitleAPI.sendTitle(p, 10, 10, 10, ChatColor.RED + "Ability activated!", "");
                }
            }
        }
    }
}
