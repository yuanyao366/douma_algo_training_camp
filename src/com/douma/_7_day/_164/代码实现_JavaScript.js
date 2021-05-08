/**
 * @param {number[]} nums
 * @return {number}
 */
var maximumGap = function(nums) {
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

    bucketNum = nums.length
    const buckets = new Array(bucketNum).fill(0).map(x => new Array(2).fill(0))
    for (let i = 0; i < bucketNum; i++) {
        buckets[i][0] = INT_MAX
        buckets[i][1] = INT_MIN
    }

    for (const num of nums) {
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