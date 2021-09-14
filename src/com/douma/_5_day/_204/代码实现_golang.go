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
        for i := x; i < n; i += x {
            notPrimes[i] = true
        }
    }
    return ans
}