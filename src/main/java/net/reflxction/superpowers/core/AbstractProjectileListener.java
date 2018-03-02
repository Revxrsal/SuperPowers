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
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public abstract class AbstractProjectileListener implements Listener, ConfigAccess {

    protected String projectileName;

    private SuperPowers m;

    public AbstractProjectileListener(SuperPowers m) {
        this.m = (m == null) ? SuperPowers.getPlugin(SuperPowers.class) : m;
    }

    @EventHandler
    public abstract void onProjectileLaunch(ProjectileLaunchEvent event);

    protected void setName(String name) {
        this.projectileName = ChatColor.translateAlternateColorCodes('&', name);
    }


    public void title(Player p, BowAbility a) {
        final int fadeIn = getBowAbilitiesConfig().getInt("BowAbilities." + a.getName() + ".TitleOnAbility.FadeInTicks");
        final int stay = getBowAbilitiesConfig().getInt("BowAbilities." + a.getName() + ".TitleOnAbility.StayTicks");
        final int fadeOut = getBowAbilitiesConfig().getInt("BowAbilities." + a.getName() + ".TitleOnAbility.FadeOutTicks");
        final String title = getBowAbilitiesConfig().getString("BowAbilities." + a.getName() + ".TitleOnAbility.Title");
        final String subtitle = getBowAbilitiesConfig().getString("BowAbilities." + a.getName() + ".TitleOnAbility.Subtitle");
        if (m.isTitleApiAvailable() && getBowAbilitiesConfig().getBoolean("BowAbilities." + a.getName() + ".TitleOnAbility.Show")) {
            TitleAPI.sendTitle(p, fadeIn, stay, fadeOut, title, subtitle);
        }
    }

    @Override
    public SuperPowers getPlugin() {
        return m;
    }

}
