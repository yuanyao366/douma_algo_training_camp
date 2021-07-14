/**
 * @param {string} s
 * @return {string[][]}
 */
// 方法二： 使用动态规划优化判断是否是回文串
var partition = function(s) {

    const len = s.length

    // 定义状态，dp[i][j] 表示子数组区间 [i, j] 对应的子串是否是回文
    const dp = new Array(len).fill(0).map(() => new Array(len).fill(false))
    // 状态初始化
    for (let i = 0; i < len; i++) {
        dp[i][i] = true
    }
    // 状态转移
    for (let j = 1; j < len; j++) {
        for (let i = 0; i < j; i++) {
            // [i, j]
            const isCharEqual = s[i] == s[j]
            if (j - i == 1) { // 只有两个字符
                dp[i][j] = isCharEqual
            } else  { // 大于两个字符
                dp[i][j] = isCharEqual && dp[i + 1][j - 1]
            }
        }
    }

    const res = []
    const dfs = (startIndex, path) => {
        if (startIndex == s.length) {
            res.push(path.slice())
            return
        }
        for (let i = startIndex; i < s.length; i++) {
            if (!dp[startIndex][i]) continue
            path.push(s.substring(startIndex, i + 1))
            dfs(i + 1, path)
            path.pop()
        }
    }

    dfs(0, [])
    return res
};

// 方法一
var partition1 = function(s) {

    const checkPalindrome = (str, left, right) => {
        while (left < right) {
            if (s[left] != s[right]) return false
            left++
            right--
        }
        return true
    }

    const res = []
    const dfs = (startIndex, path) => {
        if (startIndex == s.length) {
            res.push(path.slice())
            return
        }
        for (let i = startIndex; i < s.length; i++) {
            if (!checkPalindrome(s, startIndex, i)) continue
            path.push(s.substring(startIndex, i + 1))
            dfs(i + 1, path)
            path.pop()
        }
    }

    dfs(0, [])
    return res
};