package cn.luckyblock.luckypoke.util;

import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.List;

public class HeadUtil {

    public static ItemStack createLuckyBlock() {
        try {
            // 使用萤石方块作为幸运方块
            ItemStack block = new ItemStack(Material.GLOWSTONE);
            ItemMeta meta = block.getItemMeta();
            meta.setDisplayName("§6§l幸运方块");
            List<String> lore = new ArrayList<>();
            lore.add("§7右键点击放置");
            lore.add("§7破坏后获得惊喜");
            meta.setLore(lore);
            block.setItemMeta(meta);
            return block;
        } catch (Exception e) {
            // 如果出错，使用简单的萤石方块
            ItemStack block = new ItemStack(Material.GLOWSTONE);
            ItemMeta meta = block.getItemMeta();
            meta.setDisplayName("§6§l幸运方块");
            block.setItemMeta(meta);
            return block;
        }
    }

    public static boolean isLuckyBlock(ItemStack item) {
        return item != null && item.hasItemMeta()
                && "§6§l幸运方块".equals(item.getItemMeta().getDisplayName());
    }
}
