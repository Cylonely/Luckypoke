package cn.luckyblock.luckypoke;

import cn.luckyblock.luckypoke.command.LuckyBlockCommand;
import cn.luckyblock.luckypoke.block.LuckyBlockListener;
import cn.luckyblock.luckypoke.config.LuckyBlockConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class LuckyCobbleBlock extends JavaPlugin {

    private static LuckyCobbleBlock instance;
    private LuckyBlockConfig config;

    @Override
    public void onEnable() {
        instance = this;
        saveResource("示例方块.yml", false);
        loadConfig();

        getCommand("luckyblock").setExecutor(new LuckyBlockCommand());
        getServer().getPluginManager().registerEvents(new LuckyBlockListener(), this);
        getLogger().info("LuckyCobbleBlock 已加载");
        getLogger().info("作者：3584590949");
    }

    public void loadConfig() {
        this.config = new LuckyBlockConfig(this);
    }

    public LuckyBlockConfig getLuckyConfig() {
        return config;
    }

    public static LuckyCobbleBlock getInstance() {
        return instance;
    }
}
