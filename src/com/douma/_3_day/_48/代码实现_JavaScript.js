var rotate = function(matrix) {
    const n = matrix.length
    for (let row = 0; row < Math.floor(n / 2); row++) {
        for (let col = 0; col < Math.floor((n + 1) / 2); col++) {
            temp = matrix[row][col]
            matrix[row][col] = matrix[n - col - 1][row]
            matrix[n - col - 1][row] = matrix[n - row - 1][n - col - 1]
            matrix[n - row - 1][n - col - 1] = matrix[col][n - row - 1]
            matrix[col][n - row - 1] = temp
        }
    }
};