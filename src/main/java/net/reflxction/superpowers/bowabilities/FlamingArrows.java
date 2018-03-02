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

import net.reflxction.superpowers.config.bow_abilities.FlamingArrowsConfig;
import net.reflxction.superpowers.core.AbstractProjectileListener;
import net.reflxction.superpowers.core.BowAbility;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.CheckUtils;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.util.Random;

public class FlamingArrows extends AbstractProjectileListener {

    private FlamingArrowsConfig fconfig = new FlamingArrowsConfig(getPlugin());

    private SuperPowers m;

    public FlamingArrows(SuperPowers m) {
        super(m);
    }

    @EventHandler
    @Override
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            Player p = ((Player) event.getEntity().getShooter());
            if (CheckUtils.canUseAbility(p, BowAbility.FLAMING_ARROWS)) {
                Random r = new Random();
                int x = r.nextInt(100) + 1;
                if (x <= fconfig.getChance()) {
                    Arrow a = (Arrow) event.getEntity();
                    a.setFireTicks(Integer.MAX_VALUE);
                }
            }
        }
    }

    @Override
    public SuperPowers getPlugin() {
        return m;
    }
}
