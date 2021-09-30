func largestPerimeter(nums []int) int {
    sort.Ints(nums)
    for i := len(nums) - 1; i >= 2; i-- { // bug 修复：是 i--
        if nums[i - 2] + nums[i - 1] > nums[i] {
            return nums[i - 2] + nums[i - 1] + nums[i]
        }
    }
    return 0
}