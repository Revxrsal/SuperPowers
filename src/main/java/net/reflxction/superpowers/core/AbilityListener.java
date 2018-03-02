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

package net.reflxction.superpowers.core;

import com.connorlinfoot.titleapi.TitleAPI;
import net.reflxction.superpowers.api.ConfigAccess;
import net.reflxction.superpowers.utils.managers.AbilityManager;
import net.reflxction.superpowers.utils.managers.ConfigVariables;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public interface AbilityListener extends Listener, ConfigAccess {

    SuperPowers getPlugin();

    ConfigVariables c = new ConfigVariables(SuperPowers.getPlugin(SuperPowers.class));

    default void title(Player p, AbilityType a) {
        final int fadeIn = getAbilitiesConfig().getInt("Abilities." + a.getName() + ".TitleOnAbility.FadeInTicks");
        final int stay = getAbilitiesConfig().getInt("Abilities." + a.getName() + ".TitleOnAbility.StayTicks");
        final int fadeOut = getAbilitiesConfig().getInt("Abilities." + a.getName() + ".TitleOnAbility.FadeOutTicks");
        final String title = getAbilitiesConfig().getString("Abilities." + a.getName() + ".TitleOnAbility.Title");
        final String subtitle = getAbilitiesConfig().getString("Abilities." + a.getName() + ".TitleOnAbility.Subtitle");
        if (getPlugin().isTitleApiAvailable() && getAbilitiesConfig().getBoolean("Abilities." + a.getName() + ".TitleOnAbility.Show")) {
            TitleAPI.sendTitle(p, fadeIn, stay, fadeOut, title, subtitle);
        }
    }

}
