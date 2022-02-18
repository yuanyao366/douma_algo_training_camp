func threeSum(nums []int) [][]int {
    if len(nums) < 3 {
        return [][]int{}
    }

    var res = make([][]int, 0)

    sort.Ints(nums) // O(nlogn)

    // O(n^2)
    for i := 0; i < len(nums) - 2; i++ {
        if i > 0 && nums[i] == nums[i - 1] {
            continue
        }
        // 在 i + 1 ... nums.length - 1 中查找相加等于 -nums[i] 的两个数
        var target = -nums[i]
        var left, right = i + 1, len(nums) - 1
        for left < right {
            var sum = nums[left] + nums[right]
            if sum == target {
                res = append(res, []int{nums[i], nums[left], nums[right]})
                // 去重
                for left < right {
                    left++
                    if nums[left - 1] != nums[left] {
                        break
                    }
                }
                for left < right {
                    right--
                    if nums[right + 1] != nums[right] {
                        break
                    }
                }
            } else if sum < target {
                left++
            } else {
                right--
            }
        }
    }
    return res
}