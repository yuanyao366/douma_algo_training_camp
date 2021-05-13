/**
 * @param {number[]} nums
 * @return {number}
 */
var singleNumber = function(nums) {
    const map = new Map();
    for (const num of nums) {
        if (map.has(num)) {
            count = map[num] + 1
            map.set(num, count)
        } else {
            map.set(num, 1)
        }

    }
    for (const key of map.keys()) {
        if (map.get(key) == 1) return key
    }
    return -1
}

var singleNumber1 = function(nums) {
    let single = 0;
    for (const num of nums) {
        single ^= num
    }
    return single
};