from bisect import bisect
from typing import List


class Solution:
    def maxEnvelopes(self, envelopes: List[List[int]]) -> int:
        envelopes.sort(key=lambda x: (x[0], -x[1]))
        n = len(envelopes)
        dp = [1] * n
        res = 1
        for j in range(1, n):
            for i in range(0, j):
                if envelopes[j][1] > envelopes[i][1]:
                    dp[j] = max(dp[i] + 1, dp[j])
                    res = max(res, dp[j])

        return res

    """
    俄罗斯套娃这道题的输入改掉了，之前的输入数据规模是 5000 ，所以时间复杂度为 O(n^2) 的动态规划方案是可以的

    现在的输入数据规模改为 10^5 ，所以，时间复杂度为 O(n^2) 会超时了
    现在题目的输入数据规模为：
    1 <= envelopes.length <= 10^5
    envelopes[i].length == 2
    1 <= wi, hi <= 10^5
    
    // 所以需要使用二分解法
    // 时间复杂度 O(nlogn)
    // 空间复杂度 O(n)
    """
    def maxEnvelopes(self, envelopes: List[List[int]]) -> int:
        # 先按照宽度升序排列，宽度相同的话，按照高度降序排列
        envelopes.sort(key=lambda x: (x[0], -x[1]))

        # res 用于存储所有可套娃信封的高度值
        # 保证 res 中的元素肯定是升序排列的
        n, res = len(envelopes), []

        # 1. 将宽度最小的信封的高度值放在 res 中
        res.append(envelopes[0][1])

        # 2. 从第二个信封开始，遍历并处理每一个信封
        for i in range(1, n):
            curr_height = envelopes[i][1]
            # 2.1 如果当前信封的高度大于 res 中最后一个信封的高度
            if curr_height > res[len(res) - 1]:
                # 那么，可以将当前的信封放入到 res 中
                # (当前信封的宽度肯定大于等于果集中最后一个信封的宽度，因为是按照宽度升序排列的)
                res.append(curr_height)
            else: # 2.2 当前信封的高度小于等于 res 中最后一个信封的高度
                # 将当前信封插入到 res 合适的位置上
                # 先使用二分查找，在 res 中查找 currHeight 合适的位置
                index = bisect.bisect_left(res, curr_height)
                # 将当前信封替换之前这个位置上的信封
                # 之所以可以将当前信封替换掉第 index 处的信封，是因为：
                # ① 当前信封的宽度大于等于第 index - 1 处的信封的宽度
                # ② 当前信封的高度大于第 index - 1 处的信封的高度
                res[index] = curr_height

        return len(res)