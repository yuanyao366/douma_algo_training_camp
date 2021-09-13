// 方法 1 ：使用辅助数组
func setZeroes1(matrix [][]int)  {
    m, n := len(matrix), len(matrix[0])

    // 记录每行或者每列是否包含 0
    rows, cols := make([]bool, m), make([]bool, n)

    for row := 0; row < m; row++ {
        for col := 0; col < n; col++ {
            if matrix[row][col] == 0 {
                rows[row] = true
                cols[col] = true
            }
        }
    }

    for row := 0; row < m; row++ {
        for col := 0; col < n; col++ {
            if rows[row] || cols[col] {
                matrix[row][col] = 0
            }
        }
    }
}

// 方法 2 ：使用两个辅助变量
func setZeroes2(matrix [][]int)  {
    m, n := len(matrix), len(matrix[0])

    // 先计算第一行第一列是否包含 0
    flagRow1 := false
    for i := 0; i < n; i++ {
        if matrix[0][i] == 0 {
            flagRow1 = true
        }
    }
    flagCol1 := false
    for i := 0; i < m; i++ {
        if matrix[i][0] == 0 {
            flagCol1 = true
        }
    }

    // 使用 matrix 的第一行和第一列来记录每行每列是否包含 0
    for row := 1; row < m; row++ {
        for col := 1; col < n; col++ {
            if matrix[row][col] == 0 {
                matrix[0][col] = 0
                matrix[row][0] = 0
            }
        }
    }
    for row := 1; row < m; row++ {
        for col := 1; col < n; col++ {
            if matrix[0][col] == 0 || matrix[row][0] == 0 {
                matrix[row][col] = 0
            }
        }
    }
    if flagRow1 {
        for i := 0; i < n; i++ {
            matrix[0][i] = 0
        }
    }

    if flagCol1 {
        for i := 0; i < m; i++ {
            matrix[i][0] = 0
        }
    }
}

// 方法 3 ：使用一个辅助变量
func setZeroes(matrix [][]int)  {
    m, n := len(matrix), len(matrix[0])

    // 先计算第一列是否包含 0
    flagCol1 := false

    // 使用 matrix 的第一行和第一列来记录每行每列是否包含 0
    for row := 0; row < m; row++ {
        // 计算第一列是否包含 0
        if matrix[row][0] == 0 {
            flagCol1 = true
        }
        for col := 1; col < n; col++ {
            if matrix[row][col] == 0 {
                matrix[0][col] = 0
                matrix[row][0] = 0
            }
        }
    }
    // bug 修复：row--
    for row := m - 1; row >= 0; row-- {
        for col := 1; col < n; col++ {
            if matrix[0][col] == 0 || matrix[row][0] == 0 {
                matrix[row][col] = 0
            }
        }
        if (flagCol1) {
            matrix[row][0] = 0
        }
    }
}

