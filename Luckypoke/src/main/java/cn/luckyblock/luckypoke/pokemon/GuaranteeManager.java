package cn.luckyblock.luckypoke.pokemon;

import java.util.HashMap;
import java.util.UUID;

public class GuaranteeManager {

    private static final HashMap<UUID, Integer> COUNT = new HashMap<>();

    public static int add(UUID uuid) {
        int now = COUNT.getOrDefault(uuid, 0) + 1;
        COUNT.put(uuid, now);
        return now;
    }

    public static void reset(UUID uuid) {
        COUNT.remove(uuid);
    }

    public static int get(UUID uuid) {
        return COUNT.getOrDefault(uuid, 0);
    }
}
