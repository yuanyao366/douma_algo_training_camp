/**
 * @param {number[]} numbers
 * @param {number} target
 * @return {number[]}
 */
// 二分查找
// 时间复杂度：O(nlogn)
// 空间复杂度：O(1)
var twoSum1 = function(numbers, target) {
    for (let i = 0; i < numbers.length; i++) {
        const x = numbers[i]
        const index = binarySearch(numbers, i + 1, numbers.length - 1, target - x)
        if (index != -1) {
            return [i + 1, index + 1]
        }
    }
    return []
};

var binarySearch = function(number, left, right, target) {
    while (left <= right) {
        const mid = left + Math.floor((right - left) / 2)
        if (target == number[mid]) {
            return mid;
        } else if (target < number[mid]) {
            right = mid - 1
        } else {
            left = mid + 1
        }
    }
    return -1
}

// 双指针
// 时间复杂度：O(n)
// 空间复杂度：O(1)
var twoSum = function(numbers, target) {
    let left = 0, right = numbers.length - 1
    while (left < right) {
        const sum = numbers[left] + numbers[right]
        if (sum == target) {
            return [left + 1, right + 1]
        } else if (sum < target) {
            left++
        } else {
            right--
        }
    }
    return []
};