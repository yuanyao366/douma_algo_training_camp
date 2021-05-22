from typing import List
import random
import collections
import heapq


class Solution:
    #  小顶堆
    #  时间复杂度：O(nlogk)
    #  空间复杂度：O(n)
    def topKFrequent1(self, nums: List[int], k: int) -> List[int]:
        # 计数
        count = collections.Counter(nums)
        # 小顶堆维护 k 个元素
        pq = []
        for num, cnt in count.items():
            heapq.heappush(pq, (cnt, num))
            if len(pq) > k:
                heapq.heappop(pq)
        return [x[1] for x in pq]

    # 快速排序分区优化
    # 时间复杂度：O(n)
    # 空间复杂度：O(n)
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        # 计数
        count = collections.Counter(nums)
        nums = [(cnt, num) for num, cnt in count.items()]

        left, right = 0, len(nums) - 1
        target = len(nums) - k
        while True:
            pivot_index = self.partition(nums, left, right)
            if pivot_index == target:
                break
            elif pivot_index < target:
                left = pivot_index + 1
            else:
                right = pivot_index - 1
        return [x[1] for x in nums[-k:]]

    def partition(self, nums, lo, hi) -> None:
        i = random.randint(lo, hi)
        nums[i], nums[hi] = nums[hi], nums[i]
        pivot = nums[hi][0]
        less = great = lo
        while great <= hi - 1:
            if nums[great][0] < pivot:
                nums[less], nums[great] = nums[great], nums[less]
                less += 1
            great += 1
        nums[less], nums[hi] = nums[hi], nums[less]
        return less