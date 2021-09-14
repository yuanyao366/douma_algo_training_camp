func toHex(num int) string {
    if num == 0 {
        return "0"
    }
    hexChars := []byte{'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'}
    res := ""
    // 先转成无符号整数，因为 go 中没有无符号右移
    temp := uint32(num)
    for temp != 0 {
        index := temp & 15
        res = string(hexChars[index]) + res
        temp >>= 4
    }
    return res
}