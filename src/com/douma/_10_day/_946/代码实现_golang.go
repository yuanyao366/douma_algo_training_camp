func validateStackSequences(pushed []int, popped []int) bool {
    var stack, i = []int{}, 0
    for _, num := range pushed {
        stack = append(stack, num)
        for len(stack) > 0 && i < len(popped) && popped[i] == stack[len(stack) - 1] {
            stack = stack[:len(stack) - 1]
            i++
        }
    }
    return i == len(popped)
}