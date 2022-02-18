from typing import List
import random
import heapq


# 普通排序
class MedianFinder1:

    def __init__(self):
        """
        initialize your data structure here.
        """
        self.data = []


    def addNum(self, num: int) -> None:
        self.data.append(num)

    def findMedian(self) -> float:
        self.data.sort()
        n = len(self.data)
        if n % 2 == 1:
            return self.data[n // 2]
        else:
            return (self.data[n // 2 - 1] + self.data[n // 2]) * 0.5

# 插入排序
class MedianFinder2:

    def __init__(self):
        """
        initialize your data structure here.
        """
        self.data = []


    def addNum(self, num: int) -> None:
        if not len(self.data):
            self.data.append(num)
        else:
            n = len(self.data)
            self.data.append(num)
            j = n
            while j > 0:
                if num < self.data[j - 1]:
                    self.data[j] = self.data[j - 1]
                else:
                    break
                j -= 1
            self.data[j] = num

    def findMedian(self) -> float:
        n = len(self.data)
        if n % 2 == 1:
            return self.data[n // 2]
        else:
            return (self.data[n // 2 - 1] + self.data[n // 2]) * 0.5

#3 大顶堆 + 小顶堆
class MedianFinder3:

    def __init__(self):
        """
        initialize your data structure here.
        """
        self.min_heap = []
        self.max_heap = []


    def addNum(self, num: int) -> None:
        if not len(self.max_heap):
            heapq.heappush(self.max_heap, -num)
            return
        if num <= -self.max_heap[0]:
            heapq.heappush(self.max_heap, -num)
        else:
            heapq.heappush(self.min_heap, num)

        if len(self.max_heap) > len(self.min_heap) + 1:
            heapq.heappush(self.min_heap, -self.max_heap[0])
            heapq.heappop(self.max_heap)
        if len(self.max_heap) < len(self.min_heap):
            heapq.heappush(self.max_heap, -self.min_heap[0])
            heapq.heappop(self.min_heap)

    def findMedian(self) -> float:
        if len(self.max_heap) > len(self.min_heap):
            return -self.max_heap[0]
        else:
            return (-self.max_heap[0] + self.min_heap[0]) * 0.5

# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()