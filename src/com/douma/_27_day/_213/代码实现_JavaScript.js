/**
 * @param {number[]} nums
 * @return {number}
 */
var rob = function(nums) {
    const n = nums.length
    if (n == 1) return nums[0]

    const rob = (start, end) => {
        let prevMax = 0
        let currMax = 0

        for (let i = start; i <= end; i++) {
            const tmpMax = Math.max(currMax, prevMax + nums[i])
            prevMax = currMax
            currMax = tmpMax
        }

        return currMax
    }

    return Math.max(rob(1, n - 1), rob(0, n - 2))
};