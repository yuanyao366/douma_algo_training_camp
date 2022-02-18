// 动态规划
func findLength(nums1 []int, nums2 []int) int {
    var m, n = len(nums1), len(nums2)
    // dp[i][j]：表示 A 中前 i 个元素中和 B 的前 j 个元素中最长公共子数组长度
    var dp = make([][]int, m + 1)
    for i := range dp {
        dp[i] = make([]int, n + 1)
    }

    var ans = 0
    for i := 1; i <= m; i++ {
        for j := 1; j <= n; j++ {
            if nums1[i - 1] == nums2[j - 1] {
                dp[i][j] = dp[i - 1][j - 1] + 1
                if dp[i][j] > ans {
                    ans = dp[i][j]
                }
            }
        }
    }

    return ans
}