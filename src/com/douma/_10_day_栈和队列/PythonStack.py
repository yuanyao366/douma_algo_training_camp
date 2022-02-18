def test(self) -> None:
    stack = list()
    # 压栈
    stack.append(2)
    stack.append(3)

    # 栈顶元素
    top = stack[-1]

    # 栈是否为空
    is_empty = not stack

    # 出栈
    stack.pop()
