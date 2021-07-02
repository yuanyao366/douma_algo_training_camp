from typing import List


class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        res = []
        if n <= 0: return res

        def dfs(path, open, close) -> None:
            if open > n or close > open:
                return
            if len(path) == 2 * n:
                res.append(path)
                return

            dfs(path + "(", open + 1, close)
            dfs(path + ")", open, close + 1)

        dfs("", 0, 0)
        return res