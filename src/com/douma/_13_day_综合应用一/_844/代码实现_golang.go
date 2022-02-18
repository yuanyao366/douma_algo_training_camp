func backspaceCompare1(s string, t string) bool {
    return build(s) == build(t)
}

// 重建字符串
// 时间复杂度：O(m + n)
// 空间复杂度：O(m + n)
func build1(str string) string {
    var newS = ""
    for _, c := range str {
        if c != '#' {
            newS += string(c)
        } else if (len(newS) > 0) {
            newS = newS[:len(newS) - 1]
        }
    }
    return newS
}

// 栈
// 时间复杂度：O(m + n)
// 空间复杂度：O(m + n)
func build2(str string) string {
    var stack = make([]rune, 0)
    for _, c := range str {
        if c != '#' {
            stack = append(stack, c)
        } else if (len(stack) > 0) {
            stack = stack[:len(stack) - 1]
        }
    }
    return string(stack)
}


// 双指针（从前往后遍历）
// 时间复杂度：O(m + n)
// 空间复杂度：O(1)
func build(str string) string {
    var chars = []byte(str)
    var slow, fast = -1, 0
    for fast < len(chars) {
        if str[fast] != '#' {
            slow++
            if slow != fast {
                chars[slow], chars[fast] = chars[fast], chars[slow]
            }
        } else if slow > -1 {
            slow--
        }
        fast++
    }
    if slow == -1 {
        return ""
    } else {
        return string(chars)[0:slow + 1]
    }
}


// 双指针（从后往前遍历）
// 时间复杂度：O(m + n)
// 空间复杂度：O(1)
func backspaceCompare(s string, t string) bool {
    var i, j = len(s) - 1, len(t) - 1
    var skipS, skipT = 0, 0
    for i >= 0 || j >= 0 {
        // 回退 S 字符串的字符
        for i >= 0 {
            if s[i] == '#' {
                skipS++
                i--
            } else if skipS > 0 {
                skipS--
                i--
            } else {
                break
            }
        }

        // 回退 T 字符串的字符
        for j >= 0 {
            if t[j] == '#' {
                skipT++
                j--
            } else if skipT > 0 {
                skipT--
                j--
            } else {
                break
            }
        }

        // 判断 S 和 T 是否相等
        // 如果当前的 i 和 j 对应的字符不相等
        if i >= 0 && j >= 0 && s[i] != t[j] {
            return false
        }
        // 有一个指针到头了，还有一个不到头，则返回 false
        if (i >= 0) != (j >= 0) {
            return false
        }
        i--
        j--
    }
    return true
}