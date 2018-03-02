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

import net.reflxction.superpowers.api.ConfigAccess;
import net.reflxction.superpowers.config.abilities.QuickArcheryConfig;
import net.reflxction.superpowers.config.bow_abilities.*;
import net.reflxction.superpowers.core.BowAbility;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.managers.BowAbilityManager;
import net.reflxction.superpowers.utils.managers.ConfigVariables;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.io.IOException;

public class BowGUIListener implements Listener, ConfigAccess {

    private SuperPowers s;

    private final ConfigVariables c = new ConfigVariables(s);

    public BowGUIListener(SuperPowers s) {
        this.s = s;
    }

    private BowAbilityManager m = new BowAbilityManager(s);

    private void cancel(InventoryClickEvent e) {
        e.setCancelled(true);
        e.getWhoClicked().closeInventory();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) throws IOException {
        final Inventory i = event.getInventory();
        final int slot = event.getRawSlot();
        final String title = ChatColor.stripColor(i.getTitle());
        final Player p = (Player) event.getWhoClicked();

        ExplosiveArrowsConfig exc = new ExplosiveArrowsConfig(s);
        FlamingArrowsConfig fc = new FlamingArrowsConfig(s);
        MonsterArrowsConfig mc = new MonsterArrowsConfig(s);
        WolfArrowsConfig wc = new WolfArrowsConfig(s);
        InfiniteArrowsConfig ic = new InfiniteArrowsConfig(s);
        QuickArcheryConfig qc = new QuickArcheryConfig(s);

        if (title.equalsIgnoreCase(ChatColor.stripColor(format(c.getBAbilitiesGuiName())))) {
            if (slot == exc.getSlot()) {
                m.setAbility(p, BowAbility.EXPLOSIVE_ARROWS);
                p.closeInventory();
                p.sendMessage(format("&eYour bow ability has been set to &cExplosive Arrows"));
                cancel(event);
            }
            if (slot == fc.getSlot()) {
                m.setAbility(p, BowAbility.FLAMING_ARROWS);
                p.closeInventory();
                p.sendMessage(format("&eYour bow ability has been set to &cFlaming Arrows"));
                cancel(event);
            }
            if (slot == mc.getSlot()) {
                m.setAbility(p, BowAbility.MONSTER_ARROWS);
                p.closeInventory();
                p.sendMessage(format("&eYour bow ability has been set to &cMonster Arrows"));
                cancel(event);
            }
            if (slot == wc.getSlot()) {
                m.setAbility(p, BowAbility.WOLF_ARROWS);
                p.closeInventory();
                p.sendMessage(format("&eYour bow ability has been set to &cWolf Arrows"));
                cancel(event);
            }
            if(slot == ic.getSlot()) {
                m.setAbility(p, BowAbility.INFINITE_ARROWS);
                p.closeInventory();
                p.sendMessage(format("&eYour bow ability has been set to &cInfinite Arrows"));
                cancel(event);
            }
            if(slot == qc.getSlot()) {
                m.setAbility(p, BowAbility.QUICK_ARCHERY);
                p.closeInventory();
                p.sendMessage(format("&eYour bow ability has been set to &cQuick Archery"));
                cancel(event);
            }
        }
    }

    private String format(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    @Override
    public SuperPowers getPlugin() {
        return s;
    }
}