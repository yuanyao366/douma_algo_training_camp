package com.douma.笔试代码.tengxun._20210826;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class _2_RecoverNumber {

    // 时间复杂度：O(nk)
    // 空间复杂度：O(nk)
    public int recoverNum(int[] nums, int k) {
        int n = nums.length;
        int mod = (int)10e9 + 7;

        // 1. 使用动态规划，求连续 i 个 0 上，填充 k 个数字的方案数
        // dp[i][j] 表示在连续 i 个 0 上，填充 k 个数字的方案数
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            // 连续 i 个 0 上，填充 1 个数字的方案数就是 1 种
            dp[i][1] = 1;
        }
        for (int i = 1; i <= k; i++) {
            // 连续 1 个 0 上，填充 i 个数字的方案数就是 i 种
            dp[1][i] = i;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                // 连续 i 个 0 上，填充 j 个数字的方案数等于：
                // 连续 i 个 0 上，填充 j - 1 个数字的方案数 + 连续 i - 1 个 0 上，填充 j 个数字的方案数
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % mod;
            }
        }

        // 2. 利用上面的 dp 状态值求解恢复数组的方案数
        int ans = 0;
        int prevMinNum = 1, maxNum = k;
        int i = 0;
        while (i < n) {
            // 计算连续的 0 的个数
            int zeroCnt = 0;
            while (i < n && nums[i] == 0) {
                zeroCnt++;
                i++;
            }

            // 计算可以在连续 0 上填充的数字的个数
            int numCnt = 0;
            if (i < n) {
                numCnt = nums[i] - prevMinNum + 1;
                prevMinNum = nums[i];
            } else {
                numCnt = maxNum - prevMinNum + 1;
            }

            // 累加方案数
            ans += dp[zeroCnt][numCnt] % mod;
            i++;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 2, 0, 0, 0, 6};
        System.out.println(new _2_RecoverNumber().recoverNum(nums, 10));
    }
}
