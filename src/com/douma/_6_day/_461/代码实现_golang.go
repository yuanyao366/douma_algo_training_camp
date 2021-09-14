func hammingDistance(x int, y int) int {
    // 使用异或计算 x 和 y 的不同位，结果中位为 1 ，说明这位不同
    diff := x ^ y

    // 计算 diff 中位 1 个数
    res := 0
    for diff != 0 {
        // bug 修复：这里是 &，去掉最后一个 1
        diff = diff & (diff - 1)
        res++
    }
    return res
}