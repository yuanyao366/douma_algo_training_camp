


func main() {
    // 使用数组来实现栈的功能
    var stack = []int{}
    // 压栈
    stack = append(stack, 3)
    stack = append(stack, 3)

    // 拿到栈顶元素
    var top = stack[len(stack) - 1]

    // 判断栈是否为空
    if len(stack) == 0 {
        fmt.Println("栈 stack 是空的")
    }

    // 弹出栈顶元素
    stack = stack[:len(stack) - 1]
}