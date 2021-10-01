# 抖码算法，让算法学习变得简单有趣
# 作者：老汤


# issue 讨论：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4CJHD
if __name__ == '__main__':

    dirs = [[0, 1], [1, 0], [-1, 0], [0, -1]]
    # 1. 输入数据解析
    n, k = map(lambda a: int(a), str(input()).split(" "))
    grid = [[0] * n for _ in range(n)]
    for i in range(n):
        data = str(input()).split(" ")
        for j in range(n):
            grid[i][j] = int(data[j])

    ans = 2 ** 31 - 1


    def dfs(row, col, res):
        # 1. 如果已经走到了 k，说明找到了一条路径，那么更新结果
        if grid[row][col] == k:
            global ans
            ans = min(ans, res)
            return

        # 2. 上下左右四个方式不断的去尝试跳
        for dir in dirs:
            nextRow = row + dir[0]
            nextCol = col + dir[1]
            if (0 <= nextRow < n and 0 <= nextCol < n and
                    not visited[nextRow][nextCol] and
                    grid[nextRow][nextCol] == grid[row][col] + 1):
                visited[nextRow][nextCol] = True
                # 计算两个点的曼哈顿距离
                mahadunDis = abs(nextRow - row) + abs(nextCol - col)
                dfs(nextRow, nextCol, res + mahadunDis)

    # 2. 使用 floodfill 算法求解最小花费
    for row in range(n):
        for col in range(n):
            # 必须从值等于 1 的元素开始
            if grid[row][col] == 1:
                visited = [[False] * n for _ in range(n)]
                dfs(row, col, 0)

    if ans == 2 ** 31 - 1:
        print(-1)
    else:
        print(ans)
