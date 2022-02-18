// 完全平方数最小为 1，最大为 sqrt(n)
// 也就是我们要从 nums = [1, 2, ..., sqrt(n)] 数组里选出几个数，
// 令其平方和为 n(背包容量)。求最少的完全平方数
func numSquares(n int) int {
    // dp[i] 表示和为 i 的 nums 组合中完全平方数最少个数
    var dp = make([]int, n + 1)
    for i := range dp {
        dp[i] = math.MaxInt32
    }
    dp[0] = 0
    for num := 1; num * num <= n; num++ {
        for c := num * num; c <= n; c++{
            if dp[c - num * num] + 1 < dp[c] {
                dp[c] = dp[c - num * num] + 1
            }
        }
    }

    return dp[n]
}