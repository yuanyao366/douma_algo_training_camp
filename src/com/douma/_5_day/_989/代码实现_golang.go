func addToArrayForm(num []int, k int) []int {
    res := make([]int, 0)
    carry := 0
    l1 := len(num) - 1
    for l1 >= 0 || k != 0 {
        x, y := 0, 0
        if l1 >= 0 {
            x = num[l1]
        }
        if k != 0 {
            y = k % 10
        }

        sum := x + y + carry
        res = append(res, sum % 10)
        carry = sum / 10

        l1--
        k /= 10
    }
    if carry != 0 {
        res = append(res, carry)
    }

    // 反转 res
    left, right := 0, len(res) - 1
    for left < right {
        res[left], res[right] = res[right], res[left]
        left++
        right--
    }

    return res
}