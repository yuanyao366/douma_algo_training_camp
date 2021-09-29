func combinationSum2(candidates []int, target int) [][]int {
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
            // 为了保证数组元素访问的顺序，所以 i > startIndex
            if i > startIndex && candidates[i] == candidates[i - 1] {
                continue
            }
            combination = append(combination, candidates[i])
            dfs(i + 1, target - candidates[i])
            combination = combination[:len(combination) - 1]
        }
    }
    // 将相同的元素放在一起
    sort.Ints(candidates)
    dfs(0, target)
    return res
}