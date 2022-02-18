/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var subarraySum = function(nums, k) {
    const mp = new Map()
    mp.set(0, 1)
    let res = 0, prefixSum = 0
    for (const num of nums) {
        prefixSum += num
        const diff = prefixSum - k
        if (mp.has(diff)) res += mp.get(diff)

        if (mp.has(prefixSum)) {
            mp.set(prefixSum, mp.get(prefixSum) + 1)
        } else {
            mp.set(prefixSum, 1)
        }
    }
    return res
};