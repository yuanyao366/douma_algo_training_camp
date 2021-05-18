class Solution:
    def isValid1(self, s: str) -> bool:
        if len(s) % 2 == 1: return False
        stack = list()
        for c in s:
            if c == '(' or c == '{' or c == '[':
                stack.append(c)
            else:
                if not stack:
                    return False
                top_char = stack[-1]
                stack.pop()
                if c == ')' and top_char != '(': return False
                if c == ']' and top_char != '[': return False
                if c == '}' and top_char != '{': return False
        return not stack

    def isValid(self, s: str) -> bool:
        if len(s) % 2 == 1: return False

        pairs = {
            "(": ")",
            "[": "]",
            "{": "}",
        }

        stack = list()
        for c in s:
            if c in pairs:
                stack.append(c)
            else:
                if not stack:
                    return False
                top_char = stack[-1]
                if c != pairs[top_char]: return False
                stack.pop()
        return not stack


def isValid(self, s: str) -> bool:
    if len(s) % 2 == 1: return False
    stack = list()
    for c in s:
        if c == '(':
            stack.append(c)
        else:
            if not stack:
                return False
            stack.pop()
    return not stack
