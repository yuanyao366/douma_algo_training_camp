
import (
    "math/rand"
)

// 归并排序
func sortArray1(nums []int) []int {
    mergeSort(nums, 0, len(nums) - 1, make([]int, len(nums)))
    return nums
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


// 快排
func sortArray2(nums []int) []int {
    quickSort(nums, 0, len(nums) - 1)
    return nums
}

func quickSort(nums []int, lo int, hi int) {
    if lo >= hi {
        return
    }
    index := partition(nums, lo, hi)

    quickSort(nums, lo, index - 1)
    quickSort(nums, index + 1, hi)
}

// 二分切分
func partition(nums []int, lo int, hi int) int {
    i := rand.Intn(hi - lo + 1) + lo
    nums[i], nums[hi] = nums[hi], nums[i]
    pivot := nums[hi]
    less, great := lo, lo
    for ; great <= hi - 1; great++ {
        if nums[great] < pivot {
            nums[less], nums[great] = nums[great], nums[less]
            less++
        }
    }
    nums[less], nums[hi] = nums[hi], nums[less]
    return less
}

// 快排
func sortArray(nums []int) []int {
    quickSort1(nums, 0, len(nums) - 1)
    return nums
}

// 三路快排
func quickSort1(nums []int, lo int, hi int) {
    if lo >= hi {
        return
    }

    i := rand.Intn(hi - lo + 1) + lo
    nums[i], nums[hi] = nums[hi], nums[i]
    pivot := nums[hi]
    less, great, i := lo, hi, lo

    for i <= great {
        if nums[i] < pivot {
            nums[i], nums[less] = nums[less], nums[i]
            less++
            i++
        } else if nums[i] > pivot {
            nums[i], nums[great] = nums[great], nums[i]
            great--
        } else {
            i++
        }
    }

    quickSort1(nums, lo, less - 1)
    quickSort1(nums, great + 1, hi)
}