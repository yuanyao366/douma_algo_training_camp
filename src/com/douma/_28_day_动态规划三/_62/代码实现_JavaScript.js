/**
 * @param {number} m
 * @param {number} n
 * @return {number}
 */
// 1. 记忆化搜索
var uniquePaths1 = function(m, n) {
    const memo = new Array(m).fill(-1).map(() => new Array(n).fill(-1))

    const dfs = (i, j) => {
        if (i == m - 1 && j == n - 1) return 1
        if (i == m || j == n) return 0

        if (memo[i][j] != -1) return memo[i][j]

        const left = dfs(i, j + 1)
        const right = dfs(i + 1, j)

        memo[i][j] = left + right
        return memo[i][j]
    }

    return dfs(0, 0)
};

// 2. 动态规划（左上到右下）
var uniquePaths2 = function(m, n) {
    // dp[i][j]：表示从位置 [i, j] 到 [m - 1. n - 1] 的路径数
    const dp = new Array(m).fill(-1).map(() => new Array(n).fill(-1))

    // 状态转移
    for (let i = m - 1; i >= 0; i--) {
        for (let j = n - 1; j >= 0; j--) {
            if (i == m - 1 || j == n - 1) {
                dp[i][j] = 1;
            } else {
                dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
            }
        }
    }
    return dp[0][0];
}

// 3. 动态规划（左上到右下）- 状态压缩
var uniquePaths3 = function(m, n) {
    // dp[i][j]：表示从位置 [i, j] 到 [m - 1. n - 1] 的路径数
    const dp = new Array(n).fill(-1)

    // 状态转移
    for (let i = m - 1; i >= 0; i--) {
        for (let j = n - 1; j >= 0; j--) {
            if (i == m - 1 || j == n - 1) {
                dp[j] = 1;
            } else {
                dp[j] = dp[j] + dp[j + 1];
            }
        }
    }
    return dp[0];
}

// 4. 动态规划(右下到左上)
var uniquePaths4 = function(m, n) {
    // dp[i][j]：表示从位置[0, 0] 到 [i, j] 的路径数
    const dp = new Array(m).fill(-1).map(() => new Array(n).fill(-1))

    // 状态转移
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (i == 0 || j == 0) {
                dp[i][j] = 1;
            } else if (i != 0 && j != 0) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
    }

    return dp[m - 1][n - 1];
}

// 5. 动态规划(右下到左上) - 状态压缩
var uniquePaths = function(m, n) {
    // dp[i][j]：表示从位置[0, 0] 到 [i, j] 的路径数
    const dp = new Array(n).fill(-1)

    // 状态转移
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (i == 0 || j == 0) {
                dp[j] = 1;
            } else if (i != 0 && j != 0) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
    }

    return dp[n - 1];
}