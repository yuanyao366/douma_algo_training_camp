// 方法 1 ：直接模拟
func spiralOrder1(matrix [][]int) []int {
    dirs := [][]int{[]int{0, 1}, []int{1, 0}, []int{0, -1}, []int{-1, 0}}

    m, n := len(matrix), len(matrix[0])
    row, col, di := 0, 0, 0
    seen := make([][]bool, m)
    for i := range seen {
        seen[i] = make([]bool, n)
    }
    res := make([]int, 0)

    for i := 0; i < m * n; i++ {
        res = append(res, matrix[row][col])
        seen[row][col] = true

        nextRow, nextCol := row + dirs[di][0], col + dirs[di][1]

        if nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n || seen[nextRow][nextCol] {
            // 改变方向
            di = (di + 1) % 4
        }

        row, col = row + dirs[di][0], col + dirs[di][1]
    }

    return res
}

// 方法 2 ：按层模拟
func spiralOrder(matrix [][]int) []int {
    res := make([]int, 0)
    startRow, endRow := 0, len(matrix) - 1
    startCol, endCol := 0, len(matrix[0]) - 1

    for startRow <= endRow && startCol <= endCol {
        // top 行
        for col := startCol; col <= endCol; col++ {
            res = append(res, matrix[startRow][col])
        }
        // right 列
        for row := startRow + 1; row <= endRow; row++ {
            res = append(res, matrix[row][endCol])
        }
        if startRow < endRow && startCol < endCol {
            // bottom 行
            for col := endCol - 1; col > startCol; col-- {
                res = append(res, matrix[endRow][col])
            }
            // left 列
            for row := endRow; row > startRow; row-- {
                res = append(res, matrix[row][startCol])
            }
        }
        startRow++
        endRow--
        startCol++
        endCol--
    }

    return res
}