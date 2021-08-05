class Solution {
public:
    int longestArithSeqLength(vector<int>& nums) {
        int n = nums.size();

        int ans = 2;
        // dp[i][j]：表示以 arr[i] 为结尾且公差为 j 的最长等差数列的长度
        vector<vector<int>> dp(n, vector<int>(20010, 1));

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int diff = nums[i] - nums[j];
                // +10000 的目的：保证公差 diff 为正数
                diff += 10000;
                dp[i][diff] = max(dp[i][diff], dp[j][diff] + 1);
                ans = max(dp[i][diff], ans);
            }
        }
        return ans;
    }
};