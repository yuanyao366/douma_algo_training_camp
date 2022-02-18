// 对撞指针
func removeElement(nums []int, val int) int {
    if len(nums) == 0 {
        return 0
    }
    left, right := 0, len(nums) - 1
    for left <= right {
        // 遇到要删除的元素，我们使用数组最后一个元素来替换这个元素
        // 替换后的数字有可能也是要删除的元素，那就接着判断
        // 但是至少，我们从数组的最后删除了一个元素
        if nums[left] == val {
            nums[left] = nums[right]
            right--
        } else {
            left++
        }
    }
    return right + 1
}

// 快慢指针
func removeElement1(nums []int, val int) int {
    if len(nums) == 0 {
        return 0
    }
    slow, fast := 0, 0
    for fast < len(nums) {
        if nums[fast] != val {
            nums[slow] = nums[fast]
            slow++
        }
        fast++
    }
    return slow
}