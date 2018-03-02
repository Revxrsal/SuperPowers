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

import org.bukkit.entity.EntityType;

import java.util.Optional;

public enum BowAbility {

    EXPLOSIVE_ARROWS("ExplosiveArrows", "superpowers.bow_abilities.explosive_arrows"),
    FLAMING_ARROWS("FlamingArrows", "superpowers.bow_abilities.flaming_arrows"),
    MONSTER_ARROWS("MonsterArrows", "superpowers.bow_abilities.monster_arrows"),
    WOLF_ARROWS("WolfArrows", "superpowers.bow_abilities.wolf_arrows"),
    INFINITE_ARROWS("InfiniteArrows", "superpowers.bow_abilities.infinite_arrows"),
    QUICK_ARCHERY("QuickArchery", "superpowers.bow_abilities.quick_archery"),
    DOUBLED_ARROWS("DoubledArrows", "superpowers.bow_abilities.doubled_arrows"),
    NONE("None", null);

    private String name;

    private String permission;

    BowAbility(String abilityName, String permission) {
        this.name = abilityName;
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public String getPermission() {
        return permission;
    }

    public static Optional<BowAbility> getAbilityByName(String name) {
        for (BowAbility b : BowAbility.values()) {
            if (b.getName().equalsIgnoreCase(name)) {
                return Optional.of(b);
            }
        }
        return null;
    }

    public static EntityType getEntityByName(String name) {
        for (EntityType type : EntityType.values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }

    public static boolean isEnabled(BowAbility a) {
        return SuperPowers.getPlugin(SuperPowers.class).getBowAbilitiesConfig().getBoolean("BowAbilities." + a.getName() + ".Enabled");
    }

}
