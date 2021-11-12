// O(nlog(logn))
func countPrimes(n int) int {
    ans := 0

    notPrimes := make([]bool, n)
    for x := 2; x < n; x++ {
        if notPrimes[x] {
            continue
        }
        ans++
        // 如果 x 是质数，那么 2x、3x、4x.... 肯定不是质数
        // 说明：这里 i 最好是从 x + x 开始，因为 x 有可能是质数
        // 其实 i 从 x 开始也没啥问题，因为 x 在上面已经判断过了
        // 但是，这样就违背了 notPrimes 数组的含义了，所以这里修改为 x + x
        for i := x + x; i < n; i += x {
            notPrimes[i] = true
        }
    }
    return ans
}