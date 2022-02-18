// 双指针
func isPalindrome1(x int) bool {
    chars := []byte(strconv.Itoa(x))
    left, right := 0, len(chars) - 1
    for left < right {
        if chars[left] != chars[right] {
            return false
        }
        left++
        right--
    }
    return true
}

// 整数反转
func isPalindrome2(x int) bool {
    if x == 0 {
        return true
    }
    if x < 0 || x % 10 == 0 {
        return false
    }

    res, y := 0, x
    for x != 0 {
        pop := x % 10
        x = x / 10
        // bug 修复：这里是除以 10
        if res > math.MaxInt32 / 10 || (res == math.MaxInt32 / 10 && pop > 7) {
            return false
        }
        if res < math.MinInt32 / 10 || (res == math.MinInt32 / 10 && pop < -8) {
            return false
        }

        res = res * 10 + pop
    }

    return y == res
}

// 整数反转一半
func isPalindrome(x int) bool {
    if x == 0 {
        return true
    }
    if x < 0 || x % 10 == 0 {
        return false
    }

    res := 0
    for res < x {
        pop := x % 10
        x = x / 10
        res = res * 10 + pop
    }
    // 当数字长度为奇数时，我们可以通过 res/10 去除处于个位的数字。
    // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，res = 123，
    // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
    return res == x || x == res / 10
}