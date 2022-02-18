var setZeroes = function(matrix) {
    const m = matrix.length, n = matrix[0].length
    let flagCol1 = false

    for (let row = 0; row < m; row++) {
        if (matrix[row][0] == 0) flagCol1 = true
        for (let col = 1; col < n; col++) {
            if (matrix[row][col] == 0) {
                matrix[row][0] = 0
                matrix[0][col] = 0
            }
        }
    }

    for (let row = m - 1; row >= 0; row--) {
        for (let col = 1; col < n; col++) {
            if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                matrix[row][col] = 0
            }
        }
        if (flagCol1) matrix[row][0] = 0
    }
};