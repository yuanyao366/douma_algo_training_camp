// 抖码算法，让算法学习变的简单有趣
// 作者：老汤

class TwoSum {
    constructor() {
        this.nums = new Map()
    }

    add(number) {
        if (this.nums.has(number)) {
            this.nums.set(number, this.nums.get(number) + 1)
        } else {
            this.nums.set(number, 1)
        }
    }

    find(value) {
        for (const key of this.nums.keys()) {
            const target = value - key
            if (target == key && this.nums.get(target) > 1) return true
            if (target != key && this.nums.has(target)) return true
        }
        return false
    }
}