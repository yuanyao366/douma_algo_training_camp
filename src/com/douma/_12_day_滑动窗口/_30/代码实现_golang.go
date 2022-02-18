// 哈希查找
func findSubstring1(s string, words []string) []int {
    // 统计每个单词出现的次数
    var wordCnt = make(map[string]int)
    for _, word := range words {
        wordCnt[word]++
    }

    var oneWordLen, wordNum  = len(words[0]), len(words)
    var totalLen = oneWordLen * wordNum

    var res = make([]int, 0)
    for i := 0; i < len(s) - totalLen + 1; i++ {
        // 拿到等于所有单词长度之和的子串
        var subStr = s[i:i + totalLen]
        // 统计子串中单词出现的次数
        var tmpMap = make(map[string]int)
        for j := 0; j < totalLen; j += oneWordLen {
            var oneWord = subStr[j:j + oneWordLen]
            tmpMap[oneWord]++
        }
        // 如果单词出现的次数和原始 words 中单词出现的次数相同，则符合条件
        if reflect.DeepEqual(wordCnt, tmpMap) {
            res = append(res, i)
        }
    }
    return res
}


// 滑动窗口(O(n))
func findSubstring(s string, words []string) []int {
    // 统计每个单词出现的次数
    var wordCnt = make(map[string]int)
    for _, word := range words {
        wordCnt[word]++
    }

    var oneWordLen, wordNum  = len(words[0]), len(words)

    var res = make([]int, 0)
    for i := 0; i < oneWordLen; i++ {
        var left, right = i, i
        var matchedWords = 0
        var windowMap = make(map[string]int)
        for right <= len(s) - oneWordLen {
            var currWord = s[right:right + oneWordLen]
            windowMap[currWord]++
            matchedWords++
            for windowMap[currWord] > wordCnt[currWord] {
                var leftWord = s[left:left + oneWordLen]
                windowMap[leftWord]--
                left += oneWordLen
                matchedWords--
            }
            if matchedWords == wordNum {
                res = append(res, left)
            }
            right += oneWordLen
        }
    }
    return res
}