package dev.club.aquatic.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import dev.club.aquatic.AuthCore;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author Vasty
 * @date 18/12/2024 @ 03:49
 * @url https://github.com/vastydev
 */

public class YML extends YamlConfiguration {
    private File File;

    private String Path;

    private FileConfiguration FileC = null;

    public YML(String Path) {
        this.Path = Path + ".yml";
        this.File = new File(AuthCore.getInstance().getDataFolder(), this.Path);
        SaveDefault();
        reload();
    }

    public void reload() {
        try {
            load(this.File);
            ReloadUFT8();
        } catch (InvalidConfigurationException|IOException invalidConfigurationException) {}
    }

    public void save() {
        try {
            save(this.File);
        } catch (IOException iOException) {}
    }

    public void SaveDefault() {
        try {
            if (!this.File.exists())
                AuthCore.getInstance().saveResource(this.Path, false);
        } catch (Exception exception) {}
    }

    public void ReloadUFT8() {
        if (this.FileC == null)
            this.File = new File(AuthCore.getInstance().getDataFolder(), this.Path);
        this.FileC = (FileConfiguration)YamlConfiguration.loadConfiguration(this.File);
        try {
            Reader defConfigStream = new InputStreamReader(AuthCore.getInstance().getResource(this.Path), "UTF8");
            if (defConfigStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                this.FileC.setDefaults((Configuration)defConfig);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}