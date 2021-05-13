/**
 * @param {string} s
 * @param {string} t
 * @return {boolean}
 */
var isIsomorphic = function(s, t) {
    const s2t = {}, t2s = {}
    for (let i = 0; i < s.length; i++) {
        const sc = s[i]
        const tc = t[i]
        if ((s2t[sc] && s2t[sc] != tc) ||
             t2s[tc] && t2s[tc] != sc) {
            return false
        }
        s2t[sc] = tc
        t2s[tc] = sc
    }
    return true
};