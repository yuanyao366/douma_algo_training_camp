/**
 * @param {string[]} words
 * @return {number}
 */
var maxProduct = function(words) {
    const bitmaskMap = new Map()
    for (let i = 0; i < words.length; i++) {
        let bitmask = 0
        for (const c of words[i]) {
            bitmask |= 1 << (c.charCodeAt() - 'a'.charCodeAt())
        }
        if (bitmaskMap.has(bitmask)) {
            bitmaskMap.set(bitmask, Math.max(bitmaskMap.get(bitmask), words[i].length))
        } else {
            bitmaskMap.set(bitmask, words[i].length)
        }
    }

    let ans = 0
    for (const x of bitmaskMap.keys()) {
        for (const y of bitmaskMap.keys()) {
            if ((x & y) == 0) {
                ans = Math.max(ans, bitmaskMap.get(x) * bitmaskMap.get(y))
            }
        }
    }

    return ans
};