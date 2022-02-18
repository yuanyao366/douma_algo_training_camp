// 时间复杂度：O(n)
// 空间复杂度：O(n)
func dailyTemperatures(temperatures []int) []int {
    var n = len(temperatures)
    if n == 1 {
        return []int{0}
    }

    var res, stack = make([]int, n), []int{}
    for i := 0; i < n; i++ {
        var x = temperatures[i]
        // 单调递减栈
        for len(stack) > 0 && x > temperatures[stack[len(stack) - 1]] {
            var prevIndex = stack[len(stack) - 1]
            res[prevIndex] = i - prevIndex
            stack = stack[:len(stack) - 1]
        }
        stack = append(stack, i)
    }

    return res
}