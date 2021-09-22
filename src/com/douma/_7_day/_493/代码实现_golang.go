// 归并排序
func reversePairs(nums []int) int {
    if len(nums) < 2 {
        return 0
    }
    return mergeSort(nums, 0, len(nums) - 1, make([]int, len(nums)))
}

func mergeSort(nums []int, lo int, hi int, tmp []int) int {
    if lo >= hi {
        return 0
    }
    mid := lo + (hi - lo) / 2

    var leftSumCount = mergeSort(nums, lo, mid, tmp)
    var rightSumCount = mergeSort(nums, mid + 1, hi, tmp)

    var count = 0
    // 计算当前翻转对的个数
    i, j := lo, mid + 1
    for i <= mid {
        for j <= hi && nums[i] > 2 * nums[j] {
            j++
        }
        count += (j - mid - 1)
        i++
    }

    merge(nums, lo, mid, hi, tmp)
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