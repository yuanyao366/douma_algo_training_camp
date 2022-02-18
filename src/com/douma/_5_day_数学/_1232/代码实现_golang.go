func checkStraightLine(coordinates [][]int) bool {
    x0, y0 := coordinates[0][0], coordinates[0][1]
    deltaY := coordinates[1][1] - y0
    deltaX := coordinates[1][0] - x0
    for i := 2; i < len(coordinates); i++ {
        deltaYi := coordinates[i][1] - y0
        deltaXi := coordinates[i][0] - x0
        if deltaY * deltaXi != deltaX * deltaYi {
            return false
        }
    }
    return true
}