// 暴力解法
// 时间复杂度：O(n^3) => 超时
// 空间复杂度：O(1)
func countRangeSum1(nums []int, lower int, upper int) int {
    var count = 0
    for i := 0; i < len(nums); i++ {
        for j := i; j < len(nums); j++ {
            var sum = 0
            for k := i; k <= j; k++ {
                sum += nums[k]
            }
            if sum <= upper && sum >= lower {
                count++
            }
        }
    }
    return count
}

// 前缀和辅助解法
// 时间复杂度：O(n^2) => 超时
// 空间复杂度：O(n)
func countRangeSum2(nums []int, lower int, upper int) int {
    var prefixSum = make([]int, len(nums) + 1)
    prefixSum[0] = 0
    for i := 0; i < len(nums); i++ {
        prefixSum[i + 1] = prefixSum[i] + nums[i]
    }

    var count = 0
    for i := 0; i < len(prefixSum); i++ {
        for j := i + 1; j < len(prefixSum); j++ {
            var sum = prefixSum[j] - prefixSum[i]
            if sum <= upper && sum >= lower {
                count++
            }
        }
    }
    return count
}

// 归并排序
// 时间复杂度：O(nlogn)
// 空间复杂度：O(n)
func countRangeSum(nums []int, lower int, upper int) int {
    var prefixSum = make([]int, len(nums) + 1)
    prefixSum[0] = 0
    for i := 0; i < len(nums); i++ {
        prefixSum[i + 1] = prefixSum[i] + nums[i]
    }

    return mergeSort(prefixSum, 0, len(prefixSum) - 1, make([]int, len(prefixSum)), lower, upper)
}

func mergeSort(prefixSum []int, lo int, hi int, tmp []int, lower int, upper int) int {
    if lo >= hi {
        return 0
    }
    mid := lo + (hi - lo) / 2

    var leftSumCount = mergeSort(prefixSum, lo, mid, tmp, lower, upper)
    var rightSumCount = mergeSort(prefixSum, mid + 1, hi, tmp, lower, upper)

    var count = 0
    // 计算当前有效的区间和个数
    i, l, r := lo, mid + 1, mid + 1
    for i <= mid {
        for l <= hi && prefixSum[l] - prefixSum[i] < lower {
            l++
        }
        for r <= hi && prefixSum[r] - prefixSum[i] <= upper {
            r++
        }
        count += (r - l)
        i++
    }

    merge(prefixSum, lo, mid, hi, tmp)
    return leftSumCount + rightSumCount + count
}

func merge(nums []int, lo int, mid int, hi int, tmp []int) {
    for i := lo; i <= hi; i++ {
        tmp[i] = nums[i]
    }

    i, j := lo, mid + 1
    for k := lo; k <= hi; k++ {
        if i == mid + 1{
            nums[k] = tmp[j]
            j++
        } else if j == hi + 1 {
            nums[k] = tmp[i]
            i++
        } else if tmp[i] <= tmp[j] {
            nums[k] = tmp[i]
            i++
        } else {
            nums[k] = tmp[j]
            j++
        }
    }
}