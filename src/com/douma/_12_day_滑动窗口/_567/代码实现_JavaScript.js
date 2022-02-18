/**
 * @param {string} s1
 * @param {string} s2
 * @return {boolean}
 */
// 滑动窗口
var checkInclusion = function(s1, s2) {
    const n = s1.length
    const m = s2.length
    if (n > m) return false

    const cnt = new Array(26).fill(0)
    for (const c of s1) {
        cnt[c.charCodeAt() - 'a'.charCodeAt()]++
    }

    let left = 0, right = 0
    while (right < m) {
        const x = s2[right].charCodeAt() - 'a'.charCodeAt()
        cnt[x]--
        while (cnt[x] < 0) {
            cnt[s2[left].charCodeAt() - 'a'.charCodeAt()]++
            left++
        }
        if (right - left + 1 == n) return true
        right++
    }
    return false
};