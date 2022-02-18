// 只有小括号的场景
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func isValid1(s string) bool {
    if len(s) % 2 == 1 {
        return false
    }
    var stack = []byte{}
    for i := range s {
        if s[i] == '(' {
            stack = append(stack, s[i])
        } else {
            if len(stack) == 0 {
                return false
            }
            stack = stack[:len(stack) - 1]
        }
    }
    return len(stack) == 0
}

// 考虑包括大、中、小括号
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func isValid2(s string) bool {
    if len(s) % 2 == 1 {
        return false
    }
    var stack = []byte{}
    for i := range s {
        var c = s[i]
        if c == '(' || c == '{' || c == '[' {
            stack = append(stack, c)
        } else {
            if len(stack) == 0 {
                return false
            }
            var top = stack[len(stack) - 1]
            stack = stack[:len(stack) - 1]
            if c == ')' && top != '(' {
                return false
            }
            if c == '}' && top != '{' {
                return false
            }
            if c == ']' && top != '[' {
                return false
            }
        }
    }
    return len(stack) == 0
}

// 时间复杂度：O(n)
// 空间复杂度：O(n)
func isValid(s string) bool {
    if len(s) % 2 == 1 {
        return false
    }
    var m = map[byte]byte{'(': ')', '[': ']', '{': '}'}
    var stack = []byte{}
    for i := range s {
        var c = s[i]
        if _, ok := m[c]; ok {
            stack = append(stack, c)
        } else {
            if len(stack) == 0 {
                return false
            }
            var top = stack[len(stack) - 1]
            stack = stack[:len(stack) - 1]
            if c != m[top] {
                return false
            }
        }
    }
    return len(stack) == 0
}