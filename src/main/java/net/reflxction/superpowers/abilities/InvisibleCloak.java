/*
 * * Copyright 2017-2018 github.com/ReflxctionDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.reflxction.superpowers.abilities;

import net.reflxction.superpowers.api.events.PlayerUseAbilityEvent;
import net.reflxction.superpowers.config.abilities.InvisibleCloakConfig;
import net.reflxction.superpowers.core.AbilityListener;
import net.reflxction.superpowers.core.AbilityType;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.CheckUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Represents the listener for the Invisibility Cloak ability
 */

public class InvisibleCloak implements AbilityListener {

    // Main class instance
    private SuperPowers m;

    /**
     * @param m Main class instance
     */
    public InvisibleCloak(SuperPowers m) {
        // Use the static method provided by Bukkit if the constructor fails to set the instance
        this.m = (m == null) ? SuperPowers.getPlugin(SuperPowers.class) : m;
    }

    // Instance of the config manager for this ability
    private final InvisibleCloakConfig iconfig = new InvisibleCloakConfig(m);

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        // Instance of the player
        Player p = event.getPlayer();
        // Check if the player can use the ability
        if (CheckUtils.canUseAbility(p, AbilityType.INVISIBLE_CLOAK)) {
            // Run the API
            PlayerUseAbilityEvent apiEvent = new PlayerUseAbilityEvent(p, AbilityType.INVISIBLE_CLOAK);
            Bukkit.getPluginManager().callEvent(apiEvent);
            // Check if the API event isn't cancelled
            if (!apiEvent.isCancelled()) {
                // Manage the ability
                if (iconfig.getDuration() == -1) {
                    if (p.isSneaking()) {
                        p.removePotionEffect(PotionEffectType.INVISIBILITY);
                    } else {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 2, true, iconfig.showParticles()));
                    }
                } else {
                    if (p.isSneaking()) {
                        p.removePotionEffect(PotionEffectType.INVISIBILITY);
                    } else {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, iconfig.getDuration(), 2, true, iconfig.showParticles()));
                    }
                }
            }
        }
    }


    @Override
    public SuperPowers getPlugin() {
        return m;
    }
}