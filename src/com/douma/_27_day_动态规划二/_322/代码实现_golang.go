// 转化为完全背包问题：
    //     从 coins 列表中可重复选择最少数量的硬币，使得他们的总金额为 amount
func coinChange(coins []int, amount int) int {
    // 1. 状态定义：dp[c] : 表示凑齐总金额为 c 的时候需要的最小硬币数
    var dp = make([]int, amount + 1)

    // 2. 状态初始化
    for i := range dp {
        dp[i] = amount + 1
    }
    // 凑齐总金额为 0 的时候需要的最小硬币数就是不取硬币
    dp[0] = 0

    // 3. 状态转移
    for i := range coins {
        for c := coins[i]; c <= amount; c++ {
            if 1 + dp[c - coins[i]] < dp[c] {
                dp[c] = 1 + dp[c - coins[i]]
            }
        }
    }

    if dp[amount] == amount + 1 {
        return -1
    } else {
        return dp[amount]
    }
}