from typing import List


class Solution:
    def sortArrayByParity(self, A: List[int]) -> List[int]:
        less, great = 0, len(A) - 1
        while less < great:
            if A[less] % 2 > A[great] % 2:
                A[less], A[great] = A[great], A[less]
            if A[less] % 2 == 0: less += 1
            if A[great] % 2 != 0: great -= 1
        return A