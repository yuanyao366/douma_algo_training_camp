func combinationSum(candidates []int, target int) [][]int {
    var res = make([][]int, 0)
    var combination = make([]int, 0)

    var dfs func(int, int)
    dfs = func(startIndex int, target int) {
        if target < 0 {
            return
        }
        if target == 0 {
            var temp = make([]int, len(combination))
            copy(temp, combination)
            res = append(res, temp)
            return
        }

        for i := startIndex; i < len(candidates); i++ {
            combination = append(combination, candidates[i])
            dfs(i, target - candidates[i])
            combination = combination[:len(combination) - 1]
        }
    }

    dfs(0, target)
    return res
}