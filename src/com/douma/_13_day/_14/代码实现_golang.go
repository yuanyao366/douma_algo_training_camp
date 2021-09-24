
// 1. 纵向扫描
// 时间复杂度：O(mn) m 表示字符串数组中所有字符串的平均长度，n 表示字符串数组的大小
// 空间复杂度：O(1)
func longestCommonPrefix1(strs []string) string {
    var rows, cols = len(strs), len(strs[0])
    for i := 0; i < cols; i++ {
        var firstChar = strs[0][i]
        for j := 1; j < rows; j++ {
            if len(strs[j]) == i || strs[j][i] != firstChar {
                return strs[0][0:i]
            }
        }
    }
    return strs[0]
}


// 2. 横向扫描
// 时间复杂度：O(mn) m 表示字符串数组中所有字符串的平均长度，n 表示字符串数组的大小
// 空间复杂度：O(1)
func longestCommonPrefix2(strs []string) string {
    var prefix = strs[0]
    for i := 1; i < len(strs); i++ {
        prefix = longestCommonPrefixHelp(prefix, strs[i])
        if len(prefix) == 0 {
            return ""
        }
    }
    return prefix
}

func longestCommonPrefixHelp(str1 string, str2 string) string {
    var length = len(str1)
    if len(str2) < length {
        length = len(str2)
    }
    var index = 0
    for index < length && str1[index] == str2[index] {
        index++
    }
    return str1[0:index]
}


// 3. 分治思想
// 时间复杂度：O(mn) m 表示字符串数组中所有字符串的平均长度，n 表示字符串数组的大小
// 空间复杂度：O(mlogn) 空间复杂度主要取决于递归调用的层数，层数最大为 logn，每层需要 m 的空间存储返回结果。
func longestCommonPrefix(strs []string) string {
    return lcp(strs, 0, len(strs) - 1)
}

func lcp(strs []string, left int, right int) string {
    if left == right {
        return strs[left]
    }
    var mid = left + (right - left) / 2
    var leftLcp = lcp(strs, left, mid)
    var rightLcp = lcp(strs, mid + 1, right)

    return longestCommonPrefixHelp(leftLcp, rightLcp)
}