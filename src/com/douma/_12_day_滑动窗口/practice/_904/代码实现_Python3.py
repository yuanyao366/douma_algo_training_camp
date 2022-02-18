from typing import List


class Solution:
    def totalFruit(self, tree: List[int]) -> int:
        res = left = right = 0
        couter = {}
        while right < len(tree):
            if tree[right] in couter:
                couter[tree[right]] += 1
            else:
                couter[tree[right]] = 1
            while len(couter) > 2:
                couter[tree[left]] -= 1
                if couter[tree[left]] == 0:
                    del couter[tree[left]]
                left += 1
            res = max(res, right - left + 1)
            right += 1
        return res