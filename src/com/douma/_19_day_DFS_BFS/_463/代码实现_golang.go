var dirs = [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

// DFS
func islandPerimeter1(grid [][]int) int {
    var rows, cols = len(grid), len(grid[0])
    var visited = make([][]bool, rows)
    for i := range visited {
        visited[i] = make([]bool, cols)
    }
    var res = 0
    // 1. 前序遍历
    var dfs func(int, int)
    dfs = func(row int, col int) {
        if row < 0 || row >= rows ||
            col < 0 || col >= cols ||
            grid[row][col] == 0 || visited[row][col] {
            return
        }

        visited[row][col] = true

        for _, dir := range dirs {
            var nextRow = row + dir[0]
            var nextCol = col + dir[1]
            if nextRow < 0 || nextRow >= rows ||
                nextCol < 0 || nextCol >= cols ||
                grid[nextRow][nextCol] == 0 {
                res += 1
            }
            dfs(nextRow, nextCol)
        }
    }

    for row := 0; row < rows; row++ {
        for col := 0; col < cols; col++ {
            if grid[row][col] == 1 {
                dfs(row, col)
            }
        }
    }

    return res
}


// DFS
func islandPerimeter2(grid [][]int) int {
    var rows, cols = len(grid), len(grid[0])
    var visited = make([][]bool, rows)
    for i := range visited {
        visited[i] = make([]bool, cols)
    }
    // 2. 后序遍历
    var dfs func(int, int) int
    dfs = func(row int, col int) int {
        if row < 0 || row >= rows ||
            col < 0 || col >= cols ||
            grid[row][col] == 0 {
            return 1
        }
        if visited[row][col] {
            return 0
        }

        visited[row][col] = true
        var res = 0
        for _, dir := range dirs {
            var nextRow = row + dir[0]
            var nextCol = col + dir[1]
            res += dfs(nextRow, nextCol)
        }

        return res
    }

    for row := 0; row < rows; row++ {
        for col := 0; col < cols; col++ {
            if grid[row][col] == 1 {
                return dfs(row, col)
            }
        }
    }

    return 0
}

// BFS
func islandPerimeter(grid [][]int) int {
    var rows, cols = len(grid), len(grid[0])
    var visited = make([][]bool, rows)
    for i := range visited {
        visited[i] = make([]bool, cols)
    }

    var bfs func(int, int) int
    bfs = func(row int, col int) int {
        var res = 0
        var queue = list.New()
        queue.PushBack([2]int{row, col})
        visited[row][col] = true

        for queue.Len() > 0 {
            var curr = queue.Remove(queue.Front()).([2]int)
            var row, col = curr[0], curr[1]

            for _, dir := range dirs {
                var nextRow = row + dir[0]
                var nextCol = col + dir[1]
                if nextRow < 0 || nextRow >= rows ||
                        nextCol < 0 || nextCol >= cols ||
                        grid[nextRow][nextCol] == 0 {
                    res += 1
                } else if !visited[nextRow][nextCol] {
                    queue.PushBack([2]int{nextRow, nextCol})
                    visited[nextRow][nextCol] = true
                }
            }
        }
        return res
    }

    for row := 0; row < rows; row++ {
        for col := 0; col < cols; col++ {
            if grid[row][col] == 1 {
                return bfs(row, col)
            }
        }
    }

    return 0
}