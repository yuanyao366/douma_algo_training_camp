/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var subsetsWithDup = function(nums) {
    nums.sort((a, b) => a - b)
    const res = [], subset = []

    const findSubsets = (startIndex) => {
        res.push(subset.slice())
        for (let i = startIndex; i < nums.length; i++) {
            // i > startIndex 的目的就是为了排除 i == startIndex 的情况，也就是保证 i 不是第一个子节点
            // 因为第一个子节点前面没有节点的，那么 nums[i] == nums[i - 1] 就没有意义的
            if (i > startIndex && nums[i] == nums[i - 1]) continue
            subset.push(nums[i])
            findSubsets(i + 1)
            subset.pop()
        }
    }

    findSubsets(0)
    return res
};