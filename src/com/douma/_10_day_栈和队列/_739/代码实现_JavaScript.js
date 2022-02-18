/**
 * @param {number[]} T
 * @return {number[]}
 */
var dailyTemperatures = function(T) {
    const n = T.length
    const res = new Array(n).fill(0)
    // 数组实现栈的功能，数组的最后作为栈顶
    const stack = []
    for (let i = 0; i < n; i++) {
        while (stack.length && T[i] > T[stack[stack.length - 1]]) {
            const prevIndex = stack.pop()
            res[prevIndex] = i - prevIndex
        }
        stack.push(i)
    }
    return res
};