/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var longestOnes = function(nums, k) {
    let ans = 0, left = 0, right = 0, oneCnts = 0
    while (right < nums.length) {
        if (nums[right] == 1) oneCnts++

        while ((right - left + 1) - oneCnts > k) {
            if (nums[left] == 1) oneCnts--
            left++
        }
        ans = Math.max(ans, right - left + 1)
        right++
    }
    return ans
};