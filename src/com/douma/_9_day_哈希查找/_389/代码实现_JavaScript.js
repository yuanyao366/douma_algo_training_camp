/**
 * @param {string} s
 * @param {string} t
 * @return {character}
 */
var findTheDifference = function(s, t) {
    let ret = 0
    for (const c of s) ret ^= c.charCodeAt()
    for (const c of t) ret ^= c.charCodeAt()
    return String.fromCharCode(ret)
};

var findTheDifference2 = function(s, t) {
    let as = 0, at = 0
    for (const c of s) as += c.charCodeAt()
    for (const c of t) at += c.charCodeAt()
    return String.fromCharCode(at - as)
};

var findTheDifference1 = function(s, t) {
    const count = new Array(26).fill(0)
    for (const c of s) count[c.charCodeAt() - 'a'.charCodeAt()]++
    for (const c of t) {
        count[c.charCodeAt() - 'a'.charCodeAt()]--
        if (count[c.charCodeAt() - 'a'.charCodeAt()] < 0) return c
    }
    return ' '
};