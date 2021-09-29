func partition(s string) [][]string {
    var res, path = make([][]string, 0), make([]string, 0)

    var dfs func(int)
    dfs = func(startIndex int) {
        if startIndex == len(s) {
            var tmp = make([]string, len(path))
            copy(tmp, path)
            res = append(res, tmp)
            return
        }

        for i := startIndex; i < len(s); i++ {
            var endIndex = i
            // [startIndex, endIndex]
            if !checkPalindrome(s, startIndex, endIndex) {
                continue
            }
            path = append(path, s[startIndex:endIndex + 1])
            dfs(i + 1)
            path = path[:len(path) - 1]
        }
    }

    dfs(0)
    return res
}

func checkPalindrome(str string, left int, right int) bool {
    for left < right {
        if str[left] != str[right] {
            return false
        }
        left++
        right--
    }
    return true
}