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

package net.reflxction.superpowers.utils.managers;

import net.reflxction.superpowers.api.ConfigAccess;
import net.reflxction.superpowers.core.BowAbility;
import net.reflxction.superpowers.core.SuperPowers;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class BowAbilityManager implements ConfigAccess {

    private SuperPowers m;

    public BowAbilityManager(SuperPowers m) {
        this.m = (m == null) ? SuperPowers.getPlugin(SuperPowers.class) : m;
    }

    public BowAbility getAbility(Player p) {
        String abilityName = getPlayerDataFile().getString("Players." + p.getName() + ".BowAbility");
        return BowAbility.getAbilityByName(abilityName).get();
    }

    public void setAbility(Player p, BowAbility ability) {
        getPlayerDataFile().set("Players." + p.getName() + ".UUID", p.getUniqueId().toString());
        getPlayerDataFile().set("Players." + p.getName() + ".Name", p.getName());
        getPlayerDataFile().set("Players." + p.getName() + ".BowAbility", ability.getName());
        try {
            getPlayerDataFile().save(new File(getDataFolder(), "playerdata.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public SuperPowers getPlugin() {
        return m;
    }
}
