func reverseWords(s string) string {
    t, n, left := []byte(s), len(s), 0

    for left < n {
        if t[left] != byte(' ') {
            right := left
            for right + 1 < n && t[right + 1] != byte(' ') {
                right++
            }
            reverseWord(t, left, right)
            left = right + 1
        } else {
            left++
        }
    }

    return string(t)
}


func reverseWord(cArr []byte, start int, end int) {
    for start < end {
        cArr[start], cArr[end] = cArr[end], cArr[start]
        start++
        end--
    }
}