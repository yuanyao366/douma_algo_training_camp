/**
 * @param {number[][]} costs
 * @return {number}
 */
/*
我们这样来看这个问题，公司首先将这 2N 个人全都安排飞往 B 市，
再选出 N 个人改变它们的行程，让他们飞往 A 市。
如果选择改变一个人的行程，那么公司将会额外付出 price_A - price_B 的费用，这个费用可正可负。

因此最优的方案是，选出 price_A - price_B 最小的 N 个人，让他们飞往 A 市，其余人飞往 B 市。
 */
var twoCitySchedCost = function(costs) {
    costs.sort((x, y) => {
        return x[0] - x[1] - (y[0] - y[1])
    })

    let total = 0
    const n = Math.floor(costs.length / 2)
    for (let i = 0; i < n; i++) {
        total += costs[i][0] + costs[i + n][1]
    }
    return total
};