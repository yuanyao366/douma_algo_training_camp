// 1. 暴力解法：遍历数组的所有的区间，然后找到最长没有重复字符的区间
// 时间复杂度：O(n^3)
// 空间复杂度：O(n)
// 会超时
func lengthOfLongestSubstring1(s string) int {
    var n = len(s)
    if n <= 1 {
        return n
    }
    var maxLen = 1
    for i := range s {
        for j := i + 1; j < n; j++ {
            if allUnique(s, i, j) && j - i + 1 > maxLen {
                maxLen = j - i + 1
            }
        }
    }
    return maxLen
}

func allUnique(s string, start int, end int) bool {
    var set = make(map[byte]bool)
    for i := start; i <= end; i++ {
        if set[s[i]] {
            return false
        }
        set[s[i]] = true
    }
    return true
}

// 暴力解法存在重复计算，同一个子串会进行多次判断是否存在重复字符
// 我们可以做如下的优化：
// 1. 如果 s[i, j) 子串没有重复字符的话，那么如果要判断 s[i, j] 没有重复字符的话
// 2. 我们只需要判断 s[i, j) 中是否存在 s[j] 即可

// 2. 滑动窗口
// 时间复杂度：O(2n) = O(n)，最坏的情况是 left 和 right 都遍历了一遍字符串
// 空间复杂度：O(n)
func lengthOfLongestSubstring2(s string) int {
    var n = len(s)
    if n <= 1 {
        return n
    }
    var maxLen = 1
    var left, right, window = 0, 0, make(map[byte]bool)
    for right < n {
        var rightChar = s[right]
        for window[rightChar] {
            delete(window, s[left])
            left++
        }
        if right - left + 1 > maxLen {
            maxLen = right - left + 1
        }
        window[rightChar] = true
        right++
    }
    return maxLen
}

// 以上当在窗口中存在重复字符，是一个一个字符的缩小窗口
// 我们可以通过记住每个字符在字符串中的索引，当遇到重复字符的时候，就可以直接跳到重复字符的后面

// 3. 优化后的滑动窗口
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func lengthOfLongestSubstring3(s string) int {
    var n = len(s)
    if n <= 1 {
        return n
    }
    var maxLen = 1
    var left, right, window = 0, 0, make(map[byte]int)
    for right < n {
        var rightChar = s[right]
        var rightCharIndex = 0
        if _, ok := window[rightChar]; ok {
            rightCharIndex = window[rightChar]
        }
        // 下面这样代码的详细解释请见：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4JB1P
        if rightCharIndex > left {
            left = rightCharIndex
        }
        if right - left + 1 > maxLen {
            maxLen = right - left + 1
        }
        window[rightChar] = right + 1
        right++
    }
    return maxLen
}

// 4. 追求程序的极致性能
// s 由英文字母、数字、符号和空格组成
func lengthOfLongestSubstring(s string) int {
    var n = len(s)
    if n <= 1 {
        return n
    }
    var maxLen = 1
    var left, right, window = 0, 0, [128]int{}
    for right < n {
        var rightChar = s[right]
        var rightCharIndex = window[rightChar]
        if rightCharIndex > left {
            left = rightCharIndex
        }
        if right - left + 1 > maxLen {
            maxLen = right - left + 1
        }
        window[rightChar] = right + 1
        right++
    }
    return maxLen
}