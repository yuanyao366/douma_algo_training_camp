/**
 * @param {number[][]} image
 * @param {number} sr
 * @param {number} sc
 * @param {number} newColor
 * @return {number[][]}
 */
// DFS
var floodFill1 = function(image, sr, sc, newColor) {
    const oldColor = image[sr][sc]
    if (oldColor == newColor) {
        return image;
    }

    const dirs = [[-1, 0], [1, 0], [0, -1], [0, 1]]
    const rows = image.length, cols = image[0].length

    const dfs = (row, col) => {
        if (row < 0 || row >= rows || col < 0 || col >= cols
                || image[row][col] != oldColor) {
            return
        }

        image[row][col] = newColor

        for (const dir of dirs) {
            const nextRow = row + dir[0]
            const nextCol = col + dir[1]
            dfs(nextRow, nextCol)
        }
    }

    dfs(sr, sc);
    return image;
};

// BFS
var floodFill = function(image, sr, sc, newColor) {
    const oldColor = image[sr][sc]
    if (oldColor == newColor) {
        return image;
    }

    const dirs = [[-1, 0], [1, 0], [0, -1], [0, 1]]
    const rows = image.length, cols = image[0].length

    const queue = []
    queue.push([sr, sc])
    image[sr][sc] = newColor
    while (queue.length) {
        const curr = queue.shift()
        for (let dir of dirs) {
            const nextRow = curr[0] + dir[0]
            const nextCol = curr[1] + dir[1]
            if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                        && image[nextRow][nextCol] == oldColor) {
                queue.push([nextRow, nextCol])
                image[nextRow][nextCol] = newColor
            }
        }
    }
    return image
}


