var largestNumber = function(nums) {
    nums = nums.sort((a, b) => {
        const s1 = `${a}${b}`
        const s2 = `${b}${a}`
        return s2 - s1
    })
    return nums[0] ? nums.join('') : '0'
};