
// O(n) --> 超时
func myPow1(x float64, n int) float64 {
    if n < 0 {
        x  = 1 / x
        n = -n
    }
    res := float64(1)
    for i := 0; i < n; i++ {
        res *= x
    }
    return res
}

// 快速幂 + 递归
// 时间：O(logn)
// 空间：O(logn)
func myPow2(x float64, n int) float64 {
    if n < 0 {
        x  = 1 / x
        n = -n
    }
    return quickPow(x, n)
}

func quickPow(x float64, n int) float64 {
    if n == 0 {
        return 1.0
    }
    if n == 1 {
        return x
    }
    mid := n / 2
    y := quickPow(x, mid)
    if n % 2 == 0 {
        return y * y
    }
    return x * y * y
}

// 快速幂 + 迭代
// 时间：O(32)
// 空间：O(1)
func myPow(x float64, n int) float64 {
    if n < 0 {
        x  = 1 / x
        n = -n
    }
    res := float64(1)
    for n != 0 {
        if (n & 1) == 1 {
            res *= x
        }
        x *= x
        n >>= 1
    }
    return res
}