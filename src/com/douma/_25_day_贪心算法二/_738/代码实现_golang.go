func monotoneIncreasingDigits(n int) int {
    var strN = []byte(strconv.Itoa(n))

    // 1. 找到第一个递减的位
    var i = 1
    for i < len(strN) && strN[i - 1] <= strN[i] {
        i++
    }

    if i < len(strN) {
        // 不断将前一个数字 -1，直到前一个数字小于等于后一个数字
        for i > 0 && strN[i - 1] > strN[i] {
            strN[i - 1] -= 1
            i--
        }

        // 将 i 后面的数字都设置为 9
        i++
        for i < len(strN) {
            strN[i] = '9'
            i++
        }
        var res, _ = strconv.Atoi(string(strN))
        return res
    } else {
        return n
    }
}