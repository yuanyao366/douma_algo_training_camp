/**
 * @param {number[]} nums
 * @return {number}
 */
var findMaxConsecutiveOnes = function(nums) {
    let ans = 0, left = 0, right = 0
    let zeroIndex = -1
    while (right < nums.length) {
        if (nums[right] == 0) {
            if (zeroIndex >= 0) {
                ans = Math.max(ans, right - left)
                left = zeroIndex + 1
            }
            zeroIndex = right
        }
        right++
    }
    return Math.max(ans, right - left)
};