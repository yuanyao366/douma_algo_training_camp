/**
 * @param {number[]} nums
 * @return {number}
 */
var longestConsecutive = function(nums) {
    if (nums.length < 2) return nums.length;
    const lookupTable = new Set(nums)
    let ans = 1
    for (const num of nums) {
        // 这里会存在重复计算，为什么会产生以及如何解决，请参考 issue：
        // https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4H4RZ
        if (lookupTable.has(num - 1)) continue
        let currNum = num, count = 1
        while (lookupTable.has(currNum + 1)) {
            currNum++
            count++
        }
        ans = Math.max(ans, count)
    }
    return ans
}

var longestConsecutive1 = function(nums) {
    if (nums.length < 2) return nums.length;
    nums.sort((a, b) => a - b)
    let ans = 1, count = 1
    for (let i = 1; i < nums.length; i++) {
        if (nums[i] == nums[i - 1]) continue
        if (nums[i] == nums[i - 1] + 1) {
            count++
            ans = Math.max(ans, count)
        } else {
            count = 1
        }
    }
    return ans
};