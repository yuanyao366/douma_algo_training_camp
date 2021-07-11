class Solution:
    # 栈
    def removeDuplicates(self, s: str, k: int) -> str:
        stack = []
        for c in s:
            if not len(stack) or stack[-1][0] != c:
                stack.append([c, 1])
            else:
                stack[-1][1] += 1
                if stack[-1][1] == k:
                    stack.pop()

        res = ""
        for pair in stack:
            for i in range(pair[1]):
                res += pair[0]
        return res


    # 快慢指针
    def removeDuplicates2(self, s: str, k: int) -> str:
        chars, count = list(s), []
        slow = fast = 0
        while fast < len(s):
            chars[slow] = chars[fast]
            if slow == 0 or chars[slow] != chars[slow - 1]:
                count.append(1)
            else:
                incremented = count.pop() + 1
                if incremented == k:
                    slow = slow - k
                else:
                    count.append(incremented)
            slow, fast = slow + 1, fast + 1
        return ''.join(chars[0:slow])