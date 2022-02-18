def romanToInt(self, s: str) -> int:
    values = {'I': 1, 'V': 5, 'X': 10, 'L': 50, 'C': 100, 'D': 500, 'M': 1000}
    sum, preNum = 0, values.get(s[0], 0)
    for i in range(1, len(s)):
        num = values.get(s[i], 0)
        if preNum < num:
            sum -= preNum
        else:
            sum += preNum
        preNum = num
    sum += preNum
    return sum
