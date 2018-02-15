package net.reflxction.superpowers.utils;

import net.reflxction.superpowers.core.AbilityType;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.managers.AbilityManager;
import net.reflxction.superpowers.utils.managers.ConfigVariables;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Reflxction, on 02/01/18.
 */
public class CheckUtils {

    private static ConfigVariables c = new ConfigVariables(SuperPowers.getPlugin());

    public static boolean isInDisabledWorlds(Player p, AbilityType abilityType) throws NullPointerException {
        for (int i = 0; i < c.getList("Abilities." + abilityType.getName() + ".DisabledWorlds").size(); i++) {
            final List<String> disabledWorlds = SuperPowers.getPlugin().getConfig().getStringList("Abilities." + abilityType.getName() + ".DisabledWorlds");
            if (!p.getWorld().getName().equalsIgnoreCase(disabledWorlds.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }

    public static boolean canUseAbility(Player p, AbilityType a) {
        final AbilityManager au = new AbilityManager(SuperPowers.getPlugin());
        if (au.getAbility(p) == a) {
            if (p.hasPermission(a.getPermission())) {
                if (AbilityType.isEnabled(a)) {
                    if (!isInDisabledWorlds(p, a)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}