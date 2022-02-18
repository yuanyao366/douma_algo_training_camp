def repeatedSubstringPattern(self, s: str) -> bool:
    """
    字符串 s 拼接的目的：可以在 s + s 中找到 s 的所有旋转后的字符串
    拼接后的字符串的头部是旋转了一圈的字符串，而尾部是没有旋转的字符串，所以需要去掉头部和尾部

    从 1 开始匹配的作用就是去掉头部

    不等于 s.length 的作用就是去掉尾部
    """
    return (s + s).find(s, 1) != len(s)


def repeatedSubstringPattern1(self, s: str) -> bool:
    n = len(s)
    for len in range(1, n // 2 + 1):
        if n % len == 0:
            if all(s[j] == s[j - len] for j in range(len, n)):
                return True
    return False
