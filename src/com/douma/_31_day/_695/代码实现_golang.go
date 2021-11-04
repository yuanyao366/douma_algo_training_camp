var dirs = [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

func maxAreaOfIsland(grid [][]int) int {
    var rows, cols = len(grid), len(grid[0])
    var visited = make([][]bool, rows)
    for i := range visited {
        visited[i] = make([]bool, cols)
    }

    var dfs func(int, int) int
    dfs = func(row int, col int) int {
        if row < 0 || row >= rows ||
            col < 0 || col >= cols ||
            grid[row][col] == 0 || visited[row][col] {
            return 0
        }

        // bug 修复：需要设置当前顶点已经被访问
        visited[row][col] = true

        var res = 0
        for _, dir := range dirs {
            var nextRow = row + dir[0]
            var nextCol = col + dir[1]
            res += dfs(nextRow, nextCol)
        }

        return 1 + res
    }

    var res = 0
    for row := 0; row < rows; row++ {
        for col := 0; col < cols; col++ {
            if grid[row][col] == 1 && !visited[row][col] {
                var area = dfs(row, col)
                if area > res {
                    res = area
                }
            }
        }
    }

    return res
}