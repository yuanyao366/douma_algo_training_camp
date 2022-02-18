/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
 // 小顶堆 + 大顶堆
// 时间复杂度：O(nlogk)
// 空间复杂度：O(min(k, n - k))
var findKthLargest1 = function(nums, k) {
    const n = nums.length
    if (k < n - k) {
        // 使用 ES6 的优先队列：https://github.com/datastructures-js/priority-queue
        const pq = new MinPriorityQueue({priority: (num) => num})
        for (let i = 0; i < k; i++) {
            pq.enqueue(nums[i])
        }
        for (let i = k; i < n; i++) {
            if (nums[i] > pq.front()["element"]) {
                pq.dequeue();
                pq.enqueue(nums[i])
            }
        }
        return pq.front()['element']
    } else {
        const pq = new MaxPriorityQueue({priority: (num) => num})
        const capacity = n - k + 1
        for (let i = 0; i < capacity; i++) {
            pq.enqueue(nums[i])
        }
        for (let i = capacity; i < n; i++) {
            if (nums[i] < pq.front()["element"]) {
                pq.dequeue();
                pq.enqueue(nums[i])
            }
        }
        return pq.front()['element']
    }
};

// 快速排序分区优化
// 时间复杂度：O(n)
// 空间复杂度：O(1)
var findKthLargest = function(nums, k) {
    let left = 0, right = nums.length - 1
    const target = nums.length - k
    while (true) {
        const pivotIndex = partition(nums, left, right)
        if (pivotIndex == target) {
            return nums[pivotIndex]
        } else if (pivotIndex < target) {
            left = pivotIndex + 1
        } else {
            right = pivotIndex - 1
        }
    }
}

var partition = function(nums, lo, hi) {
    const i = Math.floor(Math.random() * (hi - lo + 1))+ lo;
    [nums[i], nums[hi]] = [nums[hi], nums[i]]
    const pivot = nums[hi]
    let less = lo
    for (let great = lo; great <= hi - 1; great++) {
        if (nums[great] < pivot) {
            [nums[less], nums[great]] = [nums[great], nums[less]]
            less++
        }
    }
    [nums[less], nums[hi]] = [nums[hi], nums[less]]
    return less
}