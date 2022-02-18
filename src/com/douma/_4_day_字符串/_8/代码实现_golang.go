func myAtoi(s string) int {
    i := 0
    // 1. 丢弃前导空格
    for i < len(s) && s[i] == byte(' ') {
        i++
    }
    if i == len(s) {
        return 0
    }

    // 2. 检查 + 和 - 是否存在
    sign := 1
    if s[i] == byte('-') || s[i] == byte('+') {
        if s[i] == byte('-') {
            sign = -1
        }
        i++
    }

    // 3. 计算结果，并且检查是否溢出
    base := 0
    for i < len(s) && s[i] >= byte('0') && s[i] <= byte('9') {
        if base > math.MaxInt32 / 10 || (base == math.MaxInt32 / 10 && s[i] - byte('0') > 7) {
            if sign > 0 {
                return math.MaxInt32
            } else {
                return math.MinInt32
            }
        }
        base = base * 10 + int(s[i] - byte('0'))
        i++
    }

    return sign * base
}