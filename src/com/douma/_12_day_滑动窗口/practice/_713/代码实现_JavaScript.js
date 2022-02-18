/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var numSubarrayProductLessThanK = function(nums, k) {
    if (k <= 1) return 0

    let left = 0, right = 0
    let ans = 0, prod = 1
    while (right < nums.length) {
        prod *= nums[right]
        while (prod >= k) {
            prod = Math.floor(prod / nums[left])
            left++
        }
        ans += right - left + 1
        right++
    }
    return ans
};