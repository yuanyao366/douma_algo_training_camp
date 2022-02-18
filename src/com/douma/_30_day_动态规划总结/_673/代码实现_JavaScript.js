/**
 * @param {number[]} nums
 * @return {number}
 */
var findNumberOfLIS = function(nums) {
    const n = nums.length
    if (n <= 1) return n
    // 1. 状态(双状态)数组的定义
    // lengths[i] 表示在子区间 [0...i] 且以 nums[i] 结尾的最长递增子序列的长度
    // 每个元素都可以以自身为一个长度的子序列，所以 lengths 初始化为 1
    const lengths = new Array(n).fill(1)
    // counts[i] 表示在子区间 [0...i] 且以 nums[i] 结尾的最长递增子序列的个数
    // 因为每个元素可以以自身结尾的最长子序列的情况至少有一种，所以 counts 初始化为 1
    const counts = new Array(n).fill(1)

    // 3. 状态转移
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < i; j++) {
            // 需要符合递增条件
            if (nums[j] < nums[i]) {
                // 以 nums[j] 结尾的最长递增序列长度 >= 以 nums[i] 结尾的最长递增序列长度
                if (lengths[j] >= lengths[i]) {
                    // 更新以 nums[i] 结尾的最长递增序列的长度和个数
                    lengths[i] = lengths[j] + 1
                    counts[i] = counts[j]
                } else if (lengths[j] + 1 == lengths[i]) {
                    counts[i] += counts[j]
                }
            }
        }
    }

    // 求最长递增序列的长度
    let longest = 0
    for (const len of lengths) longest = Math.max(longest, len)

    // 求最长递增序列的个数
    let ans = 0;
    for (let i = 0; i < n; i++) {
        if (lengths[i] == longest) {
            ans += counts[i];
        }
    }

    return ans;
};