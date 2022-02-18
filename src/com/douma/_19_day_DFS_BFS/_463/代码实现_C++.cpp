class Solution {
private:
    vector<vector<int>> grid;
    int rows, cols;
    vector<vector<bool>> visited;
    vector<vector<int>> dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    int res = 0;

    bool inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

public:
    int islandPerimeter(vector<vector<int>>& grid) {
        this->grid = grid;
        this->rows = grid.size();
        this->cols = grid[0].size();
        this->visited = vector<vector<bool>>(rows, vector<bool>(cols));

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    return bfs(row, col);
                }
            }
        }

        return 0;
    }

    // 1. 前序遍历
    void dfs1(int row, int col) {
        if (!inArea(row, col) || grid[row][col] == 0 || visited[row][col]) {
            return;
        }

        visited[row][col] = true;

        for (vector<int> dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (!inArea(nextRow, nextCol) || grid[nextRow][nextCol] == 0) {
                res += 1;
            }
            dfs1(nextRow, nextCol);
        }
    }

    // 2. 后序遍历
    int dfs(int row, int col) {
        if (!inArea(row, col) || grid[row][col] == 0) return 1;
        if (visited[row][col]) return 0;

        visited[row][col] = true;

        int res = 0;
        for (vector<int> dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            res += dfs(nextRow, nextCol);
        }

        return res;
    }

     // 3. 广度优先遍历
    int bfs(int row, int col) {
        int res = 0;
        queue<vector<int>> q;
        q.push({row, col});
        visited[row][col] = true;
        while (!q.empty()) {
            vector<int> curr = q.front();
            q.pop();
            for (vector<int> dir : dirs) {
                int nextRow = curr[0] + dir[0];
                int nextCol = curr[1] + dir[1];
                if (!inArea(nextRow, nextCol) || grid[nextRow][nextCol] == 0) {
                    res += 1;
                } else if (!visited[nextRow][nextCol]) {
                    q.push({nextRow, nextCol});
                    visited[nextRow][nextCol] = true;
                }
            }
        }
        return res;
    }
};