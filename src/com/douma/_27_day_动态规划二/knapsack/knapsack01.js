/*
    0 - 1 背包：
        有 n 种物品和一个容量为 C 的背包，
        第 i 件物品的重量是 w[i]，价值是 v[i]，件数是 1 件
        求将哪些物品装入背包可使得价值总和最大
 */
var knapsack01 = function(w, v, C) {
    // 1. 状态定义：dp[c] : 将物品放入容量为 c 的背包中产生的最大价值
    var dp = new Array(C + 1).fill(0)

    // 2. 状态初始化

    // 3. 状态转移
    for (let i = 0; i < w.length; i++) {
        for (let c = C; c >= w[i]; c--) {
            dp[c] = Math.max(dp[c], v[i] + dp[c - w[i]]);
        }
    }

    return dp[C];
}