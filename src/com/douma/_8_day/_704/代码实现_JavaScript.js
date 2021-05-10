// 思路二：在循环体内排除没有目标值的区间
var search = function(nums, target) {
    let left = 0, right = nums.length - 1
    while (left + 1 < right) {
        const mid = Math.floor((left + right) / 2)
        if (nums[mid] == target) return mid
        else if (nums[mid] < target) left = mid + 1
        else right = mid - 1
    }
    if (nums[left] == target) return left
    return nums[right] == target ? right : -1
};

// 思路二：在循环体内排除没有目标值的区间
var search3 = function(nums, target) {
    let left = 0, right = nums.length - 1
    while (left < right) {
        const mid = Math.floor((left + right + 1) / 2)
        if (nums[mid] == target) return mid
        else if (nums[mid] < target) left = mid
        else right = mid - 1
    }
    return nums[left] == target ? left : -1
};

// 思路二：在循环体内排除没有目标值的区间
var search2 = function(nums, target) {
    let left = 0, right = nums.length - 1
    while (left < right) {
        const mid = Math.floor((left + right) / 2)
        if (nums[mid] == target) return mid
        else if (nums[mid] < target) left = mid + 1
        else right = mid
    }
    return nums[left] == target ? left : -1
};

// 思路一：在循环体内查找目标值
var search1 = function(nums, target) {
    let left = 0, right = nums.length - 1
    while (left <= right) {
        const mid = Math.floor((left + right) / 2)
        if (nums[mid] == target) return mid
        else if (nums[mid] < target) left = mid + 1
        else right = mid - 1
    }
    return -1
};