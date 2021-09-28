func isUgly1(n int) bool {
    if n == 0 {
        return false
    }
    return dfs(n)
}

// 判断整数 n 是否可以被 2、3、5 整除
func dfs(n int) bool {
    if n == 1 {
        return true
    }

    var factors = []int{2, 3, 5}
    for _, factor := range factors {
        if n % factor == 0 && dfs(n / factor) {
            return true
        }
    }

    return false
}

func isUgly(n int) bool {
    if n == 0 {
        return false
    }
    var factors = []int{2, 3, 5}
    for _, factor := range factors {
        for n % factor == 0 {
            n /= factor
        }
    }
    return n == 1
}