/**
 * @param {number[][]} grid
 * @return {number}
 */

const dirs = [[1, 0], [0, 1]]

// 3. 记忆化搜索
var minPathSum1 = function(grid) {
    const MAX_INT = Math.pow(2, 31) - 1
    const m = grid.length, n = grid[0].length
    const memo = new Array(m).fill(0).map(() => new Array(n).fill(MAX_INT))

    const dfs = (row, col) => {
        if (row == m - 1 && col == n - 1) return grid[row][col]

        if (memo[row][col] != MAX_INT) return memo[row][col]

        let minPathSum = MAX_INT
        for (const dir of dirs) {
            const nextRow = row + dir[0]
            const nextCol = col + dir[1]
            if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n) continue
            const childMinPathSum = dfs(nextRow, nextCol)
            minPathSum = Math.min(minPathSum, childMinPathSum)
        }

        memo[row][col] = minPathSum + grid[row][col]
        return memo[row][col]
    }

    return dfs(0, 0)
};

// 4. 动态规划：从终点到起始点
var minPathSum2 = function(grid) {
    const m = grid.length, n = grid[0].length

    // 状态定义：dp[i][j] 表示从坐标 (i, j) 到右下角的最小路径和
    const dp = new Array(m).fill(0).map(() => new Array(n).fill(0))

    // 状态初始化
    dp[m - 1][n - 1] = grid[m - 1][n - 1]

    // 状态转移
    for (let i = m - 1; i >= 0 ; i--) {
        for (let j = n - 1; j >= 0 ; j--) {
            if (i == m - 1 && j != n - 1) { // 最后一行
                dp[i][j] = grid[i][j] + dp[i][j + 1]
            } else if (i != m - 1 && j == n - 1) { // 最后一列
                dp[i][j] = grid[i][j] + dp[i + 1][j]
            } else if (i != m - 1 && j != n - 1) {
                dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1])
            }
        }
    }

    // 返回结果
    return dp[0][0]
}

// 5. 动态规划：从起始点到终点
var minPathSum3 = function(grid) {
    const m = grid.length, n = grid[0].length

    // 状态定义：dp[i][j] 表示从 [0,0] 到 [i,j] 的最小路径和
    const dp = new Array(m).fill(0).map(() => new Array(n).fill(0))

    // 状态初始化
    dp[0][0] = grid[0][0]

    // 状态转移
    for (let i = 0; i < m ; i++) {
        for (let j = 0; j < n ; j++) {
            if (i == 0 && j != 0) {
                dp[i][j] = grid[i][j] + dp[i][j - 1]
            } else if (i != 0 && j == 0) {
                dp[i][j] = grid[i][j] + dp[i - 1][j]
            } else if (i != 0 && j != 0) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1])
            }
        }
    }

    // 返回结果
    return dp[m - 1][n - 1]
}

// 6. 动态规划：从起始点到终点 + 状态压缩
var minPathSum4 = function(grid) {
    const m = grid.length, n = grid[0].length

    // 状态定义：dp[i] 表示从 (0, 0) 到达第 i - 1 行的最短路径值
    const dp = new Array(n).fill(0)

    // 状态初始化
    dp[0] = grid[0][0]

    // 状态转移
    for (let i = 0; i < m ; i++) {
        for (let j = 0; j < n ; j++) {
            if (i == 0 && j != 0) {
                dp[j] = grid[i][j] + dp[j - 1]
            } else if (i != 0 && j == 0) {
                dp[j] = grid[i][j] + dp[j]
            } else if (i != 0 && j != 0) {
                dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1])
            }
        }
    }

    // 返回结果
    return dp[n - 1]
}

// 7. 动态规划：从起始点到终点 + 使用输入数组作为状态数组
var minPathSum = function(grid) {
    const m = grid.length, n = grid[0].length

    // 状态转移
    for (let i = 0; i < m ; i++) {
        for (let j = 0; j < n ; j++) {
            if (i == 0 && j != 0) {
                grid[i][j] = grid[i][j] + grid[i][j - 1]
            } else if (i != 0 && j == 0) {
                grid[i][j] = grid[i][j] + grid[i - 1][j]
            } else if (i != 0 && j != 0) {
                grid[i][j] = grid[i][j] + Math.min(grid[i - 1][j], grid[i][j - 1])
            }
        }
    }

    // 返回结果
    return grid[m - 1][n - 1]
}