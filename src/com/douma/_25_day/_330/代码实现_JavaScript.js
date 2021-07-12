/**
 * @param {number[]} nums
 * @param {number} n
 * @return {number}
 */
var minPatches = function(nums, n) {
    let res = 0, index = 0, x = 1
    while (x <= n) {
        if (index < nums.length && nums[index] <= x) {
            x += nums[index]
            index++
        } else {
            x *= 2
            res++
        }
    }
    return res
};