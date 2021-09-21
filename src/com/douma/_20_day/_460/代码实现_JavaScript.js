/**
 * @param {number} capacity
 */
class Node {
    constructor(key, value, count) {
        this.key = key;
        this.value = value
        this.count = count
        this.prev = null;
        this.next = null;
    }
}

class DoubleLinkedList {
    constructor() {
        this.head = new Node()
        this.tail = new Node()
        this.head.next = this.tail
        this.tail.prev = this.head
    }

    remove(node) {
        node.prev.next = node.next
        node.next.prev = node.prev
        node.prev = null
        node.next = null
        return node
    }

    // 将 node 拼接到表尾
    append(node){
        node.prev = this.tail.prev
        this.tail.prev.next = node
        node.next = this.tail
        this.tail.prev = node
    }


    popFirst() {
        if (this.head.next == this.tail)
            return null
        return this.remove(this.head.next)
    }


    isEmpty() {
        return this.head.next == this.tail
    }

}

var LFUCache = function(capacity) {
    this.keyToNode = new Map()
    this.usedCountToKeys = new Map()
    this.capacity = capacity
    this.minUsedCount = 0
};

/**
 * @param {number} key
 * @return {number}
 */
LFUCache.prototype.get = function(key) {
    if (this.capacity == 0) return -1
    if (this.keyToNode.has(key)) {
        const node = this.keyToNode.get(key)

        // 维护这个 key 对应的 count
        const usedCount = node.count

        // 1. 从这个 key 目前对应的 count 的集合中删除掉这个 key
        this.usedCountToKeys.get(usedCount).remove(node)

        // 2. 更新最小使用的次数
        // 如果当前的 usedCount 等于最小次数，
        // 并且当前的 usedCount 没有的 key，那么将最小次数加 1
        if (usedCount == this.minUsedCount
                && this.usedCountToKeys.get(usedCount).isEmpty()) {
            this.minUsedCount++;
        }

        // 3. 将 key 记录到 usedCount + 1 中的集合中
        const count = usedCount + 1
        node.count = count
        if (!this.usedCountToKeys.has(count)) {
            this.usedCountToKeys.set(count, new DoubleLinkedList())
        }
        this.usedCountToKeys.get(count).append(node)

        return node.value
    } else {
        return -1;
    }
};

/**
 * @param {number} key
 * @param {number} value
 * @return {void}
 */
LFUCache.prototype.put = function(key, value) {
    if (this.capacity == 0) return -1
    if (this.keyToNode.has(key)) {
        const node = this.keyToNode.get(key)
        node.value = value;
        // 更新 key 对应的 value 值
        this.keyToNode.set(key, node)
        // 更新 key 对应的 count 值
        this.get(key)
        return
    }
    if (this.keyToNode.size == this.capacity) {
        // 删除最少使用的 key
        const removeNode = this.usedCountToKeys.get(this.minUsedCount).popFirst()
        this.keyToNode.delete(removeNode.key)
    }
    // 新增一个缓存中不存在的 key
    const node = new Node(key, value, 1)
    this.keyToNode.set(key, node)

    // 将 key 记录到 minUsedCount 中的集合中
    this.minUsedCount = 1
    if (!this.usedCountToKeys.has(this.minUsedCount)) {
        this.usedCountToKeys.set(this.minUsedCount, new DoubleLinkedList())
    }
    this.usedCountToKeys.get(this.minUsedCount).append(node)
};

/**
 * Your LFUCache object will be instantiated and called as such:
 * var obj = new LFUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */