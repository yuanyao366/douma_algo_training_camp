# 抖码算法，让算法学习变得简单有趣
# 作者：老汤

# 猜数字猜赢的概率
# 动态规划
# 时间复杂度：O(n^3)
# 空间复杂度：O(n^2)
# 建议先看看 leetcode 相似题：https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/
if __name__ == '__main__':

    n = int(input())

    if n == 1:
        print(1)
    else:
        # dp[i][j]：表示依次从 i 到 j 的数字作为分割点(猜的数)，Alice 赢得游戏的最大概率
        dp = [[0.0] * (n + 1) for _ in range(n + 1)]
        for i in range(n + 1):
            # 只有 i 这一个数字的时候，肯定能猜对，概率为 1
            dp[i][i] = 1
        for j in range(2, n + 1):
            for i in range(j - 1, 0, -1):
                # 在区间 [i, j] 中选择每一个数字的概率
                prob = 1.0 / (j - i + 1)
                # 算除了两端的每一个分割点
                for k in range(i + 1, j):
                    # 选择 k 这个数字，被猜中的概率为 prob，然后乘以 k 左边被猜中的概率和 k 右边被猜中的概率之和
                    # 取最大值
                    dp[i][j] = max(prob * (dp[i][k - 1] + dp[k + 1][j]), dp[i][j])

                # 算两端
                dp[i][j] = max(dp[i][j], prob * dp[i + 1][j])
                dp[i][j] = max(dp[i][j], prob * dp[i][j - 1])
        print('%f' % dp[1][n])