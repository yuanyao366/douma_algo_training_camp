/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var permuteUnique = function(nums) {
    // 排序，去重的基础
    nums.sort((a, b) => a - b)
    const res = [], path = []
    const used = new Array(nums.length).fill(false)

    const dfs = () => {
        if (path.length == nums.length) {
            res.push(path.slice())
            return
        }

        for (let i = 0; i < nums.length; i++) {
            if (used[i]) continue
            // 去重的条件
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue
            path.push(nums[i])
            used[i] = true
            dfs()
            path.pop()
            used[i] = false
        }
    }

    dfs()
    return res
};