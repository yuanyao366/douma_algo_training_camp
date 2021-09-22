
var indexes, tmpIndexes, ans []int

// 基于归并排序实现
// 时间复杂度：O(nlogn)
func countSmaller(nums []int) []int {
    n := len(nums)
    // 记录原始数组每个元素的索引信息，方便在合并的时候知道是计算哪一个元素
    indexes = make([]int, n)
    for i := 0; i < n; i++ {
        indexes[i] = i
    }

    tmpIndexes = make([]int, n)
    ans = make([]int, n)
    mergeSort(nums, 0, n - 1, make([]int, n))

    return ans
}

func mergeSort(nums []int, lo int, hi int, tmp []int) {
    if lo >= hi {
        return
    }
    mid := lo + (hi - lo) / 2

    mergeSort(nums, lo, mid, tmp)
    mergeSort(nums, mid + 1, hi, tmp)

    merge(nums, lo, mid, hi, tmp)
}

func merge(nums []int, lo int, mid int, hi int, tmp []int) {
    for i := lo; i <= hi; i++ {
        tmp[i] = nums[i]
        tmpIndexes[i] = indexes[i]
    }

    i, j := lo, mid + 1
    for k := lo; k <= hi; k++ {
        if i == mid + 1{
            nums[k] = tmp[j]
            indexes[k] = tmpIndexes[j]
            j++
        } else if j == hi + 1 {
            nums[k] = tmp[i]
            indexes[k] = tmpIndexes[i]
            // 计算比当前元素小的后面元素的个数
            ans[tmpIndexes[i]] += (j - mid - 1)
            i++
        } else if tmp[i] <= tmp[j] {
            nums[k] = tmp[i]
            indexes[k] = tmpIndexes[i]
            // 计算比当前元素小的后面元素的个数
            ans[tmpIndexes[i]] += (j - mid - 1)
            i++
        } else {
            nums[k] = tmp[j]
            indexes[k] = tmpIndexes[j]
            j++
        }
    }
}