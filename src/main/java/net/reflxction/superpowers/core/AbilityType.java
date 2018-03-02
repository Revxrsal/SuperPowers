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

public enum AbilityType {

    FIRE_PUNCH("FirePunch", "superpowers.abilities.fire_punch"),

    INVISIBLE_CLOAK("InvisibilityCloak", "superpowers.abilities.invisibility_cloak"),

    BOMBER("Bomber", "superpowers.abilities.bomber"),

    NONE("None", "superpowers.abilities.none"),

    THOR("Thor", "superpowers.abilities.thor"),

    VAMPIRE("Vampire", "superpowers.abilities.vampire"),

    IRON_FIST("IronFist", "superpowers.abilities.iron_fist");


    private String name;

    private String permission;

    AbilityType(String abilityName, String permission) {
        this.name = abilityName;
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public String getPermission() {
        return permission;
    }

    public static AbilityType getAbilityByName(String name) {
        for (AbilityType a : AbilityType.values()) {
            if (a.getName().equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }


    public static boolean isEnabled(AbilityType abilityType) {
        return SuperPowers.getPlugin(SuperPowers.class).getAbilitiesConfig().getBoolean("Abilities." + abilityType.getName() + ".Enabled");
    }
}
