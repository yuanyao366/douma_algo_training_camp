/**
 * @param {string} s
 * @return {number}
 */
// 1. 动态规划
var longestValidParentheses1 = function(s) {
    const n = s.length
    if (n <= 1) return 0

    const dp = new Array(n).fill(0)

    dp[0] = 0
    if (s[0] == '(' && s[1] == ')') dp[1] = 2
    let ans = dp[1]

    for (let i = 2; i < n; i++) {
        if (s[i] == ')') {
            if (s[i - 1] == '(') {
                dp[i] = dp[i - 2] + 2
            } else if (i - dp[i - 1] >= 1 && s[i - dp[i - 1] - 1] == '(') {
                dp[i] = dp[i - 1] + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2
            }
            ans = Math.max(ans, dp[i])
        }
    }

    return ans
};

// 2. 栈
var longestValidParentheses2 = function(s) {
    const n = s.length
    if (n <= 1) return 0
    let ans = 0
    const stack = []
    stack.push(-1)
    for (let i = 0; i < n; i++) {
        if (s[i] == '(') {
            stack.push(i)
        } else {
            stack.pop()
            if (!stack.length) {
                stack.push(i)
            } else {
                ans = Math.max(ans, i - stack[stack.length - 1])
            }
        }
    }
    return ans
}


// 3. 双指针
var longestValidParentheses = function(s) {
    const n = s.length
    if (n <= 1) return 0
    let left = 0, right = 0, ans = 0
    for (let i = 0; i < n; i++) {
        if (s[i] == '(') left++
        else if (s[i] == ')') right++
        if (left == right) {
            ans = Math.max(ans, 2 * left)
        } else if (right > left) {
            left = right = 0
        }
    }
    left = right = 0
    for (let i = n - 1; i >= 0; i--) {
        if (s[i] == '(') left++
        else if (s[i] == ')') right++
        if (left == right) {
            ans = Math.max(ans, 2 * left)
        } else if (left > right) {
            left = right = 0
        }
    }
    return ans
}