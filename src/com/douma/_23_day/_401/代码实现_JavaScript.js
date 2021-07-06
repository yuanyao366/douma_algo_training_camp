/**
 * @param {number} turnedOn
 * @return {string[]}
 */
var readBinaryWatch = function(turnedOn) {
    const nums1 = [8, 4, 2, 1]
    const nums2 = [32, 16, 8, 4, 2, 1]

    const dfs = (nums, count, startIndex, sum, res) => {
        if (count == 0) {
            res.push(sum)
            return
        }
        for (let i = startIndex; i < nums.length; i++) {
            dfs(nums, count - 1, i + 1, sum + nums[i], res)
        }
    }

    const genDigits = (nums, count) => {
        const res = []
        dfs(nums, count, 0, 0, res)
        return res
    }

    const res = []
    for (let i = 0; i <= turnedOn; i++) {
        const hours = genDigits(nums1, i)
        const minutes = genDigits(nums2, turnedOn - i)
        for (const hour of hours) {
            if (hour > 11) continue
            for (const minute of minutes) {
                if (minute > 59) continue
                res.push(hour + ":" + ((minute < 10) ? "0" + minute : minute))
            }
        }
    }
    return res
};