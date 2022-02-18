func restoreIpAddresses(s string) []string {
    var res = make([]string, 0)
    if len(s) == 0 {
        return res
    }

    var restoreIp func(int, string, int)
    restoreIp = func(index int, restored string, count int) {
        if count > 4 {
            return
        }
        if count == 4 && index == len(s) {
            res = append(res, restored)
            return
        }

        for segmentLen := 1; segmentLen < 4; segmentLen++ {
            if index + segmentLen > len(s) {
                break
            }
            var segment = s[index:index + segmentLen]
            if !isValidIpSegment(segment) {
                continue
            }
            var suffix = "."
            if count == 3 {
                suffix = ""
            }
            restoreIp(index + segmentLen, restored + segment + suffix, count + 1)
        }
    }

    restoreIp(0, "", 0)
    return res
}

func isValidIpSegment(segment string) bool {
    // 长度不能大于 3
    var n = len(segment)
    if n > 3 {
        return false
    }
    // 1. ip 段如果是以 0 开始的话，那么这个 ip 段只能是 0
    // 2. ip 段需要小于等于 255
    if segment[0] == '0' {
        return n == 1
    } else {
        var num, _ = strconv.Atoi(segment)
        return num <= 255
    }
}