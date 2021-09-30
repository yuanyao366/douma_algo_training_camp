func reorganizeString(s string) string {
    var n = len(s)
    // 1. 统计每个字符出现的次数
    var count = [26]int{}
    for _, c := range s {
        var index = c - 'a'
        count[index]++
        if count[index] > (n + 1) / 2 {
            return ""
        }
    }

    // 2. 拿到出现次数最多的字符
    var maxCountIndex = 0
    for i := range count {
        if count[i] > count[maxCountIndex] {
            maxCountIndex = i
        }
    }

    // 3. 先将出现次数最多的字符放在偶数索引上
    var res = make([]byte, n)
    var idx = 0
    for count[maxCountIndex] > 0 {
        res[idx] = byte(maxCountIndex + 'a')
        idx += 2
        count[maxCountIndex]--
    }

    // 4. 将其他的字符放到其他的位置
    for i := range count {
        for count[i] > 0 {
            if idx >= n {
                idx = 1
            }
            res[idx] = byte(i + 'a')
            idx += 2
            count[i]--
        }
    }

    return string(res)
}