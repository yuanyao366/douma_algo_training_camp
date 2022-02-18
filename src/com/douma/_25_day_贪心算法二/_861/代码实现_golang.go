func matrixScore(grid [][]int) int {
    var rows, cols = len(grid), len(grid[0])

    // 使得每一行都从 1 开头
    for row := 0; row < rows; row++ {
        if grid[row][0] == 0 {
            // 行翻转
            for col := 0; col < cols; col++ {
                grid[row][col] ^= 1
            }
        }
    }

    var res = 0
    // 1 的数量越多，得到的数值越大
    for col := 0; col < cols; col++ {
       var cnt = 0
       // 统计第 col 列有多少个 1。
       for row := 0; row < rows; row++ {
           cnt += grid[row][col]
       }

       var maxOneCnt = rows - cnt
       if cnt > maxOneCnt {
           maxOneCnt = cnt
       }

       res += maxOneCnt * (1 << (cols - col - 1))
    }

    return res
}