package com.douma._25_day_贪心算法二._134;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _134_gas_station {
    /* 134. 加油站
    在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。

    你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
    你从其中的一个加油站出发，开始时油箱为空。

    如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。

    说明: 
    如果题目有解，该答案即为唯一答案。
    输入数组均为非空数组，且长度相同。
    输入数组中的元素均为非负数。

    示例 1:
    输入:
    gas  = [1,2,3,4,5]
    cost = [3,4,5,1,2]
    输出: 3
    解释:
    从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
    开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
    开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
    开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
    开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
    开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
    因此，3 可为起始索引。

    示例 2:
    输入:
    gas  = [2,3,4]
    cost = [3,4,3]
    输出: -1
    解释:
    你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
    我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
    开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
    开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
    你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
    因此，无论怎样，你都不可能绕环路行驶一周。
     */

    /*
    总结：如果 x 到不了 y+1（但能到 y），那么从 x 到 y 的任一点出发都不可能到达 y+1。
        因为从其中任一点出发的话，相当于从 0 开始加油，
        而如果从 x 出发到该点则不一定是从 0 开始加油，可能还有剩余的油。
        既然不从 0 开始都到不了y+1，那么从 0 开始就更不可能到达 y+1 了
     */

    // O(n^2)
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            if (gas[i] < cost[i]) continue;
            int index = i;
            int remainGas = gas[i] - cost[i];
            while (remainGas >= 0) {
                index = (index + 1) % gas.length;
                if (index == i) return i;
                remainGas = remainGas - cost[index] + gas[index];
            }
        }
        return -1;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;

        int totalGas = 0; // 总油量
        int currGas = 0; // 当前总油量
        int startStation = 0;
        for (int i = 0; i < n; i++) {
            totalGas += gas[i] - cost[i];
            currGas += gas[i] - cost[i];
            if (currGas < 0) {
                startStation = i + 1;
                currGas = 0;
            }
        }

        return totalGas >= 0 ? startStation : -1;
    }
}
