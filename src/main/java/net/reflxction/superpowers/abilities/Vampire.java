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
import net.reflxction.superpowers.config.abilities.VampireConfig;
import net.reflxction.superpowers.core.AbilityListener;
import net.reflxction.superpowers.core.AbilityType;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.CheckUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

/**
 * Represents the listener for the Vampire ability
 */
public class Vampire implements AbilityListener {

    // Main class instance
    private SuperPowers m;

    /**
     * @param m Main class instance
     */
    public Vampire(SuperPowers m) {
        // Use the static method provided by Bukkit if the constructor fails to set the instance
        this.m = (m == null) ? SuperPowers.getPlugin(SuperPowers.class) : m;
    }

    // Instance of the config manager for this ability
    private final VampireConfig vconfig = new VampireConfig(m);

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        // Check if the damager was a player (not an entity)
        if (event.getDamager() instanceof Player) {
            // Cast the damager to a player so we can use player methods
            final Player p = ((Player) event.getDamager());
            // Cast the entity to a living entity so we can add potion effects
            final LivingEntity e = (LivingEntity) event.getEntity();
            // Check if the player can use the ability
            if (CheckUtils.canUseAbility(p, AbilityType.VAMPIRE)) {
                // Run the API
                PlayerUseAbilityEvent apiEvent = new PlayerUseAbilityEvent(p, AbilityType.VAMPIRE);
                Bukkit.getPluginManager().callEvent(apiEvent);
                // Check if the event wasn't cancelled by the API
                if (!apiEvent.isCancelled()) {
                    // Handle the chance
                    int chance = new Random().nextInt(100 - 1) + 1;
                    if (vconfig.getChance() >= chance) {
                        // Check if the regeneration is enabled
                        if (vconfig.isRegenEnabled())
                            // Add the regen effect
                            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, vconfig.getRegenDuration(), 1));
                        // Add the poison effect to the damaged entity
                        e.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, vconfig.getDuration(), 3, true));
                        // Manage the title for the player
                        title(p, AbilityType.VAMPIRE);
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
