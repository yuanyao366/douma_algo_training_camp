from typing import List


class Solution:
    def maximalSquare(self, matrix: List[List[str]]) -> int:
        m, n, ans = len(matrix), len(matrix[0]), 0

        dp = [[0] * (n + 1) for _ in range(m + 1)]

        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if matrix[i - 1][j - 1] == '1':
                    dp[i][j] = min(dp[i - 1][j], min(dp[i - 1][j - 1], dp[i][j - 1])) + 1
                    ans = max(ans, dp[i][j])
                else:
                    # 对于以 0 为右下角的最大正方形边长设置为 0
                    # 这里可加可不加，因为 dp[i][j] 初始化的时候就是 0
                    dp[i][j] = 0

        return ans * ans

    # 状态压缩：压缩为一维数组
    # 计算当前的状态只依赖于上(preRow)、左上 (preRowPreCol) 以及左边 (dp[j - 1]) 三个状态
    # 对于 preRow 和 preRowPreCol 的计算逻辑请参考：
    # 课程 B 刷题篇第 26 天的力扣 1143 号算法题，视频讲解链接：https://ke.qq.com/course/3614291
    def maximalSquare2(self, matrix: List[List[str]]) -> int:
        m, n, ans = len(matrix), len(matrix[0]), 0

        # 状态压缩为一维数组 
        dp = [0] * (n + 1)

        for i in range(1, m + 1):
            pre_row_pre_col = pre_row = 0
            for j in range(1, n + 1):
                pre_row_pre_col = pre_row
                pre_row = dp[j]
                if matrix[i - 1][j - 1] == '1':
                    dp[j] = min(pre_row, min(pre_row_pre_col, dp[j - 1])) + 1
                    ans = max(ans, dp[j])
                else:
                    # 对于以 0 为右下角的最大正方形边长设置为 0
                    # 这里必须加上，因为经过若干个循环， dp[j] 已经不等于 0 了
                    dp[j] = 0

        return ans * ans