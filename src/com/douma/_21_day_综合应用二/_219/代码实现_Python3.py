from typing import List


class Solution:
    # 哈希表
    # 时间复杂度：O(n)
    # 空间复杂度：O(n)
    def containsNearbyDuplicate(self, nums: List[int], k: int) -> bool:
        index_map = dict()
        for i in range(len(nums)):
            if nums[i] in index_map and i - index_map.get(nums[i]) <= k:
                return True
            index_map[nums[i]] = i
        return False


    # 滑动窗口
    # 时间复杂度：O(n)
    # 空间复杂度：O(k)
    def containsNearbyDuplicate2(self, nums: List[int], k: int) -> bool:
        left = right = 0
        window = set()
        while right < len(nums):
            if nums[right] in window:
                return True
            window.add(nums[right])
            if len(window) > k:
                window.remove(nums[left])
                left += 1
            right += 1
        return False