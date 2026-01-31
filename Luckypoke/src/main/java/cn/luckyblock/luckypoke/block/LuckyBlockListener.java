package cn.luckyblock.luckypoke.block;

import cn.luckyblock.luckypoke.pokemon.PokemonSpawner;
import cn.luckyblock.luckypoke.util.HeadUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.*;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class LuckyBlockListener implements Listener {

    private static final String LUCKY_BLOCK_META = "lucky_block";

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        // 检查玩家放置的物品是否是幸运方块
        ItemStack item = e.getItemInHand();
        if (HeadUtil.isLuckyBlock(item)) {
            // 给方块添加元数据标记
            Block block = e.getBlock();
            block.setMetadata(LUCKY_BLOCK_META, new org.bukkit.metadata.FixedMetadataValue(
                    cn.luckyblock.luckypoke.LuckyCobbleBlock.getInstance(), true
            ));
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        // 检查方块是否有幸运方块元数据
        if (block.hasMetadata(LUCKY_BLOCK_META)) {
            // 阻止方块掉落
            e.setDropItems(false);
            // 生成随机宝可梦
            PokemonSpawner.spawnRandom(e.getPlayer(), block.getLocation());
            // 移除元数据
            block.removeMetadata(LUCKY_BLOCK_META, cn.luckyblock.luckypoke.LuckyCobbleBlock.getInstance());
        }
    }
}
