func insert(intervals [][]int, newInterval []int) [][]int {
    var res = [][]int{}
    // i 用于遍历所有的区间
    var i = 0

    // 1. 将区间结束小于新区间开始的区间放入到结果集
    for i < len(intervals) && intervals[i][1] < newInterval[0] {
        res = append(res, intervals[i])
        i++
    }

    // 2. 将区间开始小于等于新区间结束的区间和新区间合并
    for i < len(intervals) && intervals[i][0] <= newInterval[1] {
        if intervals[i][0] < newInterval[0] {
            newInterval[0] = intervals[i][0]
        }
        if intervals[i][1] > newInterval[1] {
            newInterval[1] = intervals[i][1]
        }
        i++
    }

    // 3. 将合并后的区间加入道结果集
    res = append(res,  newInterval)

    // 4. 将剩余的区间放入到结果集
    for i < len(intervals) {
        res = append(res, intervals[i])
        i++
    }

    return res
}