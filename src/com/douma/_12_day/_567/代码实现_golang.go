// 滑动窗口
func checkInclusion(s1 string, s2 string) bool {
    var n, m = len(s1), len(s2)
    if n > m {
        return false
    }

    // 先统计字符串 s1 中每个字符出现的次数
    var cnt = [26]int{}
    for i := range s1 {
        cnt[s1[i] - 'a']++
    }

    var left, right = 0, 0
    for right < m {
        var x = s2[right] - 'a'
        cnt[x]--
        for cnt[x] < 0 {
            // 通过缩减窗口使得 cnt[x] 不为负数
            cnt[s2[left] - 'a']++
            left++
        }
        // 到现在为止，当前窗口中字符的 cnt 值都为 0（不包含 s1 里面的字符）
        // 如果窗口的长度等于 n 的话，那么当前窗口中的 cnt 的值都是 0
        if right - left + 1 == n {
            return true
        }
        // bug 修复：right 需要往前走
        right++
    }

    return false
}