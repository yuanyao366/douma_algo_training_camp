// 从左往右遍历
func lengthOfLastWord1(s string) int {
    ans, start, end := 0, 0, 0
    for end < len(s) {
        if s[start] == byte(' ') {
            start++
            end ++
        } else {
            for end < len(s) && s[end] != byte(' ') {end++}
            ans = end - start
            for end < len(s) && s[end] == byte(' ') {end++}
            if end < len(s) && s[end] != byte(' ') {
                start = end
            }
        }
    }
    return ans
}

// 从右往左遍历
func lengthOfLastWord(s string) int {
    end := len(s) - 1
    for end >= 0 && s[end] == byte(' ') {end--}
    if end < 0 {return 0}
    start := end
    for start >= 0 && s[start] != byte(' ') {start--}
    return end - start
}