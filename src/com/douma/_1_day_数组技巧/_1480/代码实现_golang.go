// 暴力解法
// 时间复杂度：O(n^2)
func runningSum1(nums []int) []int {
    n := len(nums)
    prefixSum := make([]int, n)

    for i := 0; i < n; i++ {
        sum := 0
        for j := 0; j <= i; j++ {
            sum += nums[j]
        }
        prefixSum[i] = sum
    }

    return prefixSum
}

// 优化：消除重复计算
// 时间复杂度：O(n)
func runningSum(nums []int) []int {
    n := len(nums)
    prefixSum := make([]int, n)
    prefixSum[0] = nums[0]
    for i := 1; i < n; i++ {
        prefixSum[i] = prefixSum[i - 1] + nums[i]
    }

    return prefixSum
}