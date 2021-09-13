func isValidSudoku(board [][]byte) bool {
     rowUsed, colUsed, boxUsed := [9][9]bool{}, [9][9]bool{}, [9][9]bool{}

     for row := 0; row < len(board); row++ {
         for col := 0; col < len(board[0]); col++ {
             if board[row][col] != byte('.') {
                 num := board[row][col] - byte('1')

                 if rowUsed[row][num] {
                     return false
                 } else {
                     rowUsed[row][num] = true
                 }

                 if colUsed[col][num] {
                     return false
                 } else {
                     colUsed[col][num] = true
                 }

                 boxIndex := row / 3 + (col / 3) * 3
                 if boxUsed[boxIndex][num] {
                     return false
                 } else {
                     boxUsed[boxIndex][num] = true
                 }
             }
         }
     }
     return true
}