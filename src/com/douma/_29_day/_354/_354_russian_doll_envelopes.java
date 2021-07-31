package com.douma._29_day._354;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _354_russian_doll_envelopes {
    /* 354. 俄罗斯套娃信封问题
    给你一个二维整数数组 envelopes ，
    其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。

    当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，
    如同俄罗斯套娃一样。

    请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。

    注意：不允许旋转信封。

    示例 1：
    输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
    输出：3
    解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。

    示例 2：
    输入：envelopes = [[1,1],[1,1],[1,1]]
    输出：1

    提示：
    1 <= envelopes.length <= 5000
    envelopes[i].length == 2
    1 <= wi, hi <= 10^4

     */

    // 题意：找出二维数组的一个排列，使得其中有最长的单调递增子序列（两个维度都递增）
    // 就是 300 号算法题的变形体

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length <= 1) return envelopes.length;

        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    // bug 修复：按照高度降序排列
                    return o2[1] - o1[1];
                }
            }
        });

        int maxLen = 1;

        // 状态：dp[i] 表示以 i 对应元素结尾的时候最长递增子序列的长度
        int[] dp = new int[envelopes.length];

        // 状态初始化：单个元素最少有一个递增子序列元素
        Arrays.fill(dp, 1);

        for (int j = 1; j < envelopes.length; j++) {
            for (int i = 0; i < j; i++) {
                if (envelopes[j][1] > envelopes[i][1]) {
                    dp[j] = Math.max(dp[i] + 1, dp[j]);
                    maxLen = Math.max(maxLen, dp[j]);
                }
            }
        }

        return maxLen;
    }
}
