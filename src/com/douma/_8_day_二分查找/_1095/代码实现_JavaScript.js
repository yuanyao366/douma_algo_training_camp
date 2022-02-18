

const findInMountainArray = function (target, nums) {
    // 1. 找到峰顶元素索引
    const peakIndex = searchPeakIndex(nums);
    // 2. 在前半部分应用二分查找算法查找目标值
    const index = binarySearchFrontPart(nums, 0, peakIndex, target);
    if (index != -1) {
        return index;
    }
    // 3. 在后半部分应用二分查找算法查找目标值
    return binarySearchLatterPart(nums, peakIndex, nums.length - 1, target);
}

// 1. 找到峰顶元素索引
const searchPeakIndex = function (nums) {
    let left = 0
    let right = nums.length - 1
    while (left < right) {
        const mid = left + Math.floor((right - left) / 2)
        if (nums[mid] < nums[mid + 1]) {
            left = mid + 1
        } else {
            right = mid
        }
    }
    return left
}

// 2. 在前半部分应用二分查找算法查找目标值（思路 2 实现）
const binarySearchFrontPart = function (nums, left, peakIndex, target) {
    while (left < peakIndex) {
        const mid = left + Math.floor((peakIndex - left) / 2)
        if (target > nums[mid]) {
            left = mid + 1
        } else {
            peakIndex = mid
        }
    }
    if (nums[left] == target) return left
    return -1;
}

// 3. 在后半部分应用二分查找算法查找目标值
const binarySearchLatterPart = function (nums, peakIndex, right, target) {
    while (peakIndex < right) {
        const mid = peakIndex + Math.floor((right - peakIndex) / 2)
        if (target < nums[mid]) {
            peakIndex = mid + 1
        } else {
            right = mid
        }
    }
    if (nums[peakIndex] == target) return peakIndex
    return -1
}