// 贪心 + 单调栈
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func removeDuplicateLetters(s string) string {
    // 1. 计算字符在字符串 s 中的最后索引
    var lastIndex = [26]int{}
    for i := range s {
        lastIndex[s[i] - 'a'] = i
    }

    // 2. 维护单调栈
    var stack = make([]byte, 0)
    // 用于记录字符是否已经存在于栈中
    var exists = [26]bool{}

    for i := range s {
        var c = s[i]
        if exists[c - 'a'] {
            continue
        }

        // 条件：
        // (1). 当前字符小于栈顶字符
        // (2). 栈顶字符在当前字符的后面还会出现
        for len(stack) > 0 && stack[len(stack) - 1] > c &&
                lastIndex[stack[len(stack) - 1] - 'a'] > i {
            var top = stack[len(stack) - 1]
            stack = stack[:len(stack) - 1]
            exists[top - 'a'] = false
        }

        stack = append(stack, c)
        exists[c - 'a'] = true
    }

    // 3. 将栈中字符拼接成结果
    return string(stack)
}