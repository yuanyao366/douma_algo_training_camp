/**
 * @param {string} s
 * @param {string} t
 * @return {string}
 */
var minWindow = function(s, t) {
    const cntT = new Array(60).fill(0)
    let uniqueChars = 0
    for (const c of t) {
        const index = c.charCodeAt() - 'A'.charCodeAt()
        if (cntT[index] == 0) uniqueChars++
        cntT[index]++
    }

    const filteredS = []
    for (let i = 0; i < s.length; i++) {
        const index = s[i].charCodeAt() - 'A'.charCodeAt()
        if (cntT[index] > 0) filteredS.push([i, s[i]])
    }

    let left = 0, right = 0
    let matchedChars = 0
    const windowCnt = new Array(60).fill(0)
    const ans = [-1, 0, 0]
    while (right < filteredS.length) {
        const rightChar = filteredS[right][1]
        const rightCharIndex = rightChar.charCodeAt() - 'A'.charCodeAt()
        windowCnt[rightCharIndex]++

        if (windowCnt[rightCharIndex] == cntT[rightCharIndex]) {
            matchedChars++
        }

        while (left <= right && matchedChars == uniqueChars) {
            const start = filteredS[left][0]
            const end = filteredS[right][0]
            if (ans[0] == -1 || end - start + 1 < ans[0]) {
                ans[0] = end - start + 1
                ans[1] = start
                ans[2] = end
            }

            const leftChar = filteredS[left][1]
            const leftCharIndex = leftChar.charCodeAt() - 'A'.charCodeAt()
            windowCnt[leftCharIndex]--
            if (windowCnt[leftCharIndex] < cntT[leftCharIndex]) matchedChars--
            left++
        }
        right++
    }

    return ans[0] == -1 ? "" : s.substr(ans[1], ans[0])
};