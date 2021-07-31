class Solution:
    # 1. 记忆化搜索
    def numDecodings1(self, s: str) -> int:
        n = len(s)
        memo = [-1] * (n + 1)

        # 以第 i 个字符开头的子串能解码的个数
        def dfs(i) -> int:
            if i == n:
                return 1
            if memo[i] != -1:
                return memo[i]

            if s[i] != '0':
                ans1, ans2 = dfs(i + 1), 0
                if i < n - 1:
                    one = ord(s[i + 1]) - ord('0')
                    two = (ord(s[i]) - ord('0')) * 10
                    if one + two <= 26:
                        ans2 = dfs(i + 2)
                memo[i] = ans1 + ans2
            else:
                memo[i] = 0

            return memo[i]

        return dfs(0)

    # 2. 动态规划(从右往左)
    def numDecodings2(self, s: str) -> int:
        n = len(s)
        # dp[i]：表示以第 i 个字符开头的子串能解码的个数
        dp = [0] * (n + 1)
        dp[n] = 1
        for i in range(n - 1, -1, -1):
            if s[i] != '0':
                dp[i] += dp[i + 1]
                if i < n - 1:
                    one = ord(s[i + 1]) - ord('0')
                    two = (ord(s[i]) - ord('0')) * 10
                    if one + two <= 26:
                        dp[i] += dp[i + 2]
        return dp[0]

    # 3. 记忆化搜索
    def numDecodings3(self, s: str) -> int:
        n = len(s)
        memo = [-1] * (n + 1)

        # 以第 i 个字符结尾的子串能解码的个数
        def dfs(i) -> int:
            if i == 0:
                return 1
            if memo[i] != -1:
                return memo[i]

            ans1 = ans2 = 0
            if s[i - 1] != '0':
                ans1 = dfs(i - 1)
            if i > 1 and s[i - 2] != '0':
                one = ord(s[i - 1]) - ord('0')
                two = (ord(s[i - 2]) - ord('0')) * 10
                if one + two <= 26:
                    ans2 = dfs(i - 2)

            memo[i] = ans1 + ans2

            return memo[i]

        return dfs(n)

    # 4. 动态规划(从左往右)
    def numDecodings(self, s: str) -> int:
        n = len(s)
        dp = [0] * (n + 1)
        dp[0] = 1
        for i in range(1, n + 1):
            if s[i - 1] != '0':
                dp[i] += dp[i - 1]
            if i > 1 and s[i - 2] != '0':
                one = ord(s[i - 1]) - ord('0')
                two = (ord(s[i - 2]) - ord('0')) * 10
                if one + two <= 26:
                    dp[i] += dp[i - 2]
        return dp[n]