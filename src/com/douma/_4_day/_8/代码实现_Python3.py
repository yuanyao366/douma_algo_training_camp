def myAtoi(self, s: str) -> int:
    n = len(s)
    if n == 0: return 0

    i = 0
    while i < n and s[i] == ' ': i += 1

    sign = 1
    if i < n and (s[i] == '+' or s[i] == '-'):
        if s[i] == '-': sign = -1
        i += 1

    INT_MAX = 2 ** 31 - 1
    INT_MIN = -2 ** 31
    base = 0
    while i < n and '0' <= s[i] <= '9':
        if (base > INT_MAX // 10
                or (base == INT_MAX // 10 and ord(s[i]) - ord('0') > 7)):
            if sign > 0:
                return INT_MAX
            else:
                return INT_MIN
        base = base * 10 + ord(s[i]) - ord('0')
        i += 1

    return sign * base
