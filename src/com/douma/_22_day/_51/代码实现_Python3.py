from typing import List


class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        rows = [0] * n
        cols = [0] * n
        mains = [0] * (2 * n - 1)
        secondary = [0] * (2 * n - 1)

        output = []

        def dfs(row) -> None:
            if row >= n: return
            for col in range(n):
                if isNotUnderAttack(row, col):
                    placeQueen(row, col)
                    if row == n - 1: addSolution()
                    dfs(row + 1)
                    removeQueen(row, col)

        def placeQueen(row, col):
            # 在 row 行，col 列 放置皇后
            rows[row] = col
            # 当前位置的列方向已经有皇后了
            cols[col] = 1
            # 当前位置的主对角线方向已经有皇后了
            mains[row - col + n - 1] = 1
            # 当前位置的次对角线方向已经有皇后了
            secondary[row + col] = 1

        def removeQueen(row, col):
            # 移除 row 行上的皇后，
            # 其实 row 行上的皇后可以不移除的，因为我们是一行一行存储皇后的，所以每一行肯定会有一个皇后的
            # 而且在 isNotUnderAttack 这个方法中都没有用到 rows[row] 这个值
            # 所以下面的代码可以注释掉的
            # rows[row] = 0;

            # 当前位置的列方向没有皇后了
            cols[col] = 0
            # 当前位置的主对角线方向没有皇后了
            mains[row - col + n - 1] = 0
            # 当前位置的次对角线方向没有皇后了
            secondary[row + col] = 0

        # 判断 row 行，col 列这个位置有没有被其他方向的皇后攻击
        def isNotUnderAttack(row, col):
            # 判断的逻辑是：
            #      1. 当前位置的这一列方向没有皇后攻击
            #      2. 当前位置的主对角线方向没有皇后攻击
            #      3. 当前位置的次对角线方向没有皇后攻击
            res = cols[col] + mains[row - col + n - 1] + secondary[row + col]
            # 如果三个方向都没有攻击的话，则 res = 0，即当前位置不被任何的皇后攻击
            return res == 0

        def addSolution():
            solution = []
            for i in range(n):
                col = rows[i]
                sb = ""
                for j in range(col): sb += "."
                sb += "Q"
                for j in range(n - col - 1): sb += "."
                solution.append(sb)
            output.append(solution)

        dfs(0)
        return output