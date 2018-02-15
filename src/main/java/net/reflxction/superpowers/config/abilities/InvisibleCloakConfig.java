package net.reflxction.superpowers.config.abilities;

import net.reflxction.superpowers.config.IAbilityConfigHandler;
import net.reflxction.superpowers.core.AbilityType;

/**
 * Created by Reflxction, on 02/15/18.
 */
public class InvisibleCloakConfig implements IAbilityConfigHandler {
    @Override
    public AbilityType getAbility() {
        return AbilityType.INVISIBLE_CLOAK;
    }
}
