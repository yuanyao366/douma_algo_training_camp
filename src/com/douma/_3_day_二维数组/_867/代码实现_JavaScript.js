var transpose = function(matrix) {
    const m = matrix.length
    const n = matrix[0].length
    const t = []

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (!t[j]) t[j] = []
            t[j][i] = matrix[i][j]
        }
    }

    return t
};