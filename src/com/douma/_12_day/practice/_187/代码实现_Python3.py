from typing import List


class Solution:
    # 参考【课程 A 中应用篇】的字符串匹配 RK 算法，使用滚动哈希方法
    # 时间复杂度：O(n)
    def findRepeatedDnaSequences(self, s: str) -> List[str]:
        if len(s) <= 10: return []

        k = 10
        # 将 ACGT 看成 4 进制
        to_int = {'A': 0, 'C': 1, 'G': 2, 'T': 3}
        base = 4
        shift_left = pow(base, k - 1)

        seen, output = set(), set()

        curr_window_hash = 0
        # 计算第一个窗口对应的 hash 值
        for i in range(k):
            curr_window_hash = curr_window_hash * base + to_int[s[i]]
        seen.add(curr_window_hash)

        # 从第二个窗口开始
        left, right = 1, k
        while right < len(s):
            # 删除 s[left - 1]
            curr_window_hash = curr_window_hash - to_int[s[left - 1]] * shift_left
            # 添加 s[right]
            curr_window_hash = curr_window_hash * base + to_int[s[right]]
            if curr_window_hash in seen:
                output.add(s[left:right + 1])
            else:
                seen.add(curr_window_hash)
            left, right = left + 1, right + 1

        return list(output)