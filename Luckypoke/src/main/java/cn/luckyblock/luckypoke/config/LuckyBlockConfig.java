package cn.luckyblock.luckypoke.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class LuckyBlockConfig {

    private final File file;
    private YamlConfiguration config;

    public LuckyBlockConfig(JavaPlugin plugin) {
        this.file = new File(plugin.getDataFolder(), "示例方块.yml");
        reload();
    }

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public YamlConfiguration get() {
        return config;
    }
}
