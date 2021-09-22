// 暴力解法
// 时间复杂度：O(n^2) ==> 超时
func reversePairs1(nums []int) int {
    if len(nums) < 2 {
        return 0
    }

    count := 0
    for i := 0; i < len(nums); i++ {
        for j := i + 1; j < len(nums); j++ {
            if nums[i] > nums[j] {
                count++
            }
        }
    }

    return count
}


// 归并排序
// 时间复杂度：O(nlogn)
func reversePairs(nums []int) int {
    if len(nums) < 2 {
        return 0
    }
    // 为了不改变原数组，这里先将原数组拷贝一份
    dest := make([]int, len(nums))
    copy(dest, nums)
    return reversePairsR(dest, 0, len(nums) - 1, make([]int, len(nums)))
}

func reversePairsR(nums []int, lo int, hi int, tmp []int) int {
    if lo >= hi {
        return 0
    }
    mid := lo + (hi - lo) / 2

    leftReversePairs := reversePairsR(nums, lo, mid, tmp)
    rightReversePairs := reversePairsR(nums, mid + 1, hi, tmp)

    mergeReversePairs := mergeAndCountReversePairs(nums, lo, mid, hi, tmp)
    return leftReversePairs + rightReversePairs + mergeReversePairs
}

func mergeAndCountReversePairs(nums []int, lo int, mid int, hi int, tmp []int) int {
    for i := lo; i <= hi; i++ {
        tmp[i] = nums[i]
    }

    count := 0
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
            // 计算 temp[j] 的逆序对
            count += mid - i + 1
        }
    }

    return count
}