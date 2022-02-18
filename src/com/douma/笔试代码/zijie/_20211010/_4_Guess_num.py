# 抖码算法，让算法学习变得简单有趣
# 作者：老汤

# 猜数字猜赢的概率
# 动态规划
# 时间复杂度：O(n^2)
# 空间复杂度：O(n^2)
# leetcode 相似题：https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/
if __name__ == '__main__':

    n = int(input())

    if n == 1:
        print(1)
    else:
        # dp[i]：表示依次有 i 个数字，Alice 赢得游戏的最大概率
        dp = [0.0] * (n + 1)
        dp[1], dp[2] = 1, 0.5

        for i in range(3, n + 1):
            prob = 1.0 / i
            for j in range(1, i):
                dp[i] = max(dp[i], prob * (dp[j - 1] + dp[i - j]))

        print('%f' % dp[n])