// 1. 记忆化搜索
func PredictTheWinner1(nums []int) bool {
    var n = len(nums)
    var memo = make([][]int, n)
    for i := range memo {
        memo[i] = make([]int, n)
        for j := range memo[i] {
            memo[i][j] = math.MinInt32
        }
    }

    // 玩家 1 在区间 [i, j] 内可以赢的最多的分
    var dfs func(int, int) int
    dfs = func(i, j int) int {
        if i == j {
            return nums[i]
        }

        if memo[i][j] != math.MinInt32 {
            return memo[i][j]
        }

        var pickI = nums[i] - dfs(i + 1, j)
        var pickJ = nums[j] - dfs(i, j - 1)

        memo[i][j] = pickI
        if memo[i][j] < pickJ {
            memo[i][j] = pickJ
        }
        return memo[i][j]
    }

    return dfs(0, n - 1) >= 0
}

// 2. 动态规划
func PredictTheWinner(nums []int) bool {
    var n = len(nums)
    // dp[i][j] 表示玩家 1 在区间 [i, j] 之内可以赢的最多的分
    var dp = make([][]int, n)
    for i := range dp {
        dp[i] = make([]int, n)
        for j := range dp[i] {
            dp[i][j] = math.MinInt32
        }
        dp[i][i] = nums[i]
    }

    for i := n - 2; i >= 0; i-- {
        for j := i + 1; j < n; j++ {
            dp[i][j] = max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1])
        }
    }

    return dp[0][n - 1] >= 0
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}