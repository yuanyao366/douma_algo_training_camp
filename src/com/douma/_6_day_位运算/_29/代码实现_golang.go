// 超时
// 因为将 -2147483648 转成正数会越界，但是将 2147483647 转成负数，则不会
// 所以，我们将 a 和 b 都转成负数
// 时间复杂度：O(n)，n 是最大值 2147483647 --> 10^10
func divide1(a int, b int) int {
    if a == math.MinInt32 && b == -1 {
        return math.MaxInt32
    }

    sign := 1
    if (a > 0 && b < 0) || (a < 0 && b > 0) {
        sign = -1
    }

    if a > 0 {
        a = -a
    }
    if b > 0 {
        b = -b
    }

    res := 0
    for a <= b {
        a -= b
        res++
    }
    if sign == 1 {
        return res
    } else {
        return -res
    }
}

// 超时 时间复杂度：O(logn * logn)，n 是最大值 2147483647 --> 10^10
func divide2(a int, b int) int {
    if a == math.MinInt32 && b == -1 {
        return math.MaxInt32
    }

    sign := 1
    if (a > 0 && b < 0) || (a < 0 && b > 0) {
        sign = -1
    }

    if a > 0 {
        a = -a
    }
    if b > 0 {
        b = -b
    }

    res := 0
    for a <= b {
        value, k := b, 1
        // 0xc0000000 是十进制 -2^30 的十六进制的表示
        // 判断 value >= 0xc0000000 的原因：保证 value + value 不会溢出
        // 可以这样判断的原因是：0xc0000000 是最小值 -2^31 的一半，
        // 而 a 的值不可能比 -2^31 还要小，所以 value 不可能比 0xc0000000 小
        // -2^31 / 2 = -2^30
        for value >= 0xc0000000 && a <= value + value {
            value += value
            // 代码优化：如果 k 已经大于最大值的一半的话，那么直接返回最小值
            // 因为这个时候 k += k 的话肯定会大于等于 2147483648 ，这个超过了题目给的范围
            if k > math.MaxInt32 / 2 {
                return math.MinInt32
            }
            k += k
        }
        a -= value
        res += k
    }
    if sign == 1 {
        return res
    } else {
        return -res
    }
}

// 时间复杂度：O(1)
func divide(a int, b int) int {
    if (a == INT_MIN && b == -1) return INT_MAX;

    int res = 0;
    // 处理边界，防止转正数溢出
    // 除数绝对值最大，结果必为 0 或 1
    if (b == INT_MIN) {
        return a == b? 1 : 0;
    }

    // 被除数先减去一个除数
    if (a == INT_MIN) {
        a -= -abs(b);
        res += 1;
    }

    int sign = (a > 0) ^ (b > 0) ? -1 : 1;

    int ua = abs(a);
    int ub = abs(b);
    for (int i = 31; i >= 0; i--) {
        if ((ua >> i) >= ub) {
            ua = ua - (ub << i);
            // 代码优化：这里控制 ans 大于等于 INT_MAX
            if (res > INT_MAX - (1 << i)) {
                return INT_MIN;
            }
            res += 1 << i;
        }
    }
    // bug 修复：因为不能使用乘号，所以将乘号换成三目运算符
    return sign == 1 ? res : -res;
}

func abs(a int) int {
    if a < 0 {
        return -a
    }
    return a
}