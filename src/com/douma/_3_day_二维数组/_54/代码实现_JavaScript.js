var spiralOrder1 = function(matrix) {
    const m = matrix.length, n = matrix[0].length

    const dirs = [[0, 1], [1, 0], [0, -1], [-1, 0]]
    let row = 0, col = 0, di = 0

    const res = new Array(m * n).fill(0)
    const seen = new Array(m).fill(0).map(() => new Array(n).fill(false))

    for (let i = 0; i < m * n; i++) {
        res[i] = matrix[row][col]
        seen[row][col] = true

        const nextRow = row + dirs[di][0]
        const nextCol = col + dirs[di][1]

        if (nextRow < 0 || nextRow >= m
                || nextCol < 0 || nextCol >= n
                || seen[nextRow][nextCol]) {
            di = (di + 1) % 4
        }

        row = row + dirs[di][0]
        col = col + dirs[di][1]
    }

    return res
};

// 方法二：按层模拟
var spiralOrder = function(matrix) {
    let startRow = 0, endRow = matrix.length - 1
    let starCol = 0, endCol = matrix[0].length - 1

    const res = []

    while (startRow <= endRow && starCol <= endCol) {
        // top 行
        for (let col = starCol; col <= endCol; col++) res.push(matrix[startRow][col])
        // right 列
        for (let row = startRow + 1; row <= endRow; row++) res.push(matrix[row][endCol])
        if (startRow < endRow && starCol < endCol) {
            // bottom 行
            for (let col = endCol - 1; col > starCol; col--) res.push(matrix[endRow][col])
            // left 列
            for (let row = endRow; row > startRow; row--) res.push(matrix[row][starCol])
        }
        startRow++
        endRow--
        starCol++
        endCol--
    }
    return res
};