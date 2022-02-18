def getSum(self, a: int, b: int) -> int:
    # 32 bits integer max [0111 1111 1111 1111 1111 1111 1111 1111]
    MAX = 0x7FFFFFFF
    # mask to get last 32 bits
    mask = 0xFFFFFFFF
    while b != 0:
        # ^ get different bits and & gets double 1s, << moves carry
        a, b = (a ^ b) & mask, ((a & b) << 1) & mask
    # 如果 a > MAX 的话，那么将 a 的补码转成负数的原码
    return a if a <= MAX else ~(a ^ mask)