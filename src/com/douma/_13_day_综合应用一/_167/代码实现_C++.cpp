class Solution {
public:
    // 二分查找
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(1)
    vector<int> twoSum1(vector<int>& numbers, int target) {
        for (int i = 0; i < numbers.size(); i++) {
            int x = numbers[i];
            // 线性查找 - O(n)
            // 1. 二分查找 - O(logn)
            // [i + 1, numbers.length - 1] 区间二分查找 target - x
            int index = binarySearch(numbers, i + 1, numbers.size() - 1, target - x);
            if (index != -1) {
                return {i + 1, index + 1};
            }
        }

        return {};
    }

    int binarySearch(vector<int>& nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return -1;
    }

    // 双指针
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    vector<int> twoSum(vector<int>& numbers, int target) {
        int left = 0, right = numbers.size() - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return {left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return {};
    }
};