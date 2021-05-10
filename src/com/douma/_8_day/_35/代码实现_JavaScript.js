// 思路二：在循环体内排除没有目标值的区间
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var searchInsert = function(nums, target) {
    if (nums.length == 0) return 0
    const n = nums.length
    if (target <= nums[0]) return 0
    if (target > nums[n - 1]) return n

    let left = 0, right = n - 1
    while (left < right) {
        const mid = Math.floor((left + right) / 2)
        if (nums[mid] == target) return mid
        else if (nums[mid] < target) left = mid + 1
        else right = mid
    }

    return left
};