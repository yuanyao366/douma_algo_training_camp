from typing import List


class Solution:
    # 贪心策略：碰到相同字母的时候，我们贪心的删除删除成本最小的字符，也可以说保留删除成本最大的字母
    def minCost(self, s: str, cost: List[int]) -> int:
        res, length, i = 0, len(s), 0
        while i < length:
            c = s[i]
            max_cost = sum_cost = 0
            while i < length and s[i] == c:
                max_cost = max(max_cost, cost[i])
                sum_cost += cost[i]
                i += 1
            res += (sum_cost - max_cost)
        return res