/**
 * @param {character[][]} matrix
 * @return {number}
 */
var maximalSquare = function(matrix) {
    const m = matrix.length, n = matrix[0].length
    let ans = 0

    const dp = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0))

    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            if (matrix[i - 1][j - 1] == '1') {
                dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                ans = Math.max(ans, dp[i][j]);
            } else {
                // 对于以 0 为右下角的最大正方形边长设置为 0
                // 这里可加可不加，因为 dp[i][j] 初始化的时候就是 0
                dp[i][j] = 0
            }
        }
    }

    return ans * ans;
};

// 状态压缩：压缩为一维数组
// 计算当前的状态只依赖于上(preRow)、左上 (preRowPreCol) 以及左边 (dp[j - 1]) 三个状态
// 对于 preRow 和 preRowPreCol 的计算逻辑请参考：
// 课程 B 刷题篇第 26 天的力扣 1143 号算法题，视频讲解链接：https://ke.qq.com/course/3614291
var maximalSquare2 = function(matrix) {
    const m = matrix.length, n = matrix[0].length
    let ans = 0

    // 状态压缩为一维数组 
    const dp = new Array(n + 1).fill(0)

    for (let i = 1; i <= m; i++) {
        let preRow = 0
        let preRowPreCol = 0
        for (let j = 1; j <= n; j++) {
            preRowPreCol = preRow
            preRow = dp[j]
            if (matrix[i - 1][j - 1] == '1') {
                dp[j] = Math.min(preRow, Math.min(dp[j - 1], preRowPreCol)) + 1;
                ans = Math.max(ans, dp[j]);
            } else {
                // 对于以 0 为右下角的最大正方形边长设置为 0
                // 这里必须加上，因为经过若干个循环， dp[j] 已经不等于 0 了
                dp[j] = 0
            }
        }
    }

    return ans * ans;
};