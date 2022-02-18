// 二分查找
// 时间复杂度：O(log(m + n))
// 空间复杂度：O(l)
func findMedianSortedArrays1(nums1 []int, nums2 []int) float64 {
    var m, n = len(nums1), len(nums2)
    var leftK, rightK = (m + n + 1) / 2, (m + n + 2) / 2
    // 将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
    var lower = getKth(nums1, nums2, leftK)
    var upper = getKth(nums1, nums2, rightK)
    return float64(lower + upper) * 0.5
}

func getKth(nums1 []int, nums2 []int, k int) int {
    var len1, len2, i, j = len(nums1), len(nums2), 0, 0
    for true {
        if i == len1 {
            return nums2[j + k - 1]
        }
        if j == len2 {
            return nums1[i + k - 1]
        }
        if k == 1 {
            return min(nums1[i], nums2[j])
        }
        var newi = min(i + (k / 2), len1) - 1
        var newj = min(j + (k / 2), len2) - 1
        if nums1[newi] < nums2[newj] {
            k = k - (newi - i + 1)
            i = newi + 1;
        } else {
            k = k - (newj - j + 1)
            j = newj + 1;
        }
    }
    // 实际上这里是不会执行到，所以返回任何值都可以
    return -1
}

func min(a, b int) int {
    if a < b {
        return a
    }
    return b
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}


// 划分数组
// 时间复杂度：O(log(m + n))
// 空间复杂度：O(l)
func findMedianSortedArrays(nums1 []int, nums2 []int) float64 {
    var m, n = len(nums1), len(nums2)
    if (m > n) {
        nums1, nums2 = nums2, nums1
        m, n = n, m
    }
    var iMin, iMax, halfLen = 0, m, (m + n + 1) / 2
    for iMin <= iMax {
        var i = iMin + (iMax - iMin) / 2
        var j = halfLen - i
        if i < iMax && nums2[j - 1] > nums1[i] {
            iMin = i + 1
        } else if i > iMin && nums1[i - 1] > nums2[j] {
            iMax = i - 1
        } else {
            // B[j - 1] <= A[i] && A[i - 1] <= B[j]
            var maxLeft = 0
            if i == 0 {
                maxLeft = nums2[j - 1]
            } else if j == 0 {
                maxLeft = nums1[i - 1]
            } else {
                maxLeft = max(nums1[i - 1], nums2[j - 1])
            }
            if (m + n) % 2 == 1 {return float64(maxLeft)}

            var minRight = 0
            if i == m {
                minRight = nums2[j]
            } else if j == n {
                minRight = nums1[i]
            } else {
                minRight = min(nums1[i], nums2[j])
            }
            return float64(maxLeft + minRight) * 0.5
        }
    }
    return 0.0
}