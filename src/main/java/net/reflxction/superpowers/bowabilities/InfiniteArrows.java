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

import net.reflxction.superpowers.config.bow_abilities.InfiniteArrowsConfig;
import net.reflxction.superpowers.core.AbstractProjectileListener;
import net.reflxction.superpowers.core.BowAbility;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.CheckUtils;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class InfiniteArrows extends AbstractProjectileListener {

    private SuperPowers m;

    private InfiniteArrowsConfig iconfig = new InfiniteArrowsConfig(m);

    public InfiniteArrows(SuperPowers m) {
        super(m);
        this.m = (m == null || this.m == null) ? SuperPowers.getPlugin(SuperPowers.class) : m;
    }

    @EventHandler
    @Override
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            Player p = ((Player) event.getEntity().getShooter());
            if (CheckUtils.canUseAbility(p, BowAbility.INFINITE_ARROWS)) {
                Random r = new Random();
                int x = r.nextInt(100) + 1;
                if (x <= iconfig.getChance()) {
                    title(((Player) event.getEntity().getShooter()), BowAbility.INFINITE_ARROWS);
                    event.getEntity().setCustomName("InfiniteArrows");
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Arrow) {
            if (iconfig.playersOnly()) {
                if (event.getEntity() instanceof Player) {
                    final Arrow arrow = ((Arrow) event.getDamager());
                    final Player shooter = (Player) arrow.getShooter();
                    if (arrow.getCustomName() != null && arrow.getCustomName().equals("InfiniteArrows")) {
                        shooter.getInventory().addItem(new ItemStack(Material.ARROW));
                        shooter.updateInventory();
                    }
                }
            } else {
                final Arrow arrow = ((Arrow) event.getDamager());
                final Player shooter = (Player) arrow.getShooter();
                if (arrow.getCustomName() != null && arrow.getCustomName().equals("InfiniteArrows")) {
                    shooter.getInventory().addItem(new ItemStack(Material.ARROW));
                    shooter.updateInventory();
                }
            }
        }
    }
}
