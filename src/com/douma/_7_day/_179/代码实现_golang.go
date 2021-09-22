func largestNumber(nums []int) string {
    var strs = make([] string, len(nums))
    for i := 0; i < len(nums); i++ {
        strs[i] = strconv.Itoa(nums[i])
    }

    sort.Slice(strs, func(i, j int) bool {
        var s1 = strs[i] + strs[j]
        var s2 = strs[j] + strs[i]
        return s1 > s2
    })

    if strs[0] == "0" {
        return "0"
    }

    var res = ""
    for _, str := range strs {
        res += str
    }

    return res
}