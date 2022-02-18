func combine(n int, k int) [][]int {
    var res = make([][]int, 0)
    if n <= 0 || k <= 0 || k > n {
        return res
    }
    var combination = make([]int, 0)

    var dfs func(int)
    dfs = func(start int) {
        if len(combination) == k {
            var temp = make([]int, len(combination))
            copy(temp, combination)
            res = append(res, temp)
            return
        }

        for i := start; i <= n; i++ {
            combination = append(combination, i)
            dfs(i + 1)
            combination = combination[:len(combination) - 1]
        }
    }

    dfs(1)
    return res
}