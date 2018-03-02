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

package net.reflxction.superpowers.config.abilities;

import net.reflxction.superpowers.config.IAbilityConfigHandler;
import net.reflxction.superpowers.core.AbilityType;
import net.reflxction.superpowers.core.SuperPowers;

public class VampireConfig implements IAbilityConfigHandler {

    private SuperPowers m;

    public VampireConfig(SuperPowers m) {
        this.m = (m == null) ? SuperPowers.getPlugin(SuperPowers.class) : m;
    }

    @Override
    public AbilityType getAbility() {
        return AbilityType.VAMPIRE;
    }

    @Override
    public SuperPowers getPlugin() {
        return m;
    }

    public int getChance() {
        return getInt("Abilities.Vampire.Chance");
    }

    public int getDuration() {
        return getInt("Abilities.Vampire.Duration");
    }

    public int getRegenDuration() {
        return getInt("Abilities.Vampire.RegenDuration");
    }

    public boolean isRegenEnabled() {
        return getBoolean("Abilities.Vampire.GiveRegenOnHit");
    }

}
