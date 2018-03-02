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

package net.reflxction.superpowers.config.bow_abilities;

import net.reflxction.superpowers.config.IBowAbiliyConfigHandler;
import net.reflxction.superpowers.core.BowAbility;
import net.reflxction.superpowers.core.SuperPowers;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public class WolfArrowsConfig implements IBowAbiliyConfigHandler {

    private SuperPowers m;

    public WolfArrowsConfig(SuperPowers m) {
        this.m = (m == null) ? SuperPowers.getPlugin(SuperPowers.class) : m;
    }

    @Override
    public BowAbility getAbility() {
        return BowAbility.WOLF_ARROWS;
    }

    public int getChance() {
        return getInt("BowAbilities.WolfArrows.Chance");
    }

    public int wolvesToSpawn() {
        return getInt("BowAbilities.WolfArrows.WolvesToSpawn");
    }

    public void manageWolf(Wolf w, Player p) {
        w.setOwner(p);
        if (getBoolean("BowAbilities.WolfArrows.RenameWolf")) {
            String name = ChatColor.translateAlternateColorCodes('&', getString("BowAbilities.WolfArrows.WolfName").replace("{player}", p.getName()));
            if (getBoolean("BowAbilities.WolfArrows.SetNameVisible")) {
                w.setCustomNameVisible(true);
            }
            double health = getInt("BowAbilities.WolfArrows.WolfHealth");
            w.setCustomName(name);
            w.setMaxHealth(health);
            w.setHealth(health);
        }
    }

    public void spawn(Location l, Player p) {
        for (int i = 0; i < wolvesToSpawn(); i++) {
            Wolf w = (Wolf) l.getWorld().spawnEntity(l, EntityType.WOLF);
            manageWolf(w, p);
        }
    }

    @Override
    public SuperPowers getPlugin() {
        return m;
    }
}
