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

package net.reflxction.superpowers.gui;

import net.reflxction.superpowers.config.abilities.QuickArcheryConfig;
import net.reflxction.superpowers.config.bow_abilities.*;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.managers.ConfigVariables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class BowAbilityGUI {

    private SuperPowers m;

    public BowAbilityGUI(SuperPowers m) {
        this.m = (m == null) ? SuperPowers.getPlugin(SuperPowers.class) : m;
    }

    private ConfigVariables c = new ConfigVariables(m);

    public void openGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, 45, format(c.getBAbilitiesGuiName()));

        ExplosiveArrowsConfig exc = new ExplosiveArrowsConfig(m);
        FlamingArrowsConfig fc = new FlamingArrowsConfig(m);
        MonsterArrowsConfig mc = new MonsterArrowsConfig(m);
        WolfArrowsConfig wc = new WolfArrowsConfig(m);
        InfiniteArrowsConfig ic = new InfiniteArrowsConfig(m);
        QuickArcheryConfig qc = new QuickArcheryConfig(m);

        gui.setItem(exc.getSlot(), exc.getOverallItem());
        gui.setItem(fc.getSlot(), fc.getOverallItem());
        gui.setItem(mc.getSlot(), mc.getOverallItem());
        gui.setItem(wc.getSlot(), wc.getOverallItem());
        gui.setItem(ic.getSlot(), ic.getOverallItem());
        gui.setItem(qc.getSlot(), qc.getOverallItem());
        p.openInventory(gui);
    }

    private String format(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
