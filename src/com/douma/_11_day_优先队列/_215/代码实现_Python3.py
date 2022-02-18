from typing import List
import random
import heapq


class Solution:
    # 小顶堆 + 大顶堆
    # 时间复杂度：O(nlogk)
    # 空间复杂度：O(min(k, n - k))
    def findKthLargest1(self, nums: List[int], k: int) -> int:
        n = len(nums)
        if k < n - k:
            # 小顶堆
            pq = nums[:k]
            heapq.heapify(pq)
            for x in nums[k:]:
                heapq.heappush(pq, x)
                heapq.heappop(pq)
            return pq[0]
        else:
            # 大顶堆
            # 先将最大值变最小值，最小值变最大值
            tmp = [-x for x in nums]
            pq = tmp[:n - k + 1]
            heapq.heapify(pq)
            for x in tmp[n - k + 1:]:
                heapq.heappush(pq, x)
                heapq.heappop(pq)
            return -pq[0]

    # 快速排序分区优化
    # 时间复杂度：O(n)
    # 空间复杂度：O(1)
    def findKthLargest(self, nums: List[int], k: int) -> int:
        left, right = 0, len(nums) - 1
        target = len(nums) - k
        while True:
            pivot_index = self.partition(nums, left, right)
            if pivot_index == target:
                return nums[pivot_index]
            elif pivot_index < target:
                left = pivot_index + 1
            else:
                right = pivot_index - 1

    def partition(self, nums, lo, hi) -> None:
        i = random.randint(lo, hi)
        nums[i], nums[hi] = nums[hi], nums[i]
        pivot = nums[hi]
        less = great = lo
        while great <= hi - 1:
            if nums[great] < pivot:
                nums[less], nums[great] = nums[great], nums[less]
                less += 1
            great += 1
        nums[less], nums[hi] = nums[hi], nums[less]
        return less