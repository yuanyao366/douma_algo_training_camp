// 前缀和 + 哈希查找
func subarraySum1(nums []int, k int) int {
    var prefixSum = make([]int, len(nums) + 1)
    prefixSum[0] = 0
    for i := 1; i <= len(nums); i++ {
        prefixSum[i] = prefixSum[i - 1] + nums[i - 1]
    }

    var m, res = make(map[int]int), 0
    for i := range prefixSum {
        var diff = prefixSum[i] - k
        if value, ok := m[diff]; ok {
            res += value
        }
        if _, ok := m[prefixSum[i]]; ok {
            m[prefixSum[i]] += 1
        } else {
            m[prefixSum[i]] = 1
        }
    }

    return res
}

// 前缀和（优化成一个变量） + 哈希查找
func subarraySum(nums []int, k int) int {
    var prefixSum = 0
    var m, res = make(map[int]int), 0
    m[0] = 1
    for i := range nums {
        prefixSum += nums[i]
        var diff = prefixSum - k
        if value, ok := m[diff]; ok {
            res += value
        }
        if _, ok := m[prefixSum]; ok {
            m[prefixSum] += 1
        } else {
            m[prefixSum] = 1
        }
    }

    return res
}