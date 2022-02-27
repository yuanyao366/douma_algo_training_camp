/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */

var findMedianSortedArrays = function(nums1, nums2) {
    const m = nums1.length, n = nums2.length
    const leftK = Math.floor((m + n + 1) / 2)
    const rightK = Math.floor((m + n + 2) / 2)
    return (getKth(nums1, nums2, leftK) + getKth(nums1, nums2, rightK)) * 0.5
}

var getKth = function(nums1, nums2, k) {
    const len1 = nums1.length, len2 = nums2.length
    let i = 0, j = 0
    while (true) {
        if (i == len1) return nums2[j + k - 1]
        if (j == len2) return nums1[i + k - 1]
        if (k == 1) return Math.min(nums1[i], nums2[j])
        // tips：计算 newi 和 newj 需要减 1 的原因：k/2 表示的是长度，长度是从 1 开始，而下标是从 0 开始的，所以需要减 1
        const newi = Math.min(i + Math.floor(k / 2), len1) - 1
        const newj = Math.min(j + Math.floor(k / 2), len2) - 1
        // tips：而下面计算 i 和 j 的时候加 1 的原因是：就是排除 i 前面或者 j 前面的元素，所以往前走一个
        // 计算 k 的时候加 1 的原因是：用从 0 开始的下标计算从 1 开始的长度，都需要加 1 的
        if (nums1[newi] < nums2[newj]) {
            k = k - (newi - i + 1)
            i = newi + 1
        } else {
            k = k - (newj - j + 1)
            j = newj + 1
        }
    }
}

// 划分数组
var findMedianSortedArrays2 = function(nums1, nums2) {
    let m = nums1.length, n = nums2.length
    if (m > n) {
        const tmpArr = nums1; nums1 = nums2; nums2 = tmpArr
        const tmp = m; m = n; n = tmp
    }

    let iMin = 0, iMax = m
    const halfLen = Math.floor((m + n + 1) / 2)
    while (iMin <= iMax) {
        const i = Math.floor((iMin + iMax) / 2)
        const j = halfLen - i
        if (i < iMax && nums2[j - 1] > nums1[i]) iMin = i + 1
        else if (i > iMin && nums1[i - 1] > nums2[j]) iMax = i - 1
        else {
            let maxLeft
            if (i == 0) maxLeft = nums2[j - 1]
            else if (j == 0) maxLeft = nums1[i - 1]
            else maxLeft = Math.max(nums1[i - 1], nums2[j - 1])
            if ((m + n) % 2 ==  1) return maxLeft

            let minRight
            if (i == m) minRight = nums2[j]
            else if (j == n) minRight = nums1[i]
            else minRight = Math.min(nums1[i], nums2[j])

            return (maxLeft + minRight) / 2
        }
    }
    return 0.0
};