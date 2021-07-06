/**
 * @param {string} s
 * @return {string[][]}
 */
var partition = function(s) {

    const checkPalindrome = (str, left, right) => {
        while (left < right) {
            if (s[left] != s[right]) return false
            left++
            right--
        }
        return true
    }

    const res = []
    const dfs = (startIndex, path) => {
        if (startIndex == s.length) {
            res.push(path.slice())
            return
        }
        for (let i = startIndex; i < s.length; i++) {
            if (!checkPalindrome(s, startIndex, i)) continue
            path.push(s.substring(startIndex, i + 1))
            dfs(i + 1, path)
            path.pop()
        }
    }

    dfs(0, [])
    return res
};