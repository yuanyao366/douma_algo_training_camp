// 字符串匹配算法：暴力解法、RK 算法、BM 算法、KMP 算法
// 详细参见：课程 A：应用篇 -> 字符串匹配
// 暴力
// 时间复杂度：O(m*n)
// 空间复杂度：O(1)
func strStr1(haystack string, needle string) int {
    n := len(needle)
    if n == 0 {
        return 0
    }
    first := needle[0]
    for i := 0; i < len(haystack) - n + 1; i++ {
        // 如果等于 needle 的第一个字符，再进行 n 个字符的比较
        if haystack[i] == first {
            matched := true
            for j := 0; j < n; j++ {
                if needle[j] != haystack[j + i] {
                    matched = false
                    break
                }
            }
            if matched {
                return i
            }
        }
    }
    return -1
}

// KMP
// 时间复杂度：O(m + n)
// 空间复杂度：O(n)
func strStr(haystack string, needle string) int {
    n := len(needle)
    if n == 0 {
        return 0
    }
    m := len(haystack)
    if m < n {
        return -1
    }

    // 根据模式串所有的前缀，计算得到 next 数组
    next := getNext(needle)

    j := 0
    for i := 0; i < m; i++ {
        for j > 0 && haystack[i] != needle[j] {
            // 就不从头开始匹配了，直接跳到下一个最长匹配前缀字符串的结尾字符的下一个字符位置
            j = next[j - 1] + 1
        }
        if haystack[i] == needle[j] {
            j++
        }
        if j == n {
            return i - n + 1
        }
    }
    return -1
}

func getNext(needle string) []int {
    n := len(needle)
    if n == 1 {
        return []int{}
    }

    next := make([]int, n - 1)
    next[0] = -1
    for j := 1; j < n - 1; j++ {
        pre := next[j - 1]
        for pre != -1 && needle[pre + 1] != needle[j] {
            // 因为前一个最长串的下一个字符不与最后一个相等，所以需要找前一个的次长串
            // 问题就变成了求 0 到 next(pre) 的最长串
            pre = next[pre]
        }
        if needle[pre + 1] == needle[j] {
            pre++
        }
        next[j] = pre
    }
    return next
}