func canPlaceFlowers(flowerbed []int, n int) bool {
    i := 0
    // 当所有花坛遍历完或者花种完了，则停止循环
    for i < len(flowerbed) && n > 0 {
        if flowerbed[i] == 1 {
            // 如果当前花坛已经种花，那么至少需要到 i + 2 的地方才能种花
            i += 2
        } else if i == len(flowerbed) - 1 || flowerbed[i + 1] == 0 {
            // 注意：必须将 i == flowerbed.length - 1 放在前面， 否则 i + 1 可能会越界
            // 这里用到了或运算的特点：或的前面表达式为 false 的话，就不会去指定或的后面的表达式
            // i 没有种花，且是最后一个花坛
            // 或者 i 和 i + 1 的位置都没有种花
            // 那么 i 处肯定可以种植一朵花
            n--
            // 至此，至少需要到 i + 2 的地方才能种花
            i += 2
        } else {
            // i 处没有种花，但是 i + 1 处种花了
            // 那么这个时候，至少需要到 i + 3 的地方才能种花
            i += 3
        }
    }
    return n <= 0
}