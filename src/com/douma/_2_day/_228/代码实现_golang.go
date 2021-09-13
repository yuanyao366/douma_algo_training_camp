func summaryRanges(nums []int) []string {
    res, i := make([]string, 0), 0
    for i < len(nums) {
        low := i
        i++
        // 找到非连续的点
        for i < len(nums) && nums[i] - nums[i - 1] == 1 {
            i++
        }

        high := i - 1
        s := strconv.Itoa(nums[low])
        if low < high {
            s += "->" + strconv.Itoa(nums[high])
        }
        res = append(res, s)
    }
    return res
}