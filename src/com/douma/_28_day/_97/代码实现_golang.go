// 问题转化为二维数组路径问题
func isInterleave(s1 string, s2 string, s3 string) bool {
    var m, n, t = len(s1), len(s2), len(s3)
    if m + n != t {
        return false
    }

    // dp[i][j]：
    // s1 的前 i 个字符和 s2 的前 j 个字符是否可以交错组成 s3 的前 i + j 个字符
    var dp = make([][]bool, m + 1)
    for i := range dp {
        dp[i] = make([]bool, n + 1)
    }

    dp[0][0] = true
    // 第一列只能往下走
    // bug 修复：需要等于 m，因为是前 m 个字符
    // s1 是竖着的
    for i := 1; i <= m; i++ {
        if s1[i - 1] == s3[i - 1] {
            dp[i][0] = true
        } else { // 不相符直接终止
            break
        }
    }
    // 第一行只能往右走
    // bug 修复：需要等于 n，因为是前 n 个字符
    // s2 是横着的
    for i := 1; i <= n; i++ {
        if s2[i - 1] == s3[i - 1] {
            dp[0][i] = true
        } else { // 不相符直接终止
            break
        }
    }

    // 状态转移
    // bug 修复：需要等于 m，因为是前 m 个字符
    // bug 修复：需要等于 n，因为是前 n 个字符
    for i := 1; i <= m; i++ {
        for j := 1; j <= n; j++ {
            var k = i + j
            var s1Equals3 = s1[i - 1] == s3[k - 1] && dp[i - 1][j]
            var s2Equals3 = s2[j - 1] == s3[k - 1] && dp[i][j - 1]
            dp[i][j] = s1Equals3 || s2Equals3
        }
    }

    // 返回结果
    return dp[m][n]
}