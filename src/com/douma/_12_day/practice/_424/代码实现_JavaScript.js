/**
 * @param {string} s
 * @param {number} k
 * @return {number}
 */
var characterReplacement = function(s, k) {
    const n = s.length
    if (n < 2) return n

    let res = 0, left = 0, right = 0
    const freq = new Array(26).fill(0)
    let maxCount = 0
    while (right < n) {
        freq[s[right].charCodeAt() - 'A'.charCodeAt()]++
        maxCount = Math.max(freq[s[right].charCodeAt() - 'A'.charCodeAt()], maxCount)
        // 出现最多的次数，再加上 k 都没有当前窗口长度大，说明可以缩减窗口了
        if ((right - left + 1) - maxCount > k) {
            freq[s[left].charCodeAt() - 'A'.charCodeAt()]--
            left++
        }
        res = Math.max(res, right - left + 1)
        right++
    }
    return res
};