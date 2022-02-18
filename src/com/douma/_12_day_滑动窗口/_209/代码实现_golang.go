// 滑动窗口
// 时间复杂度：O(n)
// 空间复杂度：O(1)
func minSubArrayLen1(target int, nums []int) int {
    var ans = math.MaxInt32
    var left, right, windowSum = 0, 0, 0
    for right < len(nums) {
        windowSum += nums[right]
        for windowSum >= target {
            if right - left + 1 < ans {
                ans = right - left + 1
            }
            windowSum -= nums[left]
            left++
        }
        right++
    }
    if ans == math.MaxInt32 {
        return 0
    } else {
        return ans
    }
}

// 前缀和
// 时间复杂度：O(nlogn)
func minSubArrayLen(target int, nums []int) int {
    var prefixSum = make([]int, len(nums) + 1)
    prefixSum[0] = 0
    for i := 1; i <= len(nums); i++ {
        prefixSum[i] = prefixSum[i - 1] + nums[i - 1]
    }

    var ans = math.MaxInt32
    // 这里需要注意的是 i 是前缀和数组的索引，而非原始数组的索引
    /*
    那么 i 为什么从 1 开始呢？
    假设 i 从 0 开始，那么 prefixSum[0] 表示是原数组前 0 个元素之和，这个没什么意义，因为都不构成区间
    */
    for i := 1; i < len(prefixSum); i++ {
        // 说明：这里为什么是 i - 1 呢？
        // 首先，prefixSum[i] 表达的是原数组中前 i 个元素之和
        // 那么，原始数组中区间 [i, j) 的区间和等于：prefixSum[j] - prefixSum[i - 1]
        // 因为 [i, j) 的区间和需要包含 i 对应的元素，所以，减掉的是 i 前面的 prefixSum
        // 实际上我们要找的就是：prefixSum[j] - prefixSum[i - 1] >= target

        // 为什么 [i, j) 是右开的呢？
        // 因为 prefixSum[j] 表示的是原始数组前 j 个元素的和，前 j 个元素中不会包括索引为 j 的元素

        /*
        我们以下面的数组为例：
                    nums = [2,3,1,4, 4, 3]
            prefixSum = [0,2,5,6,10,14,17]
        i 是 从前缀和数组的第二个元素开始，即 i = 1
        这个时候比如 j = 3，
        那么 prefixSum[j] - prefixSum[i] = 4，这个是原始数组区间 [1, 2] 的区间和，即 [i, j) 的区间和
        prefixSum[j] - prefixSum[i - 1] = 6，这个是原始数组区间 [0, 2] 的区间和，即 [i - 1, j) 的区间和
        如果你是 prefixSum[j] - prefixSum[i] 的话，就会漏掉了第一个元素，所以这里必须是 i - 1
            */
        var t = target + prefixSum[i - 1]
        var j = firstGETargetElement(prefixSum, t)
        if j < 0 {
            continue
        }
        if j <= len(nums) && j - i + 1 < ans {
            ans = j - i + 1
        }
    }
    if ans == math.MaxInt32 {
        return 0
    } else {
        return ans
    }
}

func firstGETargetElement(nums []int, target int) int {
    var left, right = 0, len(nums) - 1
    for left <= right {
        var mid = left + (right - left) / 2
        if nums[mid] >= target {
            // 符合下面的两个条件之一就返回 mid ：
            // 1. mid 是数组的第一个元素
            // 2. mid 前面的那个元素小于 target
            if mid == 0 || nums[mid - 1] < target {
                return mid
            } else {
                right = mid - 1
            }
        } else {
            left = mid + 1
        }
    }
    return -1
}