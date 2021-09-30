// 二维费用背包问题
// 物品是字符串数组中的字符串，选择每个字符串有两个代价，分别是 0 的个数和 1 的个数
// 两个代价都有最大值，0 的个数最多为 m，1 的个数最多为 n
// 求选择字符串得到的最大子集的大小
func findMaxForm(strs []string, m int, n int) int {
    // dp[i][j] 表示包含 i 个 0 和 j 个 1 的最大子集的大小
    var dp = make([][]int, m + 1)
    for i := range dp {
        dp[i] = make([]int, n + 1)
    }

    for i := range strs {
        var count = countzeroesones(strs[i])
        var zeroes, ones = count[0], count[1]
        for zero := m; zero >= zeroes; zero-- {
            for one := n; one >= ones; one-- {
                if dp[zero - zeroes][one - ones] + 1 > dp[zero][one] {
                    dp[zero][one] = dp[zero - zeroes][one - ones] + 1
                }
            }
        }
    }

    return dp[m][n]
}

func countzeroesones(str string) [2]int {
    var res = [2]int{}
    for i := range str {
        res[str[i] - '0']++
    }
    return res
}