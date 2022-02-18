class Solution:
    # 超时
    def divide1(self, a: int, b: int) -> int:
        INT_MIN, INT_MAX = -2**31, 2**31 - 1
        if a == INT_MIN and b == -1:
            return INT_MAX

        sign = -1 if (a > 0) ^ (b > 0) else 1
        if a > 0:
            a = -a
        if b > 0:
            b = -b

        ans = 0
        while a <= b:
            a -= b
            ans += 1

        # bug 修复：因为不能使用乘号，所以将乘号换成三目运算符
        return ans if sign == 1 else -ans

    # 超时
    def divide1(self, a: int, b: int) -> int:
        INT_MIN, INT_MAX = -2**31, 2**31 - 1
        if a == INT_MIN and b == -1:
            return INT_MAX

        sign = -1 if (a > 0) ^ (b > 0) else 1
        if a > 0:
            a = -a
        if b > 0:
            b = -b

        ans = 0
        while a <= b:
            value, k = b, 1
            while value >= 0xc0000000 and a <= value + value:
                value += value
                if k > INT_MAX // 2:
                    return INT_MIN
                k += k

            ans, a = ans + k, a - value

        # bug 修复：因为不能使用乘号，所以将乘号换成三目运算符
        return ans if sign == 1 else -ans

    def divide(self, a: int, b: int) -> int:
        INT_MIN, INT_MAX = -2**31, 2**31 - 1
        if a == INT_MIN and b == -1:
            return INT_MAX

        ans = 0

        # 处理边界，防止转正数溢出
        if b == INT_MIN:  # 除数绝对值最大，结果必为 0 或 1
            return 1 if a == b else 0
        if a == INT_MIN:  # 被除数先减去一个除数
            a -= -abs(b)
            ans += 1

        sign = -1 if (a > 0) ^ (b > 0) else 1

        a, b = abs(a), abs(b)
        for i in range(31, -1, -1):
            if (a >> i) - b >= 0:
                a = a - (b << i)
                # 代码优化：这里控制 ans 大于等于 INT_MAX
                if ans > INT_MAX - (1 << i):
                    return INT_MIN
                ans += 1 << i
        # bug 修复：因为不能使用乘号，所以将乘号换成三目运算符
        return ans if sign == 1 else -ans
