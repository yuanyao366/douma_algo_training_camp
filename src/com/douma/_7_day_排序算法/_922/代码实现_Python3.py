from typing import List


class Solution:
    def sortArrayByParityII(self, A: List[int]) -> List[int]:
        n, j = len(A), 1
        for i in range(0, n, 2):
            if A[i] % 2 == 1:
                while A[j] % 2 == 1: j += 2
                A[i], A[j] = A[j], A[i]
        return A