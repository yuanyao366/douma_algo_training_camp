/**
 * @param {number[]} nums
 * @return {number}
 */
var majorityElement1 = function(nums) {
    return majorityElementHelp(nums, 0, nums.length - 1)
};

var majorityElementHelp = function(nums, lo, hi) {
    if (lo == hi) return nums[lo]

    const mid = lo +Math.floor((hi - lo) / 2)
    const leftNum = majorityElementHelp(nums, lo, mid)
    const rightNum = majorityElementHelp(nums, mid + 1, hi)
    if (leftNum == rightNum) return leftNum

    const leftNumCnt = countInRange(nums, leftNum, lo, hi)
    const rightNumCnt = countInRange(nums, rightNum, lo, hi)
    return leftNumCnt > rightNumCnt ? leftNum : rightNum
}

var countInRange = function(nums, target, lo, hi) {
    let count = 0
    for (let i = lo; i <= hi; i++) {
        if (target == nums[i]) count++
    }
    return count
}

// 摩尔投票算法
var majorityElement = function(nums) {
    let candidate = 0, count = 0
    for (const num of nums) {
         if (num == candidate) {
            count++
        } else if (count == 0) {
            count++
            candidate = num
        } else {
            count--
        }
    }
    return candidate
};