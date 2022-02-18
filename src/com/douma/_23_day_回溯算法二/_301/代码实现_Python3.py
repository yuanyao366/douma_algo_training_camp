from collections import deque
from typing import List



class Solution:

    def is_valid(self, s: str) -> bool:
        count = 0
        for c in s:
            if c == '(': count += 1
            elif c == ')': count -= 1
            if count < 0: return False
        return count == 0

    # BFS
    def removeInvalidParentheses1(self, s: str) -> List[str]:
        queue, visited, found, res = deque(), set(), False, []
        queue.append(s)
        visited.add(s)

        while len(queue):
            size = len(queue)
            for i in range(size):
                curr_str = queue.popleft()
                if self.is_valid(curr_str):
                    res.append(curr_str)
                    found = True
                if found: continue
                for j in range(len(curr_str)):
                    if curr_str[j] != '(' and curr_str[j] != ')':
                        continue
                    left_str = curr_str[0:j]
                    right_str = "" if j == len(curr_str) - 1 else curr_str[j + 1 : len(curr_str)]
                    next = left_str + right_str
                    if next not in visited:
                        queue.append(next)
                        visited.add(next)
            if found: break
        return res


    # DFS
    def removeInvalidParentheses(self, s: str) -> List[str]:
        res = set()

        def dfs(index, left_remove, right_remove, left_count, right_count, path) -> None:
            if index == len(s):
                if left_remove == 0 and right_remove == 0:
                    res.add(path)
                return

            c = s[index]
            if c == '(' and left_remove > 0:
                dfs(index + 1, left_remove - 1, right_remove, left_count, right_count, path)
            if c == ')' and right_remove > 0:
                dfs(index + 1, left_remove, right_remove - 1, left_count, right_count, path)

            if c != '(' and c != ')':
                dfs(index + 1, left_remove, right_remove, left_count, right_count, path + c)
            elif c == '(':
                dfs(index + 1, left_remove, right_remove, left_count + 1, right_count, path + c)
            elif right_count < left_count:
                dfs(index + 1, left_remove, right_remove, left_count, right_count + 1, path + c)

        left_remove = right_remove = 0
        for c in s:
            if c == '(':
                left_remove += 1
            elif c == ')':
                if left_remove == 0: right_remove += 1
                elif left_remove > 0: left_remove -= 1

        dfs(0, left_remove, right_remove, 0, 0, "")
        return list(res)