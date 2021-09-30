// 方法一
func partition1(s string) [][]string {
    var res, path = make([][]string, 0), make([]string, 0)

    var dfs func(int)
    dfs = func(startIndex int) {
        if startIndex == len(s) {
            var tmp = make([]string, len(path))
            copy(tmp, path)
            res = append(res, tmp)
            return
        }

        for i := startIndex; i < len(s); i++ {
            var endIndex = i
            // [startIndex, endIndex]
            if !checkPalindrome(s, startIndex, endIndex) {
                continue
            }
            path = append(path, s[startIndex:endIndex + 1])
            dfs(i + 1)
            path = path[:len(path) - 1]
        }
    }

    dfs(0)
    return res
}

func checkPalindrome(str string, left int, right int) bool {
    for left < right {
        if str[left] != str[right] {
            return false
        }
        left++
        right--
    }
    return true
}

// 方法二： 使用动态规划优化判断是否是回文串
func partition(s string) [][]string {
    var n = len(s)
    // 定义状态，dp[i][j] 表示子数组区间 [i, j] 对应的子串是否是回文
    var dp = make([][]bool, n)
    for i := range dp {
        dp[i] = make([]bool, n)
    }
    // 状态初始化
    // 一个字符，肯定是回文
    for i := 0; i < n; i++ {
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

    var res, path = make([][]string, 0), make([]string, 0)

    var dfs func(int)
    dfs = func(startIndex int) {
        if startIndex == len(s) {
            var tmp = make([]string, len(path))
            copy(tmp, path)
            res = append(res, tmp)
            return
        }

        for i := startIndex; i < len(s); i++ {
            var endIndex = i
            // [startIndex, endIndex]
            if !dp[startIndex][i] {
                continue
            }
            path = append(path, s[startIndex:endIndex + 1])
            dfs(i + 1)
            path = path[:len(path) - 1]
        }
    }

    dfs(0)
    return res
}