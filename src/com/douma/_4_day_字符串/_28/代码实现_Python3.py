from typing import List


# 字符串匹配算法：暴力解法、RK 算法、BM 算法、KMP 算法
# 详细参见：课程 A：应用篇 -> 字符串匹配

# 暴力
# 时间复杂度：O(m*n)
# 空间复杂度：O(1)
def strStr(self, haystack: str, needle: str) -> int:
    m, n = len(haystack), len(needle)
    if n == 0: return 0
    if m < n: return -1
    for i in range(m - n + 1):
        if haystack[i] == needle[0] and haystack[i: i + n] == needle:
            return i
    return -1


# KMP
# 时间复杂度：O(m + n)
# 空间复杂度：O(n)
def strStr(self, haystack: str, needle: str) -> int:
    m, n = len(haystack), len(needle)
    if n == 0: return 0
    if m < n: return -1

    nexts, j = self.get_nexts(needle), 0
    for i in range(m):
        while j > 0 and haystack[i] != needle[j]:
            j = nexts[j - 1] + 1
        if haystack[i] == needle[j]: j += 1
        if j == n: return i - n + 1
    return -1


def get_nexts(self, needle: str) -> List[int]:
    n = len(needle)
    if n == 1: return []
    nexts = [0] * (n - 1)
    nexts[0] = -1
    for j in range(1, n - 1):
        pre = nexts[j - 1]
        while pre != -1 and needle[pre + 1] != needle[j]:
            pre = nexts[pre]
        if needle[pre + 1] == needle[j]: pre += 1
        nexts[j] = pre
    return nexts
