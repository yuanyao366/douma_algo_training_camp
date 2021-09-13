func generate(numRows int) [][]int {
    rows := make([][]int, 0)
    for row := 0; row < numRows; row++ {
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
    return rows
}