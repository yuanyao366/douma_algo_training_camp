/**
 * @param {number[]} nums
 * @return {number[]}
 */
// 归并排序
var sortArray = function(nums) {
    mergeSort(nums, 0, nums.length - 1)
    return nums
};

var mergeSort = function(nums, lo, hi) {
    if (lo >= hi) return
    const mid = lo + Math.floor((hi - lo) / 2)
    mergeSort(nums, lo, mid)
    mergeSort(nums, mid + 1, hi)

    const tmp = new Array(hi - lo + 1)
    for (let i = lo; i <= hi; i++) tmp[i] = nums[i]

    let i = lo, j = mid + 1
    for (let k = lo; k <= hi; k++) {
        if (i == mid + 1) nums[k] = tmp[j++]
        else if (j == hi + 1) nums[k] = tmp[i++]
        else if (tmp[i] <= tmp[j]) nums[k] = tmp[i++]
        else nums[k] = tmp[j++]
    }
}

// 快速排序
var sortArray1 = function(nums) {
    quickSort(nums, 0, nums.length - 1)
    return nums
};

var quickSort = function(nums, lo, hi) {
    if (lo >= hi) return
    const index = partition(nums, lo, hi)
    quickSort(nums, lo, index - 1)
    quickSort(nums, index + 1, hi)
}

var partition = function(nums, lo, hi) {
    const i = Math.floor(Math.random() * (hi - lo + 1))+ lo;
    [nums[i], nums[hi]] = [nums[hi], nums[i]]
    const pivot = nums[hi]
    let less = lo
    for (let great = lo; great <= hi - 1; great++) {
        if (nums[great] < pivot) {
            [nums[less], nums[great]] = [nums[great], nums[less]]
            less++
        }
    }
    [nums[less], nums[hi]] = [nums[hi], nums[less]]
    return less
}

// 2. 快速排序 - 三路快排
var sortArray = function(nums) {

    const quickSort = (lo, hi) => {
        if (lo >= hi) return
        let i = Math.floor(Math.random() * (hi - lo + 1))+ lo;
        [nums[i], nums[hi]] = [nums[hi], nums[i]]
        const pivot = nums[hi]
        let less = lo, great = hi
        i = lo
        while (i <= great) {
            if (nums[i] < pivot) {
                [nums[i], nums[less]] = [nums[less], nums[i]]
                less++
                i++
            } else if (nums[i] > pivot) {
                [nums[i], nums[great]] = [nums[great], nums[i]]
                great--
            } else {
                i++
            }
        }
        quickSort(lo, less - 1)
        quickSort(great + 1, hi)
    }

    quickSort(0, nums.length - 1)
    return nums
};