from typing import List


class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        if len(intervals) == 0: return []
        intervals.sort(key = lambda x: x[0])
        outputs = []
        outputs.append(intervals[0])
        for i in range(1, len(intervals)):
            last_interval = outputs[-1]
            curr_left, curr_right = intervals[i][0], intervals[i][1]
            if last_interval[1] < curr_left:
                outputs.append(intervals[i])
            else:
                last_interval[1] = max(last_interval[1], curr_right)

        return outputs