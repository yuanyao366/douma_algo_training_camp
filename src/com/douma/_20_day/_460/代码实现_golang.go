/****************自定义双向链表，代替 Java 中的 LinkedHashSet ******************/
type Node struct {
    key, val, count int
    next, prev *Node
}

type DoubleLinkedList struct {
    head *Node
    tail *Node
}

func (this *DoubleLinkedList) remove(node *Node) *Node {
    node.prev.next = node.next
    node.next.prev = node.prev
    node.prev = nil
    node.next = nil
    return node
}

func (this *DoubleLinkedList) add(node *Node) {
    node.prev = this.tail.prev
    this.tail.prev.next = node
    node.next = this.tail
    this.tail.prev = node
}

func (this *DoubleLinkedList) popFirst() *Node {
    if this.isEmpty() {
        return nil
    }
    return this.remove(this.head.next)
}

func (this *DoubleLinkedList) isEmpty() bool {
    return this.head.next == this.tail
}

/****************利用上面的双向链表实现 LFU ******************/

type LFUCache struct {
    // 每个 key 对应的 Node 的映射
    keyToNode map[int]*Node
    // 每个 count 对应的所有的 keys (按照最近使用的顺序组织)
    usedCountToKeys map[int]*DoubleLinkedList
    capacity int
    // 记录整个缓存中所有 key 中使用最小的次数
    minUsedCount int
}


func Constructor(capacity int) LFUCache {
    return LFUCache {
        keyToNode:make(map[int]*Node),
        usedCountToKeys:make(map[int]*DoubleLinkedList),
        capacity:capacity,
        minUsedCount:0,
    }
}


func (this *LFUCache) Get(key int) int {
    // 注意：capacity 可能为 0
    if this.capacity == 0 {
        return -1
    }

    var node = this.keyToNode[key]
    if node == nil {
        return -1
    }

    // 维护这个 key 对应的 count
    var usedCount = node.count
    // 1. 从这个 key 目前对应的 count 的集合中删除掉这个 key
    this.usedCountToKeys[usedCount].remove(node)
    node.count = usedCount + 1

    // 2. 更新最小使用的次数
    // 如果当前的 usedCount 等于最小次数，
    // 并且当前的 usedCount 没有的 key，那么将最小次数加 1
    if usedCount == this.minUsedCount && this.usedCountToKeys[usedCount].isEmpty() {
        this.minUsedCount++
    }

    // 3. 将 node 记录到 usedCount + 1 中的集合中
    this.putUsedCount(node, usedCount + 1)
    return node.val
}


func (this *LFUCache) putUsedCount(node *Node, count int)  {
    if _, has := this.usedCountToKeys[count]; !has {
        this.usedCountToKeys[count] = newDoublyList()
    }
    this.usedCountToKeys[count].add(node)
}

func newDoublyList() *DoubleLinkedList {
    var list = &DoubleLinkedList{head:&Node{}, tail:&Node{}}
    list.head.next = list.tail
    list.tail.prev = list.head
    return list
}

func (this *LFUCache) Put(key int, value int)  {
    if this.capacity == 0 {
        return
    }

    var node, has = this.keyToNode[key]
    if has {
        node.val = value
        // 更新 key 对应的 value 值
        this.keyToNode[key] = node
        // 更新 key 对应的 count 值
        this.Get(key)
        return
    }

    if len(this.keyToNode) == this.capacity {
        // 删除最少使用的 key
        var removeNode = this.usedCountToKeys[this.minUsedCount].popFirst()
        delete(this.keyToNode, removeNode.key)
    }

    // 新增一个缓存中不存在的 key
    node = &Node{key:key, val:value, count:1}
    this.keyToNode[key] = node

    // 将 key 记录到 minUsedCount 中的集合中
    this.minUsedCount = 1
    this.putUsedCount(node, this.minUsedCount)
}




/**
 * Your LFUCache object will be instantiated and called as such:
 * obj := Constructor(capacity);
 * param_1 := obj.Get(key);
 * obj.Put(key,value);
 */