/**
 * @param {number} target
 * @param {number[]} nums
 * @return {number}
 */
var minSubArrayLen = function(target, nums) {
    const INT_MAX = 1 << 31 - 1
    let left = 0, right = 0
    let ans = INT_MAX, windowSum = 0
    while (right < nums.length) {
        windowSum += nums[right]
        while (windowSum >= target) {
            ans = Math.min(ans, right - left + 1)
            windowSum -= nums[left]
            left++
        }
        right++
    }
    return ans == INT_MAX ? 0 : ans
};