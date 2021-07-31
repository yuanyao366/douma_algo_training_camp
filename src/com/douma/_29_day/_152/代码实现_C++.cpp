class Solution {
public:
    // 动态规划
    int maxProduct1(vector<int>& nums) {
        int length = nums.size();
        vector<int> maxP(length);
        vector<int> minP(length);
        maxP[0] = nums[0];
        minP[0] = nums[0];
        int ans = nums[0];
        for (int i = 1; i < length; ++i) {
            maxP[i] = max(maxP[i - 1] * nums[i], max(nums[i], minP[i - 1] * nums[i]));
            minP[i] = min(minP[i - 1] * nums[i], min(nums[i], maxP[i - 1] * nums[i]));
            ans = max(ans, maxP[i]);
        }
        return ans;
    }

    // 动态规划 + 状态压缩
    int maxProduct(vector<int>& nums) {
        int length = nums.size();
        int maxP = nums[0];
        int minP = nums[0];
        int ans = nums[0];
        for (int i = 1; i < length; ++i) {
            int mx = maxP, mn = minP;
            maxP = max(mx * nums[i], max(nums[i], mn * nums[i]));
            minP = min(mn * nums[i], min(nums[i], mx * nums[i]));
            ans = max(ans, maxP);
        }
        return ans;
    }
};