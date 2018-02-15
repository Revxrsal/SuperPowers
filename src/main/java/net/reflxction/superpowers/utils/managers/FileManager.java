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
