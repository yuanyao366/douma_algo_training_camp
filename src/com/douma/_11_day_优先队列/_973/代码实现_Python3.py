from typing import List
import heapq
import random


class Solution:
    # 大顶堆
    def kClosest1(self, points: List[List[int]], k: int) -> List[List[int]]:
        pq = [(-x ** 2 - y ** 2, i) for i, (x, y) in enumerate(points[:k])]
        heapq.heapify(pq)
        for i in range(k, len(points)):
            x, y = points[i]
            dist = -x ** 2 - y ** 2
            heapq.heappushpop(pq, (dist, i))
        return [points[i] for (_, i) in pq]

    # 快速排序分区优化
    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
        left, right = 0, len(points) - 1
        target = len(points) - k
        while True:
            pivot_index = self.partition(points, left, right)
            if pivot_index == target:
                break
            elif pivot_index < target:
                left = pivot_index + 1
            else:
                right = pivot_index - 1
        return [x for x in points[-k:]]

    def partition(self, nums, lo, hi) -> None:
        i = random.randint(lo, hi)
        nums[i], nums[hi] = nums[hi], nums[i]
        pivot = nums[hi][0] ** 2 + nums[hi][1] ** 2
        less = great = lo
        while great <= hi - 1:
            num = nums[great][0] ** 2 + nums[great][1] ** 2
            # 降序排列
            if num >= pivot:
                nums[less], nums[great] = nums[great], nums[less]
                less += 1
            great += 1
        nums[less], nums[hi] = nums[hi], nums[less]
        return less