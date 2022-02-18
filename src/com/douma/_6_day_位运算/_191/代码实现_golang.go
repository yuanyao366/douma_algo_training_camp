func hammingWeight1(num uint32) int {
    res := 0
    for i := 0; i < 32; i++ {
        if (num & (1 << i)) != 0 {
            res++
        }
    }
    return res
}

func hammingWeight2(num uint32) int {
    res := uint32(0)
    for i := 0; i < 32; i++ {
        // bug 修复：这里不需要判断最后一位是不是 1，因为会遍历 32 个位
        res += num & 1
        num >>= 1
    }
    return int(res)
}

func hammingWeight(num uint32) int {
    res := 0
    for num != 0 {
        num = num & (num - 1)
        res++
    }
    return res
}