from typing import List


class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        m, n = len(nums1), len(nums2)
        left_k = (m + n + 1) // 2
        right_k = (m + n + 2) // 2
        return (self.get_k_th(nums1, nums2, left_k) + self.get_k_th(nums1, nums2, right_k)) * 0.5

    def get_k_th(self, nums1: List[int], nums2: List[int], k: int) -> int:
        len1, len2 = len(nums1), len(nums2)
        i = j = 0
        while True:
            if i == len1: return nums2[j + k - 1]
            if j == len2: return nums1[i + k - 1]
            if k == 1: return min(nums1[i], nums2[j])
            # tips：计算 newi 和 newj 需要减 1 的原因：k/2 表示的是长度，长度是从 1 开始，而下标是从 0 开始的，所以需要减 1
            newi = min(i + k // 2, len1) - 1
            newj = min(j + k // 2, len2) - 1
            # tips：而下面计算 i 和 j 的时候加 1 的原因是：就是排除 i 前面或者 j 前面的元素，所以往前走一个
            # 计算 k 的时候加 1 的原因是：用从 0 开始的下标计算从 1 开始的长度，都需要加 1 的
            if nums1[newi] < nums2[newj]:
                k = k - (newi - i + 1)
                i = newi + 1
            else:
                k = k - (newj - j + 1)
                j = newj + 1

    def findMedianSortedArrays2(self, nums1: List[int], nums2: List[int]) -> float:
        m, n = len(nums1), len(nums2)
        if m > n:
            nums1, nums2 = nums2, nums1
            m, n = n, m
        iMin, iMax, halfLen = 0, m, (m + n + 1) // 2
        while iMin <= iMax:
            i = (iMin + iMax) // 2
            j = halfLen - i
            if i < iMax and nums2[j - 1] > nums1[i]: iMin = i + 1
            elif i > iMin and nums1[i - 1] > nums2[j]: iMax = i - 1
            else:
                maxLeft = 0
                if i == 0: maxLeft = nums2[j - 1]
                elif j == 0: maxLeft = nums1[i - 1]
                else: maxLeft = max(nums1[i - 1], nums2[j - 1])
                if (m + n) % 2 == 1: return maxLeft

                minRight = 0
                if i == m: minRight = nums2[j]
                elif j == n: minRight = nums1[i]
                else: minRight = min(nums1[i], nums2[j])

                return (maxLeft + minRight) / 2
        return 0.0