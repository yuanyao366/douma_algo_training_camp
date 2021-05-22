/**
 * initialize your data structure here.
 */
var MedianFinder = function() {
    this.minHeap = new MinPriorityQueue({priority: (num) => num})
    this.maxHeap = new MaxPriorityQueue({priority: (num) => num})
};

/**
 * @param {number} num
 * @return {void}
 */
MedianFinder.prototype.addNum = function(num) {
    if (!this.maxHeap.size()) {
        this.maxHeap.enqueue(num)
        return
    }

    if (num <= this.maxHeap.front()['element']) {
        this.maxHeap.enqueue(num)
    } else {
        this.minHeap.enqueue(num)
    }

    if (this.maxHeap.size() > this.minHeap.size() + 1) {
        this.minHeap.enqueue(this.maxHeap.dequeue()['element'])
    }

    if (this.maxHeap.size() < this.minHeap.size()) {
        this.maxHeap.enqueue(this.minHeap.dequeue()['element'])
    }
};

/**
 * @return {number}
 */
MedianFinder.prototype.findMedian = function() {
    if (this.maxHeap.size() > this.minHeap.size()) {
        return this.maxHeap.front()['element']
    } else {
        return (this.maxHeap.front()['element'] + this.minHeap.front()['element']) * 0.5
    }
};

/**
 * Your MedianFinder object will be instantiated and called as such:
 * var obj = new MedianFinder()
 * obj.addNum(num)
 * var param_2 = obj.findMedian()
 */