func isPowerOfTwo1(n int) bool {
    if n == 0 {
        return false
    }

    for n % 2 == 0 {
        n = n / 2
    }

    return n == 1
}


func isPowerOfTwo2(n int) bool {
    if n == 0 {
        return false
    }
    return (n & (n - 1)) == 0
}

func isPowerOfTwo(n int) bool {
    if n == 0 {
        return false
    }
    return (n & -n) == n
}