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
    if a == math.MinInt32 && b == -1 {
        return math.MaxInt32
    }
    res := 0

    // 处理边界，防止转正数溢出
    // 除数绝对值最大，结果必为 0 或 1
    if b == math.MinInt32 {
        if a == b {
            return 1
        } else {
            return 0
        }
    }

    // 被除数先减去一个除数
    if (a == math.MinInt32) {
        a -= -abs(b);
        res += 1;
    }

    sign := 1
    if (a > 0 && b < 0) || (a < 0 && b > 0) {
        sign = -1
    }

    a = abs(a)
    b = abs(b)


    for i := 31; i >= 0; i-- {
        if (a >> i) - b >= 0 {
            a = a - (b << i)
            // 代码优化：这里控制 ans 大于等于 INT_MAX
            if res > math.MaxInt32 - (1 << i) {
                return math.MinInt32;
            }
            res += 1 << i
        }
    }
    return sign * res
}

func abs(a int) int {
    if a < 0 {
        return -a
    }
    return a
}
