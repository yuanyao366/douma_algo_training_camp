/**
 * @param {string} s
 * @param {string} t
 * @param {number} maxCost
 * @return {number}
 */
var equalSubstring = function(s, t, maxCost) {
    let left = 0, right = 0
    let ans = 0, windowCost = 0
    while (right < s.length) {
        windowCost += Math.abs(s[right].charCodeAt() - t[right].charCodeAt())

        while (windowCost > maxCost) {
            windowCost -= Math.abs(s[left].charCodeAt() - t[left].charCodeAt())
            left++;
        }

        ans = Math.max(ans, right - left + 1)
        right++
    }
    return ans
};