func compareVersion(version1 string, version2 string) int {
    i1, i2 := 0, 0
    for i1 < len(version1) || i2 < len(version2) {
        v1, v2 := 0, 0
        for i1 < len(version1) && version1[i1] != byte('.') {
            v1 = v1 * 10 + int(version1[i1] - byte('0'))
            i1++
        }
        // bug 修复：这里处理的是 version2
        for i2 < len(version2) && version2[i2] != byte('.') {
            v2 = v2 * 10 + int(version2[i2] - byte('0'))
            i2++
        }

        if v1 > v2 {
            return 1
        } else if v1 < v2 {
            return -1
        }

        i1++
        i2++
    }
    return 0
}