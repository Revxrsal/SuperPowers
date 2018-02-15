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

package net.reflxction.superpowers.core;

import net.reflxction.superpowers.abilities.*;
import net.reflxction.superpowers.gui.AbilityInventoryListener;
import net.reflxction.superpowers.utils.managers.FileManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class SuperPowers extends JavaPlugin {

    final FileManager fileManager = new FileManager(this);

    private FileConfiguration playerData = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "playerdata.yml"));

    private FileConfiguration messages = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "messages.yml"));

    private FileConfiguration abilitiesConfig = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "abilities.yml"));

    private FileConfiguration bowAbilitiesConfig = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "bow-abilities.yml"));

    @Override
    public void onEnable() {
        loadConfig();
        registerEvents();
        getCommand("ability").setExecutor(new AbilityCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadConfig() {
        fileManager.createFile("playerdata");
        fileManager.loadFile("playerdata");
        fileManager.createFile("config");
        fileManager.loadFile("config");
        fileManager.createFile("messages");
        fileManager.loadFile("messages");
        fileManager.createFile("abilities");
        fileManager.loadFile("abilities");
        fileManager.createFile("bow-abilities");
        fileManager.loadFile("bow-abilities");
    }

    private void registerEvents() {
        final PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new InvisibleCloak(this), this);
        pm.registerEvents(new AbilityInventoryListener(this), this);
        pm.registerEvents(new FirePunch(this), this);
        pm.registerEvents(new Bomber(), this);
        pm.registerEvents(new Thor(), this);
        pm.registerEvents(new Vampire(), this);
        pm.registerEvents(new IronFist(), this);
    }

    public FileConfiguration getPlayerDataConfig() {
        return playerData;
    }

    public static SuperPowers getPlugin() {
        return SuperPowers.getPlugin(SuperPowers.class);
    }

    public FileConfiguration getMessagesConfig() {
        return messages;
    }

    public FileConfiguration getAbilitiesConfig() {
        return abilitiesConfig;
    }

    public FileConfiguration getBowAbilitiesConfig() {
        return bowAbilitiesConfig;
    }
}
