func addTwoNum(nums1 []int, nums2 int[]) []int {
    res := make([]int, 0)
    carry := 0
    l1, l2 := len(nums1) - 1, len(nums2) - 1
    for l1 >= 0 || l2 >= 0 {
        x, y := 0, 0
        if l1 >= 0 {
            x = nums1[l1]
        }
        if l2 >= 0 {
            y = nums2[l2]
        }

        sum := x + y + carry
        res = append(res, sum % 10)
        carry = sum / 10

        l1--
        l2--
    }
    if carry != 0 {
        res = append(res, carry)
    }

    // 反转 res
    left, right := 0, len(res) - 1
    for left < right {
        res[left], res[right] = res[right], res[left]
        left++
        right--
    }

    return res
}