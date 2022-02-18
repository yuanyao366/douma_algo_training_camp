from typing import List


class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        if len(nums) < 2: return len(nums)
        lookup_table = set(nums)
        ans = 1
        for num in nums:
            # 这里会存在重复计算，为什么会产生以及如何解决，请参考 issue：
            # https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4H4RZ
            if num - 1 in lookup_table: continue
            curr_num, count = num, 1
            while curr_num + 1 in lookup_table:
                curr_num += 1
                count += 1
            ans = max(ans, count)
        return ans

    def longestConsecutive1(self, nums: List[int]) -> int:
        if len(nums) < 2: return len(nums)
        nums.sort()
        ans = count = 1
        for i in range(1, len(nums)):
            if nums[i] == nums[i - 1]: continue
            if nums[i] == nums[i - 1] + 1:
                count += 1
                ans = max(ans, count)
            else:
                count = 1
        return ans