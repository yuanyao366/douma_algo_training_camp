var dirs = [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}}

func updateBoard(board [][]byte, click []int) [][]byte {
    var rows, cols = len(board), len(board[0])
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
        if !inArea(row, col) || board[row][col] != 'E' || visited[row][col] {
            return
        }
        visited[row][col] = true

        var mines = 0
        for _, dir := range dirs {
            var nextRow = row + dir[0]
            var nextCol = col + dir[1]
            if inArea(nextRow, nextCol) && board[nextRow][nextCol] == 'M' {
                mines++
            }
        }

        if mines > 0 {
            board[row][col] = byte(mines + '0')
        } else {
            board[row][col] = 'B'
            for _, dir := range dirs {
                var nextRow = row + dir[0]
                var nextCol = col + dir[1]
                dfs(nextRow, nextCol);
            }
        }
    }

    if board[click[0]][click[1]] == 'M' {
        board[click[0]][click[1]] = 'X'
    } else {
        dfs(click[0], click[1])
    }

    return board
}