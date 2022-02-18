/**
 * @param {number} n
 * @return {string[][]}
 */
var solveNQueens = function(n) {
    const rows = new Array(n).fill(0)
    const cols = new Array(n).fill(0)
    const mains = new Array(2 * n - 1).fill(0)
    const secondary = new Array(2 * n - 1).fill(0)

    const output = []

    // 回溯，在每一行中放置一个皇后
    const dfs = (row) => {
        if (row >= n) return
        // 分别尝试在当前行的每一列中放置皇后
        for (let col = 0; col < n; col++) {
            if (isNotUnderAttack(row, col)) {
                // 选择，在当前的位置上放置皇后
                placeQueen(row, col)
                // 当前行是最后一行，则找到了一个解决方案
                if (row == n - 1) addSolution()
                // 在下一行中放置皇后
                dfs(row + 1)
                // 撤销，回溯，即将当前位置的皇后去掉
                removeQueen(row, col)
            }
        }
    }

    // 在指定的位置上放置皇后
    const placeQueen = (row, col) => {
        // 在 row 行，col 列 放置皇后
        rows[row] = col
        // 当前位置的列方向已经有皇后了
        cols[col] = 1
        // 当前位置的主对角线方向已经有皇后了
        mains[row - col + n - 1] = 1
        // 当前位置的次对角线方向已经有皇后了
        secondary[row + col] = 1
    }

    const removeQueen = (row, col) => {
        // 移除 row 行上的皇后，
        // 其实 row 行上的皇后可以不移除的，因为我们是一行一行存储皇后的，所以每一行肯定会有一个皇后的
        // 而且在 isNotUnderAttack 这个方法中都没有用到 rows[row] 这个值
        // 所以下面的代码可以注释掉的
        // rows[row] = 0;

        // 当前位置的列方向没有皇后了
        cols[col] = 0
        // 当前位置的主对角线方向没有皇后了
        mains[row - col + n - 1] = 0
        // 当前位置的次对角线方向没有皇后了
        secondary[row + col] = 0
    }

    // 判断 row 行，col 列这个位置有没有被其他方向的皇后攻击
    const isNotUnderAttack = (row, col) => {
        // 判断的逻辑是：
        //      1. 当前位置的这一列方向没有皇后攻击
        //      2. 当前位置的主对角线方向没有皇后攻击
        //      3. 当前位置的次对角线方向没有皇后攻击
        const res = cols[col] + mains[row - col + n - 1] + secondary[row + col]
        // 如果三个方向都没有攻击的话，则 res = 0，即当前位置不被任何的皇后攻击
        return res == 0
    }

    const addSolution = () => {
        const solution = []
        for (let i = 0; i < n; i++) {
            const col = rows[i]
            let sb = "";
            for(let j = 0; j < col; ++j) sb += "."
            sb += "Q"
            for(let j = 0; j < n - col - 1; ++j) sb += "."
            solution.push(sb)
        }
        output.push(solution)
    }

    dfs(0)

    return output
};