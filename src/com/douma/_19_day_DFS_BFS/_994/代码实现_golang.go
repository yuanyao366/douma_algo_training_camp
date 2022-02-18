
var dirs = [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

func orangesRotting(grid [][]int) int {
    var rows, cols = len(grid), len(grid[0])

    // 优化：现在使用二维数组的行索引 + 列索引转换成一维数组中的索引作为 Map 的 key
    var levelsMap = make(map[int]int)
    var queue = list.New()
    for row := 0; row < rows; row++ {
        for col := 0; col < cols; col++ {
            if grid[row][col] == 2 {
                var index = row * cols + col
                queue.PushBack(index)
                levelsMap[index] = 0
            }
        }
    }

    var ans = 0
    for queue.Len() > 0 {
        var currIndex = queue.Remove(queue.Front()).(int)
        var row, col = currIndex / cols, currIndex % cols
        for _, dir := range dirs {
            var nextRow = row + dir[0]
            var nextCol = col + dir[1]

            if nextRow >= 0 && nextRow < rows &&
                     nextCol >= 0 && nextCol < cols &&
                    grid[nextRow][nextCol] == 1 {
                grid[nextRow][nextCol] = 2
                var index = nextRow * cols + nextCol
                queue.PushBack(index)

                // 更新所属层信息
                var level = levelsMap[currIndex] + 1
                levelsMap[index] = level

                ans = level
            }
        }
    }

    for row := 0; row < rows; row++ {
        for col := 0; col < cols; col++ {
            if grid[row][col] == 1 {
                return -1
            }
        }
    }

    return ans
}