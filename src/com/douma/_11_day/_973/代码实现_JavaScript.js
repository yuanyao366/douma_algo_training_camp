/**
 * @param {number[][]} points
 * @param {number} k
 * @return {number[][]}
 */
// 大顶堆
var kClosest1 = function(points, k) {
    const pq = new MaxPriorityQueue({priority: (point) => point[0] * point[0] + point[1] * point[1]})
    for (let i = 0; i < k; i++) pq.enqueue(points[i])
    for (let i = k; i < points.length; i++) {
        pq.enqueue(points[i])
        pq.dequeue()
    }
    const ans = []
    let index = 0
    while (pq.size() > 0) {
        ans[index++] = pq.dequeue()["element"]
    }

    return ans
};

// 快速排序分区优化
var kClosest = function(points, k) {
    let left = 0, right = points.length - 1
    const target = points.length - k
    while (true) {
        const pivotIndex = partition(points, left, right)
        if (pivotIndex == target) {
            break
        } else if (pivotIndex < target) {
            left = pivotIndex + 1
        } else {
            right = pivotIndex - 1
        }
    }
    const ans = []
    for (let i = points.length - k; i < points.length; i++) {
        ans.push(points[i])
    }
    return ans
}

var partition = function(nums, lo, hi) {
    const i = Math.floor(Math.random() * (hi - lo + 1))+ lo;
    [nums[i], nums[hi]] = [nums[hi], nums[i]]
    const pivot = nums[hi][0] * nums[hi][0] + nums[hi][1] * nums[hi][1]
    let less = lo
    for (let great = lo; great <= hi - 1; great++) {
        const num = nums[great][0] * nums[great][0] + nums[great][1] * nums[great][1]
        // 降序排列
        if (num >= pivot) {
            [nums[less], nums[great]] = [nums[great], nums[less]]
            less++
        }
    }
    [nums[less], nums[hi]] = [nums[hi], nums[less]]
    return less
}