/**
 * @param {number[]} candidates
 * @param {number} target
 * @return {number[][]}
 */
var combinationSum2 = function(candidates, target) {
    candidates.sort((a, b) => a - b)
    const res = [], combination = []

    const dfs = (start, target) => {
        if (target < 0) return
        if (target == 0) {
            res.push(combination.slice())
            return
        }

        for (let i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) continue
            combination.push(candidates[i])
            dfs(i + 1, target - candidates[i])
            combination.pop()
        }
    }

    dfs(0, target)
    return res
};