var dirs = [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

func colorBorder(grid [][]int, row int, col int, color int) [][]int {
    var currColor = grid[row][col]
    if currColor == color {
        return grid
    }
    var rows, cols = len(grid), len(grid[0])
    var visited = make([][]bool, rows)
    for i := range visited {
        visited[i] = make([]bool, cols)
    }

    var inArea func(int, int) bool
    inArea = func(row int, col int) bool {
        return row >= 0 && row < rows && col >= 0 && col < cols
    }

    var dfs func(int, int)
    dfs = func(row int, col int) {

        visited[row][col] = true

        for _, dir := range dirs {
            var nextRow = row + dir[0]
            var nextCol = col + dir[1]
            if !inArea(nextRow, nextCol) ||
                    (grid[nextRow][nextCol] != currColor && !visited[nextRow][nextCol]) {
                grid[row][col] = color
            } else if !visited[nextRow][nextCol] {
                dfs(nextRow, nextCol)
            }
        }
    }

    dfs(row, col)

    return grid
}