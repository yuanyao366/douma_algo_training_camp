func calculate(s string) int {
    var stack = []int{}
    var preSign, i = byte('+'), 0
    for i < len(s) {
        if (s[i] == ' ') {
            i++
        } else {
            var num = 0
            for i < len(s) && s[i] <= '9' && s[i] >= '0' {
                num = num * 10 + int(s[i] - '0')
                i++
            }

            if preSign == '+' {
                stack = append(stack, num)
            } else if preSign == '-' {
                stack = append(stack, -num)
            } else if preSign == '*' {
                var tmp = stack[len(stack) - 1]
                stack = stack[:len(stack) - 1]
                stack = append(stack, tmp * num)
            } else if preSign == '/' {
                var tmp = stack[len(stack) - 1]
                stack = stack[:len(stack) - 1]
                stack = append(stack, tmp / num)
            }

            // 去掉空格，目的是拿到下一个符号字符
            for i < len(s) && s[i] == ' ' {
                i++
            }
            if i < len(s) {
                preSign = s[i]
            }
            i++
        }
    }
    var res = 0
    for _, num := range stack {
        res += num
    }
    return res
}