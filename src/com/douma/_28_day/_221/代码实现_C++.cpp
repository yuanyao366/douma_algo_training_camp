class Solution {
public:
    int maximalSquare(vector<vector<char>>& matrix) {
        int m = matrix.size();
        int n = matrix[0].size();
        int ans = 0;

        // 初始化状态数组
        // 行的长度和列的长度都增加 1，有利于边界条件的处理
        vector<vector<int>> dp(m + 1, vector<int>(n + 1));

        // 状态转移
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = min(dp[i - 1][j], min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    ans = max(ans, dp[i][j]);
                } else {
                    // 对于以 0 为右下角的最大正方形边长设置为 0
                    // 这里可加可不加，因为 dp[i][j] 初始化的时候就是 0
                    dp[i][j] = 0;
                }
            }
        }

        return ans * ans;
    }

    // 状态压缩：压缩为一维数组
    // 计算当前的状态只依赖于上(preRow)、左上 (preRowPreCol) 以及左边 (dp[j - 1]) 三个状态
    // 对于 preRow 和 preRowPreCol 的计算逻辑请参考：
    // 课程 B 刷题篇第 26 天的力扣 1143 号算法题，视频讲解链接：https://ke.qq.com/course/3614291
    int maximalSquare2(vector<vector<char>>& matrix) {
        int m = matrix.size();
        int n = matrix[0].size();
        int ans = 0;

        // 状态压缩为一维数组 
        // 行的长度和列的长度都增加 1，有利于边界条件的处理
        vector<int> dp(n + 1);

        // 状态转移
        for (int i = 1; i <= m; i++) {
            int preRow = 0;
            int preRowPreCol = 0;
            for (int j = 1; j <= n; j++) {
                preRowPreCol = preRow;
                preRow = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = min(preRow, min(dp[j - 1], preRowPreCol)) + 1;
                    ans = max(ans, dp[j]);
                } else {
                    // 对于以 0 为右下角的最大正方形边长设置为 0
                    // 这里必须加上，因为经过若干个循环， dp[j] 已经不等于 0 了
                    dp[j] = 0;
                }
            }
        }

        return ans * ans;
    }
};