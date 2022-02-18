/**
 * initialize your data structure here.
 */
// 方法 1 ：双栈实现
var MinStack1 = function() {
    this.dataStack = []
    this.minStack = []
};

/**
 * @param {number} val
 * @return {void}
 */
MinStack1.prototype.push = function(val) {
    this.dataStack.push(val)
    // bug 修复：视频中少了 = ，= 号是需要加上的
    // 如果去掉等于的话，可能会出现 dataStack 不为空，但是 minStack 为空了
    // 这样下面的 getMin 就会出现异常了
    if (!this.minStack.length || val <= this.minStack[this.minStack.length - 1]) {
        this.minStack.push(val);
    }
};

/**
 * @return {void}
 */
MinStack1.prototype.pop = function() {
    const topVal = this.dataStack.pop()
    if (topVal == this.minStack[this.minStack.length - 1]) {
        this.minStack.pop()
    }
};

/**
 * @return {number}
 */
MinStack1.prototype.top = function() {
    return this.dataStack[this.dataStack.length - 1]
};

/**
 * @return {number}
 */
MinStack1.prototype.getMin = function() {
    return this.minStack[this.minStack.length - 1]
};


function Node(val, min) {
    this.val = val
    this.min = min
    this.next = null
}

// 方法 2 ：单链表实现(表头作为栈顶)
var MinStack = function() {
    this.dummyNode = new Node()
};

/**
 * @param {number} val
 * @return {void}
 */
MinStack.prototype.push = function(val) {
    const head = this.dummyNode.next;
    let minVal = val
    if (head && head.min < val) {
        minVal = head.min
    }
    const node = new Node(val, minVal)
    node.next = this.dummyNode.next
    this.dummyNode.next = node
};

/**
 * @return {void}
 */
MinStack.prototype.pop = function() {
    const head = this.dummyNode.next
    if (head) {
        this.dummyNode.next = head.next
        head.next = null
    }
};

/**
 * @return {number}
 */
MinStack.prototype.top = function() {
    return this.dummyNode.next.val
};

/**
 * @return {number}
 */
MinStack.prototype.getMin = function() {
    return this.dummyNode.next.min
};

/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = new MinStack()
 * obj.push(val)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */