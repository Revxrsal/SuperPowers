/*
 * * Copyright 2018 github.com/ReflxctionDev
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

package net.reflxction.superpowers.bowabilities;

import net.reflxction.superpowers.config.abilities.QuickArcheryConfig;
import net.reflxction.superpowers.core.AbstractProjectileListener;
import net.reflxction.superpowers.core.BowAbility;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.CheckUtils;
import net.reflxction.superpowers.utils.LoggerUtils;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class QuickArchery extends AbstractProjectileListener {

    private SuperPowers m;

    public QuickArchery(SuperPowers m) {
        super(m);
        this.m = (m == null) ? SuperPowers.getPlugin(SuperPowers.class) : m;
    }

    private QuickArcheryConfig qconfig = new QuickArcheryConfig(m);


    @Override
    public SuperPowers getPlugin() {
        return m;
    }

    @EventHandler
    @Override
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            Player p = ((Player) event.getEntity().getShooter());
            if (CheckUtils.canUseAbility(p, BowAbility.QUICK_ARCHERY)) {
                Random r = new Random();
                int x = r.nextInt(100) + 1;
                if (x <= qconfig.getChance()) {
                    event.getEntity().setCustomName("QuickArchery");
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Arrow) {
            if (qconfig.playersOnly()) {
                if (event.getEntity() instanceof Player) {
                    final Arrow arrow = ((Arrow) event.getDamager());
                    final Player shooter = (Player) arrow.getShooter();
                    if (arrow.getCustomName() != null && arrow.getCustomName().equals("QuickArchery")) {
                        shooter.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, qconfig.getDuration(), qconfig.getAmplifier()));
                    }
                }
            } else {
                final Arrow arrow = ((Arrow) event.getDamager());
                final Player shooter = (Player) arrow.getShooter();
                if (arrow.getCustomName() != null && arrow.getCustomName().equals("QuickArchery")) {
                    shooter.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, qconfig.getDuration(), qconfig.getAmplifier()));
                }
            }
        }
    }
}

