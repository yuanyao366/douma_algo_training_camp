// 位运算
func singleNumber1(nums []int) int {
    once, twice := 0, 0
    for _, num := range nums {
        once = (once ^ num) & ^twice
        twice = (twice ^ num) & ^once
    }
    return once
}

func singleNumber(nums []int) int {
    res := int32(0)
    //int类型有32位，统计每一位1的个数
    for i := 0; i < 32; i++ {
        //统计第 i 位中 1 的个数
        oneCnt := int32(0)
        for _, num := range nums {
            oneCnt += (int32(num) >> i) & 1
        }
        //如果1的个数不是3的倍数，说明那个只出现一次的数字
        //的二进制位中在这一位是1
        if oneCnt % 3 == 1 {
            res |= 1 << i
        }
    }
    return int(res)
}