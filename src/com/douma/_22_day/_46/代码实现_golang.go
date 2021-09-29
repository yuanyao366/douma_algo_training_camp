func permute(nums []int) [][]int {
    var res, path = make([][]int, 0), make([]int, 0)
    var used = make([]bool, len(nums))

    var dfs func()
    dfs = func() {
        if len(path) == len(nums) {
            var temp = make([]int, len(path))
            copy(temp, path)
            res = append(res, temp)
            return
        }

        for i := range nums {
            if used[i] {
                continue
            }
            path = append(path, nums[i])
            used[i] = true
            dfs()
            path = path[:len(path) - 1]
            used[i] = false
        }
    }

    dfs()

    return res
}