class Solution {
public:
    int findNumberOfLIS(vector<int>& nums) {
        int n = nums.size();
        if (n <= 1) return n;
        // 1. 状态(双状态)数组的定义
        // lengths[i] 表示在子区间 [0...i] 且以 nums[i] 结尾的最长递增子序列的长度
        // 每个元素都可以以自身为一个长度的子序列，所以 lengths 初始化为 1
        vector<int> lengths(n, 1);
        // counts[i] 表示在子区间 [0...i] 且以 nums[i] 结尾的最长递增子序列的个数
        // 因为每个元素可以以自身结尾的最长子序列的情况至少有一种，所以 counts 初始化为 1
        vector<int> counts(n, 1);

        // 3. 状态转移
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 需要符合递增条件
                if (nums[j] < nums[i]) {
                    // 以 nums[j] 结尾的最长递增序列长度 >= 以 nums[i] 结尾的最长递增序列长度
                    if (lengths[j] >= lengths[i]) {
                        // 更新以 nums[i] 结尾的最长递增序列的长度和个数
                        lengths[i] = lengths[j] + 1;
                        counts[i] = counts[j];
                    } else if (lengths[j] + 1 == lengths[i]) {
                        counts[i] += counts[j];
                    }
                }
            }
        }

        // 求最长递增序列的长度
        int longest = 0;
        for (int len : lengths) longest = max(longest, len);

        // 求最长递增序列的个数
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (lengths[i] == longest) {
                ans += counts[i];
            }
        }

        return ans;
    }
};