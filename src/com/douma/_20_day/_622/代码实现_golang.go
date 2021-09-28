type MyCircularQueue struct {
    data []int
    head int
    tail int
}


func Constructor(k int) MyCircularQueue {
    return MyCircularQueue{data:make([]int, k + 1), head:0, tail:0}
}


func (this *MyCircularQueue) EnQueue(value int) bool {
    if this.IsFull() {
        return false
    }
    this.data[this.tail] = value
    this.tail = (this.tail + 1) % len(this.data)
    return true
}


func (this *MyCircularQueue) DeQueue() bool {
    if this.IsEmpty() {
        return false
    }
    // 这里不需要返回出队的元素
    // head 指针往前走即可
    this.head = (this.head + 1) % len(this.data)
    return true
}


func (this *MyCircularQueue) Front() int {
    if this.IsEmpty() {
        return -1
    }
    return this.data[this.head]
}


func (this *MyCircularQueue) Rear() int {
    if this.IsEmpty() {
        return -1
    }
    // tail 指针前一个元素就是队列的最后一个元素
    var index = len(this.data) - 1
    if this.tail > 0 {
        index = this.tail - 1
    }
    return this.data[index % len(this.data)]
}


func (this *MyCircularQueue) IsEmpty() bool {
    return this.head == this.tail
}


func (this *MyCircularQueue) IsFull() bool {
    return (this.tail + 1) % len(this.data) == this.head
}


/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * obj := Constructor(k);
 * param_1 := obj.EnQueue(value);
 * param_2 := obj.DeQueue();
 * param_3 := obj.Front();
 * param_4 := obj.Rear();
 * param_5 := obj.IsEmpty();
 * param_6 := obj.IsFull();
 */