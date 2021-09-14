 // 方法一：溢出之前判断
func reverse(x int) int {
    res := 0
    for x != 0 {
        pop := x % 10
        x = x / 10
        // bug 修复：这里是除以 10
        if res > math.MaxInt32 / 10 || (res == math.MaxInt32 / 10 && pop > 7) {
            return 0
        }
        if res < math.MinInt32 / 10 || (res == math.MinInt32 / 10 && pop < -8) {
            return 0
        }

        res = res * 10 + pop
    }
    return res
}