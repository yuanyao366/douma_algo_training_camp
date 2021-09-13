// 方案一：双指针模拟
func repeatedSubstringPattern1(s string) bool {
    n := len(s)
    for len := 1; len * 2 <= n; len++ {
        if n % len == 0 {
            matched, i := true, 0
            for j := len; j < n; j++ {
                if s[i] != s[j] {
                    matched = false
                    break
                }
                i++
            }
            if matched {
                return true
            }
        }
    }
    return false
}

// 方案二：旋转数组
func repeatedSubstringPattern2(s string) bool {
    n := len(s)
    for len := 1; len * 2 <= n; len++ {
        str := rotate([]byte(s), len)
        if s == str {
            return true
        }
    }
    return false
}

func rotate(chars []byte, k int) string {
    n := len(chars)
    k %= n
    reverse(chars, 0, n - 1)
    reverse(chars, 0, k - 1)
    reverse(chars, k, n - 1)
    return string(chars)
}

func reverse(chars []byte, start int, end int) {
    for start < end {
        chars[start], chars[end] = chars[end], chars[start]
        start++
        end--
    }
}

 // 字符串匹配法
func repeatedSubstringPattern(s string) bool {
    n, ss := len(s), s + s
    return strings.Index(ss[1:(2 * n)], s) + 1 != n
}