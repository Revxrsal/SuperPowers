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

package net.reflxction.superpowers.utils;

import net.reflxction.superpowers.core.AbilityType;
import net.reflxction.superpowers.core.BowAbility;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.managers.AbilityManager;
import net.reflxction.superpowers.utils.managers.BowAbilityManager;
import net.reflxction.superpowers.utils.managers.ConfigVariables;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CheckUtils {

    private static ConfigVariables c = new ConfigVariables(SuperPowers.getPlugin(SuperPowers.class));

    private static final SuperPowers m = SuperPowers.getPlugin(SuperPowers.class);

    public static boolean isInDisabledWorlds(Player p, AbilityType a) throws NullPointerException {
        List<String> worldsString = m.getAbilitiesConfig().getStringList("Abilities." + a.getName() + ".DisabledWorlds");
        List<World> worlds = new ArrayList<>();
        worldsString.forEach(w -> worlds.add(Bukkit.getWorld(w)));
        return worlds.contains(p.getWorld());
    }

    public static boolean isInDisabledWorlds(Player p, BowAbility a) {
        List<String> worldsString = m.getBowAbilitiesConfig().getStringList("BowAbilities." + a.getName() + ".DisabledWorlds");
        List<World> worlds = new ArrayList<>();
        worldsString.forEach(w -> worlds.add(Bukkit.getWorld(w)));
        return worlds.contains(p.getWorld());
    }

    public static boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }

    public static boolean canUseAbility(Player p, AbilityType a) {
        final AbilityManager au = new AbilityManager(m);
        if (au.getAbility(p) == a) {
            if (p.hasPermission(a.getPermission())) {
                if (AbilityType.isEnabled(a)) {
                    if (!isInDisabledWorlds(p, a)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean canUseAbility(Player p, BowAbility a) {
        BowAbilityManager b = new BowAbilityManager(m);
        if (b.getAbility(p) == a) {
            if (p.hasPermission(a.getPermission())) {
                if (BowAbility.isEnabled(a)) {
                    if (!isInDisabledWorlds(p, a)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}