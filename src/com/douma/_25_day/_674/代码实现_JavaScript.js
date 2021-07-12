/**
 * @param {number[]} nums
 * @return {number}
 */
var findLengthOfLCIS = function(nums) {
    let ans = 1, slow = 0
    for (let fast = 1; fast < nums.length; fast++) {
        if (nums[fast - 1] >= nums[fast]) slow = fast
        ans = Math.max(ans, fast - slow + 1)
    }
    return ans
};