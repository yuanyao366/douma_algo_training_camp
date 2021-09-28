// 等值查找
// 线性查找
// 时间复杂度：O(n^2)
// 空间复杂度：O(1)
func containsDuplicate1(nums []int) bool {
    for i := range nums {
        // 在 (i, nums.length) 区间内线性查找 nums[i]
        for j := i + 1; j < len(nums); j++ {
            if nums[i] == nums[j] {
                return true
            }
        }
    }
    return false
}

// 等值查找
// 线性查找
// 时间复杂度：O(n^2)
// 空间复杂度：O(1)
func containsDuplicate2(nums []int) bool {
    for i := range nums {
        // 在 [0, i) 区间内线性查找 nums[i]
        for j := 0; j < i; j++ {
            if nums[i] == nums[j] {
                return true
            }
        }
    }
    return false
}

// 哈希查找
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func containsDuplicate3(nums []int) bool {
    var visited = make(map[int]bool)
    for i := range nums {
        // 在 [0, i) 区间内哈希查找 nums[i]
        if visited[nums[i]] {
            return true
        }
        visited[nums[i]] = true
    }
    return false
}

// 排序优化
// 时间复杂度：O(nlogn)
// 空间复杂度：O(logn) 或者 O(n)
func containsDuplicate(nums []int) bool {
    sort.Ints(nums)
    for i := 1; i < len(nums); i++ {
        if nums[i] == nums[i - 1] {
            return true
        }
    }
    return false
}