func reverseStr(s string, k int) string {
    t := []byte(s)
    for start := 0; start < len(s); start += 2 * k {
        left := start
        // 主要是判断后面 k个字符是否超过数组的长度，如果超过，就将后面的所有字符反转
        right := left + k - 1
        if right > len(s) - 1 {
            right = len(s) - 1
        }
        for left < right {
            t[left], t[right] = t[right], t[left]
            left++
            right--
        }
    }
    return string(t)
}