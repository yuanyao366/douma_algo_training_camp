package com.douma._20_day_数据结构设计._381;

import java.util.*;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _381_insert_delete_getrandom_o1_duplicates_allowed {
    /* 381. O(1) 时间插入、删除和获取随机元素 - 允许重复
    设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。

    注意: 允许出现重复元素。

    insert(val)：向集合中插入元素 val。
    remove(val)：当 val 存在时，从集合中移除一个 val。
    getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
    示例:

    // 初始化一个空的集合。
    RandomizedCollection collection = new RandomizedCollection();

    // 向集合中插入 1 。返回 true 表示集合不包含 1 。
    collection.insert(1);

    // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
    collection.insert(1);

    // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
    collection.insert(2);

    // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
    collection.getRandom();

    // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
    collection.remove(1);

    // getRandom 应有相同概率返回 1 和 2 。
    collection.getRandom();
     */
}

class RandomizedCollection {

    private Map<Integer, Set<Integer>> idxMap;
    private List<Integer> nums;
    private Random rand = new Random();

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        this.nums = new ArrayList<>();
        this.idxMap = new HashMap<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        Set<Integer> indexes = idxMap.getOrDefault(val, new HashSet<>());
        indexes.add(nums.size());
        idxMap.put(val, indexes);
        nums.add(val);
        return indexes.size() == 1;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!idxMap.containsKey(val)) return false;

        // 拿到需要删除的元素在列表中的索引位置
        Iterator<Integer> indexes = idxMap.get(val).iterator();
        int index = indexes.next();

        // 将列表中的最后一个元素覆盖掉需要删除的元素
        int lastNum = nums.get(nums.size() - 1);
        nums.set(index, lastNum);

        // 维护删除的元素和最后一个元素的索引
        idxMap.get(val).remove(index);
        idxMap.get(lastNum).remove(nums.size() - 1);
        if (index < nums.size() - 1) {
            idxMap.get(lastNum).add(index);
        }

        // 删除数组列表中的最后一个元素
        nums.remove(nums.size() - 1);
        // 从 map 中删除指定的元素
        if (idxMap.get(val).size() == 0) idxMap.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}

