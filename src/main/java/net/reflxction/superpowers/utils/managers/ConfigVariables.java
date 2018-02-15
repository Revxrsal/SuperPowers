package net.reflxction.superpowers.utils.managers;

import net.reflxction.superpowers.core.SuperPowers;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.List;

/**
 * Created by Reflxction, on 01/31/18.
 */
public class ConfigVariables {

    private SuperPowers m = SuperPowers.getPlugin(SuperPowers.class);

    private final String i = "InvisibilityCloak";

    public ConfigVariables(SuperPowers m) {
        //this.m = m;
    }

    /**
     * @param path Path to the variable
     * @return String value of the entered path
     */
    private String getString(String path) {
        return m.getConfig().getString(path);
    }

    /**
     * @param path Path to the variable
     * @return Boolean value of the entered path
     */
    private boolean getBool(String path) {
        return m.getConfig().getBoolean(path);
    }

    /**
     * @param path Path to the variable
     * @return Integer value of the entered path
     */
    private int getInt(String path) {
        return m.getConfig().getInt(path);
    }

    /**
     * @param path Path to the variable
     * @return List of String objects of the entered path
     */
    public List<String> getList(String path) {
        List<String> list = m.getConfig().getStringList(path);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, ChatColor.translateAlternateColorCodes('&', list.get(i)));
        }
        return list;
    }

    private Material getMaterial(String path) {
        return Material.getMaterial(getString(path).toUpperCase());
    }

    /**
     * @return Duration of the invisibility cloak in ticks.
     */
    public int getInvisibilityDuration() {
        return getInt("Abilities.InvisibilityCloak.Duration");
    }

    /**
     * @return Should the invisibility cloak show particles when invisible
     */
    public boolean showParticles() {
        return getBool("Abilities." + i + ".ShowParticles");
    }

    public String getGuiName() {
        return getString("Abilities.GUI-Name");
    }

    public int getSlotInvisCloak() {
        return getInt("Abilities.InvisibilityCloak.Item.Slot");
    }

    public int getSlotFPunch() {
        return getInt("Abilities.FirePunch.Item.Slot");
    }

    public int getCommandCooldown() {
        return getInt("Command-Delay");
    }

    public int getFPunchChance() {
        return getInt("Abilities.FirePunch.Chance");
    }

    public int getFPunchFireTicks() {
        return getInt("Abilities.FirePunch.FireLastsFor");
    }

    public int getBomberExplosionPower() {
        return getInt("Abilities.Bomber.ExplosionPower");
    }

    public int getThorChance() {
        return getInt("Abilities.Thor.Chance");
    }

    public int getThorSlot() {
        return getInt("Abilities.Thor.Item.Slot");
    }

    public int getVampireChance() {
        return getInt("Abilities.Vampire.Chance");
    }

    public int getVampireDuration() {
        return getInt("Abilities.Vampire.Duration");
    }

    public int getVampireSlot() {
        return getInt("Abilities.Vampire.Item.Slot");
    }

    public int getIronFistChance() {
        return getInt("Abilities.IronFist.Chance");
    }

    public int getIronFistExtraDamage() {
        return getInt("Abilities.IronFist.ExtraDamage");
    }

    public int getIronFistSlot() {
        return getInt("Abilities.IronFist.Item.Slot");
    }
}
