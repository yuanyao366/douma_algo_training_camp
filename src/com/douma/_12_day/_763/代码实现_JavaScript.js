/**
 * @param {string} s
 * @return {number[]}
 */
var partitionLabels = function(s) {
    const c2Index = new Array(26).fill(-1)
    for (let i = 0; i < s.length; i++) {
        c2Index[s[i].charCodeAt() - 'a'.charCodeAt()] = i
    }

    const ans = []
    let left = 0, right = 0
    for (let i = 0; i < s.length; i++) {
        right = Math.max(right, c2Index[s[i].charCodeAt() - 'a'.charCodeAt()])
        if (i == right) {
            ans.push(right - left + 1)
            left = right + 1
        }
    }
    return ans
};