package net.reflxction.superpowers.utils.managers;

import net.reflxction.superpowers.core.AbilityType;
import net.reflxction.superpowers.core.SuperPowers;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

/**
 * Created by Reflxction, on 01/31/18.
 */
public class AbilityManager {

    private SuperPowers m;

    public AbilityManager(SuperPowers m) {
        this.m = (m == null) ? SuperPowers.getPlugin() : m;
    }

    public AbilityType getAbility(Player p) {
        String abilityName = m.getPlayerDataConfig().getString("Players." + p.getName() + ".Ability");
        AbilityType a = AbilityType.getAbilityByName(abilityName) == null ? AbilityType.NONE : AbilityType.getAbilityByName(abilityName);
        return a;
    }

    public void setAbility(Player p, AbilityType abilityType) {
        m.getPlayerDataConfig().set("Players." + p.getName() + ".UUID", p.getUniqueId().toString());
        m.getPlayerDataConfig().set("Players." + p.getName() + ".Name", p.getName());
        m.getPlayerDataConfig().set("Players." + p.getName() + ".Ability", abilityType.getName());
        try {
            m.getPlayerDataConfig().save(new File(m.getDataFolder(), "playerdata.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAbilityCooldown(final Player p, int cooldown) {
    }
/*
    public HashMap<UUID, BukkitRunnable> getCooldownTask() {
        return cooldownTask;
    }

    public HashMap<UUID, Integer> getCooldownMap() {
        return cooldownMap;
    }

    public HashMap<UUID, Integer> getCooldownTime() {
        return cooldownTime;
    }
*/
}
