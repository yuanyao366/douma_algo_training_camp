from typing import List
import heapq


class KthLargest1:

    def __init__(self, k: int, nums: List[int]):
        self.data = nums
        self.k = k
        # 普通排序
    def add(self, val: int) -> int:
        self.data.append(val)
        self.data.sort()
        return self.data[len(self.data) - self.k]

class KthLargest2:

    def __init__(self, k: int, nums: List[int]):
        self.data = nums
        self.k = k
        self.data.sort()

    # 插入排序[超时]
    def add(self, val: int) -> int:
        if len(self.data) == 0:
            self.data.append(val)
        else:
            n = len(self.data)
            self.data.append(val)
            j = n
            while j > 0:
                if val < self.data[j - 1]:
                    self.data[j] = self.data[j - 1]
                else:
                    break
                j -= 1
            self.data[j] = val

        return self.data[len(self.data) - self.k]

# 小顶堆
class KthLargest:

    def __init__(self, k: int, nums: List[int]):
        self.k = k
        self.pq = []
        for num in nums:
            self.add(num)

    def add(self, val: int) -> int:
        if len(self.pq) < self.k:
            heapq.heappush(self.pq, val)
        elif val > self.pq[0]:
            heapq.heappop(self.pq)
            heapq.heappush(self.pq, val)
        return self.pq[0]

# Your KthLargest object will be instantiated and called as such:
# obj = KthLargest(k, nums)
# param_1 = obj.add(val)