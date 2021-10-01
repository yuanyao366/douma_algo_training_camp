/*
    记石头的总重量为 sum，被粉碎的重量是 neg，那么没有粉碎的石头重量为 sum - neg
    最后一块石头的重量是：(sum - neg) - neg = sum - 2 * neg

    要使最后一块石头的重量尽可能地小，neg 需要在不超过 sum/2 的前提下尽可能地大

    因此本问题可以看作是背包容量为 sum/2，物品重量和价值均为 stones的 0-1 背包问题。
*/
func lastStoneWeightII(stones []int) int {
    var sum = 0
    for _, weight := range stones {
        sum += weight
    }

     // 背包的容量
     var m = sum / 2

     // dp[c]：表示是否可以将石头放入到容量为 c 的背包中
     var dp = make([]bool, m + 1)
     dp[0] = true

     for i := 0; i < len(stones); i++ {
         for c := m; c >= stones[i]; c-- {
             dp[c] = dp[c] || dp[c - stones[i]]
         }
     }

     for i := m; ; i-- {
         if dp[i] {
             return sum - 2 * i
         }
     }
     return -1
}