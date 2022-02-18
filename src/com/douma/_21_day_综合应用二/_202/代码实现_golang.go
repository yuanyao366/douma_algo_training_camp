// 哈希表
func isHappy1(n int) bool {
    var set = make(map[int]bool)
    for true {
        if n == 1 {
            return true
        }
        if set[n] {
            return false
        }

        set[n] = true
        n = squareSum(n)
    }
    panic("这里是不会执行到的")
}

func isHappy(n int) bool {
    if n == 1 {
        return true
    }

    var slow, fast = n, n
    for true {
        slow = squareSum(slow)
        fast = squareSum(squareSum(fast))

        if slow == 1 || fast == 1 {
            return true
        }
        if slow == fast {
            return false
        }
    }

    panic("这里是不会执行到的")
}


func squareSum(n int) int {
    var sum = 0
    for n != 0 {
        // bug 修复： % 10 是为了拿到个位数
        var num = n % 10
        sum += num * num
        n /= 10
    }
    return sum
}


