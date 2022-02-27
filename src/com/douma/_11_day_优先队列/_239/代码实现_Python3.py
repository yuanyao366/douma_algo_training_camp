from typing import List
import heapq
import collections


class Solution:
    # 优先队列 (大顶堆)
    # 时间复杂度：O(nlogn)
    # 空间复杂度：O(n)
    def maxSlidingWindow1(self, nums: List[int], k: int) -> List[int]:
        # python 默认是小顶堆
        """
        tips：这里维护的是大顶堆
        两个元素值不想等的话，那么元素大的放在前面，
        如果两个元素值相等的话，坐标大的放在前面，这样坐标 小于等于 i - k 的机会就会少点，这样删除的动作就会少发生了，
        其实元素相等的时候哪个放在前面，哪个放在后面，都无所谓的
        """
        pq = [(-nums[i], i) for i in range(k)]
        heapq.heapify(pq)

        ans = [-pq[0][0]]
        for i in range(k, len(nums)):
            heapq.heappush(pq, (-nums[i], i))
            while pq[0][1] <= i - k:
                heapq.heappop(pq)
            ans.append(-pq[0][0])
        return ans

    # 双端队列
    # 时间复杂度：O(n)
    # 空间复杂度：O(n)
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        ans = []
        d = collections.deque()
        for i in range(len(nums)):
            # 说明：这里的 while 可以使用 if 来代替，因为：
            # 要维护一个大小为 k 的窗口的话，每次最多只需要处理一个元素即可
            if d and d[0] <= i - k:
                d.popleft()
            while d and nums[i] > nums[d[-1]]:
                d.pop()
            d.append(i)
            if i >= k - 1:
                ans.append(nums[d[0]])
        return ans