func isPalindrome(s string) bool {
    s = strings.ToLower(s)
    left, right := 0, len(s) - 1
    for left < right {
        // 忽略左边无效字符
        for left < right && !isLetterOrDigit(s[left]) {
            left++
        }
        // 忽略右边无效字符
        for left < right && !isLetterOrDigit(s[right]) {
            right--
        }

        if left < right {
            if s[left] != s[right] {
                return false
            }
            left++
            right--
        }
    }
    return true
}

func isLetterOrDigit(c byte) bool {
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')
}