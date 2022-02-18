package com.douma._24_day_贪心算法一._1578;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _1578_MinCost {
    // 代码优化
    // 贪心策略：碰到相同字母的时候，我们贪心的删除删除成本最小的字符，也可以说保留删除成本最大的字母
    public int minCost(String s, int[] cost) {
        int res = 0;
        int len = s.length();
        int i = 0;
        while (i < len) {
            char c = s.charAt(i);
            int maxCost = 0;
            int sumCost = 0;
            while (i < len && s.charAt(i) == c) {
                maxCost = Math.max(maxCost, cost[i]);
                sumCost = sumCost + cost[i];
                i++;
            }
            res += (sumCost - maxCost);
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "aaabaa";
        int[] cost = {1, 3, 2, 4, 1, 5};
        System.out.println(new _1578_MinCost().minCost(s, cost));
    }
}
