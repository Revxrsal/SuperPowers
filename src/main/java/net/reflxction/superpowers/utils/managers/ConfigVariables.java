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
import net.reflxction.superpowers.core.SuperPowers;

public class ConfigVariables implements ConfigAccess {

    private SuperPowers m;

    public ConfigVariables(SuperPowers m) {
        this.m = (m == null) ? SuperPowers.getPlugin(SuperPowers.class) : m;
    }

    /**
     * @param path Path to the variable
     * @return String value of the entered path
     */
    private String getString(String path) {
        return getConfig().getString(path);
    }


    public String getAbilitiesGuiName() {
        return getString("AbilityGuiName");
    }

    public String getBAbilitiesGuiName() {
        return getString("BowAbilityGuiName");
    }


    public int getAbilityCommandCooldown() {
        return getConfig().getInt("Ability-Command-Delay");
    }


    @Override
    public SuperPowers getPlugin() {
        System.out.println(m);
        return m;
    }
}
