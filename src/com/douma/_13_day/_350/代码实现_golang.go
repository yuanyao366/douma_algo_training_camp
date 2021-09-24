// 哈希查找
// 时间复杂度：O(m + n)
// 空间复杂度：O(min(m, n) + n)
func intersect1(nums1 []int, nums2 []int) []int {
    var countMap = make(map[int]int)
    for _, num := range nums2 {
        countMap[num]++
    }

    var res = make([]int, min(len(nums1), len(nums2)))
    var index = 0
    for _, num := range nums1 {
        if countMap[num] > 0 {
            res[index] = num
            index++
            countMap[num]--
        }
    }

    return res[0:index]
}


// 二分查找
// 时间复杂度：O(mlogm + nlogn)
// 空间复杂度：O(m + n)
func intersect2(nums1 []int, nums2 []int) []int {
    sort.Ints(nums1)
    sort.Ints(nums2)

    var res = make([]int, min(len(nums1), len(nums2)))
    var index, i = 0, 0
    // 时间复杂度：
    // 没有重复元素：O(mlogn)
    // 全部是重复元素：O(m + n)
    for i < len(nums1) {
        var lowerBound = searchFirstTargetIndex(nums2, nums1[i])
        if lowerBound == -1 {
            i++
            continue
        }
        // 处理相同的元素
        // 在 nums2 中找到相同元素的个数
        var count = 0
        for lowerBound < len(nums2) && nums2[lowerBound] == nums1[i] {
            count++
            lowerBound++
        }

        // 在 nums1 中找到相同元素的个数
        var j = i
        for j < len(nums1) && nums1[j] == nums1[i] {
            j++
            if count > 0 {
                res[index] = nums1[i]
                index++
                count--
            }
        }
        i = j
    }

    return res[0:index]
}

func searchFirstTargetIndex(nums []int, target int) int {
    var left, right = 0, len(nums) - 1
    for left < right {
        var mid = left + (right - left) / 2
        if target > nums[mid] {
            left = mid + 1
        } else {
            right = mid
        }
    }
    if nums[left] == target {
        return left
    }
    return -1
}

// 排序去重
// 时间复杂度：O(mlogm + nlogn)
// 空间复杂度：O(m + n)
func intersect(nums1 []int, nums2 []int) []int {
    sort.Ints(nums1)
    sort.Ints(nums2)

    var res = make([]int, min(len(nums1), len(nums2)))
    // 使用双指针去重
    var index, i, j = 0, 0, 0
    // 时间复杂度：O(max(m, n))
    for i < len(nums1) && j < len(nums2) {
        if nums1[i] == nums2[j] {
            res[index] = nums1[i]
            index++
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