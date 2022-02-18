class Solution {
public:
    // 线性查找(超时)
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    bool containsDuplicate1(vector<int>& nums) {
        int size = nums.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    // 排序查找
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(logn) 或者 O(n)
    bool containsDuplicate2(vector<int>& nums) {
        sort(nums.begin(), nums.end());

        int size = nums.size();
        for (int i = 1; i < size; i++) {
            if (nums[i - 1] == nums[i]) return true;
        }
        return false;
    }

    // 哈希查找
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    bool containsDuplicate(vector<int>& nums) {
        unordered_set<int> visited;

        int size = nums.size();
        for (int i = 0; i < size; i++) {
            if (visited.count(nums[i])) return true;
            visited.insert(nums[i]);
        }
        return false;
    }
};