/**
 * @param {string[]} strs
 * @return {string}
 */
// 1. 纵向扫描
// 时间复杂度：O(mn) m 表示字符串数组中所有字符串的平均长度，n 表示字符串数组的大小
// 空间复杂度：O(1)
var longestCommonPrefix1 = function(strs) {
    if (strs.length == 0) return "";

    const rows = strs.length;
    const cols = strs[0].length;
    for (let i = 0; i < cols; i++) {
        const firstChar = strs[0][i];
        for (let j = 1; j < rows; j++) {
            if (strs[j].length == i || strs[j][i] != firstChar) {
                return strs[0].substr(0, i);
            }
        }
    }
    return strs[0];
};

// 2. 横向扫描
// 时间复杂度：O(mn) m 表示字符串数组中所有字符串的平均长度，n 表示字符串数组的大小
// 空间复杂度：O(1)
var longestCommonPrefix2 = function(strs) {
    if (strs.length == 0) return "";

    let prefix = strs[0]
    for (let i = 1; i < strs.length; i++) {
        prefix = longestCommonPrefixHelp(prefix, strs[i])
        if (prefix.length == 0) return ""
    }
    return prefix
};

var longestCommonPrefixHelp = function(str1, str2) {
    const len = Math.min(str1.length, str2.length)
    let index = 0
    while (index < len && str1[index] == str2[index]) {
        index++
    }
    return str1.substr(0, index)
}

// 3. 分治思想
// 时间复杂度：O(mn) m 表示字符串数组中所有字符串的平均长度，n 表示字符串数组的大小
// 空间复杂度：O(mlogn) 空间复杂度主要取决于递归调用的层数，层数最大为 logn，每层需要 m 的空间存储返回结果。
var longestCommonPrefix = function(strs) {
    if (strs.length == 0) return "";

    const lcp = (left, right) => {
        if (left == right) return strs[left]
        const mid = left + Math.floor((right - left) / 2)
        const leftLCP = lcp(left, mid)
        const rightLCP = lcp(mid + 1, right)
        return longestCommonPrefixHelp(leftLCP, rightLCP)
    }

    return lcp(0, strs.length - 1)
};