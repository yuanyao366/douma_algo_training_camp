
// 1. 使用辅助数组
func rotate1(matrix [][]int)  {
    n := len(matrix)
    newMatrix := make([][]int, n)
    for i := range newMatrix {
        newMatrix[i] = make([]int, n)
    }

    for row := 0; row < n; row++ {
        for col := 0; col < n; col++ {
            newMatrix[col][n - row - 1] = matrix[row][col]
        }
    }

    copy(matrix, newMatrix)
}


// 2. 原地旋转
func rotate2(matrix [][]int)  {
    n := len(matrix)

    for row := 0; row < n / 2; row++ {
        for col := 0; col < (n + 1) / 2; col++ {
            tmp := matrix[row][col]
            matrix[row][col] = matrix[n - col - 1][row]
            matrix[n - col - 1][row] = matrix[n - row - 1][n - col - 1]
            matrix[n - row - 1][n - col - 1] = matrix[col][n - row - 1]
            matrix[col][n - row - 1] = tmp
        }
    }
}

// 2. 原地翻转
func rotate(matrix [][]int)  {
    n := len(matrix)

    // 水平翻转
    for row := 0; row < n / 2; row++ {
        for col := 0; col < n; col++ {
            matrix[row][col], matrix[n - row - 1][col] = matrix[n - row - 1][col], matrix[row][col]
        }
    }
    // 主对角线翻转
    for row := 0; row < n; row++ {
        // bug 修复：到 row 为止即可，要不然就翻转回来了
        for col := 0; col < row; col++ {
            matrix[row][col], matrix[col][row] = matrix[col][row], matrix[row][col]
        }
    }
}