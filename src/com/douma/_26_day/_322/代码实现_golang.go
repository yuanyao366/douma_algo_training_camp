// 回溯 + 记忆化搜索
func coinChange5(coins []int, amount int) int {
    var memo = make([]int, amount + 1)
    for i := range memo {
        memo[i] = math.MaxInt32
    }

    // 计算返回凑成总金额 target 需要的最少硬币数
    var dfs func(int) int
    dfs = func(target int) int {
        if target == 0 {
            return 0
        }
        if memo[target] != math.MaxInt32 {
            return memo[target]
        }

        var minCoins = math.MaxInt32
        for i := range coins {
            if target - coins[i] < 0 {
                continue
            }
            var subMinCoins = dfs(target - coins[i])
            if subMinCoins == -1 {
                continue
            }
            if subMinCoins + 1 < minCoins {
                minCoins = subMinCoins + 1
            }
        }
        if minCoins == math.MaxInt32 {
            memo[target] = -1
        } else {
            memo[target] = minCoins
        }
        return memo[target]
    }

    return dfs(amount)
}

// 动态规划
func coinChange(coins []int, amount int) int {
    if amount < 0 {
        return -1
    }
    if amount == 0 {
        return 0
    }

    // 1. 状态定义：dp[i] 表示凑齐总金额为 i 的时候需要的最小硬币数
    // 注意：因为要比较的是最小值，这个不可能的值就得赋值成为一个最大值
    var dp = make([]int, amount + 1)
    for i := range dp {
        dp[i] = math.MaxInt32
    }

    // 2. 状态初始化
    dp[0] = 0

    // 3. 状态转移
    for target := 1; target <= amount; target++ {
        for _, coin := range coins {
            if target >= coin && dp[target - coin] != math.MaxInt32 {
                if dp[target - coin] + 1 < dp[target] {
                    dp[target] = dp[target - coin] + 1
                }
            }
        }
    }

    // 4. 返回最终需要的状态值
    if dp[amount] == math.MaxInt32 {
        return -1
    } else {
        return dp[amount]
    }
}