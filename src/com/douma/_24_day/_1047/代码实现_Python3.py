class Solution:
    # 栈
    def removeDuplicates(self, s: str) -> str:
        stack = []
        for c in s:
            if len(stack) and stack[-1] == c:
                stack.pop()
            else:
                stack.append(c)
        return "".join(stack)

    # 快慢指针
    def removeDuplicates2(self, s: str) -> str:
        chars = list(s)
        slow, fast = -1, 0
        while fast < len(s):
            if slow >= 0 and chars[slow] == chars[fast]:
                slow -= 1
            else:
                slow += 1
                chars[slow] = chars[fast]
            fast += 1
        return "".join(chars[0:slow + 1])