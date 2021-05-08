/**
 * @param {number[]} nums
 * @return {number[]}
 */
var countSmaller = function(nums) {
    const n = nums.length

    const tmp = new Array(n).fill(0)
    const indexes = new Array(n).fill(0)
    for (let i = 0; i < n; i++) indexes[i] = i

    const tmpIndexes = new Array(n).fill(0)
    const ans = new Array(n).fill(0)

    var mergeSort = function(lo, hi) {
        if (lo >= hi) return

        const mid = lo + Math.floor((hi - lo) / 2)
        mergeSort(lo, mid)
        mergeSort(mid + 1, hi)

        for (let i = lo; i <= hi; i++) {
            tmp[i] = nums[i]
            tmpIndexes[i] = indexes[i]
        }

        let i = lo, j = mid + 1
        for (let k = lo; k <= hi; k++) {
            if (i == mid + 1) {
                nums[k] = tmp[j]
                indexes[k] = tmpIndexes[j]
                j++
            } else if (j == hi + 1) {
                nums[k] = tmp[i]
                indexes[k] = tmpIndexes[i]
                ans[tmpIndexes[i]] += (j - mid - 1)
                i++
            } else if (tmp[i] <= tmp[j]) {
                nums[k] = tmp[i]
                indexes[k] = tmpIndexes[i]
                ans[tmpIndexes[i]] += (j - mid - 1)
                i++
            } else {
                nums[k] = tmp[j]
                indexes[k] = tmpIndexes[j]
                j++
            }
        }
    }

    mergeSort(0, n - 1)

    return ans
};