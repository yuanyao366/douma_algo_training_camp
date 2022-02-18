func nextPermutation(nums []int)  {
    // 1. 找到尽量靠右的【较小数】
    i := len(nums) - 2
    for i >= 0 && nums[i] >= nums[i + 1] {
        i--
    }

    // 2. 如果找到了【较小数】
    if i >= 0 {
        // 找到尽量靠右的【较大数】
        j := len(nums) - 1
        for j >= 0 && nums[i] >= nums[j] {
            j--
        }

        // 交换【较小数】和【较大数】
        nums[i], nums[j] = nums[j], nums[i]
    }

    // 3. 反转【较小数】 i 之后的所有元素
    reverse(nums, i + 1)
}

func reverse(nums []int, start int) {
    left, right := start, len(nums) - 1
    for left < right {
        nums[left], nums[right] = nums[right], nums[left]
        left++
        right--
    }
}