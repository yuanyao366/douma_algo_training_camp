// 哈希查找
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func firstMissingPositive1(nums []int) int {
    var set = make(map[int]bool)
    for i := range nums {
        set[nums[i]] = true
    }

    for i := 1; i <= len(nums); i++ {
        if !set[i] {
            return i
        }
    }

    return len(nums) + 1
}

// 优化
// 时间复杂度：O(n)
// 空间复杂度：O(1)
func firstMissingPositive(nums []int) int {
    var n = len(nums)
    // 1. 将小于 0 的元素变成正数
    for i := range nums {
        if nums[i] <= 0 {
            nums[i] = n + 1
        }
    }

    // 2. 将索引为 nums[i] 的元素设置为负数
    for i := range nums {
        var num = abs(nums[i])
        if num <= n {
            nums[num - 1] = -abs(nums[num - 1])
        }
    }

    // 3. 找到第一个大于 0 的元素，返回这个元素对应的下标 i 再加 1
    for i := range nums {
        if nums[i] > 0 {
            return i + 1
        }
    }

    return n + 1
}

func abs(a int) int {
    if a < 0 {
        return -a
    }
    return a
}