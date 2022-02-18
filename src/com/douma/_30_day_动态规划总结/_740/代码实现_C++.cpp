class Solution {
public:
    /*
    选择 nums[i] ，然后删除所有的 nums[i] - 1 和 nums[i] + 1
    这个意思就是，偷了 nums[i] 后，就不能偷 nums[i] 前后相邻的两个数了
    所以，问题可以抽象为打家劫舍问题

    算法步骤为：
        1. 先对 nums 中的每个元素进行个数统计，得到 sum 数组，
            存储在一个数组中，元素的值作为 sum 数组的下标，
            相同元素的累加和作为 sum 数组元素的值
        2. 然后对 sum 数组进行打家劫舍
     */
    int deleteAndEarn(vector<int>& nums) {
        int maxVal = 0;
        for (int val : nums) {
            maxVal = max(maxVal, val);
        }
        vector<int> sum(maxVal + 1);
        for (int val : nums) {
            sum[val] += val;
        }
        return rob(sum);
    }

    // 2. 动态规划
    int rob(vector<int>& nums) {
        int n = nums.size();
        if (n == 1) return nums[0];

        vector<int> dp(n, -1);

        dp[0] = nums[0];
        dp[1] = max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[n - 1];
    }
};