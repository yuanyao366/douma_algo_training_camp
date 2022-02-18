from typing import List


class Solution:
    def dietPlanPerformance(self, calories: List[int], k: int, lower: int, upper: int) -> int:
        left = right = 0
        total_score, window_total_cal = 0, 0
        while right < len(calories):
            window_total_cal += calories[right]
            if right - left + 1 == k:
                if window_total_cal < lower:
                    total_score -= 1
                elif window_total_cal > upper:
                    total_score += 1
                window_total_cal -= calories[left]
                left += 1
            right += 1
        return total_score