/**
 * @param {number} n
 * @return {boolean}
 */

var squareSum = function(n) {
    let sum = 0
    while (n) {
        const num = n % 10
        sum += num * num
        n = Math.floor(n / 10)
    }
    return sum
}

// 哈希表
var isHappy1 = function(n) {
    const visited = new Set()
    while (true) {
        if (n == 1) return true
        if (visited.has(n)) return false

        visited.add(n)
        n = squareSum(n)
    }
};


// 快慢指针
var isHappy = function(n) {
    if (n == 1) return true
    let slow = n, fast = n
    while (true) {
        slow = squareSum(slow)
        fast = squareSum(squareSum(fast))
        if (slow == 1 || fast == 1) return true
        if (slow == fast) return false
    }
};