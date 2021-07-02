/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var permute = function(nums) {
    const res = [], path = []
    const used = new Array(nums.length).fill(false)

    const dfs = () => {
        if (path.length == nums.length) {
            res.push(path.slice())
            return
        }

        for (let i = 0; i < nums.length; i++) {
            if (used[i]) continue
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