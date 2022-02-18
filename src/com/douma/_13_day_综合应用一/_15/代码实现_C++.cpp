class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        if (nums.size() < 3) return {};
        vector<vector<int>> res;

        sort(nums.begin(), nums.end());
        for (int i = 0; i < nums.size() - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // 在 i + 1 ... nums.length - 1 中查找相加等于 -nums[i] 的两个数
            int target = - nums[i];
            int left = i + 1, right = nums.size() - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    res.push_back({nums[i], nums[left], nums[right]});
                    /*
                        下面的代码相当于：
                        while (left < right) {
                            // 不管前后相不相等，left 都要往前走
                            left++;
                            if (nums[left - 1] != nums[left]) break;
                        }
                        while (left < right) {
                            // 不管前后相不相等，right 都要往后走
                            right--;
                            if (nums[right + 1] != nums[right]) break;
                        }
                     */
                    // 去重
                    while (left < right && nums[left] == nums[++left]);
                    while (left < right && nums[right] == nums[--right]);
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
};