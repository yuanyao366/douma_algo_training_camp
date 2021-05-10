/**
 * @param {number[]} arr
 * @param {number} k
 * @return {number}
 */
var findKthPositive = function(arr, k) {
    if (arr[0] > k) return k
    let left = 0, right = arr.length
    while (left < right) {
        const mid = Math.floor((left + right) / 2)
        const x = (mid < arr.length) ? arr[mid] : 2000000
        if (x - mid - 1 < k) left = mid + 1
        else right = mid
    }
    const leftMissCount = arr[left - 1] - (left - 1) - 1
    return arr[left - 1] + (k - leftMissCount)
};