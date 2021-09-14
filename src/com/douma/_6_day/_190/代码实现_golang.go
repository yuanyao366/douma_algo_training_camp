func reverseBits1(num uint32) uint32 {
    res := uint32(0)
    for i := 0; i < 32; i++ {
        res = (res << 1) | (num & 1)
        num >>= 1
    }
    return res
}

const (
    M1 = 0x55555555 // 01010101010101010101010101010101
    M2 = 0x33333333 // 00110011001100110011001100110011
    M4 = 0x0f0f0f0f // 00001111000011110000111100001111
    M8 = 0x00ff00ff // 00000000111111110000000011111111
)

// 分治
func reverseBits(num uint32) uint32 {
    num = num >> 1 & M1 | num & M1 << 1
    num = num >> 2 & M2 | num & M2 << 2
    num = num >> 4 & M4 | num & M4 << 4
    num = num >> 8 & M8 | num & M8 << 8
    return num >> 16 | num << 16
}