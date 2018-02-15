package net.reflxction.superpowers.config;

import net.reflxction.superpowers.core.AbilityType;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by Reflxction, on 02/03/18.
 */
public interface IAbilityConfigHandler {

    AbilityType getAbility();

    default String getString(String path) {
        return SuperPowers.getPlugin().getAbilitiesConfig().getString(path);
    }

    default int getInt(String path) {
        return SuperPowers.getPlugin().getAbilitiesConfig().getInt(path);
    }

    default boolean getBoolean(String path) {
        return SuperPowers.getPlugin().getAbilitiesConfig().getBoolean(path);
    }

    default List<String> getList(String path) {
        List<String> list = SuperPowers.getPlugin().getAbilitiesConfig().getStringList(path);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, ChatColor.translateAlternateColorCodes('&', list.get(i)));
        }
        return list;
    }

    default Material getMaterial(String path) {
        return Material.getMaterial(SuperPowers.getPlugin().getAbilitiesConfig().getString(path).toUpperCase());
    }

    default int getCooldown() {
        return getInt("Abilities." + getAbility().getName() + ".Cooldown");
    }

    default String getName() {
        return getString("Abilities." + getAbility().getName() + ".Item.Name");
    }

    default List<String> getLore() {
        return getList("Abilities." + getAbility().getName() + ".Item.Lore");
    }

    default Material getMaterial() {
        return getMaterial("Abilities." + getAbility().getName() + ".Item.Material");
    }

    default boolean glow() {
        return getBoolean("Abilities." + getAbility().getName() + ".Item.Glow");
    }

    default ItemStack getOverallItem() {
        ItemStack i = new ItemBuilder(new ItemStack(getMaterial()))
                .setName(format(getName()))
                .setLore(getLore())
                .addGlowEffect(glow())
                .toItemStack();
        return i;
    }

    default int getSlot() {
        return getInt("Abilities." + getAbility().getName() + ".Item.Slot");
    }

    default String format(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
