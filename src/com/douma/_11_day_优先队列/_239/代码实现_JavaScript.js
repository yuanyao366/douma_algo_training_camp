/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number[]}
 */
// 优先队列 (大顶堆)
// 时间复杂度：O(nlogn)
// 空间复杂度：O(n)
var maxSlidingWindow1 = function(nums, k) {
    // tips：这里维护的是大顶堆
    // 两个元素值不想等的话，那么元素大的放在前面，
    // 如果两个元素值相等的话，坐标大的放在前面，这样坐标 小于等于 i - k 的机会就会少点，这样删除的动作就会少发生了，
    // 其实元素相等的时候哪个放在前面，哪个放在后面，都无所谓的
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
        // 说明：这里的 while 可以使用 if 来代替，因为：
        // 要维护一个大小为 k 的窗口的话，每次最多只需要处理一个元素即可
        if (deque.length && deque[0] <= i - k) {
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