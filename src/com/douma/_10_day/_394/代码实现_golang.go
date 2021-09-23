func decodeString(s string) string {
    var res, num = "", 0
    var numStack, strStack = []int{}, []string{}
    for _, c := range s {
        if c >= '0' && c <= '9' {
            num = num * 10 + int(c - '0')
        } else if c == '[' {
            numStack = append(numStack, num)
            strStack = append(strStack, res)
            res, num = "", 0
        } else if c == ']' {
            var item = res
            var itemCnt = numStack[len(numStack) - 1]
            for i := 1; i < itemCnt; i++ {
                res += item
            }
            res = strStack[len(strStack) - 1] + res
            numStack = numStack[:len(numStack) - 1]
            strStack = strStack[:len(strStack) - 1]
        } else {
            res += string(c)
        }
    }
    return res
}