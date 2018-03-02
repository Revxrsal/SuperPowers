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

import net.reflxction.superpowers.config.abilities.*;
import net.reflxction.superpowers.core.AbilityType;
import net.reflxction.superpowers.core.SuperPowers;
import net.reflxction.superpowers.utils.managers.AbilityManager;
import net.reflxction.superpowers.utils.managers.ConfigVariables;
import net.reflxction.superpowers.utils.managers.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.io.IOException;

public class AbilityGUIListener implements Listener {

    private SuperPowers m;

    public AbilityGUIListener(SuperPowers m) {
        this.m = (m == null) ? SuperPowers.getPlugin(SuperPowers.class) : m;
    }

    private final ConfigVariables c = new ConfigVariables(m);

    private final AbilityManager au = new AbilityManager(m);


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
        final MessageManager mm = new MessageManager(m);
        // Checking if the inventory is the one specified in the config
        if (title.equalsIgnoreCase(ChatColor.stripColor(format(c.getAbilitiesGuiName())))) {
            final int invis = new InvisibleCloakConfig(m).getSlot();
            final int bomber = new BomberConfig(m).getSlot();
            final int fpunch = new FirePunchConfig(m).getSlot();
            final int thor = new ThorConfig(m).getSlot();
            final int vampire = new VampireConfig(m).getSlot();
            final int ifist = new IronFistConfig(m).getSlot();
            if (slot == invis) {
                mm.sendMessage(p, "&eYour ability has been set to &cInvisible Cloak");
                au.setAbility(p, AbilityType.INVISIBLE_CLOAK);
                cancel(event);
            }
            if (slot == bomber) {
                mm.sendMessage(p, "&eYour ability has been set to &cBomber");
                au.setAbility(p, AbilityType.BOMBER);
                cancel(event);
            }
            if (slot == fpunch) {
                mm.sendMessage(p, "&eYour ability has been set to &cFire Punch");
                au.setAbility(p, AbilityType.FIRE_PUNCH);
                cancel(event);
            }
            if (slot == thor) {
                mm.sendMessage(p, "&eYour ability has been set to &cThor");
                au.setAbility(p, AbilityType.THOR);
                cancel(event);
            }
            if (slot == vampire) {
                mm.sendMessage(p, "&eYour ability has been set to &cVampire");
                au.setAbility(p, AbilityType.VAMPIRE);
                cancel(event);
            }
            if (slot == ifist) {
                mm.sendMessage(p, "&eYour ability has been set to &cIron Fist");
                au.setAbility(p, AbilityType.IRON_FIST);
                cancel(event);
            }

        }
    }

    private String format(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}

