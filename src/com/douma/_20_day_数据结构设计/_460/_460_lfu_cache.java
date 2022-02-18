package com.douma._20_day_数据结构设计._460;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _460_lfu_cache {
}

class LFUCache {
    // 每个 key 对应的 value 的映射
    private Map<Integer, Integer> cache;
    // 每个 key 使用的次数
    private Map<Integer, Integer> keyToUsedCount;
    // 每个 count 对应的所有的 keys (按照最近使用的顺序组织)
    private Map<Integer, LinkedHashSet<Integer>> usedCountToKeys;

    private int capacity;

    // 记录整个缓存中所有 key 中使用最小的次数
    private int minUsedCount;

    public LFUCache(int capacity) {
        cache = new HashMap<>();
        keyToUsedCount = new HashMap<>();
        usedCountToKeys = new HashMap<>();

        this.capacity = capacity;
        minUsedCount = 0;
    }

    public int get(int key) {
        // 注意：capacity 可能为 0
        if (capacity == 0) return -1;

        Integer value = cache.get(key);
        if (value == null) return -1;

        // 维护这个 key 对应的 count
        int usedCount = keyToUsedCount.get(key);
        // 1. 从这个 key 目前对应的 count 的集合中删除掉这个 key
        usedCountToKeys.get(usedCount).remove(key);
        keyToUsedCount.put(key, usedCount + 1);

        // 2. 更新最小使用的次数
        // 如果当前的 usedCount 等于最小次数，
        // 并且当前的 usedCount 没有的 key，那么将最小次数加 1
        if (usedCount == minUsedCount
                && usedCountToKeys.get(usedCount).size() == 0) {
            minUsedCount++;
        }

        // 3. 将 key 记录到 usedCount + 1 中的集合中
        putUsedCount(key, usedCount + 1);

        return value;
    }

    private void putUsedCount(Integer key, int count) {
        if (!usedCountToKeys.containsKey(count)) {
            usedCountToKeys.put(count, new LinkedHashSet<>());
        }
        usedCountToKeys.get(count).add(key);
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (cache.containsKey(key)) {
            // 更新 key 对应的 value 值
            cache.put(key, value);
            // 更新 key 对应的 count 值
            get(key);
            return;
        }

        if (cache.size() == capacity) {
            // 删除最少使用的 key
            Integer removeKey = usedCountToKeys.get(minUsedCount).iterator().next();
            usedCountToKeys.get(minUsedCount).remove(removeKey);
            cache.remove(removeKey);
            keyToUsedCount.remove(removeKey);
        }

        // 新增一个缓存中不存在的 key
        cache.put(key, value);
        keyToUsedCount.put(key, 1);

        // 将 key 记录到 minUsedCount 中的集合中
        minUsedCount = 1;
        putUsedCount(key, minUsedCount);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
