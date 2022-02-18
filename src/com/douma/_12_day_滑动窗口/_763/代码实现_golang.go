func partitionLabels(s string) []int {
    // 计算并存储每个字符在数组中所在的最大索引
    var c2Index = [26]int{}
    for i := range s {
        c2Index[s[i] - 'a'] = i 
    }

    // 存储最终结果
    var res = make([]int, 0)

    // 维护窗口
    var left, right = 0, 0
    for i := range s {
        var c = s[i]
        if c2Index[c - 'a'] > right {
            right = c2Index[c - 'a']
        }
        if i == right {
            res = append(res, right - left + 1)
            left = right + 1
        }
    }
    return res
}