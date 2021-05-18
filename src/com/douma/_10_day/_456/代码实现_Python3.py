from typing import List


class Solution:
    # 1. 单变量维护最小值 + 线性查找 k
    # 时间复杂度：O(n^2)
    def find132pattern1(self, nums: List[int]) -> bool:
        n = len(nums)
        if n < 3: return False

        numsi = nums[0]
        for j in range(1, n):
            for k in range(j + 1, n):
                if numsi < nums[k] < nums[j]:
                    return True
            numsi = min(numsi, nums[j])

        return False

    # 2. 单变量维护最小值 + 二叉查找树查找 k
    # 时间复杂度：O(nlogn)
    def find132pattern3(self, nums: List[int]) -> bool:
        n = len(nums)
        if n < 3: return False

        numsi = nums[0]
        # 有序集合查找
        from sortedcontainers import SortedList
        right_all = SortedList(nums[2:])

        for j in range(1, n - 1):
            if nums[j] > numsi:
                k = right_all.bisect_right(numsi)
                if k < len(right_all) and right_all[k] < nums[j]:
                    return True
            numsi = min(numsi, nums[j])
            right_all.remove(nums[j + 1])

        return False


    # 3. 预计算前缀最小值 + 单调栈
    # 时间复杂度：O(n)
    def find132pattern3(self, nums: List[int]) -> bool:
        n = len(nums)
        if n < 3: return False

        min_prefix = [0] * n
        min_prefix[0] = nums[0]
        for i in range(1, n):
            min_prefix[i] = min(min_prefix[i - 1], nums[i])

        stack = []
        stack.append(nums[n - 1])
        for j in range(n - 2, 0, -1):
            if nums[j] > min_prefix[j]:
                while stack and stack[-1] <= min_prefix[j]:
                    stack.pop()
                if stack and stack[-1] < nums[j]:
                    return True
                stack.append(nums[j])
        return False

    # 4. 单调栈
    # 时间复杂度：O(n)
    def find132pattern(self, nums: List[int]) -> bool:
        n = len(nums)
        if n < 3: return False
        maxk = float("-inf")
        stack = []
        stack.append(nums[n - 1])
        for i in range(n - 2, -1, -1):
            if nums[i] < maxk: return True
            while stack and nums[i] > stack[-1]:
                maxk = stack[-1]
                stack.pop()
            if nums[i] > maxk: stack.append(nums[i])
        return False