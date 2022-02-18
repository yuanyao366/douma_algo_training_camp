from typing import List



class Solution:
    def readBinaryWatch(self, turnedOn: int) -> List[str]:
        nums1 = [8, 4, 2, 1]
        nums2 = [32, 16, 8, 4, 2, 1]

        def dfs(nums, count, start_index, sum, res) -> None:
            if count == 0:
                res.append(sum)
                return
            for i in range(start_index, len(nums)):
                dfs(nums, count - 1, i + 1, sum + nums[i], res)

        def gen_digits(nums, count) -> List[int]:
            res = []
            dfs(nums, count, 0, 0, res)
            return res

        res = []
        for i in range(turnedOn + 1):
            hours, minutes = gen_digits(nums1, i), gen_digits(nums2, turnedOn - i)
            for hour in hours:
                if hour > 11:
                    continue
                for minute in minutes:
                    if minute > 59:
                        continue
                    minute_str = "0" + str(minute) if minute < 10 else str(minute)
                    res.append(str(hour) + ":" + minute_str)
        return res