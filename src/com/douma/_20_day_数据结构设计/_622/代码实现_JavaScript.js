/**
 * @param {number} k
 */
var MyCircularQueue = function(k) {
    this.data = new Array(k + 1).fill(0)
    this.head = 0
    this.tail = 0
};

/**
 * @param {number} value
 * @return {boolean}
 */
MyCircularQueue.prototype.enQueue = function(value) {
    if (this.isFull()) return false
    this.data[this.tail] = value
    this.tail = (this.tail + 1) % this.data.length
    return true
};

/**
 * @return {boolean}
 */
MyCircularQueue.prototype.deQueue = function() {
    if (this.isEmpty()) return false
    this.head = (this.head + 1) % this.data.length
    return true
};

/**
 * @return {number}
 */
MyCircularQueue.prototype.Front = function() {
    if (this.isEmpty()) return -1
    return this.data[this.head]
};

/**
 * @return {number}
 */
MyCircularQueue.prototype.Rear = function() {
    if (this.isEmpty()) return -1
    const index = this.tail > 0 ? this.tail - 1 : this.data.length - 1
    return this.data[index % this.data.length]
};

/**
 * @return {boolean}
 */
MyCircularQueue.prototype.isEmpty = function() {
    return this.head == this.tail
};

/**
 * @return {boolean}
 */
MyCircularQueue.prototype.isFull = function() {
    return ((this.tail + 1) % this.data.length) == this.head
};

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * var obj = new MyCircularQueue(k)
 * var param_1 = obj.enQueue(value)
 * var param_2 = obj.deQueue()
 * var param_3 = obj.Front()
 * var param_4 = obj.Rear()
 * var param_5 = obj.isEmpty()
 * var param_6 = obj.isFull()
 */