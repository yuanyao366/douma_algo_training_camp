// 时间复杂度：O(logn)，注意，视频中说时间复杂度是 O(n)，这是口误
func search(nums []int, target int) int {
    if len(nums) == 0 {
        return -1
    }
    var left, right = 0, len(nums) - 1
    for left <= right {
        var mid = left + (right - left) / 2
        if nums[mid] == target {
            return mid
        }

        if nums[left] <= nums[mid] { // 左边有序
            if target >= nums[left] && target < nums[mid] {
                right = mid - 1
            } else {
                left = mid + 1
            }
        } else { // 右边有序
            if target > nums[mid] && target <= nums[right] {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
    }
    return -1
}