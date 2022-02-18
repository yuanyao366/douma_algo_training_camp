class Solution {
public:
    // 完全背包问题：
    //      在 nums 列表中可重复的选择数字组合，使得组合之和等于 target
    // 请注意，顺序不同的序列被视作不同的组合。
    int combinationSum4(vector<int>& nums, int target) {
        // dp[i]：表示从 nums 中找到总和等于 i 的元素组合的个数
        vector<int> dp(target + 1);

        dp[0] = 1;

        // 为了不会排除数字相同，但是顺序不同的组合
        // 这里我们针对每一种和来选择数字
        for (int c = 1; c <= target; c++) {
            for (int num : nums) {
                // 要保证不超过 INT 范围
                if (num <= c && dp[c - num] < INT_MAX - dp[c]) {
                    dp[c] += dp[c - num];
                }
            }
        }

        return dp[target];
    }
};