class Solution:
    # 动态规划
    # 时间复杂度：O(n^2)
    def countSubstrings(self, s: str) -> int:
        if len(s) == 0: return 0

        # 定义状态，dp[i][j] 表示子数组区间 [i, j] 对应的子串是否是回文
        dp = [[False] * len(s) for _ in range(len(s))]

        res = 0
        # 状态初始化, 一个字符，肯定是回文
        for i in range(len(s)):
            dp[i][i], res = True, res + 1

        # 状态转移
        for j in range(1, len(s)):
            for i in range(j):
                is_char_equal = s[i] == s[j]
                if j - i == 1:
                    # 只有两个字符
                    dp[i][j] = is_char_equal
                else:
                    dp[i][j] = is_char_equal and dp[i + 1][j - 1]
                if dp[i][j]: res += 1

        return res