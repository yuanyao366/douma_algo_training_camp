/**
 * @param {number[][]} grid
 * @return {number}
 */

var islandPerimeter = function(grid) {
    const dirs = [[-1, 0], [1, 0], [0, -1], [0, 1]]

    const rows = grid.length, cols = grid[0].length
    const visited = new Array(rows).fill(0).map(() => new Array(cols).fill(false))

    const inArea = (row, col) => {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    let ans = 0;
    // 1. 前序遍历
    const dfs1 = (row, col) => {
        if (!inArea(row, col) || grid[row][col] == 0 || visited[row][col]) {
            return
        }

        visited[row][col] = true

        for (let dir of dirs) {
            const nextRow = row + dir[0]
            const nextCol = col + dir[1]
            if (!inArea(nextRow, nextCol) || grid[nextRow][nextCol] == 0) {
                ans += 1
            }
            dfs1(nextRow, nextCol)
        }
    }

    // 2. 后序遍历
    const dfs = (row, col) => {
        if (!inArea(row, col) || grid[row][col] == 0) {
            return 1
        }
        if (visited[row][col]) return 0

        visited[row][col] = true

        let res = 0
        for (let dir of dirs) {
            const nextRow = row + dir[0]
            const nextCol = col + dir[1]
            res += dfs(nextRow, nextCol)
        }
        return res
    }

    // 3. 广度优先遍历
    const bfs = (row, col) => {
        let res = 0
        const queue = []
        queue.push([row, col])
        visited[row][col] = true
        while (queue.length) {
            const curr = queue.shift()
            for (let dir of dirs) {
                const nextRow = curr[0] + dir[0]
                const nextCol = curr[1] + dir[1]
                if (!inArea(nextRow, nextCol) || grid[nextRow][nextCol] == 0) {
                    res += 1
                } else if (!visited[nextRow][nextCol]) {
                    queue.push([nextRow, nextCol])
                    visited[nextRow][nextCol] = true
                }
            }
        }
        return res
    }

    for (let row = 0; row < rows; row++) {
        for (let col = 0; col < cols; col++) {
            if (grid[row][col] == 1) {
                return bfs(row, col)
            }
        }
    }
    return 0
};