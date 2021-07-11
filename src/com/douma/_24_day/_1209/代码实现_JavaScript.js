/**
 * @param {string} s
 * @param {number} k
 * @return {string}
 */
// 栈
var removeDuplicates = function(s, k) {
    const stack = []
    for (const c of s) {
        if (!stack.length || stack[stack.length - 1][0] != c) {
            stack.push([c, 1])
        } else {
            stack[stack.length - 1][1]++
            if (stack[stack.length - 1][1] == k) {
                stack.pop()
            }
        }
    }

    let res = ""
    for (const pair of stack) {
        for (let i = 0; i < pair[1]; i++) {
            res += pair[0]
        }
    }

    return res
}

// 快慢指针
var removeDuplicates = function(s, k) {
   const chars = s.split(""), count = []
    let slow = 0, fast = 0
    while (fast < s.length) {
        chars[slow] = chars[fast]
        if (slow == 0 || chars[slow] != chars[slow - 1]) {
            count.push(1)
        } else {
            const incremented = count.pop() + 1
            if (incremented == k) {
                slow = slow - k
            } else {
                count.push(incremented)
            }
        }
        slow++
        fast++
    }
    return chars.join("").substring(0, slow)
};