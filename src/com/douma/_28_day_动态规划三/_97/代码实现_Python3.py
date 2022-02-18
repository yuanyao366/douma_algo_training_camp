

class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        m, n, t = len(s1), len(s2), len(s3)
        if m + n != t:
            return False
        # dp[i][j]：s1 的前 i 个字符和 s2 的前 j 个字符是否可以交错组成 s3 的前 i + j 个字符
        dp = [[False] * (n + 1) for _ in range(m + 1)]

        dp[0][0] = True
        for i in range(1, m + 1):
            if s1[i - 1] == s3[i - 1]:
                dp[i][0] = True
            else:
                break
        for j in range(1, n + 1):
            if s2[j - 1] == s3[j - 1]:
                dp[0][j] = True
            else:
                break

        for i in range(1, m + 1):
            for j in range(1, n + 1):
                k = i + j
                s1_equal_s3 = s1[i - 1] == s3[k - 1] and dp[i - 1][j]
                s2_equal_s3 = s2[j - 1] == s3[k - 1] and dp[i][j - 1]
                dp[i][j] = s1_equal_s3 or s2_equal_s3

        return dp[m][n]