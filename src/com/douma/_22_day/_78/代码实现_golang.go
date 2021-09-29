func subsets(nums []int) [][]int {
    var res = make([][]int, 0)
    var subset = make([]int, 0)

    var dfs func(int)
    dfs = func(startIndex int) {
        var temp = make([]int, len(subset))
        copy(temp, subset)
        res = append(res, temp)

        for i := startIndex; i < len(nums); i++ {
            subset = append(subset, nums[i])
            dfs(i + 1,)
            subset = subset[:len(subset) - 1]
        }
    }

    dfs(0)
    return res
}