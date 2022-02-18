from typing import List


class Solution:
    # 滑动窗口 + 桶
    # 时间复杂度：O(n)
    # 空间复杂度：O(min(n, k))
    def containsNearbyAlmostDuplicate(self, nums: List[int], k: int, t: int) -> bool:

        def get_bucket_id(num, bucket_size) -> int:
            if num >= 0:
                return num // bucket_size
            return ((num + 1) // bucket_size) - 1

        left = right = 0
        bucket_size, window_bucket = t + 1, dict()

        while right < len(nums):
            x = nums[right]
            bucket_id = get_bucket_id(x, bucket_size)

            if bucket_id in window_bucket:
                return True

            left_bucket_id, right_bucket_id = bucket_id - 1, bucket_id + 1
            if left_bucket_id in window_bucket and x - window_bucket[left_bucket_id] <= t:
                return True
            if right_bucket_id in window_bucket and window_bucket[right_bucket_id] - x <= t:
                return True

            window_bucket[bucket_id] = x

            if len(window_bucket) > k:
                del window_bucket[get_bucket_id(nums[left], bucket_size)]
                left += 1

            right += 1

        return False