/**
 * @param {number[][]} grid
 * @return {number}
 */
var matrixScore = function(grid) {
    const rows = grid.length, cols = grid[0].length
    for (let row = 0; row < rows; row++) {
        if (grid[row][0] == 0) {
            for (let col = 0; col < cols; col++) {
                grid[row][col] ^= 1
            }
        }
    }

    let res = 0
    for (let col = 0; col < cols; col++) {
        let cnt = 0
        for (let row = 0; row < rows; row++) {
            cnt += grid[row][col]
        }

        const maxOneCnt = Math.max(cnt, rows - cnt)
        res += maxOneCnt * (1 << (cols - col - 1))
    }
    return res
};