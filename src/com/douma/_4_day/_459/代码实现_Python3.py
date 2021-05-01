def repeatedSubstringPattern(self, s: str) -> bool:
    return (s + s).find(s, 1) != len(s)


def repeatedSubstringPattern1(self, s: str) -> bool:
    n = len(s)
    for i in range(1, n // 2 + 1):
        if n % i == 0:
            if all(s[j] == s[j - i] for j in range(i, n)):
                return True
    return False
