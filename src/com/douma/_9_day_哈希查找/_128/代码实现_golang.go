// 排序解法
// 时间复杂度：O(nlogn)
// 空间复杂度：O(n)
func longestConsecutive1(nums []int) int {
    if len(nums) < 2 {
        return len(nums)
    }

    sort.Ints(nums)

    var ans, count = 1, 1
    for i := 1; i < len(nums); i++ {
        if nums[i] == nums[i - 1] {
            continue
        }
        if nums[i] - nums[i - 1] == 1 {
            count++
        } else {
            if count > ans {
                ans = count
            }
            count = 1
        }
    }
    if count > ans {
        return count
    } else {
        return ans
    }
}


// 哈希查找解法
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func longestConsecutive(nums []int) int {
    if len(nums) < 2 {
        return len(nums)
    }

    var set = make(map[int]bool)
    for _, num := range nums {
        set[num] = true
    }

    var ans = 1
    for _, num := range nums {
        // 这里会存在重复计算，为什么会产生以及如何解决，请参考 issue：
        // https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4H4RZ
        if set[num - 1] {
            continue
        }
        var currNum, count = num, 1
        for set[currNum + 1] {
            currNum += 1
            count += 1
        }
        if count > ans {
            ans = count
        }
    }
    return ans
}