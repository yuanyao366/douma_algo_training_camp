func subsetsWithDup(nums []int) [][]int {
    var res = make([][]int, 0)
    var subset = make([]int, 0)

    var dfs func(int)
    dfs = func(startIndex int) {
        var temp = make([]int, len(subset))
        copy(temp, subset)
        res = append(res, temp)

        for i := startIndex; i < len(nums); i++ {
            // i > startIndex 的目的就是为了排除 i == startIndex 的情况，也就是保证 i 不是第一个子节点
            // 因为第一个子节点前面没有节点的，那么 nums[i] == nums[i - 1] 就没有意义的
            if i > startIndex && nums[i] == nums[i - 1] {
                continue
            }
            subset = append(subset, nums[i])
            dfs(i + 1,)
            subset = subset[:len(subset) - 1]
        }
    }

    sort.Ints(nums)
    dfs(0)
    return res
}