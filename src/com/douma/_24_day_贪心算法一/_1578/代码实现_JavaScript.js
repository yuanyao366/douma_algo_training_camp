/**
 * @param {string} s
 * @param {number[]} cost
 * @return {number}
 */
// 贪心策略：碰到相同字母的时候，我们贪心的删除删除成本最小的字符，也可以说保留删除成本最大的字母
var minCost = function(s, cost) {
    let res = 0, i = 0;
    while (i < s.length) {
        const c = s[i]
        let maxCost = 0, sumCost = 0
        while (i < s.length && s[i] == c) {
            maxCost = Math.max(maxCost, cost[i])
            sumCost += cost[i]
            i++
        }
        res += (sumCost - maxCost)
    }
    return res
};