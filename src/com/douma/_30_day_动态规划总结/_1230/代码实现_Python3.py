from typing import List


class Solution:
    def probabilityOfHeads(self, prob: List[float], target: int) -> float:
        n = len(prob)
        # dp[i][j] ：表示前 i 枚硬币抛掷正面 j 次的概率
        dp = [[0.0] * (target + 1) for _ in range(n + 1)]
        dp[0][0] = 1
        for i in range(1, n + 1):
            # 当前投掷中有 0 次为正面，一种可能是上一次投掷就已经是 0 次正面了，本次投掷结果是反面
            dp[i][0] = dp[i - 1][0] * (1 - prob[i - 1])
        for i in range(1, n + 1):
            for j in range(1, target + 1):
                # 当前投掷中有 j 次为正面，一种可能是上一次投掷就已经是 j 次正面了，本次投掷结果是反面
                dp[i][j] = dp[i - 1][j] * (1 - prob[i - 1])
                # 前一次有 j - 1 次为正面，本次投掷为正面的可能性
                dp[i][j] += dp[i - 1][j - 1] * prob[i - 1]
        return dp[n][target]