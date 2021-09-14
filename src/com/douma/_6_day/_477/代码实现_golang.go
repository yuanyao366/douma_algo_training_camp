func totalHammingDistance(nums []int) int {
    // 我们考虑数组中每个数二进制的第 i 位，
    // 假设一共有 t 个 1 和 n - t 个 0，
    // 那么显然在第 i 位的汉明距离的总和为 t * (n - t)
    n := len(nums)
    // 存储所有元素对应位的 1 的个数
    cnt := make([]int, 32)
    for _, num := range nums {
        i := 0
        for num > 0 {
            // 检查每个位是否为 1，并累加
            cnt[i] += num & 1
            num >>= 1
            i++
        }
    }

    res := 0
    for _, k := range cnt {
        res += k * (n - k)
    }
    return res
}