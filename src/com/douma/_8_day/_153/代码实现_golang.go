// 二分查找
// 时间复杂度是： O(logn)
// 空间复杂度是：O(1)
func findMin(nums []int) int {
    var left, right = 0, len(nums) - 1
    for left < right {
        var mid = left + (right - left) / 2
        if nums[mid] > nums[right] {
            left = mid + 1
        } else {
            right = mid
        }
    }
    return nums[left]
}