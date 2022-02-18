/**
 * @param {number[][]} matrix
 * @return {number}
 */
var minFallingPathSum = function(matrix) {
    const n = matrix.length

    // dp[i][j]：表示元素 [i, j] 的下降路径最小和
    const dp = new Array(n).fill(0).map(() => new Array(n).fill(0))

    // 最后一行所有元素的下降路径最小和就是元素值本身
    for (let j = 0; j < n; j++) {
        dp[n - 1][j] = matrix[n - 1][j]
    }

    for (let i = n - 2; i >= 0; i--) {
        for (let j = 0; j < n; j++) {
            // dp[i][j] = min(dp[i + 1][j], dp[i + 1][j - 1], dp[i + 1][j + 1])
            let minSum = dp[i + 1][j]
            if (j > 0) minSum = Math.min(minSum, dp[i + 1][j - 1])
            if (j + 1 < n) minSum = Math.min(minSum, dp[i + 1][j + 1])

            dp[i][j] = minSum + matrix[i][j]
        }
    }

    let ans = Math.pow(2, 31) - 1
    for (const x of dp[0]) ans = Math.min(ans, x)

    return ans
};