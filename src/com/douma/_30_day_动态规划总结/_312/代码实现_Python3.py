from typing import List



class Solution:
    # 动态回归
    # 状态定义：dp[i][j] 表示不戳破 i 与 j ，仅戳破 i 与 j 之间的气球我们能得到的最大金币数
    # 状态转移方程：
    #  dp[i][j] = max {dp[i][k] + dp[k][j] + nums[i]*nums[k]*nums[j]} | i < k < j
    #  上面方程中的 nums[i]*nums[k]*nums[j] 表示戳破气球 k 时能得到的金币数
    # 我们可以对上面的方程进行推演下，比如我们要求 dp[i][i + 2]：
    #  dp[i][i + 2] = dp[i][i + 1] + dp[i + 1][i + 2] + nums[i]*nums[i + 1]*nums[i + 2]
    #  其中 dp[i][i + 1] 和 dp[i + 1][i + 2] 都是返回 0，
    #  因为 i 到 i + 1 和 i + 1 到 i + 2 之间都没有气球，
    #  而我们对 dp[i][j] 的定义是不戳破 i 与 j ，仅戳破 i 与 j 之间的气球我们能得到的最大金币数
    # 所以说  dp[i][i + 2] = nums[i]*nums[i + 1]*nums[i + 2]，其实就是等于戳破 i + 1 获得的金币
    def maxCoins(self, nums: List[int]) -> int:
        # 加虚拟边界 nums[-1] = nums[nums.length] = 1;
        numsTemp = [0] * (len(nums) + 2)
        for i in range(1, len(nums) + 1):
            numsTemp[i] = nums[i - 1]
        numsTemp[0] = 1
        numsTemp[len(nums) + 1] = 1

        # 创建 dp 数组，并且 dp 数组中的每个元素都初始化成 0
        length = len(numsTemp)
        dp = [[0] * length for _ in range(length)]

        # 状态转移方程(计算不戳破 i 与 j ，仅戳破 i 与 j 之间的气球我们能得到的最大金币数)：
        #  dp[i][j] = max {dp[i][k] + dp[k][j] + nums[i]*nums[k]*nums[j]} | i < k < j
        # 这里 i 从右往左遍历的原因：
        # dp[i][j] 依赖于 dp[k][j] ，然而 k 又是大于 i 的，所以需要先计算数组右边的状态
        # 也就是 i 需要从右往左遍历
        for i in range(length - 2, -1, -1):
            # 这里 +2 的原因：保证 (i, j) 之间肯定 1 个元素，因为没有元素的话，dp[i][j] 就等于 0
            for j in range(i + 2, length):
                max_ = 0
                # 计算戳破 k 气球得到的最大金币数
                for k in range(i + 1, j):
                    # 注意：使用增加了虚拟边界的数组 numsTemp
                    temp = dp[i][k] + dp[k][j] + numsTemp[i] * numsTemp[k] * numsTemp[j];
                    if temp > max_: max_ = temp
                # 记录状态值
                dp[i][j] = max_

        # 返回最终获得的最大硬币数
        # 当然是：计算不戳破 0 与 length - 1 ，仅戳破 0 与 length - 1 之间的气球我们能得到的最大金币数
        # 因为前面我们对原数组 nums 增加了虚拟边界，所以返回 dp[0][length - 1] 就是返回最终获得的最大硬币数
        return dp[0][length - 1]