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
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private SuperPowers m;

    public FileManager(SuperPowers bot) {
        m = bot;
    }

    public void createFile(String fileName) {
        File file = new File(m.getDataFolder(), fileName + ".yml");
        if (!file.exists()) {
            m.saveResource(fileName + ".yml", false);
        }
    }

    public void saveFile(FileConfiguration fileConfig, String fileName) {
        try {
            File file = new File(m.getDataFolder(), fileName + ".yml");
            fileConfig.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {

        }
    }

    public FileConfiguration loadFile(String fileName) {
        File file = new File(m.getDataFolder(), fileName + ".yml");
        FileConfiguration fileConfig = new YamlConfiguration();
        try {
            fileConfig.load(file);
            return fileConfig;
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
