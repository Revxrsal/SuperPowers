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

package net.reflxction.superpowers.bowabilities;

import net.reflxction.superpowers.config.bow_abilities.ExplosiveArrowsConfig;
import net.reflxction.superpowers.core.AbstractProjectileListener;
import net.reflxction.superpowers.core.BowAbility;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.CheckUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.util.Random;

public class ExplosiveArrows extends AbstractProjectileListener {
    private SuperPowers m;

    private ExplosiveArrowsConfig econfig = new ExplosiveArrowsConfig(m);

    public ExplosiveArrows(SuperPowers m) {
        super(m);
        this.m = (m == null) ? SuperPowers.getPlugin(SuperPowers.class) : m;
    }

    @EventHandler
    @Override
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            Player p = ((Player) event.getEntity().getShooter());
            if (CheckUtils.canUseAbility(p, BowAbility.EXPLOSIVE_ARROWS)) {
                event.getEntity().setCustomName("ExplosiveArrows");
            }
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity().getCustomName() != null && event.getEntity().getCustomName().equalsIgnoreCase("ExplosiveArrows")) {
            Random r = new Random();
            int x = r.nextInt(100) + 1;
            if (x <= econfig.getChance()) {
                title(((Player) event.getEntity().getShooter()), BowAbility.EXPLOSIVE_ARROWS);
                event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), econfig.getExplosionPower(), econfig.leaveFire());
            }
        }
    }

    @Override
    public SuperPowers getPlugin() {
        return m;
    }
}
