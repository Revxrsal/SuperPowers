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
import net.reflxction.superpowers.config.abilities.IronFistConfig;
import net.reflxction.superpowers.core.AbilityListener;
import net.reflxction.superpowers.core.AbilityType;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.CheckUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;

/**
 * Represents the listener for the Iron Fist ability
 */

public class IronFist implements AbilityListener {

    // Main class instance
    private SuperPowers m;

    /**
     * @param m Main class instance
     */
    public IronFist(SuperPowers m) {
        // Use the static method provided by Bukkit if the constructor fails to set the instance
        this.m = (m == null) ? SuperPowers.getPlugin(SuperPowers.class) : m;
    }

    // Instance of the config manager for this ability
    private final IronFistConfig ifconfig = new IronFistConfig(m);

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        // Check if the damager was a player (not any other entity)
        if (event.getDamager() instanceof Player) {
            // Cast to a player, so we can use the player methods
            final Player p = ((Player) event.getDamager());
            // Check if the player can use the ability
            if (CheckUtils.canUseAbility(p, AbilityType.IRON_FIST)) {
                // Run the API
                PlayerUseAbilityEvent apiEvent = new PlayerUseAbilityEvent(p, AbilityType.IRON_FIST);
                Bukkit.getPluginManager().callEvent(apiEvent);
                // Check if the event wasn't cancelled by the API
                if (!apiEvent.isCancelled()) {
                    // Handle the chance
                    Random r = new Random();
                    int chance = r.nextInt(100 - 1) + 1;
                    if (ifconfig.getChance() >= chance) {
                        // Add the extra damage to the event damage
                        event.setDamage(event.getDamage() + ifconfig.getExtraDamage());
                        // Manage the title for the player
                        title(p, AbilityType.IRON_FIST);
                    }
                }
            }
        }
    }

    /**
     * @return Instance of the main class
     */
    @Override
    public SuperPowers getPlugin() {
        return m;
    }
}