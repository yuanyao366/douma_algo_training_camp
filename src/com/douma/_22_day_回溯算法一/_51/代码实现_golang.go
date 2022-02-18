func solveNQueens(n int) [][]string {
    var rows, cols = make([]int, n), make([]int, n)
    var mains, secondary = make([]int, 2 * n - 1), make([]int, 2 * n - 1)
    var output = make([][]string, 0)

    // 回溯，在每一行中放置一个皇后
    var dfs func(int)
    dfs = func(row int) {
        if row >= n {
            return
        }

        // 分别尝试在当前行的每一列中放置皇后
        for col := 0; col < n; col++ {
            if isNotUnderAttack(cols, mains, secondary, row, col, n) {
                // 选择，在当前的位置上放置皇后
                placeQueen(rows, cols, mains, secondary, row, col, n)
                // 当前行是最后一行，则找到了一个解决方案
                if row == n - 1 {
                    addSolution(rows, &output, n)
                }
                // 在下一行中放置皇后
                dfs(row + 1)
                // 撤销，回溯，即将当前位置的皇后去掉
                removeQueen(rows, cols, mains, secondary, row, col, n)
            }
        }
    }

    dfs(0)
    return output
}

func placeQueen(rows []int, cols []int, mains []int, secondary []int, row int, col int, n int) {
    // 在 row 行，col 列 放置皇后
    rows[row] = col
    // 当前位置的列方向已经有皇后了
    cols[col] = 1
    // 当前位置的主对角线方向已经有皇后了
    mains[row - col + n - 1] = 1
    // 当前位置的次对角线方向已经有皇后了
    secondary[row + col] = 1
}

func removeQueen(rows []int, cols []int, mains []int, secondary []int, row int, col int, n int) {
    // 移除 row 行上的皇后，
    // 其实 row 行上的皇后可以不移除的，因为我们是一行一行存储皇后的，所以每一行肯定会有一个皇后的
    // 而且在 isNotUnderAttack 这个方法中都没有用到 rows[row] 这个值
    // 所以下面的代码可以注释掉的
    // rows[row] = 0

    // 当前位置的列方向没有皇后了
    cols[col] = 0
    // 当前位置的主对角线方向没有皇后了
    mains[row - col + n - 1] = 0
    // 当前位置的次对角线方向没有皇后了
    secondary[row + col] = 0
}

// 判断 row 行，col 列这个位置有没有被其他方向的皇后攻击
func isNotUnderAttack(cols []int, mains []int, secondary []int, row int, col int, n int) bool {
    // 判断的逻辑是：
    //      1. 当前位置的这一列方向没有皇后攻击
    //      2. 当前位置的主对角线方向没有皇后攻击
    //      3. 当前位置的次对角线方向没有皇后攻击
    var res = cols[col] + mains[row - col + n - 1] + secondary[row + col]
    // 如果三个方向都没有攻击的话，则 res = 0，即当前位置不被任何的皇后攻击
    return res == 0
}

func addSolution(rows []int, output *[][]string, n int) {
    var solution = make([]string, 0)
    for i := 0; i < n; i++ {
        var col = rows[i]
        var res = ""
        for j := 0; j < col; j++ {
            res += "."
        }
        res += "Q"
        for j := 0; j < n - col - 1; j++ {
            res += "."
        }
        solution = append(solution, res)
    }
    *output = append(*output, solution)
}