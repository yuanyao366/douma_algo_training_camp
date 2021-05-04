def repeatedSubstringPattern(self, s: str) -> bool:
    return (s + s).find(s, 1) != len(s)


def repeatedSubstringPattern1(self, s: str) -> bool:
    n = len(s)
    for len in range(1, n // 2 + 1):
        if n % len == 0:
            if all(s[j] == s[j - len] for j in range(len, n)):
                return True
    return False
