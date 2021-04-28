var compareVersion = function(version1, version2) {
    let i1 = 0, i2 = 0
    let n1 = version1.length, n2 = version2.length
    while (i1 < n1 || i2 < n2) {
        let v1 = 0, v2 = 0
        while (i1 < n1 && version1[i1] != '.'){
            v1 = v1 * 10 + (version1[i1] - '0')
            i1++
        }
        while (i2 < n2 && version2[i2] != '.') {
            v2 = v2 * 10 + (version2[i2] - '0')
            i2++
        }
        if (v1 != v2) {
            return v1 > v2 ? 1 : -1
        }
        i1++
        i2++
    }
    return 0
};