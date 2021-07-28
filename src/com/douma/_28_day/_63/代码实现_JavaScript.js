/**
 * @param {number[][]} obstacleGrid
 * @return {number}
 */
var uniquePathsWithObstacles1 = function(obstacleGrid) {
    const m = obstacleGrid.length, n = obstacleGrid[0].length

    // dp[i][j]：表示从点 [0, 0] 到达 [i, j] 的不同路径数
    const dp = new Array(m).fill(0).map(() => new Array(n).fill(0))

    // 状态转移
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (obstacleGrid[i][j] == 1) {
                dp[i][j] = 0;
                continue;
            }
            if (i == 0 && j == 0) {
                dp[i][j] = 1;
            } else if (i == 0) {
                dp[i][j] = dp[i][j - 1];
            } else if (j == 0) {
                dp[i][j] = dp[i - 1][j];
            } else {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
    }
    return dp[m - 1][n - 1];
};

var uniquePathsWithObstacles = function(obstacleGrid) {
    const m = obstacleGrid.length, n = obstacleGrid[0].length

    // dp[i][j]：表示从点 [0, 0] 到达 [i, j] 的不同路径数
    const dp = new Array(n)

    // 状态转移
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (obstacleGrid[i][j] == 1) {
                dp[j] = 0;
                continue;
            }
            if (i == 0 && j == 0) {
                dp[j] = 1;
            } else if (i == 0) {
                dp[j] = dp[j - 1];
            } else if (j == 0) {
                dp[j] = dp[j];
            } else {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
    }
    return dp[n - 1];
};