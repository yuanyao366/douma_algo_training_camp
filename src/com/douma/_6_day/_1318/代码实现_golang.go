func minFlips(a int, b int, c int) int {
    aOrb := a | b
    equal := aOrb ^ c
    if equal == 0 {
        return 0
    }

    ans := 0
    for i := 0; i < 31; i++ {
        mask := 1 << i
        // a | b 和 c 的第 i 位不同，那么至少需要翻转 1 次
        if (equal & mask) > 0 {
            // 如果 a 和 b 的第 i 位是 1，且 c 的第 i 位是 0，那么需要翻转 2 次
            if (c & mask) == 0 && (a & mask) == (b & mask) {
                ans += 2
            } else {
                ans++
            }
        }
    }

    return ans
}