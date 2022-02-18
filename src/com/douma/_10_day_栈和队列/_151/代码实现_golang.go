// 方法一：直接解析，不使用内置 API
// 时间复杂度：O(n)
// 空间复杂度：O(1)
func reverseWords1(s string) string {
    /*
    输入：s = "  Bob    Loves  Alice   "
    1. "Bob Loves Alice"
    2. "ecilA sevoL boB"
    3. "Alice Loves Bob"
    输出："Alice Loves Bob"
    */
    s = trimSpaces([]rune(s), len(s))
    var chars = []rune(s)
    reverse(chars, 0, len(chars) - 1)
    return reverseEachWord(chars)
}

// 空间复杂度为 O(1)
// 快慢指针
func trimSpaces(chars []rune, n int) string {
    var slow, fast = 0, 0
    for fast < n {
        for fast < n && chars[fast] == ' ' {
            fast++
        }
        for fast < n && chars[fast] != ' ' {
            chars[slow] = chars[fast]
            slow++
            fast++
        }
        for fast < n && chars[fast] == ' ' {
            fast++
        }
        if fast < n {
            chars[slow] = ' '
            slow++
        }
    }
    return string(chars)[0:slow]
}

func reverseEachWord(chars []rune) string {
    var n, left = len(chars), 0
    for left < n {
        if chars[left] != ' ' {
            var right = left
            for right + 1 < n && chars[right + 1] != ' ' {
                right++
            }
            reverse(chars, left, right)
            left = right + 1
        } else {
            left++
        }
    }
    return string(chars)
}

func reverse(cArr []rune, start int, end int) {
    for start < end {
        cArr[start], cArr[end] = cArr[end], cArr[start]
        start++
        end--
    }
}


// 方法二：双端队列
// 时间复杂度：O(n)
// 空间复杂度：O(1)
func reverseWords(s string) string {
    /*
    输入：s = "  Bob    Loves  Alice   "
    1. "Bob Loves Alice"
    2. "ecilA sevoL boB"
    3. "Alice Loves Bob"
    输出："Alice Loves Bob"
    */
    s = trimSpaces([]rune(s), len(s))
    var chars = []rune(s)
    reverse(chars, 0, len(chars) - 1)
    return reverseEachWord(chars)
}

