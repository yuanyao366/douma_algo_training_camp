// 思路一：在循环体内查找目标值
func search1(nums []int, target int) int {
    var left, right = 0, len(nums) - 1
    for left <= right {
        var mid = left + (right - left) / 2
        if nums[mid] == target {
            return mid
        } else if nums[mid] < target {
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    return -1
}

// 思路二：在循环体内排除没有目标值的区间
func search2(nums []int, target int) int {
    var left, right = 0, len(nums) - 1
    for left < right {
        var mid = left + (right - left) / 2
        if nums[mid] == target {
            return mid
        } else if nums[mid] < target {
            left = mid + 1
        } else {
            right = mid
        }
    }
    if nums[left] ==target {
        return left
    }
    return -1
}

// 思路二：在循环体内排除没有目标值的区间
func search3(nums []int, target int) int {
    var left, right = 0, len(nums) - 1
    for left < right {
        var mid = left + (right - left) / 2 + 1
        if nums[mid] == target {
            return mid
        } else if nums[mid] < target {
            left = mid
        } else {
            right = mid - 1
        }
    }
    if nums[left] ==target {
        return left
    }
    return -1
}

// 思路二：在循环体内排除没有目标值的区间
func search(nums []int, target int) int {
    var left, right = 0, len(nums) - 1
    // 剩余两个元素在循环体外处理
    for left + 1 < right {
        var mid = left + (right - left) / 2 + 1
        if nums[mid] == target {
            return mid
        } else if nums[mid] < target {
            left = mid
        } else {
            right = mid - 1
        }
    }
    if nums[left] ==target {
        return left
    }
    if nums[right] ==target {
        return right
    }
    return -1
}