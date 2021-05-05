def reverseBits(self, n: int) -> int:
    # 01010101010101010101010101010101
    M1 = 0x55555555
    # 00110011001100110011001100110011
    M2 = 0x33333333
    # 00001111000011110000111100001111
    M4 = 0x0f0f0f0f
    # 00000000111111110000000011111111
    M8 = 0x00ff00ff
    # python 中没有 32 位的 int，我们需要将数字强转成 32 位 int
    n = ((n >> 1) & M1) | ((n & M1) << 1) & 0xffffffff
    n = ((n >> 2) & M2) | ((n & M2) << 2) & 0xffffffff
    n = ((n >> 4) & M4) | ((n & M4) << 4) & 0xffffffff
    n = ((n >> 8) & M8) | ((n & M8) << 8) & 0xffffffff
    return (n >> 16) | (n << 16) & 0xffffffff

def reverseBits1(self, n: int) -> int:
    res = 0
    for i in range(32):
        res = (res << 1) | (n & 1)
        n >>= 1
    return res