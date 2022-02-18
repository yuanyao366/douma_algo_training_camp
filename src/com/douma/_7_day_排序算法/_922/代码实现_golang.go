// 方法一
// 时间复杂度：O(2n)
// 空间复杂度：O(n)
func sortArrayByParityII1(nums []int) []int {
    var ans = make([]int, len(nums))

    var count = 0
    for i := 0; i < len(nums); i++ {
        if nums[i] % 2 == 0 {
            ans[count] = nums[i]
            count += 2
        }
    }

    count = 1
    for i := 0; i < len(nums); i++ {
        if nums[i] % 2 == 1 {
            ans[count] = nums[i]
            count += 2
        }
    }

    return ans
}

// 方法二
// 时间复杂度：O(n)
// 空间复杂度：O(1)
func sortArrayByParityII(nums []int) []int {
    var i, j = 0, 1
    for i < len(nums) {
        // 如果当前偶数位置是奇数元素的话
        if nums[i] % 2 == 1 {
            // 那么在奇数位置上找到一个偶数，与之交换
            for nums[j] % 2 == 1 {
                j += 2
            }
            nums[i], nums[j] = nums[j], nums[i]
        }
        i += 2
    }
    return nums
}