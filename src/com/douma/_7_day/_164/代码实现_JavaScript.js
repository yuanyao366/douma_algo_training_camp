/**
 * @param {number[]} nums
 * @return {number}
 */
const maximumGap = function(nums) {
    if (nums.length < 2) return 0

    const INT_MAX = 2147483647
    const INT_MIN = -2147483648

    let minVal = nums[0], maxVal = nums[0]
    for (const num of nums) {
        minVal = Math.min(minVal, num)
        maxVal = Math.max(maxVal, num)
    }
    if (minVal == maxVal) return 0

    const gap = Math.ceil((maxVal - minVal) / (nums.length - 1))

    const bucketNum = nums.length
    const buckets = new Array(bucketNum).fill(0).map(x => new Array(2).fill(0))
    for (let i = 0; i < bucketNum; i++) {
        buckets[i][0] = INT_MAX
        buckets[i][1] = INT_MIN
    }

    for (const num of nums) {
        // bucketId 计算逻辑如何理解，请参考 issue：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I498BD
        const bucketId = Math.floor((num - minVal) / gap)
        buckets[bucketId][0] = Math.min(buckets[bucketId][0], num)
        buckets[bucketId][1] = Math.max(buckets[bucketId][1], num)
    }

    let maxGap = 0, prev = minVal
    for (const bucket of buckets) {
        if (bucket[0] == INT_MAX) continue
        maxGap = Math.max(maxGap, bucket[0] - prev)
        prev = bucket[1]
    }

    return maxGap
};


// 基数排序
const maximumGap1 = function(nums) {
    if (nums.length < 2) return 0

    radixSort(nums); // O(n)

    let maxGap = 0;
    for (let i = 0; i < nums.length - 1; i++) {
        maxGap = Math.max(maxGap, nums[i + 1] - nums[i]);
    }

    return maxGap;
};

const radixSort = function (data) {
    if (data == null || data.length == 0) return

    // 1. 找到最大值  4006869915
    let max = data[0]
    for (let i = 1; i < data.length; i++) {
        max = Math.max(max, data[i])
    }

    // 2. 对数组按照每个元素的每位进行计数排序
    for (let exp = 1; Math.floor(max / exp) > 0; exp *= 10) { // O(n)
        countSort(data, exp) // 时间复杂度：O(n)
    }
}

// 时间复杂度：O(n)
const countSort = function (data, exp) {
    // 之所以是 10，是因为数字只有 0...9 十个数字
    const count = new Array(10).fill(0);

    for (let i = 0; i < data.length; i++) {
        // 个位数： (234 / 1) % 10 = 4
        // 十位数： (234 / 10) % 10 = 3
        // 百位数： (234 / 100) % 10 = 2
        const digit = (Math.floor(data[i] / exp)) % 10
        count[digit]++
    }

    for (let i = 1; i < 10; i++) {
        count[i] += count[i - 1]
    }

    const output = new Array(data.length).fill(0)
    for (let i = data.length - 1; i >= 0; i--) {
        const digit = (Math.floor(data[i] / exp)) % 10
        const k = count[digit] - 1
        output[k] = data[i]
        count[digit]--
    }

    for (let i = 0; i < data.length; i++) {
        data[i] = output[i]
    }
}
