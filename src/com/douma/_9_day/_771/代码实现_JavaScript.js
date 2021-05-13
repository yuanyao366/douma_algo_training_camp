/**
 * @param {string} jewels
 * @param {string} stones
 * @return {number}
 */
var numJewelsInStones = function(jewels, stones) {
    const jewelsSet = new Set(jewels.split(""))
    let ans = 0;
    for (const c of stones) {
        if (jewelsSet.has(c)) ans++
    }
    return ans
};