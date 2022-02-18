// 贪心策略：每次先安排出现次数最多的任务
func leastInterval(tasks []byte, n int) int {
    var counter = [26]int{}
    // 出现次数最多的任务出现的次数
    var maxCount = 0
    // 出现次数最多的任务数
    var countMax = 0
    for _, task := range tasks {
        counter[task - 'A']++
        // bug 修复：是减去大写的 A
        if counter[task - 'A'] == maxCount {
            countMax++
        } else if counter[task - 'A'] > maxCount {
            maxCount = counter[task - 'A']
            countMax = 1
        }
    }

    var partCount = maxCount - 1
    var partLength = n - (countMax - 1)
    var emptySlots = partCount * partLength
    var availableTasks = len(tasks) - maxCount * countMax
    var idles = emptySlots - availableTasks
    if idles < 0 {
        idles = 0
    }

    return len(tasks) + idles
}