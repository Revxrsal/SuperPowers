package net.reflxction.superpowers.abilities;

import net.reflxction.superpowers.core.AbilityListener;
import net.reflxction.superpowers.core.AbilityType;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.managers.AbilityManager;
import net.reflxction.superpowers.utils.CheckUtils;
import net.reflxction.superpowers.utils.managers.ConfigVariables;
import net.reflxction.superpowers.utils.managers.MessageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Reflxction, on 01/31/18.
 */
public class InvisibleCloak implements AbilityListener{

    private SuperPowers m;

    public InvisibleCloak(SuperPowers m) {
        this.m = m;
        this.m = (this.m == null || m == null) ? SuperPowers.getPlugin() : m;
    }

    private final AbilityManager au = new AbilityManager(m);

    private final ConfigVariables c = new ConfigVariables(m);

    private final MessageManager mm = new MessageManager(m);

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        Player p = event.getPlayer();
        if (CheckUtils.canUseAbility(p, AbilityType.INVISIBLE_CLOAK)) {

            if (c.getInvisibilityDuration() == -1) {
                if (p.isSneaking()) {
                    p.removePotionEffect(PotionEffectType.INVISIBILITY);
                } else {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 2, true, c.showParticles()));
//                    au.sendAbilityCooldown(p, c.getInvisCooldown());
                }
            } else {
                if (p.isSneaking()) {
                    p.removePotionEffect(PotionEffectType.INVISIBILITY);
                } else {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, c.getInvisibilityDuration(), 2, true, c.showParticles()));
                    //                  au.sendAbilityCooldown(p, c.getInvisCooldown());
                }
            }
        }
    }


}