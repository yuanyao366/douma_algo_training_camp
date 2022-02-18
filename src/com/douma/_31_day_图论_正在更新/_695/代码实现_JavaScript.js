/**
 * @param {number[][]} grid
 * @return {number}
 */
var maxAreaOfIsland = function(grid) {
    const dirs = [[-1, 0], [1, 0], [0, -1], [0, 1]]

    const rows = grid.length, cols = grid[0].length
    const visited = new Array(rows).fill(0).map(() => new Array(cols).fill(false))

    const inArea = (row, col) => {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    const dfs = (row, col) => {
        if (!inArea(row, col) || grid[row][col] == 0 || visited[row][col]) {
            return 0
        }

        visited[row][col] = true
        let res = 0
        for (let dir of dirs) {
            const nextRow = row + dir[0]
            const nextCol = col + dir[1]
            res += dfs(nextRow, nextCol)
        }
        return 1 + res
    }

    let ans = 0
    for (let row = 0; row < rows; row++) {
        for (let col = 0; col < cols; col++) {
            if (grid[row][col] == 1 && !visited[row][col]) {
                ans = Math.max(dfs(row, col), ans)
            }
        }
    }

    return ans
};