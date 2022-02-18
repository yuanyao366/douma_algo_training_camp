// O(logn)
func countDigitOne(n int) int {
    count := 0
    for i := 1; i <= n; i *= 10 {
        // (n/10)*1 + min(max((n%10 - 1 + 1), 0), 1)
        // (n/100)*10 + min(max((n%100 - 10 + 1), 0), 10)
        // (n/1000)*100 + min(max((n%1000 - 100 + 1), 0), 100)
        divider := i * 10
        count += (n / divider) * i + min(max(n % divider - i + 1, 0), i)
    }
    return count
}

func min(x int, y int) int {
    if x < y {
        return x
    }
    return y
}

func max(x int, y int) int {
    if x > y {
        return x
    }
    return y
}