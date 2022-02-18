/**
 * @param {number[]} nums
 * @return {boolean}
 */
// 记忆化搜索
var PredictTheWinner1 = function(nums) {
    const m = nums.length, MIN_INT = -Math.pow(2, 31)
    const memo = new Array(m).fill(0).map(() => new Array(m).fill(MIN_INT))

    // 玩家 1 在区间 [i, j] 内可以赢的最多的分
    const dfs = (i, j) => {
        if (i == j) return nums[i]

        if (memo[i][j] != MIN_INT) return memo[i][j]

        const pickI = nums[i] - dfs(i + 1, j)
        const pickJ = nums[j] - dfs(i, j - 1)

        memo[i][j] = Math.max(pickI, pickJ)
        return memo[i][j]
    }

    return dfs(0, m - 1) >= 0
};

// 动态规划
var PredictTheWinner = function(nums) {
    const m = nums.length, MIN_INT = -Math.pow(2, 31)
    // dp[i][j] 表示玩家 1 在区间 [i, j] 之内可以赢的最多的分
    const dp = new Array(m).fill(0).map(() => new Array(m).fill(MIN_INT))

    for (let i = 0; i < m; i++) {
        dp[i][i] = nums[i]
    }

    for (let i = m - 2; i >= 0; i--) {
        for (let j = i + 1; j < m; j++) {
            dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1])
        }
    }

    return dp[0][m - 1] >= 0
};