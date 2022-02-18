func countAndSay(n int) string {
    curr, prev := "1", ""

    for i := 1; i < n; i++ {
        prev = curr
        curr = ""

        say, count := prev[0], 1
        for j := 1; j < len(prev); j++ {
            if prev[j] == say {
                count++
            } else {
                curr += strconv.Itoa(count) + string(say)
                say = prev[j]
                count = 1
            }
        }
        curr += strconv.Itoa(count) + string(say)
    }

    return curr
}