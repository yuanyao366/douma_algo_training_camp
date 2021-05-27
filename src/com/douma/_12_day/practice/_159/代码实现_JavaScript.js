/**
 * @param {string} s
 * @return {number}
 */
var lengthOfLongestSubstringTwoDistinct = function(s) {
    let ans = 0, left = 0, right = 0
    const windowCharCnt = new Map()
    while (right < s.length) {
        const currChar = s[right]
        if (windowCharCnt.has(currChar)) {
            const count = windowCharCnt.get(currChar)
            windowCharCnt.set(currChar, count + 1)
        } else {
            windowCharCnt.set(currChar, 1)
        }

        while (left <= right && windowCharCnt.size > 2) {
            const leftChar = s[left]

            windowCharCnt.set(leftChar, windowCharCnt.get(leftChar, 0) - 1)
            if (windowCharCnt.get(leftChar, 0) <= 0) {
                windowCharCnt.delete(leftChar)
            }

            left++
        }
        ans = Math.max(ans, right - left + 1)
        right++
    }
    return ans
};