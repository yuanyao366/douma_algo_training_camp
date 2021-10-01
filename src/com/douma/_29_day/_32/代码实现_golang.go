// 动态规划
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func longestValidParentheses1(s string) int {
    var n = len(s)
    if n <= 1 {
        return 0
    }

    // 1. 状态，dp[i] 表示以下标为 i 的字符结尾的最长有效子字符串的长度
    var dp = make([]int, n)

    dp[0] = 0
    // bug 修复：考虑第一个字符和第二个字符
    if s[0] == '(' && s[1] == ')' {
        dp[1] = 2
    }

    var res = dp[1]
    for i := 2; i < n; i++ {
        if s[i] == ')' {
            if s[i - 1] == '(' {
                dp[i] = dp[i - 2] + 2
            } else if i - dp[i - 1] >= 1 && s[i - dp[i - 1] - 1] == '(' {
                dp[i] = dp[i - 1] + 2
                if i - dp[i - 1] >= 2 {
                    dp[i] = dp[i - 1] + 2 + dp[i - dp[i - 1] - 2]
                }
            }
            if dp[i] > res {
                res = dp[i]
            }
        }
    }

    return res
}

// 栈
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func longestValidParentheses2(s string) int {
    var ans = 0
    var stack = []int{-1}
    for i := range s {
        if s[i] == '(' {
            stack = append(stack, i)
        } else {
            stack = stack[:len(stack) - 1]
            if len(stack) == 0 {
                stack = append(stack, i)
            } else {
                if i - stack[len(stack) - 1] > ans {
                    ans = i - stack[len(stack) - 1]
                }
            }
        }
    }
    return ans
}

// 双变量
// 时间复杂度：O(n)
// 空间复杂度：O(1)
func longestValidParentheses(s string) int {
    var left, right, ans = 0, 0, 0
    for i := range s {
        if s[i] == '(' {
            left++
        } else {
            right++
        }

        if left == right {
            if 2 * left > ans {
                ans = 2 * left
            }
        } else if right > left {
            left, right = 0, 0
        }
    }

    left, right = 0, 0
    for i := len(s) - 1; i >= 0; i-- {
        if s[i] == '(' {
            left++
        } else {
            right++
        }
        if left == right {
            if 2 * left > ans {
                ans = 2 * left
            }
        } else if left > right {
            left, right = 0, 0
        }
    }

    return ans
}