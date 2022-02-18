// 暴力解法
// 时间复杂度：O(n^2) 超时
// 空间复杂度：O(1)
func validPalindrome1(s string) bool {
    // 删除逐个字符，然后判断， i = -1 表示不删除任何字符
    for i := -1; i < len(s); i++ {
        var isPalindrome = true
        var left, right = 0, len(s) - 1
        for left < right {
            // 删除索引 i 对应的字符
            if left == i {
                left++
            }
            if right == i {
                right--
            }

            if s[left] == s[right] {
                left++
                right--
            } else {
                isPalindrome = false
                break
            }
        }
        if isPalindrome {
            return true
        }
    }
    return false
}

// 贪心策略：只有在开头和结尾两个字符不相等的时候，才去尝试删除开头或者结尾任一个字符
// 时间复杂度：O(n)
// 空间复杂度：O(1)
func validPalindrome(s string) bool {
    var left, right = 0, len(s) - 1
    for left < right {
        if s[left] == s[right] {
            left++
            right--
        } else {
            // 要么删除 left 指向的字符，要么删除 right 指向的字符
            // 然后再判断剩余的字符是否是回文
            return validPalindromeHelp(s, left + 1, right) ||
                        validPalindromeHelp(s, left, right - 1)
        }
    }
    return true
}

func validPalindromeHelp(s string, left int, right int) bool {
    for left < right {
        if s[left] != s[right] {
            return false
        }
        left++
        right--
    }
    return true
}