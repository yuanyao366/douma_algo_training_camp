/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function(nums, target) {
    const map = new Map()

    for (let i = 0; i < nums.length; i++) {
        const x = nums[i];
        if (map.has(target - x)) {
            const index = map.get(target - x)
            return [i, index]
        }
        map.set(x, i)
    }

    return []
};

var twoSum2 = function(nums, target) {
    const map = new Map()
    nums.forEach((num, i) => map.set(num, i))

    for (let i = 0; i < nums.length; i++) {
        const x = nums[i];
        if (map.has(target - x)) {
            const index = map.get(target - x)
            if (i != index) return [i, index]
        }
    }

    return []
};

var twoSum1 = function(nums, target) {
    for (let i = 0; i < nums.length; i++) {
        const x = nums[i];
        for (let j = i + 1; j < nums.length; j++) {
            if (nums[j] == target - x) {
                return [i, j]
            }
        }
    }
    return []
};