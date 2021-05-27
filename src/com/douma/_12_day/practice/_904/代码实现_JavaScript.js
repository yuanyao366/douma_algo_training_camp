/**
 * @param {number[]} tree
 * @return {number}
 */
var totalFruit = function(tree) {
    let res = 0, left = 0, right = 0
    const counter = new Map()
    while (right < tree.length) {
        if (counter.has(tree[right])) {
            counter.set(tree[right], counter.get(tree[right]) + 1)
        } else {
            counter.set(tree[right], 1)
        }

        while (counter.size > 2) {
            counter.set(tree[left], counter.get(tree[left]) - 1)
            if (counter.get(tree[left]) == 0) counter.delete(tree[left])
            left++
        }

        res = Math.max(res, right - left + 1)
        right++
    }
    return res
};