/**
 * @param {number[]} stones
 * @return {number}
 */
/*
    记石头的总重量为 sum，被粉碎的重量是 neg，那么没有粉碎的石头重量为 sum - neg
    最后一块石头的重量是：(sum - neg) - neg = sum - 2 * neg

    要使最后一块石头的重量尽可能地小，neg 需要在不超过 sum/2 的前提下尽可能地大

    因此本问题可以看作是背包容量为 sum/2，物品重量和价值均为 stones的 0-1 背包问题。
     */
var lastStoneWeightII = function(stones) {
    let sum = 0
    for (const weight of stones) {
        sum += weight
    }
    // 背包的容量
    const m = Math.floor(sum / 2)

    // dp[c]：表示是否可以将石头放入到容量为 c 的背包中
    const dp = new Array(m + 1).fill(false)
    dp[0] = true;

    for (let i = 0; i < stones.length; i++) {
        for (let c = m; c >= stones[i]; c--) {
            dp[c] = dp[c] || dp[c - stones[i]];
        }
    }

    for (let i = m; ; i--) {
        if (dp[i]) return sum - 2 * i;
    }
};