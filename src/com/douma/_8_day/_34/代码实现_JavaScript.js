/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var searchRange = function(nums, target) {
    if (nums.length == 0) return [-1, -1]
    const first = searchFirstIndex(nums, target)
    if (first == -1) return [-1, -1]
    const last = searchLastIndex(nums, target)
    return [first, last]
};

var searchFirstIndex = function(nums, target) {
    let left = 0, right = nums.length - 1
    while (left <= right) {
        const mid = Math.floor((left + right) / 2)
        if (nums[mid] == target) {
            if (mid == 0 || nums[mid - 1] != target) return mid
            else right = mid - 1
        } else if (nums[mid] < target) left = mid + 1
        else right = mid - 1
    }
    return -1
}

var searchLastIndex = function(nums, target) {
    let left = 0, right = nums.length - 1
    while (left <= right) {
        const mid = Math.floor((left + right) / 2)
        if (nums[mid] == target) {
            if (mid == nums.length - 1 || nums[mid + 1] != target) return mid
            else left = mid + 1
        } else if (nums[mid] < target) left = mid + 1
        else right = mid - 1
    }
    return -1
}