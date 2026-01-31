package cn.luckyblock.luckypoke.command;

import cn.luckyblock.luckypoke.LuckyCobbleBlock;
import cn.luckyblock.luckypoke.util.HeadUtil;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class LuckyBlockCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c只有玩家可以使用此命令");
            return true;
        }
        
        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage("§c用法: /luckyblock <give|reload>");
            return true;
        }

        if (args[0].equalsIgnoreCase("give")) {
            try {
                p.getInventory().addItem(HeadUtil.createLuckyBlock());
                p.sendMessage("§a已获得幸运方块");
            } catch (Exception e) {
                p.sendMessage("§c创建幸运方块时出错: " + e.getMessage());
                e.printStackTrace();
            }
        }

        if (args[0].equalsIgnoreCase("reload")) {
            try {
                LuckyCobbleBlock.getInstance().loadConfig();
                p.sendMessage("§eLuckyBlock 配置已热重载");
            } catch (Exception e) {
                p.sendMessage("§c重载配置时出错: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return true;
    }
}
