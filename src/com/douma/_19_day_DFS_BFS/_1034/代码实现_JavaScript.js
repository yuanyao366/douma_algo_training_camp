/**
 * @param {number[][]} grid
 * @param {number} r0
 * @param {number} c0
 * @param {number} color
 * @return {number[][]}
 */
var colorBorder = function(grid, r0, c0, color) {
    const currColor = grid[r0][c0]
    if (currColor == color) return grid

    const dirs = [[-1, 0], [1, 0], [0, -1], [0, 1]]
    const rows = grid.length, cols = grid[0].length
    const visited = new Array(rows).fill(0).map(() => new Array(cols).fill(false))

    const inArea = (row, col) => {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    const dfs = (row, col) => {
        visited[row][col] = true

        for (let dir of dirs) {
            const nextRow = row + dir[0]
            const nextCol = col + dir[1]
            if (!inArea(nextRow, nextCol)
                    || (grid[nextRow][nextCol] != currColor && !visited[nextRow][nextCol])) {
                grid[row][col] = color;
            } else if (!visited[nextRow][nextCol]){
                dfs(nextRow, nextCol);
            }
        }
    }

    dfs(r0, c0)
    return grid
};