package com.douma._9_day_哈希查找._554;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _554_brick_wall {
    /* leetcode 554 号算法题：砖墙
    你的面前有一堵矩形的、由多行砖块组成的砖墙。
    这些砖块高度相同但是宽度不同。你现在要画一条自顶向下的、穿过最少砖块的垂线。

    砖墙由行的列表表示。 每一行都是一个代表从左至右每块砖的宽度的整数列表。

    如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。
    你需要找出怎样画才能使这条线穿过的砖块数量最少，并且返回穿过的砖块数量。

    你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。
     */

    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> edgeFreq = new HashMap<>();

        int maxFreq = 0;
        for (int row = 0; row < wall.size(); row++) {
            int edgePosition = 0;
            for (int col = 0; col < wall.get(row).size() - 1; col++) {
                int currBrickLength = wall.get(row).get(col);
                edgePosition += currBrickLength;
                edgeFreq.put(edgePosition, edgeFreq.getOrDefault(edgePosition, 0) + 1);
                maxFreq = Math.max(maxFreq, edgeFreq.get(edgePosition));
            }
        }
        return wall.size() - maxFreq;
    }
}
