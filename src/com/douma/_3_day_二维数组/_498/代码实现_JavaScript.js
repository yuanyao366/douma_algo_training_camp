var findDiagonalOrder = function(matrix) {
    const m = matrix.length
    if (m == 0) return []
    const n = matrix[0].length

    const result = new Array(m * n).fill(0)
    const dirs = [[-1, 1], [1, -1]]
    let row = 0, col = 0, d = 0

    for (let i = 0; i < m * n; i++) {
        result[i] = matrix[row][col]
        row += dirs[d][0]
        col += dirs[d][1]

        if (row >= m) {row = m - 1; col += 2; d = 1 - d;}
        if (col >= n) {col = n - 1; row += 2; d = 1 - d;}
        if (row < 0) {row = 0; d = 1 - d;}
        if (col < 0) {col = 0; d = 1 - d;}
    }

    return result
};