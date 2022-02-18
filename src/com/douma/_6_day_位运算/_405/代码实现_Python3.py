def toHex(self, num: int) -> str:
    if num == 0: return "0"
    hexChars = '0123456789abcdef'
    res = ""
    # 模拟 32 位整数
    for i in range(8):
        res = hexChars[(num & 15)] + res
        num >>= 4
    return res.lstrip('0') # 删除前导 0