/**
 * @param {string} s
 * @return {number}
 */
// 哈希集合
var lengthOfLongestSubstring1 = function(s) {
    const n = s.length
    if (n <= 1) return n

    let left = 0, right = 0
    const window = new Set()
    let maxLen = 0
    while (right < n) {
        if (!window.has(s[right])) {
            maxLen = Math.max(maxLen, right - left + 1)
            window.add(s[right])
            right++
        } else {
            window.delete(s[left])
            left++
        }
    }
    return maxLen
};

// 哈希映射
var lengthOfLongestSubstring2 = function(s) {
    const n = s.length
    if (n <= 1) return n

    let left = 0, right = 0
    const window = new Map()
    let maxLen = 0
    while (right < n) {
        const rightCharIndex = window.has(s[right]) ? window.get(s[right]) : -1
        // 下面这样代码的详细解释请见：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4JB1P
        left = Math.max(left, rightCharIndex)
        maxLen = Math.max(maxLen, right - left + 1)
        window.set(s[right], right + 1)
        right++
    }
    return maxLen
};

// 字符数组
var lengthOfLongestSubstring = function(s) {
    const n = s.length
    if (n <= 1) return n

    let left = 0, right = 0
    const window = new Array(128).fill(0)
    let maxLen = 0
    while (right < n) {
        const rightCharIndex = window[s[right].charCodeAt()]
        left = Math.max(left, rightCharIndex)
        maxLen = Math.max(maxLen, right - left + 1)
        window[s[right].charCodeAt()] = right + 1
        right++
    }
    return maxLen
};