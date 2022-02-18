// 暴力解法
// m 表示单词的平均长度，n 表示单词的个数
// 时间复杂度：O(n^2 * m)
// 空间复杂度：O(1)
func maxProduct1(words []string) int {
    var ans = 0
    for i := range words {
        var word1 = words[i]
        for j := i + 1; j < len(words); j++ {
            var word2 = words[j]
            // 每个单词的 bitMask 会重复计算很多次
            if !hasSameChar(word1, word2) {
                var length = len(word1) * len(word2)
                if length > ans {
                    ans = length
                }
            }
        }
    }
    return ans
}

// O(m^2)
func hasSameChar1(word1 string, word2 string) bool {
    for _, c := range word1 {
        if strings.Index(word2, string(c)) >= 0 {
            return true
        }
    }
    return false
}

func hasSameChar2(word1 string, word2 string) bool {
    var count = [26]int{}
    for _, c := range word1 {
        count[c - 'a'] = 1
    }
    for _, c := range word2 {
        if count[c - 'a'] == 1 {
            return true
        }
    }
    return false
}

// O(m)
func hasSameChar(word1 string, word2 string) bool {
    var bitMask1, bitMask2 = 0, 0
    for _, c := range word1 {
        bitMask1 |= (1 << (c - 'a'))
    }
    for _, c := range word2 {
        bitMask2 |= (1 << (c - 'a'))
    }
    return (bitMask1 & bitMask2) != 0
}

// 位运算 + 预计算
// 时间复杂度：O((m + n)* n)
// 空间复杂度：O(n)
func maxProduct2(words []string) int {
    // O(mn)
    var n = len(words)
    var masks = make([]int, n)
    for i := 0; i < n; i++ {
        var bitMask = 0
        for _, c := range words[i] {
            bitMask |= (1 << (c - 'a'))
        }
        masks[i] = bitMask
    }

    var ans = 0
    for i := range words {
        var word1 = words[i]
        for j := i + 1; j < len(words); j++ {
            var word2 = words[j]
            if (masks[i] & masks[j]) == 0 {
                var length = len(word1) * len(word2)
                if length > ans {
                    ans = length
                }
            }
        }
    }
    return ans
}


// 位运算 + 预计算
// 时间复杂度：O((m + n)* n)
// 空间复杂度：O(n)
func maxProduct(words []string) int {
    // O(mn)
    var n = len(words)
    var masksMap = make(map[int]int)
    for i := 0; i < n; i++ {
        var bitMask = 0
        for _, c := range words[i] {
            bitMask |= (1 << (c - 'a'))
        }
        // there could be different words with the same bitmask
        // ex. ab and aabb
        if len(words[i]) > masksMap[bitMask] {
            masksMap[bitMask] = len(words[i])
        }
    }

    var ans = 0
    for x := range masksMap {
        for y := range masksMap {
            if (x & y) == 0 {
                var length = masksMap[x] * masksMap[y]
                if length > ans {
                    ans = length
                }
            }
        }
    }
    return ans
}