func maxNumber(nums1 []int, nums2 []int, k int) []int {
    var maxSubSeq = make([]int, k)
    var m, n = len(nums1), len(nums2)
    var start, end = max(0, k - n), min(m, k)
    for i := start; i <= end; i++ {
        var subSeq1 = maxSubSequence(nums1, i)
        var subSeq2 = maxSubSequence(nums2, k - i)
        var currMaxSubSeq = merge(subSeq1, subSeq2)
        if compare(currMaxSubSeq, 0, maxSubSeq, 0) > 0 {
            maxSubSeq = currMaxSubSeq
        }
    }
    return maxSubSeq
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}

func min(a, b int) int {
    if a < b {
        return a
    }
    return b
}

func maxSubSequence(nums []int, k int) []int {
    var remain, stack, top = len(nums) - k, make([]int, k), -1
    for i := range nums {
        var num = nums[i]
        for top >= 0 && num > stack[top] && remain > 0 {
            top--
            remain--
        }
        if top < k - 1 {
            top++
            stack[top] = num
        } else {
            remain--
        }
    }
    return stack
}

func maxSubSequence1(nums []int, k int) []int {
    var remain, stack = len(nums) - k, []int{}
    for i := range nums {
        var num = nums[i]
        for len(stack) > 0 && num > stack[len(stack) - 1] && remain > 0 {
            stack = stack[:len(stack) - 1]
            remain--
        }
        if len(stack) < k {
            stack = append(stack, num)
        } else {
            remain--
        }
    }
    return stack[len(stack) - k:len(stack)]
}

func merge(subSeq1 []int, subSeq2 []int) []int {
    var x, y = len(subSeq1), len(subSeq2)
    if x == 0 {
        return subSeq2
    }
    if y == 0 {
        return subSeq1
    }
    var mergeLen = x + y
    var merged = make([]int, mergeLen)
    var index1, index2 = 0, 0
    for i := 0; i < mergeLen; i++ {
        if compare(subSeq1, index1, subSeq2, index2) > 0 {
            merged[i] = subSeq1[index1]
            index1++
        } else {
            merged[i] = subSeq2[index2]
            index2++
        }
    }
    return merged
}


func compare(subSeq1 []int, index1 int, subSeq2 []int, index2 int) int {
    var x, y = len(subSeq1), len(subSeq2)
    for index1 < x && index2 < y {
        var diff = subSeq1[index1] - subSeq2[index2]
        if diff != 0 {
            return diff
        }
        index1++
        index2++
    }
    return (x - index1) - (y - index2)
}