func exist(board [][]byte, word string) bool {
    var dirs = [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}
    var m, n = len(board), len(board[0])
    var visited = make([][]bool, m)
    for i := range visited {
        visited[i] = make([]bool, n)
    }

    var dfs func(int, int, int) bool
    dfs = func(row int, col int, index int) bool {
        if board[row][col] != word[index] {
            return false
        } else if index == len(word) - 1 {
            return true
        }

        visited[row][col] = true
        for _, dir := range dirs {
            var nextRow = row + dir[0]
            var nextCol = col + dir[1]
            if nextRow >= 0 && nextRow < m &&
                    nextCol >= 0 && nextCol < n &&
                    !visited[nextRow][nextCol] {
                if dfs(nextRow, nextCol, index + 1) {
                    return true
                }
            }
        }
        visited[row][col] = false
        return false
    }

    for row := 0; row < m; row++ {
        for col := 0; col < n; col++ {
            if board[row][col] == word[0] {
                if dfs(row, col, 0) {
                    return true
                }
            }
        }
    }

    return false
}