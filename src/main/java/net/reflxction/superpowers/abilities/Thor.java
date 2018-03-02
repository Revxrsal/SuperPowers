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
import net.reflxction.superpowers.config.abilities.ThorConfig;
import net.reflxction.superpowers.core.AbilityListener;
import net.reflxction.superpowers.core.AbilityType;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.CheckUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;

public class Thor implements AbilityListener {

    private SuperPowers m;

    public Thor(SuperPowers m) {
        this.m = (m == null) ? SuperPowers.getPlugin(SuperPowers.class) : m;
    }


    private ThorConfig tconfig = new ThorConfig(m);

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            final Player p = ((Player) event.getDamager());
            final Entity e = event.getEntity();
            if (CheckUtils.canUseAbility(p, AbilityType.THOR)) {
                PlayerUseAbilityEvent apiEvent = new PlayerUseAbilityEvent(p, AbilityType.THOR);
                Bukkit.getPluginManager().callEvent(apiEvent);
                if (!apiEvent.isCancelled()) {

                    System.out.println("hi");
                    Random r = new Random();
                    int chance = r.nextInt(100 - 1) + 1;
                    if (chance <= tconfig.getChance()) {
                        e.getWorld().strikeLightning(e.getLocation());
                        title(p, AbilityType.THOR);
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