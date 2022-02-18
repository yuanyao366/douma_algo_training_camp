def compareVersion(self, version1: str, version2: str) -> int:
    i1 = i2 = 0
    n1, n2 = len(version1), len(version2)
    while i1 < n1 or i2 < n2:
        v1 = v2 = 0
        while i1 < n1 and version1[i1] != '.':
            v1 = v1 * 10 + ord(version1[i1]) - ord('0')
            i1 += 1
        while i2 < n2 and version2[i2] != '.':
            v2 = v2 * 10 + ord(version2[i2]) - ord('0')
            i2 += 1
        if v1 != v2:
            return 1 if v1 > v2 else -1
        i1, i2 = i1 + 1, i2 + 1
    return 0