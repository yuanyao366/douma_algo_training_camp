// 贪心策略：拿高位后面比高位大的值进行交换，而且越大越好
func maximumSwap(num int) int {
    var chars = []byte(strconv.Itoa(num))

    var last = [10]int{}
    for i := range chars {
        last[chars[i] - '0'] = i
    }

    for i := range chars {
        for d := 9; d > int(chars[i] - '0'); d-- { // bug 修复：d--
            if last[d] > i {
                chars[i], chars[last[d]] = chars[last[d]], chars[i]
                var res, _ = strconv.Atoi(string(chars))
                return res
            }
        }
    }

    return num
}