// 哈希表查重
func singleNumber1(nums []int) int {
    single := make(map[int]int)
    for _, num := range nums {
        single[num]++
    }
    for k, v := range single {
        if v == 1 {
            return k
        }
    }
    return 0
}

// 位运算 - 异或
func singleNumber(nums []int) int {
    single := 0
    for _, num := range nums {
        single ^= num
    }
    return single
}