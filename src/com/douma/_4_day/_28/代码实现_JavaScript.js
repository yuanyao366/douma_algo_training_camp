// 字符串匹配算法：暴力解法、RK 算法、BM 算法、KMP 算法
// 详细参见：课程 A：应用篇 -> 字符串匹配

// 暴力
// 时间复杂度：O(m*n)
// 空间复杂度：O(1)
var strStr = function(haystack, needle) {
    const m = haystack.length, n = needle.length
    if (n == 0) return 0
    if (m < n) return -1
    for (let i = 0; i < m - n + 1; i++) {
        if (haystack.charCodeAt(i) == needle.charCodeAt(0)
                && haystack.substr(i, n) == needle) {
            return i;
        }
    }
    return -1;
};

// KMP
// 时间复杂度：O(m + n)
// 空间复杂度：O(n)
var strStr = function(haystack, needle) {
    const m = haystack.length, n = needle.length
    if (n == 0) return 0
    if (m < n) return -1
    const nexts = getNexts(needle)

    let j = 0
    for (let i = 0; i < m; i++) {
        while (j > 0 && haystack[i] != needle[j]) {
            j = nexts[j - 1] + 1
        }
        if (haystack[i] == needle[j]) j++
        if (j == n) return i - n + 1
    }
    return -1
};

var getNexts = function(needle) {
    const n = needle.length
    if (n == 1) return []
    const nexts = new Array(n - 1).fill(0)
    nexts[0] = -1
    for (let j = 1; j < n - 1; j++) {
        let pre = nexts[j - 1]
        while (pre != -1 && needle[pre + 1] != needle[j]) {
            pre = nexts[pre]
        }
        if (needle[pre + 1] == needle[j]) pre++
        nexts[j] = pre
    }
    return nexts
}

