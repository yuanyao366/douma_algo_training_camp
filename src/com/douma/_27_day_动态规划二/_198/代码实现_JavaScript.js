/**
 * @param {number[]} nums
 * @return {number}
 */
// 1. 记忆化搜索(一)
var rob1 = function(nums) {
    const n = nums.length
    const memo = new Array(n).fill(-1)

    const dfs = (i) => {
        if (i >= n) return 0
        if (memo[i] != -1) return memo[i]

        const left = dfs(i + 1)
        const right = dfs(i + 2)

        memo[i] = Math.max(left, right + nums[i])
        return memo[i]
    }

    return dfs(0)
};

// 2. 记忆化搜索(二)
var rob = function(nums) {
    const n = nums.length
    const memo = new Array(n).fill(-1)

    const dfs = (i) => {
        if (i == 0) return nums[0]
        if (i == 1) return Math.max(nums[0], nums[1])

        if (memo[i] != -1) return memo[i]

        const left = dfs(i - 1)
        const right = dfs(i - 2)

        memo[i] = Math.max(left, right + nums[i])
        return memo[i]
    }

    return dfs(nums.length - 1)
};

// 3. 动态规划
var rob2 = function(nums) {
    const n = nums.length
    if (n == 1) return nums[0]

    const dp = new Array(n).fill(-1)
    dp[0] = nums[0]
    dp[1] = Math.max(nums[0], nums[1])

    for (let i = 2; i < n; i++) {
        dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i])
    }

    return dp[n - 1]
}

// 4. 动态规划 + 状态压缩
var rob4 = function(nums) {
    const n = nums.length
    if (n == 1) return nums[0]

    let prevMax = 0
    let currMax = 0

    for (const num of nums) {
        const tmpMax = Math.max(currMax, prevMax + num)
        prevMax = currMax
        currMax = tmpMax
    }

    return currMax
}