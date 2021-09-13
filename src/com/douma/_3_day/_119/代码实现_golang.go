
// 空间复杂度：O(k^2)
func getRow1(rowIndex int) []int {
    rows := make([][]int, 0)
    for row := 0; row <= rowIndex; row++ {
        oneRow := make([]int, 0)
        for col := 0; col <= row; col++ {
            if col == 0 || col == row {
                oneRow = append(oneRow, 1)
            } else {
                preRow := rows[row - 1]
                oneRow = append(oneRow, preRow[col - 1] + preRow[col])
            }
        }
        rows = append(rows, oneRow)
    }
    return rows[rowIndex]
}

// 空间复杂度：O(k^2)，减少空间使用
func getRow2(rowIndex int) []int {
    preRow := make([]int, 0)
    for row := 0; row <= rowIndex; row++ {
        oneRow := make([]int, 0)
        for col := 0; col <= row; col++ {
            if col == 0 || col == row {
                oneRow = append(oneRow, 1)
            } else {
                oneRow = append(oneRow, preRow[col - 1] + preRow[col])
            }
        }
        preRow = oneRow
    }
    return preRow
}

// 方法三：优化成只用一个数组 O(k)
func getRow(rowIndex int) []int {
    oneRow := make([]int, 0)
    oneRow = append(oneRow, 1)
    for row := 1; row <= rowIndex; row++ {
        oneRow = append(oneRow, 0)
        for col := row; col > 0; col-- {
            oneRow[col] = oneRow[col - 1] + oneRow[col]
        }
    }
    return oneRow
}