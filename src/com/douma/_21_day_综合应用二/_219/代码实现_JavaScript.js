/**
 * @param {number[]} nums
 * @param {number} k
 * @return {boolean}
 */
// 哈希表
// 时间复杂度：O(n)
// 空间复杂度：O(n)
var containsNearbyDuplicate1 = function(nums, k) {
    const indexMap = new Map()
    for (let i = 0; i < nums.length; i++) {
        if (indexMap.has(nums[i]) && i - indexMap.get(nums[i]) <= k) {
            return true
        }
        indexMap.set(nums[i], i)
    }
    return false
};

// 滑动窗口
// 时间复杂度：O(n)
// 空间复杂度：O(k)
var containsNearbyDuplicate = function(nums, k) {
    const window = new Set()
    let left = 0, right = 0
    while (right < nums.length) {
        if (window.has(nums[right])) return true
        window.add(nums[right])
        if (window.size > k) {
            window.delete(nums[left])
            left++
        }
        right++
    }
    return false
};