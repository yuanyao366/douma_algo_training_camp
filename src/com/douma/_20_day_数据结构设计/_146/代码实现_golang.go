type Node struct {
    key int
    value int
    next *Node
    prev *Node
}

type LRUCache struct {
    cache map[int]*Node
    capacity int
    head *Node
    tail *Node
}


func Constructor(capacity int) LRUCache {
    var lru = LRUCache{
        head:&Node{},
        tail:&Node{},
        cache:make(map[int]*Node),
        capacity:capacity,
    }
    lru.head.next = lru.tail
    lru.tail.prev = lru.head
    return lru
}


func (this *LRUCache) Get(key int) int {
    var node, has = this.cache[key]
    if !has {
        return -1
    }
    // 将查询到的 node 移动到表头
    this.moveNodeToHead(node)
    return node.value
}

func (this *LRUCache) moveNodeToHead(node *Node) {
    // 1. 删除节点 node
    this.removeNode(node)

    // 2. 将节点 node 添加到表头
    this.addNodeToHead(node)
}

func (this *LRUCache) removeNode(node *Node) {
    var preNode, nextNode = node.prev, node.next

    preNode.next = nextNode
    nextNode.prev = preNode

    node.prev = nil
    node.next = nil
}

func (this *LRUCache) addNodeToHead(node *Node) {
    node.next = this.head.next
    this.head.next.prev = node

    this.head.next = node
    node.prev = this.head
}


func (this *LRUCache) Put(key int, value int)  {
    var node, has = this.cache[key]
    if !has {
        // 1. 判断缓存容量大小
        if len(this.cache) == this.capacity {
            var delNode = this.removeTailNode()
            delete(this.cache, delNode.key)
        }
        // 2. 创建节点
        node = &Node{key:key, value:value}

        // 3. 维护链表和缓存
        this.cache[key] = node
        this.addNodeToHead(node)
    } else {
        node.value = value
        // 有的话，则将节点放到头结点
        this.moveNodeToHead(node)
    }
}

func (this *LRUCache) removeTailNode() *Node {
    var delNode = this.tail.prev
    this.removeNode(delNode)
    return delNode
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * obj := Constructor(capacity);
 * param_1 := obj.Get(key);
 * obj.Put(key,value);
 */