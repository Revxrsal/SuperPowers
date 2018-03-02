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

public class Vampire implements AbilityListener {

    private SuperPowers m;

    public Vampire(SuperPowers m) {
        this.m = (m == null) ? SuperPowers.getPlugin(SuperPowers.class) : m;
    }

    private final VampireConfig vconfig = new VampireConfig(m);

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            final Player p = ((Player) event.getDamager());
            final LivingEntity e = (LivingEntity) event.getEntity();
            if (CheckUtils.canUseAbility(p, AbilityType.VAMPIRE)) {
                PlayerUseAbilityEvent apiEvent = new PlayerUseAbilityEvent(p, AbilityType.VAMPIRE);
                Bukkit.getPluginManager().callEvent(apiEvent);
                if (!apiEvent.isCancelled()) {
                    int chance = new Random().nextInt(100 - 1) + 1;
                    if (vconfig.getChance() >= chance) {
                        if (vconfig.isRegenEnabled())
                            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, vconfig.getRegenDuration(), 1));
                        e.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, vconfig.getDuration(), 3, true));
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
