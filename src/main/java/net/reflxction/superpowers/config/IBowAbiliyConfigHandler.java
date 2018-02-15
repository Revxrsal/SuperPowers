package net.reflxction.superpowers.config;

import net.reflxction.superpowers.core.BowAbility;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Reflxction, on 02/15/18.
 */
public interface IBowAbiliyConfigHandler     extends Serializable {

    default String getString(String path) {
        return SuperPowers.getPlugin().getBowAbilitiesConfig().getString(path);
    }

    default int getInt(String path) {
        return SuperPowers.getPlugin().getBowAbilitiesConfig().getInt(path);
    }

    default boolean getBoolean(String path) {
        return SuperPowers.getPlugin().getBowAbilitiesConfig().getBoolean(path);
    }

    default List<String> getList(String path) {
        List<String> list = SuperPowers.getPlugin().getBowAbilitiesConfig().getStringList(path);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, ChatColor.translateAlternateColorCodes('&', list.get(i)));
        }
        return list;
    }

    default Material getMaterial(String path) {
        return Material.getMaterial(SuperPowers.getPlugin().getBowAbilitiesConfig().getString(path).toUpperCase());
    }

    BowAbility getAbility();

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


    default String format(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
