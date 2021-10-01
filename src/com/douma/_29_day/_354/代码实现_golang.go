// 题意：找出二维数组的一个排列，使得其中有最长的单调递增子序列（两个维度都递增）
// 就是 300 号算法题的变形体
func maxEnvelopes(envelopes [][]int) int {
    var n = len(envelopes)
    if n <= 1 {
        return n
    }

    sort.Slice(envelopes, func(i, j int) bool {
        if envelopes[i][0] != envelopes[j][0] {
            return envelopes[i][0] < envelopes[j][0]
        } else {
            // bug 修复：按照高度降序排列
            return envelopes[i][1] > envelopes[j][1]
        }
    })

    var maxLen = 1
    // 状态：dp[i] 表示以 i 对应元素结尾的时候最长递增子序列的长度
    var dp = make([]int, n)
    // 状态初始化：单个元素最少有一个递增子序列元素
    for i := range dp {
        dp[i] = 1
    }
    for j := 0; j < n; j++ {
        for i := 0; i < j; i++ {
            if envelopes[j][1] > envelopes[i][1] {
                if dp[i] + 1 > dp[j] {
                    dp[j] = dp[i] + 1
                }
                if dp[j] > maxLen {
                    maxLen = dp[j]
                }
            }
        }
    }

    return maxLen
}