func minWindow1(s string, t string) string {
    // 统计字符串 t 中每个字符出现的次数
    var cntT = [60]int{}
    // 统计 t 中不同的字符数
    var uniqueCharsInT = 0
    for _, c := range t {
        if cntT[c - 'A'] == 0 {
            uniqueCharsInT++
        }
        cntT[c - 'A']++
    }

    var left, right = 0, 0
    // 窗口中每个字符出现的次数
    var windowCntS = [60]int{}
    // 记录当前窗口中字符出现的次数和 t 中字符出现次数相等的字符数
    var matchedChars = 0
    var ans = [3]int{-1, 0, 0}
    for right < len(s) {
        var rightChar = s[right]
        var rightCharIndex = rightChar - 'A'
        windowCntS[rightCharIndex]++

        if windowCntS[rightCharIndex] == cntT[rightCharIndex] {
            matchedChars++
        }

        for left <= right && matchedChars == uniqueCharsInT {
            // 尝试缩减窗口，因为我们想找到最短符合条件的子串
            if ans[0] == -1 || right - left + 1 < ans[0] {
                ans[0] = right - left + 1
                ans[1] = left
                ans[2] = right
            }

            var leftChar = s[left]
            var leftCharIndex = leftChar - 'A'
            windowCntS[leftCharIndex]--
            if windowCntS[leftCharIndex] < cntT[leftCharIndex] {
                matchedChars--
            }
            left++
        }
        right++
    }
    if ans[0] == -1 {
        return ""
    } else {
        return s[ans[1]:ans[2] + 1]
    }
}


type Pair struct {
    key int
    value byte
}

func minWindow(s string, t string) string {
    // 统计字符串 t 中每个字符出现的次数
    var cntT = [60]int{}
    // 统计 t 中不同的字符数
    var uniqueCharsInT = 0
    for _, c := range t {
        if cntT[c - 'A'] == 0 {
            uniqueCharsInT++
        }
        cntT[c - 'A']++
    }

    // 在 s 中拿到是 t 中的字符，及其在 s 中的位置
    var filteredS = make([]Pair, 0)
    for i := range s {
        var c = s[i]
        if cntT[c - 'A'] > 0 {
            filteredS = append(filteredS, Pair{i, c})
        }
    }

    var left, right = 0, 0
    // 窗口中每个字符出现的次数
    var windowCntS = [60]int{}
    // 记录当前窗口中字符出现的次数和 t 中字符出现次数相等的字符数
    var matchedChars = 0
    var ans = [3]int{-1, 0, 0}
    for right < len(filteredS) {
        var rightChar = filteredS[right].value
        var rightCharIndex = rightChar - 'A'
        windowCntS[rightCharIndex]++

        if windowCntS[rightCharIndex] == cntT[rightCharIndex] {
            matchedChars++
        }

        for left <= right && matchedChars == uniqueCharsInT {
            // 尝试缩减窗口，因为我们想找到最短符合条件的子串
            var end = filteredS[right].key
            var start = filteredS[left].key
            if ans[0] == -1 || end - start + 1 < ans[0] {
                ans[0] = end - start + 1
                ans[1] = start
                ans[2] = end
            }

            var leftChar = filteredS[left].value
            var leftCharIndex = leftChar - 'A'
            windowCntS[leftCharIndex]--
            if windowCntS[leftCharIndex] < cntT[leftCharIndex] {
                matchedChars--
            }
            left++
        }
        right++
    }
    if ans[0] == -1 {
        return ""
    } else {
        return s[ans[1]:ans[2] + 1]
    }
}