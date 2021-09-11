func moveZeroes1(nums []int)  {
    if len(nums) == 0 {
        return
    }

    slow, fast := 0, 0
    for fast < len(nums) {
        if nums[fast] != 0 {
            if slow != fast { // 减少交换的次数
                nums[slow], nums[fast] = nums[fast], nums[slow]
            }
            slow++
        }
        fast++
    }
}

func moveZeroes(nums []int)  {
    if len(nums) == 0 {
        return
    }

    slow, fast := 0, 0
    for fast < len(nums) {
        if nums[fast] != 0 {
            if slow != fast { // 减少交换的次数
                // 使用直接赋值代替交换
                nums[slow] = nums[fast]
            }
            slow++
        }
        fast++
    }
    for i := slow; i < len(nums); i++ {
        nums[i] = 0
    }
}