// 经典 LIS 问题
func longestArithSeqLength(nums []int) int {
    var ans, n = 0, len(nums)
    // dp[i][j]：表示以 nums[i] 为结尾且公差为 j 的最长等差数列的长度
    // 用数组会内存溢出，使用 map 代替数组
    var dp = make([]map[int]int, n + 1)
    for i := range dp {
        dp[i] = make(map[int]int)
    }

    for i := 0; i < n; i++ {
        for j := 0; j < i; j++ {
            var diff = nums[i] - nums[j]
            if _, ok := dp[j][diff]; ok {
                dp[i][diff] = dp[j][diff] + 1
            } else {
                dp[j][diff] = 1
                dp[i][diff] = dp[j][diff] + 1
            }

            if dp[i][diff] > ans {
                ans = dp[i][diff]
            }
        }
    }

    return ans
}