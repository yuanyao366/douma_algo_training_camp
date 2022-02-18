var CQueue = function() {
    this.stack1 = []
    this.stack2 = []
};

/**
 * @param {number} value
 * @return {void}
 */
CQueue.prototype.appendTail = function(value) {
    this.stack1.push(value)
};

/**
 * @return {number}
 */
CQueue.prototype.deleteHead = function() {
    if (this.stack2.length == 0) {
        while (this.stack1.length > 0) {
            this.stack2.push(this.stack1[this.stack1.length - 1])
            this.stack1.pop()
        }
    }
    if (this.stack2.length == 0) {
        return -1
    } else {
        const ret = this.stack2[this.stack2.length - 1]
        this.stack2.pop()
        return ret
    }
};

/**
 * Your CQueue object will be instantiated and called as such:
 * var obj = new CQueue()
 * obj.appendTail(value)
 * var param_2 = obj.deleteHead()
 */