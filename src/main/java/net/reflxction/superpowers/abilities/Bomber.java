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
import net.reflxction.superpowers.config.abilities.BomberConfig;
import net.reflxction.superpowers.core.AbilityListener;
import net.reflxction.superpowers.core.AbilityType;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.CheckUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Represents the listener for the bomber ability
 */
public class Bomber implements AbilityListener {

    // Instance of the main class
    private SuperPowers m;

    /**
     * @param m Main class
     */
    public Bomber(SuperPowers m) {
        // Use the static method provided by Bukkit if the constructor fails
        this.m = (m == null) ? SuperPowers.getPlugin(SuperPowers.class) : m;
    }

    // Instance of the config variables related to this ability
    private final BomberConfig bconfig = new BomberConfig(m);

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        // Instance of the player who died
        final Player p = event.getEntity();
        // Check if they can use the ability
        if (CheckUtils.canUseAbility(p, AbilityType.BOMBER)) {
            // Run the API
            PlayerUseAbilityEvent apiEvent = new PlayerUseAbilityEvent(p, AbilityType.BOMBER);
            Bukkit.getPluginManager().callEvent(apiEvent);
            // Check if the event wasn't cancelled by the API
            if (!apiEvent.isCancelled()) {
                // boom i guess
                p.getWorld().createExplosion(p.getLocation(), bconfig.getExplosionPower(), true);
                // Handle the title and everything specified in the config
                title(p, AbilityType.BOMBER);
            }
        }
    }

    /**
     * @return Instance of the main class, as required by the AbilityListener interface
     */
    @Override
    public SuperPowers getPlugin() {
        return m;
    }
}
