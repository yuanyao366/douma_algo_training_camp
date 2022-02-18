from typing import List



class Solution:
    def partition(self, s: str) -> List[List[str]]:
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