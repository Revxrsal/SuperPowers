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

package net.reflxction.superpowers.api;

import net.reflxction.superpowers.core.SuperPowers;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

/**
 * A simple functional interface which gives direct access to the plugin's main files
 */
@FunctionalInterface
public interface ConfigAccess {

    /**
     * @return Main class instance
     */
    SuperPowers getPlugin();

    /**
     * @return Instance of config.yml
     */
    default FileConfiguration getConfig() {
        return getPlugin().getConfig();
    }

    /**
     * @return Instance of playerdata.yml
     */
    default FileConfiguration getPlayerDataFile() {
        return getPlugin().getPlayerDataConfig();
    }

    /**
     * @return Instance of abilities.yml
     */
    default FileConfiguration getAbilitiesConfig() {
        return getPlugin().getAbilitiesConfig();
    }

    /**
     * @return Instance of bowabilities.yml
     */
    default FileConfiguration getBowAbilitiesConfig() {
        return getPlugin().getBowAbilitiesConfig();
    }

    /**
     * @return Instance of the data folder
     */
    default File getDataFolder() {
        return getPlugin().getDataFolder();
    }

}
