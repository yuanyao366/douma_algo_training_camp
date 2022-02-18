/**
 * @param {number[]} nums
 * @return {boolean}
 */
// 排序
var containsDuplicate1 = function(nums) {
    nums.sort((a, b) => a - b)
    for (let i = 1; i < nums.length; i++) {
        if (nums[i - 1] == nums[i]) return true
    }
    return false
};

// 哈希查找
var containsDuplicate = function(nums) {
    const visited = new Set()
    for (let i = 0; i < nums.length; i++) {
        if (visited.has(nums[i])) return true
        visited.add(nums[i])
    }
    return false
};