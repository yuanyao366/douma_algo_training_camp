/**
 * @param {number[]} A
 * @param {number[]} B
 * @return {number[]}
 */
var fairCandySwap = function(A, B) {
    // 第一个参数 prev 表示前一个元素
    // 第二个参数 curr 表示当前元素
    // 第三个参数表示当前元素对应的索引，这里不用，所以使用 _ 占位
    const sumA = A.reduce((prev, curr, _) => {
        return prev + curr;
    })
    const sumB = B.reduce((prev, curr, _) => {
        return prev + curr;
    })

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