// 转化为完全背包问题：
//     从 coins 列表中可重复选择最少数量的硬币，使得他们的总金额为 amount
//     请问有多少种组合
func change(amount int, coins []int) int {
    // 1. 状态定义：dp[c] : 硬币列表能够凑成总金额为 c 的组合数。
    var dp = make([]int, amount + 1)

    // 2. 状态初始化
    // 凑成总金额为 0 的组合就是不选择任何硬币
    dp[0] = 1

    // 3. 状态转移
    for i := range coins {
        for c := coins[i]; c <= amount; c++ {
            dp[c] = dp[c] + dp[c - coins[i]]
        }
    }

    return dp[amount]
}