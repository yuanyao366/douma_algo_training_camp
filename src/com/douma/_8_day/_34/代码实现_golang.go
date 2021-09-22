func searchRange(nums []int, target int) []int {
    if len(nums) == 0 {
        return []int{-1, -1}
    }
    var firstTargetIndex = searchFirstTargetIndex(nums, target)
    if firstTargetIndex == -1 {
        return []int{-1, -1}
    }
    var lastTargetIndex = searchLastTargetIndex(nums, target)
    return []int{firstTargetIndex, lastTargetIndex}
}

// 思路二：在循环体内排除没有目标值的区间
func searchFirstTargetIndex(nums []int, target int) int {
    var left, right = 0, len(nums) - 1
    for left < right {
        var mid = left + (right - left) / 2
        if nums[mid] < target {
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

 // 思路二：在循环体内排除没有目标值的区间
func searchLastTargetIndex(nums []int, target int) int {
    var left, right = 0, len(nums) - 1
    for left < right {
        var mid = left + (right - left) / 2 + 1
        if nums[mid] > target {
            right = mid - 1
        } else {
            left = mid
        }
    }
    if nums[left] == target {
        return left
    }
    return -1
}

// 思路一：在循环体内查找目标值
func searchFirstTargetIndex1(nums []int, target int) int {
    var left, right = 0, len(nums) - 1
    for left <= right {
        var mid = left + (right - left) / 2
        if nums[mid] == target {
            if mid == 0 || nums[mid - 1] < target {
                return mid
            } else {
                right = mid - 1
            }
        } else if nums[mid] < target {
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    return -1
}

// 思路一：在循环体内查找目标值
func searchLastTargetIndex1(nums []int, target int) int {
    var left, right = 0, len(nums) - 1
    for left <= right {
        var mid = left + (right - left) / 2
        if nums[mid] == target {
            if mid == len(nums) - 1 || nums[mid + 1] > target {
                return mid
            } else {
                left = mid + 1
            }
        } else if nums[mid] < target {
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    return -1
}