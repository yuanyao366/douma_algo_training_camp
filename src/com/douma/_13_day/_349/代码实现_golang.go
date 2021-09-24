// 线性查找
// 时间复杂度：O(m*n)
// 空间复杂度：O(min(m, n))
func intersection1(nums1 []int, nums2 []int) []int {
    var set = make(map[int]bool)

    for _, num1 := range nums1 {
        // 线性查找
        for _, num2 := range nums2 {
            if num1 == num2 {
                // O(1)
                // 哈希去重
                set[num1] = true
            }
        }
    }

    var res, i = make([]int, len(set)), 0
    for num := range set {
        res[i] = num
        i++
    }
    return res
}

// 二分查找
// 时间复杂度：O((m + n)logn)
// 空间复杂度：O(min(m, n))
func intersection2(nums1 []int, nums2 []int) []int {
    var set = make(map[int]bool)

    sort.Ints(nums2) // O(nlogn)

    for _, num1 := range nums1 {
        // 二分查找 O(logn)
        if contains(nums2, num1) {
            set[num1] = true
        }
    }

    var res, i = make([]int, len(set)), 0
    for num := range set {
        res[i] = num
        i++
    }
    return res
}

func contains(nums []int, target int) bool {
    var left, right = 0, len(nums) - 1
    for left <= right {
        var mid = left + (right - left) / 2
        if target == nums[mid] {
            return true
        } else if target < nums[mid] {
            right = mid - 1
        } else {
            left = mid + 1
        }
    }
    return false
}


// 哈希查找
// 时间复杂度：O(m + n)
// 空间复杂度：O(min(m, n) + n)
func intersection3(nums1 []int, nums2 []int) []int {
    var set = make(map[int]bool)

    var set2 = make(map[int]bool)
    for _, num := range nums2 {
        set2[num] = true
    }

    for _, num1 := range nums1 {
        // 哈希查找 O(1)
        if set2[num1] {
            set[num1] = true
        }
    }

    var res, i = make([]int, len(set)), 0
    for num := range set {
        res[i] = num
        i++
    }
    return res
}


// 排序去重
// 时间复杂度：O(mlogm + nlogn)
// 空间复杂度：O(m + n)
func intersection(nums1 []int, nums2 []int) []int {
    sort.Ints(nums1)
    sort.Ints(nums2)

    var res = make([]int, min(len(nums1), len(nums2)))
    // 使用双指针去重
    var index, i, j = 0, 0, 0
    // 时间复杂度：O(max(m, n))
    for i < len(nums1) && j < len(nums2) {
        if nums1[i] == nums2[j] {
            // 保证加入元素的唯一性
            if index == 0 || res[index - 1] != nums1[i] {
                res[index] = nums1[i]
                index++
            }
            i++
            j++
        } else if nums1[i] < nums2[j] {
            i++
        } else {
            j++
        }
    }

    return res[0:index]
}

func min(a, b int) int {
    if a < b {
        return a
    }
    return b
}