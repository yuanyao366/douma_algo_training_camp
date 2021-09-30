func minPatches(nums []int, n int) int {
    var res = 0
    // 贪心的保证 [1, x - 1] 这个区间中所有数字会被覆盖
    var x = 1
    var index = 0
    for x <= n {
        if index < len(nums) && nums[index] <= x {
            // 因为根据贪心思想，我们总保证区间小于x的所有值会被覆盖掉
            // [1, x + x - 1]  [1, x + nums[index] - 1]
            // 因此 x + 1，x + 2，... x + nums[index] - 1 都会被覆盖到，更新x += nums[index]
            x += nums[index]
            index++
        } else {
            // 把 x 放入数组中
            res++

            // 对于正整数 x，如果区间 [1,x-1] 内的所有数字都已经被覆盖，
            // 且 x 在数组中，则区间 [1,2x-1] 内的所有数字也都被覆盖
            x *= 2
        }
    }
    return res
}