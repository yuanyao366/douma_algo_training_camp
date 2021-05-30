/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
// 线性查找
// 时间复杂度：O(m*n*(min(m, n)))
// 空间复杂度：min(m, n)
var intersection1 = function(nums1, nums2) {
    const res = []
    for (const num1 of nums1) {
        for (const num2 of nums2) {
            if (num1 == num2) {
                if (res.indexOf(num1) == -1) {
                    res.push(num1)
                }
            }
        }
    }
    return res
};

// 线性查找
// 时间复杂度：O(m*n)
// 空间复杂度：min(m, n)
var intersection2 = function(nums1, nums2) {
    const res = new Set()
    for (const num1 of nums1) {
        for (const num2 of nums2) {
            if (num1 == num2) {
                res.add(num1)
            }
        }
    }
    return Array.from(res)
};

// 二分查找
// 时间复杂度：O((m + n)logn)
// 空间复杂度：min(m, n)
var intersection3 = function(nums1, nums2) {
    const res = new Set()
    nums2.sort((a, b) => a - b)
    for (const num1 of nums1) {
        if (contains(nums2, num1)) {
            res.add(num1)
        }
    }
    return Array.from(res)
};

var contains = function(nums, target) {
    let left = 0, right = nums.length
    while (left <= right) {
        const mid = left + Math.floor((right - left) / 2)
        if (target == nums[mid]) {
            return true
        } else if (target < nums[mid]) {
            right = mid - 1
        } else {
            left = mid + 1
        }
    }
    return false
}


// 哈希查找
// 时间复杂度：O(m + n)
// 空间复杂度：O(m + n)
var intersection4 = function(nums1, nums2) {
    const set1 = new Set()
    const set2 = new Set(nums2)
    for (const num1 of nums1) {
        if (set2.has(num1)) set1.add(num1)
    }
    return Array.from(set1)
};


// 排序查找
// 时间复杂度：O(m + n)
// 空间复杂度：O(m + n)
var intersection = function(nums1, nums2) {
    nums1.sort((a, b) => a - b)
    nums2.sort((a, b) => a - b)

    const res = []
    let i = 0, j = 0
    while (i < nums1.length && j < nums2.length) {
        if (nums1[i] == nums2[j]) {
            if (res.length == 0 || res[res.length - 1] != nums1[i]) {
                res.push(nums1[i])
            }
            i++
            j++
        } else if (nums1[i] < nums2[j]) {
            i++
        } else {
            j++
        }
    }
    return res
};