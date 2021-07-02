/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var subsets = function(nums) {
    const res = [], subset = []

    const findSubsets = (startIndex) => {
        res.push(subset.slice())
        for (let i = startIndex; i < nums.length; i++) {
            subset.push(nums[i])
            findSubsets(i + 1)
            subset.pop()
        }
    }

    findSubsets(0)
    return res
};