class Solution:
    def calculate(self, s: str) -> int:
        stack = []
        pre_sign, i = '+', 0
        while i < len(s):
            if s[i] == ' ':
                i += 1
            else :
                num = 0
                while i < len(s) and s[i] <= '9' and s[i] >= '0':
                    num = num * 10 + (ord(s[i]) - ord('0'))
                    i += 1

                if pre_sign == '+':
                    stack.append(num)
                elif pre_sign == '-':
                    stack.append(-num)
                elif pre_sign == '*':
                    tmp = stack[-1]
                    stack.pop()
                    stack.append(tmp * num)
                elif pre_sign == '/':
                    tmp = stack[-1]
                    stack.pop()
                    if tmp // num < 0 and tmp % num != 0:
                        stack.append(tmp // num+1)
                    else:
                        stack.append(tmp // num)

                while i < len(s) and s[i] == ' ':
                    i += 1
                if i < len(s):
                    pre_sign = s[i]
                i += 1

        res = 0
        for i in stack:
            res += i
        return res