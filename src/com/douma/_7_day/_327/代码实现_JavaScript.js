/**
 * @param {number[]} nums
 * @param {number} lower
 * @param {number} upper
 * @return {number}
 */
var countRangeSum = function(nums, lower, upper) {
    const prefixSum = new Array(nums.length + 1).fill(0)
    for (let i = 0; i < nums.length; i++) {
        prefixSum[i + 1] = prefixSum[i] + nums[i]
    }
    return mergeSort(prefixSum, lower, upper, 0, prefixSum.length - 1)
};

var mergeSort = function(prefixSum, lower, upper, lo, hi) {
    if (lo >= hi) return 0
    const mid = lo + Math.floor((hi - lo) / 2)
    const left = mergeSort(prefixSum, lower, upper, lo, mid)
    const right = mergeSort(prefixSum, lower, upper, mid + 1, hi)

    let count = 0, i = lo
    let l = mid + 1
    let r = mid + 1
    while (i <= mid) {
        while (l <= hi && prefixSum[l] - prefixSum[i] < lower) l++
        while (r <= hi && prefixSum[r] - prefixSum[i] <= upper) r++
        count += (r - l)
        i++
    }

    merge(prefixSum, lo, mid, hi)

    return left + right + count
}

var merge = function(prefixSum, lo, mid, hi) {
    const tmp = new Array(hi - lo + 1)
    for (let i = lo; i <= hi; i++) tmp[i] = prefixSum[i]

    let i = lo, j = mid + 1
    for (let k = lo; k <= hi; k++) {
        if (i == mid + 1) prefixSum[k] = tmp[j++]
        else if (j == hi + 1) prefixSum[k] = tmp[i++]
        else if (tmp[i] <= tmp[j]) prefixSum[k] = tmp[i++]
        else prefixSum[k] = tmp[j++]
    }
}