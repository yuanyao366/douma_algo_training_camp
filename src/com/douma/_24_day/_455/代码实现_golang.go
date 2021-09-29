// 贪心策略：每次把最小的饼干分配给胃口最小的小孩，这样才能满足最多的小孩
// g[i] 表示第 i 个小孩的胃口值
// s[i] 表示第 i 个饼干的尺寸
func findContentChildren(g []int, s []int) int {
    sort.Ints(g)
    sort.Ints(s)

    var i, j = 0, 0
    for i < len(g) && j < len(s) {
        if s[j] >= g[i] {
            i++
        }
        j++
    }
    return i
}