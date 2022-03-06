// 题意：找出二维数组的一个排列，使得其中有最长的单调递增子序列（两个维度都递增）
// 就是 300 号算法题的变形体
func maxEnvelopes(envelopes [][]int) int {
    var n = len(envelopes)
    if n <= 1 {
        return n
    }

    sort.Slice(envelopes, func(i, j int) bool {
        if envelopes[i][0] != envelopes[j][0] {
            return envelopes[i][0] < envelopes[j][0]
        } else {
            // bug 修复：按照高度降序排列
            return envelopes[i][1] > envelopes[j][1]
        }
    })

    var maxLen = 1
    // 状态：dp[i] 表示以 i 对应元素结尾的时候最长递增子序列的长度
    var dp = make([]int, n)
    // 状态初始化：单个元素最少有一个递增子序列元素
    for i := range dp {
        dp[i] = 1
    }
    for j := 0; j < n; j++ {
        for i := 0; i < j; i++ {
            if envelopes[j][1] > envelopes[i][1] {
                if dp[i] + 1 > dp[j] {
                    dp[j] = dp[i] + 1
                }
                if dp[j] > maxLen {
                    maxLen = dp[j]
                }
            }
        }
    }

    return maxLen
}


/*
俄罗斯套娃这道题的输入改掉了，之前的输入数据规模是 5000 ，所以时间复杂度为 O(n^2) 的动态规划方案是可以的

现在的输入数据规模改为 10^5 ，所以，时间复杂度为 O(n^2) 会超时了
现在题目的输入数据规模为：
1 <= envelopes.length <= 10^5
envelopes[i].length == 2
1 <= wi, hi <= 10^5
 */
// 所以需要使用二分解法
// 时间复杂度 O(nlogn)
// 空间复杂度 O(n)
func maxEnvelopes(envelopes [][]int) int {
    var n = len(envelopes)
    if n <= 1 {
        return n
    }

    sort.Slice(envelopes, func(i, j int) bool {
        if envelopes[i][0] != envelopes[j][0] {
            // 先按照宽度升序排列
            return envelopes[i][0] < envelopes[j][0]
        } else {
            // 宽度相同的话，按照高度降序排列
            return envelopes[i][1] > envelopes[j][1]
        }
    })

    // res 用于存储所有可套娃信封的高度值
    // 保证 res 中的元素肯定是升序排列的
    var res = []int{}

    // 1. 将宽度最小的信封的高度值放在 res 中
    res = append(res, envelopes[0][1])

    // 2. 从第二个信封开始，遍历并处理每一个信封
    for i := 1; i < n; i++ {
        var currHeight = envelopes[i][1]
        // 2.1 如果当前信封的高度大于 res 中最后一个信封的高度
        if currHeight > res[len(res) - 1] {
            // 那么，可以将当前的信封放入到 res 中
            // (当前信封的宽度肯定大于等于果集中最后一个信封的宽度，因为是按照宽度升序排列的)
            res = append(res, currHeight)
        } else { // 2.2 当前信封的高度小于等于 res 中最后一个信封的高度
            // 将当前信封插入到 res 合适的位置上
            // 先使用二分查找，在 res 中查找 currHeight 合适的位置
            var index = sort.SearchInts(res, currHeight)
            // 将当前信封替换之前这个位置上的信封
            // 之所以可以将当前信封替换掉第 index 处的信封，是因为：
            // ① 当前信封的宽度大于等于第 index - 1 处的信封的宽度
            // ② 当前信封的高度大于第 index - 1 处的信封的高度
            res[index] = currHeight
        }
    }

    return len(res)
}