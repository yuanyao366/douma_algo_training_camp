/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number[]}
 */
// 优先队列 (大顶堆)
// 时间复杂度：O(nlogn)
// 空间复杂度：O(n)
var maxSlidingWindow1 = function(nums, k) {
    const pq = new MaxPriorityQueue({priority: (num) => num[0]})
    for (let i = 0; i < k; i++) pq.enqueue([nums[i], i])
    ans = [pq.front()['element'][0]]
    for (let i = k; i < nums.length; i++) {
        pq.enqueue([nums[i], i])
        while (pq.front()['element'][1] <= i - k) {
            pq.dequeue()
        }
        ans.push(pq.front()['element'][0])
    }
    return ans
};

// 双端队列
// 时间复杂度：O(n)
// 空间复杂度：O(n)
var maxSlidingWindow = function(nums, k) {
    const ans = []
    const deque = []
    for (let i = 0; i < nums.length; i++) {
        while (deque.length && deque[0] <= i - k) {
            deque.shift()
        }
        while (deque.length && nums[i] > nums[deque[deque.length - 1]]) {
            deque.pop()
        }
        deque.push(i)
        if (i >= k - 1) ans.push(nums[deque[0]])
    }
    return ans
}