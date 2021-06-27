/**
 * @param {number} capacity
 */

class Node {
    constructor() {
        this.key = 0;
        this.value = 0;
        this.prev = null;
        this.next = null;
    }
}

var LRUCache = function(capacity) {
    this.cache = new Map()
    this.capacity = capacity
    this.head = new Node()
    this.tail = new Node()
    this.head.next = this.tail
    this.tail.prev = this.head
};

/**
 * @param {number} key
 * @return {number}
 */
LRUCache.prototype.get = function(key) {
    if (!this.cache.has(key)) return -1
    const node = this.cache.get(key)
    this.moveNodeToHead(node)
    return node.value
};

LRUCache.prototype.moveNodeToHead = function(node) {
    this.removeNode(node)
    this.addNodeToHead(node)
};

LRUCache.prototype.removeNode = function(node) {
    const prevNode = node.prev;
    const nextNode = node.next;

    prevNode.next = nextNode;
    nextNode.prev = prevNode;

    node.prev = null;
    node.next = null;
};

LRUCache.prototype.addNodeToHead = function(node) {
    node.next = this.head.next;
    this.head.next.prev = node;

    this.head.next = node;
    node.prev = this.head;
};

/**
 * @param {number} key
 * @param {number} value
 * @return {void}
 */
LRUCache.prototype.put = function(key, value) {
    if (!this.cache.has(key)) {
        if (this.cache.size == this.capacity) {
            const delNode = this.removeTailNode()
            this.cache.delete(delNode.key)
        }

        const node = new Node()
        node.key = key
        node.value = value

        this.cache.set(key, node)
        this.addNodeToHead(node)

    } else {
        const node = this.cache.get(key)
        node.value = value

        this.moveNodeToHead(node)
    }

};

LRUCache.prototype.removeTailNode = function() {
    const delNode = this.tail.prev
    this.removeNode(delNode)
    return delNode
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = new LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */