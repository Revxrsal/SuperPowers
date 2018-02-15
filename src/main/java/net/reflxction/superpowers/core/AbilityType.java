package net.reflxction.superpowers.core;

/**
 * Created by Reflxction, on 01/31/18.
 */
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
        return SuperPowers.getPlugin().getConfig().getBoolean("Abilities." + abilityType.getName() + ".Enabled");
    }
}
