class Solution {
public:
    // 哈希表
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    bool containsNearbyDuplicate1(vector<int>& nums, int k) {
        unordered_map<int, int> indexMap;
        for (int i = 0; i < nums.size(); i++) {
            // 在 [0, i) 区间内哈希查找 nums[i]
            if (indexMap.count(nums[i])) {
                int j = indexMap[nums[i]];
                if (i - j <= k) return true;
            }
            indexMap[nums[i]] = i;
        }
        return false;
    }

    // 滑动窗口
    // 时间复杂度：O(n)
    // 空间复杂度：O(k)
    bool containsNearbyDuplicate(vector<int>& nums, int k) {
        int left = 0;
        int right = 0;
        unordered_set<int> window;

        while (right < nums.size()) {
            // 窗口内哈希查找
            if (window.count(nums[right])) return true;

            window.insert(nums[right]);

            if (window.size() > k) {
                window.erase(nums[left]);
                left++;
            }

            right++;
        }

        return false;
    }
};