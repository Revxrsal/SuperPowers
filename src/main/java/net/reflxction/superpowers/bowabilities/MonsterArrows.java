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

import net.reflxction.superpowers.config.bow_abilities.MonsterArrowsConfig;
import net.reflxction.superpowers.core.AbstractProjectileListener;
import net.reflxction.superpowers.core.BowAbility;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.CheckUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.util.Random;

public class MonsterArrows extends AbstractProjectileListener {

    private SuperPowers m;

    private MonsterArrowsConfig mconfig = new MonsterArrowsConfig(m);

    public MonsterArrows(SuperPowers m) {
        super(m);
        this.m = (m == null) ? SuperPowers.getPlugin(SuperPowers.class) : m;
    }

    @EventHandler
    @Override
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            Player p = ((Player) event.getEntity().getShooter());
            if (CheckUtils.canUseAbility(p, BowAbility.MONSTER_ARROWS)) {
                event.getEntity().setCustomName("MonsterArrows");
            }
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity().getCustomName() != null && event.getEntity().getCustomName().equalsIgnoreCase("MonsterArrows")) {
            Random r = new Random();
            int x = r.nextInt(100) + 1;
            if (x <= mconfig.getChance()) {
                title(((Player) event.getEntity().getShooter()), BowAbility.MONSTER_ARROWS);
                for (EntityType t : mconfig.getEntitiesList()) {
                    event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), t);
                }
            }
        }
    }

    @Override
    public SuperPowers getPlugin() {
        return m;
    }
}
