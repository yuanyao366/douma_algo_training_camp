class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> map;

        for (int i = 0; i < nums.size(); i++) {
            int x = nums[i];
            if (map.count(target - x)) {
                int index = map[target - x];
                return {i, index};
            }
            map[x] = i;
        }

        return {};
    }

    vector<int> twoSum2(vector<int>& nums, int target) {
        unordered_map<int, int> map;
        for (int i = 0; i < nums.size(); i++) {
            map[nums[i]] = i;
        }

        for (int i = 0; i < nums.size(); i++) {
            int x = nums[i];
            if (map.count(target - x)) {
                int index = map[target - x];
                if (i != index) return {i, index};
            }
        }

        return {};
    }

    vector<int> twoSum1(vector<int>& nums, int target) {
        for (int i = 0; i < nums.size(); i++) {
            int x = nums[i];
            // 线性查找 - O(n)
            for (int j = i + 1; j < nums.size(); j++) {
                if (nums[j] == target - x) {
                    return {i, j};
                }
            }
        }

        return {};
    }
};