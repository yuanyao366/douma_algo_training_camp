class Solution {
public:
    // 思路二：在循环体内排除没有目标值的区间
    int search(vector<int>& nums, int target) {
        int left = 0;
        int right = nums.size() - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }

    // 思路二：在循环体内排除没有目标值的区间
    int search3(vector<int>& nums, int target) {
        int left = 0;
        int right = nums.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2 + 1;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid;
            else right = mid - 1;
        }
        if (nums[left] == target) return left;
        return -1;
    }

    // 思路二：在循环体内排除没有目标值的区间
    int search2(vector<int>& nums, int target) {
        int left = 0;
        int right = nums.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        if (nums[left] == target) return left;
        return -1;
    }

    // 思路一：在循环体内查找目标值
    int search1(vector<int>& nums, int target) {
        int left = 0;
        int right = nums.size();
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
};