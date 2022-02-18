/**
 * @param {string} s
 * @return {string}
 */

// 栈
var removeDuplicates = function(s) {
    const stack = []
    for (const c of s) {
        if (stack.length && stack[stack.length - 1] == c) {
            stack.pop()
        } else {
            stack.push(c)
        }
    }
    return stack.join("")
}

// 快慢指针
var removeDuplicates2 = function(s) {
    const chars = s.split("")
    let slow = -1, fast = 0
    while (fast < s.length) {
        if (slow >= 0 && chars[slow] == chars[fast]) {
            slow--
        } else {
            slow++
            chars[slow] = chars[fast]
        }
        fast++;
    }
    return chars.join("").substring(0, slow + 1)
};