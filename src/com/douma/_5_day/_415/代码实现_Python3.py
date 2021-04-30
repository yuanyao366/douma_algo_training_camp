def addStrings(self, num1: str, num2: str) -> str:
    res = ''
    i1, i2, carry = len(num1) - 1, len(num2) - 1, 0
    while i1 >= 0 or i2 >= 0:
        x = ord(num1[i1]) - ord('0') if i1 >= 0 else 0
        y = ord(num2[i2]) - ord('0') if i2 >= 0 else 0

        sum = x + y + carry
        res += str(sum % 10)
        carry = sum // 10

        i1, i2 = i1 - 1, i2 - 1
    if carry != 0: res += str(carry)
    return res[::-1]