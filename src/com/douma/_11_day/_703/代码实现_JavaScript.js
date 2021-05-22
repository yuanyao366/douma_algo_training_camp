/**
 * @param {number} k
 * @param {number[]} nums
 */
var KthLargest1 = function(k, nums) {
    this.data = nums
    this.k = k
};

/**
 * @param {number} val
 * @return {number}
 */
// 普通排序
KthLargest1.prototype.add = function(val) {
    this.data.push(val)
    this.data.sort((a, b) => a - b)
    return this.data[this.data.length - this.k]
};

/**
 * @param {number} k
 * @param {number[]} nums
 */
var KthLargest2 = function(k, nums) {
    this.data = nums
    this.data.sort((a, b) => a - b)
    this.k = k
};

/**
 * @param {number} val
 * @return {number}
 */
// 插入排序
KthLargest2.prototype.add = function(val) {
    if (!this.data.length) {
        this.data.push(val)
    } else {
        const n = this.data.length
        this.data.push(val)
        let j = n
        for (; j > 0; j--) {
            if (val < this.data[j - 1]) {
                this.data[j] = this.data[j - 1]
            } else {
                break
            }
        }
        this.data[j] = val
    }

    return this.data[this.data.length - this.k]
};

/**
 * @param {number} k
 * @param {number[]} nums
 */
var KthLargest = function(k, nums) {
    this.pq = new MinPriorityQueue({priority : (num) => num})
    this.k = k
    for (const num of nums) {
        this.add(num)
    }
};

/**
 * @param {number} val
 * @return {number}
 */
// 小顶堆
KthLargest.prototype.add = function(val) {
    if (this.pq.size() < this.k) {
        this.pq.enqueue(val)
    } else if (val > this.pq.front()['element']){
        this.pq.dequeue()
        this.pq.enqueue(val)
    }
    return this.pq.front()['element']
};

/**
 * Your KthLargest object will be instantiated and called as such:
 * var obj = new KthLargest(k, nums)
 * var param_1 = obj.add(val)
 */