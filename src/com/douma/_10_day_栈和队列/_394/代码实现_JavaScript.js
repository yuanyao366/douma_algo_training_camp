/**
 * @param {string} s
 * @return {string}
 */
var decodeString = function(s) {
    let res = ""
    const numStack = []
    const strStack = []
    let num = 0
    for (const c of s) {
        if (c <= '9' && c >= '0') {
            num = num * 10 + (c.charCodeAt() - '0'.charCodeAt())
        } else if (c == '[') {
            numStack.push(num)
            strStack.push(res)
            num = 0
            res = ""
        } else if (c == ']') {
            const item = res
            for (let i = 1; i < numStack[numStack.length - 1]; i++) {
                res += item
            }
            res = strStack[strStack.length - 1] + res
            numStack.pop()
            strStack.pop()
        } else {
            res += c
        }
    }
    return res
};