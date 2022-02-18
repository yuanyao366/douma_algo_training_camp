/**
 * @param {string} s
 * @return {number}
 */
var calculate = function(s) {
    const stack = []
    let preSign = '+'
    let i = 0
    while (i < s.length) {
        if (s[i] == ' ') {
            i++
        } else {
            let num = 0
            while (i < s.length && s[i] <= '9' && s[i] >= '0') {
                num = num * 10 + (s[i].charCodeAt() - '0'.charCodeAt())
                i++
            }

            if (preSign == '+') {
                stack.push(num)
            } else if (preSign == '-') {
                stack.push(-num)
            } else if (preSign == '*') {
                stack.push(stack.pop() * num)
            } else if (preSign == '/') {
                stack.push(parseInt(stack.pop() / num, 10))
            }

            while (i < s.length && s[i] == ' ') i++
            if (i < s.length) preSign = s[i]
            i++
        }
    }
    let res = 0
    for (const i of stack) res += i
    return res
};