class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        if (nums.size() < 2) return nums.size();
        unordered_set<int> set;
        for (int num : nums) set.insert(num);

        int ans = 1;
        for (int num : nums) {
            // 这里会存在重复计算，为什么会产生以及如何解决，请参考 issue：
            // https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4H4RZ
            if (set.count(num - 1)) continue;
            int currNum = num;
            int count = 1;
            while (set.count(currNum + 1)) {
                currNum += 1;
                count += 1;
            }
            ans = max(ans, count);
        }

        return ans;
    }

    int longestConsecutive1(vector<int>& nums) {
        if (nums.size() < 2) return nums.size();

        sort(nums.begin(), nums.end());
        int ans = 1;
        int count = 1;
        for (int i = 1; i < nums.size(); i++) {
            if (nums[i] == nums[i - 1]) continue;
            if (nums[i] == nums[i - 1] + 1) {
                count++;
                ans = max(ans, count);
            } else {
                count = 1;
            }
        }

        return ans;
    }
};