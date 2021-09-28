 // 线性查找(超时)
// 时间复杂度：O(n^2)
// 空间复杂度：O(1)
func containsNearbyDuplicate1(nums []int, k int) bool {
    for i := range nums {
        // 在 [0, i) 区间内线性查找 nums[i]
        for j := 0; j < i; j++ {
            if nums[i] == nums[j] && i - j <= k {
                return true
            }
        }
    }
    return false
}

// 哈希表
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func containsNearbyDuplicate2(nums []int, k int) bool {
    var indexMap = make(map[int]int)
    for i := range nums {
        // 在 [0, i) 区间内哈希查找 nums[i]
        if _, ok := indexMap[nums[i]]; ok {
            var j = indexMap[nums[i]]
            if i - j <= k {
                return true
            }
        }
        indexMap[nums[i]] = i
    }
    return false
}


// 滑动窗口
// 时间复杂度：O(n*min(n, k)) 注意：k 有可能大于 n
// 空间复杂度：O(1)
func containsNearbyDuplicate3(nums []int, k int) bool {
    var left, right = 0, 0
    for right < len(nums) {
        left = 0
        if right - k > 0 {
            left = right - k
        }

        // 窗口内线性查找
        for left < right {
            if nums[left] == nums[right] {
                return true
            }
            left++
        }

        right++
    }

    return false
}


// 滑动窗口
// 时间复杂度：O(n*min(n, k)) 注意：k 有可能大于 n
// 空间复杂度：O(1)
func containsNearbyDuplicate(nums []int, k int) bool {
    var left, right = 0, 0
    var window = make(map[int]bool)
    for right < len(nums) {
        // 窗口内哈希查找
        if window[nums[right]] {
            return true
        }
        window[nums[right]] = true

        if len(window) > k {
            delete(window, nums[left])
            left++
        }

        right++
    }

    return false
}