/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var findMaxAverage = function(nums, k) {
    let left = 0, right = 0
    let maxSum = 1 << 31, windowSum = 0
    while (right < nums.length) {
        windowSum += nums[right]
        if (right - left + 1 == k) {
            maxSum = Math.max(maxSum, windowSum)
            windowSum -= nums[left]
            left++
        }
        right++;
    }
    return maxSum / k
};