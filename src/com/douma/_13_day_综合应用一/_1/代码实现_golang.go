// 方法一：线性查找
// 时间复杂度：O(n^2)
// 空间复杂度：O(1)
func twoSum1(nums []int, target int) []int {
    var n = len(nums)
    for i := 0; i < n; i++ {
        var x = nums[i]
        // 线性查找
        for j := i + 1; j < n; j++ {
            if nums[j] == target - x {
                return []int{i, j}
            }
        }
    }
    return []int{}
}

// 方法二：两遍遍历 + 哈希查找
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 空间换时间
func twoSum2(nums []int, target int) []int {
    // 数据预处理
    var numIndexMap = make(map[int]int)
    for i := range nums {
        numIndexMap[nums[i]] = i
    }

    for i := range nums {
        var x = nums[i]
        // 2. 哈希查找 - O(1)
        if index, ok := numIndexMap[target - x]; ok {
            // i 和 index 不是同一个元素，同一个元素不能使用两次
            if i != index {
                return []int{i, index}
            }
        }
    }
    return []int{}
}

// 方法三：一遍遍历 + 哈希查找
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 空间换时间
func twoSum(nums []int, target int) []int {
    var numIndexMap = make(map[int]int)
    for i := range nums {
        var x = nums[i]
        // 2. 哈希查找 - O(1)
        if index, ok := numIndexMap[target - x]; ok {
            return []int{i, index}
        }
        numIndexMap[x] = i
    }
    return []int{}
}