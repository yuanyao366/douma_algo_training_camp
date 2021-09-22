// 方法一：两趟遍历
// 时间复杂度：O(2n)
// 空间复杂度：O(n)
func sortArrayByParity1(nums []int) []int {
    var ans = make([]int, len(nums))

    var count = 0
    for i := 0; i < len(nums); i++ {
        if nums[i] % 2 == 0 {
            ans[count] = nums[i]
            count++
        }
    }

    for i := 0; i < len(nums); i++ {
        if nums[i] % 2 == 1 {
            ans[count] = nums[i]
            count++
        }
    }

    return ans
}

// 方法二：一趟遍历
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func sortArrayByParity2(nums []int) []int {
    var ans = make([]int, len(nums))

    var left, right = 0, len(nums) - 1
    for i := 0; i < len(nums); i++ {
        if nums[i] % 2 == 0 {
            ans[left] = nums[i]
            left++
        } else {
            ans[right] = nums[i]
            right--
        }
    }

    return ans
}

// 方法三：自定义排序
// 时间复杂度：O(nlogn)
// 空间复杂度：O(n) 或者 O(logn)
func sortArrayByParity3(nums []int) []int {

    sort.Slice(nums, func(i, j int) bool {
        return nums[i] % 2 < nums[j] % 2
    })

    return nums
}

// 方法四：快排分区
// 时间复杂度：O(n)
// 空间复杂度：O(1)
func sortArrayByParity4(nums []int) []int {

    var less, great = 0, 0
    for ; great < len(nums); great++ {
        if nums[less] % 2 > nums[great] % 2 {
            nums[less], nums[great] = nums[great], nums[less]
        }
        if nums[less] % 2 == 0 {
            less++
        }
    }

    return nums
}

// 方法五：快排分区第二种写法
// 时间复杂度：O(n)
// 空间复杂度：O(1)
func sortArrayByParity(nums []int) []int {

    var less, great = 0, len(nums) - 1
    for less < great {
        if nums[less] % 2 > nums[great] % 2 {
            nums[less], nums[great] = nums[great], nums[less]
        }
        if nums[less] % 2 == 0 {
            less++
        }
        if nums[great] % 2 == 1 {
            great--
        }
    }

    return nums
}