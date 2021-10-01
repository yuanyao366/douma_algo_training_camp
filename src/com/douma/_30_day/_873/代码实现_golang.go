// 经典 LIS 问题
func lenLongestFibSubseq(arr []int) int {
    var n = len(arr)

    var indexes = make(map[int]int)
    for i := 0; i < n; i++ {
        indexes[arr[i]] = i
    }

    var ans = 0
    // dp[i][j]：表示以 arr[i]，arr[j] 为结尾的最长的斐波那契子序列的长度
    var dp = make([][]int, n)
    for i := range dp {
        dp[i] = make([]int, n)
    }

    for j := 0; j < n; j++ {
        for i := 0; i < j; i++ {
            var arrk = arr[j] - arr[i]
            // 在 [0...i] 中找到一个元素 arr[k]，使得 arr[k] + arr[i] = arr[j]
            // 所以需要保证 arrk < arr[i]
            if _, ok := indexes[arrk]; ok && arrk < arr[i] {
                var k = indexes[arrk]
                if dp[k][i] + 1 > dp[i][j] {
                    dp[i][j] = dp[k][i] + 1
                }
                if dp[i][j] + 2 > ans {
                    ans = dp[i][j] + 2
                }
            }
        }
    }

    if ans < 3 {
        return 0
    }
    return ans
}