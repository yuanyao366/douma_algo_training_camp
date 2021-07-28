/**
 * @param {number[][]} triangle
 * @return {number}
 */
var minimumTotal1 = function(triangle) {
    const n = triangle.length
    // dp[i][j] 表示从点 [i, j] 到底边的最小路径和。
    const dp = new Array(n).fill(0).map(() => new Array(n).fill(0))
    for (let i = 0; i < n; i++) {
        dp[n - 1][i] = triangle[n - 1][i]
    }

    for (let i = n - 2; i >= 0; i--) {
        for (let j = 0; j <= i; j++) {
            dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j]
        }
    }
    return dp[0][0]
};

var minimumTotal = function(triangle) {
    const n = triangle.length
    // dp[i][j] 表示从点 [i, j] 到底边的最小路径和。
    const dp = new Array(n).fill(0)
    for (let i = 0; i < n; i++) {
        dp[i] = triangle[n - 1][i]
    }

    for (let i = n - 2; i >= 0; i--) {
        for (let j = 0; j <= i; j++) {
            dp[j] = Math.min(dp[j], dp[j + 1]) + triangle[i][j]
        }
    }
    return dp[0]
};