// 方法一：严格的二分查找
// 时间复杂度：O(nlogn)
// 空间复杂度：O(1)
func twoSum1(numbers []int, target int) []int {
    for i := range numbers {
        var x = numbers[i]
        // 线性查找 - O(n)
        // 1. 二分查找 - O(logn)
        // [i + 1, nums.length - 1] 区间二分查找 target - x
        var index = binarySearch(numbers, i + 1, len(numbers) - 1, target - x)
        if index != -1 {
            return []int{i + 1, index + 1}
        }
    }
    return []int{}
}

func binarySearch(numbers []int, left int, right int, target int) int {
    for left <= right {
        var mid = left + (right - left) / 2
        if numbers[mid] == target {
            return mid
        } else if numbers[mid] > target {
            right = mid - 1
        } else {
            left = mid + 1
        }
    }
    return -1
}

// 方法二： 双指针
// 时间复杂度：O(n)
// 空间复杂度：O(1)
func twoSum(numbers []int, target int) []int {
    var left, right = 0, len(numbers) - 1
    for left < right {
        var sum = numbers[left] + numbers[right]
        if sum == target {
            return []int{left + 1, right + 1}
        } else if sum < target {
            left++
        } else {
            right--
        }
    }
    return []int{}
}