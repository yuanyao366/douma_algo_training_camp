/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
var intersect1 = function(nums1, nums2) {
    const countMap = new Map()
    for (const num of nums1) {
        if (countMap[num]) countMap[num]++
        else countMap[num] = 1
    }

    const res = []
    for (const num of nums2) {
        if (countMap[num] > 0) {
            res.push(num)
            countMap[num]--
        }
    }
    return res
};

var intersect2 = function(nums1, nums2) {
    nums1.sort((a, b) => a - b)
    nums2.sort((a, b) => a - b)

    const res = []
    let i = 0
    while (i < nums1.length) {
        let lower_bound = searchFirstTarget(nums2, nums1[i])
        if (lower_bound == -1) {
            i++
            continue
        }

        let count = 0;
        while (lower_bound < nums2.length && nums2[lower_bound] == nums1[i]) {
            count++
            lower_bound++
        }
        let j = i
        while (j < nums1.length && nums1[j] == nums1[i]) {
            j++
            if (count > 0) {
                res.push(nums1[i])
                count--
            }
        }
        i = j
    }
    return res
};

var searchFirstTarget = function(nums, target) {
    let left = 0, right = nums.length - 1
    while (left < right) {
        const mid = left + Math.floor((right - left) / 2)
        if (nums[mid] < target) {
            left = mid + 1
        } else {
            right = mid
        }
    }
    return nums[left] == target ? left : -1
}

var intersect = function(nums1, nums2) {
    nums1.sort((a, b) => a - b)
    nums2.sort((a, b) => a - b)
    const res = []
    let i = 0, j = 0
    while (i < nums1.length && j < nums2.length) {
        if (nums1[i] == nums2[j]) {
            res.push(nums1[i])
            i++
            j++
        } else if (nums1[i] < nums2[j]) {
            i++
        } else {
            j++
        }
    }
    return res
}