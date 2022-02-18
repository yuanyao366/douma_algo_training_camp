// 记忆化搜索(一)
func rob1(nums []int) int {
    var n = len(nums)
    // memo[i]：存储偷盗 [i, nums.length - 1] 区间房子得到的最大金额
    var memo = make([]int, n)
    for i := range memo {
        memo[i] = -1
    }

    var dfs func(int) int
    dfs = func(i int) int {
        if i >= n {
            return 0
        }
        if memo[i] != -1 {
            return memo[i]
        }
        var left = dfs(i + 1)
        var right = dfs(i + 2)
        memo[i] = max(left, right + nums[i])
        return memo[i]
    }

    return dfs(0)
}

// 记忆化搜索(二)
func rob2(nums []int) int {
    var n = len(nums)
    // memo[i]：存储偷盗 [0, i] 区间房子得到的最大金额
    var memo = make([]int, n)
    for i := range memo {
        memo[i] = -1
    }

    var dfs func(int) int
    dfs = func(i int) int {
        if i == 0 {
            return nums[0]
        }
        if i == 1 {
            return max(nums[0], nums[1])
        }
        if memo[i] != -1 {
            return memo[i]
        }
        var left = dfs(i - 1)
        var right = dfs(i - 2)
        memo[i] = max(left, right + nums[i])
        return memo[i]
    }

    return dfs(n - 1)
}

// 动态规划
func rob3(nums []int) int {
    var n = len(nums)
    if n == 1 {
        return nums[0]
    }

    // dp[i]：表示偷盗 [0, i] 区间房子得到的最大金额
    var dp = make([]int, n)

    // 2. 状态初始化
    dp[0] = nums[0]
    dp[1] = max(nums[0], nums[1])

    // 3. 状态转移
    for i := 2; i < n; i++ {
        dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])
    }

    // 4. 返回最终需要的状态值
    return dp[n - 1]
}

// 动态规划 + 状态压缩
func rob(nums []int) int {
    var n = len(nums)
    if n == 1 {
        return nums[0]
    }

    var prevMax, currMax = 0, 0

    for i := 0; i < n; i++ {
        var tmpMax = max(currMax, prevMax + nums[i])
        prevMax = currMax
        currMax = tmpMax
    }

    return currMax
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}