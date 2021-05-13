/**
 * @param {string} s
 * @param {string} t
 * @return {boolean}
 */
var isAnagram = function(s, t) {
    if (s.length != t.length) return false
    const count = {}
    for (const c of s) {
        if (count[c.charCodeAt() - 97]) {
            count[c.charCodeAt() - 97]++
        } else {
            count[c.charCodeAt() - 97] = 1
        }
    }
    for (const c of t) {
        if (!count[c.charCodeAt() - 97] ||
            count[c.charCodeAt() - 97] <= 0) return false
        count[c.charCodeAt() - 97]--
    }
    return true
};
var isAnagram1 = function(s, t) {
    if (s.length != t.length) return false
    const count = new Array(26).fill(0)
    for (const c of s) count[c.charCodeAt() - 97]++
    for (const c of t) {
        if (count[c.charCodeAt() - 97] <= 0) return false
        count[c.charCodeAt() - 97]--
    }
    return true
};