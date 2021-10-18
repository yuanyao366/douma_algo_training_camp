/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
// 时间复杂度：O(logn)，注意，视频中说时间复杂度是 O(n)，这是口误
var search = function(nums, target) {
    let left = 0, right = nums.length - 1
    while (left <= right) {
        const mid = Math.floor((left + right) / 2)
        if (nums[mid] == target) return mid
        if (nums[left] <= nums[mid]) {
            if (nums[left] <= target && target < nums[mid]) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        } else {
            if (nums[mid] < target && target <= nums[right]) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
    }
    return -1
};