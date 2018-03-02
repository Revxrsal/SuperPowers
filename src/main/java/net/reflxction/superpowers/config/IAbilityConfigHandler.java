/*
 * * Copyright 2017-2018 github.com/ReflxctionDev
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

package net.reflxction.superpowers.config;

import net.reflxction.superpowers.core.AbilityType;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface IAbilityConfigHandler {

    AbilityType getAbility();

    SuperPowers getPlugin();
    
    default String getString(String path) {
        return getPlugin().getAbilitiesConfig().getString(path);
    }

    default int getInt(String path) {
        return getPlugin().getAbilitiesConfig().getInt(path);
    }

    default boolean getBoolean(String path) {
        return getPlugin().getAbilitiesConfig().getBoolean(path);
    }

    default List<String> getList(String path) {
        List<String> list = getPlugin().getAbilitiesConfig().getStringList(path);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, ChatColor.translateAlternateColorCodes('&', list.get(i)));
        }
        return list;
    }

    default Material getMaterial(String path) {
        return Material.getMaterial(getPlugin().getAbilitiesConfig().getString(path).toUpperCase());
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
        List<String> lore = getLore();
        lore.add(ChatColor.AQUA.toString());
        lore.add((AbilityType.isEnabled(getAbility())) ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled");
        ItemStack i = new ItemBuilder(new ItemStack(getMaterial()))
                .setName(format(getName()))
                .setLore(lore)
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
