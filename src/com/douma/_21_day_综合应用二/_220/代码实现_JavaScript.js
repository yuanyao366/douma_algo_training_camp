/**
 * @param {number[]} nums
 * @param {number} k
 * @param {number} t
 * @return {boolean}
 */

// 滑动窗口 + 桶
// 时间复杂度：O(n)
// 空间复杂度：O(min(n, k))
var containsNearbyAlmostDuplicate = function(nums, k, t) {

    const getBucketId = (num, bucketSize) => {
        if (num >= 0) return Math.floor(num / bucketSize)
        return Math.floor((num + 1) / bucketSize) - 1
    }

    let left = 0, right = 0
    const bucketSize = t + 1, windowBucket = new Map()
    while (right < nums.length) {
        const x = nums[right]
        const bucketId = getBucketId(x, bucketSize)

        if (windowBucket.has(bucketId)) return true
        const leftBucketId = bucketId - 1, rightBucketId = bucketId + 1
        if (windowBucket.has(leftBucketId) && x - windowBucket.get(leftBucketId) <= t) return true
        if (windowBucket.has(rightBucketId) && windowBucket.get(rightBucketId) - x <= t) return true

        windowBucket.set(bucketId, x)

        if (windowBucket.size > k) {
            windowBucket.delete(getBucketId(nums[left], bucketSize))
            left++
        }

        right++
    }

    return false
};