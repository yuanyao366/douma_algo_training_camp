func multiply(num1 string, num2 string) string {
    if num1 == "0" || num2 == "0" {
        return "0"
    }

    m, n := len(num1), len(num2)
    res := make([]int, m + n)

    for i := m - 1; i >= 0; i-- {
        x := int(num1[i] - byte('0'))
        for j := n - 1; j >= 0; j-- {
            y := int(num2[j] - byte('0'))
            sum := res[i + j + 1] + x * y
            res[i + j + 1] = sum % 10
            res[i + j] += sum / 10
        }
    }

    ans := ""
    for i := 0; i < len(res); i++ {
        if i == 0 && res[i] == 0 {
            continue
        }
        ans += strconv.Itoa(res[i])
    }

    return ans
}