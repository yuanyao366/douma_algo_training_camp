/**
 * @param {number[]} A
 * @param {number[]} B
 * @return {number[]}
 */
var fairCandySwap = function(A, B) {
    const sumA = _.sum(A), sumB = _.sum(B)
    const delta = Math.floor((sumA - sumB) / 2)
    const set = new Set(A)
    let ans
    for (const y of B) {
        const x = y + delta
        if (set.has(x)) {
            ans = [x, y]
            break
        }
    }
    return ans
};