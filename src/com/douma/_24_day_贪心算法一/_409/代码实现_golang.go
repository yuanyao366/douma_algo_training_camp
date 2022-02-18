// 贪心策略：每次将这个字母的 v / 2 数放在回文串的左右两侧
func longestPalindrome(s string) int {
    // 字符总的个数是 128，包含了大写字母和小写字母
    var counter = [128]int{}
    for _, c := range s {
        counter[c]++
    }

    var ans = 0
    for _, v := range counter {
        ans += v / 2 * 2
        // 中间只能有一个出现奇数次的字符
        if v % 2 == 1 && ans % 2 == 0 {
            ans++
        }
    }

    return ans
}