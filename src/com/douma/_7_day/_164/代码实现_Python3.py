from typing import List
import math


class Solution:

    def maximumGap(self, nums: List[int]) -> int:
        if len(nums) < 2: return 0
        INT_MAX, INT_MIN = 2147483647, -2147483648

        min_val, max_val = min(nums), max(nums)
        if min_val == max_val: return 0

        gap = int(math.ceil((max_val - min_val) / (len(nums) - 1)))

        bucket_num = len(nums)
        buckets = [[INT_MAX, INT_MIN] for _ in range(bucket_num)]

        for num in nums:
            bucket_id = (num - min_val) // gap
            buckets[bucket_id][0] = min(buckets[bucket_id][0], num)
            buckets[bucket_id][1] = max(buckets[bucket_id][1], num)

        max_gap, prev = 0, min_val
        for bucket in buckets:
            if bucket[0] == INT_MAX: continue
            max_gap = max(max_gap, bucket[0] - prev)
            prev = bucket[1]

        return max_gap