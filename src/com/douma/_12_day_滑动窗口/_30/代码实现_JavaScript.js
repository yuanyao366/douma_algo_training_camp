/**
 * @param {string} s
 * @param {string[]} words
 * @return {number[]}
 */
var findSubstring = function(s, words) {
    const oneWordLen = words[0].length
    const wordNum = words.length
    if (s.length < oneWordLen) return []

    const wordsCnt = new Map();
    for (const word of words) {
        if (wordsCnt.has(word)) {
            const cnt = wordsCnt.get(word)
            wordsCnt.set(word, cnt + 1)
        } else {
            wordsCnt.set(word, 1)
        }
    }
    const ans = []
    for (let i = 0; i < oneWordLen; i++) {
        let left = i, right = i
        let matchedWords = 0
        const windowMap = new Map();
        while (right <= s.length - oneWordLen) {
            const currWord = s.substr(right, oneWordLen)
            if (windowMap.has(currWord)) {
                const cnt = windowMap.get(currWord)
                windowMap.set(currWord, cnt + 1)
            } else {
                windowMap.set(currWord, 1)
            }
            matchedWords++
            const currWordCnt = wordsCnt.has(currWord) ? wordsCnt.get(currWord) : 0
            while (windowMap.get(currWord) > currWordCnt) {
                const leftWord = s.substr(left, oneWordLen)
                const cnt = windowMap.get(leftWord)
                windowMap.set(leftWord, cnt - 1)
                left += oneWordLen
                matchedWords--
            }
            if (matchedWords == wordNum) ans.push(left)
            right += oneWordLen
        }
    }
    return ans
};