func minSwaps(data []int) int {
    // 1. 统计数组中元素值等于 1 的个数
    var k = 0
    for _, x := range data {
        if x == 1 {
            k++
        }
    }

    // 维护窗口大小为 k 的滑动窗口
    var left, right = 0, 0
    // 存储每个窗口中 0 的数量
    var windowZeroCnt = 0
    // 所有窗口中最少的 0 的数量
    var minZeroCnt = math.MaxInt32

    for right < len(data) {
        if data[right] == 0 {
            windowZeroCnt++
        }
        if right - left + 1 == k {
            if windowZeroCnt < minZeroCnt {
                minZeroCnt = windowZeroCnt
            }
            if data[left] == 0 {
                windowZeroCnt--
            }
            left++
        }
        right++
    }

    if minZeroCnt == math.MaxInt32 {
        return 0
    } else {
        return minZeroCnt
    }
}