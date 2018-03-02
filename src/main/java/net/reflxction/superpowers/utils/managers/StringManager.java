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
import net.reflxction.superpowers.utils.managers.AbilityManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class StringManager {

    public String capitalise(String string) {
        String s = string.substring(0, 1).toUpperCase();
        return s + string.substring(1);
    }

    public String format(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public String filter(String s, Player p, int delay) {
        AbilityType abilityType = new AbilityManager(SuperPowers.getPlugin(SuperPowers.class)).getAbility(p);
        return s.replace("{player}", p.getName()).replace("{ability}", abilityType.getName()).replace("{time_left}", s(delay));
    }


    public String filter(String s, Player p) {
        AbilityType abilityType = new AbilityManager(SuperPowers.getPlugin(SuperPowers.class)).getAbility(p);
        return s.replace("{player}", p.getName()).replace("{abilityType}", abilityType.getName());
    }


    public List<String> format(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, format(list.get(i)));
        }
        return list;
    }

    public String s(Object o) {
        return String.valueOf(o);
    }

}
