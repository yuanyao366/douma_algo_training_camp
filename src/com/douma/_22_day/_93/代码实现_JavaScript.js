/**
 * @param {string} s
 * @return {string[]}
 */
var restoreIpAddresses = function(s) {
    const res = []
    if (!s) return res

    const isValidIpSegment = (segment) => {
        // 长度不能大于 3
        if (segment.length > 3) return false
        // 1. ip 段如果是以 0 开始的话，那么这个 ip 段只能是 0
        // 2. ip 段需要小于等于 255
        return segment[0] == '0' ? segment.length == 1 : parseInt(segment) <= 255
    }

    const restoreIp = (index, restored, count) => {
        if (count > 4) return
        if (count == 4 && index == s.length) {
            res.push(restored)
            return
        }

        for (let segmentLen = 1; segmentLen < 4; segmentLen++) {
            if (index + segmentLen > s.length) break
            const segment = s.substr(index, segmentLen)
            if (!isValidIpSegment(segment)) continue
            const suffix = count == 3 ? "" : "."
            restoreIp(index + segmentLen, restored + segment + suffix, count + 1)
        }
    }

    restoreIp(0, "", 0)
    return res
};