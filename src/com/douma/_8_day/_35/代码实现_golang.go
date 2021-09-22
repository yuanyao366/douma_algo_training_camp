// 思路一：在循环体内查找目标值
func searchInsert1(nums []int, target int) int {
    if len(nums) == 0 {
        return 0
    }
    if target > nums[len(nums) - 1] {
        return len(nums)
    }
    // 二分查找第一个大于等于 target 的索引
    var left, right = 0, len(nums) - 1
    for left <= right {
        var mid = left + (right - left) / 2
        if nums[mid] >= target {
            if mid == 0 || nums[mid - 1] < target {
                return mid
            } else {
                right = mid - 1
            }
        } else {
            left = mid + 1
        }
    }
    return -1
}

// 思路二：在循环体内排除没有目标值的区间
func searchInsert(nums []int, target int) int {
    if len(nums) == 0 {
        return 0
    }
    // 二分查找第一个大于等于 target 的索引
    var left, right = 0, len(nums)
    for left < right {
        var mid = left + (right - left) / 2
        if nums[mid] < target {
            left = mid + 1
        } else {
            right = mid
        }
    }
    return left
}