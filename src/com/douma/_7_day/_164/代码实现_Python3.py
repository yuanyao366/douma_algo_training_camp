from typing import List
import math


class Solution:

    # 桶排序
    # 时间复杂度：O(n)
    # 空间复杂度：O(n)
    def maximumGap(self, nums: List[int]) -> int:
        if len(nums) < 2: return 0
        INT_MAX, INT_MIN = 2147483647, -2147483648

        # 1. 找到最大最小值
        min_val, max_val = min(nums), max(nums)
        if min_val == max_val: return 0

        gap = int(math.ceil((max_val - min_val) / (len(nums) - 1)))

        # 2. 初始化桶数组
        bucket_num = len(nums)
        buckets = [[INT_MAX, INT_MIN] for _ in range(bucket_num)]

        # 3. 将所有元素添加到对应的桶中
        for num in nums:
            # bucketId 计算逻辑如何理解，请参考 issue：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I498BD
            bucket_id = (num - min_val) // gap
            buckets[bucket_id][0] = min(buckets[bucket_id][0], num)
            # bug 修复：这是是求最大值，所以需要用 max 函数
            buckets[bucket_id][1] = max(buckets[bucket_id][1], num)

        # 4. 计算最大间隔
        max_gap, prev = 0, min_val
        for bucket in buckets:
            if bucket[0] == INT_MAX: continue
            max_gap = max(max_gap, bucket[0] - prev)
            prev = bucket[1]

        return max_gap