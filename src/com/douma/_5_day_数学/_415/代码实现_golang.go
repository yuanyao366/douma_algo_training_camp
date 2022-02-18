func addStrings(num1 string, num2 string) string {
    res := ""
    carry := 0
    l1, l2 := len(num1) - 1, len(num2) - 1
    for l1 >= 0 || l2 >= 0 {
        x, y := 0, 0
        if l1 >= 0 {
            x = int(num1[l1] - byte('0'))
        }
        if l2 >= 0 {
            y = int(num2[l2] - byte('0'))
        }

        sum := x + y + carry
        res += strconv.Itoa(sum % 10)
        carry = sum / 10

        l1--
        l2--
    }
    if carry != 0 {
        res += strconv.Itoa(carry)
    }

    return reverseString(res)
}

func reverseString(str string) string {
    temp := []byte(str)
    left, right := 0, len(temp) - 1
    for left < right {
        temp[left], temp[right] = temp[right], temp[left]
        left++
        right--
    }
    return string(temp)
}

