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

package net.reflxction.superpowers.config.abilities;

import net.reflxction.superpowers.config.IBowAbiliyConfigHandler;
import net.reflxction.superpowers.core.BowAbility;
import net.reflxction.superpowers.core.SuperPowers;

public class QuickArcheryConfig implements IBowAbiliyConfigHandler {

    private SuperPowers m;

    public QuickArcheryConfig(SuperPowers m) {
        this.m = (m == null) ? SuperPowers.getPlugin(SuperPowers.class) : m;
    }

    @Override
    public SuperPowers getPlugin() {
        return m;
    }

    @Override
    public BowAbility getAbility() {
        return BowAbility.QUICK_ARCHERY;
    }

    public int getChance() {
        return getInt("BowAbilities.QuickArchery.Chance");
    }

    public boolean playersOnly() {
        return getBoolean("BowAbilities.QuickArchery.PlayersOnly");
    }

    public int getDuration() {
        return getInt("BowAbilities.QuickArchery.SpeedDuration");
    }

    public int getAmplifier() {
        return getInt("BowAbilities.QuickArchery.SpeedAmplifier");
    }

}
