from typing import List


def addToArrayForm(self, A: List[int], K: int) -> List[int]:
    res = []
    i, carry = len(A) - 1, 0
    while i >= 0 or K != 0:
        x = A[i] if i >= 0 else 0
        y = K % 10 if K != 0 else 0

        sum = x + y + carry
        res.append(sum % 10)
        carry = sum // 10

        i -= 1
        K //= 10
    if carry != 0: res.append(carry)
    return res[::-1]
