type MyStack struct {
    queue *list.List
}


func Constructor() MyStack {
    return MyStack{queue:list.New()}
}


func (this *MyStack) Push(x int)  {
    var size = this.queue.Len()
    this.queue.PushBack(x)
    for i := 0; i < size; i++ {
        this.queue.PushBack(this.queue.Remove(this.queue.Front()).(int))
    }
}


func (this *MyStack) Pop() int {
    return this.queue.Remove(this.queue.Front()).(int)
}


func (this *MyStack) Top() int {
    return this.queue.Front().Value.(int)
}


func (this *MyStack) Empty() bool {
    return this.queue.Len() == 0
}


/**
 * Your MyStack object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Push(x);
 * param_2 := obj.Pop();
 * param_3 := obj.Top();
 * param_4 := obj.Empty();
 */