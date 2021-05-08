var merge = function(intervals) {
    if (intervals.length == 0) return []
    
    intervals.sort((a, b) => a[0] - b[0])

    let outputs = []
    outputs.push(intervals[0])
    for (let i = 1; i < intervals.length; i++) {
        const lastInterval = outputs[outputs.length - 1];
        const currLeft = intervals[i][0]
        const currRight = intervals[i][1]
        if (lastInterval[1] < currLeft) {
            outputs.push(intervals[i])
        } else {
            lastInterval[1] = Math.max(lastInterval[1], currRight)
        }
    }
    return outputs
};