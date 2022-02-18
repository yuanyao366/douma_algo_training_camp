func fairCandySwap(aliceSizes []int, bobSizes []int) []int {
    var sumA, sumB = 0, 0
    for _, num := range aliceSizes {
        sumA += num
    }
    for _, num := range bobSizes {
        sumB += num
    }

    var delta = (sumA - sumB) / 2

    var set = make(map[int]bool)
    for _, num := range aliceSizes {
        set[num] = true
    }

    var ans = make([]int , 2)
    for _, y := range bobSizes {
        var x = y + delta
        if set[x] {
            ans[0] = x
            // bug 修复：将 y 设置给第二个元素
            ans[1] = y
            break
        }
    }

    return ans
}