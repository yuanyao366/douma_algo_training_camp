class Solution {
public:
    int rob(vector<int>& nums) {
        int n = nums.size();
        if (n == 1) return nums[0];

        int notRobFirstHouse = rob(nums, 1, n -1);
        int notRobLastHouse = rob(nums, 0, n - 2);
        return max(notRobFirstHouse, notRobLastHouse);
    }

    // 偷区间 [start, end] 得到最大的金额
    int rob(vector<int>& nums, int start, int end) {
        int n = nums.size();
        if (n == 1) return nums[0];

        int preMax = 0;
        int currMax = 0;

        for (int i = start; i <= end; i++) {
            int tmpMax = max(currMax, preMax + nums[i]);
            preMax = currMax;
            currMax = tmpMax;
        }

        return currMax;
    }
};