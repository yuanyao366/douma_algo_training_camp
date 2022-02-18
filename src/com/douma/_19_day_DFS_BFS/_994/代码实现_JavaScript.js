/**
 * @param {number[][]} grid
 * @return {number}
 */
var orangesRotting = function(grid) {
    const dirs = [[-1, 0], [1, 0], [0, -1], [0, 1]]
    const rows = grid.length, cols = grid[0].length

    const levelsMap = new Map()
    const queue = []
    for (let row = 0; row < rows; row++) {
        for (let col = 0; col < cols; col++) {
            if (grid[row][col] == 2) {
                const index = row * cols + col;
                queue.push(index);
                levelsMap.set(index, 0);
            }
        }
    }

    let ans = 0
    while (queue.length) {
        const currIndex = queue.shift();
        const row = Math.floor(currIndex / cols);
        const col = currIndex % cols;
        for (const dir of dirs) {
            const nextRow = row + dir[0];
            const nextCol = col + dir[1];

            if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                    && grid[nextRow][nextCol] == 1) {
                grid[nextRow][nextCol] = 2;
                const index = nextRow * cols + nextCol;
                queue.push(index);

                // 更新所属层信息
                ans = levelsMap.get(currIndex) + 1;
                levelsMap.set(index, ans);
            }
        }
    }

    for (let row = 0; row < rows; row++) {
        for (let col = 0; col < cols; col++) {
            if (grid[row][col] == 1) return -1;
        }
    }

    return ans;
};