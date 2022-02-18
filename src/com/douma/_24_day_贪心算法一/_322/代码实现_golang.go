// 贪心
func coinChange1(coins []int, amount int) int {
    sort.Ints(coins)

    var rest, res = amount, 0
    for i := len(coins) - 1; i >= 0; i-- {
        var  currCount = rest / coins[i]
        rest = rest - currCount * coins[i]

        res += currCount
        if rest == 0 {
            return res
        }
    }

    return -1
}

// 回溯
func coinChange2(coins []int, amount int) int {
    var res, cs = make([][]int, 0), make([]int, 0)

    var dfs func(int)
    dfs = func(target int) {
        if target < 0 {
            return
        }
        if target == 0 {
            var tmp = make([]int, len(cs))
            copy(tmp, cs)
            res = append(res, tmp)
            return
        }

        for i := range coins {
            cs = append(cs, coins[i])
            dfs(target - coins[i])
            cs = cs[:len(cs) - 1]
        }
    }
    // 1. 回溯穷举所有的硬币组合
    dfs(amount)

    // 2. 没有任何的硬币组合，返回 -1
    if len(res) == 0 {
        return -1
    }

    // 3. 找到适应硬币数最少的组合的硬币数
    var min = 0
    for i := 1; i < len(res); i++ {
        if len(res[i]) < len(res[min]) {
            min = i
        }
    }

    return len(res[min])
}

// 回溯
func coinChange3(coins []int, amount int) int {
    var cs = make([]int, 0)
    var minCoins = math.MaxInt32

    var dfs func(int)
    dfs = func(target int) {
        if target == 0 {
            if len(cs) < minCoins {
                minCoins = len(cs)
            }
            return
        }

        for i := range coins {
            if target - coins[i] < 0 {
                continue
            }
            cs = append(cs, coins[i])
            dfs(target - coins[i])
            cs = cs[:len(cs) - 1]
        }
    }
    dfs(amount)

    if minCoins == math.MaxInt32 {
        return -1
    } else {
        return minCoins
    }
}


// 回溯 + 贪心 不能提升性能
func coinChange(coins []int, amount int) int {
    var cs = make([]int, 0)
    var minCoins = math.MaxInt32

    var dfs func(int)
    dfs = func(target int) {
        if target == 0 {
            if len(cs) < minCoins {
                minCoins = len(cs)
            }
            return
        }

        for i := len(coins) - 1; i >= 0; i-- {
            if target - coins[i] < 0 {
                continue
            }
            cs = append(cs, coins[i])
            dfs(target - coins[i])
            cs = cs[:len(cs) - 1]
        }
    }
    // 升序排列
    sort.Ints(coins)

    dfs(amount)

    if minCoins == math.MaxInt32 {
        return -1
    } else {
        return minCoins
    }
}

