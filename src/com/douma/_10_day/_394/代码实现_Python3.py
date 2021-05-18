class Solution:
    def decodeString(self, s: str) -> str:
        res = ""
        num_stack = []
        str_stack = []
        num = 0
        for c in s:
            if c <= '9' and c >= '0':
                num = num * 10 + (ord(c) - ord('0'))
            elif c == '[':
                num_stack.append(num)
                str_stack.append(res)
                num, res = 0, ""
            elif c == ']':
                item = res
                for i in range(1, num_stack[-1]):
                    res += item
                res = str_stack[-1] + res
                num_stack.pop()
                str_stack.pop()
            else:
                res += c
        return res