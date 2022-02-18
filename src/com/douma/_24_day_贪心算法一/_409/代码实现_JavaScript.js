/**
 * @param {string} s
 * @return {number}
 */
var longestPalindrome = function(s) {
    const count = new Array(128).fill(0)
    for (const c of s) {
        count[c.charCodeAt()]++
    }

    let ans = 0
    for (const v of count) {
        ans += Math.floor(v / 2) * 2
        if (v % 2 == 1 && ans % 2 == 0) ans++
    }

    return ans
};