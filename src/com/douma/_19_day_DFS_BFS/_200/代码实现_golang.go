var dirs = [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

func numIslands(grid [][]byte) int {
    var rows, cols = len(grid), len(grid[0])
    var visited = make([][]bool, rows)
    for i := range visited {
        visited[i] = make([]bool, cols)
    }

    var dfs func(int, int)
    dfs = func(row int, col int) {
        if row < 0 || row >= rows ||
            col < 0 || col >= cols ||
            grid[row][col] == '0' || visited[row][col] {
            return
        }

        visited[row][col] = true

        for _, dir := range dirs {
            var nextRow = row + dir[0]
            var nextCol = col + dir[1]
            dfs(nextRow, nextCol)
        }
    }

    var res = 0
    for row := 0; row < rows; row++ {
        for col := 0; col < cols; col++ {
            if grid[row][col] == '1' && !visited[row][col] {
                dfs(row, col)
                res++
            }
        }
    }

    return res
}