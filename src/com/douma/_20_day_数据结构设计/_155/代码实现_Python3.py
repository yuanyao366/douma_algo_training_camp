# 方法 1：双栈实现
class MinStack1:

    def __init__(self):
        """
        initialize your data structure here.
        """
        self.data_stack = []
        self.min_stack = []


    def push(self, val: int) -> None:
        self.data_stack.append(val)
        # bug 修复：视频中少了 = ，= 号是需要加上的
        # 如果去掉等于的话，可能会出现 dataStack 不为空，但是 minStack 为空了
        # 这样下面的 getMin 就会出现异常了
        if not len(self.min_stack) or val <= self.min_stack[-1]:
            self.min_stack.append(val)

    def pop(self) -> None:
        top_val = self.data_stack.pop()
        if top_val == self.min_stack[-1]:
            self.min_stack.pop()

    def top(self) -> int:
        return self.data_stack[-1]

    def getMin(self) -> int:
        return self.min_stack[-1]

class Node:
    def __init__(self, x = 0, y = 0):
        self.val = x
        self.min = y
        self.next = None

# 方法 2：单链表实现(表头作为栈顶)
class MinStack:

    def __init__(self):
        """
        initialize your data structure here.
        """
        self.dummy_node = Node()


    def push(self, val: int) -> None:
        head, min_val = self.dummy_node.next, val
        if head and head.min < val:
            min_val = head.min
        node = Node(val, min_val)
        node.next = self.dummy_node.next
        self.dummy_node.next = node

    def pop(self) -> None:
        head = self.dummy_node.next
        if head:
            self.dummy_node.next = head.next
            head.next =None

    def top(self) -> int:
        return self.dummy_node.next.val

    def getMin(self) -> int:
        return self.dummy_node.next.min



# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(val)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.getMin()