/**
 * @param {number} n
 * @param {number} k
 * @return {number}
 */
var findKthNumber = function(n, k) {

    const calNodes = (n, curr, next) => {
        let nodes = 0
        while (curr <= n) {
            nodes += Math.min(n + 1, next) - curr
            curr *= 10
            next *= 10
        }
        return nodes
    }

    let curr = 1
    k = k - 1
    while (k > 0) {
        const nodes = calNodes(n, curr, curr + 1)
        if (nodes <= k) {
            curr += 1
            k -= nodes
        } else {
            curr *= 10
            k -= 1
        }
    }
    return curr
};