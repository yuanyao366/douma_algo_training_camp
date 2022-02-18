func minCut(s string) int {
    var n = len(s)
    // 这里是在 leetcode 647 之上，做了点改变
    // 定义状态，dp[i][j] 表示子数组区间 [i, j] 对应的子串是否是回文
    var dp = make([][]bool, n)
    for i := range dp {
        dp[i] = make([]bool, n)
        // 一个字符，肯定是回文
        dp[i][i] = true
    }

    // 状态转移
    for j := 1; j < n; j++ {
        for i := 0; i < j; i++ {
            // [i, j]
            var isCharEqual = s[i] == s[j]
            if j - i == 1 { // 只有两个字符
                dp[i][j] = isCharEqual
            } else { // 大于两个字符
                dp[i][j] = isCharEqual && dp[i + 1][j - 1]
            }
        }
    }

    // f[i]：表示以 s[i] 结尾最少分割次数
    var f = make([]int, n)
    for i := range f {
        f[i] = math.MaxInt32
    }
    for i := 0; i < n; i++ {
        if dp[0][i] { // s[0...i] 是回文串，那么不需要分割
            f[i] = 0
        } else {
            for j := 0; j < i; j++ {
                if dp[j + 1][i] { // s[j + 1...i] 是回文串，那么可以不分割
                    if f[j] + 1 < f[i] {
                        f[i] = f[j] + 1
                    }
                }
            }
        }
    }

    return f[n - 1]
}