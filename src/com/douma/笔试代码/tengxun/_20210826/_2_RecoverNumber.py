# 抖码算法，让算法学习变得简单有趣
# 作者：老汤


# 时间复杂度：O(nk)
# 空间复杂度：O(nk)
def recover_num(nums, k):
    n, mod = len(nums), int(10e9 + 7)

    # 1. 使用动态规划，求连续 i 个 0 上，填充 k 个数字的方案数
    # dp[i][j] 表示在连续 i 个 0 上，填充 k 个数字的方案数
    dp = [[0] * (k + 1) for _ in range(n + 1)]
    for i in range(1, n + 1):
        # 连续 i 个 0 上，填充 1 个数字的方案数就是 1 种
        dp[i][1] = 1
    for i in range(1, k + 1):
        # 连续 1 个 0 上，填充 i 个数字的方案数就是 i 种
        dp[1][i] = i

    for i in range(2, n + 1):
        for j in range(2, k + 1):
            # 连续 i 个 0 上，填充 j 个数字的方案数等于：
            # 连续 i 个 0 上，填充 j - 1 个数字的方案数 + 连续 i - 1 个 0 上，填充 j 个数字的方案数
            dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % mod

    # 2. 利用上面的 dp 状态值求解恢复数组的方案数
    pre_min_num, max_num = 1, k
    ans = i = 0
    while i < n:
        # 计算连续的 0 的个数
        zero_cnt = 0
        while i < n and nums[i] == 0:
            zero_cnt, i = zero_cnt + 1, i + 1
        # 计算可以在连续 0 上填充的数字的个数
        num_cnt = 0
        if i < n:
            num_cnt = nums[i] - pre_min_num + 1
            pre_min_num = nums[i]
        else:
            num_cnt = max_num - pre_min_num + 1
        # 累加方案数
        ans += dp[zero_cnt][num_cnt] % mod
        i += 1

    return ans


if __name__ == '__main__':
    nums = [0, 0, 2, 0, 0, 0, 6]
    print(recover_num(nums, 10))