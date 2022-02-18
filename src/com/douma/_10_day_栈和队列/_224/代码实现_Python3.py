class Solution:
    def calculate(self, s: str) -> int:
        stack = []
        sign, num, res = 1, 0, 0
        for c in s:
            if c <= '9' and c >= '0':
                num = num * 10 + (ord(c) - ord('0'))
            elif c == '+':
                res += sign * num
                sign, num = 1, 0
            elif c == '-':
                res += sign * num
                sign, num = -1, 0
            elif c == '(':
                stack.append(res)
                stack.append(sign)
                sign, res = 1, 0
            elif c == ')':
                res += sign * num
                res *= stack[-1]
                stack.pop()
                res += stack[-1]
                stack.pop()
                num = 0
        return res + sign * num