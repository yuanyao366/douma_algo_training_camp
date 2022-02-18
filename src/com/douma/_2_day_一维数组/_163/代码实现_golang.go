func findMissingRanges(nums []int, lower int, upper int) []string {
    res := make([]string, 0)
    pre := lower - 1
    for i := 0; i < len(nums); i++ {
        if nums[i] == pre + 2 {
            res = append(res, fmt.Sprintf("%d", pre + 1))
        } else if nums[i] > pre + 2 {
            res = append(res, fmt.Sprintf("%d->%d", pre + 1, nums[i] - 1))
        }
        pre = nums[i]
    }

    if upper == pre + 1 {
        res = append(res, fmt.Sprintf("%d", pre + 1))
    } else if upper > pre + 1 {
        res = append(res, fmt.Sprintf("%d->%d", pre + 1, upper))
    }

    return res
}