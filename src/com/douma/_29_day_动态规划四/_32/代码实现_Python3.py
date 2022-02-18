class Solution:
    # 1. 动态规划
    def longestValidParentheses1(self, s: str) -> int:
        n = len(s)
        if n <= 1:
            return 0
        dp = [0] * n

        dp[0] = 0
        if s[0] == '(' and s[1] == ')':
            dp[1] = 2
        ans = dp[1]

        for i in range(2, n):
            if s[i] == ')':
                if s[i - 1] == '(':
                    dp[i] = dp[i - 2] + 2
                elif i - dp[i - 1] >= 1 and s[i - dp[i - 1] - 1] == '(':
                    dp[i] = dp[i - 1] + (dp[i - dp[i - 1] - 2] if i - dp[i - 1] >= 2 else 0) + 2
                ans = max(ans, dp[i])
        return ans

    # 2. 栈
    def longestValidParentheses2(self, s: str) -> int:
        n = len(s)
        if n <= 1:
            return 0
        stack, ans = [], 0
        stack.append(-1)
        for i in range(n):
            if s[i] == '(':
                stack.append(i)
            else:
                stack.pop()
                if not stack:
                    stack.append(i)
                else:
                    ans = max(ans, i - stack[-1])
        return ans

    # 3. 双指针
    def longestValidParentheses(self, s: str) -> int:
        n = len(s)
        if n <= 1:
            return 0
        ans = left = right = 0
        for i in range(n):
            if s[i] == '(': left += 1
            elif s[i] == ')': right += 1
            if left == right:
                ans = max(ans, 2 * left)
            elif right > left:
                left = right = 0
        left = right = 0
        for i in range(n - 1, -1, -1):
            if s[i] == '(': left += 1
            elif s[i] == ')': right += 1
            if left == right:
                ans = max(ans, 2 * left)
            elif left > right:
                left = right = 0
        return ans