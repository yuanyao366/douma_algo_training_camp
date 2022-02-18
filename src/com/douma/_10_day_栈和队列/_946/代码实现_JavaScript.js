/**
 * @param {number[]} pushed
 * @param {number[]} popped
 * @return {boolean}
 */
var validateStackSequences = function(pushed, popped) {
    const stack = []
    let i = 0
    for (const num of pushed) {
        stack.push(num)
        while (stack.length && i < popped.length && stack[stack.length - 1] == popped[i]) {
            stack.pop()
            i++
        }
    }
    return i == popped.length
};