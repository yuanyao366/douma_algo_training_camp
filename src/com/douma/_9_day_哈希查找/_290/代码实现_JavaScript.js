/**
 * @param {string} pattern
 * @param {string} s
 * @return {boolean}
 */
var wordPattern = function(pattern, s) {
    const words = s.split(' ')
    if (pattern.length != words.length) return false;

    const char2Word = {}, word2Char = {}
    for (let i = 0; i < s.length; i++) {
        const c = pattern[i]
        const word = words[i]
        if ((char2Word[c] && char2Word[c] != word) ||
             word2Char[word] && word2Char[word] != c) {
            return false
        }
        char2Word[c] = word
        word2Char[word] = c
    }
    return true
};