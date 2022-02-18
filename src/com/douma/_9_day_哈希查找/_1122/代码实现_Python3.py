from typing import List


class Solution:
    def relativeSortArray1(self, arr1: List[int], arr2: List[int]) -> List[int]:
        num_index_map = {x: i for i, x in enumerate(arr2)}

        def mycmp(x: int) -> (int, int):
            return (0, num_index_map[x]) if x in num_index_map else (1, x)

        arr1.sort(key=mycmp)
        return arr1

    def relativeSortArray(self, arr1: List[int], arr2: List[int]) -> List[int]:
        max_num = max(arr1)
        count = [0] * (max_num + 1)
        for num in arr1:
            count[num] += 1

        ans = list()
        for num in arr2:
            ans.extend([num] * count[num])
            count[num] = 0

        for num in range(max_num + 1):
            if count[num] > 0:
                ans.extend([num] * count[num])

        return ans