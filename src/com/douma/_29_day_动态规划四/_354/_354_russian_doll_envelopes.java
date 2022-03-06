package com.douma._29_day_动态规划四._354;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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


    /*
    俄罗斯套娃这道题的输入改掉了，之前的输入数据规模是 5000 ，所以时间复杂度为 O(n^2) 的动态规划方案是可以的

    现在的输入数据规模改为 10^5 ，所以，时间复杂度为 O(n^2) 会超时了
    现在题目的输入数据规模为：
    1 <= envelopes.length <= 10^5
    envelopes[i].length == 2
    1 <= wi, hi <= 10^5
     */
    // 所以需要使用二分解法
    // 时间复杂度 O(nlogn)
    // 空间复杂度 O(n)
    public int maxEnvelopes2(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }

        int n = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] e1, int[] e2) {
                if (e1[0] != e2[0]) {
                    // 先按照宽度升序排列
                    return e1[0] - e2[0];
                } else {
                    // 宽度相同的话，按照高度降序排列
                    return e2[1] - e1[1];
                }
            }
        });

        // res 用于存储所有可套娃信封的高度值
        // 保证 res 中的元素肯定是升序排列的
        List<Integer> res = new ArrayList<>();

        // 1. 将宽度最小的信封的高度值放在 res 中
        res.add(envelopes[0][1]);

        // 2. 从第二个信封开始，遍历并处理每一个信封
        for (int i = 1; i < n; ++i) {
            int currHeight = envelopes[i][1];
            // 2.1 如果当前信封的高度大于 res 中最后一个信封的高度
            if (currHeight > res.get(res.size() - 1)) {
                // 那么，可以将当前的信封放入到 res 中
                // (当前信封的宽度肯定大于等于果集中最后一个信封的宽度，因为是按照宽度升序排列的)
                res.add(currHeight);
            } else { // 2.2 当前信封的高度小于等于 res 中最后一个信封的高度
                // 将当前信封插入到 res 合适的位置上
                // 先使用二分查找，在 res 中查找 currHeight 合适的位置
                int index = binarySearch(res, currHeight);
                // 将当前信封替换之前这个位置上的信封
                // 之所以可以将当前信封替换掉第 index 处的信封，是因为：
                // ① 当前信封的宽度大于等于第 index - 1 处的信封的宽度
                // ② 当前信封的高度大于第 index - 1 处的信封的高度
                res.set(index, currHeight);
            }
        }

        return res.size();
    }

    // 二分查找：查找第一个大于等于 target 的元素的索引
    public int binarySearch(List<Integer> res, int target) {
        int low = 0, high = res.size() - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (res.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

}
