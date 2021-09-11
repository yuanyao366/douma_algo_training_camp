func sortString(s string) string {
    counts := [26]int{}
    for _, c := range s {
        counts[c - 'a']++
    }

    n := len(s)
    res := make([]byte, 0, n)
    for len(res) < n {
        // 上升
        for i := 0; i < 26; i++ {
            if counts[i] > 0 {
                res = append(res, byte('a' + i))
                counts[i]--
            }
        }

        // 下降
        for i := 25; i>= 0; i-- {
            if counts[i] > 0 {
                res = append(res, byte('a' + i))
                counts[i]--
            }
        }
    }

    return string(res)
}