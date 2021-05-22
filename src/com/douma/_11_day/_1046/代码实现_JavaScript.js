/**
 * @param {number[]} stones
 * @return {number}
 */
var lastStoneWeight1 = function(stones) {
    const n = stones.length
    if (n == 1) return stones[0]

    for (let i = 0; i < n - 1; i++) {
        stones.sort((a, b) => a - b)
        console.log(stones)
        const y = stones[n - 1]
        const x = stones[n - 2]
        if (x == 0) break
        stones[n - 1] = y - x
        stones[n - 2] = 0
    }

    return stones[n - 1]
};

var lastStoneWeight = function(stones) {
    const n = stones.length
    if (n == 1) return stones[0]

    // 使用 ES6 的优先队列：https://github.com/datastructures-js/priority-queue
    const pq = new MaxPriorityQueue()
    for (const stone of stones) {
        pq.enqueue('x', stone)
    }

    while (pq.size() > 1) {
        const y = pq.dequeue()['priority']
        const x = pq.dequeue()['priority']
        if (x != y) {
            pq.enqueue('x', y - x)
        }
    }

    return pq.isEmpty() ? 0 : pq.dequeue()['priority']
};