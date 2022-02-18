/**
 * @param {string} s
 * @return {boolean}
 */
// 贪心策略：只有在开头和结尾两个字符不相等的时候，才去尝试删除开头或者结尾任一个字符
// 时间复杂度：O(n)
// 空间复杂度：O(1)
var validPalindrome = function(s) {
    if (s.length <= 1) return true

    const validPalindrome = (s, left, right) => {
        while (left < right) {
            if (s[left] != s[right]) return false
            left++;
            right--;
        }
        return true
    }

    let left = 0, right = s.length - 1;
    while (left < right) {
        if (s[left] == s[right]) {
            left++;
            right--;
        } else {
            return validPalindrome(s, left + 1, right) || validPalindrome(s, left, right - 1)
        }
    }
    return true
};