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

package net.reflxction.superpowers.utils.managers;

import net.reflxction.superpowers.core.SuperPowers;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageManager {

    private SuperPowers m = SuperPowers.getPlugin(SuperPowers.class);

    private final StringManager su = new StringManager();

    private final AbilityManager au = new AbilityManager(m);

    public MessageManager(SuperPowers m) {
        this.m = (m == null) ? SuperPowers.getPlugin(SuperPowers.class) : m;
    }

    private String format(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    private String getMessage(String path) {
        return format(m.getMessagesConfig().getString("Messages." + path));
    }

    private boolean getBool(String path) {
        return m.getMessagesConfig().getBoolean(path);
    }

    public String getNotPlayerMessage() {
        return getMessage("NotPlayer");
    }

    public String getNoPermissionCmdMessage() {
        return getMessage("NoPermissionCommand");
    }

    public String getNoPermissionAbilityMessage() {
        return getMessage("NoPermissionAbility");
    }

    public void sendMessage(Player p, String message) {
        p.sendMessage(format(message));
    }


}
