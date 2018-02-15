package net.reflxction.superpowers.core;

import org.bukkit.entity.EntityType;

/**
 * Created by Reflxction, on 01/31/18.
 */
public enum BowAbility {

    EXPLOSIVE_ARROWS("ExplosiveArrows"),
    FLAMING_ARROWS("FlamingArrows"),
    MONSTER_ARROWS("MonsterArrows"),
    WOLF_ARROWS("WolfArrows"),
    INFINITE_ARROWS("InfiniteArrows"),
    QUICK_ARCHERY("QuickArchery"),
    DOUBLED_ARROWS("DoubledArrows"),
    NONE("None");

    private String name;

    BowAbility(String abilityName) {
        this.name = abilityName;
    }

    public String getName() {
        return name;
    }

    public static BowAbility getAbilityByName(String name) {
        for (BowAbility b : BowAbility.values()) {
            if (b.getName().equalsIgnoreCase(name)) {
                return b;
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


}
