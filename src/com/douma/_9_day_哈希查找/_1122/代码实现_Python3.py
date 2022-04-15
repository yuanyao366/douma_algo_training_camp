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
            # 清 0 的原因：
            # 在 arr1 中等于 num 的所有的元素都放在一起了
            # 也就是 num 排好序了，为了可以区分出已经排好序，和还没排序的元素
            # 我们将排好序的元素出现的次数清 0
            count[num] = 0
            # 清 0 后，在下面的循环中就不用处理了，下面的循环只要处理在 arr2 中没出现的元素了

        for num in range(max_num + 1):
            if count[num] > 0:
                ans.extend([num] * count[num])

        return ans