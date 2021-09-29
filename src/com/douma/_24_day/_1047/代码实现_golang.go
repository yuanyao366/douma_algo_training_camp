// 栈
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func removeDuplicates1(s string) string {
    var stack = make([]byte, 0)
    for i := range s {
        var c = s[i]
        if len(stack) > 0 && stack[len(stack) - 1] == c {
            stack = stack[:len(stack) - 1]
        } else {
            stack = append(stack, c)
        }
    }

    return string(stack)
}

// 快慢指针
// 时间复杂度：O(n)
// 空间复杂度：O(1)
func removeDuplicates(s string) string {
    var chars = []byte(s)
    var slow, fast = -1, 0
    for fast < len(s) {
        if slow >= 0 && chars[fast] == chars[slow] {
            slow--
        } else {
            slow++
            chars[slow] = chars[fast]
        }
        fast++
    }
    // bug 修复：需要将 chars 中的前 slow 个字符组成新的字符串
    return string(chars[0:slow + 1])
}