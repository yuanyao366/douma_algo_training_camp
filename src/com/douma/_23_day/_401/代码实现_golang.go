func readBinaryWatch(turnedOn int) []string {
    var nums1 = []int{8, 4, 2, 1}
    var nums2 = []int{32, 16, 8, 4, 2, 1}
    var res = make([]string, 0)

    // bug 修复：需要等于，不然会少一种组合，总共是[0，turnedOn]种
    for i := 0; i <= turnedOn; i++ {
        // 拿到 i 个小时的组合
        var hours = findCombinations(nums1, i)
        // 拿到 turnedOn - i 个分钟的组合
        var minutes = findCombinations(nums2, turnedOn - i)
        for _, hour := range hours {
            if hour > 11 {
                continue
            }
            for _, minute := range minutes {
                if minute > 59 {
                    continue
                }
                var minuteStr = strconv.Itoa(minute)
                if minute < 10 {
                    minuteStr = "0" + strconv.Itoa(minute)
                }
                res = append(res, strconv.Itoa(hour) + ":" + minuteStr)
            }
        }
    }

    return res
}

func findCombinations(nums []int, count int) []int {
    var res = make([]int, 0)

    var dfs func(int, int, int)
    dfs = func(count int, sum int, start int) {
        if count == 0 {
            res = append(res, sum)
            return
        }

        for i := start; i < len(nums); i++ {
            dfs(count - 1, sum + nums[i], i + 1)
        }
    }

    dfs(count, 0, 0)
    return res
}