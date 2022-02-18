class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        int size = nums.size();
        if (size == 0) return 0;

        // 状态：dp[i] 表示以 i 对应元素结尾的时候最长递增子序列的长度
        vector<int> dp(size, 1);

        int res = 1;
        for (int j = 1; j < size; j++) {
            for (int i = 0; i < j; i++) {
                if (nums[j] > nums[i]) {
                    dp[j] = max(dp[i] + 1, dp[j]);
                    res = max(res, dp[j]);
                }
            }
        }

        return res;
    }
};