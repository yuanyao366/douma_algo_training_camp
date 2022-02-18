from typing import List


class Solution:
    # 1. 纵向扫描
    # 时间复杂度：O(mn) m 表示字符串数组中所有字符串的平均长度，n 表示字符串数组的大小
    # 空间复杂度：O(1)
    def longestCommonPrefix1(self, strs: List[str]) -> str:
        if len(strs) == 0:
            return ""

        rows, cols = len(strs), len(strs[0])
        for i in range(cols):
            for j in range(rows):
                if len(strs[j]) == i or strs[j][i] != strs[0][i]:
                    return strs[0][:i]

        return strs[0]

    # 2. 横向扫描
    # 时间复杂度：O(mn) m 表示字符串数组中所有字符串的平均长度，n 表示字符串数组的大小
    # 空间复杂度：O(1)
    def longestCommonPrefix2(self, strs: List[str]) -> str:
        if len(strs) == 0:
            return ""
        prefix = strs[0]
        for i in range(1, len(strs)):
            prefix = self.longestCommonPrefixHelp(prefix, strs[i])
            if len(prefix) == 0: return ""
        return prefix

    def longestCommonPrefixHelp(self, str1, str2) -> str:
        length, index = min(len(str1), len(str2)), 0
        while index < length and str1[index] == str2[index]:
            index += 1
        return str1[:index]

    # 3. 分治思想
    # 时间复杂度：O(mn) m 表示字符串数组中所有字符串的平均长度，n 表示字符串数组的大小
    # 空间复杂度：O(mlogn) 空间复杂度主要取决于递归调用的层数，层数最大为 logn，每层需要 m 的空间存储返回结果。
    def longestCommonPrefix(self, strs: List[str]) -> str:
        if len(strs) == 0:
            return ""

        def lcp(strs, left, right) -> str:
            if left == right:
                return strs[left]
            mid = left + (right - left) // 2
            left_lcp, right_lcp = lcp(strs, left, mid), lcp(strs, mid + 1, right)
            return self.longestCommonPrefixHelp(left_lcp, right_lcp)

        return lcp(strs, 0, len(strs) - 1)