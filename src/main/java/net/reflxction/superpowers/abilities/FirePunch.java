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
import net.reflxction.superpowers.config.abilities.FirePunchConfig;
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
 * Represents the listener for the Fire Punch ability
 */
public class FirePunch implements AbilityListener {

    // Main class instance
    private SuperPowers m;

    /**
     * @param m Main class instance
     */
    public FirePunch(SuperPowers m) {
        // Use the static method provided by Bukkit if the constructor fails to set the instance
        this.m = (m == null) ? SuperPowers.getPlugin(SuperPowers.class) : m;
    }

    // Instance of the config manager for this ability
    private final FirePunchConfig fpconfig = new FirePunchConfig(m);

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        // Check if the damager is a player (not any other entity)
        if (event.getDamager() instanceof Player) {
            // Cast the entity to player so we can use player methods
            Player p = (Player) event.getDamager();
            // Random utility from java, to handle the chance
            Random r = new Random();
            // Check if the player can use the ability
            if (CheckUtils.canUseAbility(p, AbilityType.FIRE_PUNCH)) {
                // Run the API
                PlayerUseAbilityEvent apiEvent = new PlayerUseAbilityEvent(p, AbilityType.FIRE_PUNCH);
                Bukkit.getPluginManager().callEvent(apiEvent);
                // Check if the ability isn't cancelled by the API
                if (!apiEvent.isCancelled()) {
                    // Manage the chance
                    int chance = r.nextInt(100 - 1) + 1;
                    if (chance <= fpconfig.getChance()) {
                        // After the chance has been met, run the ability
                        event.getEntity().setFireTicks(fpconfig.getFireTicks());
                        // Manager the title specified by the config of this ability
                        title(p, AbilityType.FIRE_PUNCH);
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
