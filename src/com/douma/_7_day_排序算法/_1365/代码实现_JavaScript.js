/**
 * @param {number[]} nums
 * @return {number[]}
 */
var smallerNumbersThanCurrent = function(nums) {
    const n = nums.length;
    const cnt = new Array(101).fill(0)
    const res = new Array(n).fill(0)

    for (const num of nums) cnt[num]++
    for (let i = 1; i < 101; i++) {
        cnt[i] += cnt[i - 1]
    }

    for (let i = 0; i < n; i++) {
        res[i] = nums[i] == 0 ? 0 : cnt[nums[i] - 1];
    }

    return res
};