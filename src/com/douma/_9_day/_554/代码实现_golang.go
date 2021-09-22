func leastBricks(wall [][]int) int {
    var edgeFreq, maxFreq = make(map[int]int), 0

    for row := 0; row < len(wall); row++ {
        var edgePosition = 0
        for col := 0; col < len(wall[row]) - 1; col++ {
            var currBrickLength = wall[row][col]
            edgePosition += currBrickLength
            if _, ok := edgeFreq[edgePosition]; ok {
                edgeFreq[edgePosition] += 1
            } else {
                edgeFreq[edgePosition] = 1
            }
            if edgeFreq[edgePosition] > maxFreq {
                maxFreq = edgeFreq[edgePosition]
            }
        }
    }
    return len(wall) - maxFreq
}