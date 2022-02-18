
class Solution:
    def minCut(self, s: str) -> int:
        # 这里是在 leetcode 647 之上，做了点改变
        # 定义状态，dp[i][j] 表示子数组区间 [i, j] 对应的子串是否是回文
        dp = [[False] * len(s) for _ in range(len(s))]
        for i in range(len(s)):
            dp[i][i] = True
        for j in range(1, len(s)):
            for i in range(j):
                is_char_equal = s[i] == s[j]
                if j - i == 1:
                    # 只有两个字符
                    dp[i][j] = is_char_equal
                else:
                    dp[i][j] = is_char_equal and dp[i + 1][j - 1]

        # f[i]：表示以 s[i] 结尾最少分割次数
        f = [2**31 - 1] * len(s)
        for i in range(len(s)):
            # s[0...i] 是回文串，那么不需要分割
            if dp[0][i]:
                f[i] = 0
            else:
                for j in range(i):
                    # s[j + 1...i] 是回文串，那么可以不分割
                    if dp[j + 1][i]:
                        f[i] = min(f[i], f[j] + 1)

        return f[len(s) - 1]