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

import net.reflxction.superpowers.core.AbilityType;
import net.reflxction.superpowers.core.SuperPowers;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class AbilityManager {

    private SuperPowers m;

    public AbilityManager(SuperPowers m) {
        this.m = (m == null) ? SuperPowers.getPlugin(SuperPowers.class) : m;
    }

    public AbilityType getAbility(Player p) {
        String abilityName = m.getPlayerDataConfig().getString("Players." + p.getName() + ".Ability");
        return AbilityType.getAbilityByName(abilityName) == null ? AbilityType.NONE : AbilityType.getAbilityByName(abilityName);
    }

    public void setAbility(Player p, AbilityType abilityType) {
        m.getPlayerDataConfig().set("Players." + p.getName() + ".UUID", p.getUniqueId().toString());
        m.getPlayerDataConfig().set("Players." + p.getName() + ".Name", p.getName());
        m.getPlayerDataConfig().set("Players." + p.getName() + ".Ability", abilityType.getName());
        try {
            m.getPlayerDataConfig().save(new File(m.getDataFolder(), "playerdata.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
