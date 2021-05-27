/**
 * @param {string} s
 * @param {number} k
 * @return {number}
 */
var numKLenSubstrNoRepeats = function(s, k) {
    if (s.length < k) return 0;
    let left = 0, right = 0
    let ans = 0
    const count = new Array(26).fill(0)
    while (right < s.length) {
        const rightIndex = s[right].charCodeAt() - 'a'.charCodeAt()
        count[rightIndex]++

        while (count[rightIndex] > 1) {
            const leftIndex = s[left].charCodeAt() - 'a'.charCodeAt()
            count[leftIndex]--
            left++
        }

        if (right - left + 1 == k) {
            ans++;
            const leftIndex = s[left].charCodeAt() - 'a'.charCodeAt()
            count[leftIndex]--
            left++
        }
        right++
    }
    return ans
};