/**
 * @param {number[]} nums
 * @return {number}
 */
var reversePairs = function(nums) {
    return mergeSort(nums, 0, nums.length - 1)
};

var mergeSort = function(nums, lo, hi) {
    if (lo >= hi) return 0
    const mid = lo + Math.floor((hi - lo) / 2)
    const left = mergeSort(nums, lo, mid)
    const right = mergeSort(nums, mid + 1, hi)

    const tmp = new Array(hi - lo + 1)
    for (let i = lo; i <= hi; i++) tmp[i] = nums[i]

    let i = lo, j = mid + 1, count = 0
    for (let k = lo; k <= hi; k++) {
        if (i == mid + 1) nums[k] = tmp[j++]
        else if (j == hi + 1) nums[k] = tmp[i++]
        else if (tmp[i] <= tmp[j]) nums[k] = tmp[i++]
        else {
            nums[k] = tmp[j++]
            count += (mid - i + 1)
        }
    }

    return left + right + count
}