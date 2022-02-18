/**
 * @param {number[]} nums
 * @return {number}
 */
var findMaxConsecutiveOnes = function(nums) {
    let ans = 0, left = 0, right = 0
    while (right < nums.length) {
        if (nums[right] == 0) {
            ans = Math.max(ans, right - left)
            left = right + 1
        }
        right++
    }
    return Math.max(ans, right - left)
};