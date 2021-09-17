/**
* 抖码算法，让算法学习变的简单有趣
* @作者 : 老汤
*/

#include <vector>
using namespace std;

class Solution {
public:
    int findInMountainArray(int target, vector<int> nums) {
        // 1. 找到峰顶元素索引
        int peakIndex = searchPeakIndex(nums);
        // 2. 在前半部分应用二分查找算法查找目标值
        int index = binarySearchFrontPart(nums, 0, peakIndex, target);
        if (index != -1) {
            return index;
        }
        // 3. 在后半部分应用二分查找算法查找目标值
        return binarySearchLatterPart(nums, peakIndex, nums.size() - 1, target);
    }

    // 1. 找到峰顶元素索引
    int searchPeakIndex(vector<int> nums) {
        int left = 0;
        int right = nums.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // 2. 在前半部分应用二分查找算法查找目标值（思路 2 实现）
    int binarySearchFrontPart(vector<int> nums, int left, int peakIndex, int target) {
        while (left < peakIndex) {
            int mid = left + (peakIndex - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                peakIndex = mid;
            }
        }
        if (nums[left] == target) return left;
        return -1;
    }

    // 3. 在后半部分应用二分查找算法查找目标值（思路 2 实现）
    int binarySearchLatterPart(vector<int> nums, int peakIndex, int right, int target) {
        while (peakIndex < right) {
            int mid = peakIndex + (right - peakIndex) / 2;
            if (target < nums[mid]) {
                peakIndex = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[peakIndex] == target) return peakIndex;
        return -1;
    }
};