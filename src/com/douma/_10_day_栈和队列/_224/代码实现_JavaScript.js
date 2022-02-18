/**
 * @param {string} s
 * @return {number}
 */
var calculate = function(s) {
    const stack = []
    let sign = 1, num = 0, res = 0
    for (const c of s) {
        if (c <= '9' && c >= '0') {
            num = num * 10 + (c.charCodeAt() - '0'.charCodeAt())
        } else if (c == '+') {
            res += sign * num
            sign = 1
            num = 0
        } else if (c == '-') {
            res += sign * num
            sign = -1
            num = 0
        } else if (c == '(') {
            stack.push(res)
            stack.push(sign)
            sign = 1
            res = 0
        } else if (c == ')') {
            res += sign * num
            res *= stack[stack.length - 1]
            stack.pop()
            res += stack[stack.length - 1]
            stack.pop()
            num = 0
        }
    }
    return res + sign * num
};