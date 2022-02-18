class Solution {
public:
    // 思路二：在循环体内排除没有目标值的区间
    int searchInsert(vector<int>& nums, int target) {
        if (nums.size() == 0) return 0;
        int n = nums.size();
        if (target <= nums[0]) return 0;
        if (target > nums[n - 1]) return n;

        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid;
        }

        return left;
    }
};