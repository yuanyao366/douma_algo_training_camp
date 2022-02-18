/**
 * @param {number[]} candidates
 * @param {number} target
 * @return {number[][]}
 */
var combinationSum = function(candidates, target) {
    const res = [], combination = []

    const dfs = (start, target) => {
        if (target < 0) return
        if (target == 0) {
            res.push(combination.slice())
            return
        }

        for (let i = start; i < candidates.length; i++) {
            combination.push(candidates[i])
            dfs(i, target - candidates[i])
            combination.pop()
        }
    }

    dfs(0, target)
    return res
};