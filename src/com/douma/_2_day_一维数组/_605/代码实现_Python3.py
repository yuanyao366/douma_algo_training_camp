from typing import List


def canPlaceFlowers(self, flowerbed: List[int], n: int) -> bool:
    i, l = 0, len(flowerbed)
    while i < l and n > 0:
        if flowerbed[i] == 1:
            i += 2
        elif i == l - 1 or flowerbed[i + 1] == 0:
            # 注意：必须将 i == flowerbed.length - 1 放在前面， 否则 i + 1 可能会越界
            # 这里用到了或运算的特点：或的前面表达式为 false 的话，就不会去指定或的后面的表达式
            n -= 1
            i += 2
        else:
            i += 3
    return n <= 0
