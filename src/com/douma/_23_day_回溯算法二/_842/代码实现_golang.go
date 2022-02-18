func splitIntoFibonacci(num string) []int {
    var res = make([]int, 0)

    var dfs func(int, int, int) bool
    dfs = func(startIndex int, prevTwoNumSum int, prevNum int) bool {
        if startIndex == len(num) {
            return len(res) >= 3
        }

        var currLongNum = int64(0)
        for i := startIndex; i < len(num); i++ {
            // 如果当前字符是 '0' 的话，则不做处理，因为数字不能以 0 开头
            if i > startIndex && num[startIndex] == '0' {
                continue
            }

            currLongNum = currLongNum * 10 + int64(num[i] - '0')
            if currLongNum > math.MaxInt32 {
                break
            }
            var currNum = int(currLongNum)
            if len(res) >= 2 {
                if currNum < prevTwoNumSum {
                    continue
                } else if currNum > prevTwoNumSum {
                    break
                }
            }

            res = append(res, currNum)
            if dfs(i + 1, currNum + prevNum, currNum) {
                return true
            }
            res = res[:len(res) - 1]
        }
        return false
    }

    dfs(0, 0, 0)
    return res
}