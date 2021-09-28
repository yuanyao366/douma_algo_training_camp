// 1. 双栈实现
type MinStack struct {
    dataStack []int
    minStack []int
}


func Constructor() MinStack {
    return MinStack{dataStack:[]int{}, minStack:[]int{}}
}


func (this *MinStack) Push(val int)  {
    this.dataStack = append(this.dataStack, val)
    // bug 修复：视频中少了 = ，= 号是需要加上的
    // 如果去掉等于的话，可能会出现 dataStack 不为空，但是 minStack 为空了
    // 这样下面的 getMin 就会出现异常了
    if len(this.minStack) == 0 || val <= this.minStack[len(this.minStack) - 1] {
        this.minStack = append(this.minStack, val)
    }
}


func (this *MinStack) Pop()  {
    if this.dataStack[len(this.dataStack) - 1] == this.minStack[len(this.minStack) - 1] {
        this.minStack = this.minStack[:len(this.minStack) - 1]
    }
    this.dataStack = this.dataStack[:len(this.dataStack) - 1]
}


func (this *MinStack) Top() int {
    return this.dataStack[len(this.dataStack) - 1]
}


func (this *MinStack) GetMin() int {
    return this.minStack[len(this.minStack) - 1]
}


type Node struct {
    val int
    min int
}


// 2. 单栈 + 每个元素记住当前为止的最小值
type MinStack struct {
    s []Node
}


func Constructor() MinStack {
    return MinStack{s:[]Node{}}
}


func (this *MinStack) Push(val int)  {
    var node = Node{val:val}
    var min = val
    if len(this.s) != 0 && this.s[len(this.s) - 1].min < val {
        min = this.s[len(this.s) - 1].min
    }
    node.min = min
    this.s = append(this.s, node)
}


func (this *MinStack) Pop()  {
    this.s = this.s[:len(this.s) - 1]
}


func (this *MinStack) Top() int {
    return this.s[len(this.s) - 1].val
}


func (this *MinStack) GetMin() int {
    return this.s[len(this.s) - 1].min
}


type Node struct {
    val int
    min int
    next *Node
}

// 方法 3. 单链表实现(表头作为栈顶)
type MinStack struct {
    dummyNode *Node
}


func Constructor() MinStack {
    return MinStack{dummyNode:&Node{}}
}


func (this *MinStack) Push(val int)  {
    var head = this.dummyNode.next
    var min = val
    if head != nil && head.min < val {
        min = head.min
    }
    var node = &Node{val:val, min:min, next:this.dummyNode.next}
    this.dummyNode.next = node
}


func (this *MinStack) Pop()  {
    var head = this.dummyNode.next
    if head != nil {
        this.dummyNode.next = head.next
        head.next = nil
    }
}


func (this *MinStack) Top() int {
    return this.dummyNode.next.val
}


func (this *MinStack) GetMin() int {
    return this.dummyNode.next.min
}


/**
 * Your MinStack object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Push(val);
 * obj.Pop();
 * param_3 := obj.Top();
 * param_4 := obj.GetMin();
 */

