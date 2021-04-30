def addTwoNum(self, num1: List[int], num2: List[int]) -> List[int]:
    res = []
    i1, i2, carry = len(num1) - 1, len(num2) - 1, 0
    while i1 >= 0 or i2 >= 0:
        x = num1[i1] if i1 >= 0 else 0
        y = num2[i2] if i2 >= 0 else 0

        sum = x + y + carry
        res.append(sum % 10)
        carry = sum // 10

        i1, i2 = i1 - 1, i2 - 1
    if carry != 0: res.append(carry)
    return res[::-1]