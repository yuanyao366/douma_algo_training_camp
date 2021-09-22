func merge(intervals [][]int) [][]int {
    // 按照 end 升序排列
    sort.Slice(intervals, func(i, j int) bool {
        return intervals[i][0] < intervals[j][0]
    })

    var outputs = [][]int{}
    outputs = append(outputs, intervals[0])
    for i := 1; i < len(intervals); i++ {
        var lastInterval = outputs[len(outputs) - 1]
        var currLeft = intervals[i][0]
        var currRight = intervals[i][1]
        if lastInterval[1] < currLeft {
            outputs = append(outputs, intervals[i])
        } else {
            lastInterval[1] = max(lastInterval[1], currRight)
        }
    }
    return outputs
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}