package cn.luckyblock.luckypoke.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class WeightRandom<T> {

    private final Map<T, Integer> weightMap = new LinkedHashMap<>();
    private int totalWeight = 0;
    private final Random random = new Random();

    public void add(T obj, int weight) {
        if (weight <= 0) return;
        weightMap.put(obj, weight);
        totalWeight += weight;
    }

    public T random() {
        if (totalWeight <= 0) return null;
        int r = random.nextInt(totalWeight);
        int cur = 0;
        for (Map.Entry<T, Integer> e : weightMap.entrySet()) {
            cur += e.getValue();
            if (r < cur) return e.getKey();
        }
        return null;
    }
}
