from typing import List


class Solution:
    # 方法二： 使用动态规划优化判断是否是回文串
    def partition(self, s: str) -> List[List[str]]:

        # 定义状态，dp[i][j] 表示子数组区间 [i, j] 对应的子串是否是回文
        dp = [[False] * len(s) for _ in range(len(s))]
        # 状态初始化, 一个字符，肯定是回文
        for i in range(len(s)):
            dp[i][i] = True
        # 状态转移
        for j in range(1, len(s)):
            for i in range(j):
                is_char_equal = s[i] == s[j]
                if j - i == 1:
                    # 只有两个字符
                    dp[i][j] = is_char_equal
                else:
                    dp[i][j] = is_char_equal and dp[i + 1][j - 1]

        res = []
        def dfs(start_index, path) -> None:
            if start_index == len(s):
                res.append(path[:])
                return
            for i in range(start_index, len(s)):
                if not dp[start_index][i]:
                    continue
                path.append(s[start_index:i + 1])
                dfs(i + 1, path)
                path.pop()

        dfs(0, [])
        return res

    # 方法一
    def partition1(self, s: str) -> List[List[str]]:
        res = []

        def check_palindrome(str, left, right) -> bool:
            while left < right:
                if str[left] != str[right]:
                    return False
                left, right = left + 1, right - 1
            return True

        def dfs(start_index, path) -> None:
            if start_index == len(s):
                res.append(path[:])
                return
            for i in range(start_index, len(s)):
                if not check_palindrome(s, start_index, i):
                    continue
                path.append(s[start_index:i + 1])
                dfs(i + 1, path)
                path.pop()

        dfs(0, [])
        return res