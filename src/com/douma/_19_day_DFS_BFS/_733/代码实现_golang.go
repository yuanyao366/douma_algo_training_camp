var dirs = [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

// DFS
func floodFill1(image [][]int, sr int, sc int, newColor int) [][]int {
    var oldColor = image[sr][sc]
    if oldColor == newColor {
        return image
    }

    var rows, cols = len(image), len(image[0])

    var dfs func(int, int)
    dfs = func(row int, col int) {
        if row < 0 || row >= rows || col < 0 || col >= cols || image[row][col] != oldColor {
            return
        }

        image[row][col] = newColor

        for _, dir := range dirs {
            var nextRow = row + dir[0]
            var nextCol = col + dir[1]
            dfs(nextRow, nextCol)
        }
    }

    dfs(sr, sc)

    return image
}


// BFS
func floodFill(image [][]int, sr int, sc int, newColor int) [][]int {
    var oldColor = image[sr][sc]
    if oldColor == newColor {
        return image
    }

    var rows, cols = len(image), len(image[0])
    var queue = list.New()
    queue.PushBack([2]int{sr, sc})
    image[sr][sc] = newColor

    for queue.Len() > 0 {
        var curr = queue.Remove(queue.Front()).([2]int)
        var row, col = curr[0], curr[1]

        for _, dir := range dirs {
            var nextRow = row + dir[0]
            var nextCol = col + dir[1]
            if nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols && image[nextRow][nextCol] == oldColor {
                queue.PushBack([2]int{nextRow, nextCol})
                image[nextRow][nextCol] = newColor
            }
        }
    }

    return image
}