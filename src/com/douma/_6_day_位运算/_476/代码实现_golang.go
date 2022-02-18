func findComplement(num int) int {
    // go 中可以使用 ^ 来表示取反
    mask := ^0
    for (num & mask) > 0 {
        mask <<= 1
    }
    return (^mask) ^ num
}