/**
 * @param {string} jewels
 * @param {string} stones
 * @return {number}
 */
var numJewelsInStones1 = function(jewels, stones) {
    const jewelsSet = new Set(jewels.split(""))
    let ans = 0;
    for (const c of stones) {
        if (jewelsSet.has(c)) ans++
    }
    return ans
};

var numJewelsInStones = function(jewels, stones) {
    // count 中存储 A 到 Z 中的所有的字符，包含 58 个字符
    const start = 'A'.charCodeAt(0)
    const end = 'z'.charCodeAt(0)
    const len = end - start + 1;
    const count = new Array(len).fill(0)
    for (const c of jewels) count[c.charCodeAt(0) - 'A'.charCodeAt(0)] = 1

    let ans = 0;
    for (const c of stones) {
        if (count[c.charCodeAt(0) - 'A'.charCodeAt(0)] == 1) ans++
    }

    return ans
};