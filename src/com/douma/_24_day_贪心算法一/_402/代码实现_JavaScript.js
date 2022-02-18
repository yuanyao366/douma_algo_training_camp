/**
 * @param {string} num
 * @param {number} k
 * @return {string}
 */
// 贪心 + 栈
var removeKdigits = function(num, k) {
    const stack = []
    for (const c of num) {
        while (stack.length && k > 0 && stack[stack.length - 1] > c) {
            stack.pop()
            k--
        }
        stack.push(c)
    }

    for (let i = 0; i < k; i++) {
        stack.pop()
    }

    let res = "", isFirst = true
    for (const c of stack) {
        if (c == '0' && isFirst) continue
        res += c
        isFirst = false
    }

    return res.length == 0 ? "0" : res
};