/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number[]}
 */
// 小顶堆
// 时间复杂度：O(nlogk)
// 空间复杂度：O(n)
var topKFrequent1 = function(nums, k) {
    const count = new Map()
    for (const num of nums) {
        if (count.has(num)) {
            count.set(num, count.get(num) + 1)
        } else {
            count.set(num, 1)
        }
    }

    const uniqueNums = []
    for (const [key, value] of count) {
        uniqueNums.push({key, value})
    }

    const pq = new MinPriorityQueue({priority: (kv) => kv.value})
    for (let i = 0; i < k; i++) {
        pq.enqueue(uniqueNums[i])
    }

    for (let i = k; i < uniqueNums.length; i++) {
        if (uniqueNums[i].value > pq.front()["element"].value) {
            pq.dequeue();
            pq.enqueue(uniqueNums[i])
        }
    }

    const ans = []
    let index = 0
    while (pq.size() > 0) {
        ans[index++] = pq.dequeue()["element"].key
    }

    return ans
};

// 快速排序分区优化
// 时间复杂度：O(n)
// 空间复杂度：O(n)
var topKFrequent = function(nums, k) {
    const count = new Map()
    for (const num of nums) {
        if (count.has(num)) {
            count.set(num, count.get(num) + 1)
        } else {
            count.set(num, 1)
        }
    }

    const uniqueNums = []
    for (const [key, value] of count) {
        uniqueNums.push({key, value})
    }

    let left = 0, right = uniqueNums.length - 1
    const target = uniqueNums.length - k
    while (true) {
        const pivotIndex = partition(uniqueNums, left, right)
        if (pivotIndex == target) {
            break
        } else if (pivotIndex < target) {
            left = pivotIndex + 1
        } else {
            right = pivotIndex - 1
        }
    }
    const ans = []
    for (let i = uniqueNums.length - k; i < uniqueNums.length; i++) {
        ans.push(uniqueNums[i].key)
    }
    return ans
}

var partition = function(nums, lo, hi) {
    const i = Math.floor(Math.random() * (hi - lo + 1))+ lo;
    [nums[i], nums[hi]] = [nums[hi], nums[i]]
    const pivot = nums[hi].value
    let less = lo
    for (let great = lo; great <= hi - 1; great++) {
        if (nums[great].value < pivot) {
            [nums[less], nums[great]] = [nums[great], nums[less]]
            less++
        }
    }
    [nums[less], nums[hi]] = [nums[hi], nums[less]]
    return less
}