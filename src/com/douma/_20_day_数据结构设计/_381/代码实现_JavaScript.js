/**
 * Initialize your data structure here.
 */
var RandomizedCollection = function() {
    this.idxMap = new Map()
    this.nums = []
};

/**
 * Inserts a value to the set. Returns true if the set did not already contain the specified element.
 * @param {number} val
 * @return {boolean}
 */
RandomizedCollection.prototype.insert = function(val) {
    if (!this.idxMap.has(val)) {
        this.idxMap.set(val, new Set())
    }
    const set = this.idxMap.get(val)
    set.add(this.nums.length)

    this.idxMap.set(val, set)
    this.nums.push(val)

    return set.size == 1
};

/**
 * Removes a value from the set. Returns true if the set contained the specified element.
 * @param {number} val
 * @return {boolean}
 */
RandomizedCollection.prototype.remove = function(val) {
    if (!this.idxMap.has(val)) return false

    const index = this.idxMap.get(val).keys().next().value
    const lastNum = this.nums[this.nums.length - 1]
    this.nums[index] = lastNum

    this.idxMap.get(val).delete(index)
    this.idxMap.get(lastNum).delete(this.nums.length - 1)
    if (index < this.nums.length - 1) {
        this.idxMap.get(lastNum).add(index)
    }

    this.nums.pop()
    if (this.idxMap.get(val).size == 0) {
        this.idxMap.delete(val)
    }

    return true
};

/**
 * Get a random element from the set.
 * @return {number}
 */
RandomizedCollection.prototype.getRandom = function() {
    return this.nums[Math.floor(Math.random() * this.nums.length)]
};

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * var obj = new RandomizedCollection()
 * var param_1 = obj.insert(val)
 * var param_2 = obj.remove(val)
 * var param_3 = obj.getRandom()
 */